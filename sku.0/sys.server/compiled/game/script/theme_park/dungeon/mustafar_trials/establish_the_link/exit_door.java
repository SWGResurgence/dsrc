package script.theme_park.dungeon.mustafar_trials.establish_the_link;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.instance;
import script.library.trial;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class exit_door extends script.base_script
{
    public static final string_id SID_EXIT_TRIAL = new string_id(trial.UPLINK_STF, "bunker_exit");
    public static final string_id SID_EXIT_TRIAL_CONFIRM = new string_id(trial.UPLINK_STF, "bunker_exit_confirm");
    public exit_door()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (getDistance(self, player) > 6.0f)
        {
            return SCRIPT_CONTINUE;
        }
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_EXIT_TRIAL);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            instance.requestExitPlayer("uplink_cave", player);
        }
        return SCRIPT_CONTINUE;
    }
}
