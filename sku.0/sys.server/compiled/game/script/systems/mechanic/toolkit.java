package script.systems.mechanic;

import script.*;
import script.library.sui;
import script.library.utils;
import script.library.vehicle;

public class toolkit extends script.base_script
{
    public toolkit()
    {
    }
    public static int PAYOUT_AMOUNT = 24000;
    public static String[] TOOLKIT_TYPES =
    {
        "speed", "height", "acceleration", "turning", "banking", "deceleration", "damping_height", "damping_pitch", "damping_roll", "damping_yaw",
    };
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {

        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("sui", "toolkit"));
        if (isGod(player))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU2, new string_id("sui", "toolkit_god"));
            mi.addSubMenu(menu_info_types.SERVER_MENU2, menu_info_types.SERVER_MENU3, new string_id("sui", "toolkit_god_set_type"));
            mi.addSubMenu(menu_info_types.SERVER_MENU2, menu_info_types.SERVER_MENU4, new string_id("sui", "toolkit_god_set_power"));
        }
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
            if (!isNearGarage(player))
            {
                broadcast(player,"You must near a garage bay to apply this tune-up.");
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
            vehicle.setMaximumSpeed(veh, currentSpeed + modifier); //@TODO add in other vehicle stats
            broadcast(player,"Your vehicle has been tuned up.");
            listAndSaveAllModifiers(self, player);
            obj_id cod = getCrafter(self);
            if (cod != player) // if you are the mechanic do not pay yourself.
            {
                depositCashToBank(cod, PAYOUT_AMOUNT, null, null, null);
            }
            else
            {
                sendSystemMessage(player, "You have tuned this vehicle.", null);
            }
            if (getCount(self) > 1) // if there are more than one of these in the inventory, remove one.
            {
                setCount(self, getCount(self) - 1); //Scuffed? I don't know, but it works.
            }
            else
            {
                destroyObject(self); //if no count, destroy the object.
            }

        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            if (!isGod(player))
            {
                sui.listbox(self, player, "Select an option", sui.OK_CANCEL, "God Menu", TOOLKIT_TYPES, "handleToolkitTypeSelect");
                return SCRIPT_CONTINUE;
            }
            else
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (hasObjVar(self, "mechanic.modifier"))
        {
            float modifier = getFloatObjVar(self, "mechanic.modifier");
            names[idx] = "mechanic.modifier";
            attribs[idx] = Float.toString(modifier);
            idx++;
        }
        if (hasObjVar(self, "mechanic.toolkit.speed"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Speed";
            idx++;
        }
        if (hasObjVar(self, "mechanic.toolkit.height"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Height";
            idx++;
        }
        if (hasObjVar(self, "mechanic.toolkit.acceleration"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Acceleration";
            idx++;
        }
        if (hasObjVar(self, "mechanic.toolkit.banking"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Banking";
            idx++;
        }
        if (hasObjVar(self, "mechanic.toolkit.turning"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Turning";
            idx++;
        }
        if (hasObjVar(self, "mechanic.toolkit.deceleration"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Deceleration";
            idx++;
        }
         if (hasObjVar(self, "mechanic.toolkit.damping_height"))
        {
            names[idx] = "toolkit_type";
            attribs[idx] = "Increase Vehicular Damping Height";
            idx++;
        }
        return SCRIPT_CONTINUE;
    }
    public boolean isNearGarage(obj_id player) throws InterruptedException
    {
        obj_id[] objects = getObjectsInRange(getLocation(player), 12);
        for (int i = 0; i < objects.length; i++)
        {
            if (getTemplateName(objects[i]).contains("object/building/player/city/garage_"))
            {
                return true;
            }
        }
        return false;
    }
    public void setToolKitType(obj_id self, String type) throws InterruptedException
    {
        setObjVar(self, "mechanic.toolkit." + type, true);
    }
    public void setToolkitPower(obj_id self, float power) throws InterruptedException
    {
        setObjVar(self, "mechanic.modifier", power);
    }
    public boolean isMunicipal(location loc) throws InterruptedException
    {
        obj_id[] objects = getObjectsInRange(loc, 115);
        for (int i = 0; i < objects.length; i++)
        {
            if (getTemplateName(objects[i]).contains("object/building/player/city/"))
            {
                return true;
            }
        }
        return false;
    }
    public void listAndSaveAllModifiers(obj_id self, obj_id player) throws InterruptedException
    {
        if (!vehicle.isRidingVehicle(player)){
            broadcast(player,"You must be in your vehicle to save your diagnostics.");
        }
        obj_id veh_id = getMountId(player);
        float minspeed = vehicle.getMinimumSpeed(veh_id);
        float maxspeed = vehicle.getMaximumSpeed(veh_id);
        float height = vehicle.getHoverHeight(veh_id);
        float acceleration = vehicle.getAccelMin(veh_id);
        float accelerationmax = vehicle.getAccelMax(veh_id);
        float banking = vehicle.getBankingAngle(veh_id);
        float turning = vehicle.getTurnRateMin(veh_id);
        float turning_max = vehicle.getTurnRateMax(veh_id);
        float deceleration = vehicle.getDecel(veh_id);
        float glide = vehicle.getGlide(veh_id);
        float autolevel = vehicle.getAutoLevelling(veh_id);
        float dampingheight = vehicle.getDampingHeight(veh_id);
        float dampingpitch = vehicle.getDampingPitch(veh_id);
        float dampingroll = vehicle.getDampingRoll(veh_id);
        boolean strafe = vehicle.getStrafe(veh_id);
        broadcast(player,"Minimum Speed: " + minspeed);
        broadcast(player,"Maximum Speed: " + maxspeed);
        broadcast(player,"Hover Height: " + height);
        broadcast(player,"Acceleration: " + acceleration);
        broadcast(player,"Acceleration Max: " + accelerationmax);
        broadcast(player,"Banking Angle: " + banking);
        broadcast(player,"Turning Rate: " + turning);
        broadcast(player,"Turning Rate Max: " + turning_max);
        broadcast(player,"Deceleration: " + deceleration);
        broadcast(player,"Glide: " + glide);
        broadcast(player,"Auto-Levelling: " + autolevel);
        broadcast(player,"Damping Height (falling speed): " + dampingheight);
        broadcast(player,"Damping Pitch: " + dampingpitch);
        broadcast(player,"Damping Roll: " + dampingroll);
        broadcast(player,"Strafe: " + strafe);
        setObjVar(player, "mechanic.modifer.speed_min", minspeed);
        setObjVar(player, "mechanic.modifer.speed_max", maxspeed);
        setObjVar(player, "mechanic.modifer.height", height);
        setObjVar(player, "mechanic.modifer.acceleration", acceleration);
        setObjVar(player, "mechanic.modifer.acceleration_max", accelerationmax);
        setObjVar(player, "mechanic.modifer.banking", banking);
        setObjVar(player, "mechanic.modifer.turning", turning);
        setObjVar(player, "mechanic.modifer.turning_max", turning_max);
        setObjVar(player, "mechanic.modifer.deceleration", deceleration);
        setObjVar(player, "mechanic.modifer.glide", glide);
        setObjVar(player, "mechanic.modifer.autolevelling", autolevel);
        setObjVar(player, "mechanic.modifer.damping_height", dampingheight);
        setObjVar(player, "mechanic.modifer.damping_pitch", dampingpitch);
        setObjVar(player, "mechanic.modifer.damping_roll", dampingroll);
        setObjVar(player, "mechanic.modifer.strafe", strafe);
        setObjVar(player, "mechanic.targetVehicle", veh_id);
        broadcast(player,"All modifiers saved.");

    }
    public void handleToolkitTypeSelect(obj_id self, obj_id player, dictionary webster) throws InterruptedException//@TODO: order these with the string array and implement power change sui
    {
        obj_id pOwner = sui.getPlayerId(webster);
        int bp = sui.getIntButtonPressed(webster);
        if (bp == sui.BP_CANCEL)
        {
            return;
        }
        int idx = sui.getListboxSelectedRow(webster);
        switch (idx)
        {
            case 0:
            setToolKitType(self, "speed");
            //setToolkitPower(self, 1.5f);
            break;
            case 1:
            setToolKitType(self, "height");
            //setToolkitPower(self, 1.5f);
            break;
            case 2:
            setToolKitType(self, "acceleration");
            break;
            case 3:
            setToolKitType(self, "acceleration_max");
            break;
            case 4:
            setToolKitType(self, "banking");
            break;
            case 5:
            setToolKitType(self, "turning");
            break;
            case 6:
            setToolKitType(self, "turning_max");
            break;
            case 7:
            setToolKitType(self, "deceleration");
            break;
            case 8:
            setToolKitType(self, "glide");
            break;
            case 9:
            setToolKitType(self, "autolevelling");
            break;
            case 10:
            setToolKitType(self, "damping_height");
            break;
            case 11:
            setToolKitType(self, "damping_pitch");
            break;
            case 12:
            setToolKitType(self, "damping_roll");
            break;
            case 13:
            setToolKitType(self, "strafe");
            break;
        }
    }
}
