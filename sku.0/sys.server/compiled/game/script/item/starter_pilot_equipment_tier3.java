package script.item;

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.HashSet;

public class starter_pilot_equipment_tier3 extends script.base_script
{
    public static final String STF_FILE = "npe";

    public static obj_id[] grantShipPartsTier3(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();

        theSet.add(static_item.createNewItemFunction("item_kuat_systems_engineering_modified_heavy_durasteel", pInv));
        theSet.add(static_item.createNewItemFunction("item_kuat_systems_engineering_modified_heavy_durasteel", pInv));
        theSet.add(static_item.createNewItemFunction("item_incom_intimidator_mk3_boosters_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_rendili_imperator_droid_interface_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_kse_mk3_capacitor_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_sfs_imperial_engine_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_sorosuub_fusion_reactor_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_kse_mk4_shields_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_hk_scorcher_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_hk_scorcher_01_01", pInv));

        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "crate_use"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = grantShipPartsTier3(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
