package script.bot;/*
@Filename: script.bot.medic
@Author: BubbaJoeX
@Purpose: Entertainer bot.
*/

import script.*;
import script.library.*;

public class entertainer extends script.base_script
{
    //public static final float buffDuration = 3600f;
    public static final String BUFF_PROMPT = colors_hex.HEADER + colors_hex.AQUAMARINE + "Do you wish to recieve a reduced strength enhancement? " + colors_hex.FOOTER;
    public static final String BUFF_TITLE = "MicroData Technologies SELF-ENTERTAINMENT MODULE";
    public static final String[] buffComponentKeys = {
            "kinetic",
            "energy",
            "action_cost_reduction",
            "dodge",
            "strength",
            "constitution",
            "stamina",
            "precision",
            "agility",
            "luck",
            "critical_hit",
            "healing",
            "healer",
            "reactive_go_with_the_flow",
            "flush_with_success",
            "reactive_second_chance"
    };
    public static final int[] buffComponentValues =
            {
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15,
                    15
            };

    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Watch Performance"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {

        if (item == menu_info_types.SERVER_MENU1)
        {
            int pid = sui.createSUIPage(sui.SUI_MSGBOX, self, player, "handleDialogInput");
            sui.setSUIProperty(pid, sui.MSGBOX_PROMPT, sui.PROP_TEXT, BUFF_PROMPT);
            sui.setSUIProperty(pid, sui.MSGBOX_TITLE, sui.PROP_TEXT, BUFF_TITLE);
            sui.msgboxButtonSetup(pid, sui.OK_ONLY);
            sui.setSUIProperty(pid, sui.MSGBOX_BTN_OK, sui.PROP_TEXT, "Allow Enhancement");
            sui.showSUIPage(pid);
        }
        return SCRIPT_CONTINUE;
    }

    public int handleDialogInput(obj_id self, dictionary params) throws InterruptedException
    {
        if (!isValidId(self))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        if (!isValidId(player))
        {
            return SCRIPT_CONTINUE;
        }
        triggerBuff(self, player);
        return SCRIPT_CONTINUE;
    }

    public int triggerBuff(obj_id self, obj_id target) throws InterruptedException
    {
        float currentBuffTime = performance.inspireGetMaxDuration(target);
        buff.applyBuff(target, "buildabuff_inspiration", currentBuffTime);
        utils.setScriptVar(target, "performance.buildabuff.buffComponentKeys", buffComponentKeys);
        utils.setScriptVar(target, "performance.buildabuff.buffComponentValues", buffComponentValues);
        utils.setScriptVar(target, "performance.buildabuff.player", target);
        return SCRIPT_CONTINUE;
    }
}
