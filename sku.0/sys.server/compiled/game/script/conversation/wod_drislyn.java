// ======================================================================
//
// wod_drislyn.java
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

public class wod_drislyn extends script.base_script
{
	public wod_drislyn()

	{

	}

// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wod_drislyn";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wod_drislyn_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_isOnQuestFiveTaskTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_distorted_visions", "collectMidranaPlant"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_isOnQuestDrislynOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isQuestActive(player, "quest/wod_gathering_pieces_one"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_readyForRewardDrislynOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_gathering_pieces_one", "returnDrislynOne"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_finishedDrislynQuestOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/quest/wod_gathering_pieces_one"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_isOnQuestDrislynTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isQuestActive(player, "quest/wod_gathering_pieces_two"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_readyForRewardDrislynTwo (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_gathering_pieces_two", "returnDrislynTwo"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_finishedDrislynQuestTwoNoPlant (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/wod_gathering_pieces_two") && groundquests.isTaskActive(player, "quest/wod_distorted_visions", "collectMidranaPlant"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_finishedDrislynQuestTwoNotQuestFive (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/wod_gathering_pieces_two") && groundquests.isTaskActive(player, "quest/wod_distorted_visions", "returnMerillaOne"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_drislyn_condition_finishedDrislynQuestTwoAndQuestFive (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.hasCompletedQuest(player, "quest/wod_gathering_pieces_two") && groundquests.hasCompletedQuest(player, "quest/wod_distorted_visions"))
        {
            return true;
        }
        return false;

}

// ======================================================================
// Script Actions
// ======================================================================

public void wod_drislyn_action_grantQuestDrislynOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_gathering_pieces_one");
        return;
}

// ----------------------------------------------------------------------

public void wod_drislyn_action_sendRewardSignalDrislynOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedGatheringPiecesOne");
}

// ----------------------------------------------------------------------

public void wod_drislyn_action_grantQuestDrislynTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_gathering_pieces_two");
        return;
}

// ----------------------------------------------------------------------

public void wod_drislyn_action_sendRewardSignalDrislynTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedGatheringPiecesTwo");
}

// ----------------------------------------------------------------------

public void wod_drislyn_action_sendRewardSignalQuestFiveTaskTwo (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "giveMidranaPlant");
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

