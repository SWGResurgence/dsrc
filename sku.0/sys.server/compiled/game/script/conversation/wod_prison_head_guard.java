// ======================================================================
//
// wod_prison_head_guard.java
// Copyright 2004, Sony Online Entertainment
// All Rights Reserved.
//
// Created with SwgConversationEditor 1.37 - DO NOT EDIT THIS AUTO-GENERATED FILE!
// 
// ======================================================================

package script.conversation;

// ======================================================================
// Library Includes
// ======================================================================

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class wod_prison_head_guard extends script.base_script
{
	public wod_prison_head_guard()

	{

	}

// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wod_prison_head_guard";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wod_prison_head_guard_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_isOnQuestSeven (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_seeking_the_spellweavers_two"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_isOnQuestPrisonHeadGuardOne (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_scouting_for_salvage"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_readyForRewardPrisonHeadGuardOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_scouting_for_salvage", "returnPrisonHeadGuardOne"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_finishedPrisonHeadGuardQuestOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/wod_scouting_for_salvage"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_finishedGuardQuestisOnQuestSeven (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/wod_scouting_for_salvage") && groundquests.isQuestActive(player, "quest/wod_seeking_the_spellweavers_two"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_isOnQuestPrisonHeadGuardTwo (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_striking_back_at_the_nightsisters"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_finishedPrisonHeadGuardQuestTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/wod_striking_back_at_the_nightsisters"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_prison_head_guard_condition_readyForRewardPrisonHeadGuardTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_striking_back_at_the_nightsisters", "returnPrisonHeadGuardTwo"))
        {
            return true;
        }
        return false;

}

// ======================================================================
// Script Actions
// ======================================================================

public void wod_prison_head_guard_action_sendRewardSignalQuestSevenTaskOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "talktoDathPrisonGuard");
}

// ----------------------------------------------------------------------

public void wod_prison_head_guard_action_grantQuestPrisonHeadGuardOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_scouting_for_salvage");
        return;
}

// ----------------------------------------------------------------------

public void wod_prison_head_guard_action_sendRewardSignalPrisonHeadGuardOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedScoutingforSalvage");
}

// ----------------------------------------------------------------------

public void wod_prison_head_guard_action_grantQuestPrisonHeadGuardTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_striking_back_at_the_nightsisters");
        return;
}

// ----------------------------------------------------------------------

public void wod_prison_head_guard_action_sendRewardSignalQuestSevenTaskTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "giveNSCampLocation");
}

// ----------------------------------------------------------------------

public void wod_prison_head_guard_action_sendRewardSignalPrisonHeadGuardTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedStrikingBackattheNightsisters");
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

