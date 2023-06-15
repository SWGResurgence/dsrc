package script.theme_park.dungeon.avatar_platform;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class speaker_setup extends script.base_script
{
    public speaker_setup()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        setName(self, "an intercom");
        detachScript(self, "systems.combat.credit_for_kills");
        obj_id structure = getTopMostContainer(self);
        setObjVar(structure, "speaker", self);
        return SCRIPT_CONTINUE;
    }
}
