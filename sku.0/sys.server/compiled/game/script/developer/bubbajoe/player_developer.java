package script.developer.bubbajoe;
import script.*;
import script.library.groundquests;
import script.library.chat;
import script.library.utils;

public class player_developer extends base_script {
    public player_developer(){

    }
    public int cmdDeveloper(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException {
        java.util.StringTokenizer tok = new java.util.StringTokenizer(params);

        String cmd = tok.nextToken();
        if (cmd.equalsIgnoreCase("quest")) {
            String subcommand = tok.nextToken();
            String questString = tok.nextToken();
            if (subcommand.equalsIgnoreCase("grant")) {
                groundquests.grantQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("complete")) {
                groundquests.completeQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("clear")) {
                groundquests.clearQuest(target, questString);
            }
            if (subcommand.equalsIgnoreCase("task")) {
                String task = tok.nextToken();
                if (task.equalsIgnoreCase("complete")) {
                    groundquests.completeTask(target, questString, tok.nextToken());
                }
            }
        }
        if (cmd.equalsIgnoreCase("puppet")) {
            String speech = tok.nextToken();
            String splitMsg = String.valueOf(split(speech, ' '));
            chat.chat(target, splitMsg);
        }
        if (cmd.equalsIgnoreCase("wiki")) {
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
        if (cmd.equalsIgnoreCase("scale")) {
            float original = getScale(target);
            broadcast(self, "Original Scale: " + original);
            setScale(target, utils.stringToFloat(tok.nextToken()));
            broadcast(target, "You have been resized.");
            return SCRIPT_CONTINUE;
        }
        if (cmd.equalsIgnoreCase("messageto")) {
            dictionary param = new dictionary();
            messageTo(target, tok.nextToken(), param, utils.stringToFloat(tok.nextToken()), true);
            return SCRIPT_CONTINUE;
            //messageTo(target,token[0], params, token[1], true);
        }
        if (cmd.equals("-help"))
        {
            debugConsoleMsg(self, "quest [grant, complete, clear, task], puppet, wiki, scale, messageto");
        }
        return SCRIPT_CONTINUE;
    }
    public int getPlayerLevel (obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        int level = getLevel(target);
        debugSpeakMsg(self, "Your level is " + level);
        return SCRIPT_CONTINUE;
    }
}