int wod_prison_head_guard_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You have returned. I take it the Nightsisters are missing some of their sisters and an AT-ST?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Indeed, they are.
	if (response.equals("s_111"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_sendRewardSignalPrisonHeadGuardTwo (player, npc);

			//-- NPC: Good work, mercenary. Here is your reward as promised. Our salvage team at the prison was able to repurpose some of the AT-ST parts that weren't able to be used as spare parts into a gift. Consider it a personal token of gratitude for making their job easier.  
			string_id message = new string_id (c_stringFile, "s_112");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Have you struck the Nightsisters at their camp yet?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have not.
	if (response.equals("s_107"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Get back out there. Return back when you have done as I asked. 
			string_id message = new string_id (c_stringFile, "s_108");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch6 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Ready for your next mission?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What about the location of the Nightsister camp?
	if (response.equals("s_93"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Interesting you mention that. That is where I was going to send you.
			string_id message = new string_id (c_stringFile, "s_95");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What is it you need done?
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_102");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 7);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Interesting you mention that. That is where I was going to send you.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What is it you need done?
	if (response.equals("s_102"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_sendRewardSignalQuestSevenTaskTwo (player, npc);

			//-- NPC: We're in no position here at the prison to launch offensive operations anymore. However, my scouts were able to locate a Nightsister camp nearby. I would like you to eliminate some of the witches at the camp. Also, we have reports of Imperial walkers in use by them. I would like you to take at least one of them out as well. Here is the location of the camp. Are you ready to go?
			string_id message = new string_id (c_stringFile, "s_103");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I am.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_104");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 8);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch8 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We're in no position here at the prison to launch offensive operations anymore. However, my scouts were able to locate a Nightsister camp nearby. I would like you to eliminate some of the witches at the camp. Also, we have reports of Imperial walkers in use by them. I would like you to take at least one of them out as well. Here is the location of the camp. Are you ready to go?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am.
	if (response.equals("s_104"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_grantQuestPrisonHeadGuardTwo (player, npc);

			//-- NPC: Return to me once you have slain some of those horrid Nightsisters. Do not forget to destroy at least one walker as well.
			string_id message = new string_id (c_stringFile, "s_105");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch10 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Are you ready for your next mission?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am. What is it you need?
	if (response.equals("s_89"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: We're going to go on the offensive. Well more like you're going to go on the offensive. My scouts were able to get the location of a Nightsister camp near the prison. The intel is a bit dated, but I can guarantee they would not have moved a camp of that size. 
			string_id message = new string_id (c_stringFile, "s_97");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: You want me to launch an attack on a whole camp?
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_98");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 11);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch11 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We're going to go on the offensive. Well more like you're going to go on the offensive. My scouts were able to get the location of a Nightsister camp near the prison. The intel is a bit dated, but I can guarantee they would not have moved a camp of that size. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: You want me to launch an attack on a whole camp?
	if (response.equals("s_98"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: No, of course not. I would like you to eliminate some of the witches there. Also, we know the witches are employing some of our walkers. I want you to take at least one of them out of commission. Are you ready to go?
			string_id message = new string_id (c_stringFile, "s_99");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I am.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_100");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 12);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch12 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: No, of course not. I would like you to eliminate some of the witches there. Also, we know the witches are employing some of our walkers. I want you to take at least one of them out of commission. Are you ready to go?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am.
	if (response.equals("s_100"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_grantQuestPrisonHeadGuardTwo (player, npc);

			//-- NPC: Good. Return to me when once you have eliminated some of those vile witches. Do not forget about the walker either. 
			string_id message = new string_id (c_stringFile, "s_101");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch14 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You have returned. Have you been to all the locations yet?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have.
	if (response.equals("s_62"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Great work. Are you ready to give me a debriefing of the conditions of each wreck?
			string_id message = new string_id (c_stringFile, "s_63");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I am ready.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_64");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 15);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch15 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Great work. Are you ready to give me a debriefing of the conditions of each wreck?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am ready.
	if (response.equals("s_64"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: What happened at the first wreck?
			string_id message = new string_id (c_stringFile, "s_65");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I arrived and saw the walker in relatively good condition. No one was around, it looked untouched. 
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_66");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 16);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch16 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: What happened at the first wreck?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I arrived and saw the walker in relatively good condition. No one was around, it looked untouched. 
	if (response.equals("s_66"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: That's good. How about the second?
			string_id message = new string_id (c_stringFile, "s_67");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I was ambushed by a group of rancors. However, the walker looked to be in okay condition. 
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_68");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 17);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch17 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: That's good. How about the second?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was ambushed by a group of rancors. However, the walker looked to be in okay condition. 
	if (response.equals("s_68"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: You sound like quite the capable spacer. Now, the third location?
			string_id message = new string_id (c_stringFile, "s_69");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: The walker seems to have become a home for some kamurith, internally it may not be in the best shape.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_70");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 18);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch18 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You sound like quite the capable spacer. Now, the third location?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: The walker seems to have become a home for some kamurith, internally it may not be in the best shape.
	if (response.equals("s_70"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: The armor plating and some of the external parts may still be usable. How about the fourth walker?
			string_id message = new string_id (c_stringFile, "s_72");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: There was no sign of a walker at that spot. 
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_73");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 19);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch19 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: The armor plating and some of the external parts may still be usable. How about the fourth walker?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: There was no sign of a walker at that spot. 
	if (response.equals("s_73"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: That is not good. How about the last walker wreck?
			string_id message = new string_id (c_stringFile, "s_74");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I was ambushed by some Nightsisters. One wore a distinctive black and blue outfit. The wreck seems to be in good shape though.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_75");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 20);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch20 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: That is not good. How about the last walker wreck?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was ambushed by some Nightsisters. One wore a distinctive black and blue outfit. The wreck seems to be in good shape though.
	if (response.equals("s_75"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_sendRewardSignalPrisonHeadGuardOne (player, npc);

			//-- NPC: Thank you for now. You have done a great service for my salvage teams and for the Empire. Give me a moment to put together your next mission. 
			string_id message = new string_id (c_stringFile, "s_76");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch22 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Have you gone to all the locations yet?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have not.
	if (response.equals("s_31"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Get back out there.
			string_id message = new string_id (c_stringFile, "s_32");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch24 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Why are you here? Are you one of the mercenaries hired by the Warden?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: No, I am looking for a Nightsister camp in this area.
	if (response.equals("s_27"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_sendRewardSignalQuestSevenTaskOne (player, npc);

			//-- NPC: You are looking for a Nightsister camp? I think I have heard everything now. Why would you want to do that?
			string_id message = new string_id (c_stringFile, "s_29");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I've been hired to eliminate a Spell Weaver. She is believed to be at that camp. 
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I've been hired by the Empire to eliminate the camp.
			boolean hasResponse1 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_34");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_42");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 25);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch25 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You are looking for a Nightsister camp? I think I have heard everything now. Why would you want to do that?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I've been hired to eliminate a Spell Weaver. She is believed to be at that camp. 
	if (response.equals("s_34"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: I do know its location. It looks like we have a mutual interest. You perform some duties for the Empire and I can provide you the camp's location.
			string_id message = new string_id (c_stringFile, "s_36");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What would you have me do?
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_38");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 26);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I've been hired by the Empire to eliminate the camp.
	if (response.equals("s_42"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: As much as I want to believe that the Imperial forces here are too stretched to do offensive actions. Even the mercenaries we hire are used in purely a defensive capacity. That being said, I do know the general location of a Nightsister camp, but you will have to earn my trust before I can give you that information. 
			string_id message = new string_id (c_stringFile, "s_44");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: How can I do that?
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_46");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 28);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch26 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I do know its location. It looks like we have a mutual interest. You perform some duties for the Empire and I can provide you the camp's location.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What would you have me do?
	if (response.equals("s_38"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_grantQuestPrisonHeadGuardOne (player, npc);

			//-- NPC: Several AT-STs have been lost in combat to those infernal witches. I need you to go to these locations, confirm the walkers are still there, and inspect their conditions. Once you have done all of that, return to me. 
			string_id message = new string_id (c_stringFile, "s_40");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch28 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: As much as I want to believe that the Imperial forces here are too stretched to do offensive actions. Even the mercenaries we hire are used in purely a defensive capacity. That being said, I do know the general location of a Nightsister camp, but you will have to earn my trust before I can give you that information. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: How can I do that?
	if (response.equals("s_46"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: I need someone to go out to a few locations, confirm there is a walker at each. If there is, inspect their conditions and return to me.
			string_id message = new string_id (c_stringFile, "s_48");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'll do it.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_50");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 29);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch29 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I need someone to go out to a few locations, confirm there is a walker at each. If there is, inspect their conditions and return to me.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'll do it.
	if (response.equals("s_50"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_grantQuestPrisonHeadGuardOne (player, npc);

			//-- NPC: Good. Here are the locations. You would do best to remember that these walkers were taken out in combat, so you should be prepared for some yourself.
			string_id message = new string_id (c_stringFile, "s_52");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch31 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: What are you doing here? Are you one of the mercenaries hired by the Warden?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was just passing through. 
	if (response.equals("s_56"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Passing through? You are on Dathomir near a restricted area. Only a fool would be sight seeing out here.
			string_id message = new string_id (c_stringFile, "s_58");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'm quite the adventurer.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_60");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 32);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch32 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Passing through? You are on Dathomir near a restricted area. Only a fool would be sight seeing out here.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'm quite the adventurer.
	if (response.equals("s_60"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Must be if you are out here and still alive. Enough small talk. The situation at this prison is dire. Normally we would not approach spacers, but we aren't left with much choice. Would you like to work for us? The Empire would be able to greatly compensate you. 
			string_id message = new string_id (c_stringFile, "s_80");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Sure. What would you need done?
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I'm not interested. 
			boolean hasResponse1 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_82");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_94");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 33);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch33 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Must be if you are out here and still alive. Enough small talk. The situation at this prison is dire. Normally we would not approach spacers, but we aren't left with much choice. Would you like to work for us? The Empire would be able to greatly compensate you. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Sure. What would you need done?
	if (response.equals("s_82"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: We have lost a number of AT-STs in some skirmishes with the insufferable Nightsisters. We aren't able to get any replacements and we need parts to maintain the current ones we still have. I have the locations of where lost several of them. I need you to go these locations, inspect the wrecks and let me know the condition or if they are still even there. 
			string_id message = new string_id (c_stringFile, "s_84");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Still even there?
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_86");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 34);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'm not interested. 
	if (response.equals("s_94"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Then I am going to have to ask you to leave. If you reconsider let the guard on duty know.
			string_id message = new string_id (c_stringFile, "s_96");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch34 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We have lost a number of AT-STs in some skirmishes with the insufferable Nightsisters. We aren't able to get any replacements and we need parts to maintain the current ones we still have. I have the locations of where lost several of them. I need you to go these locations, inspect the wrecks and let me know the condition or if they are still even there. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Still even there?
	if (response.equals("s_86"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			//-- NPC: Some of our wrecked walkers have just disappeared. That's why I need you to confirm they're still there, so my engineers don't risk their lives for nothing.
			string_id message = new string_id (c_stringFile, "s_88");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I can do that.
			boolean hasResponse0 = false;
			if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_90");

				utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 35);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_prison_head_guard_handleBranch35 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Some of our wrecked walkers have just disappeared. That's why I need you to confirm they're still there, so my engineers don't risk their lives for nothing.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I can do that.
	if (response.equals("s_90"))
	{
		//-- [NOTE] 
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
		{
			wod_prison_head_guard_action_grantQuestPrisonHeadGuardOne (player, npc);

			//-- NPC: Good. Here are the locations. I do not expect you to provide detailed information. Just inspect the general condition of the walkers.
			string_id message = new string_id (c_stringFile, "s_92");
			utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

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
		detachScript(self, "conversation.wod_prison_head_guard");
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
	detachScript (self, "conversation.wod_prison_head_guard");

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
	if (wod_prison_head_guard_condition_finishedPrisonHeadGuardQuestTwo (player, npc))
	{
		//-- NPC: Thank you for your assistance, mercenary. I don't have any more tasks for you at the moment.
		string_id message = new string_id (c_stringFile, "s_110");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_readyForRewardPrisonHeadGuardTwo (player, npc))
	{
		//-- NPC: You have returned. I take it the Nightsisters are missing some of their sisters and an AT-ST?
		string_id message = new string_id (c_stringFile, "s_109");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Indeed, they are.
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_111");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 2);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_isOnQuestPrisonHeadGuardTwo (player, npc))
	{
		//-- NPC: Have you struck the Nightsisters at their camp yet?
		string_id message = new string_id (c_stringFile, "s_106");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have not.
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_107");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 4);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_finishedGuardQuestisOnQuestSeven (player, npc))
	{
		//-- NPC: Ready for your next mission?
		string_id message = new string_id (c_stringFile, "s_91");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: What about the location of the Nightsister camp?
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_93");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 6);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_finishedPrisonHeadGuardQuestOne (player, npc))
	{
		//-- NPC: Are you ready for your next mission?
		string_id message = new string_id (c_stringFile, "s_77");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I am. What is it you need?
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_89");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 10);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_readyForRewardPrisonHeadGuardOne (player, npc))
	{
		//-- NPC: You have returned. Have you been to all the locations yet?
		string_id message = new string_id (c_stringFile, "s_61");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have.
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_62");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 14);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_isOnQuestPrisonHeadGuardOne (player, npc))
	{
		//-- NPC: Have you gone to all the locations yet?
		string_id message = new string_id (c_stringFile, "s_30");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have not.
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_31");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 22);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition_isOnQuestSeven (player, npc))
	{
		//-- NPC: Why are you here? Are you one of the mercenaries hired by the Warden?
		string_id message = new string_id (c_stringFile, "s_25");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: No, I am looking for a Nightsister camp in this area.
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_27");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 24);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_prison_head_guard_condition__defaultCondition (player, npc))
	{
		//-- NPC: What are you doing here? Are you one of the mercenaries hired by the Warden?
		string_id message = new string_id (c_stringFile, "s_54");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I was just passing through. 
		boolean hasResponse0 = false;
		if (wod_prison_head_guard_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_56");

			utils.setScriptVar (player, "conversation.wod_prison_head_guard.branchId", 31);

			npcStartConversation (player, npc, "wod_prison_head_guard", message, responses);
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
	if (!conversationId.equals("wod_prison_head_guard"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wod_prison_head_guard.branchId");

	if (branchId == 2 && wod_prison_head_guard_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 4 && wod_prison_head_guard_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 6 && wod_prison_head_guard_handleBranch6 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 7 && wod_prison_head_guard_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 8 && wod_prison_head_guard_handleBranch8 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 10 && wod_prison_head_guard_handleBranch10 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 11 && wod_prison_head_guard_handleBranch11 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 12 && wod_prison_head_guard_handleBranch12 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 14 && wod_prison_head_guard_handleBranch14 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 15 && wod_prison_head_guard_handleBranch15 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 16 && wod_prison_head_guard_handleBranch16 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 17 && wod_prison_head_guard_handleBranch17 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 18 && wod_prison_head_guard_handleBranch18 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 19 && wod_prison_head_guard_handleBranch19 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 20 && wod_prison_head_guard_handleBranch20 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 22 && wod_prison_head_guard_handleBranch22 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 24 && wod_prison_head_guard_handleBranch24 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 25 && wod_prison_head_guard_handleBranch25 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 26 && wod_prison_head_guard_handleBranch26 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 28 && wod_prison_head_guard_handleBranch28 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 29 && wod_prison_head_guard_handleBranch29 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 31 && wod_prison_head_guard_handleBranch31 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 32 && wod_prison_head_guard_handleBranch32 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 33 && wod_prison_head_guard_handleBranch33 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 34 && wod_prison_head_guard_handleBranch34 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 35 && wod_prison_head_guard_handleBranch35 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wod_prison_head_guard.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}