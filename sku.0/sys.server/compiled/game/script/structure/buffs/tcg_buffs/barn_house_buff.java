package script.structure.buffs.tcg_buffs;

import script.base_script;
import script.library.buff;
import script.obj_id;

public class barn_house_buff extends base_script
{
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        buff.applyBuff(item, "tcg_barn_buff");
        return SCRIPT_CONTINUE;
    }
}
