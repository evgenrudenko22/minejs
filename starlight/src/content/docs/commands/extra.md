---
title: Extra features
description: Some things you can do with commands
---

You can do something with result of `createCommand` function before calling `register()`. For example set description of command with `setDescription(description)` function.

### `setUsage(usage)`
Sets correct usage message of command. This is shown when the command is entered incorrectly.
### `setAliases(aliases)`
Pass an array of aliases for the command to this function. Like for `"balance"` command you can pass `["bal", "money"]`
### `setPermission(permission)`
Sets permission needed to use this command