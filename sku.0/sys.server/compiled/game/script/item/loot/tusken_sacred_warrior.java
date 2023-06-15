package script.item.loot;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class tusken_sacred_warrior extends script.base_script
{
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        createLoot(self);
        return SCRIPT_CONTINUE;
    }

    public void createLoot(obj_id self) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null)
        {
            return;
        }
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null)
        {
            return;
        }
        int x = rand(1, 100);
        if (x <= 15)
        {  // 15% chance to drop Tusken Dawn painting
            static_item.createNewItemFunction("item_tusken_dawn_painting_01_01", corpseInventory);
            if (x <= 5)
            {  // 5% chance to drop Hett's Isolation
                static_item.createNewItemFunction("item_color_crystal_02_35", corpseInventory);
            }
        }
    }
}
