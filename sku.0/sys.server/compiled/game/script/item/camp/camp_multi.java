package script.item.camp;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class camp_multi extends script.item.camp.camp_base
{
    public camp_multi()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "campPower", 2);
        setObjVar(self, "skillReq", 20);
        return SCRIPT_CONTINUE;
    }
}
