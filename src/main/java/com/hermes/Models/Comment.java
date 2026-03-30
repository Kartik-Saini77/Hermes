package com.hermes.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment {
    @Id
    @BsonProperty("_id")
    private String id;
    @BsonProperty("userId")
    private String userId;
    @BsonProperty("description")
    private String description;
    @BsonProperty("commentId")
    private String commentId;
    @BsonProperty("createdAt")
    private LocalDateTime createdAt;
    @BsonProperty("isDeleted")
    private boolean isDeleted;
    @BsonProperty("upVoteCount")
    private int upVoteCount;
    @BsonProperty("downVoteCount")
    private Integer downVoteCount;
    @BsonProperty("replies")
    private List<String> replies;
    @BsonProperty("postId")
    private String postId;

    public Comment() {
        commentId = UUID.randomUUID().toString();
        createdAt = LocalDateTime.now();
        isDeleted = false;
        upVoteCount = 0;
        downVoteCount = 0;
        replies = new ArrayList<>();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public Integer getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(Integer downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}