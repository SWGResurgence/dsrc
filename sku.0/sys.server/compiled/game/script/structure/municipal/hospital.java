package script.structure.municipal;

import script.library.buff;
import script.library.player_structure;
import script.library.structure;
import script.library.utils;
import script.obj_id;

public class hospital extends script.base_script {
    public static final int HEALING_PULSE_MIN = 200;
    public static final int HEALING_PULSE_MAX = 400;
    public static final int WOUND_HEAL = 10;
    public static final int SHOCK_HEAL = 3;
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) {
        if (isPlayer(item) && utils.isProfession(item, utils.MEDIC)) {
            buff.applyBuff(item, "hospital_buff");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) {
        if (isPlayer(item) && buff.hasBuff(item, "hospital_buff")) {
            buff.removeBuff(item, "hospital_buff");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        if (player_structure.isCivic(self)) {
            if (!utils.hasScriptVar(self, "terminalsCreated")) {
                structure.createStructureTerminals(self, "datatables/structure/municipal/registration_terminal.iff");
                utils.setScriptVar(self, "terminalsCreated", true);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException {
        if (!utils.hasScriptVar(self, "terminalsCreated")) {
            structure.createStructureTerminals(self, "datatables/structure/municipal/registration_terminal.iff");
            utils.setScriptVar(self, "terminalsCreated", true);
        }
        return SCRIPT_CONTINUE;
    }
}
