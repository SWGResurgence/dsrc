// ======================================================================
//
// wod_yirra.java
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

public class wod_yirra extends script.base_script
{
	public wod_yirra()
	{
	}
// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wod_yirra";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wod_yirra_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_isOnPreQuestSM (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isTaskActive(player, "quest/wod_arlin_goto_rh", "talkToYirra"))
        {
            if (badge.hasBadge(player, "bdg_wod_sm_trust") && badge.hasBadge(player, "bdg_wod_sm_spider_quests") && badge.hasBadge(player, "bdg_wod_sm_rancor_quests") && !badge.hasBadge(player, "bdg_wod_ns_trust") && !badge.hasBadge(player, "bdg_wod_ns_spider_quests") && !badge.hasBadge(player, "bdg_wod_ns_rancor_quests"))
            {
                return true;
            }
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_isOnPreQuestNS (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isTaskActive(player, "quest/wod_arlin_goto_rh", "talkToYirra"))
        {
            if (badge.hasBadge(player, "bdg_wod_ns_trust") && badge.hasBadge(player, "bdg_wod_ns_spider_quests") && badge.hasBadge(player, "bdg_wod_ns_rancor_quests") && !badge.hasBadge(player, "bdg_wod_sm_trust") && !badge.hasBadge(player, "bdg_wod_sm_spider_quests") && !badge.hasBadge(player, "bdg_wod_sm_rancor_quests"))
            {
                return true;
            }
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_isOnPreQuestBoth (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isTaskActive(player, "quest/wod_arlin_goto_rh", "talkToYirra"))
        {
            if (badge.hasBadge(player, "bdg_wod_ns_trust") && badge.hasBadge(player, "bdg_wod_ns_spider_quests") && badge.hasBadge(player, "bdg_wod_ns_rancor_quests") && badge.hasBadge(player, "bdg_wod_sm_trust") && badge.hasBadge(player, "bdg_wod_sm_spider_quests") && badge.hasBadge(player, "bdg_wod_sm_rancor_quests"))
            {
                return true;
            }
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_isOnQuestOne (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_seeking_retribution"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_finishedQuestOne (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_seeking_retribution"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_readyForRewardOne (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_seeking_retribution", "returnYirraOne"))
        {
            return true;
        }
        return false;

}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_finishedPreQuest (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_arlin_goto_rh"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_finishedKyreneQuest (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_interfering_forces_2"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_isOnQuestFour (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.isQuestActive(player, "quest/wod_trail_of_discovery"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_finishedQuestFour (obj_id player, obj_id npc) throws InterruptedException
{
        if (groundquests.hasCompletedQuest(player, "quest/wod_trail_of_discovery"))
        {
            return true;
        }
        return false;
}

// ----------------------------------------------------------------------

public boolean wod_yirra_condition_readyForRewardFour (obj_id player, obj_id npc) throws InterruptedException
{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/wod_trail_of_discovery", "returnYirraTwo"))
        {
            return true;
        }
        return false;

}

// ======================================================================
// Script Actions
// ======================================================================

public void wod_yirra_action_sendRewardSignalPreQ (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedGotoRedHillsStronghold");
}

// ----------------------------------------------------------------------

public void wod_yirra_action_grantQuestOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_seeking_retribution");
        return;
}

// ----------------------------------------------------------------------

public void wod_yirra_action_sendRewardSignalOne (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedSeekingRetribution");
}

// ----------------------------------------------------------------------

public void wod_yirra_action_grantQuestFour (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.grantQuest(player, "quest/wod_trail_of_discovery");
        return;
}

// ----------------------------------------------------------------------

public void wod_yirra_action_sendRewardSignalFour (obj_id player, obj_id npc) throws InterruptedException
{
        groundquests.sendSignal(player, "completedTrailofDiscovery");
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

int wod_yirra_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You have returned and I sense you have found something. What is it that you've found?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Well I found a lot more than I expected at the ruins..
	if (response.equals("s_102"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: Go on.
			string_id message = new string_id (c_stringFile, "s_103");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: The Sith Shadows were there and setup an ambush.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch3 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Go on.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: The Sith Shadows were there and setup an ambush.
	if (response.equals("s_104"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: You must have handled yourself well. You are here and have something in your possession, do you not?
			string_id message = new string_id (c_stringFile, "s_105");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: After defeating one of their leaders I found these fragments. I am not sure if they will be of any use. 
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_106");

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 4);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You must have handled yourself well. You are here and have something in your possession, do you not?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: After defeating one of their leaders I found these fragments. I am not sure if they will be of any use. 
	if (response.equals("s_106"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			doAnimationAction (npc, "rub_chin_thoughtful");

			wod_yirra_action_sendRewardSignalFour (player, npc);

			//-- NPC: I am not sure yet. Though this does seem rather promising. It will take time to see if anything can be salvaged. Go speak with Sage Merillia for the time being. Once you finish her tasks then you may return to me.
			string_id message = new string_id (c_stringFile, "s_107");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You've accomplished Huntress Kyrene's tasks. I am surprised, you have proven your worth.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have. What am I to do next?
	if (response.equals("s_84"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			wod_yirra_action_grantQuestFour (player, npc);

			//-- NPC: You are to go to the ruins of the Jedi Temple on Dantooine. You will see what you can find about Allya.
			string_id message = new string_id (c_stringFile, "s_90");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Was there ever any doubt?
	if (response.equals("s_85"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: Do not push it, %TU. You still have a lot to learn. While you were helping Kyrene, I discovered something. 
			string_id message = new string_id (c_stringFile, "s_86");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What did you discover?
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_87");

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 9);

				prose_package pp = new prose_package ();
				pp.stringId = message;
				pp.actor.set (player);
				pp.target.set (npc);

				npcSpeak (player, pp);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				prose_package pp = new prose_package ();
				pp.stringId = message;
				pp.actor.set (player);
				pp.target.set (npc);

				npcEndConversationWithMessage (player, pp);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch9 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Do not push it, %TU. You still have a lot to learn. While you were helping Kyrene, I discovered something. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What did you discover?
	if (response.equals("s_87"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			wod_yirra_action_grantQuestFour (player, npc);

			//-- NPC: I discovered there are some ruins of a Jedi Temple on Dantooine. I believe you should be able to recover some information on Allya. You will go there and see what you can find.
			string_id message = new string_id (c_stringFile, "s_88");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch12 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You have handled those Nightsisters? I am correct, am I not, %TU?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: You are. I've eliminated the small group of Nightsisters as you requested. 
	if (response.equals("s_63"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: I am most pleased. I have some matters to attend to. Go speak with Huntress Kyrene. She will have some tasks for you.
			string_id message = new string_id (c_stringFile, "s_64");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch14 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Have you dealt with those Nightsisters?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have not.
	if (response.equals("s_60"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: As I have sensed, %TU. Dispatch them without haste and do not bother me until you have.
			string_id message = new string_id (c_stringFile, "s_61");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

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

int wod_yirra_handleBranch16 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: So you have decided to assist us, %TU?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have.
	if (response.equals("s_74"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			wod_yirra_action_grantQuestOne (player, npc);

			//-- NPC: Excellent. There is a small Nightsister outpost near our stronghold. I would like you to go there and return the favor that Gethzerion has so graciously given my scouts. 
			string_id message = new string_id (c_stringFile, "s_75");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I haven't decided yet.
	if (response.equals("s_76"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: Begone from my sight! Return to me only when you're able to be of more use.
			string_id message = new string_id (c_stringFile, "s_77");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch19 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: It is a tumultuous time. Gethzerion's Nightsisters are growing far too powerful. Word has travelled from Rubina herself that you have aided both the Nightsisters and Singing Mountain Clan in crippling the Spider Clan and forcing that outcast, Kyrisa into hiding. Why are you here Outworlder?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I encountered someone who used to be a slave of your clan. I have this for you.
	if (response.equals("s_34"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: This belonged to Eiren... 
			string_id message = new string_id (c_stringFile, "s_36");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'm sorry. I know where she's located. She was already struck down by the time I found her.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 20);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch20 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: This belonged to Eiren... 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'm sorry. I know where she's located. She was already struck down by the time I found her.
	if (response.equals("s_38"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: Who did this! I demand to know. 
			string_id message = new string_id (c_stringFile, "s_40");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I was ambushed by Nightsisters when I found her.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_42");

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 21);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch21 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Who did this! I demand to know. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was ambushed by Nightsisters when I found her.
	if (response.equals("s_42"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: I knew my seekers shouldn't have gone so close to Gethzerion's stronghold. They were determined to find out why so many starships have inexplicably crashed or were forced to land in the jungle.
			string_id message = new string_id (c_stringFile, "s_44");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Is there anything that I can do to help?
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 22);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch22 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I knew my seekers shouldn't have gone so close to Gethzerion's stronghold. They were determined to find out why so many starships have inexplicably crashed or were forced to land in the jungle.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Is there anything that I can do to help?
	if (response.equals("s_46"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			wod_yirra_action_grantQuestOne (player, npc);

			//-- NPC: Actually yes, %TU. There is a small Nightsister outpost near our stronghold. I would like you to go there and return the favor that Gethzerion has so graciously given my scouts. 
			string_id message = new string_id (c_stringFile, "s_48");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

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

int wod_yirra_handleBranch24 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: It is a tumultuous time. Gethzerion's Nightsisters are growing far too powerful. Word has travelled from the Singing Mountain that you helped Aujante K'Lee in crippling the Spider Clan and forcing that outcast, Kyrisa into hiding. Why are you here Outworlder?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I encountered someone who used to be a slave of your clan. I have this for you.
	if (response.equals("s_52"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: This belonged to Eiren... 
			string_id message = new string_id (c_stringFile, "s_54");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'm sorry. I know where she's located. She was already struck down by the time I found her.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 25);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch25 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: This belonged to Eiren... 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'm sorry. I know where she's located. She was already struck down by the time I found her.
	if (response.equals("s_56"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: Who did this! I demand to know. 
			string_id message = new string_id (c_stringFile, "s_58");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I was ambushed by Nightsisters when I found her.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 26);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch26 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Who did this! I demand to know. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was ambushed by Nightsisters when I found her.
	if (response.equals("s_66"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: I knew my seekers shouldn't have gone so close to Gethzerion's stronghold. They were determined to find out why so many starships have inexplicably crashed or were forced to land in the jungle.
			string_id message = new string_id (c_stringFile, "s_68");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Is there anything that I can do to help?
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 27);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch27 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I knew my seekers shouldn't have gone so close to Gethzerion's stronghold. They were determined to find out why so many starships have inexplicably crashed or were forced to land in the jungle.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Is there anything that I can do to help?
	if (response.equals("s_70"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			wod_yirra_action_grantQuestOne (player, npc);

			//-- NPC: Actually yes, %TU. There is a small Nightsister outpost near our stronghold. I would like you to go there and return the favor that Gethzerion has so graciously given my scouts. 
			string_id message = new string_id (c_stringFile, "s_78");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

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

int wod_yirra_handleBranch29 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: It is a tumultuous time. Gethzerion's Nightsisters are growing far too powerful. Word has travelled from the Singing Mountain that you helped Gethzerion in crippling the Spider Clan and forcing that outcast, Kyrisa into hiding. Why are you here Outworlder?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I encountered someone who used to be a slave of your clan. I have this for you.
	if (response.equals("s_82"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: This belonged to Eiren...
			string_id message = new string_id (c_stringFile, "s_93");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'm sorry. I know where she's located. She was already struck down by the time I found her.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 30);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch30 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: This belonged to Eiren...

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'm sorry. I know where she's located. She was already struck down by the time I found her.
	if (response.equals("s_95"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: Who did this! I demand to know. 
			string_id message = new string_id (c_stringFile, "s_97");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I was ambushed by Nightsisters when I found her.
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_99");

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 31);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch31 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Who did this! I demand to know. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I was ambushed by Nightsisters when I found her.
	if (response.equals("s_99"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			//-- NPC: I knew my seekers shouldn't have gone so close to Gethzerion's stronghold. They were determined to find out why so many starships have inexplicably crashed or were forced to land in the jungle.
			string_id message = new string_id (c_stringFile, "s_108");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Is there anything that I can do to help?
			boolean hasResponse0 = false;
			if (wod_yirra_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_110");

				utils.setScriptVar (player, "conversation.wod_yirra.branchId", 32);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_yirra_handleBranch32 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I knew my seekers shouldn't have gone so close to Gethzerion's stronghold. They were determined to find out why so many starships have inexplicably crashed or were forced to land in the jungle.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Is there anything that I can do to help?
	if (response.equals("s_110"))
	{
		//-- [NOTE] 
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			wod_yirra_action_grantQuestOne (player, npc);

			//-- NPC: Actually yes, %TU. There is a small Nightsister outpost near our stronghold. I would like you to go there and return the favor that Gethzerion has so graciously given my scouts. 
			string_id message = new string_id (c_stringFile, "s_112");
			utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

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

// ======================================================================
// User Script Triggers
// ======================================================================

public int OnInitialize(obj_id self) throws InterruptedException
{
	if ((!isMob (self)) || (isPlayer (self)))
	{
		detachScript(self, "conversation.wod_yirra");
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
	detachScript (self, "conversation.wod_yirra");

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
	if (wod_yirra_condition_finishedQuestFour (player, npc))
	{
		//-- NPC: Speak with Sage Merilla. She has some tasks that you will do until I am ready for you again.
		string_id message = new string_id (c_stringFile, "s_92");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_readyForRewardFour (player, npc))
	{
		//-- NPC: You have returned and I sense you have found something. What is it that you've found?
		string_id message = new string_id (c_stringFile, "s_101");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Well I found a lot more than I expected at the ruins..
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 2);

			npcStartConversation (player, npc, "wod_yirra", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_isOnQuestFour (player, npc))
	{
		//-- NPC: Only return to me once you have gone to the temple ruins and recovered what information you can on Allya.
		string_id message = new string_id (c_stringFile, "s_91");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_finishedKyreneQuest (player, npc))
	{
		//-- NPC: You've accomplished Huntress Kyrene's tasks. I am surprised, you have proven your worth.
		string_id message = new string_id (c_stringFile, "s_83");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have. What am I to do next?
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: Was there ever any doubt?
		boolean hasResponse1 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_84");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_85");

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 7);

			npcStartConversation (player, npc, "wod_yirra", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_finishedQuestOne (player, npc))
	{
		//-- NPC: Go speak with Huntress Kyrene. She will have some tasks for you.
		string_id message = new string_id (c_stringFile, "s_71");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_readyForRewardOne (player, npc))
	{
		wod_yirra_action_sendRewardSignalOne (player, npc);

		//-- NPC: You have handled those Nightsisters? I am correct, am I not, %TU?
		string_id message = new string_id (c_stringFile, "s_62");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: You are. I've eliminated the small group of Nightsisters as you requested. 
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_63");

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 12);

			prose_package pp = new prose_package ();
			pp.stringId = message;
			pp.actor.set (player);
			pp.target.set (npc);

			npcStartConversation (player, npc, "wod_yirra", null, pp, responses);
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
	if (wod_yirra_condition_isOnQuestOne (player, npc))
	{
		//-- NPC: Have you dealt with those Nightsisters?
		string_id message = new string_id (c_stringFile, "s_59");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have not.
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 14);

			npcStartConversation (player, npc, "wod_yirra", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_finishedPreQuest (player, npc))
	{
		//-- NPC: So you have decided to assist us, %TU?
		string_id message = new string_id (c_stringFile, "s_73");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have.
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: I haven't decided yet.
		boolean hasResponse1 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_74");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_76");

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 16);

			prose_package pp = new prose_package ();
			pp.stringId = message;
			pp.actor.set (player);
			pp.target.set (npc);

			npcStartConversation (player, npc, "wod_yirra", null, pp, responses);
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
	if (wod_yirra_condition_isOnPreQuestBoth (player, npc))
	{
		wod_yirra_action_sendRewardSignalPreQ (player, npc);

		//-- NPC: It is a tumultuous time. Gethzerion's Nightsisters are growing far too powerful. Word has travelled from Rubina herself that you have aided both the Nightsisters and Singing Mountain Clan in crippling the Spider Clan and forcing that outcast, Kyrisa into hiding. Why are you here Outworlder?
		string_id message = new string_id (c_stringFile, "s_33");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I encountered someone who used to be a slave of your clan. I have this for you.
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_34");

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 19);

			npcStartConversation (player, npc, "wod_yirra", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_isOnPreQuestSM (player, npc))
	{
		wod_yirra_action_sendRewardSignalPreQ (player, npc);

		//-- NPC: It is a tumultuous time. Gethzerion's Nightsisters are growing far too powerful. Word has travelled from the Singing Mountain that you helped Aujante K'Lee in crippling the Spider Clan and forcing that outcast, Kyrisa into hiding. Why are you here Outworlder?
		string_id message = new string_id (c_stringFile, "s_50");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I encountered someone who used to be a slave of your clan. I have this for you.
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 24);

			npcStartConversation (player, npc, "wod_yirra", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition_isOnPreQuestNS (player, npc))
	{
		wod_yirra_action_sendRewardSignalPreQ (player, npc);

		//-- NPC: It is a tumultuous time. Gethzerion's Nightsisters are growing far too powerful. Word has travelled from the Singing Mountain that you helped Gethzerion in crippling the Spider Clan and forcing that outcast, Kyrisa into hiding. Why are you here Outworlder?
		string_id message = new string_id (c_stringFile, "s_80");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I encountered someone who used to be a slave of your clan. I have this for you.
		boolean hasResponse0 = false;
		if (wod_yirra_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_82");

			utils.setScriptVar (player, "conversation.wod_yirra.branchId", 29);

			npcStartConversation (player, npc, "wod_yirra", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (wod_yirra_condition__defaultCondition (player, npc))
	{
		//-- NPC: Leave now! Forget this place exists.
		string_id message = new string_id (c_stringFile, "s_114");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	chat.chat (npc, "Error:  All conditions for OnStartNpcConversation were false.");

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
{
	if (!conversationId.equals("wod_yirra"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wod_yirra.branchId");

	if (branchId == 2 && wod_yirra_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 3 && wod_yirra_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 4 && wod_yirra_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 7 && wod_yirra_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 9 && wod_yirra_handleBranch9 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 12 && wod_yirra_handleBranch12 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 14 && wod_yirra_handleBranch14 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 16 && wod_yirra_handleBranch16 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 19 && wod_yirra_handleBranch19 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 20 && wod_yirra_handleBranch20 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 21 && wod_yirra_handleBranch21 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 22 && wod_yirra_handleBranch22 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 24 && wod_yirra_handleBranch24 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 25 && wod_yirra_handleBranch25 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 26 && wod_yirra_handleBranch26 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 27 && wod_yirra_handleBranch27 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 29 && wod_yirra_handleBranch29 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 30 && wod_yirra_handleBranch30 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 31 && wod_yirra_handleBranch31 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 32 && wod_yirra_handleBranch32 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wod_yirra.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}