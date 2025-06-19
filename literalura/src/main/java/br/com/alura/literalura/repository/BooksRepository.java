package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<BookData, Long> {
    Optional<BookData> findByTitleIgnoreCase(String title);

    Optional<BookData> findByTitle(String title);
}
