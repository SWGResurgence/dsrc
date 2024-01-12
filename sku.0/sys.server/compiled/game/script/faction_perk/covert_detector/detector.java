package script.faction_perk.covert_detector;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class detector extends script.base_script
{
    public detector()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        detachScript(self, "faction_perk.covert_detector.detector");
        return SCRIPT_CONTINUE;
    }
}
