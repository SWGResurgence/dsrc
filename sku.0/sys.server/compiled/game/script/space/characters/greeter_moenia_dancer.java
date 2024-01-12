package script.space.characters;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.ai_lib;
import script.library.utils;
import script.obj_id;

public class greeter_moenia_dancer extends script.base_script
{
    public greeter_moenia_dancer()
    {
    }

    public int stopDancing(obj_id self, dictionary params) throws InterruptedException
    {
        utils.removeScriptVar(self, "isDancing");
        ai_lib.setMood(self, "calm");
        return SCRIPT_CONTINUE;
    }
}
