package br.com.senac.aula.javafx;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class AulaJavafxApplication {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
