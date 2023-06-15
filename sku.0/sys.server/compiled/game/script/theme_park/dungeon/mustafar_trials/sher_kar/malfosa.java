package script.theme_park.dungeon.mustafar_trials.sher_kar;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.trial;
import script.obj_id;

public class malfosa extends script.base_script
{
    public malfosa()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        trial.setHp(self, trial.HP_SHER_KAR_CONSORT);
        trial.bumpSession(self);
        return SCRIPT_CONTINUE;
    }
}
