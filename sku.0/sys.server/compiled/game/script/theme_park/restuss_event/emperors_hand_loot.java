package script.theme_park.restuss_event;

import script.dictionary;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class emperors_hand_loot extends script.base_script {
	public emperors_hand_loot() {
	}
    public int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.legacy.hand");
        setObjVar(tatooine, "dungeon_finder.legacy.hand", "Active");
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.legacy.hand");
        setObjVar(tatooine, "dungeon_finder.legacy.hand", "Inactive");
        return SCRIPT_CONTINUE;
    }
	public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException {
		obj_id corpseInventory = utils.
		getInventoryContainer(self);
		if (corpseInventory == null) {
			return SCRIPT_CONTINUE;
		}
		if (!isIdValid(self)) {
			return SCRIPT_CONTINUE;
		}
		createMyLoot(self);
		return SCRIPT_CONTINUE;
	}
	public void createMyLoot(obj_id self) throws InterruptedException {
		obj_id corpseInventory = utils.
		getInventoryContainer(self);
		if (corpseInventory == null) {
			return;
		}
		String mobType = ai_lib.getCreatureName(self);
		if (mobType == null) {
			return;
		}
		int x = rand(1, 100);
		if (x < 100) { /* 99% Drop Chance: Legendary Loot Chest */
			static_item.createNewItemFunction("rare_loot_chest_quality_3", corpseInventory);
		}
		if (x < 100) { /* 99% Drop Chance: Jinsu Razor Lightsaber Schematic */
			static_item.createNewItemFunction("item_restuss_schematic_saber_04_01", corpseInventory);
		}
		if (x < 2) { /* 1% Drop Chance: Sith Waistpack 4/5 */
			static_item.createNewItemFunction("item_collection_sith_holocron_01_04", corpseInventory);
		}
		/* String myLoot1 = "object/tangible/loot/loot_schematic/generic_limited_use_flashy.iff";
		createObject(myLoot1, corpseInventory, ""); */
		return;
	}
}