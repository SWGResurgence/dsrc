package script.item;

import script.*;
import script.library.static_item;
import script.library.utils;

import java.util.HashSet;

public class game_update_gift_box_6 extends base_script {
	
	public static final String STF_FILE = "gu_gift_box";
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_game_update_gift"));
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			sendSystemMessage(player, new string_id(STF_FILE, "opened_gift_box"));
            obj_id[] allTheArmor = grantGameUpdateGift(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
	public static obj_id[] grantGameUpdateGift(obj_id player) throws InterruptedException {
		obj_id pInv = utils.getInventoryContainer(player);
		HashSet theSet = new HashSet();
		theSet.add(static_item.createNewItemFunction("item_cts_sarlacc_mini_game", pInv));
		theSet.add(static_item.createNewItemFunction("item_gas_recycler_01_01", pInv));
		theSet.add(static_item.createNewItemFunction("sher_kar_cave", pInv));
		obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}