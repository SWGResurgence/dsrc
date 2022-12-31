// ======================================================================
//
// wod_captain_drass.java
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
import script.library.utils;
import script.*;

public class wod_captain_drass extends script.base_script
{


// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wod_captain_drass";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wod_captain_drass_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
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

int wod_captain_drass_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Hello! I'm Captain Drass, leader of the current Solonar Rangers operation here. I'd advise you to stay within this outpost for your own safety. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I have a few questions.
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: Sure, ask away, but be quick. We're getting paid to protect this outpost and select occupants, not chat.
			string_id message = new string_id (c_stringFile, "s_5");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Why shouldn't I leave this outpost? 
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Why are you here?
			boolean hasResponse1 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Who are the Solonar Rangers?
			boolean hasResponse2 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			//-- PLAYER: Thank you for speaking with me.
			boolean hasResponse3 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse3 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_7");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse3)
					responses [responseIndex++] = new string_id (c_stringFile, "s_12");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Sure, ask away, but be quick. We're getting paid to protect this outpost and select occupants, not chat.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why shouldn't I leave this outpost? 
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: There has been an increase in hostile actions from the local witch clans, particularly the Nightsisters. Unless you want to become food for a rancor or enslaved by a witch I would stay within this outpost. 
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'll strongly consider it. Thank you.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_16");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why are you here?
	if (response.equals("s_7"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We were hired by a coalition of pilots, traders and merchants currently contracted to deliver supplies to the Imperial Prison here on Dathomir. The Empire is too occupied with other operations at the moment to offer protection. Even if they could I would imagine they would just have their own personnel do the transporting then. 
			string_id message = new string_id (c_stringFile, "s_10");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Makes sense. I appreciate the information.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 5);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Who are the Solonar Rangers?
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We are a private security company that offers a variety of services to our clients. In this case, we are providing protection of property and personnel here at this trade outpost. 
			string_id message = new string_id (c_stringFile, "s_11");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Oh okay. Thank you
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_14");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 7);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Thank you for speaking with me.
	if (response.equals("s_12"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: No problem. Stay safe.
			string_id message = new string_id (c_stringFile, "s_13");
			utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch3 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: There has been an increase in hostile actions from the local witch clans, particularly the Nightsisters. Unless you want to become food for a rancor or enslaved by a witch I would stay within this outpost. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I'll strongly consider it. Thank you.
	if (response.equals("s_16"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: Any other questions?
			string_id message = new string_id (c_stringFile, "s_17");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Why shouldn't I leave this outpost? 
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Why are you here?
			boolean hasResponse1 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Who are the Solonar Rangers?
			boolean hasResponse2 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			//-- PLAYER: Thank you for speaking with me.
			boolean hasResponse3 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse3 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_7");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse3)
					responses [responseIndex++] = new string_id (c_stringFile, "s_12");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Any other questions?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why shouldn't I leave this outpost? 
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: There has been an increase in hostile actions from the local witch clans, particularly the Nightsisters. Unless you want to become food for a rancor or enslaved by a witch I would stay within this outpost. 
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'll strongly consider it. Thank you.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_16");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why are you here?
	if (response.equals("s_7"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We were hired by a coalition of pilots, traders and merchants currently contracted to deliver supplies to the Imperial Prison here on Dathomir. The Empire is too occupied with other operations at the moment to offer protection. Even if they could I would imagine they would just have their own personnel do the transporting then. 
			string_id message = new string_id (c_stringFile, "s_10");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Makes sense. I appreciate the information.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 5);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Who are the Solonar Rangers?
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We are a private security company that offers a variety of services to our clients. In this case, we are providing protection of property and personnel here at this trade outpost. 
			string_id message = new string_id (c_stringFile, "s_11");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Oh okay. Thank you
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_14");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 7);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Thank you for speaking with me.
	if (response.equals("s_12"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: No problem. Stay safe.
			string_id message = new string_id (c_stringFile, "s_13");
			utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch5 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We were hired by a coalition of pilots, traders and merchants currently contracted to deliver supplies to the Imperial Prison here on Dathomir. The Empire is too occupied with other operations at the moment to offer protection. Even if they could I would imagine they would just have their own personnel do the transporting then. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Makes sense. I appreciate the information.
	if (response.equals("s_15"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: Do you have any other questions?
			string_id message = new string_id (c_stringFile, "s_18");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Why shouldn't I leave this outpost? 
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Why are you here?
			boolean hasResponse1 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Who are the Solonar Rangers?
			boolean hasResponse2 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			//-- PLAYER: Thank you for speaking with me.
			boolean hasResponse3 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse3 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_7");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse3)
					responses [responseIndex++] = new string_id (c_stringFile, "s_12");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch6 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Do you have any other questions?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why shouldn't I leave this outpost? 
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: There has been an increase in hostile actions from the local witch clans, particularly the Nightsisters. Unless you want to become food for a rancor or enslaved by a witch I would stay within this outpost. 
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'll strongly consider it. Thank you.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_16");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why are you here?
	if (response.equals("s_7"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We were hired by a coalition of pilots, traders and merchants currently contracted to deliver supplies to the Imperial Prison here on Dathomir. The Empire is too occupied with other operations at the moment to offer protection. Even if they could I would imagine they would just have their own personnel do the transporting then. 
			string_id message = new string_id (c_stringFile, "s_10");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Makes sense. I appreciate the information.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 5);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Who are the Solonar Rangers?
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We are a private security company that offers a variety of services to our clients. In this case, we are providing protection of property and personnel here at this trade outpost. 
			string_id message = new string_id (c_stringFile, "s_11");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Oh okay. Thank you
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_14");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 7);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Thank you for speaking with me.
	if (response.equals("s_12"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: No problem. Stay safe.
			string_id message = new string_id (c_stringFile, "s_13");
			utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We are a private security company that offers a variety of services to our clients. In this case, we are providing protection of property and personnel here at this trade outpost. 

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Oh okay. Thank you
	if (response.equals("s_14"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: Anything else I can answer?
			string_id message = new string_id (c_stringFile, "s_19");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Why shouldn't I leave this outpost? 
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Why are you here?
			boolean hasResponse1 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Who are the Solonar Rangers?
			boolean hasResponse2 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			//-- PLAYER: Thank you for speaking with me.
			boolean hasResponse3 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse3 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_7");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse3)
					responses [responseIndex++] = new string_id (c_stringFile, "s_12");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

int wod_captain_drass_handleBranch8 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Anything else I can answer?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why shouldn't I leave this outpost? 
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: There has been an increase in hostile actions from the local witch clans, particularly the Nightsisters. Unless you want to become food for a rancor or enslaved by a witch I would stay within this outpost. 
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'll strongly consider it. Thank you.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_16");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Why are you here?
	if (response.equals("s_7"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We were hired by a coalition of pilots, traders and merchants currently contracted to deliver supplies to the Imperial Prison here on Dathomir. The Empire is too occupied with other operations at the moment to offer protection. Even if they could I would imagine they would just have their own personnel do the transporting then. 
			string_id message = new string_id (c_stringFile, "s_10");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Makes sense. I appreciate the information.
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 5);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Who are the Solonar Rangers?
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: We are a private security company that offers a variety of services to our clients. In this case, we are providing protection of property and personnel here at this trade outpost. 
			string_id message = new string_id (c_stringFile, "s_11");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Oh okay. Thank you
			boolean hasResponse0 = false;
			if (wod_captain_drass_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_14");

				utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 7);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Thank you for speaking with me.
	if (response.equals("s_12"))
	{
		//-- [NOTE] 
		if (wod_captain_drass_condition__defaultCondition (player, npc))
		{
			//-- NPC: No problem. Stay safe.
			string_id message = new string_id (c_stringFile, "s_13");
			utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

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
		detachScript(self, "conversation.wod_captain_drass");
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
	detachScript (self, "conversation.wod_captain_drass");

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
	if (wod_captain_drass_condition__defaultCondition (player, npc))
	{
		//-- NPC: Hello! I'm Captain Drass, leader of the current Solonar Rangers operation here. I'd advise you to stay within this outpost for your own safety. 
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I have a few questions.
		boolean hasResponse0 = false;
		if (wod_captain_drass_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.wod_captain_drass.branchId", 1);

			npcStartConversation (player, npc, "wod_captain_drass", message, responses);
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
	if (!conversationId.equals("wod_captain_drass"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wod_captain_drass.branchId");

	if (branchId == 1 && wod_captain_drass_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 2 && wod_captain_drass_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 3 && wod_captain_drass_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 4 && wod_captain_drass_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 5 && wod_captain_drass_handleBranch5 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 6 && wod_captain_drass_handleBranch6 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 7 && wod_captain_drass_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 8 && wod_captain_drass_handleBranch8 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wod_captain_drass.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}