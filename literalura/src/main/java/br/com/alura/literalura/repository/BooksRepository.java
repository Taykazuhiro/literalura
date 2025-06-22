package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BookData, Long> {

    @Query("SELECT b FROM BookData b WHERE b.languages like :language")
    List<BookData> searchBookByLanguage(String language);
}
