/*
@Filename: script.conversation._convert_no_trade
@Author: BubbaJoeX
@Purpose: This script allows players to talk to an npc to convert their inventory full of no-trade items into tradeable items that have been edited in the code base as such, without retroactive changes.
*/

package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.*;

public class convert_no_trade extends script.base_script
{
    public static String c_stringFile = "conversation/convert_no_trade";

    public convert_no_trade()

    {

    }
    public boolean convert_no_trade_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        if (utils.hasScriptVar(player, "can_convert_no_trade"))
        {
            return true;
        }
        else return getConfigSetting("GameServer", "enableConvertNoTradeConvo").equals("true");
	}

    int convert_no_trade_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: What do you need?

        //-- [RESPONSE NOTE]
        //-- PLAYER: I have some assets I need like to liquidate, but the Empire put embargos on all exports. Can you help me? \#DD1234[Remove No-Trade]\#FF0000
        if (response.equals("s_5"))
        {
            if (convert_no_trade_condition__defaultCondition(player, npc))
            {
                //-- NPC: Let me see what we are working with here. Give me one moment and I can complete your request.
                string_id message = new string_id(c_stringFile, "s_10");
                utils.removeScriptVar(player, "conversation.convert_no_trade.branchId");
                expelFromTriggerVolume(npc, "convert_no_trade", player);
                utils.removeScriptVar(player, "can_convert_no_trade");
                handleNoTradeRemoval(player);
                npcEndConversationWithMessage(player, message);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.convert_no_trade");
        }
        createTriggerVolume("convert_no_trade", 10, true);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setName(self, "a local exporter");
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.convert_no_trade");

        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String name, obj_id who) throws InterruptedException
    {
        if (!name.startsWith("convert_no_trade"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!isPlayer(who))
        {
            return SCRIPT_CONTINUE;
        }
        debugConsoleMsg(who, "You have entered the proximity of a local exporter. Speak to them to convert your no-trade items into tradeable items.");

        if (!utils.hasScriptVar(who, "can_convert_no_trade"))
        {
            int TIME_TO_CHECK = 2592000; // 30 days to be able to convert no-trade items.
            if (getPlayerBirthDate(who) + TIME_TO_CHECK > getCalendarTime())
            {
                broadcast(who, "Your character age is too low to partake in this offer.");
                return SCRIPT_CONTINUE;
            }
            utils.setScriptVar(who, "can_convert_no_trade", 1);
        }
        return SCRIPT_CONTINUE;
    }

    //-- This function should move to base_class.java
    boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;

        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
            return SCRIPT_OVERRIDE;

        if (convert_no_trade_condition__defaultCondition(player, npc))
        {
            //-- NPC: What do you need?
            string_id message = new string_id(c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: I have some assets I would like to liquidate, but the Empire put embargos on all exports Can you help me? [Remove No-Trade]
            boolean hasResponse0 = false;
            if (convert_no_trade_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }

            if (hasResponse)
            {
                int responseIndex = 0;
                string_id[] responses = new string_id[numberOfResponses];

                if (hasResponse0)
                    responses[responseIndex++] = new string_id(c_stringFile, "s_5");

                utils.setScriptVar(player, "conversation.convert_no_trade.branchId", 1);

                npcStartConversation(player, npc, "convert_no_trade", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }
        else if (!convert_no_trade_condition__defaultCondition(player, npc))
        {
            //-- NPC: Move Along.
            string_id message = new string_id(c_stringFile, "s_4");
            chat.chat(npc, player, message);
            broadcast(player, "You have nothing of interest to offer to this person.");
            return SCRIPT_CONTINUE;
        }

        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("convert_no_trade"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.convert_no_trade.branchId");

        if (branchId == 1 && convert_no_trade_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.convert_no_trade.branchId");

        return SCRIPT_CONTINUE;
    }

    public int handleNoTradeRemoval(obj_id player) throws InterruptedException
    {
        //@TODO: Read table for template lists that we want to make retroactive.
        obj_id inventory = utils.getInventoryContainer(player);
        obj_id[] contents = getContents(inventory);
        for (int i = 0; i < contents.length; i++)
        {
            if (hasObjVar(contents[i], "noTrade"))
            {
                removeObjVar(contents[i], "noTrade");
            }
            if (hasScript(contents[i], "item.special.nomove"))
            {
                detachScript(contents[i], "item.special.nomove");
            }
        }
        broadcast(player, "Items flagged as *No Trade* inside your inventory are now be tradeable. Please note: this is temporary and will get reapplied upon initialization.");
        return SCRIPT_CONTINUE;
    }
// ======================================================================

}