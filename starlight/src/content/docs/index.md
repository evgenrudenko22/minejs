---
title: Overview
description: General information about MineJS
sidebar:
    order: 0
---

## About
MineJS is a Minecraft server plugin that lets you write server logic in JavaScript instead of a custom scripting language. Drop a .js file into the scripts folder, load it in-game, and your code is running. No compiling, no restarts, no Java project setup.

If you like the "quick scripts, instant results" workflow of Skript but want real variables, functions, modules and the whole JavaScript ecosystem of tooling, MineJS is for you.

## Features
- **Modern JavaScript** powered by [GraalJS](https://www.graalvm.org/javascript/)
- **Hot reload in-game**: load, unload and reload scripts without restarting the server
- **Events**: subscribe to server events with a single line
- **Custom commands**: register commands with descriptions and usage
- **MiniMessage support** built in for rich, modern chat formatting
- **PlaceholderAPI** integration out of the box
- **Oraxen** integration out of the box

## How to install
1. Make sure your server runs **Java 21** or newer (required by Minecraft 1.21+).
2. Download the latest `MineJS.jar` from the Releases page.
3. Put the jar into your server's `plugins/` folder.
4. Start the server. MineJS will create its folder in `plugins/MineJS/`.
5. Put your `.js` scripts into the scripts folder and load them with /script load <filename>.