package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose:
*/

import script.*;
import script.library.sui;

@SuppressWarnings("unused")
public class cell_lock extends script.base_script
{
    public obj_id getCurrentCellName(obj_id player) throws InterruptedException
    {
        return getContainedBy(player);
    }
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest( obj_id self, obj_id player, menu_info mi ) throws InterruptedException
    {
        if (isGod(player))
        {
            mi.addRootMenu( menu_info_types.SERVER_MENU1, new string_id( "Lock Room" ));
            mi.addRootMenu( menu_info_types.SERVER_MENU2, new string_id( "Unlock Room" ));
            mi.addRootMenu( menu_info_types.SERVER_MENU3, new string_id( "Add to Room Permissions" ));
            mi.addRootMenu( menu_info_types.SERVER_MENU4, new string_id( "Remove from Room Permissions" ));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int menu) throws InterruptedException
    {
        if (isGod(player))
        {
            if (menu == menu_info_types.SERVER_MENU1)
            {
                lockRoom(self, player);
            }
            else if (menu == menu_info_types.SERVER_MENU2)
            {
                unlockRoom(self, player);
            }
            else if (menu == menu_info_types.SERVER_MENU3)
            {
                addPlayerToRoomPermissions(self, player);
            }
            else if (menu == menu_info_types.SERVER_MENU4)
            {
                removePlayerFromRoomPermissions(self, player);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public void unlockRoom(obj_id self, obj_id player) throws InterruptedException
    {
        setObjVar(getCurrentCellName(self), "roomLocked", false);
        broadcast(player, "You have unlocked this room.");
    }

    public void removePlayerFromRoomPermissions(obj_id self, obj_id player) throws InterruptedException
    {
        sui.msgbox(self, player, "Enter the name of the player you wish to remove from the room's permissions.", sui.OK_CANCEL, "Remove Player from Room", "handleRemovePlayerFromRoomPermissions");
    }

    public void addPlayerToRoomPermissions(obj_id self, obj_id player) throws InterruptedException
    {
        sui.msgbox(self, player, "Please enter the name of the player you wish to add to the room's permissions.", sui.OK_CANCEL, "Add Player to Room", "handleAddPlayerToRoomPermissions");
    }

    public void lockRoom(obj_id self, obj_id player) throws InterruptedException
    {
        setObjVar(getCurrentCellName(self), "roomLocked", true);
        broadcast(player, "You have locked this room.");
    }
    public int handleAddPlayerToRoomPermissions(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id house = getTopMostContainer(self);
        obj_id cell = getCellId(house, getCellName(self));
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String playerName = sui.getInputBoxText(params);
        obj_id player = getPlayerIdFromFirstName(playerName);
        if (player == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(cell, "roomPermissions"))
        {
            obj_id[] roomPermissions = getObjIdArrayObjVar(cell, "roomPermissions");
            obj_id[] newRoomPermissions = new obj_id[roomPermissions.length + 1];
            System.arraycopy(roomPermissions, 0, newRoomPermissions, 0, roomPermissions.length);
            newRoomPermissions[newRoomPermissions.length - 1] = player;
            setObjVar(cell, "roomPermissions", newRoomPermissions);
        }
        else
        {
            obj_id[] roomPermissions = new obj_id[1];
            roomPermissions[0] = player;
            setObjVar(cell, "roomPermissions", roomPermissions);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleRemovePlayerFromRoomPermissions(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id house = getTopMostContainer(self);
        obj_id cell = getCellId(house, getCellName(self));
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String playerName = sui.getInputBoxText(params);
        obj_id player = getPlayerIdFromFirstName(playerName);
        if (player == null)
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(cell, "roomPermissions"))
        {
            obj_id[] roomPermissions = getObjIdArrayObjVar(cell, "roomPermissions");
            obj_id[] newRoomPermissions = new obj_id[roomPermissions.length - 1];
            int j = 0;
            for (obj_id roomPermission : roomPermissions)
            {
                if (roomPermission != player)
                {
                    newRoomPermissions[j] = roomPermission;
                    j++;
                }
            }
            setObjVar(cell, "roomPermissions", newRoomPermissions);
        }
        return SCRIPT_CONTINUE;
    }
}
