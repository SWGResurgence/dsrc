// ======================================================================
//
// isd_bypass_smuggler.java
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

import script.library.*;
import script.*;

public class isd_bypass_smuggler extends script.base_script
{
	public isd_bypass_smuggler()

	{

	}

// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/isd_bypass_smuggler";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean isd_bypass_smuggler_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ----------------------------------------------------------------------

public boolean isd_bypass_smuggler_condition_canTalk (obj_id player, obj_id npc) throws InterruptedException
{
	if (instance.isFlaggedForInstance(player, "heroic_star_destroyer"));
	{
        if (group.isLeader(player))
        {
            return true;
        }
        else
        {
            chat.chat(npc, "Sorry, I only speak to those in charge.");
        }
	}
    chat.chat(npc, "You don't look like you should be troubling yourself with the Blackguard. Talk to me when you have more experience.");
    return false;
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

int isd_bypass_smuggler_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: So, you need transport to the infamous Blackguard aye? Well, it'll cost ya, but I can get you there.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Fine, I'll pay. [10,000 credits]
	if (response.equals("s_4"))
	{
		//-- [NOTE] send to ISD
		if (isd_bypass_smuggler_condition__defaultCondition (player, npc))
		{
			//-- NPC: Very well! This transit will be quick and painless. After the payment, of course.
			string_id message = new string_id (c_stringFile, "s_6");
			utils.removeScriptVar (player, "conversation.isd_bypass_smuggler.branchId");
			npcEndConversationWithMessage (player, message);
            sui.msgbox(npc, player, "Do you wish to lead your group into Blackguard? This will cost you " + colors_hex.HEADER + colors_hex.AQUAMARINE + " 10,000 credits.\\#.", "handleTransport");
			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] end convo
	//-- PLAYER: Buzz off, you heckler.
	if (response.equals("s_5"))
	{
		//-- [NOTE] 
		if (isd_bypass_smuggler_condition_canTalk (player, npc))
		{
			//-- NPC: So, you need transport to the infamous Blackguard aye? Well, it'll cost ya, but I can get you there.
			string_id message = new string_id (c_stringFile, "s_3");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Fine, I'll pay. [10,000 credits]
			boolean hasResponse0 = false;
			if (isd_bypass_smuggler_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Buzz off, you heckler.
			boolean hasResponse1 = false;
			if (isd_bypass_smuggler_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				utils.setScriptVar (player, "conversation.isd_bypass_smuggler.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.isd_bypass_smuggler.branchId");

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
		detachScript(self, "conversation.isd_bypass_smuggler");
	}
    setCondition(self, CONDITION_SPACE_INTERESTING);
    setName(self, "Soc-ah (a crazed smuggler)");
	setCondition (self, CONDITION_CONVERSABLE);

	return SCRIPT_CONTINUE;
}

public int OnAttach(obj_id self) throws InterruptedException
{
	setCondition (self, CONDITION_CONVERSABLE);
    setCondition(self, CONDITION_SPACE_INTERESTING);
    setName(self, "Soc-ah (a crazed smuggler)");
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
	detachScript (self, "conversation.isd_bypass_smuggler");

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
	if (isd_bypass_smuggler_condition_canTalk (player, npc))
	{
		//-- NPC: So, you need transport to the infamous Blackguard aye? Well, it'll cost ya, but I can get you there.
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Fine, I'll pay. [10,000 credits]
		boolean hasResponse0 = false;
		if (isd_bypass_smuggler_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: Buzz off, you heckler.
		boolean hasResponse1 = false;
		if (isd_bypass_smuggler_condition__defaultCondition (player, npc))
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
				responses [responseIndex++] = new string_id (c_stringFile, "s_4");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_5");

			utils.setScriptVar (player, "conversation.isd_bypass_smuggler.branchId", 1);

			npcStartConversation (player, npc, "isd_bypass_smuggler", message, responses);
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
	if (!conversationId.equals("isd_bypass_smuggler"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.isd_bypass_smuggler.branchId");

	if (branchId == 1 && isd_bypass_smuggler_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.isd_bypass_smuggler.branchId");

	return SCRIPT_CONTINUE;
}
public int handleTransport(obj_id self, dictionary params) throws InterruptedException
{
    if (params == null)
    {
        return SCRIPT_CONTINUE;
    }
    obj_id player = sui.getPlayerId(params);
    int button = sui.getIntButtonPressed(params);
    if (button == sui.BP_OK);
    {
        if (money.getCashBalance(player) >= 10000)
        {
            money.withdraw(player, 10000);
            chat.chat(self, "Off we go!");
            obj_id[] players = group.getGroupMemberIds(group.getGroupObject(player));
            sendGroupToISD(players); //will fail if players are not all on the same gameserver fyi
        }
        else
        {
            broadcast(player, "You do not have the funds to pay this smuggler.");
            chat.chat(player, "Another cheapskate I see... Begone!");
        }
    }
    return SCRIPT_CONTINUE;
}
public void sendGroupToISD(obj_id[] players) throws InterruptedException
{
    for (obj_id singlePlayer : players)
    {
        instance.requestInstanceMovement(singlePlayer, "heroic_star_destroyer");
    }
}

// ======================================================================

}