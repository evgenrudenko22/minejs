package net.evgenru22.mineJS;

import com.google.common.base.Preconditions;
import net.evgenru22.mineJS.utils.Files;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;

public class ScriptCommand implements CommandExecutor, TabCompleter {

    private static final Component HELP_MESSAGE = Component.text("=============== MINEJS ===============").color(NamedTextColor.GREEN)
            .appendNewline().append(Component.text("/script load <filename> - load script").color(NamedTextColor.BLUE))
            .appendNewline().append(Component.text("/script unload <filename> - unload script").color(NamedTextColor.BLUE))
            .appendNewline().append(Component.text("/script reload <filename> - reload script").color(NamedTextColor.BLUE))
            .appendNewline().append(Component.text("/script help - show this message").color(NamedTextColor.BLUE));

    public ScriptCommand(@Nullable PluginCommand command) {
        Preconditions.checkArgument(command != null, "/script command is not registered");
        command.setExecutor(this);
        command.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage(HELP_MESSAGE);
        } else if (args.length == 1 && args[0].equals("help")) {
            sender.sendMessage(HELP_MESSAGE);
        } else if (args.length == 2) {
            String filename = args[1];
            switch (args[0]) {
                case "load":
                    ScriptLoader.loadScript(filename);
                    break;
                case "unload":
                    ScriptLoader.unloadScript(filename);
                    break;
                case "reload":
                    ScriptLoader.reloadScript(filename);
                    break;
                default:
                    sender.sendMessage(HELP_MESSAGE);
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of("help", "load", "unload", "reload");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "load":
                    return Files.findJsFiles();
                case "unload", "reload":
                    return ScriptLoader.activeScripts.keySet().stream().toList();
            }
        }

        return null;
    }
}
