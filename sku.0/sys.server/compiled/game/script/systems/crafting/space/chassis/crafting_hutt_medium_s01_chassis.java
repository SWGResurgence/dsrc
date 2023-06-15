package script.systems.crafting.space.chassis;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.craftinglib;
import script.resource_weight;

public class crafting_hutt_medium_s01_chassis extends script.systems.crafting.space.chassis.crafting_base_hutt_medium_s01_chassis
{
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS =
            {
                    "crafting_shipwright_novice"
            };
    public static final String[] ASSEMBLY_SKILL_MODS =
            {
                    "chassis_assembly"
            };
    public static final String[] EXPERIMENT_SKILL_MODS =
            {
                    "chassis_experimentation"
            };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("massMax", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            }),
                    new resource_weight("hp", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            })
            };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("massMax", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            }),
                    new resource_weight("hp", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            })
            };

    public crafting_hutt_medium_s01_chassis()
    {
    }

    public String[] getRequiredSkills() throws InterruptedException
    {
        return REQUIRED_SKILLS;
    }

    public String[] getAssemblySkillMods() throws InterruptedException
    {
        return ASSEMBLY_SKILL_MODS;
    }

    public String[] getExperimentSkillMods() throws InterruptedException
    {
        return EXPERIMENT_SKILL_MODS;
    }

    public resource_weight[] getResourceMaxResourceWeights() throws InterruptedException
    {
        return OBJ_MAX_ATTRIBUTE_RESOURCES;
    }

    public resource_weight[] getAssemblyResourceWeights() throws InterruptedException
    {
        return OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;
    }
}
