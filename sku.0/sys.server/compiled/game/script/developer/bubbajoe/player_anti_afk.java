package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.player_anti_afk
@Author: BubbaJoeX
@Purpose:
@WIP - This script is a work in progress and is not yet functional.
*/

import script.obj_id;

public class player_anti_afk extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String name, obj_id who) throws InterruptedException
    {
        if (!name.startsWith("anti_macro_volume"))
        {
            return SCRIPT_CONTINUE;
        }
        handleMacroTermination(self, who);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeExited(obj_id self, String name, obj_id who) throws InterruptedException
    {
        if (!name.startsWith("anti_macro_volume"))
        {
            return SCRIPT_CONTINUE;
        }
        handleMacroTermination(self, who);
        return SCRIPT_CONTINUE;
    }
    private void handleMacroTermination(obj_id self, obj_id who)
    {
        if (isPlayer(who))
        {
            broadcast(who, "This area has been protected against the use of automation macros.");
            sendConsoleCommand("/echo Terminating Macro.", who);
            sendConsoleCommand("/dumpPausedCommands", who);

        }
    }
}
