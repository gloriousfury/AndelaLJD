package com.alc.ljv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class SingleUserModel {

@SerializedName("login")
@Expose
private String login;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("avatar_url")
@Expose
private String avatarUrl;
@SerializedName("url")
@Expose
private String url;
@SerializedName("html_url")
@Expose
private String htmlUrl;
@SerializedName("name")
@Expose
private String name;
@SerializedName("location")
@Expose
private String location;
@SerializedName("public_repos")
@Expose
private Integer publicRepos;
@SerializedName("public_gists")
@Expose
private Integer publicGists;
@SerializedName("followers")
@Expose
private Integer followers;
@SerializedName("following")
@Expose
private Integer following;

public String getLogin() {
return login;
}

public void setLogin(String login) {
this.login = login;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getAvatarUrl() {
return avatarUrl;
}

public void setAvatarUrl(String avatarUrl) {
this.avatarUrl = avatarUrl;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getHtmlUrl() {
return htmlUrl;
}

public void setHtmlUrl(String htmlUrl) {
this.htmlUrl = htmlUrl;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public Integer getPublicRepos() {
return publicRepos;
}

public void setPublicRepos(Integer publicRepos) {
this.publicRepos = publicRepos;
}

public Integer getPublicGists() {
return publicGists;
}

public void setPublicGists(Integer publicGists) {
this.publicGists = publicGists;
}

public Integer getFollowers() {
return followers;
}

public void setFollowers(Integer followers) {
this.followers = followers;
}

public Integer getFollowing() {
return following;
}

public void setFollowing(Integer following) {
this.following = following;
}

}