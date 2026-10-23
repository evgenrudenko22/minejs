package net.evgenru22.mineJS.commands;

import com.google.common.base.Preconditions;
import org.graalvm.polyglot.Value;

public class CommandRegistrar {
    private final String path;

    public CommandRegistrar(String path) {
        this.path = path;
    }

    @SuppressWarnings("unused")
    public CommandBuilder createCommand(String name, Value executor) {
        Preconditions.checkArgument(executor.canExecute(), "Second argument must be callable");
        return new CommandBuilder(path, name, executor);
    }
}
