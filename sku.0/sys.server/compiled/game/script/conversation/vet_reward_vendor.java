package script.conversation;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.ai_lib;
import script.library.chat;
import script.library.prose;
import script.library.utils;

public class vet_reward_vendor extends script.base_script
{
    public static final String c_stringFile = "conversation/vet_reward_vendor";

    public boolean vet_reward_vendor_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }

    public void vet_reward_vendor_action_showTokenVendorUI(obj_id player, obj_id npc) throws InterruptedException
    {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!isTangible(self) || isPlayer(self))
        {
            detachScript(self, "conversation.vet_reward_vendor");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
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
        detachScript(self, "conversation.vet_reward_vendor");
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

    public int vet_reward_vendor_handleBranch1(obj_id self, obj_id player, string_id response) throws InterruptedException
    {
        if (response.equals("s_2"))
        {
            vet_reward_vendor_action_showTokenVendorUI(player, self);
            chat.chat(self, player, new string_id(c_stringFile, "s_3"));
        }
        else if (response.equals("s_4"))
        {
            chat.chat(self, player, new string_id(c_stringFile, "s_5"));
        }
        else if (response.equals("s_6"))
        {
            chat.chat(self, player, new string_id(c_stringFile, "s_7"));
        }
        npcEndConversation(player);
        return SCRIPT_CONTINUE;
    }

    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (vet_reward_vendor_condition__defaultCondition(player, self))
        {
            prose_package pp = new prose_package();
            pp = prose.setStringId(pp, new string_id(c_stringFile, "s_1"));
            pp.target.set(player);
            npcStartConversation(player, self, "vet_reward_vendor", null, pp, new string_id[]{new string_id(c_stringFile, "s_2"), new string_id(c_stringFile, "s_4"), new string_id(c_stringFile, "s_6")});
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("vet_reward_vendor"))
        {
            return SCRIPT_CONTINUE;
        }
        if (vet_reward_vendor_handleBranch1(self, player, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.vet_reward_vendor.branchId");
        return SCRIPT_CONTINUE;
    }
}
