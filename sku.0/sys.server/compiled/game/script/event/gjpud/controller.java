package script.event.gjpud;/*
@Filename: script.event.ggpud.
@Author: BubbaJoeX
@Purpose: Galactic Junk Pickup Day 2023
@ This script spawns junk objects around the planet (8km x 8km) in even dispersions, for the players to click on and destroy for a chance to get TCG items, or just an increment on your pickup count.
@ This script should be used no later than 05/01/2023 but not before 03/01/2023
*/

import script.*;
import java.lang.reflect.InvocationTargetException;

public class controller extends script.base_script
{
    public float PLANETSIDE = 16000.0f;
    public int JUNK_LIMIT = 1000;
    public String[] NAME_VARIATIONS = {
            "Rugged Scrap",
            "Rusty Scrap",
            "Solid Scrap",
            "Dented Scrap",
            "Oxidized Scrap",
            "Dusty Scrap",
            "Tarnished Scrap",
    };
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest( obj_id self, obj_id player, menu_info mi )
    {
        if (isGod(player))
        {
            int main = mi.addRootMenu( menu_info_types.ITEM_USE, new string_id("Galactic Junk Pickup Day"));
            mi.addSubMenu(main, menu_info_types.SERVER_MENU1, new string_id("Stats"));
            mi.addSubMenu(main, menu_info_types.SERVER_MENU2, new string_id("Create Junkfield"));
            mi.addSubMenu(main, menu_info_types.SERVER_MENU3, new string_id( "Force Cleanup"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException, InvocationTargetException
    {
        if (isGod(player))
        {
            if (item == menu_info_types.SERVER_MENU1)
            {
                String prompt = "Junkfield Stats:\n";
                prompt += "Junkfield Size: 7.25km x 7.25km\n";
                prompt += "Junkfield Center: " + getLocation(self).toClipboardFormat() + "\n";
                prompt += "Junkfield Objects: " + getJunkCount(self) + "\n";
                prompt += "Junkfield Limit: " + JUNK_LIMIT + "\n";

                prompt += "Junkfield Variations: 5 templates with " + NAME_VARIATIONS.length + " different naming variations.\n";
                int pid = createSUIPage("/Script.messageBox", player, player, "noHandler");
                setSUIProperty(pid, "bg.caption.lblTitle", "Text", "Junkfield Stats");
                setSUIProperty(pid, "Prompt.lblPrompt", "Font", "bold_15");
                setSUIProperty(pid, "Prompt.lblPrompt", "Editable", "true");
                setSUIProperty(pid, "Prompt.lblPrompt", "Text", prompt);
                subscribeToSUIProperty(pid, "btnOk", "Ok");
                showSUIPage(pid);
            }
            if (item == menu_info_types.SERVER_MENU2)
            {
                spawnJunk(self, player);
            }
            else if (item == menu_info_types.SERVER_MENU3)
            {
                destroyJunk(self);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int spawnJunk(obj_id self, obj_id player) throws InterruptedException
    {
        for (int i = 0; i <= JUNK_LIMIT; i++)
        {
            location junkLoc = getLocation(self);
            junkLoc.x = junkLoc.x + (rand(-7250.0f, 7250.0f));
            junkLoc.z = junkLoc.z + (rand(-7250.0f, 7250.0f));
            junkLoc.y = getHeightAtLocation(junkLoc.x, junkLoc.z);
            obj_id junk = createObject("object/tangible/gjpud/gjpud_junk_s0" + rand(1,5) + ".iff", junkLoc);
            attachScript(junk, "event.gjpud.junk");
            setObjVar(junk, "gjpudObject", 1);
            setYaw(junk, rand(0.0f, 359.9f));
            String randomName = NAME_VARIATIONS[rand(0, NAME_VARIATIONS.length - 1)];
            if (randomName == null)
            {
                randomName = "Junk";
            }
            setName(junk, randomName);
        };
        broadcast(player,"Junk spawned.");
        return SCRIPT_CONTINUE;
    }
    public int destroyJunk(obj_id self) throws InterruptedException
    {
        obj_id[] junkObjects = getObjectsInRange(getLocation(self), PLANETSIDE);
        for (int i = 0; i < junkObjects.length; i++)
        {
            if (hasObjVar(junkObjects[i], "gjpudObject"))
            {
                destroyObject(junkObjects[i]);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int getJunkCount(obj_id self) throws InterruptedException
    {
        int junkCount = 0;
        obj_id[] junkObjects = getAllObjectsWithScript(getLocation(self), PLANETSIDE, "event.gjpud.junk");
        for (int i = 0; i < junkObjects.length; i++)
        {
            if (hasObjVar(junkObjects[i], "gjpudObject"))
            {
                junkCount++;
            }
        }
        return junkCount;
    }
}
