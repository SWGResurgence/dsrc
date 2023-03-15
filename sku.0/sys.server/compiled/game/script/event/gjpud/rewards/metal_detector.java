package script.event.gjpud.rewards;/*
@Filename: script.event.gjpud.rewards.
@Author: BubbaJoeX
@Purpose: Detects invisible metal caches on planets. (Think treasure chests, but beefed up and less stupid)
*/

import script.*;
import script.library.city;

public class metal_detector extends script.base_script
{
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Scan Area"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            // TODO: 3/15/2023 bubba is tired. bubba needs rest. bubba is a social path.
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int getPlanetBonus(obj_id self)
    {
        String scene = getCurrentSceneName();
        switch (scene)
        {
            case "tatooine": //core
            case "naboo":
            case "corellia":
                return 1;
            case "lok": //secondary
            case "rori":
            case "talus":
                return 5;
            case "dantooine": //adventure med
            case "dathomir":
                return 10;
            case "endor": //adventure high
            case "yavin4":
                return 15;
            default:
                return 0;

        }
    }
}
