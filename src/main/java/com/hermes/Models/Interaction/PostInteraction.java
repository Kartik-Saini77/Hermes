package com.hermes.Models.Interaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostInteraction {
    @JsonProperty("title")
    public String title;

    @JsonProperty("textContent")
    public String textContent;

    @JsonProperty("mediaContent")
    public String mediaContent;

    @JsonProperty("postTags")
    public List<String> postTags;

    @JsonProperty("authorUserId")
    private String authorUserId;

    @JsonProperty("communityId")
    public String communityId;
}
