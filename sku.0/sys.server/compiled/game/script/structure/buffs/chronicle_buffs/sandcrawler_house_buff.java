package script.structure.buffs.chronicle_buffs;

import script.*;
import script.library.*;

public class sandcrawler_house_buff extends base_script {
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && utils.isProfession(item, utils.TRADER)) {
            buff.applyBuff(item, "tcg_sandcrawler_buff");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && buff.hasBuff(item, "tcg_sandcrawler_buff")) {
            buff.removeBuff(item, "tcg_sandcrawler_buff");
        }
        return SCRIPT_CONTINUE;
    }
}
