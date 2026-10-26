---
title: Server utils
description: Utils to work with Bukkit Server api
---
There is `server` object in MineJS scripts. It has some methods to work with server

### `getOnlinePlayers()`
Return array of Player instances. For methods of Player see [SpigotMC](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/Player.html)

### `getPlayer(nick)`
Return Player instance by nickname of player

### `runTaskLater(callback, delay)` and `runTaskTimer(callback, delay, period)`
Functions for performing tasks later or periodically, as appropriate. They return BukkitTask, that can be cancelled.

### `createChest(slots, title)`
Functions to create chest inventory with given number of slots and title. Title can be String or Component.