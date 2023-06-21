package script.theme_park.dungeon.trando_slave_camp;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class warden_tosk extends script.base_script
{
    public warden_tosk()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        obj_id bunker = getTopMostContainer(self);
        if (hasObjVar(self, "parent"))
        {
            bunker = getObjIdObjVar(self, "parent");
        }
        setObjVar(bunker, "toskKilled", 1);
        return SCRIPT_CONTINUE;
    }
}
