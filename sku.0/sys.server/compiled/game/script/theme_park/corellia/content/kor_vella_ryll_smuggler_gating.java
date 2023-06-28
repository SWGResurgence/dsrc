package script.theme_park.corellia.content;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class kor_vella_ryll_smuggler_gating extends script.base_script
{
    public kor_vella_ryll_smuggler_gating()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        messageTo(self, "doGating", null, 19, true);
        return SCRIPT_CONTINUE;
    }

    public int doGating(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id targetRoom = getCellId(self, "entry");
        if (isIdValid(targetRoom))
        {
            if (!hasScript(targetRoom, "theme_park.corellia.content.kor_vella_ryll_smuggler_block"))
            {
                attachScript(targetRoom, "theme_park.corellia.content.kor_vella_ryll_smuggler_block");
            }
        }
        return SCRIPT_CONTINUE;
    }
}
