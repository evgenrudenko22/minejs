package net.evgenru22.mineJS.events;

import com.google.common.base.CaseFormat;
import com.google.common.reflect.ClassPath;
import net.evgenru22.mineJS.MineJS;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.graalvm.polyglot.Value;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsRegistry {

    public static final Map<String, Class<? extends Event>> EVENTS = new HashMap<>();

    private static final List<String> TARGET_PACKAGES = List.of(
            "org.bukkit.event",
            "com.destroystockyo.paper.event",
            "io.papermc.paper.event"
    );

    static {
        MineJS.getInstance().getLogger().info("Loading server events...");
        if (Bukkit.getPluginManager().isPluginEnabled("Oraxen")) {
            MineJS.getInstance().getLogger().info("Oraxen is present on the server");
            TARGET_PACKAGES.add("io.th0rgal.oraxen.api.events");
        }

        try {
            ClassLoader classLoader = MineJS.getInstance().getClass().getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            for (String pkg : TARGET_PACKAGES) {
                for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive(pkg)) {
                    try {
                        Class<?> clazz = classInfo.load();

                        if (Event.class.isAssignableFrom(clazz)
                                && !clazz.equals(Event.class)
                                && !Modifier.isAbstract(clazz.getModifiers())) {
                            @SuppressWarnings("unchecked")
                            Class<? extends Event> eventClass = (Class<? extends Event>) clazz;
                            String eventName = CaseFormat.UPPER_CAMEL
                                    .to(CaseFormat.LOWER_UNDERSCORE,
                                            eventClass.getSimpleName().replaceFirst("Event$", "")
                                    ).replace("_", " ");
                            EVENTS.put(eventName, eventClass);
                        }
                    } catch (Throwable e) {
                        MineJS.getInstance().getLogger().warning("Cannot load event-class: " + e.getMessage());
                    }
                }
            }

            MineJS.getInstance().getLogger().info("Loading event successful(loaded " + EVENTS.size() + " events)");
        } catch (Exception e) {
            MineJS.getInstance().getLogger().warning("Cannot load event-classes: " + e.getMessage());
        }
    }

    public static Listener registerEvent(String eventClassName, Value callback) {
        Class<? extends Event> eventClass = EVENTS.get(eventClassName);

        if (eventClass == null) {
            MineJS.getInstance().getLogger().warning("Cannot load event: " + eventClassName);
            return null;
        }

        EventExecutor executor = ((listener, event) -> callback.execute(event));

        Listener listener = new Listener() {};

        MineJS.getInstance().getServer().getPluginManager().registerEvent(
                eventClass,
                listener,
                EventPriority.NORMAL,
                executor,
                MineJS.getInstance()
        );

        return listener;
    }
}
