package script.theme_park.dungeon.mustafar_trials.old_republic_facility;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class boss_timer extends script.base_script
{
    public boss_timer()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
