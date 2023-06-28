package script.systems.crafting.weapon.core;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.resource_weight;

public class crafting_comp_power_bit extends script.systems.crafting.weapon.crafting_new_base_weapon_component
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
            };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES =
            {
            };

    public crafting_comp_power_bit()
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
}
