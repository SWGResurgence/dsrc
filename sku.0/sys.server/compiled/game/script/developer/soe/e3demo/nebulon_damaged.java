package script.developer.soe.e3demo;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class nebulon_damaged extends script.base_script
{
    public nebulon_damaged()
    {
    }

    public int OnShipWasHit(obj_id self, obj_id objAttacker, int intWeaponIndex, boolean isMissile, int missileType, int intTargetedComponent, boolean fromPlayerAutoTurret, float hitLocationX_o, float hitLocationY_o, float hitLocationZ_o) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
