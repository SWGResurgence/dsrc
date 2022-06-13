package script.item;

import script.*;
import java.util.HashSet;

public class profession_roadmap_supplies_jedi extends script.base_script {
    public static final String STF_FILE = "npe";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_contents"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantJediRoadmapSupplies(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public static obj_id[] grantJediRoadmapSupplies(obj_id player) throws InterruptedException {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        theSet.add(static_item.createNewItemFunction("item_force_sensitive_backpack_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_npe_fs_robe_02_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_force_sensitive_ring_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_force_sensitive_pendant_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_force_sensitive_ring_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_roadmap_belt_force_sensitive_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_npe_lightsaber_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_krayt_pearl_04_01", pInv));
        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}
