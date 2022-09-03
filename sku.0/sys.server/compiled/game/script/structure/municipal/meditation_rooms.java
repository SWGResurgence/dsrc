package script.structure.municipal;

import script.*;
import script.library.*;

public class meditation_rooms extends script.base_script {
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && utils.isProfession(item, utils.FORCE_SENSITIVE)) {
            buff.applyBuff(item, "tcg_diner_meditation_rooms");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && buff.hasBuff(item, "tcg_diner_meditation_rooms")) {
            buff.removeBuff(item, "tcg_diner_meditation_rooms");
        }
        return SCRIPT_CONTINUE;
    }
}