int wod_drislyn_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Ah, I take it you have the hides now?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I do. 
	if (response.equals("s_65"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_sendRewardSignalDrislynTwo (player, npc);

			//-- NPC: I will just be a moment, then you will have what you seek. 
			string_id message = new string_id (c_stringFile, "s_66");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Thank you. 
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_67");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 5);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch5 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I will just be a moment, then you will have what you seek. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Thank you. 
	if (response.equals("s_67"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_sendRewardSignalQuestFiveTaskTwo (player, npc);

			//-- NPC: Now you can thank me. Here is the Midrana. I hope it may help those of the Red Hills Clan.
			string_id message = new string_id (c_stringFile, "s_69");
			utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch8 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: It is good that we are now able to harvest our own plants. However, I still require the hide of some Malklocs. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I will retrieve them. This is the last part, right?
	if (response.equals("s_61"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_grantQuestDrislynTwo (player, npc);

			//-- NPC: This is last 'part' to successfully harvest a plant for you. If that is what you mean. There is no telling when our paths may cross again.
			string_id message = new string_id (c_stringFile, "s_62");
			utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch10 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Do you have the recipe?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I do.
	if (response.equals("s_46"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_sendRewardSignalDrislynOne (player, npc);

			//-- NPC: Good, I take it the Howling Crag Clan Sage was most helpful?
			string_id message = new string_id (c_stringFile, "s_47");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: She was.. I think you should really send one of your scouts to their stronghold.
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_48");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 11);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch11 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Good, I take it the Howling Crag Clan Sage was most helpful?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: She was.. I think you should really send one of your scouts to their stronghold.
	if (response.equals("s_48"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: We will do as you suggest. It has been quite some time since we have heard from them. 
			string_id message = new string_id (c_stringFile, "s_49");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Are you able to give me a Midrana plant now?
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 12);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch12 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We will do as you suggest. It has been quite some time since we have heard from them. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Are you able to give me a Midrana plant now?
	if (response.equals("s_50"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: Unfortunately not. 
			string_id message = new string_id (c_stringFile, "s_51");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: But I thought you said after I retrieved the recipe..
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 13);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch13 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Unfortunately not. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: But I thought you said after I retrieved the recipe..
	if (response.equals("s_52"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: We still need the hide of a Malkloc. I will need you to collect a few so that my clan will not need to hunt for a while. 
			string_id message = new string_id (c_stringFile, "s_53");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: So I'm doing your clan's hunting now too!
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Fine. I will do it.
			boolean hasResponse1 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_54");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_55");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 14);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch14 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We still need the hide of a Malkloc. I will need you to collect a few so that my clan will not need to hunt for a while. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: So I'm doing your clan's hunting now too!
	if (response.equals("s_54"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: I do not need to give you a plant and you cannot acquire one without our assistance. I suggest you adjust your tone and do as you are told.
			string_id message = new string_id (c_stringFile, "s_56");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I will go retrieve those hides.
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_57");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 15);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Fine. I will do it.
	if (response.equals("s_55"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_grantQuestDrislynTwo (player, npc);

			//-- NPC: Good, do not return to me until you do. 
			string_id message = new string_id (c_stringFile, "s_59");
			utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch15 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I do not need to give you a plant and you cannot acquire one without our assistance. I suggest you adjust your tone and do as you are told.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I will go retrieve those hides.
	if (response.equals("s_57"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_grantQuestDrislynTwo (player, npc);

			//-- NPC: That is better. Now go quickly, my patience is wilting like the blades of these Midrana. 
			string_id message = new string_id (c_stringFile, "s_58");
			utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch19 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Foolish being, leave these shores now!

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was sent to these shores by Merilla of the Red Hills Clan.
	if (response.equals("s_7"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: Does Clan Mother Yirra know about this?
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, she is assisting the Clan Mother.
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_11");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 20);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch20 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Does Clan Mother Yirra know about this?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Yes, she is assisting the Clan Mother.
	if (response.equals("s_11"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: What is it that you need? Speak offworlder.
			string_id message = new string_id (c_stringFile, "s_13");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I was sent to collect a Midrana plant. 
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_15");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 21);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch21 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: What is it that you need? Speak offworlder.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was sent to collect a Midrana plant. 
	if (response.equals("s_15"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: There are many Midrana plants right here. However, you cannot collect them.
			string_id message = new string_id (c_stringFile, "s_17");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What? Why can't I?
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_19");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 22);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch22 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: There are many Midrana plants right here. However, you cannot collect them.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What? Why can't I?
	if (response.equals("s_19"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: The plant is very intricate and requires careful removal. Only certain witch clans are successful at it. Others, such as yourself would watch it crumble in your hands. 
			string_id message = new string_id (c_stringFile, "s_21");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Would you collect one for me?
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: That is rather convenient. How do I know you're telling the truth?
			boolean hasResponse1 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_23");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_35");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 23);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch23 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: The plant is very intricate and requires careful removal. Only certain witch clans are successful at it. Others, such as yourself would watch it crumble in your hands. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Would you collect one for me?
	if (response.equals("s_23"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: Yes, but first I require you to do some tasks for me.
			string_id message = new string_id (c_stringFile, "s_25");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What is it you need done?
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 24);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: That is rather convenient. How do I know you're telling the truth?
	if (response.equals("s_35"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: Insolent fool! The Midrana is a creation by the early Witches of Dathomir. I have no reason to lie to you and I would let you attempt yourself, but these plants are not cultivated overnight. I will gather you one for the Red Hills Clan. But, first you must do some tasks for me. 
			string_id message = new string_id (c_stringFile, "s_37");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What is it that you need?
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_39");

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 27);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch24 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Yes, but first I require you to do some tasks for me.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What is it you need done?
	if (response.equals("s_27"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			//-- NPC: I require you to go to the Howling Crag Clan and retrieve the recipe from the Clan Mother or Sage. They have been unresponsive recently and they typically are the ones who process the plant for us. 
			string_id message = new string_id (c_stringFile, "s_29");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: The Howling Crag Clan is no more. 
			boolean hasResponse0 = false;
			if (wod_drislyn_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 25);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch25 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I require you to go to the Howling Crag Clan and retrieve the recipe from the Clan Mother or Sage. They have been unresponsive recently and they typically are the ones who process the plant for us. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: The Howling Crag Clan is no more. 
	if (response.equals("s_31"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_grantQuestDrislynOne (player, npc);

			//-- NPC: How dare you! Go there and retrieve the recipe. Do not speak to me again until you do. 
			string_id message = new string_id (c_stringFile, "s_33");
			utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_drislyn_handleBranch27 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Insolent fool! The Midrana is a creation by the early Witches of Dathomir. I have no reason to lie to you and I would let you attempt yourself, but these plants are not cultivated overnight. I will gather you one for the Red Hills Clan. But, first you must do some tasks for me. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What is it that you need?
	if (response.equals("s_39"))
	{
		//-- [NOTE] 
		if (wod_drislyn_condition__defaultCondition (player, npc))
		{
			wod_drislyn_action_grantQuestDrislynOne (player, npc);

			//-- NPC: You are to go here and retrieve the recipe for processing the Midrana. Normally we do not process the plant ourselves, but we have not heard word from our fellow sisters in quite some time. 
			string_id message = new string_id (c_stringFile, "s_41");
			utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

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
		detachScript(self, "conversation.wod_drislyn");
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
	detachScript (self, "conversation.wod_drislyn");

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
	if (wod_drislyn_condition_finishedDrislynQuestTwoAndQuestFive (player, npc))
	{
		//-- NPC: You were most helpful. 
		string_id message = new string_id (c_stringFile, "s_72");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_finishedDrislynQuestTwoNotQuestFive (player, npc))
	{
		//-- NPC: You were most helpful. You should return to the Red Hills Clan.
		string_id message = new string_id (c_stringFile, "s_71");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_finishedDrislynQuestTwoNoPlant (player, npc))
	{
		wod_drislyn_action_sendRewardSignalQuestFiveTaskTwo (player, npc);

		//-- NPC: You have done well, here is the plant you seek. I hope it may help the Red Hills Clan.
		string_id message = new string_id (c_stringFile, "s_70");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_readyForRewardDrislynTwo (player, npc))
	{
		//-- NPC: Ah, I take it you have the hides now?
		string_id message = new string_id (c_stringFile, "s_64");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I do. 
		boolean hasResponse0 = false;
		if (wod_drislyn_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_65");

			utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 4);

			npcStartConversation (player, npc, "wod_drislyn", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_isOnQuestDrislynTwo (player, npc))
	{
		//-- NPC: Do you offworlders just like to stand around and talk? You have hides to retrieve. Go now!
		string_id message = new string_id (c_stringFile, "s_63");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_finishedDrislynQuestOne (player, npc))
	{
		//-- NPC: It is good that we are now able to harvest our own plants. However, I still require the hide of some Malklocs. 
		string_id message = new string_id (c_stringFile, "s_60");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I will retrieve them. This is the last part, right?
		boolean hasResponse0 = false;
		if (wod_drislyn_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_61");

			utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 8);

			npcStartConversation (player, npc, "wod_drislyn", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_readyForRewardDrislynOne (player, npc))
	{
		//-- NPC: Do you have the recipe?
		string_id message = new string_id (c_stringFile, "s_45");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I do.
		boolean hasResponse0 = false;
		if (wod_drislyn_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 10);

			npcStartConversation (player, npc, "wod_drislyn", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_isOnQuestDrislynOne (player, npc))
	{
		//-- NPC: I have given you a task. I expect it done.
		string_id message = new string_id (c_stringFile, "s_43");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition_isOnQuestFiveTaskTwo (player, npc))
	{
		//-- NPC: Foolish being, leave these shores now!
		string_id message = new string_id (c_stringFile, "s_5");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I was sent to these shores by Merilla of the Red Hills Clan.
		boolean hasResponse0 = false;
		if (wod_drislyn_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_7");

			utils.setScriptVar (player, "conversation.wod_drislyn.branchId", 19);

			npcStartConversation (player, npc, "wod_drislyn", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_drislyn_condition__defaultCondition (player, npc))
	{
		//-- NPC: Leave these shores now!
		string_id message = new string_id (c_stringFile, "s_44");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	chat.chat (npc, "Error:  All conditions for OnStartNpcConversation were false.");

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
{
	if (!conversationId.equals("wod_drislyn"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wod_drislyn.branchId");

	if (branchId == 4 && wod_drislyn_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 5 && wod_drislyn_handleBranch5 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 8 && wod_drislyn_handleBranch8 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 10 && wod_drislyn_handleBranch10 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 11 && wod_drislyn_handleBranch11 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 12 && wod_drislyn_handleBranch12 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 13 && wod_drislyn_handleBranch13 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 14 && wod_drislyn_handleBranch14 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 15 && wod_drislyn_handleBranch15 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 19 && wod_drislyn_handleBranch19 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 20 && wod_drislyn_handleBranch20 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 21 && wod_drislyn_handleBranch21 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 22 && wod_drislyn_handleBranch22 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 23 && wod_drislyn_handleBranch23 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 24 && wod_drislyn_handleBranch24 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 25 && wod_drislyn_handleBranch25 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 27 && wod_drislyn_handleBranch27 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wod_drislyn.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}