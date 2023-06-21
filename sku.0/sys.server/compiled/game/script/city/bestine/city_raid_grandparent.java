package script.city.bestine;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.location;
import script.obj_id;

public class city_raid_grandparent extends script.base_script
{
    public static final String MASTER_OBJECT_TEMPLATE = "object/tangible/poi/tatooine/bestine/bestine_city_raid_spawner_object.iff";

    public city_raid_grandparent()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        messageTo(self, "bootStrap", null, 10, false);
        return SCRIPT_CONTINUE;
    }

    public int bootStrap(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id[] objRaidSpawners = getAllObjectsWithTemplate(getLocation(self), 10, MASTER_OBJECT_TEMPLATE);
        if ((objRaidSpawners != null) && (objRaidSpawners.length != 0))
        {
            setObjVar(self, "objRaidSpawner", objRaidSpawners[0]);
        }
        else
        {
            location locTest = getLocation(self);
            locTest.x = locTest.x + 1;
            obj_id objRaidSpawner = createObject(MASTER_OBJECT_TEMPLATE, locTest);
            persistObject(objRaidSpawner);

            setObjVar(self, "objRaidSpawner", objRaidSpawner);
            String strDataTable = getStringObjVar(self, "strDataTable");
            setObjVar(objRaidSpawner, "strDataTable", strDataTable);
        }
        return SCRIPT_CONTINUE;
    }
}
