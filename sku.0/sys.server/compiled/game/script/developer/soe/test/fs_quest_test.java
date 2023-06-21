package script.developer.soe.test;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class fs_quest_test extends script.base_script
{
    public fs_quest_test()
    {
    }

    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
