/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxappescolar.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafxappescolar.modelo.ConexionBD;
import javafxappescolar.modelo.pojo.Usuario;

/**
 *
 * @author omaralex
 */
public class InicioSesionDAO {
    
    public Usuario verificarCredenciales(String username, String password) throws SQLException {
        Usuario usuarioSesion = null;
        
        Connection conexionBD = ConexionBD.abrirConexion();

        if(conexionBD != null){
            String consultaSQL = "select idUsuario, nombre, apellidoPaterno, apellidoMaterno, username, password " +
                    "from usuario " +
                    "where username = ? and password = ?";

            PreparedStatement ps = conexionBD.prepareStatement(consultaSQL);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                usuarioSesion = new Usuario();
                usuarioSesion.setResultSet(rs);
            }

        } else {
            System.err.println("Error al conectar con la base de datos");
        }
        
        return usuarioSesion;
    }
    
}
