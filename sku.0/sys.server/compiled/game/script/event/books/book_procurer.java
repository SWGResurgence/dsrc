package script.event.books;

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
import script.library.money;
import script.library.utils;
import script.*;

public class book_procurer extends script.base_script
{
    public static String c_stringFile = "conversation/book_procurer";

// ======================================================================
// Script Constants
// ======================================================================

    public book_procurer()

    {

    }

// ======================================================================
// Script Conditions
// ======================================================================

    public boolean book_procurer_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }

// ======================================================================
// Script Actions
// ======================================================================

    public void book_procurer_action_grantBook(obj_id player, obj_id npc) throws InterruptedException
    {
        int credits = getTotalMoney(player);
        if (credits < 50000)
        {
            broadcast(player, "You need " + (50000 - credits) + " more credits to purchase a book.");
            chat.chat(npc, "Are you trying to rob me!?!?!?");
            npcEndConversation(player);
        }
        else
        {
            money.pay(player, npc, 50000, "noHandler", null);
            String[] randomBooks = {
                    "object/tangible/book/black.iff",
                    "object/tangible/book/blue.iff",
                    "object/tangible/book/brown.iff",
                    "object/tangible/book/cerulean.iff",
                    "object/tangible/book/gold.iff",
                    "object/tangible/book/orange.iff",
                    "object/tangible/book/purple.iff",
                    "object/tangible/book/red.iff",
                    "object/tangible/book/teal.iff",
                    "object/tangible/book/green.iff",
            };
            int randomBook = rand(0, randomBooks.length - 1);
            obj_id book = createObject(randomBooks[randomBook], utils.getInventoryContainer(player), "");
            attachScript(book, "systems.bookworm.book");
            setName(book, "a blank book");
            setDescriptionString(book, "This book does not have anything scribed into it. Only the owner of the book may modify it's contents.");
            setCrafter(book, player);
            chat.chat(npc, "Here is your book. I hope you enjoy it.");
            npcEndConversation(player);
        }
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

    int book_procurer_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE] First greeting.
        //-- NPC: Greetings.

        //-- [RESPONSE NOTE] First response
        //-- PLAYER: Hello. I was told you sell old-school books?
        if (response.equals("s_4"))
        {
            doAnimationAction(player, "salute1");

            //-- [NOTE] Explaination of price
            if (book_procurer_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "explain");

                doAnimationAction(player, "nod_head_multiple");

                //-- NPC: I do. However they are a bit spendy! They cost 50,000 credits. You know, the import tax and what not...
                string_id message = new string_id(c_stringFile, "s_8");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Yes please!
                boolean hasResponse0 = false;
                if (book_procurer_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                //-- PLAYER: That seem too expensive for me. No thank you.
                boolean hasResponse1 = false;
                if (book_procurer_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }

                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id[] responses = new string_id[numberOfResponses];

                    if (hasResponse0)
                        responses[responseIndex++] = new string_id(c_stringFile, "s_9");

                    if (hasResponse1)
                        responses[responseIndex++] = new string_id(c_stringFile, "s_10");

                    utils.setScriptVar(player, "conversation.book_procurer.branchId", 2);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.book_procurer.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_DEFAULT;
    }

// ----------------------------------------------------------------------

    int book_procurer_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE] Explaination of price
        //-- NPC: I do. However they are a bit spendy! They cost 50,000 credits. You know, the import tax and what not...

        //-- [RESPONSE NOTE] GRANT BOOK
        //-- PLAYER: Yes please!
        if (response.equals("s_9"))
        {
            doAnimationAction(player, "nod_head_once");

            book_procurer_action_grantBook(player, npc);

            //-- [NOTE] First greeting.
            if (book_procurer_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "wave2");

                //-- NPC: Greetings.
                string_id message = new string_id(c_stringFile, "s_3");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Hello. I was told you sell old-school books?
                boolean hasResponse0 = false;
                if (book_procurer_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.book_procurer.branchId", 1);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.book_procurer.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        //-- [RESPONSE NOTE] Decline and sanitize convo.
        //-- PLAYER: That seem too expensive for me. No thank you.
        if (response.equals("s_10"))
        {
            doAnimationAction(player, "squirm");
            npcEndConversation(player);
            //-- [NOTE] First greeting.
            if (book_procurer_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "wave2");

                //-- NPC: Greetings.
                string_id message = new string_id(c_stringFile, "s_3");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Hello. I was told you sell old-school books?
                boolean hasResponse0 = false;
                if (book_procurer_condition__defaultCondition(player, npc))
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

                    utils.setScriptVar(player, "conversation.book_procurer.branchId", 1);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.book_procurer.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_DEFAULT;
    }

// ----------------------------------------------------------------------

// ======================================================================
// User Script Triggers
// ======================================================================

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.book_procurer");
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
        detachScript(self, "conversation.book_procurer");

        return SCRIPT_CONTINUE;
    }

// ======================================================================
// Script Triggers
// ======================================================================

    //-- This function should move to base_class.java
    boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
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

        //-- [NOTE] First greeting.
        if (book_procurer_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "wave2");

            //-- NPC: Greetings.
            string_id message = new string_id(c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Hello. I was told you sell old-school books?
            boolean hasResponse0 = false;
            if (book_procurer_condition__defaultCondition(player, npc))
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

                utils.setScriptVar(player, "conversation.book_procurer.branchId", 1);

                npcStartConversation(player, npc, "book_procurer", message, responses);
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
        if (!conversationId.equals("book_procurer"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.book_procurer.branchId");

        if (branchId == 1 && book_procurer_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 2 && book_procurer_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.book_procurer.branchId");

        return SCRIPT_CONTINUE;
    }

// ======================================================================

}