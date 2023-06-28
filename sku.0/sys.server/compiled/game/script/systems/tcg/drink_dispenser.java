package script.systems.tcg;/*
@Filename: script.systems.tcg.drink_dispenser
@Author: BubbaJoeX
@Purpose: Abbub's Sodapop Machine
@   This item will randomly give you one of four specialty drinks.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.buff;
import script.library.utils;

public class drink_dispenser extends script.base_script
{
    public String SODA_NAME = "Abubb's Sodapop";
    public float DURATION = 3600.0f;
    public float BASE = 1.0f;
    public float BONUS = 0.25f;

    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        obj_id[] pInvContents = utils.getContents(player, false);
        if (pInvContents == null)
        {
            return SCRIPT_CONTINUE;
        }
        //check if the player has a 'small glass' in their inventory
        for (obj_id pInvContent : pInvContents)
        {
            if (getTemplateName(pInvContent).equals("object/tangible/component/food/container_small_glass.iff"))
            {
                mi.addRootMenu(menu_info_types.ITEM_USE, unlocalized("Dispense Drink"));
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int menu) throws InterruptedException
    {
        if (menu == menu_info_types.ITEM_USE)
        {
            obj_id[] pInvContents = utils.getContents(player, false);
            if (pInvContents == null)
            {
                return SCRIPT_CONTINUE;
            }
            //check if the player has the item template of object/tangible/component/food/container_small_glass.iff
            for (obj_id pInvContent : pInvContents)
            {
                if (getTemplateName(pInvContent).equals("object/tangible/component/food/container_small_glass.iff"))
                {
                    int drink = rand(1, 4);
                    if (drink == 1)
                    {
                        broadcast(player, "You dispense a glass of " + SODA_NAME + ".");
                        obj_id blueberry = createObject("object/tangible/food/drink/bubbas_blueberry_soda.iff", player, "");
                        if (isIdValid(blueberry))
                        {
                            destroyObject(pInvContent);
                            return SCRIPT_CONTINUE;
                        }
                    }
                    else if (drink == 2)
                    {
                        broadcast(player, "You dispense a glass of " + SODA_NAME + ".");
                        obj_id grape = createObject("object/tangible/food/drink/bubbas_grape_soda.iff", player, "");
                        if (isIdValid(grape))
                        {
                            destroyObject(pInvContent);
                            return SCRIPT_CONTINUE;
                        }
                    }
                    else if (drink == 3)
                    {
                        broadcast(player, "You dispense a glass of " + SODA_NAME + ".");
                        obj_id orange = createObject("object/tangible/food/drink/bubbas_orange_soda.iff", player, "");
                        if (isIdValid(orange))
                        {
                            destroyObject(pInvContent);
                            return SCRIPT_CONTINUE;
                        }
                    }
                    else if (drink == 4)
                    {
                        broadcast(player, "You dispense a glass of " + SODA_NAME + ".");
                        obj_id strawberry = createObject("object/tangible/food/drink/bubbas_strawberry_soda.iff", player, "");
                        if (isIdValid(strawberry))
                        {
                            destroyObject(pInvContent);
                            return SCRIPT_CONTINUE;
                        }
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void refreshDrink(obj_id drink, String buffName, int buffDuration) throws InterruptedException
    {
        if (isIdValid(drink))
        {

        }
    }
}
