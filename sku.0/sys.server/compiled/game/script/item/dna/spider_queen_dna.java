package script.item.dna;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class spider_queen_dna extends script.base_script
{
    public static final String SPIDER_QUEEN_DNA_LOOT_ITEM = "item_spiderclan_queen_dna";
    public static final int SPIDER_QUEEN_DNA_LOOT_CHANCE = 5;

    public spider_queen_dna()
    {
    }

    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        int chanceDna = rand(1, 100);
        if (chanceDna > SPIDER_QUEEN_DNA_LOOT_CHANCE)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id inv = utils.getInventoryContainer(self);
        obj_id dna = static_item.createNewItemFunction(SPIDER_QUEEN_DNA_LOOT_ITEM, inv);
        return SCRIPT_CONTINUE;
    }
}
