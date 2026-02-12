package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Marcos");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2003, 11, 25));

        var autorSalva = repository.save(autor);
        System.out.println(autorSalva.getNome());
    }


    @Test
    public void atualizar(){
        var id = UUID.fromString("67e1addd-98c9-47e0-be81-b5c1f3419e9a");
        Optional<Autor> autor = repository.findById(id);

        if (autor.isPresent()){
            Autor autorFound = autor.get();
            autorFound.setNome("Marcos");
            repository.save(autorFound);
        }
    }

    @Test
    public void listarAutores(){
        List<Autor> autores = repository.findAll();
        autores.forEach(System.out::println);
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
}
