package script.systems.fs_quest.fs_quests_sad;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.utils;
import script.obj_id;

public class notify_killable extends script.base_script
{
    public static final String VAR_SPAWNED_BY = "quest_spawner.spawned_by";
    public static final String VAR_SPAWNER_PARENT = "quest_spawner.parent";
    public static final String VAR_NOTIFY_KILLABLE_OID = "quest_spawner.notify_killable_oid";

    public notify_killable()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        LOG("newquests", "notify_killable OnAttach");
        obj_id spawner = utils.getObjIdObjVar(self, VAR_SPAWNED_BY);
        if (spawner == null)
        {
            LOG("newquests", "notify_killable spawner is null");
            return SCRIPT_CONTINUE;
        }
        obj_id parent = utils.getObjIdObjVar(spawner, VAR_SPAWNER_PARENT);
        if (parent == null)
        {
            LOG("newquests", "notify_killable parent is null");
            return SCRIPT_CONTINUE;
        }
        utils.setObjVar(self, VAR_NOTIFY_KILLABLE_OID, parent);
        LOG("newquests", "notify_killable self = " + self + " spawner = " + spawner + " parent = " + parent);
        dictionary params = new dictionary();
        params.put("objId", self);
        messageTo(parent, "OnCreatedKillableObject", params, 1, true);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        LOG("newquests", "notify_killable OnIncapacitated");
        obj_id parent = utils.getObjIdObjVar(self, VAR_NOTIFY_KILLABLE_OID);
        if (parent == null)
        {
            LOG("newquests", "notify_killable OnIncapacitated parent is null");
            return SCRIPT_CONTINUE;
        }
        LOG("newquests", "notify_killable OnIncapacitated self = " + self + " parent = " + parent);
        dictionary params = new dictionary();
        params.put("objId", self);
        messageTo(parent, "OnIncapacitatedKillableObject", params, 1, true);
        return SCRIPT_CONTINUE;
    }
}
