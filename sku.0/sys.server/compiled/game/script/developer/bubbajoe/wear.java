package script.developer.bubbajoe;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class wear extends script.base_script
{
    public wear()
    {
    }

    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        equipOverride(item, self);
        if (isGod(giver))
        {
            broadcast(giver, "Attempting to give NPC : " + getEncodedName(self) + " item : " + getEncodedName(item));
        }
        String template = getTemplateName(item);
        if (template.contains("object/weapon/"))
        {
            return SCRIPT_OVERRIDE;
        }
        else if (template.contains("ring"))
        {
            return SCRIPT_OVERRIDE;
        }
        LOG("bubbajoe", "Trying to  attach  clothing to : " + getEncodedName(self) + " with item : " + getEncodedName(item));
        return SCRIPT_CONTINUE;
    }
}
