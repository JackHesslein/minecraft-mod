# Item List

## Tier 1 Crafting Table

- Registry ID: `minecraftreimagined:tier_one_crafting_table`
- Type: Block item for a crafting table that drops when broken by hand or axe; axes mine it faster.
- Java: `src/main/java/com/jackhesslein/mcreimagined/`
- Block resources: `src/main/resources/assets/minecraftreimagined/blockstates/` and `src/main/resources/assets/minecraftreimagined/models/block/`
- Item resources: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Textures: `src/main/resources/assets/minecraftreimagined/textures/block/`
- Drops and mining tags: `src/main/resources/data/minecraftreimagined/loot_table/blocks/` and `src/main/resources/data/minecraft/tags/block/mineable/axe.json`

## Tier 2 Crafting Table

- Registry ID: `minecraftreimagined:tier_two_crafting_table` (block and block item).
- Type: Block item for a crafting table that drops when broken by hand or axe; axes mine it faster. Uses crimson-planks sounds when broken.
- Java: `src/main/java/com/jackhesslein/mcreimagined/`
- Block resources: `src/main/resources/assets/minecraftreimagined/blockstates/` and `src/main/resources/assets/minecraftreimagined/models/block/`
- Item resources: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Textures: `src/main/resources/assets/minecraftreimagined/textures/block/tier_two_crafting_table_{top,front,side,bottom}.png` (split from `tier_two_crafting_table.png`).
- Drops and mining tags: `src/main/resources/data/minecraftreimagined/loot_table/blocks/` and `src/main/resources/data/minecraft/tags/block/mineable/axe.json`

## Tier 3 Crafting Table

- Registry ID: `minecraftreimagined:tier_three_crafting_table` (block and block item).
- Type: Block item for a crafting table with ancient-debris mining hardness (30) and obsidian sounds when broken; drops only when mined with a diamond or netherite pickaxe.
- Java: `src/main/java/com/jackhesslein/mcreimagined/`
- Block resources: `src/main/resources/assets/minecraftreimagined/blockstates/` and `src/main/resources/assets/minecraftreimagined/models/block/`
- Item resources: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Textures: `src/main/resources/assets/minecraftreimagined/textures/block/tier_three_crafting_table_{top,front,side,bottom}.png` (split from `tier_three_crafting_table.png`).
- Mining properties: `src/main/java/com/jackhesslein/mcreimagined/MinecraftReimagined.java`
- Drops and mining tags: `src/main/resources/data/minecraftreimagined/loot_table/blocks/` and `src/main/resources/data/minecraft/tags/block/` (`mineable/pickaxe.json`, `needs_diamond_tool.json`)

## Tier 1 Upgrade Template

- Registry ID: `minecraftreimagined:tier_one_upgrade_template`
- Behavior: Sneak + Interact with a vanilla crafting table to replace it with a Tier 1 crafting table, releasing double-sized `minecraft:enchant` particles around the table; consumes one template unless in creative mode.
- Java: `src/main/java/com/jackhesslein/mcreimagined/` (`MinecraftReimagined.java`, `TierOneCraftingTableBlock.java`, `CraftingTableUpgradeItem.java`) and `src/main/java/com/jackhesslein/mcreimagined/client/` (`TierOneUpgradeParticles.java`)
- Item model and tooltip: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Texture: `src/main/resources/assets/minecraftreimagined/textures/item/tier_one_upgrade_template.png`

## Tier 2 Upgrade Template

- Registry ID: `minecraftreimagined:tier_two_upgrade_template`
- Behavior: Sneak + Interact with a Tier 1 crafting table to replace it with a Tier 2 crafting table, releasing blaze-like flame particles around the table; consumes one template unless in creative mode.
- Java: `src/main/java/com/jackhesslein/mcreimagined/` (`MinecraftReimagined.java`, `CraftingTableUpgradeItem.java`)
- Item model and tooltip: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Texture: `src/main/resources/assets/minecraftreimagined/textures/item/tier_two_upgrade_template.png`

## Tier 3 Upgrade Template

- Registry ID: `minecraftreimagined:tier_three_upgrade_template`
- Behavior: Sneak + Interact with a Tier 2 crafting table to replace it with a Tier 3 crafting table, releasing purple dragon's breath particles around the table; consumes one template unless in creative mode.
- Java: `src/main/java/com/jackhesslein/mcreimagined/` (`MinecraftReimagined.java`, `CraftingTableUpgradeItem.java`)
- Item model and tooltip: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Texture: `src/main/resources/assets/minecraftreimagined/textures/item/tier_three_upgrade_template.png`

## Flashbang

- Registry ID: `minecraftreimagined:flashbang`
- Behavior: Throwable snowball-style item (stack size 16); on impact, spawns 50 `minecraft:flash` particles and 50 bright `minecraft:end_rod` particles within a 5-block radius and plays an explosion sound. Players within 5 blocks take a negligible 0.1 damage, see a solid-white flash for 3 seconds fading over the next second, and hear one loud, continuous 5-second ringing sound. Direct hits on other entities also deal only 0.1 damage. Each wither-boss skull impact has a 15% chance to trigger the particles and a 2-second whiteout with a 2.5-second ringing sound within 5 blocks, alongside the skull's normal damage and explosion. Available in the Combat creative tab.
- Java: `src/main/java/com/jackhesslein/mcreimagined/` (`MinecraftReimagined.java`, `FlashbangItem.java`, `FlashbangProjectile.java`, `FlashbangEffects.java`, `FlashbangFlashPayload.java`) and `src/main/java/com/jackhesslein/mcreimagined/client/FlashbangOverlay.java`
- Item model and name: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Texture: `src/main/resources/assets/minecraftreimagined/textures/item/flashbang.png`
- Sounds: `src/main/resources/assets/minecraftreimagined/sounds/flashbang_ringing.ogg`, `src/main/resources/assets/minecraftreimagined/sounds/wither_flash_ringing.ogg`, and `src/main/resources/assets/minecraftreimagined/sounds.json`
- Recipe: `src/main/resources/data/minecraftreimagined/recipe/flashbang.json` — nether star between two netherite ingots vertically in any crafting-table column; yields 16 flashbangs.
