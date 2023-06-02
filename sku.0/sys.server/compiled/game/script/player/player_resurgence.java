package script.player;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: This script is used for Resurgence specific functions.
*/

import script.library.*;
import script.obj_id;
import script.location;
import script.menu_info_types;
import script.menu_info_data;
import script.dictionary;

import java.util.StringTokenizer;

import static script.library.factions.setFaction;

public class player_resurgence extends script.base_script
{
    public boolean requireEntBuffRecycle = false;
    public boolean restoredContent = false;
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "saved_performance"))
        {
            //restoreEntertainerBuffs(self);
        }
        arrivalSound(self);
        //messageTo(self, "arrivalSound", null, 60, true);
        showServerInfo(self);
        incrementPlayerCount(self);
        return SCRIPT_CONTINUE;
    }

    public void arrivalSound(obj_id self)
    {
        String planetName = getCurrentSceneName();
        if (planetName.equals("tutorial"))
        {
            return;
        }
        else if (planetName.equals("corellia"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_corellia.snd", self, "");
        }
        else if (planetName.equals("dantooine"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_dantooine.snd", self, "");
        }
        else if (planetName.equals("dathomir"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_dathomir.snd", self, "");
        }
        else if (planetName.equals("endor"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_endor.snd", self, "");
        }
        else if (planetName.equals("lok"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_lok.snd", self, "");
        }
        else if (planetName.equals("naboo"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_naboo.snd", self, "");
        }
        else if (planetName.equals("rori"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_rori.snd", self, "");
        }
        else if (planetName.equals("talus"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_talus.snd", self, "");
        }
        else if (planetName.equals("tanaab"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_tanaab.snd", self, "");
        }
        else if (planetName.equals("tatooine"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_tatooine.snd", self, "");
        }
        else if (planetName.equals("yavin4"))
        {
            playClientEffectObj(self, "voice/sound/voice_trnspt_welcome_yavin4.snd", self, "");
        }

    }

    public void showServerInfo(obj_id self)
    {
        if (!hasObjVar(self, "resurgence_welcome_onetimer"))
        {
            String gold = " \\#FFD700";
            String orange = " \\#FFA500";
            String tan = " \\#D2B48C";
            String white = " \\#FFFFFF";
            String welcomeMessage = "\\#.Thanks for playing on Apotheosis!" + "\n";
            String pleaseRead = "Please read the " + tan + "Rules & Policies" + white + " and " + tan + "F.A.Q." + white + " before starting your adventure(s)." + "\n";
            String numCharacters = "Number of Allowed Character(s): " + gold + "8" + white + "\n";
            String maxLogin = "Number of Allowed Character(s) Online: " + gold + "4" + white + "\n";
            String numAccts = "Number of Allowed Account(s): " + gold + "1" + white + "\n";
            String multiAccts = "Multiple Account(s): " + gold + "Contact Customer Support" + white + "\n";
            String features = gold + "Key Features:\n";
            String feature1 = gold + "* " + white + "Instant " + orange + "Level 90" + white + "Token." + "\n";
            String feature2 = gold + "* " + white + "One set of Heroic Jewelry.\n";
            String feature3 = gold + "* " + white + "10 Daily Terminal Missions with a x26 multiplier.\n";
            String feature4 = gold + "* " + white + "20 Housing Lots.\n";
            String feature5 = gold + "* " + white + "Starter Packs for Traders and Pilots\n";
            String feature6 = gold + "* " + white + "Veteran Reward Vendor to obtain old rewards.\n";
            String feature7 = gold + "* " + white + "Rare Loot System (RLS).\n";
            String feature8 = gold + "* " + white + "World Boss System.\n";
            String feature9 = gold + "* " + white + "New Planet: Dxun\n";
            String feature10 = gold + "* " + white + "Variety of TCG and Custom Content.\n";
            String feature11 = tan + "* " + white + "More yet to come...\n";
            String nl = "\n\\#.";
            String welcome = welcomeMessage + pleaseRead + numCharacters + maxLogin + numAccts + multiAccts + features + feature1 + feature2 + feature3 + feature4 + feature5 + feature6 + feature7 + feature8 + feature9 + feature10 + feature11;
            String title = colors_hex.Gold("Welcome to Apotheosis") + "\\#.";
            int page = sui.createSUIPage(sui.SUI_MSGBOX, self, self, "noHandler");
            setSUIProperty(page, "Prompt.lblPrompt", "LocalText", welcome);
            setSUIProperty(page, "Prompt.lblPrompt", "TextAlignmentVertical'", "Center");
            setSUIProperty(page, "bg.caption.lblTitle", "Text", title);
            setSUIProperty(page, "bg.caption.lblTitle", "Font", "starwarslogo_optimized_56");
            setSUIProperty(page, "Prompt.lblPrompt", "Editable", "false");
            setSUIProperty(page, "Prompt.lblPrompt", "Font", "starwarslogo_optimized_56");
            setSUIProperty(page, "Prompt.lblPrompt", "GetsInput", "false");
            setSUIProperty(page, "btnCancel", "Visible", "true");
            setSUIProperty(page, "btnRevert", "Visible", "false");
            setSUIProperty(page, "btnOk", sui.PROP_TEXT, "Exit");
            //saveTextOnClient(self, "server_welcome.txt", welcome);
            showSUIPage(page);
            flushSUIPage(page);
            setObjVar(self, "resurgence_welcome_onetimer", 1);
        }
    }

    public void restoreEntertainerBuffs(obj_id self) throws InterruptedException
    {
        utils.removeScriptVar(self, "performance.buildabuff");
        utils.setScriptVar(self, "performance.buildabuff.buffComponentKeys", getStringArrayObjVar(self, "saved_performance.buildabuff.buffComponentKeys"));
        utils.setScriptVar(self, "performance.buildabuff.buffComponentValues", getIntArrayObjVar(self, "saved_performance.buildabuff.buffComponentValues"));
        utils.setScriptVar(self, "performance.buildabuff.bufferId", getObjIdObjVar(self, "saved_performance.buildabuff.bufferId"));
        float buffTime = getFloatObjVar(self, "saved_performance.buildabuff.buffTime");
        buff.applyBuff(self, "buildabuff_inspiration", buffTime);
        if (requireEntBuffRecycle)
        {
            removeObjVar(self, "saved_performance");
            debugConsoleMsg(self, "Your entertainment buff package has been restored.");
        }
        else
        {
            debugConsoleMsg(self, "\\#DAA520Your entertainment buff package has been restored. You will need to seek out an entertainer to change your buff package values.\\#.");
        }
    }

    public int OnLogout(obj_id self)
    {
        decrementPlayerCount(self);
        return SCRIPT_CONTINUE;
    }

    public void incrementPlayerCount(obj_id self)
    {
        int count = 1;
        obj_id tatooine = getPlanetByName("tatooine");
        if (!hasObjVar(tatooine, "avatarCount"))
        {
            setObjVar(tatooine, "avatarCount", 1);
        }
        else
        {
            int playerCount = getIntObjVar(tatooine, "avatarCount");
            playerCount += count;
            setObjVar(tatooine, "avatarCount", playerCount);
        }
    }
    public void decrementPlayerCount(obj_id self)
    {
        int count = 1;
        obj_id tatooine = getPlanetByName("tatooine");
        if (!hasObjVar(tatooine, "avatarCount"))
        {
            setObjVar(tatooine, "avatarCount", 1);
        }
        else
        {
            int playerCount = getIntObjVar(tatooine, "avatarCount");
            playerCount -= count;
            setObjVar(tatooine, "avatarCount", playerCount);
        }
    }
    public int cmdContentFinder(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        listAllContentStatuses(self);
        return SCRIPT_CONTINUE;
    }

    public int listAllContentStatuses(obj_id self) throws InterruptedException
    {
        String prompt = "";
        prompt += "Current Status of Content\n";
        prompt += "\n";
        prompt += "\tCollections\n";
        prompt += "\t\tEmperor's Hand: " + getDungeonStatus("legacy.hand") + "\n\n";
        prompt += "\tWorld Bosses\n";
        prompt += "\t\tElder Ancient Krayt Dragon: " + getDungeonStatus("world_boss.krayt") + "\n";
        prompt += "\t\tEmpress Peko-Peko: " + getDungeonStatus("world_boss.peko") + "\n";
        prompt += "\t\tDarth Gizmo: " + getDungeonStatus("world_boss.gizmo") + "\n";
        prompt += "\t\tPax Vizla: " + getDungeonStatus("world_boss.pax") + "\n";
        prompt += "\t\tDonk-Donk Binks: " + getDungeonStatus("world_boss.donkdonk_binks") + "\n";
        prompt += "\t\tAurra Sing: " + getDungeonStatus("world_boss.aurra_sing") + "\n\n";
        prompt += "\t\tEmperor's Hand: " + getDungeonStatus("world_boss.emperor's_hand") + "\n";
        prompt += "\tDungeons\n";
        prompt += "\t\tGeonosian Biolab\n";
        prompt += "\t\t\tAcklay: " + getDungeonStatus("dungeon.geo_madbio.acklay") + "\n";
        prompt += "\t\t\tReek: " + getDungeonStatus("dungeon.geo_madbio.reek") + "\n";
        prompt += "\t\t\tNexu: " + getDungeonStatus("dungeon.geo_madbio.nexu") + "\n\n";
        prompt += "\t\tDeath Watch Bunker\n";
        prompt += "\t\t\tDeath Watch Overlord: " + getDungeonStatus("dungeon.death_watch_bunker.overlord") + "\n\n";
        if (restoredContent)
        {
            prompt += "\tDynamic Dungeons\n";
            prompt += "\t\tCzerka Hideout: " + getDungeonStatus("dynamic_dungeon.czerka") + "\n";
            prompt += "\t\tMos Eisley Caverns: " + getDungeonStatus("dynamic_dungeon.caverns") + "\n\n";
        }

        String finalPrompt = prompt;
        int page = sui.msgbox(self, self, finalPrompt);
        setSUIProperty(page, sui.MSGBOX_PROMPT, "Text", finalPrompt);
        setSUIProperty(page, sui.MSGBOX_PROMPT, "Font", "starwarslogo_optimized_56");
        setSUIProperty(page, sui.MSGBOX_TITLE, "Text", "Content Listings: " + getClusterName());
        setSUIProperty(page, sui.MSGBOX_PROMPT, "Editable", "false");
        setSUIProperty(page, sui.MSGBOX_PROMPT, "GetsInput", "false");
        subscribeToSUIEvent(page, sui_event_type.SET_onButton, "%btnOk%", "noHandler");
        setSUIProperty(page, "btnCancel", "Visible", "false");
        setSUIProperty(page, "btnRevert", "Visible", "false");
        setSUIProperty(page, "btnOk", "Visible", "false");
        showSUIPage(page);
        return SCRIPT_CONTINUE;
    }

    public String getDungeonStatus(String dungeonName)
    {
        obj_id tatooine = getPlanetByName("tatooine");
        String dungeonStatus = getStringObjVar(tatooine, "dungeon_finder." + dungeonName);
        if (dungeonStatus == null || dungeonStatus.equals(""))
        {
            dungeonStatus = "\\#FFFF00Unknown\\#.";
        }
        if (dungeonStatus.equals("Inactive"))
        {
            dungeonStatus = "\\#F32B2BInactive\\#.";
        }
        else if (dungeonStatus.equals("Active"))
        {
            dungeonStatus = "\\#7CFC00Active\\#.";
        }
        else if (dungeonStatus.equals("Engaged"))
        {
            dungeonStatus = "\\#EDBB17Engaged\\#.";
        }
        return dungeonStatus;
    }

    public int cmdAiManipulate(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        if (!isIdValid(target) || !isPlayer(target) || params == null || params.equalsIgnoreCase(""))
        {
            broadcast(self, "[syntax] /aiManipulate [command] ([subcommand])");
        }
        else
        {
            StringTokenizer st = new StringTokenizer(params);
            String command = st.nextToken();
            if (command.equals("aggroName"))
            {
                obj_id whom = getPlayerIdFromFirstName(st.nextToken());
                if (isIdValid(whom))
                {
                    startCombat(target, whom);
                }
            }
            if (command.equals("flee"))
            {
                ai_lib.flee(target, self, 5f, 28f);
            }
            if (command.equals("movement"))
            {
                String movement = st.nextToken();
                if (movement.equals("wander"))
                {
                    ai_lib.setDefaultCalmBehavior(target, ai_lib.BEHAVIOR_WANDER);
                }
                else if (movement.equals("sentinel"))
                {
                    ai_lib.setDefaultCalmBehavior(target, ai_lib.BEHAVIOR_SENTINEL);
                }
                else if (movement.equals("loiter"))
                {
                    ai_lib.setDefaultCalmBehavior(target, ai_lib.BEHAVIOR_LOITER);
                }
                else if (movement.equals("stop"))
                {
                    ai_lib.setDefaultCalmBehavior(target, ai_lib.BEHAVIOR_STOP);
                }
            }
            if (command.equals("follow"))
            {
                String flag = st.nextToken();
                if (flag.equals("-name"))
                {
                    obj_id whom = getPlayerIdFromFirstName(st.nextToken());
                    if (isIdValid(whom))
                    {
                        ai_lib.aiFollow(whom, target);
                    }
                }
                else if (flag.equals("-self"))
                {
                    ai_lib.aiFollow(target, self);
                }
                else if (flag.equals("stop"))
                {
                    ai_lib.aiStopFollowing(target);
                }
            }
            if(command.equals("level"))
            {
                int level = utils.stringToInt(st.nextToken());
                setLevel(target, level);
            }
            if(command.equals("health"))
            {
                int health = utils.stringToInt(st.nextToken());
                setMaxAttrib(target, HEALTH, health);
                setAttrib(target, HEALTH, health);
            }
            if(command.equals("action"))
            {
                int action = utils.stringToInt(st.nextToken());
                setMaxAttrib(target, ACTION, action);
                setAttrib(target, ACTION, action);
            }
            if (command.equals("wearMe"))
            {
                obj_id[] equipments = getAllWornItems(self, true);
                for (obj_id equipment : equipments)
                {
                    if (isIdValid(equipment))
                    {
                        String template = getTemplateName(equipment);
                        obj_id newEquipment = createObject(template, target, "");
                        if (isIdValid(newEquipment))
                        {
                            equip(newEquipment, target);
                        }
                    }
                }
            }
            if (command.equals("mood"))
            {
                ai_lib.setMood(self, st.nextToken());
            }
            if (command.equals("name"))
            {
                String name = st.nextToken();
                setName(target, name);
            }
            if (command.equals("faction"))
            {
                String faction = st.nextToken();
                setFaction(target, faction);
            }
            if (command.equals("noClap"))
            {
                String flag = st.nextToken();
                if (flag.equals("on"))
                {
                    setObjVar(target, "ai.noClap", true);
                }
                else if (flag.equals("off"))
                {
                    removeObjVar(target, "ai.noClap");
                }
            }
            if (command.equals("expel"))
            {
                String flag = st.nextToken();
                if (flag.equals("on"))
                {
                    setObjVar(target, "ai.expel", true);
                }
                else if (flag.equals("off"))
                {
                    removeObjVar(target, "ai.expel");
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
