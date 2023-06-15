package script.space.content_tools;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.space_combat;
import script.library.space_create;
import script.obj_id;

public class star_destroyer_e3 extends script.base_script
{
    public star_destroyer_e3()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "makeDestroyer", null, 1, false);
        return SCRIPT_CONTINUE;
    }

    public int makeDestroyer(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id objShip = null;
        objShip = space_create.createShip("star_destroyer_e3", getTransform_o2p(self));
        space_combat.setupCapitalShipFromTurretDefinition(objShip, "star_destroyer_e3");
        return SCRIPT_CONTINUE;
    }
}
