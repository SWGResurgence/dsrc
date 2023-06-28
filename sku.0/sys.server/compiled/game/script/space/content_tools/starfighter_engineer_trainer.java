package script.space.content_tools;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class starfighter_engineer_trainer extends script.base_script
{
    public starfighter_engineer_trainer()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "trainer", "trainer_starfighter_engineer");
        return SCRIPT_CONTINUE;
    }
}
