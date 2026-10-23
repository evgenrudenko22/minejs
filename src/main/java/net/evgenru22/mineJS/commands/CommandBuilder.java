package net.evgenru22.mineJS.commands;

import org.graalvm.polyglot.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class CommandBuilder {
    private final String script;
    private final String name;
    private final Value executor;
    private String description;
    private String usage;
    private List<String> aliases;
    private String permission;

    public CommandBuilder(String script, String name, Value executor) {
        this.name = name;
        this.script = script;
        this.executor = executor;
    }

    public CommandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    public CommandBuilder setAliases(Value value) {
        if (!value.hasArrayElements()) return null;
        List<String> aliases = new ArrayList<>();
        for (int i = 0; i < value.getArraySize(); i++) {
            aliases.add(value.getArrayElement(i).asString());
        }
        this.aliases = aliases;
        return this;
    }

    public CommandBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public void register() {
        if (aliases == null) this.aliases = Collections.emptyList();
        CommandManager.registerCommand(script, name, executor, description, usage, aliases, permission);
    }
}
