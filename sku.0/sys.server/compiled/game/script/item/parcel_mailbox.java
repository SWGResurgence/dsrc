package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose: Allows players to send items to other players online (or offline) if the mailboxes are on the same planet..*/

import script.*;
import script.library.sui;
import script.library.utils;

@SuppressWarnings("unused")
class parcel_mailbox extends script.base_script
{
    public String VAR_OWNER = "parcel_mailbox.owner";
    public String VAR_SETUP = "parcel_mailbox.setup";
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self)
    {
        if (hasObjVar(getPlanetByName("tatooine"), "mailbox_" + getObjIdObjVar(self, VAR_OWNER)))
        {
            removeObjVar(getPlanetByName("tatooine"), "mailbox_" + getObjIdObjVar(self, VAR_OWNER));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (hasObjVar(self, VAR_OWNER))
        {
            if (transferer != getObjIdObjVar(self, VAR_OWNER))
            {
                sendSystemMessage(transferer, "You do not have access to this mailbox.", null);
                return SCRIPT_OVERRIDE;
            }
            else if (hasScript(srcContainer, "item.parcel_mailbox"))
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (hasObjVar(self, VAR_OWNER))
        {
            if (transferer != getObjIdObjVar(self, VAR_OWNER))
            {
                sendSystemMessage(transferer, "You do not have access to this mailbox.", null);
                return SCRIPT_OVERRIDE;
            }
            else if (hasScript(srcContainer, "item.parcel_mailbox"))
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!hasObjVar(self, VAR_SETUP))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU5, new string_id("Initialize Mailbox"));
        }
        if (player == getObjIdObjVar(self, VAR_OWNER) && getFilledVolume(self) > 0)
        {
            int mailRadial = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Mail Menu"));
            mi.addSubMenu(mailRadial, menu_info_types.SERVER_MENU2, new string_id("Send Parcels"));
            mi.addSubMenu(mailRadial, menu_info_types.SERVER_MENU3, new string_id("Retrieve Parcels"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU5)
        {
            if (hasObjVar(player, "mailboxObj"))
            {
                sendSystemMessage(player, "You already initialized a mailbox, and cannot setup another.", null);
                return SCRIPT_CONTINUE;
            }
            if (!hasObjVar(self, VAR_OWNER))
            {
                setObjVar(self, VAR_OWNER, player);
                setObjVar(self, VAR_SETUP, 1);
                setObjVar(getPlanetByName("tatooine"), "mailbox_" + player, self);
                broadcast(player, "You have initialized your mailbox. In order to send parcels, your recipient's mailbox must be initialized as well.");
                setObjVar(player, "mailboxObj", self);
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(self, VAR_OWNER))
        {
            if (player == getObjIdObjVar(self, VAR_OWNER))
            {
                if (item == menu_info_types.SERVER_MENU2)
                {
                    obj_id[] contents = getContents(self);
                    for (obj_id discardedItem : contents)
                    {
                        putInOverloaded(discardedItem, utils.getInventoryContainer(player));
                    }
                    return SCRIPT_CONTINUE;
                }
                if (item == menu_info_types.SERVER_MENU3)
                {
                    sendItemMail(self, player);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public static void sendItemMail(obj_id self, obj_id player) throws InterruptedException
    {
        sui.inputbox(self, player, "Please enter the non-capitalized name of which you wish to send these items to.", sui.OK_CANCEL, "MAILBOX", sui.INPUT_NORMAL, null, "handleMailTo", null);
    }

    public int handleMailTo(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        String recipient = sui.getInputBoxText(params);
        if (recipient == null || recipient.equals(""))
        {
            broadcast(player, "You must enter a name.");
            return SCRIPT_CONTINUE;
        }
        obj_id recipientId = getPlayerIdFromFirstName(recipient);
        if (!isIdValid(recipientId))
        {
            broadcast(player, "That player does not exist.");
            return SCRIPT_CONTINUE;
        }
        if (recipientId == player)
        {
            broadcast(player, "You cannot send items to yourself.");
            return SCRIPT_CONTINUE;
        }
        obj_id destinationContainer = getObjIdObjVar(getPlanetByName("tatooine"), "mailbox_" + recipientId);
        if (!isIdValid(destinationContainer))
        {
            broadcast(player, "That player does not have a mailbox on this planet.");
            return SCRIPT_CONTINUE;
        }
        if (getFilledVolume(self) == 0)
        {
            broadcast(player, "You have no items to send.");
            return SCRIPT_CONTINUE;
        }
        obj_id[] items = getContents(self);
        int numItems = items.length;
        for (obj_id item : items)
        {
            putIn(item, destinationContainer);
        }
        broadcast(player, "You have sent " + numItems + " items to " + getPlayerName(recipientId) + ".");
        return SCRIPT_CONTINUE;
    }

}
