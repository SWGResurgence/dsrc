package script.space.crafting;

import script.library.space_transition;
import script.library.utils;
import script.location;
import script.obj_id;

import java.util.ArrayList;
import java.util.List;

public class alarm extends script.base_script
{
    public int OnAttach(obj_id self) throws InterruptedException
    {
        LOG("space", "ONATTACH GOING OFF ON INTERIOR COMPONETNES");
        setInvulnerable(self, true);
        obj_id objShip = space_transition.getContainingShip(self);
        LOG("space", "I AM INSIDE " + objShip);
        List objAlarms = new ArrayList<obj_id>();
        if (utils.hasScriptVar(objShip, "objAlarms"))
        {
            objAlarms = utils.getResizeableObjIdArrayScriptVar(objShip, "objAlarms");
        }
        objAlarms.add(self);
        utils.setScriptVar(objShip, "objAlarms", objAlarms);
        location locTest = getLocation(self);
        if (hasObjVar(objShip, "intAlarmsOn"))
        {
            setInvulnerableHitpoints(self, 1);
        }
        return SCRIPT_CONTINUE;
    }
}
