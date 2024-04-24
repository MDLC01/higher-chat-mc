<img src="src/main/resources/assets/higher-chat/icon.png" alt="Higher Chat icon" width="128" align="right">

# Higher Chat

[![Downloads](https://img.shields.io/modrinth/dt/higher-chat)](https://modrinth.com/mod/higher-chat)
[![Game versions](https://img.shields.io/modrinth/game-versions/higher-chat)](https://modrinth.com/mod/higher-chat/versions)
[![GitHub release](https://img.shields.io/github/release/MDLC01/higher-chat-mc)](https://github.com/MDLC01/higher-chat-mc/releases/latest)
[![License](https://img.shields.io/github/license/MDLC01/higher-chat-mc)](UNLICENSE)

This is a Fabric mod for Minecraft that moves the chat above the armor bar.

## Q&A

### Will it move the chat even if my screen is wide enough for the chat and armor bar not to collide?

No. This mod automatically detects what bars appear on the same abscissa as the chat, and only takes those bars into account when computing the new position of the chat.

### What is the difference between this mod and [some other mod that does the same thing]?

Most similar mods only move the chat by a fixed amount, whereas this one decides on an optimal amount depending on your window width and how many bars are displayed.

### Is it compatible with mods that add other bars?

Probably not. Only vanilla bars are taken into account when computing how high the chat should be displayed.
