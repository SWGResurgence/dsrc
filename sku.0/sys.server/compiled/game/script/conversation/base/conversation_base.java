package script.conversation.base;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.*;

public class conversation_base extends script.base_script {
    private String c_stringFile = "conversation/";
    private String conversation = "conversation.";
    private String scriptName;

    private obj_id npc;
    private obj_id player;

    public conversation_base(String scriptName) {
        this.c_stringFile += scriptName;
        this.conversation += scriptName;
        this.scriptName = scriptName;
    }

    public int OnInitialize(obj_id npc) {
        if (!isTangible(npc) || isPlayer(npc)) {
            detachScript(self, conversation);
        }
        setCondition(npc, CONDITION_CONVERSABLE);
        this.npc = npc;
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
       menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated() {
        clearCondition(npc, CONDITION_CONVERSABLE);
        detachScript(npc, conversation);
        return SCRIPT_CONTINUE;
    }

    public boolean npcStartConversation(string_id greetingId, prose_package greetingProse, string_id[] responses) {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

    public int OnStartNpcConversation(String index) {
        if (!ai_lib.isInCombat(npc) && !ai_lib.isInCombat(player)) {
            chat.chat(self, new string_id(c_stringFile, index));
        }
        return SCRIPT_CONTINUE;
    }

   public int OnStartNpcConversation(String index, String animation) {
        doAnimationAction(self, animation);
        OnStartNpcConversation(index);
        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(String conversationId, string_id response) {
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, conversation + ".branchId");
        return SCRIPT_CONTINUE;
    }

    protected int craft_response(String[] responseStrings, int branchId) {
        string_id message = new string_id(c_stringFile, responseStrings[0]);
        string_id responses[] = new string_id[responseStrings.length];
        for (int i = 1; i < responseStrings.length; i++) {
            responses[i] = new string_id(c_stringFile, responseStrings[i]);
        }
        if(responses.length > 1) {
            utils.setScriptVar(player, conversation + ".branchId", branchId);
            npcSpeak(player, message);
            npcSetConversationResponses(player, responses);
        } else {
            utils.removeScriptVar(player, conversation + ".branchId");
            npcEndConversationWithMessage(player, message);
        }
        return SCRIPT_CONTINUE;
    }

    protected int craft_repeater(String[] responseStrings, int branchId, obj_id player, obj_id self) {
        string_id message = new string_id(c_stringFile, responseStrings[0]);
        string_id responses[] = new string_id[responseStrings.length];

        for (int i = 1; i < responseStrings.length; i++){
            responses[i] = new string_id(c_stringFile, responseStrings[i]);
        }

        if (responseStrings.length > 1) {
            utils.setScriptVar(player, conversation + ".branchId", branchId);
            npcStartConversation(player, self, scriptName, message, responses);
        } else {
            chat.chat(self, player, message);
        }

        return SCRIPT_CONTINUE;
    }

    protected int craft_response_prose(String[] responseStrings, int branchId, String name) {
        string_id message = new string_id(c_stringFile, responseStrings[0]);
        string_id responses[] = new string_id[responseStrings.length];
        for (int i = 1; i < responseStrings.length; i++){
            responses[i] = new string_id(c_stringFile, responseStrings[i]);
        }
        if (responses.length > 1) {
            utils.setScriptVar(player, conversation + ".branchId", branchId);
            prose_package pp = new prose_package();
            pp.stringId = message;
            pp.actor.set(player);
            pp.target.set(npc);
            pp.other.set(name);
            npcStartConversation(player, npc, scriptName, null, pp, responses);
        } else {
            prose_package pp = new prose_package();
            pp.stringId = message;
            pp.actor.set(player);
            pp.target.set(npc);
            pp.other.set(name);
            chat.chat(npc, player, null, null, pp);
        }
        return SCRIPT_CONTINUE;
    }

    public void setPlayer(obj_id player) {
        this.player = player;
    }
}
