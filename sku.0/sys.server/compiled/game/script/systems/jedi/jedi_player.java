package script.systems.jedi;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class jedi_player extends script.base_script
{
    public jedi_player()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        detachScript(self, "systems.jedi.jedi_player");
        return SCRIPT_CONTINUE;
    }
}
