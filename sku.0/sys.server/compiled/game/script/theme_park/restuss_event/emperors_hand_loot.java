package script.theme_park.restuss_event;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.ai_lib;
import script.library.static_item;
import script.library.utils;
import script.obj_id;

public class emperors_hand_loot extends script.base_script
{
    public emperors_hand_loot()
    {
    }

    public int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.legacy.hand");
        setObjVar(tatooine, "dungeon_finder.legacy.hand", "Active");
        return SCRIPT_CONTINUE;
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id tatooine = getPlanetByName("tatooine");
        removeObjVar(tatooine, "dungeon_finder.legacy.hand");
        setObjVar(tatooine, "dungeon_finder.legacy.hand", "Inactive");
        return SCRIPT_CONTINUE;
    }
}