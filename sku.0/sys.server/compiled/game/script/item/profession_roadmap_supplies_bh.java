package script.item;

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.HashSet;

public class profession_roadmap_supplies_bh extends script.base_script
{
    public static final String STF_FILE = "npe";

    public static obj_id[] grantBhRoadmapSupplies(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();
        theSet.add(static_item.createNewItemFunction("item_bounty_hunter_backpack_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_chest_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_helmet_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_bicep_l_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_bicep_r_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_bracer_l_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_bracer_r_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_gloves_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("armor_bounty_hunter_roadmap_boots_02_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_bounty_hunter_ring_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_bounty_hunter_pendant_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("item_roadmap_belt_bounty_hunter_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_carbine_bh_roadmap_01_02", pInv));
        theSet.add(static_item.createNewItemFunction("weapon_rifle_bh_roadmap_01_02", pInv));
        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_contents"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantBhRoadmapSupplies(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
