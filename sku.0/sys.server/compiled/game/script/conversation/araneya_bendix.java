package script.conversation;

// ======================================================================
// Includes
// ======================================================================

import script.library.*;
import script.*;

public class araneya_bendix extends script.base_script {
    public araneya_bendix() {
    }

// ======================================================================
// Script Constants
// ======================================================================

    public static String c_stringFile = "conversation/ned_dross";

// ======================================================================
// Script Conditions
// ======================================================================

    public boolean araneya_bendix_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException {
        return true;
    }

// ----------------------------------------------------------------------

    public boolean araneya_bendix_condition_readyForReward (obj_id player, obj_id npc) throws InterruptedException {
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/world_boss_endor_gizmo", "returnAraneyaBendix")) {
            return true;
        }
        return false;
    }
// ----------------------------------------------------------------------

    public boolean araneya_bendix_condition_isOnQuest (obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, "quest/world_boss_endor_gizmo"))
        {
            return true;
        }
        return false;
    }

// ======================================================================
// Script Actions
// ======================================================================

    public void araneya_bendix_action_sendRewardSignal (obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedWorldBossEndorGizmo");
    }

// ----------------------------------------------------------------------

    public void araneya_bendix_action_grantQuest (obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/world_boss_endor_gizmo");
        return;
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

    int araneya_bendix_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Hello. I heard that you took care of that Renegade Mandalore.

        //-- [RESPONSE NOTE]
        //-- PLAYER: t was a challenge, but then again, the greatest enjoyment in life is to live dangerously.
        if (response.equals("s_4"))
        {
            //-- [NOTE]
            if (araneya_bendix_condition__defaultCondition (player, npc))
            {
                //-- NPC: Excellent! It's good to know that those reports are accurate.
                string_id message = new string_id (c_stringFile, "s_5");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: And; what is that supposed to mean?
                boolean hasResponse0 = false;
                if (araneya_bendix_condition__defaultCondition (player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses [] = new string_id [numberOfResponses];

                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_6");

                    utils.setScriptVar (player, "conversation.araneya_bendix.branchId", 5);

                    npcSpeak (player, message);
                    npcSetConversationResponses (player, responses);
                }
                else
                {
                    utils.removeScriptVar (player, "conversation.araneya_bendix.branchId");

                    npcEndConversationWithMessage (player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int araneya_bendix_handleBranch5 (obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Good! It's good to know that the rumors are true.

        //-- [RESPONSE NOTE]
        //-- PLAYER: And, what is that supposed to mean?
        if (response.equals("s_6"))
        {
            //-- [NOTE]
            if (araneya_bendix_condition__defaultCondition (player, npc))
            {
                //-- NPC: Excellent! It's good to know that those reports are accurate.
                string_id message = new string_id (c_stringFile, "s_7");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: And; what is that supposed to mean?
                boolean hasResponse0 = false;
                if (araneya_bendix_condition__defaultCondition (player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses [] = new string_id [numberOfResponses];

                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_8");

                    utils.setScriptVar (player, "conversation.araneya_bendix.branchId", 6);

                    npcSpeak (player, message);
                    npcSetConversationResponses (player, responses);
                }
                else
                {
                    utils.removeScriptVar (player, "conversation.araneya_bendix.branchId");

                    npcEndConversationWithMessage (player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int araneya_bendix_handleBranch6 (obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Just that you might be exactly who I need for a special covert assignment.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What kind of assignment and for that matter, who are you?
        if (response.equals("s_8"))
        {
            //-- [NOTE]
            if (araneya_bendix_condition__defaultCondition (player, npc))
            {
                //-- NPC: Be careful what you ask for, Czerka has a tendency to make people disappear just for asking questions. [sighs] - I am Araneya Bendix, An Agent for the Imperial Security Bureau, undercover as a Mercenary for Czerka. I am in need of you to take care of a threat to the Sith Order.
                string_id message = new string_id (c_stringFile, "s_9");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: PLAYER: A Sith? You mean like Darth Vader? If they are the same, why would this Sith be a threat?
                boolean hasResponse0 = false;
                if (araneya_bendix_condition__defaultCondition (player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses [] = new string_id [numberOfResponses];

                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_10");

                    utils.setScriptVar (player, "conversation.araneya_bendix.branchId", 7);

                    npcSpeak (player, message);
                    npcSetConversationResponses (player, responses);
                }
                else
                {
                    utils.removeScriptVar (player, "conversation.araneya_bendix.branchId");

                    npcEndConversationWithMessage (player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int araneya_bendix_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Be careful what you ask for, Czerka has a tendency to make people disappear just for asking questions. [sighs] - I am Araneya Bendix, An Agent for the Imperial Security Bureau, undercover as a Mercenary for Czerka. I am in need of you to take care of a threat to the Sith Order.

        //-- [RESPONSE NOTE]
        //-- PLAYER: A Sith? You mean like Darth Vader? If they are the same, why would this Sith be a threat?
        if (response.equals("s_10"))
        {
            //-- [NOTE]
            if (araneya_bendix_condition__defaultCondition (player, npc))
            {
                //-- NPC: That's classified. Any more questioning about classified information and the ISB just might have to open a file on you! In the meantime, let's just stick to the task that we have for you.
                string_id message = new string_id (c_stringFile, "s_11");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Fair enough; you just want me to eliminate this Sith?
                boolean hasResponse0 = false;
                if (araneya_bendix_condition__defaultCondition (player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses [] = new string_id [numberOfResponses];

                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_12");

                    utils.setScriptVar (player, "conversation.araneya_bendix.branchId", 8);

                    npcSpeak (player, message);
                    npcSetConversationResponses (player, responses);
                }
                else
                {
                    utils.removeScriptVar (player, "conversation.araneya_bendix.branchId");

                    npcEndConversationWithMessage (player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int araneya_bendix_handleBranch8 (obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: That's classified. Any more questioning about classified information and the ISB just might have to open a file on you! In the meantime, let's just stick to the task that we have for you.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Fair enough; you just want me to eliminate this Sith?
        if (response.equals("s_12"))
        {
            //-- [NOTE]
            if (araneya_bendix_condition__defaultCondition (player, npc))
            {
                araneya_bendix_action_grantQuest (player, npc);

                //-- NPC: Yes; just eliminate this Sith Pretender who is calling herself Darth Gizmo, by any means necessary. Like all Sith, she may have followers, you would be advised to recruit your own help.
                string_id message = new string_id (c_stringFile, "s_13");
                utils.removeScriptVar (player, "conversation.araneya_bendix.branchId");

                npcEndConversationWithMessage (player, message);

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
        if ((!isMob (self)) || (isPlayer (self)))
        {
            detachScript(self, "conversation.araneya_bendix");
        }

        setCondition (self, CONDITION_CONVERSABLE);

        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition (self, CONDITION_CONVERSABLE);

        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu (menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById (menu);
        menuInfoData.setServerNotify (false);
        setCondition (self, CONDITION_CONVERSABLE);

        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition (self, CONDITION_CONVERSABLE);
        detachScript (self, "conversation.araneya_bendix");

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

        if (ai_lib.isInCombat (npc) || ai_lib.isInCombat (player))
            return SCRIPT_OVERRIDE;

        //-- [NOTE]
        if (araneya_bendix_condition_readyForReward (player, npc))
        {
            araneya_bendix_action_sendRewardSignal (player, npc);

            //-- NPC: I see you survived! I have reported your success to Darth Vader and he has authorized the following reward. Long live the Empire.
            string_id message = new string_id (c_stringFile, "s_14");
            chat.chat (npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (araneya_bendix_condition_isOnQuest (player, npc))
        {
            //-- NPC: You handled yourself very well. Make sure you stop by because the ISB will have additional tasks for you, from time to time.
            string_id message = new string_id (c_stringFile, "s_16");
            chat.chat (npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (araneya_bendix_condition__defaultCondition (player, npc))
        {
            //-- NPC: Hello. I heard that you took care of that Renegade Mandalore.
            string_id message = new string_id (c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: It was a challenge, but then again, the greatest enjoyment in life is to live dangerously.
            boolean hasResponse0 = false;
            if (araneya_bendix_condition__defaultCondition (player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }

            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses [] = new string_id [numberOfResponses];

                if (hasResponse0)
                    responses [responseIndex++] = new string_id (c_stringFile, "s_4");

                utils.setScriptVar (player, "conversation.araneya_bendix.branchId", 4);

                npcStartConversation (player, npc, "araneya_bendix", message, responses);
            }
            else
            {
                chat.chat (npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        chat.chat (npc, "Error:  All conditions for OnStartNpcConversation were false.");

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("araneya_bendix"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar (player, "conversation.araneya_bendix.branchId");

        if (branchId == 4 && araneya_bendix_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 5 && araneya_bendix_handleBranch5 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 6 && araneya_bendix_handleBranch6 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 7 && araneya_bendix_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 8 && araneya_bendix_handleBranch8 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar (player, "conversation.araneya_bendix.branchId");

        return SCRIPT_CONTINUE;
    }

// ======================================================================

}