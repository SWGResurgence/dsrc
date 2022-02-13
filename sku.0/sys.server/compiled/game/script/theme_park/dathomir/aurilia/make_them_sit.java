package script.theme_park.dathomir.aurilia;

import script.dictionary;
import script.library.utils;
import script.obj_id;

import java.util.ArrayList;
import java.util.List;

public class make_them_sit extends script.base_script
{
    public int doSpawnEvent(obj_id self, dictionary params) throws InterruptedException
    {
        messageTo(self, "handleAreaSpawnerHaveThemSit", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int handleAreaSpawnerHaveThemSit(obj_id self, dictionary params) throws InterruptedException
    {
        List debugSpawnList = new ArrayList<obj_id>();
        if (utils.hasScriptVar(self, "debugSpawnList"))
        {
            debugSpawnList = utils.getResizeableObjIdArrayScriptVar(self, "debugSpawnList");
        }
        if (debugSpawnList != null && debugSpawnList.size() > 0)
        {
            for (Object o : debugSpawnList) {
                obj_id spawnedNpc = ((obj_id) o);
                if (isIdValid(spawnedNpc) && exists(spawnedNpc)) {
                    if (!hasScript(spawnedNpc, "theme_park.dathomir.aurilia.have_a_seat")) {
                        attachScript(spawnedNpc, "theme_park.dathomir.aurilia.have_a_seat");
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
