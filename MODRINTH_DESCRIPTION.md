# Hebrew Fix

Fixes RTL Hebrew text rendering in the Minecraft chat input preview. Hebrew characters display as typed without reversed text.

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

## Configuration

Open **Mod Menu** -> **Hebrew Fix** -> toggle **Enable/Disable**. Configuration is saved to `.minecraft/config/hebrewfix.json`.

## Requirements

| | 1.21.11 | 26.x |
|---|---|---|
| Minecraft | 1.21.11 | 26.1 - 26.3+ |
| Fabric Loader | 0.19.3+ | 0.19.3+ |
| Fabric API | Yes | Yes |
| Java | 21+ | 25+ |
| Mod Menu | Optional | Optional |
