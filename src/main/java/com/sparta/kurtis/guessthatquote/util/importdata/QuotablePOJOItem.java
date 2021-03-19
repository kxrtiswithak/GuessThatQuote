package com.sparta.kurtis.guessthatquote.util.importdata;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuotablePOJOItem{

	@JsonProperty("authorSlug")
	private String authorSlug;

	@JsonProperty("author")
	private String author;

	@JsonProperty("length")
	private int length;

	@JsonProperty("_id")
	private String id;

	@JsonProperty("authorId")
	private String authorId;

	@JsonProperty("content")
	private String content;

	@JsonProperty("tags")
	private List<String> tags;

	public String getAuthorSlug(){
		return authorSlug;
	}

	public String getAuthor(){
		return author;
	}

	public int getLength(){
		return length;
	}

	public String getId(){
		return id;
	}

	public String getAuthorId(){
		return authorId;
	}

	public String getContent(){
		return content;
	}

	public List<String> getTags(){
		return tags;
	}
}