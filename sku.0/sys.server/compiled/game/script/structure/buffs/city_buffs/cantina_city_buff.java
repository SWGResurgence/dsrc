package script.structure.buffs.city_buffs;

import script.library.buff;
import script.library.ai_lib;
import script.library.create;
import script.library.utils;
import script.location;
import script.obj_id;

public class cantina_city_buff extends script.base_script {
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && utils.isProfession(item, utils.ENTERTAINER)) {
            buff.applyBuff(item, "entertainer_buff");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && buff.hasBuff(item, "entertainer_buff")) {
            buff.removeBuff(item, "entertainer_buff");
        }
        return SCRIPT_CONTINUE;
    }
}
