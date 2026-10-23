<div align="center">

# MineJS

**Script your Minecraft server with modern JavaScript.**
Like Skript, but with a real programming language, powered by GraalJS.

![License](https://img.shields.io/github/license/evgenrudenko22/minejs?style=flat-square)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.x-brightgreen?style=flat-square)
![Platform](https://img.shields.io/badge/Paper-supported-blue?style=flat-square)
![Release](https://img.shields.io/github/v/release/evgenrudenko22/minejs?style=flat-square)

[Documentation](https://evgenrudenko22.github.io/MineJS) · [Download](https://github.com/YOUR_USERNAME/MineJS/releases) · [Report a bug](https://github.com/evgenrudenko22/MineJS/issues)

</div>

---

## What is MineJS?

MineJS is a Minecraft server plugin that lets you write server logic in **JavaScript** instead of a custom scripting language. Drop a `.js` file into the scripts folder, load it in-game, and your code is running. No compiling, no restarts, no Java project setup.

If you like the "quick scripts, instant results" workflow of Skript but want real variables, functions, modules and the whole JavaScript ecosystem of tooling, MineJS is for you.

## Features

- **Modern JavaScript** powered by [GraalJS](https://www.graalvm.org/javascript/)
- **Hot reload in-game**: load, unload and reload scripts without restarting the server
- **Events**: subscribe to server events with a single line
- **Custom commands**: register commands with descriptions and usage
- **MiniMessage support** built in for rich, modern chat formatting
- **PlaceholderAPI** integration out of the box
- **Oraxen** integration out of the box

## Quick Start

### Commands

```js
commands.createCommand("test", () => console.log("test from command"))
    .setDescription("Some description")
    .setUsage("/test")
    .register()
```

### Events

```js
events.on("player interact", event => {
    if (event.getAction() != Action.RightClickBlock) return;

    event.getPlayer().sendMessage(minimessage("<yellow>Right click"))
})
```

## Installation

1. Make sure your server runs **Java 21** or newer (required by Minecraft 1.21+).
2. Download the latest `MineJS.jar` from the [Releases](https://github.com/YOUR_USERNAME/MineJS/releases) page.
3. Put the jar into your server's `plugins/` folder.
4. Start the server. MineJS will create its folder in `plugins/MineJS/`.
5. Put your `.js` scripts into the scripts folder and load them with `/script load <filename>`.

## Managing scripts in-game

| Command                    | Description                        |
| -------------------------- | ---------------------------------- |
| `/script load <filename>`   | Load a script                      |
| `/script unload <filename>` | Unload a running script            |
| `/script reload <filename>` | Reload a script after editing it   |

Edit your script, run `/script reload <filename>`, and see the change instantly.

## Compatibility

| Software | Status                                   |
| -------- | ---------------------------------------- |
| Paper    | Tested on **1.21.4** and **1.21.8**      |
| Other 1.21.x versions | Expected to work, not fully tested |
| Spigot / Bukkit forks | Not officially tested yet   |

Found a problem on another version or platform? Please [open an issue](https://github.com/YOUR_USERNAME/MineJS/issues).

## Integrations

| Plugin           | Support        |
| ---------------- | -------------- |
| PlaceholderAPI   | Built in       |
| Oraxen           | Built in       |

## MineJS vs Skript

|                        | Skript                    | MineJS                        |
| ---------------------- | ------------------------- | ----------------------------- |
| Language               | Custom, English-like      | JavaScript (GraalJS)          |
| Learning curve         | Easy to start             | Easy if you know JS           |
| Functions / modules    | Limited                   | Full JavaScript features      |
| Editor support         | Limited                   | Any JS-capable editor / IDE   |
| Hot reload in-game     | Yes                       | Yes                           |

## Documentation

Full documentation is coming soon at **https://YOUR_USERNAME.github.io/MineJS**.

## Roadmap

- [ ] Full documentation site
- [ ] More events and API coverage
- [ ] Testing on more platforms and versions

## Contributing

Contributions are welcome! Feel free to open an issue to report a bug or suggest a feature, or send a pull request.

1. Fork the repository
2. Create a branch: `git checkout -b feature/my-feature`
3. Commit your changes
4. Open a pull request

## License

MineJS is released under the [MIT License](LICENSE).
