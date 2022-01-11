package script.library;

import script.*;
import java.util.HashSet;

public class trader_care_package_generators extends script.base_script {
	
	public static final String STF_FILE = "npe";
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "redeem_care_package"));
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			sendSystemMessage(player, new string_id(STF_FILE, "redeemped_care_package"));
            obj_id[] allTheArmor = grantTraderCarePackageGenerators(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
	public static obj_id[] grantTraderCarePackageGenerators(obj_id player) throws InterruptedException {
		obj_id pInv = utils.getInventoryContainer(player);
		HashSet theSet = new HashSet();
		
		theSet.add(static_item.createNewItemFunction("item_power_generator_wind_deed_01_01", pInv, 5));
    theSet.add(static_item.createNewItemFunction("item_power_generator_solar_deed_01_01", pInv, 3));
    theSet.add(static_item.createNewItemFunction("item_power_generator_photo_bio_deed_01_01", pInv, 2));
    theSet.add(static_item.createNewItemFunction("item_power_generator_geothermal_deed_01_01", pInv, 1));
    theSet.add(static_item.createNewItemFunction("item_power_generator_fusion_deed_01_01", pInv, 1));
		
		obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}
