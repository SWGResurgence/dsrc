package script.item.plant;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class force_melon extends script.base_script
{
    public force_melon()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (hasScript(self, "item.comestible.crafted"))
        {
            detachScript(self, "item.comestible.crafted");
        }
        if (!hasScript(self, "item.medicine.stimpack"))
        {
            attachScript(self, "item.medicine.stimpack");
        }
        if (!hasObjVar(self, "healing.power"))
        {
            setObjVar(self, "healing.power", 1000);
        }
        if (!hasObjVar(self, "healing.combat_level_required"))
        {
            setObjVar(self, "healing.combat_level_required", 50);
        }
        return SCRIPT_CONTINUE;
    }
}
