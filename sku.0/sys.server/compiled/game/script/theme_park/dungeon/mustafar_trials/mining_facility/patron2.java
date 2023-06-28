package script.theme_park.dungeon.mustafar_trials.mining_facility;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.sequencer;
import script.obj_id;

public class patron2 extends script.base_script
{
    public patron2()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        sequencer.registerSequenceObject(self, "patron2");
        return SCRIPT_CONTINUE;
    }
}
