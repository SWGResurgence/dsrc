package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: This script is used for Resurgence specific functions.
*/

import script.obj_id;
import script.location;
import script.menu_info_types;
import script.menu_info_data;
import script.dictionary;

import java.util.StringTokenizer;

public class player_resurgence extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnLogin(obj_id self)
    {
        incrementPlayerCount(self);
        return SCRIPT_CONTINUE;
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
        if (!hasObjVar(tatooine, "playerCount"))
        {
            setObjVar(tatooine, "playerCount", 0);
        }
        int playerCount = getIntObjVar(tatooine, "playerCount");
        playerCount += count;
        setObjVar(tatooine, "playerCount", playerCount);
    }
    public void decrementPlayerCount(obj_id self)
    {
        int count = 1;
        obj_id tatooine = getPlanetByName("tatooine");
        if (!hasObjVar(tatooine, "playerCount"))
        {
            setObjVar(tatooine, "playerCount", 0);
        }
        int playerCount = getIntObjVar(tatooine, "playerCount");
        playerCount -= count;
        setObjVar(tatooine, "playerCount", playerCount);
    }
    public int cmdDungeonFinder(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        if (!isIdValid(target) || !isPlayer(target) || params == null || params.equalsIgnoreCase(""))
        {
            sendSystemMessageTestingOnly(self, "[syntax] /dungeonFinder status");
        }
        else
        {
            StringTokenizer st = new StringTokenizer(params);
            String command = st.nextToken();
            if (command.equals("status"))
            {
                listAllDungeonStatuses(self);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int listAllDungeonStatuses(obj_id self)
    {
        String prompt;
        prompt = "Content\n";
        prompt += "\n";
        prompt = "\tCommon\n";
        prompt += "\t\tEmperor's Hand: " + getDungeonStatus("legacy.hand") + "\n";
        prompt = "\tWorld Bosses\n";
        prompt += "\t\tElder Ancient Krayt Dragon: " + getDungeonStatus("world_boss.krayt") + "\n";
        prompt += "\t\tEmpress Peko-Peko: " + getDungeonStatus("world_boss.peko") + "\n";
        prompt += "\t\tDarth Gizmo: " + getDungeonStatus("world_boss.gizmo") + "\n";
        prompt += "\t\tPax Vizla: " + getDungeonStatus("world_boss.pax") + "\n";
        prompt = "\tDungeons\n";
        prompt += "\t\tGeonosian Biolab\n";
        prompt += "\t\t\tAcklay: " + getDungeonStatus("dungeon.geo_madbio.acklay") + "\n";
        prompt += "\t\t\tReek: " + getDungeonStatus("dungeon.geo_madbio.reek") + "\n";
        prompt += "\t\t\tNexu: " + getDungeonStatus("dungeon.geo_madbio.nexu") + "\n";
        prompt += "\t\tDeath Watch Bunker\n";
        prompt += "\t\t\tDeath Watch Overlord: " + getDungeonStatus("dungeon.death_watch_bunker.overlord") + "\n";
        prompt += "\tDynamic Dungeons\n";
        prompt += "\t\tCzerka Hideout: " + getDungeonStatus("dynamic_dungeon.czerka") + "\n";
        prompt += "\t\tMos Eisley Caverns: " + getDungeonStatus("dynamic_dungeon.caverns") + "\n";

        int page = createSUIPage("/Script.messageBox", self, self);
        setSUIProperty(page, "Prompt.lblPrompt", "LocalText", prompt);
        setSUIProperty(page, "Prompt.lblPrompt", "Font", "starwarslogo_optimized_56");
        setSUIProperty(page, "bg.caption.lblTitle", "Text", "Dungeon Finder: " + getClusterName());
        setSUIProperty(page, "Prompt.lblPrompt", "Editable", "false");
        setSUIProperty(page, "Prompt.lblPrompt", "GetsInput", "false");
        subscribeToSUIEvent(page, sui_event_type.SET_onButton, "%btnOk%", "noHandler");
        setSUIProperty(page, "btnCancel", "Visible", "true");
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
            dungeonStatus = "Unknown";
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
}
