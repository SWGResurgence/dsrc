package script.event.ewok_festival;/*
@Filename: script.event.ewok_festival.secret_lover
@Author: BubbaJoeX
@Purpose: This script will assign the object a variable with a random player across the server. The player will then need to find the player to give them the heart.
@Date $TODAY
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.marriage;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class secret_lover extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        setName(self, "Love Master 5000");
        String DESC = "This is a Love Master 5000. It will assign you a secret lover across the galaxy. If you find them, give them this heart to show your love.";
        return SCRIPT_CONTINUE;
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        if (names == null || attribs == null || names.length != attribs.length)
        {
            return SCRIPT_CONTINUE;
        }
        final int firstFreeIndex = getFirstFreeIndex(names);
        if (firstFreeIndex >= 0 && firstFreeIndex < names.length)
        {
            names[firstFreeIndex] = utils.packStringId(new string_id("Found Love?"));
            attribs[firstFreeIndex] = getBooleanObjVar(self, "loveday.setup") ? ("Yes") : ("No");
        }
        boolean status = getBooleanObjVar(self, "loveday.setup");
        if (status)
        {
            names[firstFreeIndex] = utils.packStringId(new string_id("Secret Lover"));
            attribs[firstFreeIndex] = getStringObjVar(self, "loveday.secret_lover_name" + "\n\n");
            names[firstFreeIndex] = utils.packStringId(new string_id("True Love Bonus"));
            attribs[firstFreeIndex] = getBooleanObjVar(self, "loveday.true_love") ? ("Yes") : ("No");
        }
        return SCRIPT_CONTINUE;
    }

    private String getRandomReward(obj_id self)
    {
        return "nvm";
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (marriage.isMarried(player))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU10, new string_id("Spread Love"));
        }
        boolean setup = hasObjVar(self, "loveday.setup");
        if (setup)
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU11, new string_id("Spread Love"));
        }
        else
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU12, new string_id("Find Love"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.SERVER_MENU10)
        {
            setupLoveLink(self, player);
        }
        if (item == menu_info_types.SERVER_MENU11)
        {
            //spreadLove(self, player);
        }
        return SCRIPT_CONTINUE;
    }

    public int setupLoveLink(obj_id self, obj_id player)
    {
        obj_id[] players = getAllPlayers(getLocation(player), 160000f);
        if (players == null || players.length == 0)
        {
            broadcast(player, "The Love Master 5000 has detected no players in the area. Try a different location.");
            return SCRIPT_CONTINUE;
        }
        obj_id target = players[rand(0, players.length - 1)];
        if (target == player)
        {
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "loveday.secret_lover", target);
        setObjVar(self, "loveday.secret_lover_name", getName(target));
        setObjVar(self, "loveday.setup", true);
        return SCRIPT_CONTINUE;
    }
}
