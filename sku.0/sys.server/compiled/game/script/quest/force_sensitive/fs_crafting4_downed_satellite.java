package script.quest.force_sensitive;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class fs_crafting4_downed_satellite extends script.base_script
{
    public static final String COMPUTER_CORE_TEMPLATE = "object/tangible/item/quest/force_sensitive/fs_crafting4_computer_core.iff";

    public fs_crafting4_downed_satellite()
    {
    }

    public int cleanUpSelf(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }

    public boolean canSearch(obj_id self, obj_id player) throws InterruptedException
    {
        return false;
    }
}
