package script.theme_park.wod;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.groundquests;
import script.library.create;
import script.library.ai_lib;

public class second_sister_altar extends script.base_script
{
    public second_sister_altar()
    {
    }
    public static string_id SID_MNU_USE = new string_id("pet/pet_menu", "menu_store");
    public static final string_id TOO_SOON_REUSE = new string_id("spam", "snowball_not_ready");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!isValidId(self) || !exists(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, "string_to_use") && hasObjVar(self, "string_to_use_1"))
        {
            SID_MNU_USE = new string_id(getStringObjVar(self, "string_to_use"), getStringObjVar(self, "string_to_use_1"));
        }
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_MNU_USE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!isValidId(self) || !exists(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (item != menu_info_types.ITEM_USE)
        {
            return SCRIPT_CONTINUE;
        }
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, "quest_to_have") && hasObjVar(self, "signal_to_send") && hasObjVar(self, "task_to_have") && hasObjVar(self, "locX") && hasObjVar(self, "locY") && hasObjVar(self, "locZ") && hasObjVar(self, "toSpawn") && hasObjVar(self, "task_to_have_done_1") && hasObjVar(self, "task_to_have_done_2") && hasObjVar(self, "task_to_have_done_3"))
        {
            if (groundquests.hasCompletedTask(player, getStringObjVar(self, "quest_to_have"), getStringObjVar(self, "task_to_have_done_1")) && groundquests.hasCompletedTask(player, getStringObjVar(self, "quest_to_have"), getStringObjVar(self, "task_to_have_done_2")) && groundquests.hasCompletedTask(player, getStringObjVar(self, "quest_to_have"), getStringObjVar(self, "task_to_have_done_3")))
            {
                if (groundquests.isTaskActive(player, getStringObjVar(self, "quest_to_have"), getStringObjVar(self, "task_to_have")) || !groundquests.hasCompletedTask(player, getStringObjVar(self, "quest_to_have"), "killSister2"))
                {
                    obj_id[] targets = getNPCsInRange(getLocation(self), 100);
                    if (targets != null && targets.length > 0)
                    {
                        for (int i = 0; i < targets.length; i++)
                        {
                            if (hasObjVar(targets[i], "rescuer") && getObjIdObjVar(targets[i], "rescuer") == player)
                            {
                                return SCRIPT_CONTINUE;
                            }
                        }
                    }
                    location loc = new location(getIntObjVar(self, "locX"), getIntObjVar(self, "locY"), getIntObjVar(self, "locZ"));
                    obj_id mob = create.object(getStringObjVar(self, "toSpawn"), loc, 92);
                    if (!isValidId(mob) || !exists(mob))
                    {
                        return SCRIPT_CONTINUE;
                    }
                    setObjVar(mob, "rescuer", player);
                    ai_lib.aiFollow(mob, player);
                    playClientEffectLoc(self, "appearance/pt_wod_big_fire.prt", getLocation(self), 0.4f);
                    groundquests.sendSignal(player, getStringObjVar(self, "signal_to_send"));
                }
            }
            else if (groundquests.isTaskActive(player, getStringObjVar(self, "quest_to_have"), getStringObjVar(self, "task_to_have")))
            {
                playClientEffectLoc(self, "appearance/pt_wod_big_fire.prt", getLocation(self), 0.4f);
                groundquests.sendSignal(player, getStringObjVar(self, "signal_to_send"));
            }
        }
        return SCRIPT_CONTINUE;
    }
}