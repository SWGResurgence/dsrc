package script.conversation;


import script.*;
import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.library.create;


public class gmf_moenia_gravestone extends script.base_script
{
    public gmf_moenia_gravestone()
    {
    }
    public static final String c_stringFile = "conversation/gmf_moenia_gravetone";

    private static final String REQUIRED_SKILL = "social_language_basic_comprehend";
    private static final String GMF_QUEST = "the_skeleton";

    public boolean grave_defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    //The player has completed the scavenger hunt
    public boolean grave_taskCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!hasObjVar(player, "the_skeleton.quest_state"))
        {
            return false;
        }
        return getIntObjVar(player, "the_skeleton.quest_state") == 2;
    }
    public boolean grave_haunted(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!hasObjVar(player, "the_skeleton.quest_state"))
        {
            return false;
        }
        return getIntObjVar(player, "the_skeleton.quest_state") == 3;
    }
    public boolean grave_completeCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!hasObjVar(player, "the_skeleton.quest_state"))
        {
            return false;
        }
        return getIntObjVar(player, "the_skeleton.quest_state") == 5;
    }
    public boolean grave_onQuestCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        //Are they on the quest at all?
        return hasObjVar(player, "the_skeleton.quest_state");
    }
    /*
        This branch is our intro conversation.
        So this is the first dialog shown to the player
    */
    public int grave_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("player_investigate"))
        {
            string_id message = new string_id(c_stringFile, "npc_the_dead_speak");
            npcEndConversationWithMessage(player, message);

            //we assign them the quest for now -this should be granted by the scroll purchased from the GMF vendor
            setObjVar(player, "the_skeleton.quest_state", 1);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_DEFAULT;
    }
    public int grave_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("player_i_need_help_finding_stuff"))
        {
            //Npc message
            string_id message = new string_id(c_stringFile, "npc_use_your_eyes");

            //How many responses are there?
            int numberOfResponses = 1;

            //Don't update this manually
            int responseIndex = 0;

            //Prepare the responses
            string_id responses[] = new string_id[numberOfResponses];

            //Ensure numberOfResponses equals the amount of responses below
            responses[responseIndex++] = new string_id(c_stringFile, "player_fine_ill_use_my_eyes");

            //Set the branch that we want to go to, to check for the above responses
            utils.setScriptVar(player, "conversation.grave.branchId", 3);

            npcSpeak(player, message);
            npcSetConversationResponses(player, responses);

            return SCRIPT_CONTINUE;
        }
        if (response.equals("player_trick_or_treat"))
        {
            string_id message = new string_id(c_stringFile, "npc_quit_wasting_my_time");
            npcEndConversationWithMessage(player, message);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_DEFAULT;
    }
    public int grave_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("player_fine_ill_use_my_eyes"))
        {
            string_id message = new string_id(c_stringFile, "npc_the_dead_speak");
            npcEndConversationWithMessage(player, message);

            return SCRIPT_CONTINUE;
        }
        return SCRIPT_DEFAULT;
    }
    public int grave_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("player_offer_tribute"))
        {
            string_id message = new string_id(c_stringFile, "npc_released");
            create.object("vendor_bm_2", getLocation(player));
            npcEndConversationWithMessage(player, message);
            setObjVar(player, "the_skeleton.quest_state", 3);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_DEFAULT;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setName(self, "Dusty Gravestone");
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
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id npc, obj_id player) throws InterruptedException
    {
        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (grave_completeCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "npc_gravestone");
        }
        else if (grave_haunted(player, npc))
        {
            //Npc message
            string_id message = new string_id(c_stringFile, "npc_released");

            //How many responses are there?
            int numberOfResponses = 1;

            //Don't update this manually
            int responseIndex = 0;

            //Prepare the responses
            string_id responses[] = new string_id[numberOfResponses];

            //Ensure numberOfResponses equals the amount of responses below
            responses[responseIndex++] = new string_id(c_stringFile, "player_trick_or_treat");

            //Set the branch that we want to go to, to check for the above responses
            utils.setScriptVar(player, "conversation.grave.branchId", 2);

            npcStartConversation(player, npc, "grave", message, responses);
        }
        else if (grave_taskCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "npc_released");
            int numberOfResponses = 1;
            int responseIndex = 0;
            string_id responses[] = new string_id[numberOfResponses];
            responses[responseIndex++] = new string_id(c_stringFile, "player_offer_tribute");
            utils.setScriptVar(player, "conversation.grave.branchId", 4);
            npcStartConversation(player, npc, "grave", message, responses);
        }
        else if (grave_onQuestCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "npc_i_told_you_to_get_the_stuff");
            int numberOfResponses = 2;
            int responseIndex = 0;
            string_id responses[] = new string_id[numberOfResponses];
            responses[responseIndex++] = new string_id(c_stringFile, "player_i_need_help_finding_stuff");
            responses[responseIndex++] = new string_id(c_stringFile, "player_trick_or_treat");
            utils.setScriptVar(player, "conversation.grave.branchId", 2);
            npcStartConversation(player, npc, "grave", message, responses);
        }
        else if (grave_defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "npc_the_gravestone");
            int numberOfResponses = 1;
            int responseIndex = 0;
            string_id responses[] = new string_id[numberOfResponses];
            responses[responseIndex++] = new string_id(c_stringFile, "player_investigate");
            utils.setScriptVar(player, "conversation.grave.branchId", 1);

            npcStartConversation(player, npc, "grave", message, responses);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id npc, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("grave"))
        {
            return SCRIPT_CONTINUE;
        }
        int branchId = utils.getIntScriptVar(player, "conversation.grave.branchId");
        if (branchId == 1 && grave_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && grave_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 3 && grave_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 4 && grave_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.grave.branchId");
        return SCRIPT_CONTINUE;
    }
}