package net.evgenru22.mineJS.bindings;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.graalvm.polyglot.Value;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ItemStackBinding {
    public ItemStack createItem(Material material) {
        return ItemStack.of(material);
    }

    public ItemStack createItem(Material material, int amount) {
        return ItemStack.of(material, amount);
    }

    public ItemStack createItem(Material material, int amount, String name) {
        return createItem(material, amount, Component.text(name));
    }

    public ItemStack createItem(Material material, int amount, Component name) {
        ItemStack stack = ItemStack.of(material, amount);
        stack.editMeta(meta ->
                meta.customName(name)
        );
        return stack;
    }

    public ItemStack createItem(Material material, int amount, String name, Value loreValue) {
        return createItem(material, amount, Component.text(name), loreValue);
    }

    public ItemStack createItem(Material material, int amount, Component name, Value loreValue) {
        if (!loreValue.hasArrayElements()) return null;
        List<Component> lore = new ArrayList<>();
        for (int i = 0; i < loreValue.getArraySize(); i++) {
            Value element = loreValue.getArrayElement(i);
            if (element.isString()) {
                lore.add(Component.text(element.asString()));
            } else if (element.isHostObject() && element.asHostObject() instanceof Component) {
                lore.add(element.asHostObject());
            } else {
                return null;
            }
        }

        ItemStack stack = ItemStack.of(material, amount);
        stack.editMeta(meta -> {
            meta.customName(name);
            meta.lore(lore);
        });
        return stack;
    }
}
