package script.developer.talisa.container.profession;

import script.*;
import java.util.HashSet;

public class profession_medic extends script.base_script {
	
	public static final String STF_FILE = "npe";
	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "crate_use"));
		return SCRIPT_CONTINUE;
	}
	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantMedicUniform(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
	public static obj_id[] grantMedicUniform(obj_id player) throws InterruptedException {
		obj_id pInv = utils.getInventoryContainer(player);
		HashSet theSet = new HashSet();
		
		theSet.add(static_item.createNewItemFunction("item_medic_shirt_02_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_medic_pants_02_01", pInv));
    theSet.add(static_item.createNewItemFunction("item_medic_vest_02_01", pInv));
		theSet.add(static_item.createNewItemFunction("item_medic_shoes_02_01", pInv));
		
		obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}