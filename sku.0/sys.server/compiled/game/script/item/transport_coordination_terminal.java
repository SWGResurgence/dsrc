package script.item;/*
@Filename: script.item.
@Author: BubbaJoeX
@Purpose: Takes all the ITV objvars from the contents of this item and puts them into a sui.listbox for players to click and warp to.

*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.ai_lib;
import script.library.create;
import script.library.sui;
import script.library.utils;

import java.util.Vector;

public class transport_coordination_terminal extends script.base_script
{
    public String VAR_ITV_LOCATION_ROOT = "travel_tcg.stationary_itv.location";
    public String VAR_ITV_NAME = "travel_tcg.stationary_itv.name";
    public String VAR_ITV_NAME_ONE = "travel_tcg.stationary_itv.name.1";
    public String VAR_ITV_NAME_TWO = "travel_tcg.stationary_itv.name.2";
    public String VAR_ITV_LOCATION_ONE = "travel_tcg.stationary_itv.location.1";
    public String VAR_ITV_LOCATION_TWO = "travel_tcg.stationary_itv.location.2";
    public String VAR_ITV_TRANSIT_PREFIX = "travel_tcg.hub.";
    public String VAR_ITV_TRANSIT_SUFFIX = ".location";

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
        if (isPlayer(transferer))
        {
            if (!hasObjVar(item, VAR_ITV_LOCATION_ROOT))
            {
                broadcast(transferer, "Either this is not a valid Instant Travel Vehicle or the locations have not been setup yet. Please try again. ");
                return SCRIPT_OVERRIDE;
            }
            else
            {
                broadcast(transferer, "Transit Coordination Terminal is now loading the ITV locations. Use \"Synchronize\" to refresh the list. ");
                return SCRIPT_CONTINUE;
            }
        }
        else
        {
            return SCRIPT_OVERRIDE;
        }
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Travel"));
        if (getOwner(self) == player)
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Synchronize"));
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        obj_id[] contents = getContents(self);
        int numLocations = contents.length;
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (contents != null)
            {
                for (int i = 0; i < contents.length; i++)
                {
                    if (hasObjVar(contents[i], VAR_ITV_LOCATION_ROOT))
                    {
                        setObjVar(self, VAR_ITV_TRANSIT_PREFIX + i + VAR_ITV_TRANSIT_SUFFIX, getLocationObjVar(contents[i], VAR_ITV_LOCATION_ROOT + i + ".1"));
                        setObjVar(self, VAR_ITV_TRANSIT_PREFIX + i + VAR_ITV_TRANSIT_SUFFIX, getStringObjVar(contents[i], VAR_ITV_NAME + i + ".1"));
                        setObjVar(self, VAR_ITV_TRANSIT_PREFIX + i + VAR_ITV_TRANSIT_SUFFIX, getLocationObjVar(contents[i], VAR_ITV_LOCATION_ROOT + i + ".2"));
                        setObjVar(self, VAR_ITV_TRANSIT_PREFIX + i + VAR_ITV_TRANSIT_SUFFIX, getStringObjVar(contents[i], VAR_ITV_NAME + i + ".2"));
                    }
                }
                broadcast(self, "Transit locations have been updated.");
            }
        }
        if (item == menu_info_types.ITEM_USE)
        {
            if (contents != null)
            {
                String[] locationNames = new String[numLocations];
                String[] descNames = new String[numLocations];
                for (int i = 0; i < contents.length; i++)
                {
                    if (hasObjVar(contents[i], VAR_ITV_LOCATION_ROOT))
                    {
                        locationNames[i] = String.valueOf(getLocationObjVar(contents[i], VAR_ITV_LOCATION_ROOT + i + ".1"));
                        locationNames[i++] = String.valueOf(getLocationObjVar(contents[i], VAR_ITV_LOCATION_ROOT + i + ".2"));
                        descNames[i++] = getStringObjVar(contents[i], VAR_ITV_NAME + i + ".1");
                        descNames[i++] = getStringObjVar(contents[i], VAR_ITV_NAME + i + ".2");
                    }
                }
                if (locationNames != null)
                {
                    sui.listbox(self, player, "Select a location to travel to.", sui.OK_CANCEL, "Travel", descNames, "handleLocationSelect");
                }
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int handleLocationSelect(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        int idx = sui.getListboxSelectedRow(params);
        return SCRIPT_CONTINUE;
    }
}
