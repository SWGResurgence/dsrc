package script.library;

import script.*;
import java.util.HashSet;

public class expansion_rewards extends script.base_script {
	
	public static final String STF_FILE = "npe";
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "crate_use"));
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantExpansionRewards(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
	public static obj_id[] grantExpansionRewards(obj_id player) throws InterruptedException {
		obj_id pInv = utils.getInventoryContainer(player);
		HashSet theSet = new HashSet();
		
		theSet.add(static_item.createNewItemFunction("walker_at_rt", pInv));
    theSet.add(static_item.createNewItemFunction("barc_speeder", pInv));
    theSet.add(static_item.createNewItemFunction("instant_travel", pInv));
		theSet.add(static_item.createNewItemFunction("instant_travel_royal_ship", pInv));
    theSet.add(static_item.createNewItemFunction("tow_retail_reward", pInv));
    theSet.add(static_item.createNewItemFunction("sorosuub", pInv));
    theSet.add(static_item.createNewItemFunction("item_tow_schematic_vehicle_02_02", pInv));
    theSet.add(static_item.createNewItemFunction("item_deed_grievous_wheel_bike", pInv));
    theSet.add(static_item.createNewItemFunction("varactyl", pInv));));
    theSet.add(static_item.createNewItemFunction("item_pet_mount_lava_flea_01_01", pInv));
		
		obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}