package script.developer.soe.hnguyen;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.deltadictionary;
import script.dictionary;
import script.library.utils;
import script.obj_id;

import java.util.StringTokenizer;

public class scriptvar_test extends script.base_script
{
    public scriptvar_test()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnHearSpeech(obj_id self, obj_id objSpeaker, String strText) throws InterruptedException
    {
        if (objSpeaker != self)
        {
            return SCRIPT_CONTINUE;
        }
        if (strText.startsWith("ahh"))
        {
            StringTokenizer st = new StringTokenizer(strText);
            if (st.countTokens() == 2)
            {
                String command = st.nextToken();
                String id = st.nextToken();
                obj_id target = utils.stringToObjId(id);
                if (isIdValid(target))
                {
                    if (exists(target))
                    {
                        dictionary d = target.getScriptDictionary();
                        broadcast(self, "scriptDictionary for " + target + " are: " + d.toString());
                    }
                    else
                    {
                        broadcast(self, "ahh " + target + " - no object");
                    }
                }
                else
                {
                    broadcast(self, "ahh " + target + " - bad id");
                }
            }
        }
        else if (strText.startsWith("ohh"))
        {
            StringTokenizer st = new StringTokenizer(strText);
            if (st.countTokens() == 4)
            {
                String command = st.nextToken();
                String id = st.nextToken();
                String var = st.nextToken();
                String val = st.nextToken();
                obj_id target = utils.stringToObjId(id);
                if (isIdValid(target))
                {
                    if (exists(target))
                    {
                        dictionary d = target.getScriptDictionary();
                        d.put(var, val);
                        broadcast(self, "Setting scriptDictionary for " + target + " " + var + "=" + val);
                    }
                    else
                    {
                        broadcast(self, "ohh " + target + " - no object");
                    }
                }
                else
                {
                    broadcast(self, "ohh " + target + " - bad id");
                }
            }
        }
        else if (strText.startsWith("ah"))
        {
            StringTokenizer st = new StringTokenizer(strText);
            if (st.countTokens() == 2)
            {
                String command = st.nextToken();
                String id = st.nextToken();
                obj_id target = utils.stringToObjId(id);
                if (isIdValid(target))
                {
                    if (exists(target))
                    {
                        deltadictionary dctScriptVars = target.getScriptVars();
                        broadcast(self, "Scriptvars for " + target + " are: " + dctScriptVars.toString());
                    }
                    else
                    {
                        broadcast(self, "ah " + target + " - no object");
                    }
                }
                else
                {
                    broadcast(self, "ah " + target + " - bad id");
                }
            }
        }
        else if (strText.startsWith("oh"))
        {
            StringTokenizer st = new StringTokenizer(strText);
            if (st.countTokens() == 4)
            {
                String command = st.nextToken();
                String id = st.nextToken();
                String var = st.nextToken();
                String val = st.nextToken();
                obj_id target = utils.stringToObjId(id);
                if (isIdValid(target))
                {
                    if (exists(target))
                    {
                        utils.setScriptVar(target, var, val);
                        broadcast(self, "Setting scriptvar for " + target + " " + var + "=" + val);
                    }
                    else
                    {
                        broadcast(self, "oh " + target + " - no object");
                    }
                }
                else
                {
                    broadcast(self, "oh " + target + " - bad id");
                }
            }
        }
        else if (strText.equals("gc"))
        {
            broadcast(self, "telling Java to do garbage collection");
            System.gc();
        }
        else if (strText.equals("getGameTime"))
        {
            broadcast(self, "getGameTime() returns " + getGameTime());
        }
        return SCRIPT_CONTINUE;
    }
}
