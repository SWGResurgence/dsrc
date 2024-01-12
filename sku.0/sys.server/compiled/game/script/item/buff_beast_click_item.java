package script.item;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.*;

public class buff_beast_click_item extends script.base_script
{
    public static final string_id SID_NOT_YET = new string_id("base_player", "not_yet");
    public static final string_id SID_NOT_LINKED = new string_id("base_player", "not_linked");
    public static final string_id SID_NOT_LINKED_TO_HOLDER = new string_id("base_player", "not_linked_to_holder");
    public static final string_id BUFF_APPLIED = new string_id("base_player", "buff_applied");
    public static final string_id SID_ITEM_NOT_IN_INVENTORY = new string_id("base_player", "not_in_your_inventory");
    public static final string_id SID_MUST_BIO_LINK_FROM_INVENTORY = new string_id("base_player", "must_biolink_to_use_from_inventory");
    public static final string_id SID_BIOLINK_OTHER_PLAYER = new string_id("base_player", "wrong_player_per_biolink");
    public static final string_id SID_NO_USE_WHILE_DEAD = new string_id("player_structure", "while_dead");
    public static final string_id BUFF_REMOVED = new string_id("base_player", "buff_removed");
    public static final string_id SID_BEAST_DEAD = new string_id("base_player", "beast_buff_beast_dead");
    public static final string_id SID_BUFF_NOT_OWNER = new string_id("base_player", "beast_buff_not_owner");
    public static final string_id SID_ITEM_LEVEL_TOO_LOW = new string_id("base_player", "beast_buff_level_too_low");
    public static final string_id CANT_APPLY_BUFF = new string_id("base_player", "beast_buff_cant_apply_buff");
    public static final string_id SID_NO_ACTIVE_BEAST = new string_id("base_player", "beast_buff_no_active_beast");
    public static final String OWNER_OID = "owner";

