package script.npc.celebrity;

import script.library.*;
import script.obj_id;

public class herald_spawner extends script.base_script
{
    public herald_spawner()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if(!instance.GEONOSIAN_BUNKER_ENABLED) {
            return SCRIPT_CONTINUE;
        }
        String spawn = getStringObjVar(self, "spawns");
        obj_id celeb = create.object(spawn, getLocation(self));
        setInvulnerable(celeb, true);
        ai_lib.setDefaultCalmBehavior(celeb, ai_lib.BEHAVIOR_SENTINEL);
        if (hasObjVar(self, "quest_script"))
        {
            String script = getStringObjVar(self, "quest_script");
            attachScript(celeb, script);
        }
        if (hasObjVar(self, "quest_table"))
        {
            String table = getStringObjVar(self, "quest_table");
            setObjVar(celeb, "quest_table", table);
        }
        if (hasObjVar(self, "npc_name"))
        {
            String name = getStringObjVar(self, "npc_name");
            if (name != null)
            {
                if (!name.equals(""))
                {
                    setName(celeb, name);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
