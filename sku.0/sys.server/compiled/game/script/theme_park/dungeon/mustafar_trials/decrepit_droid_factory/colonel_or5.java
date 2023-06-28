package script.theme_park.dungeon.mustafar_trials.decrepit_droid_factory;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.trial;
import script.obj_id;

public class colonel_or5 extends script.base_script
{
    public static final boolean LOGGING = false;

    public colonel_or5()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        trial.setHp(self, trial.HP_DECREPIT_COLONEL);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        messageTo(trial.getTop(self), "colonelDied", null, 0, false);
        return SCRIPT_CONTINUE;
    }

    public void doLogging(String section, String message) throws InterruptedException
    {
        if (LOGGING)
        {
            LOG("logging/colonel_or5/" + section, message);
        }
    }
}
