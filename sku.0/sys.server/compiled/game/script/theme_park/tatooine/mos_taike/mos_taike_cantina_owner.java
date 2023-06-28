package script.theme_park.tatooine.mos_taike;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.library.hue;
import script.obj_id;

public class mos_taike_cantina_owner extends script.base_script
{
    public mos_taike_cantina_owner()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id dress = createObject("object/tangible/wearables/dress/dress_s06.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/shoes/shoes_s02.iff", self, "");
        setName(self, "Stella");
        hue.setColor(dress, 1, 95);
        hue.setColor(dress, 2, 22);
        hue.setColor(shoes, 1, 11);
        hue.setColor(shoes, 1, 58);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "Welcome to the Third Sun!");
        attachScript(self, "theme_park.tatooine.mos_taike.cantina_deliver");
        return SCRIPT_CONTINUE;
    }
}
