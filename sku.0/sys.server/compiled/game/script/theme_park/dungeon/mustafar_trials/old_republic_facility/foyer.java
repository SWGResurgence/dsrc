package script.theme_park.dungeon.mustafar_trials.old_republic_facility;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.groundquests;
import script.obj_id;

public class foyer extends script.base_script
{
    public foyer()
    {
    }

    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        groundquests.sendSignal(item, "mustafar_uplink_comm");
        return SCRIPT_CONTINUE;
    }
}
