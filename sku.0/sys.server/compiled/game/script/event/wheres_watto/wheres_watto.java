package script.event.wheres_watto;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.*;
@SuppressWarnings("unused")
public class wheres_watto extends script.base_script
{
    public static String c_stringFile = "conversation/wheres_watto";

    public boolean wheres_watto_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean wheres_watto_condition__canConverse(obj_id player, obj_id npc)
    {
        return true;
    }

    int wheres_watto_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
		//-- [RESPONSE NOTE]
        //-- PLAYER: Gotcha!
        if (response.equals("s_4"))
        {
            //-- [NOTE] warp the creo to a different spot.
            if (wheres_watto_condition__defaultCondition(player, npc))
            {
                //-- NPC: Bet you can't find me this time!
                string_id message = new string_id(c_stringFile, "s_5");
                utils.removeScriptVar(player, "conversation.wheres_watto.branchId");
                setObjVar(player, "wheres_watto.found", 1);
                npcEndConversationWithMessage(player, message);
                location locLowerLeft = new location();
                locLowerLeft.x = -5000;
                locLowerLeft.z = -5000;
                location locUpperRight = new location();
                locUpperRight.x = 5000;
                locUpperRight.z = 5000;
                location goodLoc = getGoodLocationAvoidCollidables(15.0f, 15.0f, locLowerLeft, locUpperRight, false, false, 5.0f);
                setLocation(npc, goodLoc);
                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_DEFAULT;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.wheres_watto");
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
        detachScript(self, "conversation.wheres_watto");

        return SCRIPT_CONTINUE;
    }

    boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;

        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
            return SCRIPT_OVERRIDE;

        if (wheres_watto_condition__defaultCondition(player, npc))
        {
            //-- NPC: Aye, you found me! I knew I should have hidden better.
            string_id message = new string_id(c_stringFile, "s_3");
            int numberOfResponses = 0;

            boolean hasResponse = false;

            //-- PLAYER: Gotcha!
            boolean hasResponse0 = false;
            if (wheres_watto_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }

            if (hasResponse)
            {
                int responseIndex = 0;
                string_id[] responses = new string_id[numberOfResponses];

                if (hasResponse0)
                    responses[responseIndex++] = new string_id(c_stringFile, "s_4");

                utils.setScriptVar(player, "conversation.wheres_watto.branchId", 1);

                npcStartConversation(player, npc, "wheres_watto", message, responses);
            }
            else
            {
                chat.chat(npc, player, message);
            }

            return SCRIPT_CONTINUE;
        }

        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");

        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("wheres_watto"))
            return SCRIPT_CONTINUE;

        obj_id npc = self;

        int branchId = utils.getIntScriptVar(player, "conversation.wheres_watto.branchId");

        if (branchId == 1 && wheres_watto_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
            return SCRIPT_CONTINUE;

        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

        utils.removeScriptVar(player, "conversation.wheres_watto.branchId");

        return SCRIPT_CONTINUE;
    }

}