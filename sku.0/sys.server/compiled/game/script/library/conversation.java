package script.library;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;
import script.prose_package;
import script.string_id;

public class conversation extends script.base_script
{
    public static void echoToGroup(obj_id player, obj_id actor, obj_id target, string_id sid) throws InterruptedException
    {
        if (!group.isGrouped(player))
        {
            return;
        }
        chat.chat(actor, target, sid, chat.ChatFlag_targetAndSourceGroup | chat.ChatFlag_skipTarget | chat.ChatFlag_skipSource);
    }

    public static void echoToGroup(obj_id player, obj_id actor, obj_id target, prose_package pp) throws InterruptedException
    {
        if (!group.isGrouped(player))
        {
            return;
        }
        chat.chat(actor, target, pp, chat.ChatFlag_targetAndSourceGroup | chat.ChatFlag_skipTarget | chat.ChatFlag_skipSource);
    }

    public static void npcConversationPhase(obj_id npc, obj_id player, String message, String[] string_responses, String conversationName, int phase, String c_stringFile) throws InterruptedException
    {
        string_id[] responses = new string_id[string_responses.length];
        for (int i = 0; i < responses.length; i++)
            responses[i] = new string_id(c_stringFile, string_responses[i]);
        setObjVar(player, "conversation." + conversationName + ".branchId", phase);
        npcSpeak(player, new string_id(c_stringFile, message));
        npcSetConversationResponses(player, responses);
    }

    public static void npcConversationPhase(obj_id npc, obj_id player, String message, String response, String conversationName, int phase, String c_stringFile) throws InterruptedException
    {
        npcConversationPhase(npc, player, message, new String[]{response}, conversationName, phase, c_stringFile);
    }
}
