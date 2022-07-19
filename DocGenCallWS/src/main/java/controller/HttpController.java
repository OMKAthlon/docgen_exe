package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.InputParameters;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class HttpController {

    private final static String WSSERVER = System.getenv("WSSERVER");


    public static void SendInputData(InputParameters inputParameters) {

        HttpClient client = new DefaultHttpClient();
        JSONObject json = new JSONObject();
        try {
            String sendDataURL = "http://" + WSSERVER + "/fetch_" + inputParameters.getStoredProcedure();
            System.out.println(sendDataURL);
            HttpPost post = new HttpPost(sendDataURL);
            HttpResponse response;

            json.put("ParameterList", inputParameters.getParameterList());

            StringEntity se = new StringEntity(json.toString());

            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);

//            RestTemplate restTemplate = new RestTemplate();

//            String result = restTemplate.postForObject(sendDataURL, json, String.class);
//            JSONObject jsonResponse = new JSONObject(result);
//            System.out.println(jsonResponse);

            try {
                response = client.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);
                    System.out.println("body" + responseBody);

                    ObjectMapper mapper = new ObjectMapper();
                    LinkedHashMap<Integer, String> map = mapper.readValue(responseBody, LinkedHashMap.class);
//                    System.out.println(map);
                    writeLisFile(map, inputParameters);

                }
//                String result = restTemplate.postForObject(sendDataURL, json, String.class);
//                if (result != null) {
//                    JSONObject jsonResponse = new JSONObject(result);
//                    System.out.println(jsonResponse);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLisFile(LinkedHashMap<Integer, String> map, InputParameters inputParameters) {

        File outputFile = new File(inputParameters.getLocationOutputLisFile());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            Writer writer = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                bufferedWriter.write(entry.getValue());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            writer.close();
            fileOutputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


}
