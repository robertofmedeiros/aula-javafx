package br.com.senac.aula.javafx.controller;

import br.com.senac.aula.javafx.modelos.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("cadastro.fxml")
public class CadastroController {
    @FXML
    private TextField documento;

    @FXML
    private TextField nome;

    private ClienteController clienteController;

    public void dadosCliente(Cliente cli, ClienteController clienteController){
        nome.setText(cli.getNome());
        documento.setText(cli.getDocumento());

        this.clienteController = clienteController;
    }

    public void executarOk() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main.fxml"));

        Cliente cli = new Cliente();
        cli.setDocumento(documento.getText());
        cli.setNome(nome.getText());

        clienteController.salvarDados(cli);
    }
}
