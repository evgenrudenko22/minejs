package net.evgenru22.mineJS.commands;

import net.evgenru22.mineJS.MineJS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.graalvm.polyglot.Value;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

public class CommandManager {
    private static final Map<String, List<Command>> scriptCommands = new HashMap<>();
    private static CommandMap commandMap;

    public static void initCommandMap() {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap) field.get(Bukkit.getServer());
        } catch (Exception e) {
            MineJS.getInstance().getLogger().warning("Can't get command map of server");
        }
    }

    public static void registerCommand(String script, String name, Value value, String description,
                                       String usage, List<String> aliases, String permission) {
        Command cmd = new Command(name, description, usage, aliases) {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
                value.execute(sender, args);
                return true;
            }
        };
        cmd.setPermission(permission);

        commandMap.register(MineJS.getInstance().getName().toLowerCase(), cmd);
        scriptCommands.computeIfAbsent(script, k -> new ArrayList<>()).add(cmd);

        syncToPlayers();
    }

    public static void unregisterCommand(String script) {
        List<Command> commands = scriptCommands.remove(script);
        if (commands == null || commands.isEmpty()) return;

        try {
            Field knownCommandsField = null;
            Class<?> clazz = commandMap.getClass();

            while (clazz != null && knownCommandsField == null) {
                try {
                    knownCommandsField = clazz.getDeclaredField("knownCommands");
                } catch (NoSuchFieldException e) {
                    clazz = clazz.getSuperclass();
                }
            }

            if (knownCommandsField == null) {
                MineJS.getInstance().getLogger().warning("Can't find knownCommands field in CommandMap");
                return;
            }

            knownCommandsField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, Command> knownCommands = (Map<String, Command>) knownCommandsField.get(commandMap);

            for (Command cmd : commands) {
                knownCommands.values().removeIf(c -> c.equals(cmd));
                cmd.unregister(commandMap);
            }

            syncToPlayers();
        } catch (Exception e) {
            MineJS.getInstance().getLogger().warning("Can't unregister commands from script " + script + ", because: " + e.getMessage());
        }
    }

    private static void syncToPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.updateCommands();
        }
    }
}
