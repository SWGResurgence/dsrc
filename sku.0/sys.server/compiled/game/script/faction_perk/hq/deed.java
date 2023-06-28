package script.faction_perk.hq;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;
import script.string_id;

public class deed extends script.faction_perk.base.factional_deed
{
    public deed()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        String faction = "imperial";
        if (getTemplateName(self).contains("_rebel"))
        {
            faction = "rebel";
        }
        string_id newFactionalizedName = new string_id("deed", getNameStringId(self).getAsciiId() + "_" + faction);
        setName(self, "");
        setName(self, newFactionalizedName);
        return SCRIPT_CONTINUE;
    }
}
