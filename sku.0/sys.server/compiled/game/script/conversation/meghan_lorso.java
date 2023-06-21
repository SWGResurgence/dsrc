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

public class meghan_lorso extends script.base_script
{
    public static String c_stringFile = "conversation/meghan_lorso";

// ======================================================================
// Script Constants
// ======================================================================

    public meghan_lorso()

    {

    }

// ======================================================================
// Script Conditions
// ======================================================================

    public boolean meghan_lorso_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }

// ----------------------------------------------------------------------

    public boolean meghan_lorso_condition_readyForReward(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "quest/goto_camp_czerka", "gotoCampCzerka");
    }
// ----------------------------------------------------------------------

    public boolean meghan_lorso_condition_isOnQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/goto_camp_czerka");
    }

// ======================================================================
// Script Actions
// ======================================================================

    public void meghan_lorso_action_sendRewardSignal(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedGotoCampCzerka");
    }

// ----------------------------------------------------------------------

    public void meghan_lorso_action_grantQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/goto_camp_czerka");
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

    int meghan_lorso_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Hey there! I'm Meghan Lorso, Executive Vice-President of Czerka Corporation in this region.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Lorso huh, any relation to a Jana Lorso?
        if (response.equals("s_4"))
        {
            //-- [NOTE]
            if (meghan_lorso_condition__defaultCondition(player, npc))
            {
                //-- NPC: You knw of a Jana Lorso? Yes. She is an Ancestor of mine. She was Executive Assistant for the Corporation during one of the many civil wars that the Jedi had. It's a good thing that the Emperor did away with all of that. She wasn't exactly liked, thankfully it wasn't a reputation I had to inherit.
                string_id message = new string_id(c_stringFile, "s_5");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Sounds like an unfortunate family tree to deal with at reunions...
                boolean hasResponse0 = false;
                if (meghan_lorso_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.meghan_lorso.branchId", 5);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.meghan_lorso.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int meghan_lorso_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: You knw of a Jana Lorso? Yes. She is an Ancestor of mine. She was Executive Assistant for the Corporation during one of the many civil wars that the Jedi had. It's a good thing that the Emperor did away with all of that. She wasn't exactly liked, thankfully it wasn't a reputation I had to inherit.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Sounds like an unfortunate family tree to deal with at reunions...
        if (response.equals("s_6"))
        {
            //-- [NOTE]
            if (meghan_lorso_condition__defaultCondition(player, npc))
            {
                //-- NPC: Unfortunately, everyone has something to inherit. By chance, did you want to work for Czerka?
                string_id message = new string_id(c_stringFile, "s_7");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Indeed they do. I could always use something new to occupy my time. What kind of work does Czerka need help doing?
                boolean hasResponse0 = false;
                if (meghan_lorso_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.meghan_lorso.branchId", 6);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.meghan_lorso.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int meghan_lorso_handleBranch6(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Unfortunately, everyone has something to inherit. By chance, did you want to work for Czerka?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Indeed they do. I could always use something new to occupy my time. What kind of work does Czerka need help doing?
        if (response.equals("s_8"))
        {
            //-- [NOTE]
            if (meghan_lorso_condition__defaultCondition(player, npc))
            {
                //-- NPC: There is always a need for strong adventure types. We will have have many opportunities within Czerka Corporation, with many more added with each passing day. What do you say? Do you want to work for Czerka Corporation?
                string_id message = new string_id(c_stringFile, "s_9");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Okay, what do you need me to do?
                boolean hasResponse0 = false;
                if (meghan_lorso_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.meghan_lorso.branchId", 7);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.meghan_lorso.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int meghan_lorso_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: There is always a need for strong adventure types. We will have have many opportunities within Czerka Corporation, with many more added with each passing day. What do you say? Do you want to work for Czerka Corporation?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Okay, what do you need me to do?
        if (response.equals("s_10"))
        {
            //-- [NOTE]
            if (meghan_lorso_condition__defaultCondition(player, npc))
            {
                //-- NPC: Here are the Coordinates for Camp Czerka on Rori. You'll find some Czerka Scientists there that will be certain to put your skills and talents to good use.
                string_id message = new string_id(c_stringFile, "s_11");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Fair enough, anything else I need to know?
                boolean hasResponse0 = false;
                if (meghan_lorso_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.meghan_lorso.branchId", 8);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.meghan_lorso.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int meghan_lorso_handleBranch8(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Here are the Coordinates for Camp Czerka on Rori. You'll find some Czerka Scientists there that will be certain to put your skills and talents to good use.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Fair enough, anything else I need to know?
        if (response.equals("s_12"))
        {
            //-- [NOTE]
            if (meghan_lorso_condition__defaultCondition(player, npc))
            {
                meghan_lorso_action_grantQuest(player, npc);

                //-- NPC: I mean sure, there is more I could tell you, but my superiors would then insist on you disappearing. So, best be on your way little one.
                string_id message = new string_id(c_stringFile, "s_13");
                utils.removeScriptVar(player, "conversation.meghan_lorso.branchId");

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
            detachScript(self, "conversation.meghan_lorso");
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
        detachScript(self, "conversation.meghan_lorso");

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
        if (meghan_lorso_condition_readyForReward(player, npc))
        {
            meghan_lorso_action_sendRewardSignal(player, npc);

            //-- NPC: I see that you made it to Camp Czerka. Here's your pay!
            string_id message = new string_id(c_stringFile, "s_14");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (meghan_lorso_condition_isOnQuest(player, npc))
        {
            //-- NPC: I heard that you handled yourself pretty well. I've sent quite a few employees to the camp before you and none have returned, until you. Don't be a stranger, I may have more jobs for you in the future.
            string_id message = new string_id(c_stringFile, "s_16");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (meghan_lorso_condition__defaultCondition(player, npc))
        {
            //-- NPC: Hey there! I'm Meghan Lorso, Executive Vice-President of Czerka Corporation in this region.
            string_id message = new string_id(c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Lorso huh, any relation to a Jana Lorso?
            boolean hasResponse0 = false;
            if (meghan_lorso_condition__defaultCondition(player, npc))
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

                utils.setScriptVar(player, "conversation.meghan_lorso.branchId", 4);

                npcStartConversation(player, npc, "meghan_lorso", message, responses);
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
        if (!conversationId.equals("meghan_lorso"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.meghan_lorso.branchId");

        if (branchId == 4 && meghan_lorso_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 5 && meghan_lorso_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 6 && meghan_lorso_handleBranch6(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 7 && meghan_lorso_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 8 && meghan_lorso_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.meghan_lorso.branchId");

        return SCRIPT_CONTINUE;
    }

// ======================================================================

}