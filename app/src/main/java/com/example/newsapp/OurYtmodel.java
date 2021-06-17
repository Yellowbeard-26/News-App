package com.example.newsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OurYtmodel {

        @SerializedName("YoutubeData")
        @Expose
        private List<YoutubeDatum> youtubeData = null;

        public List<YoutubeDatum> getYoutubeData() {
            return youtubeData;
        }

        public void setYoutubeData(List<YoutubeDatum> youtubeData) {
            this.youtubeData = youtubeData;
        }
    public class YoutubeDatum {

        @SerializedName("pid")
        @Expose
        private Integer pid;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("channelId")
        @Expose
        private String channelId;

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

    }
    }


