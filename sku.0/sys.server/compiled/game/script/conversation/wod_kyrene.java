// ======================================================================
//
// wod_kyrene.java
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

import script.library.*;
import script.*;

public class wod_kyrene extends script.base_script
{
	public wod_kyrene()
	{
	}
// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wod_kyrene";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wod_kyrene_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_finishedQuestOne (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_seeking_retribution"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_isOnQuestTwo (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_interfering_forces"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_readyForRewardTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_interfering_forces", "returnKyreneOne"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_finishedQuestTwo (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_interfering_forces"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_isOnQuestThree (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_interfering_forces_2"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_finishedQuestThree (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_interfering_forces_2"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_readyForRewardThree (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_interfering_forces_2", "returnKyreneTwo"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_isOnQuestRepeatOne (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_kill_remnant_spider_clan"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_isOnQuestRepeatTwo (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_kill_dathomir_imperials"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_isOnQuestRepeatThree (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_kill_sith_shadows"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_isOnQuestRepeatFour (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_kill_afflicted_witches"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_hasQuestActiveAll (obj_id player, obj_id npc) throws InterruptedException
{
        
	return questIsQuestActive(questGetQuestId("quest/wod_kill_remnant_spider_clan"), player) && questIsQuestActive(questGetQuestId("quest/wod_kill_dathomir_imperials"), player) && questIsQuestActive(questGetQuestId("quest/wod_kill_sith_shadows"), player) && questIsQuestActive(questGetQuestId("quest/wod_kill_afflicted_witches"), player);
}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_readyForRewardRepeatOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_kill_remnant_spider_clan", "returnKyreneRepeatOne"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_readyForRewardRepeatTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_kill_dathomir_imperials", "returnKyreneRepeatTwo"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_readyForRewardRepeatThree (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_kill_sith_shadows", "returnKyreneRepeatThree"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_kyrene_condition_readyForRewardRepeatFour (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_kill_afflicted_witches", "returnKyreneRepeatFour"))
        {
            return true;
        }
        return false;

}

// ======================================================================
// Script Actions
// ======================================================================

