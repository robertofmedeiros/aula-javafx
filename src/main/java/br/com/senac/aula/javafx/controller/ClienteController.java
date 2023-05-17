package br.com.senac.aula.javafx.controller;

import br.com.senac.aula.javafx.modelos.Cliente;
import br.com.senac.aula.javafx.services.ClientesService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@FxmlView("main.fxml")
public class ClienteController {
    @FXML
    private TextField documento;

    @FXML
    private TextField nome;

    @FXML
    private TableView<Cliente> clientesList;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaDocumento;

    private int index = -1;

    private ClientesService clientesService = new ClientesService();

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));

        List<Cliente> clienteList = clientesService.carregarAlunos();

        clientesList.getItems().addAll(clienteList);

        clientesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount()==2){
                    Cliente cli = clientesList.getSelectionModel().getSelectedItem();
                    documento.setText(cli.getDocumento());
                    nome.setText(cli.getNome());

                    index = clientesList.getSelectionModel().getSelectedIndex();

                    System.out.println(clientesList.getSelectionModel().getSelectedIndex());
                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/cadastro.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        CadastroController cadastroController = fxmlLoader.getController();
                        cadastroController.dadosCliente(cli, ClienteController.this);

                        clientesList.setDisable(true);

                        Stage stage = new Stage();
                        stage.setTitle("Incluir");
                        stage.setScene(scene);
                        stage.showAndWait();

                        clientesList.setDisable(false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void executarOk(ActionEvent actionEvent) {
        System.out.println(nome.getText());
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Teste");
        alerta.setHeaderText("Você informou: " + nome.getText());

        Optional<ButtonType> option = alerta.showAndWait();

        try {
            if (option.get() != null) {
                System.out.println(option.get());
            }
            Cliente cli = new Cliente();
            cli.setDocumento(documento.getText());
            cli.setNome(nome.getText());

            if(index < 0) {
                clientesList.getItems().add(cli);
            }else{
                clientesList.getItems().set(index, cli);
                index = -1;
            }

            documento.setText("");
            nome.setText("");
        } catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void executarFechar(ActionEvent actionEvent){
        Stage stage = (Stage) btnCancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }

    public void executarDeletar(){
        if(index > -1) {
            clientesList.getItems().remove(index);
        }
    }

    public void salvarDados(Cliente cli){
        clientesList.getItems().add(cli);
    }

}
