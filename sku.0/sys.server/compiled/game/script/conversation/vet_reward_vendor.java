package script.conversation;

import script.*;
import script.base_class.*;
import script.base_script;

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.factions;
import script.library.prose;
import script.library.utils;

public class vet_reward_vendor extends script.conversation.base.conversation_base {
    private static final String SCRIPT = "vet_reward_vendor";

    public vet_reward_vendor() {
        super(SCRIPT);
    }

    public void vet_reward_vendor_action_showTokenVendorUI(obj_id player, obj_id npc) throws InterruptedException {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }

    public int OnInitialize(obj_id self) throws InterruptedException {
        super.OnInitialize(self);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(menuInfo) throws InterruptedException {
        return super.OnObjectMenuRequest(menuInfo);
    }

    public int OnIncapacitated() throws InterruptedException {
        return super.OnIncapacitated();
    }

    public boolean npcStartConversation(string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(greetingId, greetingProse, objects);
    }

    public int vet_reward_vendor_handleBranch1(obj_id self, obj_id player, string_id response) throws InterruptedException {
        if (response.equals("s_2")) {
            vet_reward_vendor_action_showTokenVendorUI(player, self);
            chat.chat(self, player, new string_id(c_stringFile, "s_3"));
        } else if (response.equals("s_4")) {
            chat.chat(self, player, new string_id(c_stringFile, "s_5"));
        } else if (response.equals("s_6")) {
            chat.chat(self, player, new string_id(c_stringFile, "s_7"));
        }
        npcEndConversation(player);
        return SCRIPT_CONTINUE;
    }

    public int OnStartNpcConversation(obj_id player) throws InterruptedException {
        return super.OnStartNpcConversation("s_1", player, new String[]{ "s_3", "s_5", "s_7" })
    }
}
