package org.oasis.healthcare;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.oasis.exception.URLCanNotFoundException;
import org.oasis.exception.URLFormatException;
import org.oasis.util.SimpleEntry;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by chao on 2016/5/6.
 */
public class RestFulUtil {

    public static JSONObject http(String url) throws URLFormatException, URLCanNotFoundException, IOException {
        URLConnection urlConnection = URLUtils.getUrl(url, "GET");
        return URLUtils.input(urlConnection);
    }

    public static JSONArray httpArray(String url) throws URLFormatException, URLCanNotFoundException, IOException {
        URLConnection urlConnection = URLUtils.getUrl(url, "GET");
        return URLUtils.inputArray(urlConnection);
    }

    public static JSONObject http(String url, String type, JSONObject json) throws URLFormatException, URLCanNotFoundException, IOException {
        JSONObject jsonObject = null;
        URLConnection urlConnection = URLUtils.getUrl(url,type);
        if (json != null && !json.isEmpty()){
            URLUtils.output(urlConnection,json.toString());
        }
        jsonObject = URLUtils.input(urlConnection);
        return jsonObject;
    }

    public static JSONObject http(String url, String type, JSONArray jsonArray) throws URLFormatException, URLCanNotFoundException, IOException {
        JSONObject jsonObject = null;
        URLConnection urlConnection = URLUtils.getUrl(url, type);
        if (jsonArray != null && !jsonArray.isEmpty()) {
            URLUtils.output(urlConnection, jsonArray.toString());
        }
        jsonObject = URLUtils.input(urlConnection);
        return jsonObject;
    }

    public static JSONObject http(String url, String type, File file) {
        JSONObject jsonObject = null;
        try {
            URLConnection urlConnection = URLUtils.createUrlConnection(new URL(url), type, new SimpleEntry<String, String>("Content-Type", "application/octet-stream"));
            if (file != null)URLUtils.outputFile(urlConnection, file);
            jsonObject = URLUtils.input(urlConnection);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
