package script.theme_park.heroic.axkva_min;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.trial;
import script.obj_id;

public class axkva_shade extends script.base_script
{
    public axkva_shade()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        detachScript(self, "ai.ai");
        detachScript(self, "ai.creature_combat");
        attachScript(self, "developer.soe.e3demo.yavin_e3");
        obj_id[] players = trial.getPlayersInDungeon(trial.getTop(self));
        setCreatureCoverVisibility(self, false);
        if (players == null)
        {
            return SCRIPT_CONTINUE;
        }
        for (obj_id player : players)
        {
            addPassiveReveal(self, player, 1);
        }
        return SCRIPT_CONTINUE;
    }
}
