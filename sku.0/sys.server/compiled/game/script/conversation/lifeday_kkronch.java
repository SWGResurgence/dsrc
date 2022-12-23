// ======================================================================
//
// lifeday_kkronch.java
// Copyright 2004-2020, Sony Online Entertainment
// All Rights Reserved.
//
// Created with SwgConversationEditor 1.37 - DO NOT EDIT THIS AUTO-GENERATED FILE!
//Roachie says herro UwU
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

public class lifeday_kkronch extends script.base_script
{
public static String c_stringFile = "conversation/lifeday_kkronch";

// ======================================================================
// Script Constants
// ======================================================================

	public lifeday_kkronch()

	{

	}

// ======================================================================
// Script Conditions
// ======================================================================

public boolean lifeday_kkronch_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ======================================================================
// Script Actions
// ======================================================================

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

int lifeday_kkronch_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Happy Life Day to you!

	//-- [RESPONSE NOTE] 
	//-- PLAYER: And to you! I hope you are having a great time my furry friend.
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (lifeday_kkronch_condition__defaultCondition (player, npc))
		{
			//-- NPC: I would be having a much better time if I had my snacks. I lost it on a supply run to Doaba Guerfel..
			string_id message = new string_id (c_stringFile, "s_5");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I am sorry to hear that. I must be going.
			boolean hasResponse0 = false;
			if (lifeday_kkronch_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I can help you find your snacks!
			boolean hasResponse1 = false;
			if (lifeday_kkronch_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_7");

				utils.setScriptVar (player, "conversation.lifeday_kkronch.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.lifeday_kkronch.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int lifeday_kkronch_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I would be having a much better time if I had my snacks. I lost it on a supply run to Doaba Guerfel..

	//-- [RESPONSE NOTE] null, end convo
	//-- PLAYER: I am sorry to hear that. I must be going.
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (lifeday_kkronch_condition__defaultCondition (player, npc))
		{
			//-- NPC: Happy Life Day to you!
			string_id message = new string_id (c_stringFile, "s_3");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: And to you! I hope you are having a great time my furry friend.
			boolean hasResponse0 = false;
			if (lifeday_kkronch_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.lifeday_kkronch.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.lifeday_kkronch.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I can help you find your snacks!
	if (response.equals("s_7"))
	{
		//-- [NOTE] end convo, but grant quest
		if (lifeday_kkronch_condition__defaultCondition (player, npc))
		{
			//-- NPC: Wonderful! Head to Doaba Guerfel and look around. It's a nice big canister.
			string_id message = new string_id (c_stringFile, "s_8");
			utils.removeScriptVar (player, "conversation.lifeday_kkronch.branchId");
			groundquests.grantQuest(player, "quest/lifeday_kkronch");
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
		detachScript(self, "conversation.lifeday_kkronch");
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
	detachScript (self, "conversation.lifeday_kkronch");

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

	if (ai_lib.isInCombat (npc) || ai_lib.isInCombat (player))
		return SCRIPT_OVERRIDE;

	//-- [NOTE] 
	if (lifeday_kkronch_condition__defaultCondition (player, npc))
	{
		//-- NPC: Happy Life Day to you!
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: And to you! I hope you are having a great time my furry friend.
		boolean hasResponse0 = false;
		if (lifeday_kkronch_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.lifeday_kkronch.branchId", 1);

			npcStartConversation (player, npc, "lifeday_kkronch", message, responses);
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
	if (!conversationId.equals("lifeday_kkronch"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.lifeday_kkronch.branchId");

	if (branchId == 1 && lifeday_kkronch_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 2 && lifeday_kkronch_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.lifeday_kkronch.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}