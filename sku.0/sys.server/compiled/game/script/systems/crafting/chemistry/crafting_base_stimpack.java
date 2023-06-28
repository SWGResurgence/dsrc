package script.systems.crafting.chemistry;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.draft_schematic;
import script.library.craftinglib;
import script.obj_id;

public class crafting_base_stimpack extends script.systems.crafting.crafting_base
{
    public static final String VERSION = "v1.00.00";

    public crafting_base_stimpack()
    {
    }

    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes, dictionary craftingValuesDictionary) throws InterruptedException
    {
        super.calcAndSetPrototypeProperties(prototype, itemAttributes, craftingValuesDictionary);
    }

    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes) throws InterruptedException
    {
        debugServerConsoleMsg(null, "Beginning assembly-phase prototype property setting");
        for (draft_schematic.attribute itemAttribute : itemAttributes)
        {
            if (itemAttribute == null)
            {
                continue;
            }
            if (!calcAndSetPrototypeProperty(prototype, itemAttribute))
            {
                if (((itemAttribute.name).getAsciiId()).equals("power"))
                {
                    setObjVar(prototype, "healing.power", (int) itemAttribute.currentValue);
                }
                else if (((itemAttribute.name).getAsciiId()).equals("charges"))
                {
                    setCount(prototype, (int) itemAttribute.currentValue);
                }
                else
                {
                    setObjVar(prototype, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + (itemAttribute.name).getAsciiId(), itemAttribute.currentValue);
                }
            }
        }
    }
}
