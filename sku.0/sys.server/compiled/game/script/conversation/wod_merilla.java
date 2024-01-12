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

public class wod_merilla extends script.base_script
{
    public static String c_stringFile = "conversation/wod_merilla";

// ======================================================================
// Script Constants
// ======================================================================

    public wod_merilla()

    {

    }

// ======================================================================
// Script Conditions
// ======================================================================

    public boolean wod_merilla_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_finishedYirraQuestOne(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "quest/wod_trail_of_discovery");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_isOnQuestFive(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/wod_distorted_visions");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_readyForRewardFive(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "quest/wod_distorted_visions", "returnMerillaOne");

    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_finishedQuestFive(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "quest/wod_distorted_visions");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_finishedQuestSix(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "quest/wod_silencing_the_spellweavers_one");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_readyForRewardSix(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "quest/wod_silencing_the_spellweavers_one", "returnMerillaTwo");

    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_isOnQuestSix(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/wod_seeking_the_spellweavers_one") || groundquests.isQuestActive(player, "quest/wod_silencing_the_spellweavers_one");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_finishedQuestSeven(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "quest/wod_silencing_the_spellweavers_two");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_isOnQuestSeven(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/wod_seeking_the_spellweavers_two");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_finishedQuestSixDathPrison(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "quest/wod_silencing_the_spellweavers_one") && groundquests.hasCompletedQuest(player, "quest/wod_scouting_for_salvage");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_isOnQuestSevenB(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "quest/wod_silencing_the_spellweavers_two");
    }

// ----------------------------------------------------------------------

    public boolean wod_merilla_condition_readyForRewardSeven(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "quest/wod_silencing_the_spellweavers_two", "returnMerillaThree");

    }

// ======================================================================
// Script Actions
// ======================================================================

    public void wod_merilla_action_grantQuestFive(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/wod_distorted_visions");
    }

// ----------------------------------------------------------------------

    public void wod_merilla_action_sendRewardSignalFive(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedDistortedVisions");
    }

// ----------------------------------------------------------------------

    public void wod_merilla_action_grantQuestSix(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/wod_seeking_the_spellweavers_one");
    }

// ----------------------------------------------------------------------

    public void wod_merilla_action_sendRewardSignalSix(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedSilencingtheSpellWeaversOne");
    }

// ----------------------------------------------------------------------

    public void wod_merilla_action_grantQuestSeven(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/wod_seeking_the_spellweavers_two");
    }

// ----------------------------------------------------------------------

    public void wod_merilla_action_grantQuestSevenB(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "quest/wod_silencing_the_spellweavers_two");
    }

