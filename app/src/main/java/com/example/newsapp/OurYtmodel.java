package com.example.newsapp;

public class OurYtmodel {

    String Channelid;
    String Title;

    public OurYtmodel(String channelid, String title) {
        Channelid = channelid;
        Title = title;
    }

    public String getChannelid() {
        return Channelid;
    }

    public void setChannelid(String channelid) {
        Channelid = channelid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    //        @SerializedName("YoutubeData")
//        @Expose
//        private List<YoutubeDatum> youtubeData = null;
//
//        public List<YoutubeDatum> getYoutubeData() {
//            return youtubeData;
//        }
//
//        public void setYoutubeData(List<YoutubeDatum> youtubeData) {
//            this.youtubeData = youtubeData;
//        }
//    public class YoutubeDatum {
//
//        @SerializedName("pid")
//        @Expose
//        private Integer pid;
//        @SerializedName("title")
//        @Expose
//        private String title;
//        @SerializedName("channelId")
//        @Expose
//        private String channelId;
//
//        public Integer getPid() {
//            return pid;
//        }
//
//        public void setPid(Integer pid) {
//            this.pid = pid;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getChannelId() {
//            return channelId;
//        }
//
//        public void setChannelId(String channelId) {
//            this.channelId = channelId;
//        }
//
//    }
    }


