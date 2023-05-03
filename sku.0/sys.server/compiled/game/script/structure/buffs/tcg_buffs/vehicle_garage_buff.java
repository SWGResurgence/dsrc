package script.structure.buffs.tcg_buffs;

import script.base_script;
import script.library.buff;
import script.library.utils;
import script.obj_id;

public class vehicle_garage_buff extends base_script
{
    public int OnReceivedItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && utils.isProfession(item, utils.TRADER))
        {
            buff.applyBuff(item, "tcg_garage_artisan_buff");
        }
        if (isPlayer(item) && utils.isProfession(item, utils.TRADER))
        {
            buff.applyBuff(item, "tcg_garage_armor_buff");
        }
        if (isPlayer(item) && utils.isProfession(item, utils.TRADER))
        {
            buff.applyBuff(item, "tcg_garage_weapon_buff");
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToLoseItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (isPlayer(item) && buff.hasBuff(item, "tcg_garage_artisan_buff"))
        {
            buff.removeBuff(item, "tcg_garage_artisan_buff");
        }
        if (isPlayer(item) && buff.hasBuff(item, "tcg_garage_armor_buff"))
        {
            buff.applyBuff(item, "tcg_garage_armor_buff");
        }
        if (isPlayer(item) && buff.hasBuff(item, "tcg_garage_weapon_buff"))
        {
            buff.applyBuff(item, "tcg_garage_weapon_buff");
        }
        return SCRIPT_CONTINUE;
    }
}
