package script.conversation;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

// ======================================================================
// Library Includes
// ======================================================================

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class ned_dross extends script.base_script
{
    public static String c_stringFile = "conversation/ned_dross";

// ======================================================================
// Script Constants
// ======================================================================

    public ned_dross()

    {

    }

// ======================================================================
// Script Conditions
// ======================================================================

    public boolean ned_dross_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }

// ----------------------------------------------------------------------

    public boolean ned_dross_condition_readyForReward(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "quest/world_boss_dxun_paxvizla", "returnNedDross");
    }
// ----------------------------------------------------------------------

    public boolean ned_dross_condition_isOnQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/world_boss_dxun_paxvizla");
    }

// ======================================================================
// Script Actions
// ======================================================================

    public void ned_dross_action_sendRewardSignal(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedWorldBossDxunPaxvizla");
    }

// ----------------------------------------------------------------------

    public void ned_dross_action_grantQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/world_boss_dxun_paxvizla");
    }

// ======================================================================
// Script %TO Tokens
// ======================================================================

// ======================================================================
// Script %DI Tokens
// ======================================================================

// ======================================================================
// Script %DF Tokens
// ======================================================================

// ======================================================================
// handleBranch<n> Functions 
// ======================================================================

    int ned_dross_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Hey, I heard that you helped Dr. Mistsplitter?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Yep, I did.
        if (response.equals("s_4"))
        {
            //-- [NOTE]
            if (ned_dross_condition__defaultCondition(player, npc))
            {
                //-- NPC: Good! It's good to know that the rumors are true.
                string_id message = new string_id(c_stringFile, "s_5");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: And, what is that supposed to mean?
                boolean hasResponse0 = false;
                if (ned_dross_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_6");

                    utils.setScriptVar(player, "conversation.ned_dross.branchId", 5);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ned_dross.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ned_dross_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Good! It's good to know that the rumors are true.

        //-- [RESPONSE NOTE]
        //-- PLAYER: And, what is that supposed to mean?
        if (response.equals("s_6"))
        {
            //-- [NOTE]
            if (ned_dross_condition__defaultCondition(player, npc))
            {
                //-- NPC: Just that you might be just the person I'm needing to for a special assignment.
                string_id message = new string_id(c_stringFile, "s_7");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What kind of assignment?
                boolean hasResponse0 = false;
                if (ned_dross_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_8");

                    utils.setScriptVar(player, "conversation.ned_dross.branchId", 6);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ned_dross.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ned_dross_handleBranch6(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Just that you might be just the person I'm needing to for a special assignment.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What kind of assignment?
        if (response.equals("s_8"))
        {
            //-- [NOTE]
            if (ned_dross_condition__defaultCondition(player, npc))
            {
                //-- NPC: Be careful what you ask for, Czerka has a tendency to make people disappear for asking too many questions. But, I suppose it's natural to be curious. My I need someone to go hunt down a rogue Mandalorian.
                string_id message = new string_id(c_stringFile, "s_9");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: A Mandalorian, I thought they were extinct. What did this Mandalorian do?
                boolean hasResponse0 = false;
                if (ned_dross_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_10");

                    utils.setScriptVar(player, "conversation.ned_dross.branchId", 7);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ned_dross.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ned_dross_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Be careful what you ask for, Czerka has a tendency to make people disappear for asking too many questions. But, I suppose it's natural to be curious. My I need someone to go hunt down a rogue Mandalorian.

        //-- [RESPONSE NOTE]
        //-- PLAYER: A Mandalorian, I thought they were extinct. What did this Mandalorian do?
        if (response.equals("s_10"))
        {
            //-- [NOTE]
            if (ned_dross_condition__defaultCondition(player, npc))
            {
                //-- NPC: That's classified information. So unless you'd like to find yourself hunted by some of our HK-80 Droids, let's stick to the mission.
                string_id message = new string_id(c_stringFile, "s_11");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Fair enough, you just want me to kill this Mandalorian?
                boolean hasResponse0 = false;
                if (ned_dross_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_12");

                    utils.setScriptVar(player, "conversation.ned_dross.branchId", 8);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ned_dross.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ned_dross_handleBranch8(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: That's classified information. So unless you'd like to find yourself hunted by some of our HK-80 Droids, let's stick to the mission.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Fair enough, you just want me to kill this Mandalorian?
        if (response.equals("s_12"))
        {
            //-- [NOTE]
            if (ned_dross_condition__defaultCondition(player, npc))
            {
                ned_dross_action_grantQuest(player, npc);

                //-- NPC: Yes, just kill him. He may have guards, so you should recruit your own help.
                string_id message = new string_id(c_stringFile, "s_13");
                utils.removeScriptVar(player, "conversation.ned_dross.branchId");

                npcEndConversationWithMessage(player, message);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

// ======================================================================
// User Script Triggers
// ======================================================================

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.ned_dross");
        }

        setCondition(self, CONDITION_CONVERSABLE);

        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);

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
        detachScript(self, "conversation.ned_dross");

        return SCRIPT_CONTINUE;
    }

// ======================================================================
// Script Triggers
// ======================================================================

    //-- This function should move to base_class.java
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

// ----------------------------------------------------------------------

    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;

        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
            return SCRIPT_OVERRIDE;

        //-- [NOTE]
        if (ned_dross_condition_readyForReward(player, npc))
        {
            ned_dross_action_sendRewardSignal(player, npc);

            //-- NPC: I see you're still alive and you have what I asked for. Here's the reward I promised.
            string_id message = new string_id(c_stringFile, "s_14");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (ned_dross_condition_isOnQuest(player, npc))
        {
            //-- NPC: You handled yourself pretty well. I've sent a few spacers that way before you came along and needless to say none have returned. Don't be a stranger, I may have more jobs for you.
            string_id message = new string_id(c_stringFile, "s_16");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (ned_dross_condition__defaultCondition(player, npc))
        {
            //-- NPC: Hey, I heard that you helped Dr. Mistsplitter?
            string_id message = new string_id(c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Yep, I did.
            boolean hasResponse0 = false;
            if (ned_dross_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_4");

                utils.setScriptVar(player, "conversation.ned_dross.branchId", 4);

                npcStartConversation(player, npc, "ned_dross", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("ned_dross"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.ned_dross.branchId");

        if (branchId == 4 && ned_dross_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 5 && ned_dross_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 6 && ned_dross_handleBranch6(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 7 && ned_dross_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 8 && ned_dross_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.ned_dross.branchId");

        return SCRIPT_CONTINUE;
    }

// ======================================================================

}