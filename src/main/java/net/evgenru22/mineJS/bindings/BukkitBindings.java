package net.evgenru22.mineJS.bindings;

import com.google.common.base.CaseFormat;
import io.th0rgal.oraxen.api.OraxenBlocks;
import io.th0rgal.oraxen.api.OraxenFurniture;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.mechanics.provided.gameplay.furniture.FurnitureMechanic;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.graalvm.polyglot.Value;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BukkitBindings {

    public static void setupBindings(Value bindings) {
        // Enums
        putEnum(bindings, Material.class);
        putEnum(bindings, Action.class);
        putEnum(bindings, Sound.class);
        putEnum(bindings, Particle.class);
        putEnum(bindings, Effect.class);
        putEnum(bindings, EntityType.class);

        // Utilities
        bindings.putMember("item", new ItemStackBinding());
        bindings.putMember("server", new ServerBindings());
        bindings.putMember("minimessage", (Function<String, Component>) (message) -> MiniMessage.miniMessage().deserialize(message));

        // Dependencies
        bindIfPresent("PlaceholderAPI", () ->
            bindings.putMember("placeholder",
                    (BiFunction<Player, String, String>) PlaceholderAPI::setPlaceholders)
        );

        bindIfPresent("Oraxen", () -> {
            bindings.putMember("oraxenItem", (Function<String, ItemStack>) (id) -> OraxenItems.getItemById(id).build());
            bindings.putMember("oraxenId", (Function<ItemStack, String>) OraxenItems::getIdByItem);
            bindings.putMember("oraxenBlock", (Function<Location, String>) (l) -> OraxenBlocks.getOraxenBlock(l).getItemID());
            bindings.putMember("oraxenFurniture", (Function<Location, String>) (l) -> {
                FurnitureMechanic m = OraxenFurniture.getFurnitureMechanic(l.getBlock());
                return m == null ? null : m.getItemID();
            });

            bindings.putMember("oraxenPlaceBlock", (BiConsumer<String, Location>) OraxenBlocks::place);
            bindings.putMember("oraxenPlaceFurniture", (BiConsumer<String, Location>) (id, l) -> OraxenFurniture.place(id, l, Rotation.NONE, BlockFace.UP));
            bindings.putMember("oraxenRemoveBlock", (BiConsumer<String, Location>) (id, l) -> OraxenBlocks.remove(l, null));
            bindings.putMember("oraxenRemoveFurniture", (BiConsumer<String, Location>) (id, l) -> OraxenFurniture.remove(l, null));
        });
    }

    private static void bindIfPresent(String plugin, Runnable register){
        if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
            register.run();
        }
    }

    private static <T> void putEnum(Value bindings, Class<T> _enum) {
        if (!_enum.isEnum()) return;
        Map<String, T> enumMap = new HashMap<>();
        for (T constant : _enum.getEnumConstants()) {
            enumMap.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, constant.toString()), constant);
        }
        bindings.putMember(_enum.getSimpleName(), Value.asValue(enumMap));
    }
}
