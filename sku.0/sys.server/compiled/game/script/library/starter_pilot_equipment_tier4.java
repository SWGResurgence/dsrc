package script.library;

import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;
import script.library.*;

import java.util.HashSet;

public class starter_pilot_equipment_tier4 extends script.base_script
{
    public static final String STF_FILE = "npe";

    public static obj_id[] grantShipPartsTier4(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();

        theSet.add(static_item.createNewItemFunction("item_koensayr_thick_advanced_durasteel_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_koensayr_thick_advanced_durasteel_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_corellian_advanced_thrust_enhancer_boosters_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_kse_mk4_capacitor_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_hk_mk3_droid_interface_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_kuat_f31_engine_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_mandal_dxr5_reactor_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_koensayr_deflector_m6_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_seinar_ls1_blaster_01_01", pInv));
        theSet.add(static_item.createNewItemFunction("item_seinar_ls1_blaster_01_01", pInv));

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
            obj_id[] allTheArmor = grantShipPartsTier4(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
