package org.oasis.healthcare;

import com.sun.istack.internal.NotNull;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.oasis.exception.RequestTypeException;
import org.oasis.exception.URLCanNotFoundException;
import org.oasis.exception.URLFormatException;
import org.oasis.util.Constants;
import org.oasis.util.SimpleEntry;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chao on 2016/5/5.
 */
public class URLUtils {

    public static URLConnection getUrl(@NotNull String url,String type) throws URLCanNotFoundException, URLFormatException, IOException {
        if (url == null || url.length() == 0)
            throw new URLCanNotFoundException();
        if (!url.matches("^((http)?://)[^\\s]+")){
            throw new URLFormatException();
        }
        if (type == null || type.length() == 0){
            type = "GET";
        }

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = "GET".equals(type) ? get(url1) : "POST".equals(type) ? post(url1) : "File".equals(type) ? postFile(url1) : null;
            if (urlConnection == null) {
                throw new RequestTypeException("只接受GET和POST请求");
            }

            return urlConnection;

        } catch (MalformedURLException e) {
            throw new URLCanNotFoundException(e);
        }

    }

    private static URLConnection get(URL url) throws IOException {
        return createUrlConnection(url,"GET",new SimpleEntry<String, String>("Accept","application/json"));
    }

    private static URLConnection post(URL url) throws IOException {
        return createUrlConnection(url,"POST",new SimpleEntry<String, String>("Content-Type","application/json"));
    }

    private static URLConnection postFile(URL url) throws IOException {
        return createUrlConnection(url,"POST");
    }

    public static void output(@NotNull URLConnection urlConnection,@NotNull String jsonObject){
        urlConnection.setDoOutput(true);
        try {
            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(jsonObject);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void outputFile(URLConnection urlConnection, File file) throws IOException {
        urlConnection.setDoOutput(true);
        OutputStream outputStream = urlConnection.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int k = 0;
        while ((k = fileInputStream.read(bytes)) > -1){
            outputStream.write(bytes, 0, k);
        }
        fileInputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    public static void output(@NotNull URLConnection urlConnection,@NotNull File file) {
        String BOUNDARY = UUID.randomUUID().toString(),LINE_END = "\r\n";

        urlConnection.setReadTimeout(10 * 10000000);
        urlConnection.setConnectTimeout(10 * 10000000);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("connection", "keep-alive");
        urlConnection.setRequestProperty("Content-Type","multipart/form-data;boundary=" + BOUNDARY);
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("--").append(BOUNDARY).append(LINE_END).append("Content-Disposition: form-data; name=\"file\"; filename=\""
                    + file.getName() + "\"" + LINE_END).append("Content-Type: application/octet-stream; charset="
                    + "utf-8" + LINE_END);

            OutputStream outputStream = urlConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(sb.toString().getBytes());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int k = 0;
            while ((k = fileInputStream.read(bytes)) > - 1){
                dataOutputStream.write(bytes,0,k);
            }
            fileInputStream.close();
            dataOutputStream.write(LINE_END.getBytes());
            byte[] end_data = ("--" + BOUNDARY + "--" + LINE_END)
                    .getBytes();
            dataOutputStream.write(end_data);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject input(@NotNull URLConnection urlConnection){
        JSONObject jsonObject = null;

        try {
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String result = bufferedReader.readLine();
            bufferedReader.close();
            inputStream.close();
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static JSONArray inputArray(@NotNull URLConnection urlConnection) {
        JSONArray jsonArray = null;
        try {
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String result = bufferedReader.readLine();
            bufferedReader.close();
            inputStream.close();
            jsonArray = JSONArray.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static URLConnection createUrlConnection(URL url, String requestMethod, Map.Entry<String,String> requestProperty) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(requestMethod);
        httpURLConnection.setRequestProperty(requestProperty.getKey(),requestProperty.getValue());
        return httpURLConnection;
    }

    private static URLConnection createUrlConnection(URL url, String requestMethod) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(requestMethod);
        return httpURLConnection;
    }

}
