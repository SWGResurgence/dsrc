package script.item.camp;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class camp_elite extends script.item.camp.camp_base
{
    public camp_elite()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "campPower", 5);
        setObjVar(self, "skillReq", 75);
        return SCRIPT_CONTINUE;
    }
}
