package script.conversation;

import script.*;
import script.base_class.*;
import script.base_script;

import script.library.ai_lib;
import script.library.chat;

import script.library.factions;
import script.library.prose;
import script.library.utils;

public class vet_reward_vendor extends script.conversation.base.conversation_base {
    private static final String SCRIPT = "conversation/vet_reward_vendor";

    public void OnStartNpcConversation(obj_id self, obj_id player) {
        OnStartNpcConversation(SCRIPT, "s_1", new String[]{ "s_2", "s_4", "s_6" }, player, self);
    }

public void OnNpcConversationResponse(obj_id self, String conversationName, obj_id player, string_id response) {
        switch (response.getAsciiId()) {
            case "s_2":
                showTokenVendorUI(player, self);
                chat.chat(self, player, new string_id(SCRIPT, "s_3"));
                break;
            case "s_4":
                chat.chat(self, player, new string_id(SCRIPT, "s_5"));
                break;
            case "s_6":
                chat.chat(self, player, new string_id(SCRIPT, "s_7"));
                break;            return SCRIPT_CONTINUE;
        }
        npcEndConversation(player);
    }
}