// ----------------------------------------------------------------------

    public void wod_merilla_action_sendRewardSignalSeven(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "completedSilencingtheSpellWeaversTwo");
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

    int wod_merilla_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I sense that Spell Weaver has met a swift end. I have misjudged you, %TU.

        //-- [RESPONSE NOTE]
        //-- PLAYER: This one was a bit more difficult. She had a lot more protectors. But, now her and the crystal are no more.
        if (response.equals("s_120"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: Interesting, while you were dealing with her, I was able to find out more about the first Spell Weaver.
                string_id message = new string_id(c_stringFile, "s_121");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What did you learn?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_122");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 3);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Interesting, while you were dealing with her, I was able to find out more about the first Spell Weaver.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What did you learn?
        if (response.equals("s_122"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I learned she was named Valdrianna and came from a prominent line of witches, she was once part of the Singing Mountain, before joining the Nightsisters.
                string_id message = new string_id(c_stringFile, "s_123");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Anything else? What about the crystals?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_124");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 4);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I learned she was named Valdrianna and came from a prominent line of witches, she was once part of the Singing Mountain, before joining the Nightsisters.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Anything else? What about the crystals?
        if (response.equals("s_124"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I was getting to that, %TU. As I was investigating further I was able to confirm that this has been sanctioned by the Nightsisters, perhaps even by Gethzerion herself. I am still trying to figure out the crystals origins, but they are definitely very powerful, as we previously thought.
                string_id message = new string_id(c_stringFile, "s_125");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Interesting, I suspect once the final Spell Weaver is dealt with there will be some more answers?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_126");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 5);

                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);

                    npcSpeak(player, pp);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);

                    npcEndConversationWithMessage(player, pp);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I was getting to that, %TU. As I was investigating further I was able to confirm that this has been sanctioned by the Nightsisters, perhaps even by Gethzerion herself. I am still trying to figure out the crystals origins, but they are definitely very powerful, as we previously thought.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Interesting, I suspect once the final Spell Weaver is dealt with there will be some more answers?
        if (response.equals("s_126"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_sendRewardSignalSeven(player, npc);

                //-- NPC: Precisely, though there should be more once I have had time to fully examine the last Spell Weaver you had dealt with. I believe I will be able to narrow down the last Spell Weaver's location soon. Return to me when you are ready and I should have the location by then. You have done very well, %TU.
                string_id message = new string_id(c_stringFile, "s_127");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch9(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Ah, it is good to see you have returned to us, %TU. It appears this is more dangerous than I first thought. These crystals appear to be able to significantly amplify the Spell Weavers powers.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Why would they need to do that?
        if (response.equals("s_108"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: It is too early to know their motivations. We have only stopped one of them. What we do know so far is that they are performing a strong spell using these crystals. They must not be allowed to complete it.
                string_id message = new string_id(c_stringFile, "s_110");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What would you have me do?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_111");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 10);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        //-- [RESPONSE NOTE]
        //-- PLAYER: Would it be similar to the Nightcloak spell?
        if (response.equals("s_109"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: It would be similar in some ways, yes. Though, this one seems to be for some purpose far greater than just obscuring us from seeing what they are up to.
                string_id message = new string_id(c_stringFile, "s_128");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What could the purpose be?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_129");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 14);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch10(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: It is too early to know their motivations. We have only stopped one of them. What we do know so far is that they are performing a strong spell using these crystals. They must not be allowed to complete it.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What would you have me do?
        if (response.equals("s_111"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: You will need to go to the Imperial prison, %TU. That is the direction I was able to sense one of the other Spell Weavers. I still need some time to recover and prepare my mind, so I will not be able to guide you. You should be able to speak to someone from the prison. They are likely to have a rough location where she is residing.
                string_id message = new string_id(c_stringFile, "s_113");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: I have already been to the Imperial prison.
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_114");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 11);

                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);

                    npcSpeak(player, pp);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);

                    npcEndConversationWithMessage(player, pp);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch11(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: You will need to go to the Imperial prison, %TU. That is the direction I was able to sense one of the other Spell Weavers. I still need some time to recover and prepare my mind, so I will not be able to guide you. You should be able to speak to someone from the prison. They are likely to have a rough location where she is residing.

        //-- [RESPONSE NOTE]
        //-- PLAYER: I have already been to the Imperial prison.
        if (response.equals("s_114"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: And you know the location of the Nightsister camp as well?
                string_id message = new string_id(c_stringFile, "s_115");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: I do.
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_116");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 12);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch12(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: And you know the location of the Nightsister camp as well?

        //-- [RESPONSE NOTE]
        //-- PLAYER: I do.
        if (response.equals("s_116"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestSevenB(player, npc);

                //-- NPC: Very well, %TU. You are smarter and more motivated than I expected. Go to this Nightsister camp, take care of that Spell Weaver and destroy that crystal. Once that has been done, return to me immediately, time is of critical importance.
                string_id message = new string_id(c_stringFile, "s_117");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch14(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: It would be similar in some ways, yes. Though, this one seems to be for some purpose far greater than just obscuring us from seeing what they are up to.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What could the purpose be?
        if (response.equals("s_129"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: It is too early to know. We have only stopped one Spell Weaver. So far we know they are performing a strong spell using these crystals. They must not be allowed to complete it.
                string_id message = new string_id(c_stringFile, "s_130");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What will you need me to do?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_131");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 15);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch15(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: It is too early to know. We have only stopped one Spell Weaver. So far we know they are performing a strong spell using these crystals. They must not be allowed to complete it.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What will you need me to do?
        if (response.equals("s_131"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: You will travel to the Imperial prison, %TU. I was able to sense one of the other Spell Weavers in that direction. I still need some time to recover and prepare my mind, so I will not be able to guide you. You should be able to speak to someone from the prison. They are likely to have a rough location where she could be residing.
                string_id message = new string_id(c_stringFile, "s_132");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: I have already been to the Imperial prison and I was able to acquire the location of a Nightsister camp not too far from it.
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_133");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 16);

                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);

                    npcSpeak(player, pp);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);

                    npcEndConversationWithMessage(player, pp);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch16(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: You will travel to the Imperial prison, %TU. I was able to sense one of the other Spell Weavers in that direction. I still need some time to recover and prepare my mind, so I will not be able to guide you. You should be able to speak to someone from the prison. They are likely to have a rough location where she could be residing.

        //-- [RESPONSE NOTE]
        //-- PLAYER: I have already been to the Imperial prison and I was able to acquire the location of a Nightsister camp not too far from it.
        if (response.equals("s_133"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestSevenB(player, npc);

                //-- NPC: It seems you have only one thing left to do, %TU. You will head there, strike down that Spell Weaver and destroy her crystal. You are impressing me a great deal. Be sure to report back to me when you do. It is of critical importance we do not waste much time.
                string_id message = new string_id(c_stringFile, "s_134");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch18(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Ah, good to see you have returned. This is more dangerous than I first thought. It appears these crystals are able to significantly amplify the Spell Weavers powers.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Why are they trying to do that?
        if (response.equals("s_83"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: It is too early to know. We have only stopped one of them. What we do know so far is that they are performing a strong spell using these crystals. They must not be allowed to complete it.
                string_id message = new string_id(c_stringFile, "s_85");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: So what would you have me do?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_86");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 19);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        //-- [RESPONSE NOTE]
        //-- PLAYER: Is it similar to the Nightcloak spell?
        if (response.equals("s_84"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: In some ways, yes. Though, this one seems to be for some purpose far greater than just obscuring us from seeing what they are up to.
                string_id message = new string_id(c_stringFile, "s_88");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Why would they want to do that?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_89");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 21);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch19(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: It is too early to know. We have only stopped one of them. What we do know so far is that they are performing a strong spell using these crystals. They must not be allowed to complete it.

        //-- [RESPONSE NOTE]
        //-- PLAYER: So what would you have me do?
        if (response.equals("s_86"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestSeven(player, npc);

                //-- NPC: You will need to go to the Imperial prison, %TU. That is the direction I was able to sense one of the other Spell Weavers. I still need some time to recover and prepare my mind, so I will not be able to guide you. You should be able to speak to someone from the prison. They are likely to have a rough location where she is residing.
                string_id message = new string_id(c_stringFile, "s_87");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch21(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: In some ways, yes. Though, this one seems to be for some purpose far greater than just obscuring us from seeing what they are up to.

        //-- [RESPONSE NOTE]
        //-- PLAYER: Why would they want to do that?
        if (response.equals("s_89"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: It is too early to know. We have only stopped one of them. What we know so far is they are performing a strong spell using these crystals. They must not be allowed to complete it.
                string_id message = new string_id(c_stringFile, "s_90");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: So what would you require from me?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_91");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 22);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch22(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: It is too early to know. We have only stopped one of them. What we know so far is they are performing a strong spell using these crystals. They must not be allowed to complete it.

        //-- [RESPONSE NOTE]
        //-- PLAYER: So what would you require from me?
        if (response.equals("s_91"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestSeven(player, npc);

                //-- NPC: I will need you to go to the Imperial prison, %TU. That is the direction I was able to sense one of the other Spell Weavers. I still need some time to recover and prepare my mind, so I will not be able to guide you. You should be able to speak to someone from the prison. They are likely to have a rough location where she is residing.
                string_id message = new string_id(c_stringFile, "s_92");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch24(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Ah, I can sense the spell weaver and the crystal are no more.

        //-- [RESPONSE NOTE]
        //-- PLAYER: That is correct. It was quite a challenge.
        if (response.equals("s_71"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_sendRewardSignalSix(player, npc);

                //-- NPC: I suspect the other spell weavers will be protecting crystals just like the one you destroyed. You have done well, %TU. Let me know when you are ready to seek out the next spell weaver.
                string_id message = new string_id(c_stringFile, "s_72");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch27(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: You are here to assist us again, %TU?

        //-- [RESPONSE NOTE]
        //-- PLAYER: I am.
        if (response.equals("s_49"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: Good. I have been able to briefly see the source of these disruptions.
                string_id message = new string_id(c_stringFile, "s_50");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What did you see?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_51");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 28);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch28(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Good. I have been able to briefly see the source of these disruptions.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What did you see?
        if (response.equals("s_51"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I saw three figures. My guess is they are spell weavers. Though I am unsure what clan they are from.
                string_id message = new string_id(c_stringFile, "s_52");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: From your vision were you able to figure out their location?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_53");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 29);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch29(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I saw three figures. My guess is they are spell weavers. Though I am unsure what clan they are from.

        //-- [RESPONSE NOTE]
        //-- PLAYER: From your vision were you able to figure out their location?
        if (response.equals("s_53"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: They were all in separate places. I could sense the regions they were in, but nothing more than that.
                string_id message = new string_id(c_stringFile, "s_54");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: So what would you need from me?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_55");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 30);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch30(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: They were all in separate places. I could sense the regions they were in, but nothing more than that.

        //-- [RESPONSE NOTE]
        //-- PLAYER: So what would you need from me?
        if (response.equals("s_55"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I will need you to go to each of the regions where I sensed them. I want you to start with the one I sensed in the northwest. Are you ready to go?
                string_id message = new string_id(c_stringFile, "s_56");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: I am. So what will I be doing?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                //-- PLAYER: I am not.
                boolean hasResponse1 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_65");

                    if (hasResponse1)
                        responses[responseIndex++] = new string_id(c_stringFile, "s_67");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 31);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch31(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I will need you to go to each of the regions where I sensed them. I want you to start with the one I sensed in the northwest. Are you ready to go?

        //-- [RESPONSE NOTE]
        //-- PLAYER: I am. So what will I be doing?
        if (response.equals("s_65"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestSix(player, npc);

                //-- NPC: You will go to the coordinates I am giving you. Once there you will look around and I will sense what is around you. We will continue this process until I sense the spell weaver's location.
                string_id message = new string_id(c_stringFile, "s_66");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                npcEndConversationWithMessage(player, message);

                return SCRIPT_CONTINUE;
            }

        }

        //-- [RESPONSE NOTE]
        //-- PLAYER: I am not.
        if (response.equals("s_67"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: Return to me when you can. Be quick, we do not know what these spell weavers are up to or if they may move.
                string_id message = new string_id(c_stringFile, "s_68");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                npcEndConversationWithMessage(player, message);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch34(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Ah, I sense you return with everything I seek?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Yes, here is everything you asked for.
        if (response.equals("s_36"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_sendRewardSignalFive(player, npc);

                //-- NPC: Very good, %TU. Give me a moment to prepare.
                string_id message = new string_id(c_stringFile, "s_39");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcEndConversationWithMessage(player, pp);

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch37(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: So you are the one helping the Clan Mother?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Yes, I am.
        if (response.equals("s_45"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: Good, I have been trying to figure out the source of the powerful disruptions currently being felt by many of the sisters.
                string_id message = new string_id(c_stringFile, "s_47");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What do you need from me?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_57");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 38);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch38(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: Good, I have been trying to figure out the source of the powerful disruptions currently being felt by many of the sisters.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What do you need from me?
        if (response.equals("s_57"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I need you to retrieve some ingredients for me. These will be essential to give me proper sight. Currently the visions are very cloudy.
                string_id message = new string_id(c_stringFile, "s_59");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: All you need from me is to fetch these ingredients?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_61");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 39);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch39(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I need you to retrieve some ingredients for me. These will be essential to give me proper sight. Currently the visions are very cloudy.

        //-- [RESPONSE NOTE]
        //-- PLAYER: All you need from me is to fetch these ingredients?
        if (response.equals("s_61"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: They are not the easiest to acquire and once they are my sight should be able to reveal who or what is responsible for these powerful disruptions. Are you ready to assist?
                string_id message = new string_id(c_stringFile, "s_63");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: I am.
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }

                //-- PLAYER: I have some questions.
                boolean hasResponse1 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_74");

                    if (hasResponse1)
                        responses[responseIndex++] = new string_id(c_stringFile, "s_78");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 40);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch40(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: They are not the easiest to acquire and once they are my sight should be able to reveal who or what is responsible for these powerful disruptions. Are you ready to assist?

        //-- [RESPONSE NOTE]
        //-- PLAYER: I am.
        if (response.equals("s_74"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestFive(player, npc);

                //-- NPC: I am most pleased. I am entrusting you with my notes. These will tell you the locations of what I need. Return to me once you have them all.
                string_id message = new string_id(c_stringFile, "s_76");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                npcEndConversationWithMessage(player, message);

                return SCRIPT_CONTINUE;
            }

        }

        //-- [RESPONSE NOTE]
        //-- PLAYER: I have some questions.
        if (response.equals("s_78"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: You may ask.
                string_id message = new string_id(c_stringFile, "s_80");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: What are the ingredients you need?
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_82");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 42);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch42(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: You may ask.

        //-- [RESPONSE NOTE]
        //-- PLAYER: What are the ingredients you need?
        if (response.equals("s_82"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I require a tooth from one of the oldest types of Rancor, known as the Ancient Bull Rancor. I also require the eye from a well known arachnid, known as Nis'Renn. Lastly, I require a Midrana plant. These can be found along a specific part of the Dathomir coast. Is that all?
                string_id message = new string_id(c_stringFile, "s_96");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: Yes, it is.
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_98");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 43);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch43(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I require a tooth from one of the oldest types of Rancor, known as the Ancient Bull Rancor. I also require the eye from a well known arachnid, known as Nis'Renn. Lastly, I require a Midrana plant. These can be found along a specific part of the Dathomir coast. Is that all?

        //-- [RESPONSE NOTE]
        //-- PLAYER: Yes, it is.
        if (response.equals("s_98"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                //-- NPC: I have provided you all of these in my notes, should you forget.
                string_id message = new string_id(c_stringFile, "s_100");
                int numberOfResponses = 0;

                boolean hasResponse = false;

                //-- PLAYER: [Take notes]
                boolean hasResponse0 = false;
                if (wod_merilla_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_102");

                    utils.setScriptVar(player, "conversation.wod_merilla.branchId", 44);

                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else
                {
                    utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

                    npcEndConversationWithMessage(player, message);
                }

                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    int wod_merilla_handleBranch44(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        //-- [BRANCH NOTE]
        //-- NPC: I have provided you all of these in my notes, should you forget.

        //-- [RESPONSE NOTE]
        //-- PLAYER: [Take notes]
        if (response.equals("s_102"))
        {
            //-- [NOTE]
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                wod_merilla_action_grantQuestFive(player, npc);

                //-- NPC: Now go! We must find the root of these disruptions.
                string_id message = new string_id(c_stringFile, "s_104");
                utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

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
            detachScript(self, "conversation.wod_merilla");
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
        detachScript(self, "conversation.wod_merilla");

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
        if (wod_merilla_condition_finishedQuestSeven(player, npc))
        {
            //-- NPC: I still need some time to recover and prepare my mind. Come back later, %TU.
            string_id message = new string_id(c_stringFile, "s_93");
            prose_package pp = new prose_package();
            pp.stringId = message;
            pp.actor.set(player);
            pp.target.set(npc);

            chat.chat(npc, player, null, null, pp);
            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_readyForRewardSeven(player, npc))
        {
            //-- NPC: I sense that Spell Weaver has met a swift end. I have misjudged you, %TU.
            string_id message = new string_id(c_stringFile, "s_119");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: This one was a bit more difficult. She had a lot more protectors. But, now her and the crystal are no more.
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_120");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 2);

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcStartConversation(player, npc, "wod_merilla", null, pp, responses);
            }
            else
            {
                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                chat.chat(npc, player, null, null, pp);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_isOnQuestSevenB(player, npc))
        {
            //-- NPC: Do not disturb me. I must rest if we are to find the next Spell Weaver. Eliminate that Spell Weaver and her crystal. Leave at once.
            string_id message = new string_id(c_stringFile, "s_118");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_isOnQuestSeven(player, npc))
        {
            //-- NPC: Do not disturb me. I must rest if we are to find the next Spell Weaver. Talk to someone at the Imperial prison. Leave at once.
            string_id message = new string_id(c_stringFile, "s_94");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_finishedQuestSixDathPrison(player, npc))
        {
            //-- NPC: Ah, it is good to see you have returned to us, %TU. It appears this is more dangerous than I first thought. These crystals appear to be able to significantly amplify the Spell Weavers powers.
            string_id message = new string_id(c_stringFile, "s_107");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Why would they need to do that?
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }

            //-- PLAYER: Would it be similar to the Nightcloak spell?
            boolean hasResponse1 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_108");

                if (hasResponse1)
                    responses[responseIndex++] = new string_id(c_stringFile, "s_109");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 9);

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcStartConversation(player, npc, "wod_merilla", null, pp, responses);
            }
            else
            {
                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                chat.chat(npc, player, null, null, pp);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_finishedQuestSix(player, npc))
        {
            //-- NPC: Ah, good to see you have returned. This is more dangerous than I first thought. It appears these crystals are able to significantly amplify the Spell Weavers powers.
            string_id message = new string_id(c_stringFile, "s_69");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Why are they trying to do that?
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }

            //-- PLAYER: Is it similar to the Nightcloak spell?
            boolean hasResponse1 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_83");

                if (hasResponse1)
                    responses[responseIndex++] = new string_id(c_stringFile, "s_84");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 18);

                npcStartConversation(player, npc, "wod_merilla", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_readyForRewardSix(player, npc))
        {
            //-- NPC: Ah, I can sense the spell weaver and the crystal are no more.
            string_id message = new string_id(c_stringFile, "s_70");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: That is correct. It was quite a challenge.
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_71");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 24);

                npcStartConversation(player, npc, "wod_merilla", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_isOnQuestSix(player, npc))
        {
            //-- NPC: Why are you back here? I need you to scout those locations with all due haste. Do not return here until you do.
            string_id message = new string_id(c_stringFile, "s_73");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_finishedQuestFive(player, npc))
        {
            //-- NPC: You are here to assist us again, %TU?
            string_id message = new string_id(c_stringFile, "s_37");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: I am.
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_49");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 27);

                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                npcStartConversation(player, npc, "wod_merilla", null, pp, responses);
            }
            else
            {
                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);

                chat.chat(npc, player, null, null, pp);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_readyForRewardFive(player, npc))
        {
            //-- NPC: Ah, I sense you return with everything I seek?
            string_id message = new string_id(c_stringFile, "s_34");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Yes, here is everything you asked for.
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_36");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 34);

                npcStartConversation(player, npc, "wod_merilla", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_isOnQuestFive(player, npc))
        {
            //-- NPC: Why are you back here? I sense you do not have the ingredients I have asked for.
            string_id message = new string_id(c_stringFile, "s_41");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition_finishedYirraQuestOne(player, npc))
        {
            //-- NPC: So you are the one helping the Clan Mother?
            string_id message = new string_id(c_stringFile, "s_43");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Yes, I am.
            boolean hasResponse0 = false;
            if (wod_merilla_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_45");

                utils.setScriptVar(player, "conversation.wod_merilla.branchId", 37);

                npcStartConversation(player, npc, "wod_merilla", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        //-- [NOTE]
        if (wod_merilla_condition__defaultCondition(player, npc))
        {
            //-- NPC: Ah, another fool. I have an idea for you, should you stick around. Pity you won't be alive by the end...
            string_id message = new string_id(c_stringFile, "s_106");
            chat.chat(npc, player, message);

            return SCRIPT_CONTINUE;
        }

        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");

        return SCRIPT_CONTINUE;
    }

// ----------------------------------------------------------------------

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("wod_merilla"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.wod_merilla.branchId");

        if (branchId == 2 && wod_merilla_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 3 && wod_merilla_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 4 && wod_merilla_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 5 && wod_merilla_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 9 && wod_merilla_handleBranch9(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 10 && wod_merilla_handleBranch10(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 11 && wod_merilla_handleBranch11(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 12 && wod_merilla_handleBranch12(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 14 && wod_merilla_handleBranch14(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 15 && wod_merilla_handleBranch15(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 16 && wod_merilla_handleBranch16(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 18 && wod_merilla_handleBranch18(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 19 && wod_merilla_handleBranch19(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 21 && wod_merilla_handleBranch21(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 22 && wod_merilla_handleBranch22(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 24 && wod_merilla_handleBranch24(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 27 && wod_merilla_handleBranch27(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 28 && wod_merilla_handleBranch28(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 29 && wod_merilla_handleBranch29(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 30 && wod_merilla_handleBranch30(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 31 && wod_merilla_handleBranch31(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 34 && wod_merilla_handleBranch34(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 37 && wod_merilla_handleBranch37(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 38 && wod_merilla_handleBranch38(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 39 && wod_merilla_handleBranch39(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 40 && wod_merilla_handleBranch40(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 42 && wod_merilla_handleBranch42(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 43 && wod_merilla_handleBranch43(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        if (branchId == 44 && wod_merilla_handleBranch44(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.wod_merilla.branchId");

        return SCRIPT_CONTINUE;
    }

// ======================================================================

}