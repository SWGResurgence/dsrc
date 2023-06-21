package script.faction_perk.hq;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;
import script.string_id;

public class terminal_disable extends script.base_script
{
    private static final string_id SID_TERMINAL_DISABLED = new string_id("hq", "terminal_disabled");

    public terminal_disable()
    {
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        sendSystemMessage(player, SID_TERMINAL_DISABLED);
        return SCRIPT_OVERRIDE;
    }
}
