package script.theme_park.newbie_tutorial;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class new_player_ribbon extends script.base_script
{
    public static final String questNewbieStart = "quest/tatooine_eisley_legacy";
    public static final String questCrafterEntertainer = "quest/tatooine_eisley_noncombat";
    public new_player_ribbon()
    {
    }

    public int OnLogin(obj_id self) throws InterruptedException
    {
        detachScript(self, "theme_park.newbie_tutorial.new_player_ribbon");
        return SCRIPT_CONTINUE;
    }
}
