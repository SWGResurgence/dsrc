package script.item;/*
@Filename: script.item.item_nuker
@Author: BubbaJoeX
@Purpose: 1 way storage bin. All objects will get deleted once put into.
@Note: Needs non-player controlled volume container..
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

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

    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item)
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
