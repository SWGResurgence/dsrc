package script.structure.buffs.tcg_buffs;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.base_script;
import script.library.buff;
import script.library.utils;
import script.obj_id;

public class meditative_room_buff extends base_script
{
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && utils.isProfession(item, utils.FORCE_SENSITIVE))
        {
            buff.applyBuff(item, "tcg_meditation_rooms_buff");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && buff.hasBuff(item, "tcg_meditation_rooms_buff"))
        {
            buff.removeBuff(item, "tcg_meditation_rooms_buff");
        }
        return SCRIPT_CONTINUE;
    }
}
