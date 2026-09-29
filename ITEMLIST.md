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
