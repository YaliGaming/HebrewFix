# Hebrew Fix

Fixes RTL Hebrew text rendering in the Minecraft chat input preview. Hebrew characters display exactly as typed — no more reversed text.

## Before & After

| Before (without Hebrew Fix) | After (with Hebrew Fix) |
|---------------------------|------------------------|
| ![Before](https://cdn.modrinth.com/data/cached_images/03140cc26626129c94ab2980dadddaf329259e2f.png) | ![After](https://cdn.modrinth.com/data/cached_images/19528d8087ed79e74c7698598c5f6df4cdae0c73.png) 

## Features

- Hebrew characters display in logical order (exactly as typed)
- Mixed Hebrew/English text fully supported
- Word order preserved
- Commands (`/msg`, `/w`, etc.) unaffected
- Sent messages never modified
- Client-side only — works on any server
- Mod Menu integration with enable/disable toggle
- Lightweight — single mixin, zero dependencies beyond Fabric API

## How It Works

Minecraft applies Unicode BiDi (bidirectional) text reordering when the game language is set to Hebrew or Arabic. This causes Hebrew characters in the chat input to appear reversed. Hebrew Fix intercepts the text rendering at the `ChatScreen` level and counteracts the BiDi reordering, so characters display in their original logical order.

The stored text in the chat field is **never modified** — only the visual rendering is affected. The message sent to the server is exactly what you typed.

## Configuration

Open **Mod Menu** → **Hebrew Fix** → toggle **Enable/Disable**. Config is saved to `.minecraft/config/hebrewfix.json`.

## Versions

| Minecraft | Download |
|-----------|----------|
| **26.1 – 26.2** | `HebrewFix-1.0.0.jar` (26.x) |
| **1.21.11** | `HebrewFix-1.0.0.jar` (1.21.11) |

## Requirements

| | 1.21.11 | 26.x |
|---|---|---|
| Minecraft | 1.21.11 | 26.1+ |
| Fabric Loader | 0.19.3+ | 0.19.3+ |
| Fabric API | ✓ | ✓ |
| Java | 21+ | 25+ |
| Mod Menu | Optional | Optional |
