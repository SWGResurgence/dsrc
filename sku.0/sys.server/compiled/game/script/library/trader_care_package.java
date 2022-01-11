package script.library;

import script.*;
import java.util.HashSet;

public class trader_care_package extends script.base_script {
	
	public static final String STF_FILE = "npe";
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "redeem_care_package"));
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			sendSystemMessage(player, new string_id(STF_FILE, "redeemed_care_package"));
            obj_id[] allTheArmor = grantTraderCarePackage(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
	public static obj_id[] grantTraderCarePackage(obj_id player) throws InterruptedException {
		obj_id pInv = utils.getInventoryContainer(player);
		HashSet theSet = new HashSet();
    if (utils.isProfession(player, utils.TRADER)) {
        theSet.add(static_item.createNewItemFunction("trader_care_package", pInv));
    }
		
		obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}