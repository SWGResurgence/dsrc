package script.player;/*
@Filename: script.player.
@Author: BubbaJoeX
@Purpose: Allows players to file simple bug reports on the fly without having to log into the website.
@
@ REQUIRED REST API on MantisBT!!!
@
@Created: Wednesday, 6/21/2023, at 3:34 PM, 
@Copyright:
 *
 * © 1999-2001 Bootstrap Entertainment™®
 * © 2001-2003 Verant Interactive™®
 * © 2003-2011 Sony Online Entertainment™®
 * © 2023 SWG: Resurgence
 * All Rights Reserved, No Copyright Infringement Intended, Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;
import script.location;
import script.menu_info_types;
import script.menu_info_data;
import script.dictionary;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class player_qa extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int cmdFileBugReport(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        openReport(self);
        return SCRIPT_CONTINUE;
    }

    public int openReport(obj_id self) throws InterruptedException
    {
        int page = createSUIPage("/Script.editScript", self, self);
        setSUIProperty(page, "pageText.text", "Font", "starwarslogo_optimized_56");
        setSUIProperty(page, "pageText.text", "Editable", "True");
        setSUIProperty(page, "pageText.text", "GetsInput", "True");
        String bottomBox = "";
        bottomBox += "\n" + getPlayerName(self) + "\n" + getLocation(self).toReadableFormat(true) + getCalendarTimeStringLocal_YYYYMMDDHHMMSS(getCalendarTime());
        setSUIProperty(page, "outputPage.text", "Text", bottomBox);
        setSUIProperty(page, "btnOk", "Text", "Submit Bug Report");
        setSUIProperty(page, "bg.caption.text", "LocalText", "Bug Report");
        subscribeToSUIEvent(page, sui_event_type.SET_onButton, "btnOk", "submitReport");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "pageText.text", "LocalText");
        subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "outputPage.text", "LocalText");
        setSUIAssociatedObject(page, self);
        showSUIPage(page);
        flushSUIPage(page);
        if (!utils.hasScriptVar(self, "reportId"))
        {
            utils.setScriptVar(self, "reportId", page);
        }
        setObjVar(self, "reportId", page);
        return SCRIPT_OVERRIDE;
    }

    public int submitReport(obj_id self, dictionary params) throws IOException
    {
        String summary = params.getString("pageText.text");
        String description = params.getString("outputPage.text");
        URL url = new URL("https://swgresurgence.com/bugs/api/rest/");
        HttpURLConnection mantisConnection = (HttpURLConnection) url.openConnection();
        mantisConnection.setDoOutput(true);
        mantisConnection.setRequestMethod("POST");
        mantisConnection.setRequestProperty("Content-Type", "application/json");
        mantisConnection.setRequestProperty("Authorization", "a06jSe36gkG7uHscL31gHdnkc_cpizmW");
        String input = "{\"summary\": \"" + summary + "\", \"description\": \"" + description + "\", \"category\": { \"id\": \"138\" }, \"project\": { \"id\": 2 }, \"tags\": {\"name:\": \"Scripted\"} }";
        OutputStream os = mantisConnection.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        if (mantisConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED)
        {
            broadcast(self, "Failed to parse response from Mantis Bug Tracker, please try again later.");
            throw new RuntimeException("Failed : HTTP error code : " + mantisConnection.getResponseCode());
        }
        else
        {
            broadcast(self, "Report submitted successfully!");
        }
        mantisConnection.disconnect();
        return SCRIPT_CONTINUE;
    }

}
