package script.item;

import script.*;
import java.util.HashSet;

public class profession_roadmap_supplies_medic extends script.base_script {
    public static final String STF_FILE = "npe";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_contents"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantMedicRoadmapSupplies(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public static obj_id[] grantMedicRoadmapSupplies(obj_id player) throws InterruptedException {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        theSet.add(static_item.createNewItemFunction("item_medic_backpack_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_chest_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_helmet_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_bicep_l_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_bicep_r_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_bracer_l_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_bracer_r_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_gloves_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_medic_roadmap_boots_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_medic_ring_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_medic_pendant_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_roadmap_belt_medic_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_pistol_medic_roadmap_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_carbine_medic_roadmap_01_02", pInv));
        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }
}
