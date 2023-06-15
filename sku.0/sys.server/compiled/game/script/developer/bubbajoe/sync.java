package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose:
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;

public class sync extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        String DESC = getStringObjVar(self, "null_desc");
        if (DESC == null)
        {
            setDescriptionString(self, "An unknown object");
        }
        else
        {
            String descMem = getStringObjVar(self, "null_desc");
            setDescriptionString(self, descMem);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        String DESC = getStringObjVar(self, "null_desc");
        if (DESC == null)
        {
            setDescriptionString(self, "An unknown object");
        }
        else
        {
            String descMem = getStringObjVar(self, "null_desc");
            setDescriptionString(self, descMem);
        }
        return SCRIPT_CONTINUE;
    }
}
