package org.example.textApproval;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class TextApproval {
    List<String> forbiddenWords = new ArrayList<>();

    //TODO:banned songs and kurwa inne piekne rzeczy

    public TextApproval() {
        forbiddenWords.add("nigga");
        forbiddenWords.add("ni**a");
        forbiddenWords.add("niggas");
        forbiddenWords.add("ni**as");
        forbiddenWords.add("nigger");
        forbiddenWords.add("ni**er");
        forbiddenWords.add("simp");
        forbiddenWords.add("murzyn");
        forbiddenWords.add("czarnuch");
        forbiddenWords.add("niggers");
        forbiddenWords.add("ni**ers");
        forbiddenWords.add("mokambe");
        forbiddenWords.add("pedał");
        forbiddenWords.add("pedalskie");
        forbiddenWords.add("pedal");
    }

    private String readContents(String urlAdress) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(urlAdress)
                .method("GET", null)
                .build();
        String response = null;
        try {
            response = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = response.replaceAll("<[^>]*>", "")
                .toLowerCase()
                .replaceAll(" ", "");
        return response;
    }

    public Boolean scanText(String text) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(true);
        forbiddenWords.forEach(forbiddenWord -> {
            if (text.contains(forbiddenWord)) {
                atomicBoolean.set(false);
            }
        });
        return atomicBoolean.get();
    }

    private String adjustUrl(String url) {
        return url.replaceAll(" ", "_")
                .replaceAll("\\)", "")
                .replaceAll("\\(", "")
                .replaceAll("feat\\.", "feat_")
                .replaceAll("\n", "")
                .replaceAll("'", "_");
    }

    private String getUrl(String validAuthor, String validTitle) {
        String testedUrl = "https://www.tekstowo.pl/piosenka," + validAuthor + "," + validTitle + ".html";
        testedUrl = adjustUrl(testedUrl);
        return testedUrl;
    }

    public Boolean verifyLyrics(String author,String title){

        return scanText(readContents(getUrl(author,title)));
    }
}
