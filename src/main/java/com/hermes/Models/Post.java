package com.hermes.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    @Id
    @BsonProperty("_id")
    private String id;
    @BsonProperty("partitionKey")
    private String partitionKey;
    @BsonProperty("textContent")
    private Content content;
    @BsonProperty("upVoteCount")
    private int upVoteCount;
    @BsonProperty("downVoteCount")
    private int downVoteCount;
    @BsonProperty("commentsCount")
    private int commentsCount;
    @BsonProperty("authorUserId")
    private String authorUserId;
    @BsonProperty("allComments")
    private List<Comment> allComments;
    @BsonProperty("postTags")
    private List<String> postTags;
    @BsonProperty("createdAt")
    private LocalDateTime createdAt;
    @BsonProperty("lastUpdatedAt")
    private LocalDateTime lastUpdatedAt;
    @BsonProperty("isApproved")
    private boolean isApproved;
    @BsonProperty("communityId")
    private String communityId;

    public Post() {
        partitionKey = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("MM-yyyy"));
        upVoteCount = 0;
        downVoteCount = 0;
        commentsCount = 0;
        allComments = new ArrayList<>();
        createdAt = LocalDateTime.now();
        lastUpdatedAt = LocalDateTime.now();
        isApproved = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(String authorUserId) {
        this.authorUserId = authorUserId;
    }

    public List<Comment> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<Comment> allComments) {
        this.allComments = allComments;
    }

    public List<String> getPostTags() {
        return postTags;
    }

    public void setPostTags(List<String> postTags) {
        this.postTags = postTags;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}
