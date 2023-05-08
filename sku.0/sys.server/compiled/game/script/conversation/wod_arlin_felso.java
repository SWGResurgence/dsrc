// ======================================================================
//
// wod_arlin_felso.java
// Copyright 2004, Sony Online Entertainment
// All Rights Reserved.
//
// Created with SwgConversationEditor 1.37 - DO NOT EDIT THIS AUTO-GENERATED FILE!
// ======================================================================

package script.conversation;

// ======================================================================
// Library Includes
// ======================================================================

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.utils;
import script.library.groundquests;
import script.*;

public class wod_arlin_felso extends script.base_script
{
	public wod_arlin_felso()

	{

	}

// ======================================================================
// Script Constants
// ======================================================================

	public static String c_stringFile = "conversation/wod_arlin_felso";

// ======================================================================
// Script Conditions
// ======================================================================

	public boolean wod_arlin_felso_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
	{
		return true;
	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_finishedQuestOne (obj_id player, obj_id npc) throws InterruptedException
	{
		if (groundquests.hasCompletedQuest(player, "quest/wod_last_delivery"))
		{
			return true;
		}
		return false;
	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_isOnQuestOne (obj_id player, obj_id npc) throws InterruptedException
	{
		if (groundquests.isQuestActive(player, "quest/wod_last_delivery"))
		{
			return true;
		}
		return false;
	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_readyForRewardOne (obj_id player, obj_id npc) throws InterruptedException
	{
		faceTo(npc, player);
		if (groundquests.isTaskActive(player, "quest/wod_last_delivery", "talkToArlin"))
		{
			return true;
		}
		return false;

	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_isOnQuestTwo (obj_id player, obj_id npc) throws InterruptedException
	{
		if (groundquests.isQuestActive(player, "quest/wod_arlin_goto_rh"))
		{
			return true;
		}
		return false;
	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_isOnQuestThree (obj_id player, obj_id npc) throws InterruptedException
	{
		if (groundquests.isQuestActive(player, "quest/wod_arlin_goto_estate"))
		{
			return true;
		}
		return false;
	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_finishedQuestTwo (obj_id player, obj_id npc) throws InterruptedException
	{
		if (groundquests.hasCompletedQuest(player, "quest/wod_arlin_goto_rh"))
		{
			return true;
		}
		return false;
	}

// ----------------------------------------------------------------------

	public boolean wod_arlin_felso_condition_finishedQuestThree (obj_id player, obj_id npc) throws InterruptedException
	{
		if (groundquests.hasCompletedQuest(player, "quest/wod_arlin_goto_estate"))
		{
			return true;
		}
		return false;
	}

// ======================================================================
// Script Actions
// ======================================================================

	public void wod_arlin_felso_action_grantQuestOne (obj_id player, obj_id npc) throws InterruptedException
	{
		groundquests.grantQuest(player, "quest/wod_last_delivery");
		return;
	}

// ----------------------------------------------------------------------

	public void wod_arlin_felso_action_sendRewardSignalOne (obj_id player, obj_id npc) throws InterruptedException
	{
		groundquests.sendSignal(player, "completedLastDelivery");
	}

// ----------------------------------------------------------------------

	public void wod_arlin_felso_action_grantQuestTwo (obj_id player, obj_id npc) throws InterruptedException
	{
		groundquests.grantQuest(player, "quest/wod_arlin_goto_rh");
		return;
	}

// ----------------------------------------------------------------------

	public void wod_arlin_felso_action_grantQuestThree (obj_id player, obj_id npc) throws InterruptedException
	{
		groundquests.grantQuest(player, "quest/wod_arlin_goto_estate");
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

	int wod_arlin_felso_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: I cannot thank you enough. I should be leaving this planet shortly.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Where can I find your family's estate on Corellia exactly?
		if (response.equals("s_81"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				wod_arlin_felso_action_grantQuestThree (player, npc);

				//-- NPC: It is north east of Coronet and directly above Bela Vistal. Here give me your datapad. I'll give you the location.
				string_id message = new string_id (c_stringFile, "s_90");
				utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: I have a question. When I retrieved your flight recorder. There was a body of a witch nearby, before I was ambushed. She wasn't wearing the same clothing as the ones that ambushed me.
		if (response.equals("s_82"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Oh no. That's Eiren Velene. She was the witch who I was arranged with. She's one of the Red Hill Clan Mother's daughters.
				string_id message = new string_id (c_stringFile, "s_83");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Oh no? Weren't you held captive?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_84");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 9);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch9 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Oh no. That's Eiren Velene. She was the witch who I was arranged with. She's one of the Red Hill Clan Mother's daughters.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Oh no? Weren't you held captive?
		if (response.equals("s_84"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, but she was not cruel, just a bit controlling. I have her necklace. Partners in their clan are given them, similar to our human tradition of rings. It needs to be returned, though I'm in no position to do it.
				string_id message = new string_id (c_stringFile, "s_85");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: I'll do it, I was interested in finding out more about this clan anyways.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: I'd like to, but I'm busy with other tasks at the moment.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_86");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_87");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 10);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch10 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Yes, but she was not cruel, just a bit controlling. I have her necklace. Partners in their clan are given them, similar to our human tradition of rings. It needs to be returned, though I'm in no position to do it.

		//-- [RESPONSE NOTE]
		//-- PLAYER: I'll do it, I was interested in finding out more about this clan anyways.
		if (response.equals("s_86"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				wod_arlin_felso_action_grantQuestTwo (player, npc);

				//-- NPC: Here's the rough location of the Red Hills Clan. You'll want to appear like a mercenary so that you don't become their newest member. I wish you luck.
				string_id message = new string_id (c_stringFile, "s_89");
				utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: I'd like to, but I'm busy with other tasks at the moment.
		if (response.equals("s_87"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: It's okay. I understand
				string_id message = new string_id (c_stringFile, "s_88");
				utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch13 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Hey! Are you the pilot I hired? I'm ready to leave this dreadful planet.

		//-- [RESPONSE NOTE]
		//-- PLAYER: I'm not. Who are you? Why do you want to leave so badly?
		if (response.equals("s_9"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm sorry. I'm Arlin Felso. I was previously an independent pilot hauling supplies to the Imperial Prison.
				string_id message = new string_id (c_stringFile, "s_11");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch14 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: I'm sorry. I'm Arlin Felso. I was previously an independent pilot hauling supplies to the Imperial Prison.

		//-- [RESPONSE NOTE]
		//-- PLAYER: That doesn't answer why you want to leave so badly.
		if (response.equals("s_13"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Let's just say the Imperials coming after me are the least of my worries as long as I'm here.
				string_id message = new string_id (c_stringFile, "s_19");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: What do you mean? Who could be worse than the Empire?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_20");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 15);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: If you were previously a pilot, why do you need to hire one?
		if (response.equals("s_69"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: A pilot without a ship isn't very useful.
				string_id message = new string_id (c_stringFile, "s_71");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
		if (response.equals("s_76"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not sure. The pay was very good, I wasn't inclined to ask many questions.
				string_id message = new string_id (c_stringFile, "s_78");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch15 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Let's just say the Imperials coming after me are the least of my worries as long as I'm here.

		//-- [RESPONSE NOTE]
		//-- PLAYER: What do you mean? Who could be worse than the Empire?
		if (response.equals("s_20"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: The witches on this planet. Both my friend and I here were captured by different clans after our ships went down. We were both slaves until we each managed to escape and make it here.
				string_id message = new string_id (c_stringFile, "s_21");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch16 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: The witches on this planet. Both my friend and I here were captured by different clans after our ships went down. We were both slaves until we each managed to escape and make it here.

		//-- [RESPONSE NOTE]
		//-- PLAYER: You were slaves?!
		if (response.equals("s_22"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, the witches of this planet still maintain primative practices, like many other planets in the galaxy. Though as you may be aware even the most advanced, like the Empire aren't beneath using it.
				string_id message = new string_id (c_stringFile, "s_23");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: How did you escape?
		if (response.equals("s_25"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: The witch of the clan I was captured by got into a skirmish with a witch from another clan. That was my opportunity to flee.
				string_id message = new string_id (c_stringFile, "s_27");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: You said you and your friend were captured by different clans?
		if (response.equals("s_29"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, I was lucky to be captured by the Red Hills Clan. He wasn't as lucky, he was captured by the Nightsisters.
				string_id message = new string_id (c_stringFile, "s_31");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Lucky? You both ended up as slaves.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_33");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_37");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 19);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch17 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Yes, the witches of this planet still maintain primative practices, like many other planets in the galaxy. Though as you may be aware even the most advanced, like the Empire aren't beneath using it.

		//-- [RESPONSE NOTE]
		//-- PLAYER: You were slaves?!
		if (response.equals("s_22"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, the witches of this planet still maintain primative practices, like many other planets in the galaxy. Though as you may be aware even the most advanced, like the Empire aren't beneath using it.
				string_id message = new string_id (c_stringFile, "s_23");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: How did you escape?
		if (response.equals("s_25"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: The witch of the clan I was captured by got into a skirmish with a witch from another clan. That was my opportunity to flee.
				string_id message = new string_id (c_stringFile, "s_27");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: You said you and your friend were captured by different clans?
		if (response.equals("s_29"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, I was lucky to be captured by the Red Hills Clan. He wasn't as lucky, he was captured by the Nightsisters.
				string_id message = new string_id (c_stringFile, "s_31");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Lucky? You both ended up as slaves.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_33");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_37");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 19);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch18 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: The witch of the clan I was captured by got into a skirmish with a witch from another clan. That was my opportunity to flee.

		//-- [RESPONSE NOTE]
		//-- PLAYER: You were slaves?!
		if (response.equals("s_22"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, the witches of this planet still maintain primative practices, like many other planets in the galaxy. Though as you may be aware even the most advanced, like the Empire aren't beneath using it.
				string_id message = new string_id (c_stringFile, "s_23");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: How did you escape?
		if (response.equals("s_25"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: The witch of the clan I was captured by got into a skirmish with a witch from another clan. That was my opportunity to flee.
				string_id message = new string_id (c_stringFile, "s_27");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: You were slaves?!
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: How did you escape?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: You said you and your friend were captured by different clans?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_22");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_29");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 16);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: You said you and your friend were captured by different clans?
		if (response.equals("s_29"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Yes, I was lucky to be captured by the Red Hills Clan. He wasn't as lucky, he was captured by the Nightsisters.
				string_id message = new string_id (c_stringFile, "s_31");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Lucky? You both ended up as slaves.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_33");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_37");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 19);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch19 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Yes, I was lucky to be captured by the Red Hills Clan. He wasn't as lucky, he was captured by the Nightsisters.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Lucky? You both ended up as slaves.
		if (response.equals("s_33"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Relatively, the Red Hills Clan isn't as sadistic as the Nightsisters. They're not exactly paradise either mind you.
				string_id message = new string_id (c_stringFile, "s_35");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Lucky? You both ended up as slaves.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_33");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_37");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 19);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
		if (response.equals("s_37"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: They're another witch clan. There are many Witches of Dathomir. They generally keep to themselves and they're located pretty far away from these outposts.
				string_id message = new string_id (c_stringFile, "s_39");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Oh that makes sense. I wish you luck on getting off this rock.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_41");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 21);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch20 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Relatively, the Red Hills Clan isn't as sadistic as the Nightsisters. They're not exactly paradise either mind you.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Lucky? You both ended up as slaves.
		if (response.equals("s_33"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Relatively, the Red Hills Clan isn't as sadistic as the Nightsisters. They're not exactly paradise either mind you.
				string_id message = new string_id (c_stringFile, "s_35");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Lucky? You both ended up as slaves.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_33");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_37");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 19);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: Red Hills Clan? I've heard of the Nightsisters.
		if (response.equals("s_37"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: They're another witch clan. There are many Witches of Dathomir. They generally keep to themselves and they're located pretty far away from these outposts.
				string_id message = new string_id (c_stringFile, "s_39");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Oh that makes sense. I wish you luck on getting off this rock.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_41");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 21);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch21 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: They're another witch clan. There are many Witches of Dathomir. They generally keep to themselves and they're located pretty far away from these outposts.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Oh that makes sense. I wish you luck on getting off this rock.
		if (response.equals("s_41"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Wait! Before you go there's something you could do for me.
				string_id message = new string_id (c_stringFile, "s_43");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: What is it? I'm listening.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: I'm not interested.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_45");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_65");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 22);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch22 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: Wait! Before you go there's something you could do for me.

		//-- [RESPONSE NOTE]
		//-- PLAYER: What is it? I'm listening.
		if (response.equals("s_45"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: If you could retrieve the ship's crash log. I would be able to greatly reduce my debt with the Empire.
				string_id message = new string_id (c_stringFile, "s_47");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Why does the Empire want the crash log?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: What's in it for me?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_49");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_53");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 23);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: I'm not interested.
		if (response.equals("s_65"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Farewell.
				string_id message = new string_id (c_stringFile, "s_67");
				utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch23 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: If you could retrieve the ship's crash log. I would be able to greatly reduce my debt with the Empire.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Why does the Empire want the crash log?
		if (response.equals("s_49"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not sure. You could try to ask them. All I know is that there's been a lot more of these unexplained crashes recently.
				string_id message = new string_id (c_stringFile, "s_51");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Why does the Empire want the crash log?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: What's in it for me?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_49");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_53");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 23);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: What's in it for me?
		if (response.equals("s_53"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not able to offer anything right now. But if you are able to bring it back. You can meet my family on Corellia and I'll be able to give you a reward then. Are you interested?
				string_id message = new string_id (c_stringFile, "s_55");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: I'm interested, give me the location.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: The danger isn't worth it for a reward that isn't even guaranteed.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_57");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_61");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 25);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch24 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: I'm not sure. You could try to ask them. All I know is that there's been a lot more of these unexplained crashes recently.

		//-- [RESPONSE NOTE]
		//-- PLAYER: Why does the Empire want the crash log?
		if (response.equals("s_49"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not sure. You could try to ask them. All I know is that there's been a lot more of these unexplained crashes recently.
				string_id message = new string_id (c_stringFile, "s_51");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Why does the Empire want the crash log?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: What's in it for me?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_49");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_53");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 23);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: What's in it for me?
		if (response.equals("s_53"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not able to offer anything right now. But if you are able to bring it back. You can meet my family on Corellia and I'll be able to give you a reward then. Are you interested?
				string_id message = new string_id (c_stringFile, "s_55");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: I'm interested, give me the location.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: The danger isn't worth it for a reward that isn't even guaranteed.
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_57");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_61");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 25);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch25 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: I'm not able to offer anything right now. But if you are able to bring it back. You can meet my family on Corellia and I'll be able to give you a reward then. Are you interested?

		//-- [RESPONSE NOTE]
		//-- PLAYER: I'm interested, give me the location.
		if (response.equals("s_57"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				wod_arlin_felso_action_grantQuestOne (player, npc);

				//-- NPC: Here's the approximate location. I wish you luck.
				string_id message = new string_id (c_stringFile, "s_59");
				utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: The danger isn't worth it for a reward that isn't even guaranteed.
		if (response.equals("s_61"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I understand. Goodbye.
				string_id message = new string_id (c_stringFile, "s_63");
				utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch29 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: A pilot without a ship isn't very useful.

		//-- [RESPONSE NOTE]
		//-- PLAYER: That doesn't answer why you want to leave so badly.
		if (response.equals("s_13"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Let's just say the Imperials coming after me are the least of my worries as long as I'm here.
				string_id message = new string_id (c_stringFile, "s_19");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: What do you mean? Who could be worse than the Empire?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_20");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 15);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: If you were previously a pilot, why do you need to hire one?
		if (response.equals("s_69"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: A pilot without a ship isn't very useful.
				string_id message = new string_id (c_stringFile, "s_71");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
		if (response.equals("s_76"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not sure. The pay was very good, I wasn't inclined to ask many questions.
				string_id message = new string_id (c_stringFile, "s_78");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

// ----------------------------------------------------------------------

	int wod_arlin_felso_handleBranch30 (obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//-- [BRANCH NOTE]
		//-- NPC: I'm not sure. The pay was very good, I wasn't inclined to ask many questions.

		//-- [RESPONSE NOTE]
		//-- PLAYER: That doesn't answer why you want to leave so badly.
		if (response.equals("s_13"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: Let's just say the Imperials coming after me are the least of my worries as long as I'm here.
				string_id message = new string_id (c_stringFile, "s_19");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: What do you mean? Who could be worse than the Empire?
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_20");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 15);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: If you were previously a pilot, why do you need to hire one?
		if (response.equals("s_69"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: A pilot without a ship isn't very useful.
				string_id message = new string_id (c_stringFile, "s_71");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE]
		//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
		if (response.equals("s_76"))
		{
			//-- [NOTE]
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				//-- NPC: I'm not sure. The pay was very good, I wasn't inclined to ask many questions.
				string_id message = new string_id (c_stringFile, "s_78");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: That doesn't answer why you want to leave so badly.
				boolean hasResponse0 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: If you were previously a pilot, why do you need to hire one?
				boolean hasResponse1 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: Why does the Imperial Prison need the help of indepedent pilots for supply runs?
				boolean hasResponse2 = false;
				if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
						responses [responseIndex++] = new string_id (c_stringFile, "s_13");

					if (hasResponse1)
						responses [responseIndex++] = new string_id (c_stringFile, "s_69");

					if (hasResponse2)
						responses [responseIndex++] = new string_id (c_stringFile, "s_76");

					utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 14);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

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
			detachScript(self, "conversation.wod_arlin_felso");
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
		detachScript (self, "conversation.wod_arlin_felso");

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
		if (wod_arlin_felso_condition_readyForRewardOne (player, npc))
		{
			wod_arlin_felso_action_sendRewardSignalOne (player, npc);

			//-- NPC: You made it back and you have my flight recorder! Come to my family's estate sometime on Corellia and I'll have something to reward you with.
			string_id message = new string_id (c_stringFile, "s_75");
			chat.chat (npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition_isOnQuestThree (player, npc))
		{
			//-- NPC: I should be leaving this planet shortly. Thank you again for all you have done.
			string_id message = new string_id (c_stringFile, "s_92");
			chat.chat (npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition_isOnQuestTwo (player, npc))
		{
			//-- NPC: Thank you again for all you have done. I should be leaving this planet shortly.
			string_id message = new string_id (c_stringFile, "s_91");
			chat.chat (npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition_isOnQuestOne (player, npc))
		{
			//-- NPC: Did you retrieve the crash log? Please hurry, I'm about to leave this rock and it would help me greatly.
			string_id message = new string_id (c_stringFile, "s_74");
			chat.chat (npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition_finishedQuestThree (player, npc))
		{
			//-- NPC: Thank you.
			string_id message = new string_id (c_stringFile, "s_96");
			chat.chat (npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition_finishedQuestTwo (player, npc))
		{
			//-- NPC: Thank you again for all you have done. I should be leaving this planet shortly. Be sure to visit my family's estate.
			string_id message = new string_id (c_stringFile, "s_94");
			chat.chat (npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition_finishedQuestOne (player, npc))
		{
			//-- NPC: I cannot thank you enough. I should be leaving this planet shortly.
			string_id message = new string_id (c_stringFile, "s_73");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Where can I find your family's estate on Corellia exactly?
			boolean hasResponse0 = false;
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: I have a question. When I retrieved your flight recorder. There was a body of a witch nearby, before I was ambushed. She wasn't wearing the same clothing as the ones that ambushed me.
			boolean hasResponse1 = false;
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_81");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_82");

				utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 7);

				npcStartConversation (player, npc, "wod_arlin_felso", message, responses);
			}
			else
			{
				chat.chat (npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//-- [NOTE]
		if (wod_arlin_felso_condition__defaultCondition (player, npc))
		{
			//-- NPC: Hey! Are you the pilot I hired? I'm ready to leave this dreadful planet.
			string_id message = new string_id (c_stringFile, "s_7");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I'm not. Who are you? Why do you want to leave so badly?
			boolean hasResponse0 = false;
			if (wod_arlin_felso_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_9");

				utils.setScriptVar (player, "conversation.wod_arlin_felso.branchId", 13);

				npcStartConversation (player, npc, "wod_arlin_felso", message, responses);
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
		if (!conversationId.equals("wod_arlin_felso"))
			return SCRIPT_CONTINUE;

		obj_id npc = self;

		int branchId = utils.getIntScriptVar (player, "conversation.wod_arlin_felso.branchId");

		if (branchId == 7 && wod_arlin_felso_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 9 && wod_arlin_felso_handleBranch9 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 10 && wod_arlin_felso_handleBranch10 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 13 && wod_arlin_felso_handleBranch13 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 14 && wod_arlin_felso_handleBranch14 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 15 && wod_arlin_felso_handleBranch15 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 16 && wod_arlin_felso_handleBranch16 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 17 && wod_arlin_felso_handleBranch17 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 18 && wod_arlin_felso_handleBranch18 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 19 && wod_arlin_felso_handleBranch19 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 20 && wod_arlin_felso_handleBranch20 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 21 && wod_arlin_felso_handleBranch21 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 22 && wod_arlin_felso_handleBranch22 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 23 && wod_arlin_felso_handleBranch23 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 24 && wod_arlin_felso_handleBranch24 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 25 && wod_arlin_felso_handleBranch25 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 29 && wod_arlin_felso_handleBranch29 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		if (branchId == 30 && wod_arlin_felso_handleBranch30 (player, npc, response) == SCRIPT_CONTINUE)
			return SCRIPT_CONTINUE;

		chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

		utils.removeScriptVar (player, "conversation.wod_arlin_felso.branchId");

		return SCRIPT_CONTINUE;
	}

// ======================================================================

}
