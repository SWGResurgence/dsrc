package script.theme_park.dungeon.mustafar_trials.obiwan_finale;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class obiwan_fader_handler extends script.base_script
{
    public static final boolean CONST_FLAG_DO_LOGGING = false;

    public obiwan_fader_handler()
    {
    }

    public void debugLogging(String section, String message) throws InterruptedException
    {
        if (CONST_FLAG_DO_LOGGING)
        {
            LOG("debug/obiwan_dynamic_timeout/" + section, message);
        }
    }

    public int obiwanFadeIn(obj_id self, dictionary params) throws InterruptedException
    {
        setCreatureCover(self, 0);
        setCreatureCoverVisibility(self, true);
        return SCRIPT_CONTINUE;
    }
}
