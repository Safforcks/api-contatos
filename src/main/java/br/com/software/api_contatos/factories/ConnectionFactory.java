package br.com.software.api_contatos.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private String host;
    private String user;
    private String pass;

    public Connection createConnection() throws Exception{

        host = "jdbc:postgresql://localhost:5432/bd-apicontatos";
        user = "software";
        pass = "software";

        return DriverManager.getConnection(host, user, pass);
    }
}
