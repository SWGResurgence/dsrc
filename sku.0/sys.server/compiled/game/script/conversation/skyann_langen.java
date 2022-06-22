package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.utils;
import script.groundquests;

import script.*;

public class skyann_langen extends script.base_script {
    public skyann_langen() {
    }
    
    public static String c_stringFile = "conversation/skyann_langen";
    public boolean skyann_langen_condition_defaultCondition (obj_id player, obj_id npc) throws InterruptedException {
        return true;
    }
    public boolean skyann_langen_condition_finishedQuest (obj_id player, obj_id npc) throws InterruptedException {
        if (groundquests.hasCompletedQuest(player, "quest/world_boss_peko_empress")) {
            return true;
        }
        return true;
    }
    public boolean skyann_langen_condition_readyForReward (obj_id player, obj_id npc) throws InterruptedException {
        faceTo(npc, player);
        if (groundquests.isTaskActive(player, "quest/world_boss_peko_empress", "talkToSkyannLangen")) {
            return true;
        }
        return false;
    }
    public boolean skyann_langen_condition_isOnQuest (obj_id player, obj_id npc) throws InterruptedException {
        if (groundquests.isQuestActive(player, "quest/world_boss_peko_empress")) {
            return true;
        }
        return false;
    }
    public void skyann_langen_action_sendRewardSignal (obj_id player, obj_id npc) throws InterruptedException {
        groundquests.sendSingal(player, "completedWorldBossPekoEmpress");
    }
    public void skyann_langen_action_grantQuest (obj_id player, obj_id npc) throws InterruptedException {
        groundquests.grantQuest(player, "quest/world_boss_peko_empress");
        return;
    }
    public int skyann_langen_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException {
        if (response.equals("s_4")) {
            if (skyann_langen_condition_defaultCondition (player, npc)) {
                string_id message = new string_id (c_stringFile, "s_5");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (skyann_langen_condition_defaultCondition (player, npc) {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse) {
                    int responseIndex = 0;
                    string_id responses[] = new string_id [numberOfResponses];
                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_6");
                        utils.setScriptVar (player, "conversation.skyann_langen.branchId", 5);
                        npcSpeak (player, message);
                        npcSetConversationResponses (player, responses);
                } else {
                    utils.removeScriptVar (player, "conversation/skyann_langen.branchId");
                    npcEndConversationWithMessage (player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int skyann_langen_handleBranch5 (obj_id player, obj_id npc, string_id response) throws InterruptedException {
        if (response.equals("s_6")) {
            if (skyann_langen_condition_defaultCondition (player, npc) {
                string_id message = new string_id (c_stringFile, "s_7");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (skyann_langen_condition_defaultCondition (player, npc)) {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse) {
                    int responseIndex = 0;
                    string_id responses[] = new string_id [numberOfResponses];
                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_8");
                        utils.setScriptVar (player, "conversation/skyann_langen.branchId", 6);
                        npcSpeak (player, message);
                        npcSetConversationResponses (player, responses);
                } else {
                    utils.removeScriptVar (player, "conversation/skyann_langen.branchId");
                    npcEndConversationWithMessage (player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int skyann_langen_handleBranch6 (obj_id player, obj_id npc, string_id response) throws InterruptedException {
        if (response.equals("s_8")) {
            if (skyann_langen_condition_defaultCondition (player, npc)) {
                string_id message = new string_id (c_stringFile, "s_9");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if skyann_langen_condition_defaultCondition (player, npc) {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse) {
                    int responseIndex = 0;
                    string_id responses [] = new string_id [numberOfResponses];
                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_10");
                        utils.setScriptVar (player, "conversation.ackli.branchId", 7);
                        npcSpeak (player, message);
                        npcSetConversationResponses (player, responses);
                } else {
                    utils.removeScriptVar (player, "conversation.ackli.branchId");
                    npcEndConversationWithMessage (player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int skyann_langen_handleBranch7 (obj_id player, obj_id npc, string_id response) throws InterruptedException {
        if (response.equals("s_10")) {
            if (skyann_langen_condition_defaultCondition (player, npc)) {
                string_id message = new string_id (c_stringFile, "s_11");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if skyann_langen_condition_defaultCondition (player, npc) {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse) {
                    int responseIndex = 0;
                    string_id responses [] = new string_id [numberOfResponses];
                    if (hasResponse0)
                        responses [responseIndex++] = new string_id (c_stringFile, "s_12");
                        utils.setScriptVar (player, "conversation.ackli.branchId", 8);
                        npcSpeak (player, message);
                        npcSetConversationResponses (player, responses);
                } else {
                    utils.removeScriptVar (player, "conversation.ackli.branchId");
                    npcEndConversationWithMessage (player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public skyann_langen_handleBranch8 (obj_id player, obj_id npc, string_id response) throws InterruptedException {
        if (response.equals("s_12")) {
            if (skyann_langen_condition_defaultCondition (player, npc) {
                skyann_langen_action_grantQuest (player, npc);
                string_id message = new string_id (c_stringFile, "s_13");
                utils.removeScriptVar (player, "conversation.skyann_langen.branchId");
                npcEndConversationWithMessage (player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        setCondition (self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info_menuInfo) throws InterruptedException {
        int menu = menuInfo.addRootMenu (menu_info_types.CONVERSE_START, null);
        menuInfoData.setServerNotify (false);
        setCondition (self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException {
        clearCondition (self, CONDITION_CONVERSABLE);
        detachScript (self, "conversation.skyann_langen");
        return SCRIPT_CONTINUE;
    }
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) {
        Object[] object = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException {
        obj_id npc = self;
        if (ai_lib.isInCombat (npc) || ai_lib.isInCombat (player))
            return SCRIPT_CONTINUE;
        if (skyann_langen_condition_readyForReward (player, npc)) {
            skyann_langen_action_sendRewardSignal (player, npc);
            string_id message = new string_id (c_stringFile, "s_14");
            chat.chat (npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (skyann_langen_condition_isOnQuest (player, npc) {
            string_id message = new string_id (c_stringFile, "s_15");
            chat.chat (npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (skyann_langen_condition_finishedQuest (player, npc)) {
            string_id message = new string_id (c_stringFile, "s_16");
            chat.chat (npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (skyann_langen_condition_defaultCondition (player, npc) {
            string_id message = new string_id (c_stringFile, "s_3");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (skyann_langen_condition_defaultCondition (player, npc)) {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            if (hasResponse) {
                int responseIndex = 0;
                string_id responses[] = new string_id [numberOfResponses];
                if (hasResponse0)
                    responses [responseIndex++] = new string_id (c_stringFile, "s_4");
                    utils.setScriptVar (player, "conversation.skyann_langen.branchId", 4);
                    npcStartConversation (player, npc, "skyann_langen", message, responses);
                } else {
                    chat.chat (npc, player, message);
                }
                return SCRIPT_CONTINUE;
            }
            chat.chat (npc, "Error: All conditions for OnStartNpcConversation were false.");
            return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException {
        if (!conversationId.equalss("skyann_langen"))
            return SCRIPT_CONTINUE;
        obj_id npc = self;
        int branchId = utils.getIntScriptVar (player, "conversation.skyann_langen.branchId");
        if (branchId == 4 && skyann_langen_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;
        if (branchId == 5 && skyann_langen_handleBranch5 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;
        if (branchId == 6 && skyann_langen_handleBranch6 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;
        if (branchId == 7 && skyann_langen_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;
        if (branchId == 8 && skyann_langen_handleBranch8 (player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;
        chat.chat (npc, "Error: Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar (player, "conversation.skyann_langen.branchId");
        return SCRIPT_CONTINUE;
    }
}
