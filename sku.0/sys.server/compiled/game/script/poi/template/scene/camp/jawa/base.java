package script.poi.template.scene.camp.jawa;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class base extends script.poi.template.scene.base.base_theater
{
    public static final String TBL = "datatables/poi/camp/jawa/setup.iff";

    public base()
    {
    }

    public int handleTheaterSetup(obj_id self, dictionary params) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
