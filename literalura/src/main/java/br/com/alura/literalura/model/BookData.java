package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookData {
    private Long id;
    private String title;

    @JsonAlias("authors")
    private List<AuthorData> authorDataList;

    private List<String> languages;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<AuthorData> getAuthorDataList() { return authorDataList; }
    public void setAuthorDataList(List<AuthorData> authorDataList) { this.authorDataList = authorDataList; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }
}

