package script.systems.player_quest;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class player_quest_base extends script.base_script
{
    public static final String HOLOCRON_NAME = "holocronName";

    public player_quest_base()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, HOLOCRON_NAME))
        {
            String nameObjVar = getStringObjVar(self, HOLOCRON_NAME);
            setName(self, nameObjVar);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
