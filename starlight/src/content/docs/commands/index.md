---
title: Basic
description: Some information about commands
---

For registering custom commands in MineJS use `commands` global with `createCommand(name, executor)` where *name* is String name of command and *executor* is function with 2 arguments and no return. To register created command you need call `register()` on result of `createCommand`

## Simple example
```js
commands.createCommand("hello", (s, args) => s.sendMessage(minimessage("<blue>Hello")))
    .register()
```
Example of a command that sends a blue `Hello` message to the player who used the command.