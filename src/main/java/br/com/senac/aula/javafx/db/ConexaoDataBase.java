package br.com.senac.aula.javafx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDataBase {
    private Connection conn = null;

    public synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn == null){
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecommerce", "postgres", "601034");
        }

        return conn;
    }
}
