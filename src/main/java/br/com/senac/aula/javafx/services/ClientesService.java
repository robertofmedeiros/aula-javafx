package br.com.senac.aula.javafx.services;

import br.com.senac.aula.javafx.db.ConexaoDataBase;
import br.com.senac.aula.javafx.modelos.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientesService {
    private ConexaoDataBase conexaoDataBase = new ConexaoDataBase();

    public List<Cliente> carregarAlunos() throws SQLException, ClassNotFoundException {
        List<Cliente> out = new ArrayList<>();

        Connection conn = conexaoDataBase.getConnection();

        Statement sta = conn.createStatement();
        ResultSet resultSet = sta.executeQuery("SELECT * FROM public.clientes;");

        while(resultSet.next()){
            Cliente cli = new Cliente();
            cli.setId(resultSet.getInt(1));
            cli.setNome(resultSet.getString(2));
            cli.setDocumento(resultSet.getString(3));

            out.add(cli);
        }

        return out;
    }
}
