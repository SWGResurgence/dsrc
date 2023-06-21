package script.item;/*
@Filename: script.item.parcel_mailbox
@Author: BubbaJoeX
@Purpose: Allows players to send items to other players online (or offline) if the mailboxes are on the same planet and setup.*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.city;
import script.library.sui;
import script.library.utils;

import java.util.Random;

@SuppressWarnings("unused")
class parcel_mailbox extends script.base_script
{
    public static final String VAR_ADDRESS = "parcel_mailbox.address";
    public static final String VAR_OWNER = "parcel_mailbox.owner";
    public static final String VAR_SETUP = "parcel_mailbox.setup";
    public static final float VAR_MAIL_SPEED_NON_CITY = 300f;

    public void sendItemMail(obj_id self, obj_id player) throws InterruptedException
    {
        sui.inputbox(self, player, "Please enter the non-capitalized name of which you wish to send these items to.", sui.OK_CANCEL, "MAILBOX", sui.INPUT_NORMAL, null, "handleMailTo", null);
    }

    public String generatePostalCode(obj_id self) throws InterruptedException
    {
        Random rand = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        sb.append("MB-");
        for (int i = 0; i < 4; i++)
        {
            sb.append(letters.charAt(rand.nextInt(letters.length())));
        }
        sb.append("-");
        sb.append(digits.charAt(rand.nextInt(10)));
        sb.append(digits.charAt(rand.nextInt(10)));

        if (city.isInCity(getLocation(self)))
        {
            sb.append("-C");
        }
        else
        {
            sb.append("-W");
        }
        return sb.toString();
    }

    public int OnAttach(obj_id self)
    {
        setName(self, "Mailbox");
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
        setName(self, "Mailbox");
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(transferer) && (transferer == getObjIdObjVar(self, VAR_OWNER)))
        {
            return SCRIPT_CONTINUE;
        }
        else
        {
            return SCRIPT_OVERRIDE;
        }
    }

    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(transferer))
        {
            return SCRIPT_OVERRIDE;
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!hasObjVar(self, VAR_SETUP))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU5, new string_id("Initialize Mailbox"));
        }
        else if (player == getObjIdObjVar(self, VAR_OWNER) && getFilledVolume(self) > 0 && hasObjVar(self, VAR_SETUP))
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
                setObjVar(self, VAR_ADDRESS, generatePostalCode(self));
                setObjVar(self, VAR_OWNER, player);
                setObjVar(self, VAR_SETUP, 1);
                setObjVar(getPlanetByName("tatooine"), "mailbox_" + player, self);
                broadcast(player, "You have initialized your mailbox. In order to send parcels, your recipient's mailbox must be initialized as well.");
                setObjVar(player, "mailboxObj", self);
                return SCRIPT_CONTINUE;
            }
        }
        else if (hasObjVar(self, VAR_OWNER))
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

    public int handleMailTo(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        String recipient = sui.getInputBoxText(params);
        if (recipient == null || recipient.equals(""))
        {
            broadcast(player, "The name you entered is not valid. Try again.");
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
        if (!isContentsAbleToFitInto(self, destinationContainer))
        {
            broadcast(player, "That player's mailbox is full.");
            return SCRIPT_CONTINUE;
        }
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
        if (!isCityMailbox(self))
        {
            dictionary d = new dictionary();
            d.put("sender", player);
            d.put("recipient", recipientId);
            messageTo(self, "handleDelayedMailTo", d, VAR_MAIL_SPEED_NON_CITY, true);
            broadcast(player, "Your items will be sent to " + getPlayerName(recipientId) + "'s mailbox in 5 minutes. You can cancel this by retrieving your items from your mailbox.");
        }
        else
        {
            obj_id[] items = getContents(self);
            int numItems = items.length;
            for (obj_id item : items)
            {
                putIn(item, destinationContainer);
            }
            broadcast(player, "You have sent " + numItems + " items to " + getPlayerName(recipientId) + "'s mailbox.");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            LOG("Scripting", "idx was negative 1 for mailbox.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, VAR_ADDRESS))
        {
            names[idx] = utils.packStringId(new string_id("Address"));
            attribs[idx] = getStringObjVar(self, VAR_ADDRESS);
            idx++;
        }
        if (hasObjVar(self, VAR_OWNER))
        {
            names[idx] = utils.packStringId(new string_id("Owner"));
            attribs[idx] = getPlayerName(getObjIdObjVar(self, VAR_OWNER));
        }
        return SCRIPT_CONTINUE;
    }

    public boolean isCityMailbox(obj_id self)
    {
        String postalCode = getStringObjVar(self, VAR_ADDRESS);
        return postalCode.endsWith("-C");
    }

    public int handleDelayedMailTo(obj_id self, dictionary d)
    {
        obj_id player = d.getObjId("sender");
        obj_id[] items = getContents(self);
        int numItems = items.length;
        if (numItems == 0)
        {
            broadcast(player, "You have cancelled this delivery.");
            return SCRIPT_CONTINUE;
        }
        for (obj_id item : items)
        {
            obj_id destinationContainer = getObjIdObjVar(getPlanetByName("tatooine"), "mailbox_" + d.getObjId("recipientId"));
            putIn(item, destinationContainer);
        }
        broadcast(player, "You have sent " + numItems + " items to " + getPlayerName(d.getObjId("recipientId")) + ".");
        return SCRIPT_CONTINUE;
    }

    public boolean isContentsAbleToFitInto(obj_id containerSelf, obj_id containerDestination)
    {
        return getVolumeFree(containerDestination) >= getFilledVolume(containerSelf);
    }
}
