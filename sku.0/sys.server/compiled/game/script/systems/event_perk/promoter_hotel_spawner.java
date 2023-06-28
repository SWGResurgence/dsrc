package script.systems.event_perk;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.create;
import script.library.locations;
import script.location;
import script.obj_id;

public class promoter_hotel_spawner extends script.base_script
{
    public promoter_hotel_spawner()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "event_perk.promoter"))
        {
            location here = getLocation(self);
            // String myPlanet = getCurrentSceneName();
            String myCity = locations.getCityName(here);
            obj_id spawnCell = getCellId(self, "r10");
            location spawnPoint = getLocation(self);
            spawnPoint.x = -23.0f;
            spawnPoint.y = 1.6f;
            spawnPoint.z = -15.0f;
            spawnPoint.cell = spawnCell;
            if (!isIdValid(spawnCell))
            {
                return SCRIPT_CONTINUE;
            }
            // there's a special NPC (Zekka Thyne) in the Coronet hotel so avoid spawning the promoter on top of him.
            // there's also Pex (an elite storyteller vendor) in the Theed and Bestine hotels so avoid spawning the promoter on top of him too.
            if (myCity != null && (myCity.equals("coronet") || myCity.equals("narmle") || myCity.equals("theed") || myCity.equals("bestine")))
            {
                spawnPoint.x = -25.3f;
                spawnPoint.y = 1.6f;
                spawnPoint.z = -5.5f;
            }
            if (myCity.equals("dearic"))
            {
                spawnPoint.x = 0.8f;
                spawnPoint.y = 1.0f;
                spawnPoint.z = -3.2f;
                spawnPoint.cell = getCellId(self, "r4");
            }
            obj_id template = create.object("object/tangible/spawning/static_npc/event_promoter_neutral.iff", spawnPoint);
            setObjVar(self, "event_perk.promoter", template);
        }
        return SCRIPT_CONTINUE;
    }
}
