---
title: Oraxen
description: MineJS compatibility with Oraxen
---

Also MineJS has compatibility with [Oraxen](https://oraxen.com/) plugin. You can get Oraxen items, place block and furnitures and handle Oraxen events.

## Getters
### `oraxenItem(id)`
Create Oraxen item by id. Returns ItemStack
### `oraxenId(item)`
Gets id of given Oraxen item. If it isn't Oraxen item return null
### `oraxenBlock(location)`
Gets id of Oraxen block on given location. If it isn't Oraxen block return null
### `oraxenFurniture(location)`
Gets id of Oraxen furniture on given location. If it isn't Oraxen furniture return null
## Place/remove
### `oraxenPlaceBlock(id, location)`
Place Oraxen block by given id on given location
### `oraxenPlaceFurniture(id, location)`
Place Oraxen furniture by given id on given location
### `oraxenRemoveBlock(location)`
Break Oraxen block on given location
### `oraxenRemoveFurniture(location)`
Break Oraxen furniture on given location
## Events
See list of Oraxen events on [Oraxen Docs](https://docs.oraxen.com/developers/api#events)