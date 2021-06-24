package org.example.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamInfoData {
    @JsonProperty("id")
    String id;
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("user_login")
    String userLogin;
    @JsonProperty("user_name")
    String userName;
    @JsonProperty("game_id")
    String gameId;
    @JsonProperty("game_name")
    String gameName;
    @JsonProperty("type")
    String type;
    @JsonProperty("title")
    String title;
    @JsonProperty("viewer_count")
    Integer viewerCount;
    @JsonProperty("started_at")
    String startedAt;
    @JsonProperty("language")
    String language;
    @JsonProperty("thumbnail_url")
    String thumbnailUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(Integer viewerCount) {
        this.viewerCount = viewerCount;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getIsMature() {
        return isMature;
    }

    public void setIsMature(String isMature) {
        this.isMature = isMature;
    }

    @JsonProperty("is_mature")
    String isMature;

    public StreamInfoData() {

    }



}
