package net.evgenru22.mineJS.events;

import net.evgenru22.mineJS.MineJS;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.graalvm.polyglot.Value;

import java.util.HashMap;
import java.util.Map;

public class EventCollector {

    private final Map<String, Listener> eventListeners = new HashMap<>();

    @SuppressWarnings("unused")
    public void on(String event, Value value) {
        if (!value.canExecute()) {
            MineJS.getInstance().getLogger().warning("Error registering event: second argument must be executable(function, closure)");
            return;
        }

        eventListeners.put(event, EventsRegistry.registerEvent(event, value));
    }

    public void unregisterAll() {
        for (var event : eventListeners.keySet()) {
            HandlerList.unregisterAll(eventListeners.get(event));
        }
        eventListeners.clear();
    }
}