public void wod_kyrene_action_grantQuestTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_interfering_forces");
        return;
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_grantQuestThree (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_interfering_forces_2");
        return;
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_sendRewardSignalTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedInterferingForces");
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_sendRewardSignalThree (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedInterferingForcesTwo");
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_grantQuestRepeatOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_kill_remnant_spider_clan");
        return;
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_grantQuestRepeatTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_kill_dathomir_imperials");
        return;
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_grantQuestRepeatThree (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_kill_sith_shadows");
        return;
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_grantQuestRepeatFour (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_kill_afflicted_witches");
        return;
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_sendRewardSignalRepeatOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedSpiderClanRepeatable");
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_sendRewardSignalRepeatTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedDathomirImperialRepeatable");
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_sendRewardSignalRepeatThree (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedSithShadowRepeatable");
}

// ----------------------------------------------------------------------

public void wod_kyrene_action_sendRewardSignalRepeatFour (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedAfflictedWitchesRepeatable");
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

int wod_kyrene_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You accomplished your hunt. I can sense it.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have. I hope that no others suffer this fate. 
	if (response.equals("s_98"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: You putting them to rest ensures that. You have done well, the Blackwing Virus is most abhorrent.
			string_id message = new string_id (c_stringFile, "s_99");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch3 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Successful in your hunt, are you not? 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Indeed. It is hard to tell if my actions made a difference.
	if (response.equals("s_95"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: It is better than nothing. The Sith Shadows are a mysterious and dangerous group. The fewer of them the better. 
			string_id message = new string_id (c_stringFile, "s_96");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch5 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Do you return successful in your hunt?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I do. The Imperials at the prison are truly struggling to find replacements. 
	if (response.equals("s_92"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: That is good. The Daughters of Allya hope to see them depart this planet soon.
			string_id message = new string_id (c_stringFile, "s_93");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I take it that you return to me successful in your hunt.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was. The Spider Clan is truly vile.
	if (response.equals("s_89"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: They are indeed, corrupted beyond help. This should further delay the Queen Mother from returning, if ever. 
			string_id message = new string_id (c_stringFile, "s_90");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch10 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You have proven yourself to be quite the hunter, %TU. Do you wish to assist us further? 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I do, what needs to be done?
	if (response.equals("s_60"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: The enemies of the witch clans have be weakend with your recent actions. However, they still draw breath and could one day present a danger. Who would you like to hunt again?
			string_id message = new string_id (c_stringFile, "s_63");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: How about the remnants of the Spider Clan?
			boolean hasResponse0 = false;
			if (!wod_kyrene_condition_isOnQuestRepeatOne (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: How about those at the Imperial Prison?
			boolean hasResponse1 = false;
			if (!wod_kyrene_condition_isOnQuestRepeatTwo (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: How about the Sith Shadow mercenaries?
			boolean hasResponse2 = false;
			if (!wod_kyrene_condition_isOnQuestRepeatThree (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			//-- PLAYER: How about those afflicted witches of the Howling Crag Clan?
			boolean hasResponse3 = false;
			if (!wod_kyrene_condition_isOnQuestRepeatFour (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse3 = true;
			}

			//-- PLAYER: Is there anything else?
			boolean hasResponse4 = false;
			if (wod_kyrene_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse4 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_64");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_65");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_66");

				if (hasResponse3)
					responses [responseIndex++] = new string_id (c_stringFile, "s_67");

				if (hasResponse4)
					responses [responseIndex++] = new string_id (c_stringFile, "s_72");

				utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 11);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I cannot at this time.
	if (response.equals("s_61"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: A pity. Return here if you change your mind. 
			string_id message = new string_id (c_stringFile, "s_62");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch11 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: The enemies of the witch clans have be weakend with your recent actions. However, they still draw breath and could one day present a danger. Who would you like to hunt again?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: How about the remnants of the Spider Clan?
	if (response.equals("s_64"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestRepeatOne (player, npc);

			//-- NPC: Yes, kill a sufficient number of them. We would like to not see another Queen Mother ever again.
			string_id message = new string_id (c_stringFile, "s_68");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: How about those at the Imperial Prison?
	if (response.equals("s_65"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestRepeatTwo (player, npc);

			//-- NPC: Yes, kill a sufficient number of them. They continue to kill the Daughters of Allya indiscriminately.
			string_id message = new string_id (c_stringFile, "s_69");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: How about the Sith Shadow mercenaries?
	if (response.equals("s_66"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestRepeatThree (player, npc);

			//-- NPC: Yes, kill a sufficient number of them. They are attempting to tap into the same reserve that the witch clans draw power from. 
			string_id message = new string_id (c_stringFile, "s_70");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: How about those afflicted witches of the Howling Crag Clan?
	if (response.equals("s_67"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestRepeatFour (player, npc);

			//-- NPC: Yes, kill a sufficient number of them. They must be wiped out before this affliction spreads to the other clans.
			string_id message = new string_id (c_stringFile, "s_71");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Is there anything else?
	if (response.equals("s_72"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: Yes, go speak with Clan Mother Yirra, if you have not already. You have proved yourself.
			string_id message = new string_id (c_stringFile, "s_73");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch18 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I sense a powerful resolve in you. These leaders have been dispatched?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: They will no longer be a burden to anyone.
	if (response.equals("s_52"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: Very good. I believe it is time for you to return to Clan Mother Yirra. You have proved yourself to be quite the hunter, %TU.
			string_id message = new string_id (c_stringFile, "s_53");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			prose_package pp = new prose_package ();
			pp.stringId = message;
			pp.actor.set (player);
			pp.target.set (npc);

			npcEndConversationWithMessage (player, pp);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch20 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Is the hunt complete?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: It is not.
	if (response.equals("s_37"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: Do not come back until it is.
			string_id message = new string_id (c_stringFile, "s_39");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch22 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We must hunt greater foes. There are important targets of each group within striking distance. Are you ready to hunt again?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am ready. Where do we strike?
	if (response.equals("s_43"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestThree (player, npc);

			//-- NPC: Here are their locations. %TU, dispatch them quickly and without hesitation.
			string_id message = new string_id (c_stringFile, "s_45");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			prose_package pp = new prose_package ();
			pp.stringId = message;
			pp.actor.set (player);
			pp.target.set (npc);

			npcEndConversationWithMessage (player, pp);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am not prepared yet.
	if (response.equals("s_47"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: You may have a bit more rest to regain focus. But these targets will not be open to us forever.
			string_id message = new string_id (c_stringFile, "s_49");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch25 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I sense a hunter's calm in you. The burdens have been dispatched?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: They should cause the witch clans no further trouble.
	if (response.equals("s_56"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: Good. However, to truly stop them we must silence some prominent members of each troublesome group. Are you ready to hunt again?
			string_id message = new string_id (c_stringFile, "s_59");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I am ready to hunt again.
			boolean hasResponse0 = false;
			if (wod_kyrene_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I helped you already with one task, that's enough.
			boolean hasResponse1 = false;
			if (wod_kyrene_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_75");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_79");

				utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 26);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch26 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Good. However, to truly stop them we must silence some prominent members of each troublesome group. Are you ready to hunt again?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I am ready to hunt again.
	if (response.equals("s_75"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestThree (player, npc);

			//-- NPC: Here are their last known locations. Dispatch them quickly and without hesitation.
			string_id message = new string_id (c_stringFile, "s_77");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I helped you already with one task, that's enough.
	if (response.equals("s_79"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: Insolent fool! Come back to me only when you are willing to hunt again.
			string_id message = new string_id (c_stringFile, "s_81");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch29 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Do these burdens still persist? Are you having difficulty?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: They do and they will be settled soon.
	if (response.equals("s_85"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: That is what I like to hear. Now go!
			string_id message = new string_id (c_stringFile, "s_100");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch31 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: What are you doing here?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I've been sent by the Clan Mother.
	if (response.equals("s_104"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			//-- NPC: Ah yes, I see that you have already completed Yirra's task. 
			string_id message = new string_id (c_stringFile, "s_106");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: The Clan Mother said you would have some tasks for me.
			boolean hasResponse0 = false;
			if (wod_kyrene_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_108");

				utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 32);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_kyrene_handleBranch32 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Ah yes, I see that you have already completed Yirra's task. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: The Clan Mother said you would have some tasks for me.
	if (response.equals("s_108"))
	{
		//-- [NOTE] 
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			wod_kyrene_action_grantQuestTwo (player, npc);

			//-- NPC: Clan Mother Yirra is right. It is time that the Red Hills Clan goes on the hunt. You will assist us in eliminating those who are interfering with the witch clans.
			string_id message = new string_id (c_stringFile, "s_110");
			utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

			npcEndConversationWithMessage (player, message);

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
	if ((!isMob (self)) || (isPlayer (self)))
	{
		detachScript(self, "conversation.wod_kyrene");
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
	detachScript (self, "conversation.wod_kyrene");

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
	if (wod_kyrene_condition_readyForRewardRepeatFour (player, npc))
	{
		wod_kyrene_action_sendRewardSignalRepeatFour (player, npc);

		//-- NPC: You accomplished your hunt. I can sense it.
		string_id message = new string_id (c_stringFile, "s_97");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have. I hope that no others suffer this fate. 
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 1);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_readyForRewardRepeatThree (player, npc))
	{
		wod_kyrene_action_sendRewardSignalRepeatThree (player, npc);

		//-- NPC: Successful in your hunt, are you not? 
		string_id message = new string_id (c_stringFile, "s_94");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Indeed. It is hard to tell if my actions made a difference.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_95");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 3);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_readyForRewardRepeatTwo (player, npc))
	{
		wod_kyrene_action_sendRewardSignalRepeatTwo (player, npc);

		//-- NPC: Do you return successful in your hunt?
		string_id message = new string_id (c_stringFile, "s_91");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I do. The Imperials at the prison are truly struggling to find replacements. 
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_92");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 5);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_readyForRewardRepeatOne (player, npc))
	{
		wod_kyrene_action_sendRewardSignalRepeatOne (player, npc);

		//-- NPC: I take it that you return to me successful in your hunt.
		string_id message = new string_id (c_stringFile, "s_88");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I was. The Spider Clan is truly vile.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 7);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_hasQuestActiveAll (player, npc))
	{
		//-- NPC: You are making me question your ability when you return to me without completing a single task.
		string_id message = new string_id (c_stringFile, "s_87");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_finishedQuestThree (player, npc))
	{
		//-- NPC: You have proven yourself to be quite the hunter, %TU. Do you wish to assist us further? 
		string_id message = new string_id (c_stringFile, "s_57");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I do, what needs to be done?
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: I cannot at this time.
		boolean hasResponse1 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_60");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_61");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 10);

			prose_package pp = new prose_package ();
			pp.stringId = message;
			pp.actor.set (player);
			pp.target.set (npc);

			npcStartConversation (player, npc, "wod_kyrene", null, pp, responses);
		}
		else
		{
			prose_package pp = new prose_package ();
			pp.stringId = message;
			pp.actor.set (player);
			pp.target.set (npc);

			chat.chat (npc, player, null, null, pp);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_readyForRewardThree (player, npc))
	{
		wod_kyrene_action_sendRewardSignalThree (player, npc);

		//-- NPC: I sense a powerful resolve in you. These leaders have been dispatched?
		string_id message = new string_id (c_stringFile, "s_51");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: They will no longer be a burden to anyone.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_52");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 18);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_isOnQuestThree (player, npc))
	{
		//-- NPC: Is the hunt complete?
		string_id message = new string_id (c_stringFile, "s_35");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: It is not.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_37");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 20);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_finishedQuestTwo (player, npc))
	{
		//-- NPC: We must hunt greater foes. There are important targets of each group within striking distance. Are you ready to hunt again?
		string_id message = new string_id (c_stringFile, "s_41");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I am ready. Where do we strike?
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: I am not prepared yet.
		boolean hasResponse1 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_43");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_47");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 22);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_readyForRewardTwo (player, npc))
	{
		wod_kyrene_action_sendRewardSignalTwo (player, npc);

		//-- NPC: I sense a hunter's calm in you. The burdens have been dispatched?
		string_id message = new string_id (c_stringFile, "s_54");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: They should cause the witch clans no further trouble.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 25);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_isOnQuestTwo (player, npc))
	{
		//-- NPC: Do these burdens still persist? Are you having difficulty?
		string_id message = new string_id (c_stringFile, "s_83");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: They do and they will be settled soon.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_85");

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 29);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition_finishedQuestOne (player, npc))
	{
		//-- NPC: What are you doing here?
		string_id message = new string_id (c_stringFile, "s_102");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I've been sent by the Clan Mother.
		boolean hasResponse0 = false;
		if (wod_kyrene_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_kyrene.branchId", 31);

			npcStartConversation (player, npc, "wod_kyrene", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_kyrene_condition__defaultCondition (player, npc))
	{
		//-- NPC: Would you like to be the target of my next hunt? Leave now!
		string_id message = new string_id (c_stringFile, "s_112");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	chat.chat (npc, "Error:  All conditions for OnStartNpcConversation were false.");

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
{
	if (!conversationId.equals("wod_kyrene"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wod_kyrene.branchId");

	if (branchId == 1 && wod_kyrene_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 3 && wod_kyrene_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 5 && wod_kyrene_handleBranch5 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 7 && wod_kyrene_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 10 && wod_kyrene_handleBranch10 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 11 && wod_kyrene_handleBranch11 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 18 && wod_kyrene_handleBranch18 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 20 && wod_kyrene_handleBranch20 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 22 && wod_kyrene_handleBranch22 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 25 && wod_kyrene_handleBranch25 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 26 && wod_kyrene_handleBranch26 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 29 && wod_kyrene_handleBranch29 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 31 && wod_kyrene_handleBranch31 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 32 && wod_kyrene_handleBranch32 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wod_kyrene.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}