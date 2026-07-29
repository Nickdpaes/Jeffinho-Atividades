package model;

import java.sql.*;

public class Conexao { 

    private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca_NickChaguinhas";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
