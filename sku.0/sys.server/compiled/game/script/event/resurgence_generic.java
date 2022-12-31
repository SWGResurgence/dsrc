/*
@Purpose Generic Event Script for Resurgence

@Author BubbaJoe

 */
package script.event;

import script.library.anims;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class resurgence_generic extends script.base_script
{


    public static int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Event Listener");
        setObjVar(self, "event_reward_one", "item_event_energy_drink_01_02");
        setObjVar(self, "event_reward_two", "item_event_air_cake_01_02");
        setObjVar(self, "event_trigger", "default");
        return SCRIPT_CONTINUE;
    }

    public static void createReward(obj_id self, obj_id player) throws InterruptedException
    {
        if (isIdValid(player))
        {
            static_item.createNewItemFunction(getStringObjVar(self, "event_reward_one"), utils.getInventoryContainer(player));
            static_item.createNewItemFunction(getStringObjVar(self, "event_reward_two"), utils.getInventoryContainer(player));
            broadcastLocal(self, player, "Thank you for participating in the Resurgence Event!");
        }
    }

    public static void OnHearSpeech(obj_id self, obj_id speaker, String text) throws InterruptedException
    {
        if (text.equals(getStringObjVar(self, "event_trigger")))
        {
            createReward(self, speaker);
        }
        if (text.equalsIgnoreCase("lets dance"))
        {
            static_item.createNewItemFunction("item_event_dance_party_device_03_01", utils.getInventoryContainer(speaker));
        }
        if (text.contains("lets see"))
        {
            doAnimationAction(speaker, anims.PLAYER_DRAW_DATAPAD);
            playClientEffectLoc(speaker, "clienteffect/entertainer_dazzle_level_2.cef", getLocation(speaker), 0.0f);
        }
    }

    public static void broadcastLocal(obj_id self, obj_id recipient, String message)
    {
        sendSystemMessage(recipient, message, null);
    }
}
