package script.developer;

import script.*;
import script.library.chat;
import script.library.city;
import script.library.sui;
import script.library.utils;

import static script.library.ai_lib.setMood;

public class wear extends script.base_script
{
    public wear()
    {
    }
    public int OnAttach(obj_id self)
    {
        setInvulnerable(self, true);
        setName(self, "City Actor Hireling");
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        int city_id = getCityAtLocation(getLocation(giver), 0);
        boolean isMayor = city.isTheCityMayor(giver, city_id);
        if (canManipulateActor(self, giver))
        {
            if (isIdValid(item))
            {
                if (isPlayer(giver))
                {
                    sendSystemMessageTestingOnly(giver, "You gave " + getName(self) + " " + getName(item) + ".");
                    equipOverride(item, self);
                }
                else
                {
                    sendSystemMessageTestingOnly(giver, "You cannot give an item to a non-creature object.");
                    return SCRIPT_OVERRIDE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (canManipulateActor(self, player))
        {
            int hireling_main = mi.addRootMenu(menu_info_types.SERVER_MENU19, new string_id("city/city", "hireling_menu"));
            mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU20, new string_id("city/city", "hireling_set_name"));
            mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU21, new string_id("city/city", "hireling_set_posture"));
            mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU22, new string_id("city/city", "hireling_set_mood"));
            mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU23, new string_id("city/city", "hireling_set_animation"));
            mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU24, new string_id("city/city", "hireling_set_size"));
            mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU25, new string_id("city/city", "hireling_reset_clothing"));

        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (canManipulateActor(self, player))
        {
            if (item == menu_info_types.SERVER_MENU19)
            {
                if (!hasObjVar(self, "city_id"))
                {
                    setObjVar(self, "city_id", getCityAtLocation(getLocation(player), 0));
                }
                if (!hasScript(self, "systems.city.city_furniture"))
                {
                    attachScript(self, "systems.city.city_furniture");
                }
            }
            else if (item == menu_info_types.SERVER_MENU20)
            {
                sui.inputbox(self, player, "Enter the name for this hireling.\n(Example: Arispe Moonbeam)", "handleSetName");
                //@call msgbox for name
            }
            else if (item == menu_info_types.SERVER_MENU21)
            {
                sui.inputbox(self, player, "Enter the posture for the hireling.\n(Example, standing: 0)", "handleSetPosture");                //@call msgbox for posture
            }
            else if (item == menu_info_types.SERVER_MENU22)
            {
                sui.inputbox(self, player, "Enter the mood for the hireling.\n(Example: npc_sitting_chair)", "handleSetMood");
            }
            else if (item == menu_info_types.SERVER_MENU23)
            {
                sui.inputbox(self, player, "Enter the animation for the hireling.\n(Example: celebrate", "handleSetAnimation");


            }
            else if (item == menu_info_types.SERVER_MENU24)
            {
                sui.inputbox(self, player, "Enter the size you want the hireling to be.\n(Example: 1.0f", "handleSetSize");
            }
            else if (item == menu_info_types.SERVER_MENU25)
            {
                obj_id[] delList = getInventoryAndEquipment(self);
                for (script.obj_id obj_id : delList)
                {
                    destroyObject(obj_id);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public boolean canManipulateActor(obj_id self, obj_id player) throws InterruptedException
    {
        //@note: keep these in order of importance, with the most important last
        int city_id = getCityAtLocation(getLocation(player), 0);
        boolean isMayor = city.isTheCityMayor(player, city_id);
        if (hasObjVar(player, "city_decorator"))
        {
            return true;
        }
        if (city.isMilitiaOfCity(player, city_id))
        {
            return true;
        }
        if (isMayor)
        {
            return true;
        }
        if (isGod)
        {
            return true;
        }
        else return false;
    }
    public int handleSetName(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String name = sui.getInputBoxText(params);
        setName(self, name);
        return SCRIPT_CONTINUE;
    }
    public int handleSetSize(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String size = sui.getInputBoxText(params);
        float sizeFloat = utils.stringToFloat(size);
        setScale(self, sizeFloat);
        return SCRIPT_CONTINUE;
    }
    public int handleSetAnimation(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String anim = sui.getInputBoxText(params);
        doAnimationAction(self, anim);
        return SCRIPT_CONTINUE;
    }
    public int handleSetMood(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String mood = sui.getInputBoxText(params);
        setAnimationMood(self, mood);
        return SCRIPT_CONTINUE;
    }
    public int handleSetPosture(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String posture = sui.getInputBoxText(params);
        setPosture(self, utils.stringToInt(posture));
        return SCRIPT_CONTINUE;
    }
}
