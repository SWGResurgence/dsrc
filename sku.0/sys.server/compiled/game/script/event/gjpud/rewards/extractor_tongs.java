package script.event.gjpud.rewards;
/*
@Filename: script.event.gjpud.rewards.
@Author: BubbaJoeX
@Purpose: Extractor Tongs script for GJPUD.
@Functionality: Using this tool will allow players to extract metal (Scrap Heaps) from conspicuous areas.
*/

import script.*;
import script.library.city;
import script.library.utils;

public class extractor_tongs extends script.base_script
{
    public static int COOLDOWN_TIME = 480; // 8 minutes.
    public static int currentGameTime = getCalendarTime();
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        int lastUsed = getIntObjVar(self, "used.timestamp");
        names[idx] = "last_used";
        attribs[idx] = getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getIntObjVar(self, "used.timestamp"));
        idx++;
        names[idx] = "next_use";
        attribs[idx] = getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getIntObjVar(self, "used.timestamp") + COOLDOWN_TIME);
        idx++;
        String NO = "\\#DD1234" + "No" + "\\#FFFFFF";
        String YES = "\\#32CD32" + "Yes" + "\\#FFFFFF";
        if (currentGameTime < (lastUsed + COOLDOWN_TIME))
        {
            names[idx] = "ready";
            attribs[idx] = NO;
            idx++;
        }
        else
        {
            names[idx] = "ready";
            attribs[idx] = YES;
            idx++;
        }
        return SCRIPT_CONTINUE;
    }
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
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Attempt Extraction"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            int lastUsed = getIntObjVar(self, "used.timestamp");
            if (currentGameTime < (lastUsed + COOLDOWN_TIME))
            {
                broadcast(player, "Attempting to extract scrap metal this soon would fatigue you.");
                return SCRIPT_CONTINUE;
            }
            if (isInWorldCell(player))
            {
                if (city.isInCity(getLocation(self)))
                {
                    broadcast(player, "Due to intergalactic law, you may not use this tool within city limits");
                }
                else
                {
                    attemptExtraction(self, player);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int attemptExtraction(obj_id self, obj_id player)
    {
        String CEF = "";
        String success = "You have successfully extracted one piece of scrap metal.";
        String failure = "You have failed to extract scrap metal.";
        int chance = rand(0, 99);
        if (chance <= 49)
        {
            broadcast(player, success);
            //handleRewardTrigger
        }
        else if (chance >= 49)
        {
            broadcast(player, failure);

        }
        return SCRIPT_CONTINUE;
    }
}
