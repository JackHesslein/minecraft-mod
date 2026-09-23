# MinecraftReimagined

NeoForge mod project for Minecraft 1.21.1.

## Requirements

- 64-bit JDK 21

## Development

```powershell
.\gradlew.bat runClient
.\gradlew.bat runServer
.\gradlew.bat runData
.\gradlew.bat build
```

Production JARs are written to `build/libs`. Data-generator output is written to `src/generated/resources`.

## Project Layout

- `src/main/java/com/jackhesslein/mcreimagined`: mod code
- `src/main/resources/assets/minecraftreimagined`: client assets
- `src/main/templates/META-INF/neoforge.mods.toml`: mod metadata
