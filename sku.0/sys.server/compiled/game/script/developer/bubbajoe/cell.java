package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.cell
@Author: BubbaJoeX
@Purpose:
*/

import script.obj_id;

public class cell extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToRecieveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item))
        {
            if (isAllowedInCurrentCell(item, getContainedBy(item)))
            {
                return SCRIPT_CONTINUE;
            }
            else
            {
                broadcast(transferer, "You are not allowed in this room.");
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }

    public boolean isAllowedInCurrentCell(obj_id player, obj_id cell) throws InterruptedException
    {
        obj_id currentCell = cell;
        if (currentCell == null)
        {
            return false;
        }
        if (hasObjVar(currentCell, "roomPermissions"))
        {
            obj_id[] roomPermissions = getObjIdArrayObjVar(currentCell, "roomPermissions");
            for (int i = 0; i < roomPermissions.length; i++)
            {
                if (roomPermissions[i] == player)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
