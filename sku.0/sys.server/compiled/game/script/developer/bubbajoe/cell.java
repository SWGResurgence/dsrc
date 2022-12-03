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
            if (isAllowedInCurrentCell(item))
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
    public boolean isAllowedInCurrentCell(obj_id player) throws InterruptedException
    {
        obj_id currentCell = getContainedBy(player);
        if (currentCell == null)
        {
            return false;
        }
        if (currentCell == getTopMostContainer(player))
        {
            return true;
        }
        if (hasObjVar(currentCell, "cell.owner"))
        {
            obj_id owner = getObjIdObjVar(currentCell, "cell.owner");
            if (owner == player)
            {
                return true;
            }
        }
        if (hasObjVar(currentCell, "cell.allowed"))
        {
            obj_id[] allowed = getObjIdArrayObjVar(currentCell, "cell.allowed");
            for (int i = 0; i < allowed.length; i++)
            {
                if (allowed[i] == player)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
