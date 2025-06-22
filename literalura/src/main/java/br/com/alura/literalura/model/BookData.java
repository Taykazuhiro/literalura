package br.com.alura.literalura.model;

import br.com.alura.literalura.DTO.RBookData;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "books")
public class BookData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private AuthorData author;

    private String languages;
    private Double downloadCount;

    public BookData(){}

    public BookData(RBookData rBookData, AuthorData authorData){
        this.title = rBookData.title();
        this.author = authorData;
        this.languages = String.valueOf(rBookData.languages());
        this.downloadCount= rBookData.downloadCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorData getAuthor() {
        return author;
    }

    public void setAuthors(AuthorData author) {
        this.author = author;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "\n ---------------- DADOS DO LIVRO ---------------- " +
                "\n Título: " + title +
                "\n Autor{ " + author + "}" +
                "\n Idioma: " + languages +
                "\n Número de Downloads: " + downloadCount +
                "\n ------------------------------------------------ ";
    }
}