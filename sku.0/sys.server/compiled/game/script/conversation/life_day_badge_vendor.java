package script.conversation;

import script.library.*;
import script.*;

public class life_day_badge_vendor extends script.base_script
{

    public static String c_stringFile = "conversation/life_day_badge_vendor";
    public boolean life_day_badge_vendor_condition_defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isTangible(self)) || (isPlayer(self))) {
            detachScript(self, "conversation.life_day_badge_vendor");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setName(self, "An Elder Life Day Representative");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.life_day_badge_vendor");
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;
        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player)) {
            return SCRIPT_OVERRIDE;
        }
        string_id message = new string_id(c_stringFile, "s_1");
        int numberOfResponses = 0;
        boolean hasResponse = false;
        boolean hasResponse0 = false;
        if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
            ++numberOfResponses;
            hasResponse = true;
            hasResponse0 = true;
        }
        boolean hasResponse1 = false;
        if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
            ++numberOfResponses;
            hasResponse = true;
            hasResponse1 = true;
        }
        if (hasResponse) {
            int responseIndex = 0;
            string_id responses[] = new string_id[numberOfResponses];
            if (hasResponse0) {
                responses[responseIndex++] = new string_id(c_stringFile, "s_2");
            }
            if (hasResponse1) {
                responses[responseIndex++] = new string_id(c_stringFile, "s_3");
            }
            utils.setScriptVar(player, "conversation.life_day_badge_vendor.branchId", 1);
            npcStartConversation(player, npc, "life_day_badge_vendor", message, responses);
        }
        else {
            chat.chat(npc, player, message);
        }
        return SCRIPT_CONTINUE;
    }
    public int life_day_badge_vendor_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_2")) {
            string_id message = new string_id(c_stringFile, "s_4");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            if (hasResponse) {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0){
                    responses[responseIndex++] = new string_id(c_stringFile, "s_5");
                }
                utils.setScriptVar(player, "conversation.life_day_badge_vendor.branchId", 2);
                npcSpeak(player, message);
                npcSetConversationResponses(player, responses);
            }
            else {
                utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                npcEndConversationWithMessage(player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (response.equals("s_3")) {
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                string_id message = new string_id(c_stringFile, "s_6");
                utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int life_day_badge_vendor_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_5")) {
            string_id message = new string_id(c_stringFile, "s_7");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            boolean hasResponse2 = false;
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse2 = true;
            }
            boolean hasResponse3 = false;
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse3 = true;
            }
                if (hasResponse) {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0){
                        responses[responseIndex++] = new string_id(c_stringFile, "s_8");
                    }
                    if (hasResponse1){
                        responses[responseIndex++] = new string_id(c_stringFile, "s_9");
                    }
                    if (hasResponse2){
                        responses[responseIndex++] = new string_id(c_stringFile, "s_10");
                    }
                    if (hasResponse3){
                        responses[responseIndex++] = new string_id(c_stringFile, "s_11");
                    }
                    utils.setScriptVar(player, "conversation.life_day_badge_vendor.branchId", 3);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else {
                    utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                    npcEndConversationWithMessage(player, message);
                }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int life_day_badge_vendor_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_8")) {
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                life_day_badge_vendor_action_badgeOne(player, npc);
                utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_9")) {
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                life_day_badge_vendor_action_badgeTwo(player, npc);
                utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_10")) {
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                life_day_badge_vendor_action_badgeThree(player, npc);
                utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_11")) {
            if (life_day_badge_vendor_condition_defaultCondition(player, npc)) {
                life_day_badge_vendor_action_badgeFour(player, npc);
                utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
                return SCRIPT_CONTINUE;
            }
        }
        else {
            utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
        }
        return SCRIPT_CONTINUE;
    }
    public void life_day_badge_vendor_action_badgeOne(obj_id player, obj_id npc) throws InterruptedException
    {
        String tokenName = "";
        if (factions.isImperial(player)) {
            tokenName = "item_event_lifeday_imperial_token";
        }
        if (factions.isRebel(player)) {
            tokenName = "item_event_lifeday_rebel_token";
        }
        obj_id pInv = utils.getInventoryContainer(player);
        int tokens = trial.getTokenTotal(player, tokenName);
        if (tokens >= 500 && isIdValid(pInv) && exists(pInv) && !badge.hasBadge(player, "lifeday_badge_08")) {
            if (trial.purchaseTokenItem(player, 500, tokenName)) {
                badge.grantBadge(player, "lifeday_badge_08");
                string_id message = new string_id(c_stringFile, "s_12");
                npcEndConversationWithMessage(player, message);
                return;
            }
        }
        string_id message = new string_id(c_stringFile, "s_13");
        npcEndConversationWithMessage(player, message);
        return;
    }
    public void life_day_badge_vendor_action_badgeTwo(obj_id player, obj_id npc) throws InterruptedException
    {
        String tokenName = "";
        if (factions.isImperial(player)) {
            tokenName = "item_event_lifeday_imperial_token";
        }
        if (factions.isRebel(player)) {
            tokenName = "item_event_lifeday_rebel_token";
        }
        obj_id pInv = utils.getInventoryContainer(player);
        int tokens = trial.getTokenTotal(player, tokenName);
        if (tokens >= 500 && isIdValid(pInv) && exists(pInv) && !badge.hasBadge(player, "lifeday_badge_09")) {
            if (trial.purchaseTokenItem(player, 500, tokenName)) {
                badge.grantBadge(player, "lifeday_badge_09");
                string_id message = new string_id(c_stringFile, "s_12");
                npcEndConversationWithMessage(player, message);
                return;
            }
        }
        string_id message = new string_id(c_stringFile, "s_13");
        npcEndConversationWithMessage(player, message);
        return;
    }
    public void life_day_badge_vendor_action_badgeThree(obj_id player, obj_id npc) throws InterruptedException
    {
        String tokenName = "";
        if (factions.isImperial(player)) {
            tokenName = "item_event_lifeday_imperial_token";
        }
        if (factions.isRebel(player)) {
            tokenName = "item_event_lifeday_rebel_token";
        }
        obj_id pInv = utils.getInventoryContainer(player);
        int tokens = trial.getTokenTotal(player, tokenName);
        if (tokens >= 500 && isIdValid(pInv) && exists(pInv) && !badge.hasBadge(player, "lifeday_badge_10")) {
            if (trial.purchaseTokenItem(player, 500, tokenName)) {
                badge.grantBadge(player, "lifeday_badge_10");
                string_id message = new string_id(c_stringFile, "s_12");
                npcEndConversationWithMessage(player, message);
                return;
            }
        }
        string_id message = new string_id(c_stringFile, "s_13");
        npcEndConversationWithMessage(player, message);
        return;
    }
    public void life_day_badge_vendor_action_badgeFour(obj_id player, obj_id npc) throws InterruptedException
    {
        String tokenName = "";
        if (factions.isImperial(player)) {
            tokenName = "item_event_lifeday_imperial_token";
        }
        if (factions.isRebel(player)) {
            tokenName = "item_event_lifeday_rebel_token";
        }
        obj_id pInv = utils.getInventoryContainer(player);
        int tokens = trial.getTokenTotal(player, tokenName);
        if (tokens >= 500 && isIdValid(pInv) && exists(pInv) && !badge.hasBadge(player, "lifeday_badge_11")) {
            if (trial.purchaseTokenItem(player, 500, tokenName)) {
                badge.grantBadge(player, "lifeday_badge_11");
                string_id message = new string_id(c_stringFile, "s_12");
                npcEndConversationWithMessage(player, message);
                return;
            }
        }
        string_id message = new string_id(c_stringFile, "s_13");
        npcEndConversationWithMessage(player, message);
        return;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("life_day_badge_vendor")) {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.life_day_badge_vendor.branchId");
        if (branchId == 1 && life_day_badge_vendor_handleBranch1(player, npc, response) == SCRIPT_CONTINUE) {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && life_day_badge_vendor_handleBranch2(player, npc, response) == SCRIPT_CONTINUE) {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 3 && life_day_badge_vendor_handleBranch3(player, npc, response) == SCRIPT_CONTINUE) {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.life_day_badge_vendor.branchId");
        return SCRIPT_CONTINUE;
    }
}
