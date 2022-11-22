package script.theme_park.world_boss;

import script.dictionary;
import script.library.*;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class loot_controller_paxvizla extends script.base_script {
	public static final String VOLUME_NAME = "aggressive_area";
	public int OnAttach(obj_id self) throws InterruptedException {
		sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been last seen on Dxun at the Abandoned Mandalorian Outpost.");
		return SCRIPT_CONTINUE;
	}
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException {
		if (pet_lib.isPet(killer)) {
			sendSystemMessageGalaxyTestingOnly("ATTENTION GALAXY BOUNTY HUNTERS: The Self-Proclaimed Mandalore, The Renegade, Pax Vizla has been reported to have been killed and the Czerka Corporation has paid out the out the bounty to " + getPlayerName(pet_lib.getMaster(killer)));
		}
		return SCRIPT_CONTINUE;
	}
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException {
        obj_id corpseInventory = utils.getInventoryContainer(self);
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
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null) {
            return;
        }
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null) {
            return;
        }
        int x = rand(1, 100);
        if (x < 61) { // 60% Drop Rate: TCG - Mandalorian Knights Painting
            static_item.createNewItemFunction("item_painting_resurgence_mandalorian_knights_01_01", corpseInventory);
        }
        if (x < 26) { // 25% Drop Rate: Naboo Signaling Unit
            static_item.createNewItemFunction("item_tcg_loot_reward_series5_signal_unit", corpseInventory);
        }
		if (x < 11) { // 10% Drop Rate: TCG - Mandalorian Banner
			static_item.createNewItemFunction("item_tcg_loot_reward_series3_mandalorian_skull_banner", corpseInventory);
		}	
        /*String myLoot1 = "";
        createObject(myLoot1, corpseInventory, "");
        String myLoot2 = "";
        createObject(myLoot2, corpseInventory, "");*/
        return;
    }
}