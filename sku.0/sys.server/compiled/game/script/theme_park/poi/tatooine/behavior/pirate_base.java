package script.theme_park.poi.tatooine.behavior;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class pirate_base extends script.base_script
{
    public pirate_base()
    {
    }

    public int OnDefenderCombatAction(obj_id self, obj_id killer, obj_id weapon, int combatResult) throws InterruptedException
    {
        obj_id base = getObjIdObjVar(self, "base");
        dictionary attacker = new dictionary();
        attacker.put("target", killer);
        messageTo(base, "attack", attacker, 0, true);
        debugSpeakMsg(self, "COME HELP ME!");
        return SCRIPT_CONTINUE;
    }
}
