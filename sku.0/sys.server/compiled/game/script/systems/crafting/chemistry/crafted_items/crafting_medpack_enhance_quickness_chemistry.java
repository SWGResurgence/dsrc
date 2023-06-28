package script.systems.crafting.chemistry.crafted_items;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.craftinglib;
import script.resource_weight;

public class crafting_medpack_enhance_quickness_chemistry extends script.systems.crafting.chemistry.crafting_enhance_quickness_chemical
{
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS =
            {
                    "science_doctor_novice"
            };
    public static final String[] ASSEMBLY_SKILL_MODS =
            {
                    "medicine_assembly"
            };
    public static final String[] EXPERIMENT_SKILL_MODS =
            {
                    "medicine_experimentation",
                    "medicine_complexity"
            };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("power", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
                            }),
                    new resource_weight("charges", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
                            }),
                    new resource_weight("duration", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2)
                            }),
                    new resource_weight("skillModMin", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
                            }),
                    new resource_weight("hitPoints", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 2)
                            })
            };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("power", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
                            }),
                    new resource_weight("charges", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
                            }),
                    new resource_weight("duration", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 3),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2)
                            }),
                    new resource_weight("skillModMin", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
                            }),
                    new resource_weight("hitPoints", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
                                    new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 2)
                            })
            };

    public crafting_medpack_enhance_quickness_chemistry()
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
