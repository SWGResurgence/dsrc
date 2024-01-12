package script.conversation;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

// ======================================================================
// Library Includes
// ======================================================================

import script.*;
import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;

public class ana_fox extends base_script
{
    public static String c_stringFile = "conversation/ana_fox";

// ======================================================================
// Script Constants
// ======================================================================

    public ana_fox()

    {

    }

// ======================================================================
// Script Conditions
// ======================================================================

    public boolean ana_fox_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }

// ----------------------------------------------------------------------

    public boolean ana_fox_condition_readyForReward(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "quest/world_boss_naboo_emperors_hand", "returnAnaFox");
    }
// ----------------------------------------------------------------------

    public boolean ana_fox_condition_isOnQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/world_boss_naboo_emperors_hand");
    }

// ======================================================================
// Script Actions
// ======================================================================

    public void ana_fox_action_sendRewardSignal(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedWorldBossNabooEmperorsHand");
    }

// ----------------------------------------------------------------------

    public void ana_fox_action_grantQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/world_boss_naboo_emperors_hand");
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

    int ana_fox_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Hey, I heard that you helped Ned Dross, is this true?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Yep, the Mandalorian's didn't even proof to be a challenge.
        if (response.equals("s_4"))
        {
            //-- [NOTE]
            if (ana_fox_condition__defaultCondition(player, npc))
            {
                //-- NPC: Good! Your making quite a name for yourself, I hope you're ready for something even more challenging...
                string_id message = new string_id(c_stringFile, "s_5");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Yep, the Mandalorian's didn't even proof to be a challenge. It really depends on if the money is good.
                boolean hasResponse0 = false;
                if (ana_fox_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.ana_fox.branchId", 5);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ana_fox.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ana_fox_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Good! Your making quite a name for yourself, I hope you're ready for something even more challenging...

        //-- [RESPONSE NOTE]
        //-- PLAYER: Yep, the Mandalorian's didn't even proof to be a challenge. It really depends on if the money is good.
        if (response.equals("s_6"))
        {
            //-- [NOTE]
            if (ana_fox_condition__defaultCondition(player, npc))
            {
                //-- NPC: You don't have to worry about that, Czerka Corporation will take care of you, like we always have.
                string_id message = new string_id(c_stringFile, "s_7");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What's the job?
                boolean hasResponse0 = false;
                if (ana_fox_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.ana_fox.branchId", 6);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ana_fox.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ana_fox_handleBranch6(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: You don't have to worry about that, Czerka Corporation will take care of you, like we always have.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What's the job?
        if (response.equals("s_8"))
        {
            //-- [NOTE]
            if (ana_fox_condition__defaultCondition(player, npc))
            {
                //-- NPC: Our Bio-Engineering Division would like to Dissect The Hand of the Emperor. We would like you to bring her in.
                string_id message = new string_id(c_stringFile, "s_9");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: The Hand of the Emperor?  As in Emperor Palpatine?
                boolean hasResponse0 = false;
                if (ana_fox_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.ana_fox.branchId", 7);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ana_fox.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ana_fox_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Our Bio-Engineering Division would like to Dissect The Hand of the Emperor. We would like you to bring her in.

        //-- [RESPONSE NOTE]
        //-- PLAYER: The Hand of the Emperor?  As in Emperor Palpatine?
        if (response.equals("s_10"))
        {
            //-- [NOTE]
            if (ana_fox_condition__defaultCondition(player, npc))
            {
                //-- NPC: The one and the same. Don't tell me your afraid of her?
                string_id message = new string_id(c_stringFile, "s_11");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: ...
                boolean hasResponse0 = false;
                if (ana_fox_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.ana_fox.branchId", 8);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.ana_fox.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int ana_fox_handleBranch8(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: The one and the same. Don't tell me your afraid of her?

        //-- [RESPONSE NOTE]
        //-- PLAYER: ...
        if (response.equals("s_12"))
        {
            //-- [NOTE]
            if (ana_fox_condition__defaultCondition(player, npc))
            {
                ana_fox_action_grantQuest(player, npc);

                //-- NPC: You would be incredibly stupid not to be. That is the job, so get on with it and don't come back without the prize.
                string_id message = new string_id(c_stringFile, "s_13");
                utils.removeScriptVar(player, "conversation.ana_fox.branchId");

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
            detachScript(self, "conversation.ana_fox");
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
        detachScript(self, "conversation.ana_fox");

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
        if (ana_fox_condition_readyForReward(player, npc))
        {
            ana_fox_action_sendRewardSignal(player, npc);

            //-- NPC: I see you're still alive and you have what I asked for. Here's the reward I promised.
            string_id message = new string_id(c_stringFile, "s_14");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (ana_fox_condition_isOnQuest(player, npc))
        {
            //-- NPC: You handled yourself pretty well. I've sent a few spacers that way before you came along and needless to say none have returned. Don't be a stranger, I may have more jobs for you.
            string_id message = new string_id(c_stringFile, "s_16");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (ana_fox_condition__defaultCondition(player, npc))
        {
            //-- NPC: Hey, I heard that you helped Ned Dross?
            string_id message = new string_id(c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Yep, I did.
            boolean hasResponse0 = false;
            if (ana_fox_condition__defaultCondition(player, npc))
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

                utils.setScriptVar(player, "conversation.ana_fox.branchId", 4);

                npcStartConversation(player, npc, "ana_fox", message, responses);
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
        if (!conversationId.equals("ana_fox"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.ana_fox.branchId");

        if (branchId == 4 && ana_fox_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 5 && ana_fox_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 6 && ana_fox_handleBranch6(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 7 && ana_fox_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 8 && ana_fox_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.ana_fox.branchId");

        return SCRIPT_CONTINUE;
    }

// ======================================================================

}