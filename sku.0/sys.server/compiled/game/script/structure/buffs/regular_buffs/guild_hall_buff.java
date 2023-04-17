package script.structure.buffs.regular_buffs;

import script.base_script;
import script.library.buff;
import script.obj_id;

public class guild_hall_buff extends base_script {
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        buff.applyBuff(item, "tcg_guild_hall_buff");
        return SCRIPT_CONTINUE;
    }
}
