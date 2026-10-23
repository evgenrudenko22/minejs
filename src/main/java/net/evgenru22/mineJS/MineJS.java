package net.evgenru22.mineJS;

import net.evgenru22.mineJS.commands.CommandManager;
import net.evgenru22.mineJS.utils.Files;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MineJS extends JavaPlugin {
    private static MineJS INSTANCE = null;

    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();
        CommandManager.initCommandMap();

        new ScriptCommand(getServer().getPluginCommand("script"));

        List<String> files = Files.findJsFiles();
        if (files != null) {
            for (String file : files) {
                ScriptLoader.loadScript(file);
            }
        }
    }

    @Override
    public void onDisable() {
        for (var script : ScriptLoader.activeScripts.keySet()) {
            ScriptLoader.unloadScript(script);
        }
    }

    public static MineJS getInstance() {
        if (INSTANCE == null)
            throw new IllegalStateException("Plugin hasn't yet been initialize.");
        return INSTANCE;
    }
}
