package script.systems.crafting.bio_engineer;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class dna_component_attrib extends script.base_script
{
    public dna_component_attrib()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "");
        return SCRIPT_CONTINUE;
    }
}
