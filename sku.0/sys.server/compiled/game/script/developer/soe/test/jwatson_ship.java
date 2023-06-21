package script.developer.soe.test;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class jwatson_ship extends script.base_script
{
    public jwatson_ship()
    {
    }

    public int OnShipInternalDamageOverTimeRemoved(obj_id self, int chassisSlot, float damageRate, float damageThreshold) throws InterruptedException
    {
        obj_id pilot = getPilotId(self);
        if (pilot != null)
        {
            broadcast(pilot, "jwatson_ship IDOT removed slot=" + chassisSlot + ", damageRate=" + damageRate + ", threshold=" + damageThreshold);
        }
        return SCRIPT_CONTINUE;
    }
}
