package script.systems.crafting.space.armor;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.craftinglib;
import script.resource_weight;

public class crafting_armor_reinforcement_panel extends script.systems.crafting.space.armor.crafting_base_space_armor_component
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
                    new resource_weight("hitPointsMax", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_HEAT_RESIST, 1)
                            }),
                    new resource_weight("armorHpMax", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_HEAT_RESIST, 1)
                            }),
                    new resource_weight("mass", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            })
            };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("hitPointsMax", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_HEAT_RESIST, 1)
                            }),
                    new resource_weight("armorHpMax", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_HEAT_RESIST, 1)
                            }),
                    new resource_weight("mass", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
                            })
            };

    public crafting_armor_reinforcement_panel()
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
