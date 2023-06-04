package script.developer;

import script.dictionary;
import script.library.sui;
import script.library.utils;
import script.obj_id;
import script.system_process;

import java.io.File;
import java.util.Date;

public class script_editor extends script.base_script
{
    public script_editor()
    {
    }

    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (!isGod(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (!text.startsWith("editScript"))
        {
            return SCRIPT_CONTINUE;
        }
        java.util.StringTokenizer st = new java.util.StringTokenizer(text);
        st.nextToken();
        String params = st.nextToken();
        if (params == null)
        {
            broadcast(self, "Usage: /editScript <script name>  e.g. /editScript test.qatool");
            return SCRIPT_OVERRIDE;
        }
        if (params.length() < 1)
        {
            broadcast(self, "Usage: /editScript <script name>  e.g. /editScript test.qatool");
            return SCRIPT_OVERRIDE;
        }
        String scriptBaseName = params;
        String prefix = "../../dsrc/sku.0/sys.server/compiled/game/script";
        String classPrefix = "../../data/sku.0/sys.server/compiled/game/script";
        String[] path = split(scriptBaseName, '.');
        String scriptClassName = classPrefix;
        String scriptFileName = prefix;
        int iter = 0;
        for (iter = 0; iter < path.length; ++iter)
        {
            scriptFileName += "/" + path[iter];
        }
        for (iter = 0; iter < path.length; ++iter)
        {
            scriptClassName += "/" + path[iter];
        }
        scriptClassName += ".class";
        scriptFileName += ".java";
        String scriptContents = file_access.readTextFile(scriptFileName);
        if (scriptContents == null)
        {
           broadcast(self,"Unable to read script file " + scriptFileName);
           return SCRIPT_OVERRIDE;
        }
        int page = createSUIPage("/Script.editScript", self, self);
        setSUIProperty(page, "pageText.text", "Font", "starwarslogo_optimized_56");
        setSUIProperty(page, "pageText.text", "Editable", "True");
        setSUIProperty(page, "bg.caption.text", "LocalText", "EDIT SCRIPT - " + scriptBaseName);
        subscribeToSUIEvent(page, sui_event_type.SET_onButton, "btnOk", "onScriptEditBtnOk");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "pageText.text", "LocalText");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "outputPage.text", "LocalText");
        setSUIAssociatedObject(page, self);
        boolean showResult = showSUIPage(page);
        flushSUIPage(page);
        String trackPage = "scriptFileName" + page;
        utils.setScriptVar(self, trackPage, scriptFileName);
        String trackScriptName = "scriptBase" + page;
        utils.setScriptVar(self, trackScriptName, scriptBaseName);
        String trackClassName = "classBase" + page;
        utils.setScriptVar(self, trackClassName, scriptClassName);
        return SCRIPT_OVERRIDE;
    }

    public int onScriptEditBtnOk(obj_id self, dictionary params) throws InterruptedException
    {
        if (!isGod(self))
        {
            return SCRIPT_CONTINUE;
        }
        String scriptContents = params.getString("pageText.text.LocalText");
        if (scriptContents == null)
        {
            broadcast(self, "could not get script from data source!");
        }
        String outputWindowText = "";
        if (params.getString("outputPage.text.LocalText") != null)
        {
        }
        if (scriptContents.length() > 0)
        {
            int pageId = params.getInt("pageId");
            if (pageId >= 0)
            {
                String scriptFileNameKey = "scriptFileName" + pageId;
                if (scriptFileNameKey != null)
                {
                    String scriptFileName = utils.getStringScriptVar(self, scriptFileNameKey);
                    if (scriptFileName != null)
                    {
                        outputWindowText += "--== Compiling " + scriptFileName + " ==--\n";

                        {
                            String scriptNameScriptVar = "scriptBase" + pageId;
                            String scriptName = utils.getStringScriptVar(self, scriptNameScriptVar);
                            String classNameScriptVar = "classBase" + pageId;
                            String className = utils.getStringScriptVar(self, classNameScriptVar);

                            {
                                if (file_access.writeTextFile(scriptFileName, scriptContents))
                                {
                                    Runtime run = Runtime.getRuntime();
                                    if (run != null)
                                    {
                                        try
                                        {
                                            String outputString = system_process.runAndGetOutput("ant compile_java", new File("../../"));
                                            if (outputString != null)
                                            {
                                                outputWindowText += outputString + "\n";
                                            }
                                            if (scriptName != null)
                                            {
                                                if (reloadScript(scriptName))
                                                {
                                                    Date d = new Date();
                                                    outputWindowText += "Script " + scriptName + " reloaded successfully at " + d + "\n";
                                                }
                                                else
                                                {
                                                    outputWindowText += "*** ERROR: Could not reload " + scriptName + "\n";
                                                }
                                            }
                                        } catch (Exception e)
                                        {
                                            outputWindowText += "*** ERROR: An exception occurred while trying to compile " + scriptFileName + " : " + e + "\n";
                                        }
                                    }
                                    else
                                    {
                                        outputWindowText += "*** ERROR: could not get runtime\n";
                                    }
                                }
                                else
                                {
                                    outputWindowText += "*** ERROR: could not write " + scriptFileName + "\n";
                                }
                            }
                        }
                    }
                    else
                    {
                        outputWindowText += "*** ERROR: could not retrieve script file name from " + scriptFileNameKey + "\n";
                    }
                }
                else
                {
                    outputWindowText += "*** ERROR: could not create file name key \"scriptFileName" + pageId + "\"\n";
                }
                setSUIProperty(pageId, "outputPage.text", "Text", outputWindowText);
                boolean showResult = showSUIPage(pageId);
                flushSUIPage(pageId);
                String scriptFileName = utils.getStringScriptVar(self, scriptFileNameKey);
                String addOutput = system_process.runAndGetOutput("git add .", new File("../../data"));
                String commitOutput = system_process.runAndGetOutput("git commit -m \"Script Editor: " + scriptFileName + new Date() + "\"", new File("../../data"));
                String pushOutput = system_process.runAndGetOutput("git push origin apotheosis", new File("../../data"));
                sui.msgbox(self, self, "Git Status: \n" + addOutput + "\n" +  commitOutput + pushOutput, sui.OK_ONLY, "Git Status", "noHandler");
            }
        }
        return SCRIPT_CONTINUE;
    }
}
