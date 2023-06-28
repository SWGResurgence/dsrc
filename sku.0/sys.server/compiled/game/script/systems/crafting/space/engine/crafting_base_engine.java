package script.systems.crafting.space.engine;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.draft_schematic;
import script.library.craftinglib;
import script.library.space_crafting;
import script.obj_id;

public class crafting_base_engine extends script.systems.crafting.crafting_base
{
    public crafting_base_engine()
    {
    }

    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes, dictionary craftingValuesDictionary) throws InterruptedException
    {
        for (draft_schematic.attribute itemAttribute : itemAttributes)
        {
            if (itemAttribute == null)
            {
                continue;
            }
            if (((itemAttribute.name).getAsciiId()).equals("mass") || ((itemAttribute.name).getAsciiId()).equals("energy_maintenance"))
            {
                itemAttribute.currentValue = (itemAttribute.minValue + itemAttribute.maxValue) - itemAttribute.currentValue;
            }
        }
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
                if (((itemAttribute.name).getAsciiId()).equals("hitPointsMax"))
                {
                    space_crafting.setComponentMaximumHitpoints(prototype, itemAttribute.currentValue);
                    space_crafting.setComponentCurrentHitpoints(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("efficiency"))
                {
                    space_crafting.setComponentGeneralEfficiency(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("mass"))
                {
                    space_crafting.setComponentMass(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("armorHpMax"))
                {
                    space_crafting.setComponentMaximumArmorHitpoints(prototype, itemAttribute.currentValue);
                    space_crafting.setComponentCurrentArmorHitpoints(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("energy_efficiency"))
                {
                    space_crafting.setComponentEnergyEfficiency(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("energy_maintenance"))
                {
                    space_crafting.setComponentEnergyMaintenance(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("engine_pitch"))
                {
                    space_crafting.setEngineMaximumPitch(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("engine_yaw"))
                {
                    space_crafting.setEngineMaximumYaw(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("engine_roll"))
                {
                    space_crafting.setEngineMaximumRoll(prototype, itemAttribute.currentValue);
                }
                if (((itemAttribute.name).getAsciiId()).equals("engine_speed"))
                {
                    space_crafting.setEngineMaximumSpeed(prototype, itemAttribute.currentValue);
                }
                else
                {
                    setObjVar(prototype, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + (itemAttribute.name).getAsciiId(), itemAttribute.currentValue);
                }
            }
        }
    }
}
