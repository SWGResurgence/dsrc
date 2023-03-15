package script.event.gjpud;/*
@Filename: script.event.gjpud.gating
@Author: BubbaJoeX
@Purpose:
*/

import script.obj_id;
import script.location;
import script.menu_info_types;
import script.menu_info_data;
import script.dictionary;

public class gating extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        int gating = getIntObjVar(item, "gjpud.gating");
        if (gating != 1)
        {
            sendSystemMessageTestingOnly(item, "It would be impolite to enter a private residence.");
            return SCRIPT_OVERRIDE;
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Mos Deltro Housing");
        return SCRIPT_CONTINUE;
    }
}
