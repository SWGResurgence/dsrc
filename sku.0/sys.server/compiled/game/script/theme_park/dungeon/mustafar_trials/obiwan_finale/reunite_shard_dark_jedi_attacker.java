package script.theme_park.dungeon.mustafar_trials.obiwan_finale;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.ai_lib;
import script.obj_id;

public class reunite_shard_dark_jedi_attacker extends script.base_script
{
    public reunite_shard_dark_jedi_attacker()
    {
    }

    public int OnExitedCombat(obj_id self) throws InterruptedException
    {
        messageTo(self, "despawn", null, 120, false);
        return SCRIPT_CONTINUE;
    }

    public int despawn(obj_id self, dictionary params) throws InterruptedException
    {
        if (!ai_lib.isInCombat(self))
        {
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
