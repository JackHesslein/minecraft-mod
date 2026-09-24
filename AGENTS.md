# Agent Instructions

## Project

- Minecraft 1.21.1 with NeoForge 21.1.251
- Java 21 and Gradle wrapper
- Mod ID: `minecraftreimagined`
- Java package: `com.jackhesslein.mcreimagined`

Place common code in `src/main/java/com/jackhesslein/mcreimagined`, assets in `src/main/resources/assets/minecraftreimagined`, and generated data in `src/generated/resources`.

## Required Validation

Run the relevant Gradle task after code or resource changes. Use the following full-build command before considering a change complete:

```powershell
.\gradlew.bat build
```

Do not edit files under `build/`, `.gradle/`, `run/`, or generated caches.

## Git Restriction

Do not run any `git` command unless the user explicitly asks for Git work. This does not include read-only commands such as `git status`, `git diff`, and `git log`, only commands that modify history, branches, staging, commits, remotes, or pushes.

Agents may use non-mutating inspection commands for files, directories, code searches, and Gradle help. Do not treat that permission as authorization to use Git.

## Contribution Policy

Keep changes small and focused. Use the `minecraftreimagined` namespace for resource and registry IDs.

When registering a new item, including a block item, add it to `ITEMLIST.md`. Update its entry whenever its behavior or important file locations change. Each entry must identify the registry ID and directories relevant to future editing.

Commit messages must be concise, imperative, and specific, such as `Add copper hammer recipe`. Avoid vague messages such as `fix`, `updates`, or `wip`; use a body when rationale or compatibility impact needs explanation.

All work is pushed to `develop`. Never push directly to `main`. Merge `develop` into `main` only through a pull request after the work is stable and the build succeeds.
