package script.systems.crafting.weapon.lightsaber;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.draft_schematic;
import script.library.craftinglib;
import script.obj_id;
import script.resource_weight;

public class crafting_melee_lightsaber_two_handed_gen3 extends script.systems.crafting.weapon.crafting_base_lightsaber
{
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS =
            {
                    "class_forcesensitive_phase1_novice"
            };
    public static final String[] ASSEMBLY_SKILL_MODS =
            {
                    "jedi_saber_assembly"
            };
    public static final String[] EXPERIMENT_SKILL_MODS =
            {
                    "jedi_saber_experimentation"
            };
    public static final String[] APPEARANCES =
            {
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s1_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s2_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s3_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s4_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s5_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s6_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s7_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s8_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s9_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s10_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s11_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s12_gen3.iff",
                    "object/weapon/melee/2h_sword/crafted_saber/shared_sword_lightsaber_two_handed_s13_gen3.iff"
            };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES =
            {
                    new resource_weight("minDamage", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1)
                            }),
                    new resource_weight("maxDamage", new resource_weight.weight[]
                            {
                                    new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2),
                                    new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1)
                            })
            };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;

    public crafting_melee_lightsaber_two_handed_gen3()
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

    public String[] getAppearances(obj_id player, draft_schematic.slot[] slots) throws InterruptedException
    {
        return APPEARANCES;
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
