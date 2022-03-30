package libreria.libreria;

import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.servicios.AutorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaspringApplication {

	public static void main(String[] args) throws ErrorServicio {
		SpringApplication.run(LibreriaspringApplication.class, args);
                
	}

}
