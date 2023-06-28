package script.systems.crafting.food.crafted_items;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.consumable;
import script.obj_id;

public class crafting_chef_drink_food extends script.systems.crafting.food.crafted_items.crafting_chef_food
{
    public crafting_chef_drink_food()
    {
    }

    public void fillStomach(obj_id prototype, int filling) throws InterruptedException
    {
        int[] stomach =
                {
                        0,
                        filling,
                        0
                };
        setObjVar(prototype, consumable.VAR_CONSUMABLE_STOMACH_VALUES, stomach);
    }
}
