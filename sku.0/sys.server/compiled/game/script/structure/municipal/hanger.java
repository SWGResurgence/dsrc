package script.structure.municipal;

import script.library.buff;
import script.library.utils;
import script.obj_id;

public class hanger extends script.base_script
{
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && utils.isProfession(item, utils.TRADER))
        {
            buff.applyBuff(item, "bm_helper_monkey_shipwright_1");
            buff.applyBuff(item, "bm_helper_monkey_shipwright_2");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && buff.hasBuff(item, "bm_helper_monkey_shipwright_1"))
        {
            buff.removeBuff(item, "bm_helper_monkey_shipwright_1");
        }
        if (isPlayer(item) && buff.hasBuff(item, "bm_helper_monkey_shipwright_2"))
        {
            buff.removeBuff(item, "bm_helper_monkey_shipwright_2");
        }
        return SCRIPT_CONTINUE;
    }
}
