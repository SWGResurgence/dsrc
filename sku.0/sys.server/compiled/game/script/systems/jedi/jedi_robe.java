package script.systems.jedi;

import script.*;
import script.base_class.*;

import script.base_script;

import script.library.force_rank;
import script.library.prose;
import script.library.utils;
import script.library.jedi;
import script.library.static_item;

public class jedi_robe extends script.base_script {
    public static final String VAR_JEDI_SKILL = "jedi.skill_required";
    public static final String VAR_ROBE_REGEN = "jedi.robe.regen";
    public static final String VAR_ROBE_POWER = "jedi.robe.power";
    public static final String VAR_OWNER = "jedi_robe_owner";
    public static final string_id SID_MUST_BE_HIGHER_RANK = new string_id("jedi_spam", "must_be_higher_rank");
    public int OnInitialize(obj_id self) throws InterruptedException {
        String templateName = getTemplateName(self);
        if (templateName.endsWith("robe_jedi_light_s04.iff") || templateName.endsWith("robe_jedi_dark_s04.iff")) {
            obj_id owner = getObjIdObjVar(self, VAR_OWNER);
            if (robeIsInvalid(owner)) {
                destroyObject(self);
                return SCRIPT_CONTINUE;
            }
        } else if (templateName.endsWith("robe_jedi_light_s05.iff") || templateName.endsWith("robe_jedi_dark_s05.iff")) {
            /// TODO: Implement when council ranks are in
        }
        return SCRIPT_CONTINUE;
    }
    private boolean robeIsInvalid(obj_id owner) throws InterruptedException {
        /// TODO: Check if player is 
         return isGod(owner)
            || (getLevel(owner) < 90
            || pvpGetCurrentGcwRank(owner) < 15
            || !utils.isProfession(owner, utils.FORCE_SENSITIVE)
            || force_rank.getCouncilAffiliation(owner) == -1
            || force_rank.getForceRank(owner) == -1);
    }
}
