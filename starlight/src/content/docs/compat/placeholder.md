---
title: PlaceholderAPI
descriptiom: How to parse placeholders from PlaceholderAPI
---

MineJS has built-in compatibility with [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/). When PlaceholderAPI is present on the server, MineJS registers a `placeholder(player, text)` function that takes a player and a string containing a placeholder, and returns the parsed text.

## Example
* Simple example
    ```js
    let player = ...;
    console.log(placeholder(player, "Player %player_name% has %player_total_exp% exp."));
    ```
* Combine `placeholder` with `minimessage`
    ```js
    let player = ...;
    player.sendMessage(minimessage(placeholder(player, "<blue> Hello, <i><yellow>%player_name%</i>! You are in <gold>%player_world%")));
    ```