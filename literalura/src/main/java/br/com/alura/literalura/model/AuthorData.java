package br.com.alura.literalura.model;

import br.com.alura.literalura.DTO.RAuthorData;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "Authors")
public class AuthorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookData> books = new ArrayList<>();

    public AuthorData(){}

    public AuthorData(RAuthorData rAuthorData){
        this.name=String.valueOf(rAuthorData.name());
        this.birthYear=Integer.valueOf(rAuthorData.birthYear());
        this.deathYear=Integer.valueOf(rAuthorData.deathYear());
    }
    public List<String> getBookTitle(){
        return books.stream()
                .map(BookData::getTitle)
                .collect(Collectors.toList());
    }

    public List<BookData> getBooks() {
        return books;
    }

    public void setBooks(List<BookData> books) {
        books.forEach(b->b.setAuthors(this));
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }

    @Override
    public String toString() {
        return "\n Nome: " + name +
                "\n Ano de Nascimento: " + birthYear +
                "\n Ano de Falecimento: " + deathYear + "\n";
    }
}
