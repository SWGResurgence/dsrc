// ======================================================================
//
// wod_silas_hallen.java
// Copyright 2004, Sony Online Entertainment
// All Rights Reserved.
//
// Created with SwgConversationEditor 1.37 - DO NOT EDIT THIS AUTO-GENERATED FILE!
// blour says hi
// ======================================================================

package script.conversation;

// ======================================================================
// Library Includes
// ======================================================================

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.utils;
import script.*;

public class wod_silas_hallen extends script.base_script
{
	public wod_silas_hallen()
	{
	}
// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wod_silas_hallen";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wod_silas_hallen_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
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

int wod_silas_hallen_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You were able to escape from the sisters, too? What? You're from the stars!? Then maybe you might be able to help! The Nightsisters chose the strongest of their slaves and force them to work the mines! I managed to escape and search for help, for we cannot save ourselves! Will you please help us gain our freedom?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Yes, I'll definitely help!
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: Oh, thank you! Here is where the mines are. We're trapped down deep inside the mines, and the Nightsisters oversee everything we do. If you're going to help us, you will need to bring help of your own. I look forward to seeing my friends again! Thank you so much.
			string_id message = new string_id (c_stringFile, "s_9");
			utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I cannot help you.
	if (response.equals("s_5"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: I guess I understand. The Nightsisters are something no one should have to face in a battle. I will be off to try to find someone who is able to help.
			string_id message = new string_id (c_stringFile, "s_7");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, I'll definitely help!
			boolean hasResponse0 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I cannot help you.
			boolean hasResponse1 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Why don't you ask the Empire for help?
			boolean hasResponse2 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why don't you ask the Empire for help?
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: They were the first people I sought out. The Empire told me that they would prefer to leave well enough alone, and that they didn't care about a few escaped slaves. Blast the Empire! They are no better than the Nightsisters! Will you help us, please?
			string_id message = new string_id (c_stringFile, "s_8");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, I'll definitely help!
			boolean hasResponse0 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I cannot help you.
			boolean hasResponse1 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Why don't you ask the Empire for help?
			boolean hasResponse2 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_silas_hallen_handleBranch3 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I guess I understand. The Nightsisters are something no one should have to face in a battle. I will be off to try to find someone who is able to help.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Yes, I'll definitely help!
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: Oh, thank you! Here is where the mines are. We're trapped down deep inside the mines, and the Nightsisters oversee everything we do. If you're going to help us, you will need to bring help of your own. I look forward to seeing my friends again! Thank you so much.
			string_id message = new string_id (c_stringFile, "s_9");
			utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I cannot help you.
	if (response.equals("s_5"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: I guess I understand. The Nightsisters are something no one should have to face in a battle. I will be off to try to find someone who is able to help.
			string_id message = new string_id (c_stringFile, "s_7");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, I'll definitely help!
			boolean hasResponse0 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I cannot help you.
			boolean hasResponse1 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Why don't you ask the Empire for help?
			boolean hasResponse2 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why don't you ask the Empire for help?
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: They were the first people I sought out. The Empire told me that they would prefer to leave well enough alone, and that they didn't care about a few escaped slaves. Blast the Empire! They are no better than the Nightsisters! Will you help us, please?
			string_id message = new string_id (c_stringFile, "s_8");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, I'll definitely help!
			boolean hasResponse0 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I cannot help you.
			boolean hasResponse1 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Why don't you ask the Empire for help?
			boolean hasResponse2 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wod_silas_hallen_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: They were the first people I sought out. The Empire told me that they would prefer to leave well enough alone, and that they didn't care about a few escaped slaves. Blast the Empire! They are no better than the Nightsisters! Will you help us, please?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Yes, I'll definitely help!
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: Oh, thank you! Here is where the mines are. We're trapped down deep inside the mines, and the Nightsisters oversee everything we do. If you're going to help us, you will need to bring help of your own. I look forward to seeing my friends again! Thank you so much.
			string_id message = new string_id (c_stringFile, "s_9");
			utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I cannot help you.
	if (response.equals("s_5"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: I guess I understand. The Nightsisters are something no one should have to face in a battle. I will be off to try to find someone who is able to help.
			string_id message = new string_id (c_stringFile, "s_7");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, I'll definitely help!
			boolean hasResponse0 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I cannot help you.
			boolean hasResponse1 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Why don't you ask the Empire for help?
			boolean hasResponse2 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why don't you ask the Empire for help?
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			//-- NPC: They were the first people I sought out. The Empire told me that they would prefer to leave well enough alone, and that they didn't care about a few escaped slaves. Blast the Empire! They are no better than the Nightsisters! Will you help us, please?
			string_id message = new string_id (c_stringFile, "s_8");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, I'll definitely help!
			boolean hasResponse0 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I cannot help you.
			boolean hasResponse1 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Why don't you ask the Empire for help?
			boolean hasResponse2 = false;
			if (wod_silas_hallen_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

				npcEndConversationWithMessage (player, message);
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
	if ((!isMob (self)) || (isPlayer (self)))
	{
		detachScript(self, "conversation.wod_silas_hallen");
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
	detachScript (self, "conversation.wod_silas_hallen");

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
	if (wod_silas_hallen_condition__defaultCondition (player, npc))
	{
		//-- NPC: You were able to escape from the sisters, too? What? You're from the stars!? Then maybe you might be able to help! The Nightsisters chose the strongest of their slaves and force them to work the mines! I managed to escape and search for help, for we cannot save ourselves! Will you please help us gain our freedom?
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Yes, I'll definitely help!
		boolean hasResponse0 = false;
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: I cannot help you.
		boolean hasResponse1 = false;
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse1 = true;
		}

		//-- PLAYER: Why don't you ask the Empire for help?
		boolean hasResponse2 = false;
		if (wod_silas_hallen_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse2 = true;
		}

		if (hasResponse)
		{
			int responseIndex = 0;
			string_id responses [] = new string_id [numberOfResponses];

			if (hasResponse0)
				responses [responseIndex++] = new string_id (c_stringFile, "s_4");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_5");

			if (hasResponse2)
				responses [responseIndex++] = new string_id (c_stringFile, "s_6");

			utils.setScriptVar (player, "conversation.wod_silas_hallen.branchId", 1);

			npcStartConversation (player, npc, "wod_silas_hallen", message, responses);
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
	if (!conversationId.equals("wod_silas_hallen"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wod_silas_hallen.branchId");

	if (branchId == 1 && wod_silas_hallen_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 3 && wod_silas_hallen_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 4 && wod_silas_hallen_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wod_silas_hallen.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}