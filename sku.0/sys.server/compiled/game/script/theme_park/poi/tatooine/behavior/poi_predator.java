package script.theme_park.poi.tatooine.behavior;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class poi_predator extends script.base_script
{
    public poi_predator()
    {
    }

    public int killTarget(obj_id self, dictionary params) throws InterruptedException
    {
        debugSpeakMsg(self, "That's it! Now you die!");
        obj_id target = params.getObjId("target");
        follow(self, target, 5.0f, 10.0f);
        return SCRIPT_CONTINUE;
    }
}
