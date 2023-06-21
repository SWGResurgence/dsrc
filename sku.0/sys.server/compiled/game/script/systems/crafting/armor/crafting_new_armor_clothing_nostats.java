package script.systems.crafting.armor;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.draft_schematic;
import script.obj_id;

public class crafting_new_armor_clothing_nostats extends script.systems.crafting.crafting_base
{
    public static final String VERSION = "v0.00.00";

    public crafting_new_armor_clothing_nostats()
    {
    }

    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes, dictionary craftingValuesDictionary) throws InterruptedException
    {
        super.calcAndSetPrototypeProperties(prototype, itemAttributes, craftingValuesDictionary);
    }

    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes) throws InterruptedException
    {
        for (draft_schematic.attribute itemAttribute : itemAttributes)
        {
            if (itemAttribute == null)
            {
                continue;
            }
            if (!calcAndSetPrototypeProperty(prototype, itemAttribute))
            {
            }
        }
    }
}
