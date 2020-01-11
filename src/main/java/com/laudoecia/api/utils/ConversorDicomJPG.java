package com.laudoecia.api.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.dcm4che3.image.PaletteColorModel;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;


public class ConversorDicomJPG {
	public int n = 0;
	private Boolean finish = false;

	public void Dcm2jpg(File inputFile, File outputFile) throws IOException {
		outputFile.mkdirs();
		n = this.setNumber(inputFile);

		if (n == 0) {
			File destFinal = new File(outputFile + "\\" + inputFile.getName() + ".jpg");
			convert(inputFile, destFinal, 0);
		} else {
			for (int i = 0; i <= n - 1; i++) {
				File outputFile2 = new File(outputFile + "\\" + inputFile.getName() + "aff");
				outputFile2.mkdirs();
				File destFinal2 = new File(outputFile2 + "\\" + "(" + "frame" + i + ")" + ".jpg");
				convert(inputFile, destFinal2, i);
			}
			setFinish(true);
		}
	}
	
	private String RetiraExtensao(String titulo) {
		if(titulo.endsWith(".dcm"))
			return titulo.replaceAll(".dcm", "");
		
		return titulo;
	}

	public void Dcm2jpg2(File inputFile, File outputFile) {
		n = numberFile(inputFile);
		for (int i = 0; i <= n - 2; i++) {
			System.out.println(i);
			File fileInput2;
			try {
				fileInput2 = listFolder(inputFile, i);

				if (setNumber(fileInput2) == 1) {
					File destFinal = new File(outputFile + "\\" + fileInput2.getName() + ".jpg");
					convert(fileInput2, destFinal, 0);
				} else {
					this.Dcm2jpg(fileInput2, outputFile);
				}
				setFinish(true);
			} catch (IOException ex) {
				Logger.getLogger(ConversorDicomJPG.class.getName()).log(Level.SEVERE, null, ex);
			}
			outputFile.mkdirs();
		}
	}

	public void convert(File src, File dest, int value) throws IOException {
		BufferedImage bi = chargeImageDicomBufferise(src, value);
		bi = convert(bi);

		ImageOutputStream ios = ImageIO.createImageOutputStream(dest);
		try {
			writeImage(bi, dest);
		} finally {
			try {
				ios.close();
			} catch (IOException ignore) {}
		}

	}

	private BufferedImage convert(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		if (cm instanceof PaletteColorModel)
			return ((PaletteColorModel) cm).convertToIntDiscrete(bi.getData());
		return bi;
	}

	public BufferedImage chargeImageDicomBufferise(File file, int value) throws IOException {
		Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
		ImageReader readers = iter.next();
		DicomImageReadParam param1 = (DicomImageReadParam) readers.getDefaultReadParam();
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		readers.setInput(iis, false);
		BufferedImage image = readers.read(value, param1);
		System.out.println(image);
		readers.dispose();
		return image;

	}

	public int setNumber(File file) throws IOException {
		Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
		ImageReader readers = (ImageReader) iter.next();
		DicomImageReadParam param1 = (DicomImageReadParam) readers.getDefaultReadParam();
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		readers.setInput(iis, false);
		int number = readers.getNumImages(true);
		System.out.println(number);
		return number;
	}

	public static int numberFile(File folder) {
		int nb = 0;
		if (folder.isDirectory()) {

			File[] list = folder.listFiles();
			if (list != null) {
				nb = list.length;
				return nb;

			} else {
				System.err.println(folder + " : Erro");

			}
		}
		return nb;
	}

	public File listFolder(File folder, int i) throws IOException {
		System.out.println(folder.getAbsolutePath());
		File fileOutput = null;

		if (folder.isDirectory()) {
			File[] list = folder.listFiles();
			if (list != null) {

				fileOutput = list[i];
				return fileOutput;

			} else {
				System.err.println(folder + "error");
			}
		}
		return fileOutput;

	}

	public File[] listeRepertoire2(File folder) {
		System.out.println(folder.getAbsolutePath());
		if (folder.isDirectory()) {
			File[] list = folder.listFiles();
			return list;
		}
		return null;
	}

	private void writeImage(BufferedImage bi, File outputFile)throws IOException {
		ImageIO.write(bi, "jpg", outputFile);
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public Boolean getFinish() {
		return finish;
	}

}
