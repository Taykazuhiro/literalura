package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.AuthorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorData, Long> {
}
