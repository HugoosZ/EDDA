public class Video {
    private String videoID;
    private String videoTitle;
    private String channelID;
    private String channelTitle;
    private String publishedAt;
    private long viewCount;
    private int likeCount;
    private int commentCount;
    private float popularity;
    private Video next;

    Video(String videoID, String videoTitle, String channelID, String channelTitle, String publishedAt, long viewCount, int likeCount, int commentCount, float popularity){
        this.videoID = videoID;
        this.videoTitle = videoTitle;
        this.channelID = channelID;
        this.channelTitle = channelTitle;
        this.publishedAt = publishedAt;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.popularity = popularity;
        next = null;
    }

    public void reproduce(){
        System.out.printf("Channel: %s %s \nVideo: %s %s \n", channelID, channelTitle, videoID, videoTitle );
        System.out.printf("Date: %s views: %s likes: %s comments: %s popularity: %s\n",publishedAt, viewCount, likeCount, commentCount, popularity);
    }

    public String getVideoID(){return videoID;}
    public String getVideoTitle(){return videoTitle;}
    public String getChannelID(){return channelID;}
    public String getChannelTitle(){return channelTitle;}
    public String getPublishedAt(){return publishedAt;}
    public long getViewCount(){return viewCount;}
    public int getLikeCount(){return likeCount;}
    public int getCommentCount(){return commentCount;}
    public float getPopularity(){return popularity;}
    public Video getNext(){return next;}

    public void setVideoID(String videoID){this.videoID = videoID;}
    public void setVideoTitle(String videoTitle){this.videoTitle = videoTitle;}
    public void setChannelID(String channelID){this.channelID = channelID;}
    public void setChannelTitle(String channelTitle){this.channelTitle = channelTitle;}
    public void setPublishedAt(String publishedAt){this.publishedAt = publishedAt;}
    public void setViewCount(long viewCount){this.viewCount = viewCount;}
    public void setLikeCount(int likeCount){this.likeCount = likeCount;}
    public void setCommentCount(int commentCount){this.commentCount = commentCount;}
    public void setPopularity(float popularity){this.popularity = popularity;}
    public void setNext(Video next){this.next = next;}

    public static void main(String[] args) {
        //Test cases
        Video v1 = new Video("1","video 1", "1","channel title", "12-12-1221",131, 323, 323,12f );
        v1.reproduce();
    }
}

