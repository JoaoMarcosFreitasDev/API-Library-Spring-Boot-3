package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("12334-5695");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("David");
        livro.setDataPublicacao(LocalDate.of(1980, 1,20));

        Autor autor = autorRepository
                .findById(UUID.fromString("2da239d3-95a3-4153-925c-1dd32a80f8cd"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorAndLivro(){
        Livro livro = new Livro();
        livro.setIsbn("12334-5695");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("David");
        livro.setDataPublicacao(LocalDate.of(1980, 1,20));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2003, 11, 25));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }


    @Test
    void salvarTestCascade(){
        Livro livro = new Livro();
        livro.setIsbn("12334-5695");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("David");
        livro.setDataPublicacao(LocalDate.of(1980, 1,20));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2003, 11, 25));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarLivro(){
        Optional<Livro> livro = Optional.of(repository
                .findById(UUID.fromString("6d70c87b-244f-41a5-a400-e87769b435f0"))
                .orElse(new Livro()));

        Optional<Autor> autor = Optional.of(autorRepository
                .findById(UUID.fromString("56518b5f-67d6-42fc-800d-a461fb65b2ad"))
                .orElse(new Autor()));

        if (livro.isPresent()){
            Livro livroUpdate = livro.get();
            livroUpdate.setAutor(autor.get());
            repository.save(livroUpdate);
        }
    }

    @Test
    @Transactional
    void listarLivro(){
        UUID id = UUID.fromString("6d70c87b-244f-41a5-a400-e87769b435f0");
        Optional<Livro> livro = repository.findById(id);

        System.out.println("Livro: " + livro.get().getTitulo());

        UUID idAutor = UUID.fromString("56518b5f-67d6-42fc-800d-a461fb65b2ad");
        //Optional<Autor> autor = autorRepository.findById(idAutor);

        System.out.println("Autor: " + livro.get().getAutor());


    }

    @Test
    void listarTituloEIsbn(){
        String titulo = "David";

        List<Livro> livro = repository.findByTitulo(titulo);

        livro.forEach(System.out::println);
    }

}