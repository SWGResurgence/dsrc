/*
@Purpose: /developer command handler for player

@Author: BubbaJoe

 */
package script.developer.bubbajoe;

import script.*;
import script.library.chat;
import script.library.groundquests;
import script.library.prose;
import script.library.utils;

public class player_developer extends base_script
{
    public player_developer()
    {

    }

    public int cmdDeveloper(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        java.util.StringTokenizer tok = new java.util.StringTokenizer(params);

        String cmd = tok.nextToken();
        if (cmd.equalsIgnoreCase("quest"))
        {
            String subcommand = tok.nextToken();
            String questString = tok.nextToken();
            if (subcommand.equalsIgnoreCase("grant"))
            {
                groundquests.grantQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("complete"))
            {
                groundquests.completeQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("clear"))
            {
                groundquests.clearQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("task"))
            {
                String task = tok.nextToken();
                if (task.equalsIgnoreCase("complete"))
                {
                    groundquests.completeTask(target, questString, tok.nextToken());
                }
            }
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("reloadAllScripts"))
        {
            reloadAllScripts(self);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("say"))
        {
            String speech = tok.nextToken();
            String combinedMessage = "";
            while (tok.hasMoreTokens())
            {
                combinedMessage += tok.nextToken() + " ";
            }
            chat.chat(target, combinedMessage);
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("comm"))
        {
            String speech = tok.nextToken();
            String combinedMessage = "";
            while (tok.hasMoreTokens())
            {
                combinedMessage += tok.nextToken() + " ";
            }
            if (combinedMessage == null || combinedMessage.equals(""))
            {
                return SCRIPT_CONTINUE;
            }
            prose_package pp = prose.getPackage(new string_id(combinedMessage));
            commPlayer(self, target, pp);
            return SCRIPT_CONTINUE;
        }


        if (cmd.equalsIgnoreCase("wiki"))
        {
            //example: /developer wiki Combat Macro
            // /developer wiki (params[param, param2, param3])
            String speech = tok.nextToken();
            String wiki_link = "https://swg.fandom.com/wiki/" + speech;
            String pathed;
            pathed = wiki_link.replace(" ", "_");
            //chat.chat(target, splitMsg);
            launchClientWebBrowser(self, pathed);
            return SCRIPT_CONTINUE;
        }

        if (cmd.equalsIgnoreCase("scale"))
        {
            float original = getScale(target);
            broadcast(self, "Original Scale: " + original);
            setScale(target, utils.stringToFloat(tok.nextToken()));
            broadcast(target, "You have been resized.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("messageto"))
        {
            dictionary param = new dictionary();
            messageTo(target, tok.nextToken(), param, utils.stringToFloat(tok.nextToken()), true);
            return SCRIPT_CONTINUE;
            //messageTo(target,token[0], params, token[1], true);
        }
        if (cmd.equals("-help"))
        {
            debugConsoleMsg(self, "Developer Commands:  ");
            debugConsoleMsg(self, "  /developer quest grant <questname> - Grants a quest to the target.");
            debugConsoleMsg(self, "  /developer quest complete <questname> - Completes a quest for the target.");
            debugConsoleMsg(self, "  /developer quest clear <questname> - Clears a quest for the target.");
            debugConsoleMsg(self, "  /developer quest task complete <questname> <taskname> - Completes a task for the target.");
            debugConsoleMsg(self, "  /developer say <message> - Makes the target speak a message.");
            debugConsoleMsg(self, "  /developer comm <message> - Makes the target speak a message in a comm. window.");
            debugConsoleMsg(self, "  /developer scale <float> - Resizes the target.");
            debugConsoleMsg(self, "  /developer messageto <message> <float> - Sends a message to the target.");
            debugConsoleMsg(self, "  /developer wiki <search> - Opens a wiki page in your browser.");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    private void reloadAllScripts(obj_id self) throws InterruptedException
    {
        debugServerConsoleMsg(self, "Reloading all scripts");
        String[] scripts = getScriptList(self);
        for (String script : scripts)
        {
            reloadScript(script);
            broadcast(self, "Reloaded script: " + script);
        }
    }

    public int getPlayerLevel(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        int level = getLevel(target);
        debugSpeakMsg(self, "Your level is " + level);
        return SCRIPT_CONTINUE;
    }

    public location getOffsetFromRoot(obj_id self, obj_id structure_attachment)
    {
        location loc = getLocation(self);
        location root = getLocation(structure_attachment);
        loc.x = loc.x - root.x;
        loc.y = loc.y - root.y;
        loc.z = loc.z - root.z;
        return loc;
    }
    public void putPlayersInRing(obj_id[] targets, float ring_radius)
    {
        for (obj_id who : targets)
        {
            if (isIdValid(who))
            {
                location where = getLocation(who);
                float angle = rand(0, 360);
                float x = ring_radius * (float) Math.cos(angle);
                float y = ring_radius * (float) Math.sin(angle);
                where.x = where.x + x;
                where.y = where.y + y;
                setLocation(who, where);
            }
        }
    }
}


