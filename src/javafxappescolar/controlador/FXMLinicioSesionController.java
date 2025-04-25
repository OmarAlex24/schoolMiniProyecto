/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxappescolar.controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafxappescolar.modelo.ConexionBD;
import javafxappescolar.modelo.dao.InicioSesionDAO;
import javafxappescolar.modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class FXMLinicioSesionController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label lbErrorUsuario;
    @FXML
    private Label lbErrorPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conexionBD = ConexionBD.abrirConexion();
        if (conexionBD != null){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Conexion Base de datos");
            alerta.setContentText("La conexion con la base de datos se ha realizado correctamente");
            alerta.show();
        }
        // TODO
    }    

    @FXML
    private void btnClicVerificar(ActionEvent event) {
        String username = tfUsuario.getText();
        String password = tfPassword.getText();
        
        if (validarCampos(username, password)){
            validarCredenciales(username, password);
        }
    }
    private boolean validarCampos(String username, String passowrd){
        lbErrorPassword.setText("");
        lbErrorUsuario.setText("");
        
        boolean camposValidos = true;
        if (username.isEmpty()){
            lbErrorUsuario.setText("Usuario obligatorio");
            camposValidos = false; 
        }
        if (passowrd.isEmpty()){
            lbErrorPassword.setText("Contraseña obligatoria");
            camposValidos = false; 
        }
        return camposValidos;
    }

    private boolean validarCredenciales(String username, String password) {
        //TODO flujo normal y alterno 1
        InicioSesionDAO inicioSesionDAO = new InicioSesionDAO();

        try{
            Usuario usuario = inicioSesionDAO.verificarCredenciales(tfUsuario.getText(), tfPassword.getText());

            if(usuario != null){
                System.out.println("Usuario encontrado");
                return true;
            } else {
                System.out.println("Usuario no encontrado");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
}

