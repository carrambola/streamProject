package org.example.twitchApi;



import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.json.StreamInfoDataMain;
import org.example.model.json.StreamModeratorsData;
import org.example.model.json.StreamModeratorsDataMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            StreamInfoDataMain streamInfoDataMainData = objectMapper.readValue(response, StreamInfoDataMain.class);
            return streamInfoDataMainData.getData()[0].getViewerCount();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<StreamModeratorsData> getModerators(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.twitch.tv/helix/streams?broadcaster_id=451480562")
                .method("GET", null)
                .addHeader("Client-Id", "q6batx0epp608isickayubi39itsckt")
                .addHeader("Authorization", "Bearer rqzwcdxqzj8uhsm5lh2wblcsst521w")
                .build();
        try {
            String response = client.newCall(request).execute().body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            StreamModeratorsDataMain streamModeratorsDataMain = objectMapper.readValue(response, StreamModeratorsDataMain.class);
            return Arrays.asList(streamModeratorsDataMain.getData());
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