    public buff_beast_click_item()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (canManipulate(player, self, true, true, 15, true))
        {
            if (utils.isNestedWithinAPlayer(self))
            {
                menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
                if (mid != null)
                {
                    mid.setServerNotify(true);
                }
                else
                {
                    mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("ui_radial", "item_use"));
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (utils.getContainingPlayer(self) != player)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id biolink = getBioLink(self);
        if (isValidId(biolink) && biolink == utils.OBJ_ID_BIO_LINK_PENDING)
        {
            sendSystemMessage(player, SID_MUST_BIO_LINK_FROM_INVENTORY);
            return SCRIPT_CONTINUE;
        }
        if (isValidId(biolink) && biolink != player)
        {
            sendSystemMessage(player, SID_BIOLINK_OTHER_PLAYER);
            return SCRIPT_CONTINUE;
        }
        else if (hasObjVar(self, OWNER_OID))
        {
            if (player != getObjIdObjVar(self, OWNER_OID))
            {
                sendSystemMessage(player, SID_BUFF_NOT_OWNER);
                return SCRIPT_CONTINUE;
            }
        }
        if (item == menu_info_types.ITEM_USE)
        {
            if (isIncapacitated(player) || isDead(player))
            {
                sendSystemMessage(player, SID_NO_USE_WHILE_DEAD);
                return SCRIPT_CONTINUE;
            }
            if (hasScript(self, "item.armor.biolink_item_non_faction"))
            {
                obj_id bioLinked = getBioLink(self);
                if (bioLinked == null || bioLinked == utils.OBJ_ID_BIO_LINK_PENDING)
                {
                    sendSystemMessage(player, SID_NOT_LINKED);
                    return SCRIPT_CONTINUE;
                }
                if (bioLinked != player)
                {
                    sendSystemMessage(player, SID_NOT_LINKED_TO_HOLDER);
                    return SCRIPT_CONTINUE;
                }
            }
            String itemName = getStaticItemName(self);
            if (itemName == null || itemName.equals(""))
            {
                CustomerServiceLog("buff", "buff_click_item object self: " + self + " Name: " + getName(self) + " had an invalid static item name. Buff object is bailing out early as a result.");
                sendSystemMessage(player, "buff_click_item object self: " + self + " Name: " + getName(self) + " had an invalid static item name", "");
                return SCRIPT_CONTINUE;
            }
            dictionary itemData = new dictionary();
            itemData = dataTableGetRow(static_item.ITEM_STAT_BALANCE_TABLE, itemName);
            if (itemData == null)
            {
                CustomerServiceLog("buff", "buff_click_item object self: " + self + " Name: " + getName(self) + " had invalid item data and as a result the buff object is bailing out early.");
                sendSystemMessage(player, "buff_click_item object self: " + self + " Name: " + getName(self) + " had invalid item data", "");
                return SCRIPT_CONTINUE;
            }
            if (!beast_lib.hasActiveBeast(player))
            {
                sendSystemMessage(player, SID_NO_ACTIVE_BEAST);
                return SCRIPT_CONTINUE;
            }
            obj_id activeBeast = beast_lib.getBeastOnPlayer(player);
            if (!isIdValid(activeBeast))
            {
                sendSystemMessage(player, SID_NO_ACTIVE_BEAST);
                return SCRIPT_CONTINUE;
            }
            if (isIncapacitated(activeBeast) || isDead(activeBeast))
            {
                sendSystemMessage(player, SID_BEAST_DEAD);
                return SCRIPT_CONTINUE;
            }
            String buffName = itemData.getString("buff_name");
            String coolDownGroup = itemData.getString("cool_down_group");
            String clientEffect = itemData.getString("client_effect");
            String clientAnimation = itemData.getString("client_animation");
            int reuseTime = itemData.getInt("reuse_time");
            int requiredLevel = itemData.getInt("required_level_for_effect");
            String varName = "clickItem." + coolDownGroup;
            int buffTime = getIntObjVar(player, varName);
            int beastLevel = getLevel(activeBeast);
            if (buff.hasBuff(activeBeast, buffName))
            {
                buff.removeBuff(activeBeast, buffName);
                sendSystemMessage(player, BUFF_REMOVED);
                return SCRIPT_CONTINUE;
            }
            if (static_item.validateLevelRequired(activeBeast, requiredLevel))
            {
                if (getGameTime() > buffTime || getGameTime() < buffTime && isGod(player))
                {
                    if (buff.canApplyBuff(activeBeast, buffName))
                    {
                        if (getGameTime() < buffTime && isGod(player))
                        {
                            sendSystemMessage(player, "The Buff was applied because you were in god mode.", null);
                        }
                        CustomerServiceLog("buff", "buff_click_item object self: " + self + " Static Item Name: " + itemName + " providing buff: " + buffName + " being used by player: " + player + " Name: " + getName(player) + " on their beast: " + activeBeast + " of beast level: " + beastLevel);
                        sendSystemMessage(player, BUFF_APPLIED);
                        utils.setScriptVar(activeBeast, beast_lib.getBeastBuffItemVar(buffName), self);
                        buff.applyBuff(activeBeast, player, buffName);
                        setObjVar(player, varName, (getGameTime() + (reuseTime)));
                        sendCooldownGroupTimingOnly(player, getStringCrc(coolDownGroup.toLowerCase()), reuseTime);
                        doAnimationAction(activeBeast, clientAnimation);
                        playClientEffectObj(activeBeast, clientEffect, player, "");
                        if (getCount(self) > 0)
                        {
                            CustomerServiceLog("buff", "buff_click_item object self: " + self + " Static Item Name: " + itemName + " providing buff: " + buffName + " being used by player: " + player + " Name: " + getName(player) + " on their beast: " + activeBeast + ". Object is being decremented by ONE.");
                            static_item.decrementStaticItem(self);
                        }
                    }
                    else
                    {
                        sendSystemMessage(player, CANT_APPLY_BUFF);
                        return SCRIPT_CONTINUE;
                    }
                }
                else
                {
                    int timeDiff = buffTime - getGameTime();
                    prose_package pp = prose.getPackage(SID_NOT_YET, timeDiff);
                    sendSystemMessageProse(player, pp);
                    return SCRIPT_CONTINUE;
                }
            }
            else
            {
                sendSystemMessage(player, SID_ITEM_LEVEL_TOO_LOW);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
