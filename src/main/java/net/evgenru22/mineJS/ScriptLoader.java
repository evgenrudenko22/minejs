package net.evgenru22.mineJS;

import net.evgenru22.mineJS.bindings.BukkitBindings;
import net.evgenru22.mineJS.commands.CommandManager;
import net.evgenru22.mineJS.commands.CommandRegistrar;
import net.evgenru22.mineJS.events.EventCollector;
import org.graalvm.polyglot.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ScriptLoader {

    public record LoadedScript(String filePath, Context context, EventCollector events) {
    }

    private static final Engine engine = Engine.newBuilder()
            .option("engine.WarnInterpreterOnly", "false")
            .build();
    public static final Map<String, LoadedScript> activeScripts = new HashMap<>();

    private static String readFile(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getFile(String path) {
        return Path.of(MineJS.getInstance().getDataPath().toString(), "scripts", path).toFile();
    }

    public static void loadScript(String path) {
        if (activeScripts.containsKey(path)) {
            MineJS.getInstance().getLogger().warning("File has already been loaded");
            return;
        }
        File file = getFile(path);
        if (!file.isFile()) {
            MineJS.getInstance().getLogger().warning("Not valid file");
            return;
        }

        // check file extension

        if (!file.getName().toLowerCase().endsWith(".js")) {
            MineJS.getInstance().getLogger().warning("It's not a js file");
            return;
        }

        LoadedScript scriptInfo = new LoadedScript(
                path,
                Context.newBuilder("js")
                        .engine(engine)
                        .allowHostAccess(HostAccess.ALL)
                        .allowHostClassLookup(null)
                        .build(),
                new EventCollector()
        );

        Value bindings = scriptInfo.context.getBindings("js");
        bindings.putMember("events",
                scriptInfo.events);
        bindings.putMember("commands",
                new CommandRegistrar(path));
        BukkitBindings.setupBindings(scriptInfo.context.getBindings("js"));

        try {
            String content = readFile(file);
            scriptInfo.context.eval("js", content);

            activeScripts.put(path, scriptInfo);
            MineJS.getInstance().getLogger().info("File " + file.getName() + " loaded successful");
        } catch (PolyglotException e) {
            MineJS.getInstance().getLogger().warning("File " + file.getName() + " is not loaded, error: " + e.getMessage());
            e.printStackTrace();
            scriptInfo.context.close();
        }
    }

    public static void unloadScript(String path) {
        LoadedScript scriptInfo = activeScripts.get(path);
        if (scriptInfo == null) {
            MineJS.getInstance().getLogger().warning("File is not loaded");
            return;
        }

        scriptInfo.events.unregisterAll();
        CommandManager.unregisterCommand(path);

        scriptInfo.context.close();

        activeScripts.remove(path);
        MineJS.getInstance().getLogger().info("File " + getFile(path).getName() + " unloaded successful");
    }

    public static void reloadScript(String path) {
        MineJS.getInstance().getLogger().info("Reloading file " + path);

        unloadScript(path);

        loadScript(path);
    }
}
