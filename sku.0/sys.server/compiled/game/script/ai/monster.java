package script.ai;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.library.attrib;
import script.obj_id;

public class monster extends script.base_script
{
    public static final String ALERT_VOLUME_NAME = "alertTriggerVolume";

    public monster()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setAttributeInterested(self, attrib.NPC);
        if (getConfigSetting("GameServer", "disableAITriggerVolumes") == null)
        {
            createTriggerVolume(ALERT_VOLUME_NAME, ai_lib.aiGetApproachTriggerRange(self), true);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (hasObjVar(breacher, "gm"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!isMob(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (ai_lib.isInCombat(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (breacher == self)
        {
            return SCRIPT_CONTINUE;
        }
        if (ai_lib.isAiDead(self) || ai_lib.isAiDead(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (!ai_lib.isNpc(breacher) || !ai_lib.isAndroid(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        startCombat(self, breacher);
        return SCRIPT_CONTINUE;
    }
}
