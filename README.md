# Hebrew Fix

Fixes RTL Hebrew chat preview rendering in Minecraft.

Hebrew text in the chat input field displays correctly as typed, without affecting the actual message sent to the server.

## Before & After

| Before (without Hebrew Fix) | After (with Hebrew Fix) |
|---|---|
| ![Before](https://cdn.modrinth.com/data/cached_images/03140cc26626129c94ab2980dadddaf329259e2f.png) | ![After](https://cdn.modrinth.com/data/cached_images/19528d8087ed79e74c7698598c5f6df4cdae0c73.png) |

## Features

- Hebrew characters display in logical order (as typed)
- Mixed Hebrew and English text fully supported
- Word order preserved
- Commands (`/msg`, `/w`, etc.) unaffected
- Sent messages are never modified
- Client-side only (works on any server)
- Mod Menu integration with an on/off toggle
- No external dependencies beyond Fabric API

## How It Works

Minecraft applies Unicode BiDi (bidirectional) text reordering when the game language is set to Hebrew or Arabic. This causes Hebrew characters in the chat input to appear reversed. Hebrew Fix intercepts the text rendering in `ChatScreen` and displays characters in their original logical order.

The underlying text in the chat input is never modified, only the visual preview is fixed. Messages sent to the server are completely untouched.

## Requirements

| Version | Minecraft | Fabric Loader | Java | Fabric API |
|---|---|---|---|---|
| **1.21.11** | 1.21.11 | 0.19.3+ | 21+ | Required |
| **26.x** | 26.1, 26.2, 26.3+ | 0.19.3+ | 25+ | Required |

## Download

Get the latest JAR from [Modrinth](https://modrinth.com/mod/hebrewfix/versions#download). Pick the file matching your Minecraft version (1.21.11 or 26.x).

## Installation

1. Install Fabric for your Minecraft version.
2. Download Fabric API and place it in your `mods` folder.
3. Download the correct Hebrew Fix JAR and place it in your `mods` folder.
4. Launch the game.

## Configuration

Open **Mod Menu** -> **Hebrew Fix** -> toggle **Enable/Disable**. Configuration is saved to `config/hebrewfix.json`.

## Project Structure

- `shared/` - Common code shared between versions (text processing, configuration, mod initialization).
- `fabric-1.21.11/` - Subproject for Minecraft 1.21.11 (Yarn mappings, Java 21).
- `fabric-26.x/` - Unified subproject for Minecraft 26.1, 26.2, and 26.3+ (Mojang mappings, Java 25).

## Building

Build all versions at once:

```powershell
.\build-all.ps1
```

Or build individually:

```powershell
cd fabric-1.21.11; ..\gradlew assemble
cd fabric-26.x; ..\gradlew assemble
```

Built JARs are placed in `fabric-1.21.11/build/libs/` and `fabric-26.x/build/libs/`.

## Running in Development

Run Minecraft 1.21.11:

```powershell
cd fabric-1.21.11; ..\gradlew runClient
```

Run Minecraft 26.x (defaults to 26.3):

```powershell
cd fabric-26.x; ..\gradlew runClient
```

Run specific 26.x versions:

```powershell
cd fabric-26.x; ..\gradlew runClient -Pmc=26.2
cd fabric-26.x; ..\gradlew runClient -Pmc=26.1
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
