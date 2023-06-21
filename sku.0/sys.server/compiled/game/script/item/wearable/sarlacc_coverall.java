package script.item.wearable;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class sarlacc_coverall extends script.base_script
{
    public sarlacc_coverall()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setSkillModBonus(self, "resistance_disease", 25);
        messageTo(self, "destroyCoverall", null, 86400, true);
        return SCRIPT_CONTINUE;
    }

    public int destroyCoverall(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
