package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {


//    List<Livro> findByAutor(Autor autor);

    @Query(value = "SELECT * FROM autor as author ORDER BY author.data_nascimento", nativeQuery = true)
    List<Autor> listarAutores();
}
