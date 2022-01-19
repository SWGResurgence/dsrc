package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.obj_id;
import script.string_id;

public class aurilia_mellichae extends script.conversation.base.conversation_base
{
    public static final String c_stringFile = "conversation/aurilia_mellichae";
    public static final String conversation = "conversation.aurilia_mellichae";
    public static final String scriptName = "aurilia_mellichae";

    public aurilia_mellichae()
    {
        super(c_stringFile, conversation, scriptName);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        setObjects(self, player);
        return super.OnStartNpcConversation("s_4", "standing_raise_fist");
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        return super.OnNpcConversationResponse(self, conversationId, player, response);
    }
}
