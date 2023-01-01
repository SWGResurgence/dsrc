package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.no_macro_volume
@Author: BubbaJoeX
@Purpose: Creates a no-macro volume around the object.
@Note: The range var needs to be set on the object you want to use (i.e. an egg) and the volume will be created around it.
*/

import script.location;
import script.obj_id;

public class no_macro_volume extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        if (!hasObjVar(self, "anti_macro_range"))
        {
            setObjVar(self, "anti_macro_range", 5.0f);
        }
        createTriggerVolume("anti_macro_volume", getFloatObjVar(self, "anti_macro_range"), true);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int setupVolume(obj_id self)
    {
        String VolumeName = "anti_macro_volume";
        location here = getLocation(self);
        createTriggerVolume(VolumeName, getFloatObjVar(self, "anti_afk_range"), true);
        return SCRIPT_CONTINUE;
    }
}
