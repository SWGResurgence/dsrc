package script.structure.municipal;

import script.library.buff;
import script.library.utils;
import script.obj_id;

public class npcCantina extends script.base_script {
    public static final int HEALING_PULSE_MIN = 200;
    public static final int HEALING_PULSE_MAX = 400;
    public static final int WOUND_HEAL = 10;
    public static final int SHOCK_HEAL = 3;
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && utils.isProfession(item, utils.ENTERTAINER)) {
            buff.applyBuff(item, "entertainer_buff");
        }
        if (isPlayer(item) && utils.isProfession(item, utils.MEDIC)) {
            buff.applyBuff(item, "medic_buff");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException {
        if (isPlayer(item) && buff.hasBuff(item, "entertainer_buff")) {
            buff.removeBuff(item, "entertainer_buff");
        }
        if (isPlayer(item) && buff.hasBuff(item, "medic_buff")) {
            buff.removeBuff(item, "medic_buff");
        }
        return SCRIPT_CONTINUE;
    }
}
