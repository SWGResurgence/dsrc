package script.item;/*
@Filename: script.item.item_nuker
@Author: BubbaJoeX
@Purpose: 1 way storage bin. All objects will get deleted once put into.
@Note: Needs non-player controlled volume container..
*/

import script.obj_id;
import script.location;
import script.menu_info_types;
import script.menu_info_data;
import script.dictionary;

public class item_nuker extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToRecieveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item)
    {
        if (isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (isIdValid(item))
        {
            if (hasObjVar(self, "qa.noclear"))
            {
                return SCRIPT_CONTINUE;
            }
            if (hasObjVar(self, "timestamp"))
            {
                return SCRIPT_CONTINUE;
            }
            destroyObject(item);
            broadcast(transferer, "You have destroyed " + getEncodedName(item) + ".");
        }
        return SCRIPT_CONTINUE;
    }
}
