package script.theme_park.dungeon.mustafar_trials.valley_battleground;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.create;
import script.library.utils;
import script.obj_id;

public class koseyet_spawner extends script.base_script
{
    public static final String SPAWNED = "koseyetSpawned";

    public koseyet_spawner()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!isKoseyetSpawned(self))
        {
            obj_id foreman = create.object("som_battlefield_foreman_koseyet", getLocation(self));
            if (isIdValid(foreman))
            {
                utils.setScriptVar(self, SPAWNED, foreman);
            }
            attachScript(foreman, "conversation.trial_foreman_koseyet");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!isKoseyetSpawned(self))
        {
            obj_id foreman = create.object("som_battlefield_foreman_koseyet", getLocation(self));
            if (isIdValid(foreman))
            {
                utils.setScriptVar(self, SPAWNED, foreman);
            }
            attachScript(foreman, "conversation.trial_foreman_koseyet");
        }
        return SCRIPT_CONTINUE;
    }

    public boolean isKoseyetSpawned(obj_id self) throws InterruptedException
    {
        if (utils.hasScriptVar(self, SPAWNED))
        {
            return isIdValid(utils.getObjIdScriptVar(self, SPAWNED));
        }
        else
        {
            return false;
        }
    }
}
