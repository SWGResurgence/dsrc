package script.event.lifeday;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.static_item;
import script.library.utils;
import script.*;

public class wrapped_lifeday_present_two extends script.base_script
{
    public wrapped_lifeday_present_two()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null)
        {
            mid.setLabel(new string_id("spam", "unwrap"));
            mid.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        obj_id containingPlayer = utils.getContainingPlayer(self);
        if (!isIdValid(containingPlayer))
        {
            sendSystemMessage(player, new string_id("spam", "cannot_use_not_in_inv"));
            return SCRIPT_CONTINUE;
        }
        if (containingPlayer != player)
        {
            sendSystemMessage(player, new string_id("spam", "cannot_use_not_in_inv"));
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            unwrapMe(self, player);
        }
        return SCRIPT_CONTINUE;
    }

    public void unwrapMe(obj_id self, obj_id player) throws InterruptedException
    {
        int which_one = rand(1, 12);
        String gift = "";
        switch (which_one)
        {
            case 1:
                gift = "item_lifeday_gift_04_01";
                break;
            case 2:
                gift = "item_lifeday_gift_04_02";
                break;
            case 3:
                gift = "item_lifeday_gift_04_03";
                break;
            case 4:
                gift = "item_lifeday_gift_04_04";
                break;
            case 5:
                gift = "item_lifeday_gift_04_05";
                break;
            case 6:
                gift = "item_lifeday_gift_04_06";
                break;
            case 7:
                gift = "item_lifeday_gift_04_07";
                break;
            case 8:
                gift = "item_lifeday_painting_matriarch";
                break;
            case 9:
                gift = "item_lifeday_painting_patriarch";
                break;
            case 10:
                gift = "item_lifeday_painting_kashyyyk";
                break;
            case 11:
                gift = "item_lifeday_orb";
                break;
            case 12:
                gift = "item_lifeday_robe";
                break;
        }
        obj_id item = static_item.createNewItemFunction(gift, player);
        if (isIdValid(item))
        {
            sendSystemMessage(player, new string_id("spam", "unwrap_present"));
            destroyObject(self);
        }
    }
}