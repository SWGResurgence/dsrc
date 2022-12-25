// ======================================================================
//
// janshai_mistsplitter.java
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

public class janshai_mistsplitter extends script.base_script
{


// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/janshai_mistsplitter";

// ======================================================================
// Script Conditions
// ======================================================================

	public boolean janshai_mistsplitter_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
	{
		return true;
	}

// ----------------------------------------------------------------------

	public boolean janshai_mistsplitter_condition_readyForReward (obj_id player, obj_id npc) throws InterruptedException
	{
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/world_boss_tatooine_krayt", "returnJanshaiMistsplitter"))
        {
            return true;
        }
        return false;
	}
// ----------------------------------------------------------------------

	public boolean janshai_mistsplitter_condition_isOnQuest (obj_id player, obj_id npc) throws InterruptedException
	{
        if (groundquests.isQuestActive(player, "quest/world_boss_tatooine_krayt"))
        {
            return true;
        }
        return false;
	}

// ======================================================================
// Script Actions
// ======================================================================

	public void janshai_mistsplitter_action_sendRewardSignal (obj_id player, obj_id npc) throws InterruptedException
	{
        groundquests.sendSignal(player, "completedWorldBossTatooineKrayt");
    }

// ----------------------------------------------------------------------

	public void janshai_mistsplitter_action_grantQuest (obj_id player, obj_id npc) throws InterruptedException
	{
        groundquests.grantQuest(player, "quest/world_boss_tatooine_krayt");
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

int janshai_mistsplitter_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Hey, I heard that you helped Dr. Skyann Langen?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Yep, I did.
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (janshai_mistsplitter_condition__defaultCondition (player, npc))
		{
			//-- NPC: Good! It's good to know that the rumors are true. 
			string_id message = new string_id (c_stringFile, "s_5");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: And, what is that supposed to mean?
			boolean hasResponse0 = false;
			if (janshai_mistsplitter_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.janshai_mistsplitter.branchId", 5);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.janshai_mistsplitter.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int janshai_mistsplitter_handleBranch5 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Good! It's good to know that the rumors are true. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: And, what is that supposed to mean?
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (janshai_mistsplitter_condition__defaultCondition (player, npc))
		{
			//-- NPC: Just that you might be just the person I'm looking for to get my research off the ground.
			string_id message = new string_id (c_stringFile, "s_7");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What kind of research?
			boolean hasResponse0 = false;
			if (janshai_mistsplitter_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.janshai_mistsplitter.branchId", 6);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.janshai_mistsplitter.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int janshai_mistsplitter_handleBranch6 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Just that you might be just the person I'm looking for to get my research off the ground.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What kind of research?
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (janshai_mistsplitter_condition__defaultCondition (player, npc))
		{
			//-- NPC: Be careful what you ask for, Czerka has a tendency to make people disappear for asking too many questions. But, I suppose it's natural to be curious. My research involves Krayt Dragons. Specifically, the legendary Elder Ancient Krayt Dragon.
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Okay, what do you need from it?
			boolean hasResponse0 = false;
			if (janshai_mistsplitter_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.janshai_mistsplitter.branchId", 7);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.janshai_mistsplitter.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int janshai_mistsplitter_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Be careful what you ask for, Czerka has a tendency to make people disappear for asking too many questions. But, I suppose it's natural to be curious. My research involves Krayt Dragons. Specifically, the legendary Elder Ancient Krayt Dragon.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Okay, what do you need from it?
	if (response.equals("s_10"))
	{
		//-- [NOTE] 
		if (janshai_mistsplitter_condition__defaultCondition (player, npc))
		{
			//-- NPC: Unfortunately, you will need to kill it and bring me a biological sample from it's remains. You should plan on bringing friends with you.
			string_id message = new string_id (c_stringFile, "s_11");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Fair enough, you just want a biological sample?
			boolean hasResponse0 = false;
			if (janshai_mistsplitter_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.janshai_mistsplitter.branchId", 8);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.janshai_mistsplitter.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int janshai_mistsplitter_handleBranch8 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Unfortunately, you will need to kill it and bring me a biological sample from it's remains. You should plan on bringing friends with you.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Fair enough, you just want a biological sample?
	if (response.equals("s_12"))
	{
		//-- [NOTE] 
		if (janshai_mistsplitter_condition__defaultCondition (player, npc))
		{
			janshai_mistsplitter_action_grantQuest (player, npc);

			//-- NPC: I need a blood sample, skin sample and one of it's claws... so get out there unless you don't want to get paid.
			string_id message = new string_id (c_stringFile, "s_13");
			utils.removeScriptVar (player, "conversation.janshai_mistsplitter.branchId");

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
			detachScript(self, "conversation.janshai_mistsplitter");
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
	detachScript (self, "conversation.janshai_mistsplitter");

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
	if (janshai_mistsplitter_condition_readyForReward (player, npc))
	{
		janshai_mistsplitter_action_sendRewardSignal (player, npc);

		//-- NPC: I see you're still alive and you have what I asked for. Here's the reward I promised. 
		string_id message = new string_id (c_stringFile, "s_14");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (janshai_mistsplitter_condition_isOnQuest (player, npc))
	{
		//-- NPC: You handled yourself pretty well. I've sent a few spacers that way before you came along and needless to say none have returned. Don't be a stranger, I may have more jobs for you.
		string_id message = new string_id (c_stringFile, "s_16");
		chat.chat (npc, player, message);

		return SCRIPT_CONTINUE;
	}

	//-- [NOTE] 
	if (janshai_mistsplitter_condition__defaultCondition (player, npc))
	{
		//-- NPC: Hey, I heard that you helped Dr. Skyann Langen?
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Yep, I did.
		boolean hasResponse0 = false;
		if (janshai_mistsplitter_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.janshai_mistsplitter.branchId", 4);

			npcStartConversation (player, npc, "janshai_mistsplitter", message, responses);
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
	if (!conversationId.equals("janshai_mistsplitter"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.janshai_mistsplitter.branchId");

	if (branchId == 4 && janshai_mistsplitter_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 5 && janshai_mistsplitter_handleBranch5 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 6 && janshai_mistsplitter_handleBranch6 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 7 && janshai_mistsplitter_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 8 && janshai_mistsplitter_handleBranch8 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.janshai_mistsplitter.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}