/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ami.connector;

import okhttp3.*;

import java.util.HashMap;
import java.util.Map;

public class DataTransfer {

    static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public Map<String, String> sendHttpPost(String ccAmiHost, String path, String body) {
        try {
            RequestBody requestBody = RequestBody.create(JSON, body);
            Request request = new Request.Builder()
                    .url(ccAmiHost + "/" + path)
                    .post(requestBody)
                    .build();
            try (Response response = new OkHttpClient().newCall(request).execute()) {
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
