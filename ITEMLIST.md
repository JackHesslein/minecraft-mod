# Item List

## Upgraded Crafting Table

- Registry ID: `minecraftreimagined:upgraded_crafting_table`
- Type: Block item for a crafting table with vanilla crafting-table behavior.
- Java: `src/main/java/com/jackhesslein/mcreimagined/`
- Block resources: `src/main/resources/assets/minecraftreimagined/blockstates/` and `src/main/resources/assets/minecraftreimagined/models/block/`
- Item resources: `src/main/resources/assets/minecraftreimagined/models/item/` and `src/main/resources/assets/minecraftreimagined/lang/`
- Textures: `src/main/resources/assets/minecraftreimagined/textures/block/`

## Scuba Diver Helmet

- Registry ID: `minecraftreimagined:scuba_diver_helmet`
- Type: Helmet armor item using the `minecraftreimagined:scuba_diver` armor material (2 armor, repaired with copper ingots). Keeps the wearer's air supply full while worn underwater, so they can breathe indefinitely; normal drowning resumes as soon as it is removed.
- Java: `src/main/java/com/jackhesslein/mcreimagined/ScubaDiverHelmetItem.java` (underwater breathing), `src/main/java/com/jackhesslein/mcreimagined/ScubaDiverHelmetLayer.java` (client renderer that draws the 3D model on player and armor stand heads), `src/main/java/com/jackhesslein/mcreimagined/MinecraftReimagined.java` (registration), and `src/main/java/com/jackhesslein/mcreimagined/ModArmorMaterials.java` (armor material, with no armor texture layers)
- Item resources: `src/main/resources/assets/minecraftreimagined/models/item/scuba_diver_helmet.json` (flat icon, switching to the 3D model when worn via `neoforge:separate_transforms`), `src/main/resources/assets/minecraftreimagined/models/item/scuba_diver_helmet_worn.json` (3D fishbowl model), and `src/main/resources/assets/minecraftreimagined/lang/`
- Textures: `src/main/resources/assets/minecraftreimagined/textures/item/scuba_diver_helmet.png` (inventory icon) and `src/main/resources/assets/minecraftreimagined/textures/item/scuba_diver_helmet_worn.png` (3D model texture)
- Model source: the Blockbench project `scuba_diver_helmet.bbmodel` is not in the repo; re-export to `scuba_diver_helmet_worn.json` and replace `MODID` with `minecraftreimagined`.
