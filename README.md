# HebrewFix

Fixes RTL Hebrew chat preview rendering in Minecraft.

Hebrew text in the chat input field displays correctly — exactly as typed — without affecting the actual sent message.

## Features

- Hebrew characters display in logical order (as typed)
- Mixed Hebrew/English text supported
- Word order preserved
- Commands (`/msg`, etc.) unaffected
- Sent messages never modified
- Client-side only
- Mod Menu integration with enable/disable toggle

## Download

Two JARs available, pick the one for your Minecraft version:

| Minecraft Version | JAR |
|---|---|
| 1.21.11 | `fabric-1.21.11/build/libs/HebrewFix-1.0.0.jar` |
| 26.1+ | `fabric-26.2/build/libs/HebrewFix-1.0.0.jar` |

## Requirements

**1.21.11:** Minecraft 1.21.11, Fabric Loader 0.19.3+, Fabric API, Java 21+

**26.x:** Minecraft 26.1+, Fabric Loader 0.19.3+, Fabric API, Java 25+

## Installation

1. Install Fabric for your Minecraft version
2. Download Fabric API and place in `mods` folder
3. Download the correct HebrewFix JAR and place in `mods` folder
4. Launch the game

## Configuration

Open Mod Menu → HebrewFix → toggle Enable/Disable. Config saves to `config/hebrewfix.json`.

## Building

```
./build-all.ps1
```

Or build individually:

```
cd fabric-1.21.11 && ../gradlew build    # for 1.21.11
cd fabric-26.2 && ../gradlew build       # for 26.x
```
