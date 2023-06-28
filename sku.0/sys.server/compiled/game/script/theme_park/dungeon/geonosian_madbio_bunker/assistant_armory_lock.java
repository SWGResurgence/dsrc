package script.theme_park.dungeon.geonosian_madbio_bunker;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;
import script.string_id;

public class assistant_armory_lock extends script.base_script
{
    public static final String MSGS = "dungeon/geonosian_madbio";

    public assistant_armory_lock()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        String objvarKey = getStringObjVar(self, "objvar_key");
        if (objvarKey == null || objvarKey.equals(""))
        {
            objvarKey = "nil";
        }
        if (!hasObjVar(item, objvarKey))
        {
            string_id locked = new string_id(MSGS, "door_locked");
            sendSystemMessage(item, locked);
            return SCRIPT_OVERRIDE;
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
    }
}
