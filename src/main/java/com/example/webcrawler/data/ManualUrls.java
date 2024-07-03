package com.example.webcrawler.data;

import java.util.List;

public class ManualUrls {
    private String userManualUuid;
    private List<String> url;

    public ManualUrls() {
    }

    public ManualUrls(String userManualUuid, List<String> url) {
        this.userManualUuid = userManualUuid;
        this.url = url;
    }

    public String getUserManualUuid() {
        return userManualUuid;
    }

    public void setUserManualUuid(String userManualUuid) {
        this.userManualUuid = userManualUuid;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ManualUrls{" +
                "userManualUuid='" + userManualUuid + '\'' +
                ", url=" + url +
                '}';
    }
}
