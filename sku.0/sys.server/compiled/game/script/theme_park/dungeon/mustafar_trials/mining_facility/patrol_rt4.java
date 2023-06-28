package script.theme_park.dungeon.mustafar_trials.mining_facility;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.sequencer;
import script.obj_id;

public class patrol_rt4 extends script.base_script
{
    public patrol_rt4()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        sequencer.registerSequenceObject(self, "patrol_rt4");
        return SCRIPT_CONTINUE;
    }
}
