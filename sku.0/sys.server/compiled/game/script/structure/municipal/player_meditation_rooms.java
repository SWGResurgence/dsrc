package script.structure.municipal;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.buff;
import script.library.utils;
import script.obj_id;

public class player_meditation_rooms extends script.base_script
{
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && utils.isProfession(item, utils.FORCE_SENSITIVE))
        {
            buff.applyBuff(item, "tcg_diner_meditation_rooms");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && buff.hasBuff(item, "tcg_diner_meditation_rooms"))
        {
            buff.removeBuff(item, "tcg_diner_meditation_rooms");
        }
        return SCRIPT_CONTINUE;
    }
}
