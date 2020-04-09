package com.laudoecia.api.backup;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Backup {
	String caminhodobanco = "C:\\Program Files\\PostgreSQL\\9.3\\bin\\";
	String destino = ".\\backup\\";
	
	public static void main(String[] args) {
		Backup b = new Backup();
		b.fazBackup();
	}
	
	
	public void fazBackup(){

		String montadestino = destino + "bkp" + this.Concatena();
		
		File arq = new File(destino);
		if (arq.exists()){
			if (arq.length() > 0)
				arq.delete();
		}
		try {
			Process p = null;
			String linha = "";
			ProcessBuilder pb = new ProcessBuilder(caminhodobanco + "pg_dump.exe", "-i", "-h", "localhost", "-U", "postgres", "-F", "c", "-b", "-v", "-f", montadestino, "doorlaudos");
			pb.environment().put("PGPASSWORD", "123456");
			pb.redirectErrorStream(true);
			p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((linha = reader.readLine()) != null){
				System.out.println(linha);
			}
		}catch (Exception e) {
			System.out.println("Não foi possível efetuar o backup");
		}
	}
	
	private String Concatena() {
		LocalDate hoje = LocalDate.now();
		return hoje.getDayOfMonth() + "" + hoje.getMonthValue() + "" + hoje.getYear();
	}
}
