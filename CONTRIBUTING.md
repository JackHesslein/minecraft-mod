# Contributing to MinecraftReimagined

## Prerequisites

- 64-bit JDK 21
- An IDE with Gradle support

## Development Workflow

1. Base new work on `develop`.
2. Keep each change focused on one feature, fix, or maintenance task.
3. Run the relevant development configuration while working:

```powershell
.\gradlew.bat runClient
.\gradlew.bat runData
```

4. Before requesting review, verify the project builds:

```powershell
.\gradlew.bat build
```

Source code belongs in `src/main/java/com/jackhesslein/mcreimagined`. Client assets belong in `src/main/resources/assets/minecraftreimagined`. Generated data is written to `src/generated/resources`.

Use the `minecraftreimagined` namespace for resource and registry IDs. Do not commit build output, IDE files, or Gradle cache files.

## Commit Messages

Write concise, imperative commit subjects that describe one logical change:

```text
Add copper hammer recipe
Fix client config screen crash
Update NeoForge dependency range
```

Avoid vague messages such as `fix`, `updates`, or `wip`. Add a commit body when the reason for a change or a compatibility impact is not obvious from the subject.

## Branches and Pull Requests

Push all work to `develop`; do not push directly to `main`. `main` is the stable branch.

When work on `develop` is stable, builds successfully, and is ready to integrate, open a pull request from `develop` into `main`. Pull requests should state what changed and list the validation performed.
