package script.structure.buffs.regular_buffs;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.base_script;
import script.library.buff;
import script.obj_id;

public class merchant_tent_buff extends base_script
{
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        buff.applyBuff(item, "tcg_merchant_tent_buff");
        return SCRIPT_CONTINUE;
    }
}
