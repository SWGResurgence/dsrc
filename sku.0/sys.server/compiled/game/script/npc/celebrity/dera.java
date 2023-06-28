package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class dera extends script.base_script
{
    public static final String CONVO = "celebrity/dera_darklighter";

    public dera()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Dera Darklighter");
        return SCRIPT_CONTINUE;
    }
}
