package script.holocron;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class datapad_entry extends script.base_script
{
    public datapad_entry()
    {
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "hasClientPath"))
        {
            destroyClientPath(getObjIdObjVar(self, "hasClientPath"));
            removeObjVar(self, "hasClientPath");
        }
        dictionary params = new dictionary();
        params.put("name", getStringObjVar(self, "newbie_handoff.name"));
        messageTo(getObjIdObjVar(self, "newbie_handoff.player"), "removeDatapadEntryData", params, 0, true);
        return SCRIPT_CONTINUE;
    }
}
