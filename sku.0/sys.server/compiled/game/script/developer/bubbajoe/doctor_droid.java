package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.doctor_droid
@Author: BubbaJoeX
@Purpose: Medic Summon. Battlefield 2.0 reward.
*/

import script.*;
import script.library.*;

public class doctor_droid extends script.base_script
{
    public int OnInitialize(obj_id self)//persisted load
    {
        createTriggerVolume("healDroid", 3.0f, true);
        setName(self, colors_hex.HEADER + colors_hex.ORANGE + "FX-7 Medical Droid");
        setDescriptionStringId(self, new string_id("This FX-7 medical droid is a prototype model. It is designed to stay stationary and heal all those who are nearby."));
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)//dynamic load
    {
        createTriggerVolume("healDroid", 3.0f, true);
        setName(self, colors_hex.HEADER + colors_hex.ORANGE + "FX-7 Medical Droid");
        setDescriptionStringId(self, new string_id("This FX-7 medical droid is a prototype model. It is designed to stay stationary and heal all those who are nearby."));
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
            broadcast(breacher, "This service droid cannot assist those engaged in combat.");
            return SCRIPT_CONTINUE;
        }
        if (volumeName.equals("healDroid"))
        {
            int maxHealth = getMaxAttrib(breacher, HEALTH);
            int maxAction = getMaxAttrib(breacher, ACTION);
            setHealth(breacher, maxHealth);
            setAction(breacher, maxAction);
            int cooldownTime = 7200;
            if (hasObjVar(breacher, "healDroid.cooldown"))
            {
                int cooldown = getIntObjVar(breacher, "healDroid.cooldown");
                if (getGameTime() < cooldown)
                {
                    broadcast(breacher, "You must wait " + ((cooldown - getGameTime()) / 60) + " minutes before receiving another enhancement of this calliber.");
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
