package script.systems.crafting.weapon.core;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.craftinglib;
import script.resource_weight;

public class crafting_new_weapon_core extends script.systems.crafting.weapon.crafting_new_weapon_core
{
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS =
            {
                    "crafting_weaponsmith_novice"
            };
    public static final String[] ASSEMBLY_SKILL_MODS =
            {
                    "weapon_assembly"
            };
    public static final String[] EXPERIMENT_SKILL_MODS =
            {
                    "weapon_experimentation"
            };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("coreQualityLow", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            }),
                    new resource_weight("coreQualityHigh", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            })
            };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("coreQualityLow", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            }),
                    new resource_weight("coreQualityHigh", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            })
            };

    public crafting_new_weapon_core()
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
