package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose:
*/

import script.*;

public class sync extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        String DESC = getStringObjVar(self, "null_desc");
        if (DESC == null)
        {
            DESC = "An unknown object";
        }
        else
        {
            string_id descMem = new string_id(getStringObjVar(self, "null_desc"));
            setDescriptionStringId(self, descMem);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        String DESC = getStringObjVar(self, "null_desc");
        if (DESC == null)
        {
            DESC = "An unknown object";
        }
        else
        {
            string_id descMem = new string_id(getStringObjVar(self, "null_desc"));
            setDescriptionStringId(self, descMem);
        }
        return SCRIPT_CONTINUE;
    }
}
