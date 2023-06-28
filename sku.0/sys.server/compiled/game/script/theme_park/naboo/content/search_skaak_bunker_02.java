package script.theme_park.naboo.content;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class search_skaak_bunker_02 extends script.base_script
{
    public search_skaak_bunker_02()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        messageTo(self, "doGating", null, 19, true);
        return SCRIPT_CONTINUE;
    }

    public int doGating(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id targetRoom = getCellId(self, "core");
        setObjVar(targetRoom, "signal_name", "hugo_rescue_skaak_02");
        if (!hasScript(targetRoom, "quest.task.ground.util.enter_room_signal"))
        {
            attachScript(targetRoom, "quest.task.ground.util.enter_room_signal");
        }
        return SCRIPT_CONTINUE;
    }
}
