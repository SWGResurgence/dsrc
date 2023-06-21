package script.item;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

import java.util.HashSet;

public class mustafar_encounter_gift extends script.base_script
{
    public static final String STF_FILE = "mustafar_encounter_gift";

    public static obj_id[] grantMustafarEncounterGift(obj_id player) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(player);
        HashSet theSet = new HashSet();

        theSet.add(static_item.createNewItemFunction("heroic_encounter_auth_uplink_cavern", pInv));
        theSet.add(static_item.createNewItemFunction("mustafar_encounter_auth_decrepit_droid_factory", pInv));
        theSet.add(static_item.createNewItemFunction("mustafar_encounter_auth_working_droid_factory", pInv));
        theSet.add(static_item.createNewItemFunction("mustafar_encounter_auth_droid_army", pInv));
        theSet.add(static_item.createNewItemFunction("mustafar_encounter_auth_volcano", pInv));
        theSet.add(static_item.createNewItemFunction("mustafar_encounter_auth_sher_kar_cave", pInv));

        obj_id[] items = new obj_id[theSet.size()];
        theSet.toArray(items);
        showLootBox(player, items);
        return items;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "claim_mustafar_encounter_gift"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_box"));
            obj_id[] allTheArmor = grantMustafarEncounterGift(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
