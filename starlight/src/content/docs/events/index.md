---
title: Basic
description: Some information about events
---

Registering event listeners in MineJS is very easy. Simply use global `events` and its `on(event, handler)` where event is string id of event and handler is function which has 1 argument - event data, and returning nothing. 
For examples see [Examples](/events/examples)

:::note
Event identifiers are formed from class names according to the following rule: class name -> remove `Event` from end -> lower underscore format -> replace '_' to ' '

For example `InventoryClickEvent` transform to `'inventory click'`
:::