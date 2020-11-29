package com.laudoecia.api.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;


public class ConverterParaJpeg {

	
	public void CriaImagemNaPasta(String nomeimagem, byte[] imagem) {
		try {
			this.Converter(nomeimagem, imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void EditarImageNaPasta(String nomeImagem, byte[] imagem) {
		try {
			this.Converter(nomeImagem + nomeImagem, imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void Converter(String caminho, byte[] imager) {
		Iterator<ImageWriter> iterador = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter subescreve = iterador.next();
		ImageWriteParam parametroimagem = subescreve.getDefaultWriteParam();
		parametroimagem.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		parametroimagem.setCompressionQuality(1);

		try {
			BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(imager));
			IIOImage imagestream = new IIOImage(imagem, null, null);

			File file = new File(caminho);
			FileImageOutputStream output = new FileImageOutputStream(file);
			subescreve.setOutput(output);

			subescreve.write(null, imagestream, parametroimagem);
			subescreve.dispose();
		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}
}
