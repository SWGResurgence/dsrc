package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.doctor_droid
@Author: BubbaJoeX
@Purpose: Medic Summon. Battlefield 2.0 reward.
*/

import script.*;
import script.library.*;

public class doctor_droid extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        createTriggerVolume("healDroid", 3.0f, true);
        setName(self, colors_hex.HEADER + colors_hex.ORCHID + "FX-7 Medical Droid");
        setDescriptionStringId(self, new string_id("This FX-7 medical droid is a prototype model. It is designed to stay stationary and heal all those who are nearby."));
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (!isPlayer(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (combat.isInCombat(breacher))
        {
            chat.chat(self, "<This service droid cannot assist those engaged in combat.>");
            return SCRIPT_CONTINUE;
        }
        if (volumeName.equals("healDroid"))
        {
            int maxHealth = getMaxAttrib(breacher, HEALTH);
            int maxAction = getMaxAttrib(breacher, ACTION);
            setHealth(breacher, maxHealth);
            setAction(breacher, maxAction);
            int cooldownTime = 3600;
            if (hasObjVar(breacher, "healDroid.cooldown"))
            {
                int cooldown = getIntObjVar(breacher, "healDroid.cooldown");
                if (getGameTime() < cooldown)
                {
                    chat.chat(self, "<This service droid indicates his power levels are low, therefore unable to provide medical assistance at this time.>");
                    return SCRIPT_CONTINUE;
                }
            }
            ai_lib.aiStopFollowing(self);
            stop(self);
            faceToBehavior(self, breacher);
            buff.applyBuff(breacher,"me_buff_health_2", 3600, 225);
            buff.applyBuff(breacher,"me_buff_action_3", 3600, 225);
            buff.applyBuff(breacher,"me_buff_strength_3", 3600, 225);
            buff.applyBuff(breacher,"me_buff_agility_3", 3600, 225);
            buff.applyBuff(breacher,"me_buff_precision_3", 3600, 225);
            buff.applyBuff(breacher,"me_buff_melee_gb_1", 3600, 225);
            buff.applyBuff(breacher,"me_buff_ranged_gb_1", 3600, 225);
            chat.chat(self, convertToBinary("You have been healed and recieved low-grade enhancements."));
            setObjVar(breacher, "healDroid.cooldown", getGameTime() + cooldownTime);
        }
        return SCRIPT_CONTINUE;
    }
    public String convertToBinary(String text)
    {
        String binary = "";
        for (int i = 0; i < text.length(); i++)
        {
            binary += Integer.toBinaryString(text.charAt(i)) + " ";
        }
        return binary;
    }
}
