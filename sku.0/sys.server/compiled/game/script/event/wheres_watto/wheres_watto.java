package script.event.wheres_watto;/*
@Filename: script.event.wheres_watto.wheres_watto
@Author: BubbaJoeX
@Purpose: Watto conversation. DO NOT spawn more than one watto per galaxy. Rotate through the planets.
*/

import script.library.ai_lib;
import script.library.chat;
import script.library.static_item;
import script.library.utils;
import script.*;

@SuppressWarnings("unused")
public class wheres_watto extends script.base_script
{
    public static String c_stringFile = "conversation/wheres_watto";
    public String[] ONE_TIME_GRANT = {
            "item_tcg_loot_reward_series7_deed_vehicle_garage",
            "item_tcg_loot_reward_series3_jedi_meditation_room_deed",
            "item_tcg_loot_reward_series3_sith_meditation_room_deed",
            "item_tcg_loot_reward_series8_yoda_house_deed",
            "item_tcg_loot_reward_series6_deed_emperor_spire",
            "item_tcg_loot_reward_series6_deed_rebel_spire",
            "item_tcg_loot_reward_series4_relaxation_pool_deed_02_01",
            "item_tcg_loot_reward_series6_auto_feeder"
    };
    public String[] REPEATABLE_REWARDS = {
            "item_tcg_loot_reward_series6_beast_muzzle",
            "item_tcg_loot_reward_series4_video_game_table_02_01",
            "item_tcg_loot_reward_series2_mandalorian_strongbox",
            "item_tcg_loot_reward_series1_beru_whitesuns_cookbook",
            "item_tcg_loot_reward_series1_housecleaning_kit",
            "item_tcg_loot_reward_series2_organizational_datapad",
            "item_tcg_loot_reward_series7_build04_broken_ball_turret",
            "item_tcg_loot_reward_series7_build01_tie_canopy",
            "item_tcg_loot_reward_series7_build02_xwing_wing",
            "item_tcg_loot_reward_series7_build03_gunship_blueprint",
            "item_tcg_loot_reward_series3_swamp_speeder",
            "item_tcg_reward_series3_jango_fett_memorial_statue",
            "item_tcg_reward_series3_boba_fett_statue",
            "item_tcg_reward_series4_peko_peko_mount_02_01",
            "item_tcg_reward_series6_dewback_armor",
            "item_tcg_reward_series3_armored_bantha",
            "item_tcg_reward_series9_jedi_library_bookshelf",
            "item_tcg_reward_series3_merr_son_jt12_jetpack-blueprints",
            "item_tcg_reward_series3_mandalorian_skull_banner",
            "item_tcg_reward_series5_galactic_hunters_poster",
            "item_tcg_loot_reward_series1_painting_jedi_crest",
            "item_tcg_reward_series1_sith_speeder",
            "item_tcg_reward_series5_painting_jedi_techniques",
            "item_tcg_reward_series3_wookiee_ceremonial_pipe"
    };

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
                location watto_loc = new location(0, 0, 0, getCurrentSceneName(), null);
                createReward(npc, player);
                hideFromClient(npc, true);
                watto_loc.x = watto_loc.x + (rand(-7250.0f, 7250.0f));
                watto_loc.z = watto_loc.z + (rand(-7250.0f, 7250.0f));
                watto_loc.y = getHeightAtLocation(watto_loc.x, watto_loc.z);
                setLocation(npc, watto_loc);
                messageTo(npc, "unHideMe", null, 60, false);
                return SCRIPT_CONTINUE;
            }

        }

        return SCRIPT_CONTINUE;
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
        setCondition(self, CONDITION_HOLIDAY_INTERESTING);
        if (!hasObjVar(self, "watto_tag"))
        {
            setObjVar(self, "watto_tag", 1);
        }
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

    public int createReward(obj_id self, obj_id player) throws InterruptedException
    {
        if (!hasObjVar(player, "watto_found_main"))
        {
            String reward = ONE_TIME_GRANT[rand(0, ONE_TIME_GRANT.length - 1)];
            playClientEffectObj(player, "sound/halloween_toydarian_laugh.snd", player, "");
            broadcast(player, "Watto has rewarded you with a useless datapad. Maybe you can find use for it.");
            if (isGod(player))
            {
                sendSystemMessageTestingOnly(player, "Reward: " + reward);
            }
            static_item.createNewItemFunction(reward, utils.getInventoryContainer(player));
            setObjVar(player, "watto_found_main", 1);
        }
        else
        {
            broadcast(player, "You have received a reward for finding Watto.");
            String reward = REPEATABLE_REWARDS[rand(0, REPEATABLE_REWARDS.length - 1)];
            if (isGod(player))
            {
                sendSystemMessageTestingOnly(player, "Reward: " + reward);
            }
            static_item.createNewItemFunction(reward, utils.getInventoryContainer(player));
        }
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

    public void unHideMe(obj_id self)
    {
        hideFromClient(self, false);
    }

}