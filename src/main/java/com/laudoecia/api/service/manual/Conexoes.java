package com.laudoecia.api.service.manual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexoes {
	static Connection conexao;
	private final static String URL= "jdbc:postgresql://localhost:5432/doorlaudos";
	private final static String USER= "postgres";
	private final static String PASSWORD= "123456";
	
	
	public static Connection getConectar(){
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("erro na conexao com o banco");
			e.printStackTrace();
		}
		return conexao;
	}
}
