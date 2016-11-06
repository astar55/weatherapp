package controllers;

import play.Logger;

import javax.json.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataGet {

    public String obtainData(String zip) {
        try {
            String link = "http://apidev.accuweather.com/locations/v1/search?q="+zip+"&apikey=hoArfRosT1215";
            URL url = new URL(link);
            InputStream is = url.openStream();
            JsonReader rdr = Json.createReader(is);
            JsonArray array = rdr.readArray();
            String key = array.getValuesAs(JsonObject.class).get(0).getString("Key");
            link = "http://apidev.accuweather.com/currentconditions/v1/"+key+".json?language=en&apikey=hoArfRosT1215";
            url = new URL(link);
            is = url.openStream();
            rdr = Json.createReader(is);
            array = rdr.readArray();
            return array.toString();
        }
        catch (Exception e) {
            Logger.error("Obtain Data failed" + e.getMessage());
        }
        return "";
    }

}

