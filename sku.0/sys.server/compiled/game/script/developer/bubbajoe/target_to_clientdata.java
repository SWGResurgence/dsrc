package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.target_to_clientdata
@Author: BubbaJoeX
@Purpose: This script takes a target's equipment and palvars and saves them to a clientdata file.

@WIP: This script is a work in progress and is not yet functional.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class target_to_clientdata extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public void createFile(String filename, String contents)
    {
        String path = "/home/swg/swg-main/data/sku.0/sys.client/compiled/game/clientdata/";
    }
}
