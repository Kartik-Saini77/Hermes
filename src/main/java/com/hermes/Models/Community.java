package com.hermes.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Community {
    @Id
    @BsonProperty("_id")
    private String id;
    @BsonProperty("name")
    private String name;
    @BsonProperty("description")
    private String description;
    @BsonProperty("rules")
    private List<String> rules;
    @BsonProperty("posts")
    private List<Post> posts;
    @BsonProperty("createdBy")
    private String createdBy;
    @BsonProperty("members")
    private List<String> members;
    @BsonProperty("roles")
    private List<String> roles;
    @BsonProperty("tags")
    private List<String> tags;
    @BsonProperty("homeFeed")
    private List<Post> homeFeed;
    @BsonProperty("createdAt")
    private LocalDateTime createdAt;

    public Community() {
        rules = new ArrayList<>();
        posts = new ArrayList<>();
        members = new ArrayList<>();
        roles = new ArrayList<>();
        tags = new ArrayList<>();
        homeFeed = new ArrayList<>();
        createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Post> getHomeFeed() {
        return homeFeed;
    }

    public void setHomeFeed(List<Post> homeFeed) {
        this.homeFeed = homeFeed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
