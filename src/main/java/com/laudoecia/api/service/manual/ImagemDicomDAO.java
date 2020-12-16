package com.laudoecia.api.service.manual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.laudoecia.api.domain.Imagem;

public class ImagemDicomDAO {
private Connection conexao;
	
	public ImagemDicomDAO() {
		super();
		conexao = Conexoes.getConectar();
	}
	
	public void Salvar(Imagem imagem){
		String sql = "insert into imagem(caminho, nomeimagem, extensao, dicom, codigouid) values(?,?,?,?,?)";
		
		try {
			PreparedStatement prepara = this.conexao.prepareStatement(sql);
			prepara.setString(1, imagem.getCaminho());
			prepara.setString(2, imagem.getNomeimagem());
			prepara.setString(3, imagem.getExtensao());
			prepara.setBoolean(4, imagem.isDicom());
			prepara.setString(5, imagem.getCodigouid());
			
			prepara.execute();
			prepara.close();
		} catch (Exception e) {
			System.out.println("erro no metodo salvar ImagemDicomJpeg");
			e.printStackTrace();
		}
	}
	
	public void Atualizar(Imagem imagem){
		String sql = "update imagem set caminho=?, nomeimagem=?, extensao=?, dicom=?, codigouid=? where codigo=?";
		
		PreparedStatement prepara;
		
		try {
			prepara = conexao.prepareStatement(sql);
			prepara.setString(1, imagem.getCaminho());
			prepara.setString(2, imagem.getNomeimagem());
			prepara.setString(3, imagem.getExtensao());
			prepara.setBoolean(4, imagem.isDicom());
			prepara.setString(5, imagem.getCodigouid());
			
			prepara.close();
		} catch (SQLException e) {
			System.out.println("erro nometodo atualizar nego");
			e.printStackTrace();
		}
		
	}
	
	public Imagem BuscarPeloNome(String nomeimagem){
		String sql= "SELECT * FROM imagem WHERE nomeimagem=?";
		Imagem imagem = new Imagem();
		
		try{
			PreparedStatement preparador= conexao.prepareStatement(sql);
			preparador.setString(1, nomeimagem);
			
			ResultSet resultado= preparador.executeQuery();
			
			while(resultado.next()){
				imagem.setCodigo(resultado.getLong("codigo"));
				imagem.setCaminho(resultado.getString("caminho"));
				imagem.setExtensao(resultado.getString("extensao"));
				imagem.setNomeimagem(resultado.getString("nomeimagem"));
				imagem.setDicom(resultado.getBoolean("dicom"));
				imagem.setCodigouid(resultado.getString("codigouid"));
			}
			preparador.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return imagem;
	}
	
	public Imagem BuscarPelaId(Long codigo){
		String sql= "SELECT * FROM imagem WHERE codigo=?";
		Imagem imagem= new Imagem();
	
		try {
			PreparedStatement prepara = conexao.prepareStatement(sql);
			prepara.setLong(1, codigo);
			
			ResultSet resultado = prepara.executeQuery();
			 
			while(resultado.next()){
				imagem.setCodigo(resultado.getLong("codigo"));
				imagem.setCaminho(resultado.getString("caminho"));
				imagem.setExtensao(resultado.getString("extensao"));
				imagem.setNomeimagem(resultado.getString("nomeimagem"));
				imagem.setDicom(resultado.getBoolean("dicom"));
				imagem.setCodigouid(resultado.getString("codigouid"));
			}			
			prepara.close();
		} catch (Exception e) {
			System.out.println("erro no metodo buscar pela codigo");
			e.printStackTrace();
		}
		
		return imagem;
	}	
	
}



