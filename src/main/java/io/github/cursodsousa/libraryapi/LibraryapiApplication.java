package io.github.cursodsousa.libraryapi;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		var contect = SpringApplication.run(LibraryapiApplication.class, args);
        AutorRepository autorRepository = contect.getBean(AutorRepository.class);
        exemploSalvarAutor(autorRepository);
	}

    public static void exemploSalvarAutor(AutorRepository autorRepository){
        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2003, 11, 25));

        var autorSalva = autorRepository.save(autor);
        System.out.println(autorSalva.getNome());
    }

}
