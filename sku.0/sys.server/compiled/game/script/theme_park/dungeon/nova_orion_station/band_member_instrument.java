package script.theme_park.dungeon.nova_orion_station;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.utils;
import script.obj_id;

public class band_member_instrument extends script.base_script
{
    public band_member_instrument()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        messageTo(self, "giveBandMemberAnIntrument", null, 1, false);
        return SCRIPT_CONTINUE;
    }

    public int giveBandMemberAnIntrument(obj_id self, dictionary params) throws InterruptedException
    {
        String myInstrument = utils.getStringObjVar(self, "myInstrument");
        if (myInstrument != null || myInstrument.length() > 0)
        {
            obj_id instrument = createObject(myInstrument, self, "");
            equip(instrument, self);
        }
        return SCRIPT_CONTINUE;
    }
}
