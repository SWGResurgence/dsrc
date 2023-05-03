package script.systems.mechanic;

import script.*;
import script.library.callable;
import script.library.sui;
import script.library.utils;
import script.library.vehicle;

import static script.library.vehicle.s_varInfoConversions;
import static script.library.vehicle.s_varInfoNames;

public class toolkit extends script.base_script
{
    public static int PAYOUT_AMOUNT = 24000;
    public static int VEH_MAX_SPEED = 44;
    public static String MECHANIC_VAR = "vehicle_mechanic";
    public static String[] TOOLKIT_TYPES =
            {
                    "speed", "height", "acceleration", "banking", "turning", "deceleration", "damping_height"
            };

    public toolkit()
    {
    }

    public static int setValue(obj_id vehicle, float value, int var_index) throws InterruptedException
    {
        String vi_name = s_varInfoNames[var_index];
        float vi_conversion = s_varInfoConversions[var_index];
        int ivalue = (int) (value * vi_conversion);
        setRangedIntCustomVarValue(vehicle, vi_name, ivalue);
        return ivalue;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "mechanic.modifier", 2.0f);
        setName(self, "Mechanic Toolkit");
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {

        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("sui", "toolkit"));
        if (isGod(player))
        {
            int mama = mi.addRootMenu(menu_info_types.SERVER_MENU2, new string_id("sui", "toolkit_god"));
            mi.addSubMenu(mama, menu_info_types.SERVER_MENU3, new string_id("sui", "toolkit_god_set_type"));
            mi.addSubMenu(mama, menu_info_types.SERVER_MENU4, new string_id("sui", "toolkit_god_set_power"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (!vehicle.isRidingVehicle(player))
            {
                broadcast(player, "You must be in your vehicle to apply this tune-up.");
                return SCRIPT_CONTINUE;
            }
            if (!isNearGarage(player))
            {
                broadcast(player, "You must near a garage bay to apply this tune-up.");
                return SCRIPT_CONTINUE;
            }
            if (vehicle.getMaximumSpeed(vehicle.getMountId(player)) > VEH_MAX_SPEED)
            {
                broadcast(player, "Your vehicle cannot possibly accept any more tuning.");
                return SCRIPT_CONTINUE;
            }
            obj_id veh = getMountId(player);
            float currentValue;
            switch (getTookitType(self))
            {
                case 1:
                    currentValue = vehicle.getMaximumSpeed(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_SPEED_MAX);
                    broadcast(player, "You have increased your vehicle's maximum speed by " + getTookitPower(self));
                    break;
                case 13:
                    currentValue = vehicle.getHoverHeight(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_HOVER_HEIGHT);
                    broadcast(player, "You have increased your vehicle's maximum hover height by " + getTookitPower(self));
                    break;
                case 5:
                    currentValue = vehicle.getAccelMax(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_ACCEL_MAX);
                    broadcast(player, "You have increased your vehicle's acceleration by " + getTookitPower(self));
                    break;
                case 12:
                    currentValue = vehicle.getBankingAngle(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_BANKING);
                    broadcast(player, "You have increased your vehicle's banking by " + getTookitPower(self));
                    break;
                case 4:
                    currentValue = vehicle.getTurnRateMax(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_TURN_RATE_MAX);
                    broadcast(player, "You have increased your vehicle's turning by " + getTookitPower(self));
                    break;
                case 6:
                    currentValue = vehicle.getDecel(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_DECEL);
                    broadcast(player, "You have increased your vehicle's deceleration by " + getTookitPower(self));
                    break;
                case 10:
                    currentValue = vehicle.getDampingHeight(veh);
                    currentValue += getTookitPower(self);
                    setValue(veh, currentValue, vehicle.VAR_DAMP_HEIGHT);
                    broadcast(player, "You have increased your vehicle's damping height by " + getTookitPower(self));
                    break;
                default:
                    broadcast(player, "This toolkit seems to be malfunctioning.");
                    break;
            }
            listAndSaveAllModifiers(self, player);
            obj_id cod = getCrafter(self);
            if (getCount(self) > 1)
            {
                setCount(self, getCount(self) - 1);
            }
            else
            {
                destroyObject(self);
            }
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            if (!isGod(player))
            {
                return SCRIPT_CONTINUE;
            }
            listAndSaveAllModifiers(self, player);
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            if (isGod(player))
            {
                sui.listbox(self, player, "Select tool type.", sui.OK_CANCEL, "[GM] Mechanic", TOOLKIT_TYPES, "handleToolkitTypeSelect");
                return SCRIPT_CONTINUE;
            }
            else
            {
                broadcast(player, "You must be a god to use this function.");
                return SCRIPT_CONTINUE;
            }
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            if (isGod(player))
            {
                sui.inputbox(self, player, "Input tool modifer (float).", sui.OK_CANCEL, "[GM] Mechanic", sui.INPUT_NORMAL, null, "handleToolkitPowerSelect", null);
                return SCRIPT_CONTINUE;
            }
            else
            {
                broadcast(player, "You must be a god to use this function.");
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public float getTookitPower(obj_id self)
    {
        return getFloatObjVar(self, "mechanic.modifier");
    }

    public int getTookitType(obj_id self)
    {
        if (hasObjVar(self, "mechanic.toolkit.speed"))
        {
            return vehicle.VAR_SPEED_MAX;
        }
        if (hasObjVar(self, "mechanic.toolkit.height"))
        {
            return vehicle.VAR_HOVER_HEIGHT;
        }
        if (hasObjVar(self, "mechanic.toolkit.acceleration"))
        {
            return vehicle.VAR_ACCEL_MAX;
        }
        if (hasObjVar(self, "mechanic.toolkit.banking"))
        {
            return vehicle.VAR_BANKING;
        }
        if (hasObjVar(self, "mechanic.toolkit.turning"))
        {
            return vehicle.VAR_TURN_RATE_MIN;
        }
        if (hasObjVar(self, "mechanic.toolkit.deceleration"))
        {
            return vehicle.VAR_DECEL;
        }
        if (hasObjVar(self, "mechanic.toolkit.damping_height"))
        {
            return vehicle.VAR_DAMP_HEIGHT;
        }
        else
        {
            return 1; //default to speed_min
        }
    }

    public boolean isNearGarage(obj_id player) throws InterruptedException
    {
        obj_id[] objects = getObjectsInRange(getLocation(player), 12);
        for (obj_id object : objects)
        {
            if (getTemplateName(object).contains("object/building/player/player_garage"))
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

    public void setToolkitPower(obj_id self, float power)
    {
        setObjVar(self, "mechanic.modifier", power);
    }

    public boolean isMunicipal(location loc) throws InterruptedException
    {
        obj_id[] objects = getObjectsInRange(loc, 115);
        for (obj_id object : objects)
        {
            if (getTemplateName(object).contains("object/building/player/city/"))
            {
                return true;
            }
        }
        return false;
    }

    public void listAndSaveAllModifiers(obj_id self, obj_id player) throws InterruptedException
    {
        String vehicleType = getShortenTemplateName(self, getMountId(player));
        if (!vehicle.isRidingVehicle(player))
        {
            broadcast(player, "You must be in your vehicle to save your diagnostics.");
        }
        obj_id veh_id = getMountId(player);
        obj_id vehicleControlDevice = callable.getCallableCD(self);
        float minspeed = vehicle.getMinimumSpeed(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".minspeed", minspeed);
        float maxspeed = vehicle.getMaximumSpeed(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".maxspeed", maxspeed);
        float height = vehicle.getHoverHeight(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".height", height);
        float acceleration = vehicle.getAccelMin(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".acceleration", acceleration);
        float accelerationmax = vehicle.getAccelMax(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".accelerationmax", accelerationmax);
        float banking = vehicle.getBankingAngle(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".banking", banking);
        float turning = vehicle.getTurnRateMin(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".turning", turning);
        float turning_max = vehicle.getTurnRateMax(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".turning_max", turning_max);
        float deceleration = vehicle.getDecel(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".deceleration", deceleration);
        float glide = vehicle.getGlide(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".glide", glide);
        float autolevel = vehicle.getAutoLevelling(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".autolevel", autolevel);
        float dampingheight = vehicle.getDampingHeight(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".dampingheight", dampingheight);
        float dampingpitch = vehicle.getDampingPitch(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".dampingpitch", dampingpitch);
        float dampingroll = vehicle.getDampingRoll(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".dampingroll", dampingroll);
        boolean strafe = vehicle.getStrafe(veh_id);
        setObjVar(vehicleControlDevice, MECHANIC_VAR + "." + vehicleType + ".strafe", strafe);
        broadcast(player, "Vehicle diagnostics saved.");

    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
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
            attribs[idx] = "Increase Vehicular Height Neogotiation";
            idx++;
        }
        if (hasObjVar(self, "mechanic.modifier"))
        {
            float modifier = getFloatObjVar(self, "mechanic.modifier");
            names[idx] = "toolkit_power";
            attribs[idx] = Float.toString(modifier);
            idx++;
        }
        return SCRIPT_CONTINUE;
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
                setToolkitPower(self, 1.5f);
                break;
            case 1:
                setToolKitType(self, "height");
                setToolkitPower(self, 1.5f);
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

    public void handleToolkitPowerSelect(obj_id self, obj_id player, dictionary webster) throws InterruptedException//@TODO: order these with the string array and implement power change sui
    {
        obj_id pOwner = sui.getPlayerId(webster);
        int bp = sui.getIntButtonPressed(webster);
        if (bp == sui.BP_CANCEL)
        {
            return;
        }
        String inputText = sui.getInputBoxText(webster);
        if (inputText == null || inputText.equals(""))
        {
            return;
        }
        float power = Float.parseFloat(inputText);
        setToolkitPower(self, power);
    }

    public boolean isNumbers(String str) throws InterruptedException
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public String getShortenTemplateName(obj_id self, obj_id vehicle)
    {
        String template = getTemplateName(vehicle);
        template = template.replaceAll("object/mobile/vehicle/shared_", "");
        template = template.replaceAll(".iff", "");
        return template;
    }
}
