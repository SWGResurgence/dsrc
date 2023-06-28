package script.systems.gcw;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.library.*;
import script.obj_id;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class gcw_flag extends script.base_script
{
    public gcw_flag()//this script can be attached to NPCs, and enables pre-CU style "flagging" upon entering combat
    {
    }

    public int OnHateTargetAdded(obj_id self, obj_id target) throws InterruptedException
    {
        Vector allPlayersHatedList = new Vector();
        allPlayersHatedList.setSize(0);
        if (utils.hasScriptVar(self, "allPlayersEverHated"))
        {
            allPlayersHatedList = utils.getResizeableObjIdArrayScriptVar(self, "allPlayersEverHated");
        }
        utils.addElement(allPlayersHatedList, target);
        utils.setScriptVar(self, "allPlayersEverHated", allPlayersHatedList);
        trial.addNonInstanceFactionParticipant(target, self);
        return SCRIPT_CONTINUE;
    }
}