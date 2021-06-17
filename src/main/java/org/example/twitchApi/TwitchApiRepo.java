package org.example.twitchApi;



import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.json.Stream;

import java.io.IOException;

public class TwitchApiRepo {

    public Integer getLiveUserCount(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.twitch.tv/helix/streams?user_id=451480562")
                .method("GET", null)
                .addHeader("Client-Id", "q6batx0epp608isickayubi39itsckt")
                .addHeader("Authorization", "Bearer rqzwcdxqzj8uhsm5lh2wblcsst521w")
                .build();
        try {
            String response = client.newCall(request).execute().body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            Stream streamData = objectMapper.readValue(response, Stream.class);
            return streamData.getData()[0].getViewerCount();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
