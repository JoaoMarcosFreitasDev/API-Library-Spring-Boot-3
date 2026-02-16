package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("James");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2020, 11, 25));

        var autorSalva = repository.save(autor);
        System.out.println(autorSalva.getNome());
    }


    @Test
    public void atualizar(){
        var id = UUID.fromString("2da239d3-95a3-4153-925c-1dd32a80f8cd");
        Optional<Autor> autor = repository.findById(id);

        if (autor.isPresent()){
            Autor autorFound = autor.get();
            autorFound.setNome("Marcos");
            autorFound.setDataNascimento(LocalDate.of(2015,12,29));
            repository.save(autorFound);
        }
    }

    @Test
    public void listarAutores(){
        List<Autor> autores = repository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    public void listarAutoresJPQL(){
        var resultado = repository.listarAutores();
        resultado.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Autores: " + repository.count());
    }

    @Test
    public void delete(){
        var id = UUID.fromString("67e1addd-98c9-47e0-be81-b5c1f3419e9a");
        System.out.println("Autor: " + repository.findById(id));
        repository.deleteById(id);

    }

    @Test
    @Transactional
    void listarLivros(){
        var id = UUID.fromString("56518b5f-67d6-42fc-800d-a461fb65b2ad");
        Autor autor = repository.findById(id).get();

        List<Livro> livrosF = livroRepository.findByAutor(autor);

        livrosF.forEach(System.out::println);
    }


}
