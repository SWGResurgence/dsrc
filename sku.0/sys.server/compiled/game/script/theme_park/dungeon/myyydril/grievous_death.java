package script.theme_park.dungeon.myyydril;/*
@Filename: script.theme_park.dungeon.myyydril.grievous_death
@Author: BubbaJoeX
@Purpose: Readded Grievous Loot script. This could be added to a loot table, but lets not touch legacy scripts.
*/


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

public class grievous_death extends script.base_script
{
    public grievous_death()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

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
        createMyLoot(self);
        System.out.println("Grievous Loot Created");
        return SCRIPT_CONTINUE;
    }

    public void createMyLoot(obj_id self) throws InterruptedException
    {
        obj_id corpseInventory = utils.getInventoryContainer(self);
        if (corpseInventory == null) return;
        String mobType = ai_lib.getCreatureName(self);
        if (mobType == null) return;
        int x = rand(1, 100);
        if (x < 15)
        {
            static_item.createNewItemFunction("item_color_crystal_02_16", corpseInventory);
            static_item.createNewItemFunction("item_city_actor_deed", corpseInventory);
        }
        if (x < 6)
        {
            static_item.createNewItemFunction("item_color_crystal_02_16", corpseInventory);
        }
        String starfighter = "object/tangible/ship/crafted/chassis/grievous_starfighter_reward_deed.iff";
        String cybernetic = "object/tangible/wearables/cybernetic/s02/cybernetic_s02_arm_r.iff";
        createObject(starfighter, corpseInventory, "");
        createObject(cybernetic, corpseInventory, "");
    }

}