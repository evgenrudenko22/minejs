---
title: Examples
---
This page shows what we can do with events in MineJS

### Player Interact
```js
events.on("player interact", (event) => {
    console.log(event.getPlayer().getName())
})
```
This example show how we can handle `PlayerInteractEvent`. It simply prints name of player who interact to server console.

### Inventory Click
```js
events.on("inventory click", (event) => {
    if (event.getSlot() == 0) {
        event.setCancelled(true);
    }
})
```
This example show how we can handle `InventoryClickEvent`. It's blocks **0** slot of all invetories.

### Block Place
```js
events.on("block place", (event) => {
    if (!event.getPlayer().hasPermission("server.can.place")) return;
    event.setCancelled(true);
})
```
This example show how we can handle `BlockPlaceEvent`. It's cancel all events from players who don't have `server.can.place` permission

### Other events
You can find useful events on [SpigotMC Wiki](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/). Check the **Methods Summary** section for information about the event's methods.