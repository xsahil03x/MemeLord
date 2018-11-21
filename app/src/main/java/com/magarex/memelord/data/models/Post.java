package com.magarex.memelord.data.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by sahil on 14/11/18.
 **/
public class Post {

    private String postId;
    private String caption;
    private String uploader;
    private long upvoteCount;
    private String downloadURL;
    private long timestamp;
    private String uploaderPic;

    public Post() {
    }

    public Post(String postId, String caption, String uploader, long upvoteCount, String downloadURL, long timestamp, String uploaderPic) {
        this.postId = postId;
        this.caption = caption;
        this.uploader = uploader;
        this.upvoteCount = upvoteCount;
        this.downloadURL = downloadURL;
        this.timestamp = timestamp;
        this.uploaderPic = uploaderPic;
    }

    public static final DiffUtil.ItemCallback<Post> DIFF_CALLBACK = new DiffUtil.ItemCallback<Post>() {

        @Override
        public boolean areItemsTheSame(Post oldItem, Post newItem) {
            return oldItem.getPostId().equals(newItem.getPostId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return true;
        }
    };


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public long getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(long upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUploaderPic() {
        return uploaderPic;
    }

    public void setUploaderPic(String uploaderPic) {
        this.uploaderPic = uploaderPic;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
