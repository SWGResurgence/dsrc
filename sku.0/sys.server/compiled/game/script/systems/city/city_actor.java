package script.systems.city;

import script.*;
import script.library.ai_lib;
import script.library.city;
import script.library.sui;
import script.library.utils;

import static script.library.ai_lib.setMood;

public class city_actor extends script.base_script
{
    public city_actor()
    {
    }
    public int OnAttach(obj_id self)
    {
        int city_id = getCityAtLocation(getLocation(self), 0);
        setInvulnerable(self, true);
        setName(self, "Bio-logical Display Matrix");
        setDescriptionStringId(self, new string_id("This is a Bio-logical Display Matrix. It is used to display creatures in a city. This creature cannot be attacked, or aggroed."));
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        int city_id = getCityAtLocation(getLocation(self), 0);
        setInvulnerable(self, true);
        setDescriptionStringId(self, new string_id("This is a Bio-logical Display Matrix. It is used to display creatures in a city. This creature cannot be attacked, or aggroed."));
        return SCRIPT_CONTINUE;
    }

    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        int city_id = getCityAtLocation(getLocation(giver), 0);
        boolean isMayor = city.isTheCityMayor(giver, city_id);
        if (canManipulateActor(self, giver))
        {
            if (isIdValid(item) && !getTemplateName(item).contains("city_actor"))
            {
                if (isPlayer(giver))
                {
                    sendSystemMessageTestingOnly(giver, "You gave [" + getName(item) + "] to [" + getName(self) + "].");
                    equipOverride(item, self);
                }
                else
                {
                    sendSystemMessageTestingOnly(giver, "You cannot give this item to that object.");
                    return SCRIPT_OVERRIDE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!utils.hasScriptVar(self, "city_actor_setup"))
        {
            utils.setScriptVar(self, "city_actor_setup", true);
        }
        if (canManipulateActor(self, player))
        {
            if (isInWorldCell(player))
            {
                int hireling_main = mi.addRootMenu(menu_info_types.SERVER_MENU20, new string_id("Actor *"));
                mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU21, new string_id("* Customize: Posture"));
                mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU22, new string_id("* Customize: Mood"));
                mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU23, new string_id("* Customize: Animation"));
                mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU24, new string_id("* Customize: Size"));
                mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU26, new string_id("* Customize: AI"));
                mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU25, new string_id("* Reset All Settings"));
                if (hasObjVar(self, "city_id"))
                {
                    mi.addSubMenu(hireling_main, menu_info_types.SERVER_MENU27, new string_id("* Remove Actor"));
                }

            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (canManipulateActor(self, player))
        {
            if (item == menu_info_types.SERVER_MENU20)
            {
                //@note catch stragglers
                if (!hasObjVar(self, "city_id"))
                {
                    setObjVar(self, "city_id", getCityAtLocation(getLocation(player), 0));
                }
                if (!hasScript(self, "systems.city.city_furniture"))
                {
                    attachScript(self, "systems.city.city_furniture");
                }
            }
            else if (item == menu_info_types.SERVER_MENU21)
            {
                sui.inputbox(self, player, "Enter the posture for the hireling.\n(Example: '0' [which is standing])", "handleSetPosture");                //@call msgbox for posture
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
                sui.inputbox(self, player, "Enter the size you want the hireling to be.\n(Example: 1.0)", "handleSetSize");
            }
            else if (item == menu_info_types.SERVER_MENU25)
            {
                obj_id[] delList = getInventoryAndEquipment(self);
                for (script.obj_id goodItem : delList)
                {
                    putIn(goodItem, utils.getInventoryContainer(player));
                }
                setName(self, "City Actor");
                setPosture(self, POSTURE_UPRIGHT);
                setMood(self, "none");
            }
            else if (item == menu_info_types.SERVER_MENU26)
            {
                if (!hasScript(self, "ai.ai"))
                {
                    attachScript(self, "ai.ai");
                }
                sui.inputbox(self, player, "Enter the A.I. you want this actor to have.\n\n\nOptions: [wander, loiter, none]", "handleSetAI");
            }
            else if (item == menu_info_types.SERVER_MENU27)
            {
                sui.msgbox(self, player, "\\#FFD700Are you sure you want to remove this actor?", sui.YES_NO, "handleRemoveActor");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int handleRemoveActor(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        if (sui.getIntButtonPressed(params) == sui.BP_OK)
        {
            removeObjVar(self, "city_id");
            city.removeDecoration(self);
            destroyObject(self);
        }
        else
        {
            broadcast(player, "Bio-logical Actor removal cancelled.");
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
        if (isGod(player))
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
        if (sizeFloat <= 0.0f)
        {
            setScale(self, 0.5f);
        }
        if (sizeFloat > 25.0f)
        {
            setScale(self, 25.0f);
        }
        else
        {
            setScale(self, sizeFloat);
        }
        setScale(self, sizeFloat);
        return SCRIPT_CONTINUE;
    }
    public int handleSetAi(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String ai_template = sui.getInputBoxText(params);
        if (ai_template.equals("loiter"))
        {
            ai_lib.loiterLocation(self, getLocation(self), 1.0f, 20.0f, 1.0f, 2.0f);
            return SCRIPT_CONTINUE;
        }
        else if (ai_template.equals("wander"))
        {
            ai_lib.wander(self);
            return SCRIPT_CONTINUE;
        }
        else if (ai_template.equals("none"))
        {
            ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_STOP);
            return SCRIPT_CONTINUE;
        }
        else
        {
            broadcast(player, "Invalid AI Template. Valid settings: [wander | loiter | none]");
            return SCRIPT_CONTINUE;
        }

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
