package script.player.skill;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.minigame;
import script.obj_id;
import script.string_id;

public class minigame_cmd extends script.base_script
{
    public static final String STF = "som/som_item";

    public minigame_cmd()
    {
    }

    public int cmdFish(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        String planetName = getCurrentSceneName();
        if (planetName.startsWith("mustafar"))
        {
            sendSystemMessage(self, new string_id(STF, "cannot_fish"));
            return SCRIPT_CONTINUE;
        }
        if (minigame.isFishing(self))
        {
            minigame.stopFishing(self);
            queueCommand(self, (138370278), null, "", COMMAND_PRIORITY_DEFAULT);
            return SCRIPT_CONTINUE;
        }
        minigame.startFishing(self);
        return SCRIPT_CONTINUE;
    }
}
