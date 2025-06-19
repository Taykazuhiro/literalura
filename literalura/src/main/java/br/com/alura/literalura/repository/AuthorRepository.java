package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.AuthorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorData, Long> {
    Optional<AuthorData> findByNameIgnoreCase(String name);
}
