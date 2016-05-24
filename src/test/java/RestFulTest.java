import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.IncludeCategories;
import org.oasis.exception.URLCanNotFoundException;
import org.oasis.exception.URLFormatException;
import org.oasis.healthcare.RestFulUtil;
import org.oasis.healthcare.URLUtils;
import org.oasis.util.Constants;

import java.io.*;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chao on 2016/5/6.
 */
public class RestFulTest {

    @Test
    public void test1111(){
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject();
            json.put("key", "gesweeks");
            json.put("value", "23");
            json.put("date", "20150315");
            jsonArray.add(json);
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_BOE + "/cds/1/431268", "POST", jsonArray);
            System.out.println(jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1211(){
        File file = new File("D:/work/病历样本/demo/北京军区总医院_处方.JPG");
        JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_DATACAPTURE + "/1/1211/image","POST",file);
        System.out.println(jsonObject);
    }

    @Test
    public void test1212() {
        try {
            JSONArray jsonArray = RestFulUtil.httpArray(Constants.BASEURL_DATACAPTURE + "/1/1212/document");
            System.out.println(jsonArray);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1213() {
        try {
            URLConnection urlConnection = URLUtils.getUrl(Constants.BASEURL_DATACAPTURE + "/1/1213/image/1", "GET");
            urlConnection.setRequestProperty("Accept","application/octet-stream");
            InputStream inputStream = urlConnection.getInputStream();
            byte[] bytes = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/work/1.jpg"));
            int k = 0;
            while ((k = inputStream.read(bytes)) > -1) {
                fileOutputStream.write(bytes,0,k);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1214() {

        try {
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_DATACAPTURE + "/1/1214/document/1");
            System.out.println(jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13111(){
        try {
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_HRASS + "/1/111/data");
            System.out.println(jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13112(){
        try {
            JSONObject json = new JSONObject();
            json.put("highfruit15", false);
            json.put("vaginalbleeding5",false);
            json.put("chdfamilyhistory", false);
            json.put("weight", 57);
            json.put("miscarriage10", false);
            json.put("birthweight", 3.2);
            json.put("bmi", 23);
            json.put("alcohol3", false);
            json.put("conceive12", false);
            json.put("gdmhistory", 1);
            json.put("prevbirthweight", 3.2);
            json.put("dmrelative2", false);
            json.put("dmrelative1", false);
            json.put("ovulation", false);
            json.put("cigarettes", 0);
            json.put("height", 164);
            json.put("racial", 1);
            json.put("gesweeks", 11);
            json.put("ghfamilyhistory", false);
            json.put("age", 30);
            json.put("pregnancy", true);
            json.put("map", 85);
            json.put("gender", 2);
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_HRASS + "/1/112/data", "POST", json);
            System.out.println("请求体>>" + json);
            System.out.println("返回体>>" + jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13113(){
        try {
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_HRASS + "/1/113/history");
            System.out.println(jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13121(){
        JSONObject jsonObject = null;
        try {
            jsonObject = RestFulUtil.http(Constants.BASEURL_HRASS + "/1/123/risk");
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);
    }

    @Test
    public void test13122(){

        try {
            JSONObject json = new JSONObject();
            json.put("map", 90);
            json.put("bmi", 23);
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_HRASS + "/1/122/risk/372/impact", "POST", json);
            System.out.println("请求体>>" + json);
            System.out.println("返回体>>" + jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13123(){
        try {
            JSONObject jsonObject = RestFulUtil.http(Constants.BASEURL_HRASS + "/1/123/risk/alert");
            System.out.println(jsonObject);
        } catch (URLFormatException e) {
            e.printStackTrace();
        } catch (URLCanNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUploadimage(){
        JSONObject jsonObject = null;
        jsonObject = RestFulUtil.http("http://127.0.0.1:8081/adm/management/sm/cognitive/8ad0b3965364799f015365be7c3f0039/uploadimage?name=黑龙江医药病案首页.png", "POST", new File("D:/work/病历样本/demo/黑龙江医药病案首页.png"));
        System.out.println(jsonObject);
    }
}
