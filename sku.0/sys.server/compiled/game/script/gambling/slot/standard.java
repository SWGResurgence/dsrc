package script.gambling.slot;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.gambling;
import script.obj_id;

public class standard extends script.gambling.base.slot
{
    private static final String GAME_TYPE = "slot_standard";
    public static final String TBL = "datatables/gambling/slot/" + GAME_TYPE + ".iff";
    public standard()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String gameType = GAME_TYPE;
        if (hasObjVar(self, gambling.VAR_PREDEFINED_TYPE))
        {
            gameType = getStringObjVar(self, gambling.VAR_PREDEFINED_TYPE);
        }
        gambling.initializeTable(self, gameType);
        return super.OnInitialize(self);
    }
}
