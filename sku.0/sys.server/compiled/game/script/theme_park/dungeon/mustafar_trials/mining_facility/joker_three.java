package script.theme_park.dungeon.mustafar_trials.mining_facility;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.sequencer;
import script.obj_id;

public class joker_three extends script.base_script
{
    public joker_three()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        sequencer.registerSequenceObject(self, "joker3");
        return SCRIPT_CONTINUE;
    }
}
