/*
 * Copyright (c) 2020. Alfatell
 * Developer Pavlov Aleksey alekseypavlov1998@gmail.com
 */

package ami.connector;

import okhttp3.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author Pavlov Aleksey
 * @date 27-01-2020
 * Class for send http requests
 */
public class DataTransfer {

    // Set current mediatype
    static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * Method for run POST HTTP request
     * @param ccAmiHost - URL for call another program integration API
     * @param path      - path for call
     * @param body      - body json object
     * @return          - server API response
     */
    public Map<String, String> sendHttpPost(String ccAmiHost, String path, String body) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            RequestBody requestBody = RequestBody.create(JSON, body);
            Request request = new Request.Builder()
                    .url(ccAmiHost + "/" + path)
                    .post(requestBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                Map<String, String> answer = new HashMap<>();
                answer.put("status", String.valueOf(response.code()));
                answer.put("data", response.body().string());
                return answer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
