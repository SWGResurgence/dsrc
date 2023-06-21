package script.item.loot;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class hetts_isolation extends script.base_script
{
    private static final int HETTS_ISOLATION_LOOT_CHANCE = 1;

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        int chanceLoot = rand(1, 100);
        if (chanceLoot > HETTS_ISOLATION_LOOT_CHANCE)
        {
            return SCRIPT_CONTINUE;
        }
        static_item.createNewItemFunction("item_color_crystal_02_35", utils.getInventoryContainer(self));
        return SCRIPT_CONTINUE;
    }
}
