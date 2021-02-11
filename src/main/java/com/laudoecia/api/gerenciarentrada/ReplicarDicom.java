package com.laudoecia.api.gerenciarentrada;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(value = "DicomReplica")
public class ReplicarDicom {
	private ConcurrentHashMap<String,String> mapa = null;
	
	public ReplicarDicom(){		
		this.InsereLista(new ConcurrentHashMap<String,String>());
	}

	public Map<String,String> BuscarMapa() {
		return mapa;
	}

	public void InsereLista(ConcurrentHashMap<String,String> lista) {
		this.mapa = lista;
	}
	
	public Integer getTamanho(){
		return mapa.size();
	}
	
	public void Adicionar(String key, String value){
		this.mapa.put(key, value);
	}
	
	public void Remover(String key){
		this.mapa.remove(key);
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, String> entry : mapa.entrySet()) { 
			buffer.append(entry.getValue());
		}
		
		return buffer.toString();
	}
}
