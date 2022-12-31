package script.developer.soe.working;

import script.library.utils;
import script.location;
import script.obj_id;

public class dungeon_teleport extends script.base_script
{


    public int OnSpeaking(obj_id self, String strText) throws InterruptedException
    {
        String[] strCommands = split(strText, ' ');
        if (strCommands[0].equals("teleport"))
        {
            obj_id top = getTopMostContainer(self);
            String whereTo = strCommands[1];
            location randomLoc = getGoodLocation(top, whereTo);
            obj_id cell = getCellId(top, whereTo);
            utils.warpPlayer(self, randomLoc);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
