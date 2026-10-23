package net.evgenru22.mineJS.bindings;

import com.google.common.base.Preconditions;
import net.evgenru22.mineJS.MineJS;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;
import org.graalvm.polyglot.Value;

import java.util.List;

@SuppressWarnings("unused")
public class ServerBindings {
    @SuppressWarnings("unchecked")
    public List<Player> getOnlinePlayers() {
        return (List<Player>) Bukkit.getOnlinePlayers();
    }

    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    public BukkitTask runTaskLater(Value callback, long delay) {
        Preconditions.checkArgument(callback.canExecute(), "First argument must be callable");
        return Bukkit.getScheduler().runTaskLater(MineJS.getInstance(), () -> callback.execute(), delay);
    }

    public BukkitTask runTaskTimer(Value callback, long delay, long period) {
        Preconditions.checkArgument(callback.canExecute(), "First argument must be callable");
        return Bukkit.getScheduler().runTaskTimer(MineJS.getInstance(), () -> callback.execute(), delay, period);
    }

    public Inventory createChest(int slots, String title) {
        return Bukkit.getServer().createInventory(null, slots, Component.text(title));
    }

    public Inventory createChest(int slots, Component title) {
        return Bukkit.getServer().createInventory(null, slots, title);
    }
}
