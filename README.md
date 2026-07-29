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

## Requirements

- Minecraft 1.21.11
- Fabric Loader 0.19.3+
- Fabric API

## Installation

1. Install Fabric for Minecraft 1.21.11
2. Download Fabric API and place in `mods` folder
3. Download HebrewFix and place in `mods` folder
4. Launch the game

## Configuration

Open Mod Menu → HebrewFix → toggle Enable/Disable. Config saves to `config/hebrewfix.json`.
