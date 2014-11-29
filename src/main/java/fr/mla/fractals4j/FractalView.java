package fr.mla.fractals4j;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class FractalView {

	private double x0 = 0.0;
	private double x1 = 0.0;
	private double y0 = 0.0;
	private double y1 = 0.0;
	private int width = 0;
	private int height = 0;
	private int maxIterations = 0;
	private String workingDirectory;

	private int[][] divPicture;
	private int[] divSpectrum;

	private BufferedImage image;

	public FractalView(double x0, double x1, double y0, double y1, int width, int height, int maxIterations, String workingDirectory) {
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
		this.width = width;
		this.height = height;
		this.maxIterations = maxIterations;
		this.workingDirectory = workingDirectory;
	}

	public BufferedImage getImage() {
		return image;
	}


	public void doSomething() {

		divPicture = new int[width][height];
		divSpectrum = new int[maxIterations];


		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				double xc = ((width - i) * x0 + i * x1) / width;
				double yc = ((height - j) * y1 + j * y0) / height;

				int n = computeOrbit(xc, yc);

				divPicture[i][j] = n;
				divSpectrum[n - 1]++;
			}

		}


		computeImage();

	}


	private int computeOrbit(double xc, double yc) {

		int n = 0;
		double x = 0;
		double y = 0;

		double modCsq = 0;

		while ((n < maxIterations) && (modCsq < 4)) {
			double tmpX = x * x - y * y + xc;
			double tmpY = 2 * x * y + yc;
			x = tmpX;
			y = tmpY;
			modCsq = x * x + y * y;
			n++;
		}

		return n;
	}


	public void saveStat() throws IOException {

		try (PrintWriter outputFile = new PrintWriter(new FileWriter(someTimeStampedFileName("stat", "txt")))) {
			for (int n = 0; n < divSpectrum.length; n++) {
				outputFile.println((n + 1) + "\t" + divSpectrum[n]);
			}
		}

	}


	public void computeImage() {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = bufferedImage.getRaster();

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				raster.setPixel(w, h, colorOffset(w, h));
			}
		}
		
		this.image = bufferedImage;
		
	}
	
	private void saveImage() throws IOException {

		if (this.image != null) {
			ImageIO.write(this.image, "PNG", new File(someTimeStampedFileName("render", "png")));
		}
	}


	private int[] colorOffset(int w, int h) {

		int k = 0;
		while ((k < colorScheme.length) && (divPicture[w][h] > colorScheme[k])) {
			k++;
		}
		return colors[k];
	}

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	private String someTimeStampedFileName(String prefix, String ext) {
		return workingDirectory + "/" + prefix + "-" + dateFormat.format(new Date()) + "." + ext;
	}




	private static final int[] colorScheme = new int[]
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 75, 100, 250};

	private static final int[][] colors = new int[][] {
		{32, 232, 83},
		{205, 243, 40},
		{140, 68, 251},
		{221, 130, 62},
		{251, 199, 219},
		{91, 251, 240},
		{12, 26, 232},
		{241, 215, 162},
		{189, 59, 98},
		{62, 4, 202},
		{202, 67, 143},
		{51, 140, 97},
		{101, 11, 102},
		{148, 102, 126},
		{28, 243, 41},
		{200, 186, 121},
		{163, 36, 22},
		{243, 134, 16},
		{39, 99, 8},
		{195, 229, 197},
		{207, 47, 220},
		{199, 24, 194},
		{211, 115, 144},
		{195, 76, 79},
		{88, 186, 230},
		{182, 94, 33},
		{19, 89, 72},
		{61, 9, 234},
		{250, 247, 143},
		{99, 202, 168},
		{232, 14, 73},
		{62, 230, 180},
		{67, 31, 156},
		{73, 157, 56},
		{254, 110, 211},
		{186, 124, 191},
		{137, 122, 204},
		{44, 224, 55},
		{101, 117, 65},
		{7, 156, 92},
		{18, 54, 196},
		{11, 132, 3},
		{22, 156, 13},
		{220, 25, 248},
		{230, 72, 133},
		{252, 127, 164},
		{1, 132, 108},
		{42, 13, 175},
		{64, 138, 56},
		{59, 87, 215},
		{152, 76, 64},
		{61, 74, 8},
		{143, 15, 214},
		// Always add one more color (black) for trailing values
		{0, 0, 0}
	};




}
