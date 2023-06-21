package script.library;/*
@Filename: script.library.
@Author: BubbaJoeX
@Purpose:
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.location;
import script.obj_id;

public class guild_station extends script.base_script
{
    public static final String ROOT_STATION_VAR = "guild_station.";
    public static final String STATION_OWNER_VAR = ".buildingId";
    public static final String STATION_OWNER_VARLIST = ".accessList";
    public static final int STATION_MAINT = 2400000;
    public guild_station()
    {
    }

    public obj_id getGuildStation(obj_id guildMember)
    {
        String varPath = ROOT_STATION_VAR + getGuildId(guildMember);
        return getObjIdObjVar(getPlanetByName("tatooine"), varPath + STATION_OWNER_VAR);
    }

    public int purchaseGuildStation(obj_id player)
    {
        int guildId = guild.getGuildId(player);
        if (guild.getGroupLeaderId(obj_id.getObjId(guildId)) != player)
        {
            broadcast(player, "You must be the leader of your guild to purchase a guild station.");
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(player, "guild_station_master_setup"))
        {
            obj_id emptyStation = getEmptyGuildStation(player);
            if (emptyStation == null)
            {
                broadcast(player, "There are currently no empty guild stations available.");//shouldn't happen but ya never know.
                return SCRIPT_CONTINUE;
            }
            else
            {
                setObjVar(getPlanetByName("tatooine"), ROOT_STATION_VAR + getGuildId(player) + STATION_OWNER_VAR, emptyStation);
                setObjVar(emptyStation, "guild_station_master_setup", true);
            }
            broadcast(player, "You have purchased a guild station. To travel to your station, use the travel terminal assigned by your Guild Leader.");
        }
        else
        {
            broadcast(player, "You already have a guild station.");
        }
        return SCRIPT_CONTINUE;
    }

    public obj_id getEmptyGuildStation(obj_id player)
    {
        location origin = new location();
        origin.area = getLocation(player).area; //dungeon_hub
        origin.x = 0;
        origin.y = 0;
        origin.z = 0;
        obj_id[] stations = getAllObjectsWithTemplate(origin, 8000, "object/building/guild/guild_station.iff");
        if (stations != null)
        {
            for (obj_id station : stations)
            {
                if (!hasObjVar(station, "guild_station_master_setup"))
                {
                    return station;
                }
            }
        }
        return null;
    }
}
