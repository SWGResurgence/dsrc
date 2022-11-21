package script.systems.mechanic;

import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;
import script.library.vehicle;

public class toolkit extends script.base_script
{
    public toolkit()
    {
    }
    public static int PAYOUT_AMOUNT = 24000;
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {

        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("sui", "toolkit"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (!vehicle.isRidingVehicle(player))
            {
                broadcast(player,"You must be in your vehicle to apply this tune-up.");
                return SCRIPT_CONTINUE;
            }
            if (vehicle.getMaximumSpeed(vehicle.getMountId(player)) > 256)
            {
                broadcast(player,"Your vehicle cannot possibly accept any more tuning.");
                return SCRIPT_CONTINUE;
            }
            obj_id veh = getMountId(player);
            float currentSpeed = vehicle.getMaximumSpeed(veh);
            float modifier = getFloatObjVar(self, "mechanic.modifier");
            vehicle.setMaximumSpeed(veh, currentSpeed + modifier);
            broadcast(player,"Your vehicle has been tuned up.");
            obj_id cod = getCrafter(self);
            if (cod != player) // if you are the mechanic do not pay yourself.
            {
                depositCashToBank(cod, PAYOUT_AMOUNT, null, null, null);
            }
            else
            {
                sendSystemMessage(player, "You have tuned this vehicle.", null);
            }

        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (hasObjVar(self, "mechanic.toolkit.speed"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Speed";
            idx++;
        }
        return SCRIPT_CONTINUE;
    }
}
