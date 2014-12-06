package fr.mla.fractals4j;

import fr.mla.fractals4j.algorithm.AutomaticDwellLimit;
import fr.mla.fractals4j.algorithm.Orbit;
import fr.mla.fractals4j.color.HSVtoRGB;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class FractalView {

	private double x0 = 0.0;
	private double x1 = 0.0;
	private double y0 = 0.0;
	private double y1 = 0.0;

	private int width = 0;
	private int height = 0;

	int automaticDwellLimit;

	private int[][] dwellMap;
//	private double[][] continuousDwellMap;

//	private DwellMap<Integer> genericDwellMap;


	private BufferedImage image;



	public FractalView(double x0, double x1, double y0, double y1, int width, int height) {
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
		this.width = width;
		this.height = height;
	}

	public BufferedImage getImage() {
		return image;
	}


	public void doSomething() {

		this.dwellMap = new int[width][height];
//		this.continuousDwellMap = new double[width][height];

		System.out.println("X0 = " + this.x0);
		System.out.println("X1 = " + this.x1);
		System.out.println("Y0 = " + this.y0);
		System.out.println("Y1 = " + this.y1);


		this.automaticDwellLimit = AutomaticDwellLimit.getAutomaticDwellLimit(x0, x1, y0, y1, width, height);
		System.out.println("automatic dwell limit = " + automaticDwellLimit);

		long now = System.currentTimeMillis();
		System.out.println("Start");
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				double xc = ((width - i) * x0 + i * x1) / width;
				double yc = ((height - j) * y1 + j * y0) / height;

				this.dwellMap[i][j] = Orbit.dwell(xc, yc, automaticDwellLimit);
//				this.continuousDwellMap[i][j] = Orbit.continuousDwell(xc, yc, automaticDwellLimit);

			}

		}
		System.out.println("Done in " + (System.currentTimeMillis() - now) + " ms.");

//		FileUtil.saveComparativeDwellMapToFile(dwellMap, continuousDwellMap);

		computeImage();

	}





	public void computeImage() {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = bufferedImage.getRaster();

		float[] hsv = new float[3];
		int[] rgb = new int[3];

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {

				if (dwellMap[w][h] == automaticDwellLimit) { // We're inside ==> black
					hsv[2] = 0.0f;
				} else { // We're outside ==> be imaginative !!
					hsv[0] = 360.0f * dwellMap[w][h] / automaticDwellLimit;
					hsv[1] = 0.9f;//(1.0f * h) / height;
					hsv[2] = 0.9f;//(1.0f * w) / width;
				}

//				if (continuousDwellMap[w][h] == (double) automaticDwellLimit) { // We're inside ==> black
//					hsv[2] = 0.0f;
//				} else { // We're outside ==> be imaginative !!
//					hsv[0] = 360.0f * (float)(continuousDwellMap[w][h] / automaticDwellLimit);
//					hsv[1] = 0.9f;//(1.0f * h) / height;
//					hsv[2] = 0.9f;//(1.0f * w) / width;
//				}

				HSVtoRGB.convert(hsv, rgb);

				// Full Color
				raster.setPixel(w, h, rgb);

				// Limited Color
//				raster.setPixel(w, h, colorOffset(w, h));
			}
		}
		
		this.image = bufferedImage;
		
	}
	

//	private int[] colorOffset(int w, int h) {
//
//		int k = 0;
//		while ((k < colorScheme.length) && (dwellMap[w][h] > colorScheme[k])) {
//			k++;
//		}
//		return colors[k];
//	}



	public double getX0() {
		return x0;
	}

	public double getX1() {
		return x1;
	}

	public double getY0() {
		return y0;
	}

	public double getY1() {
		return y1;
	}

//	private static final int[] colorScheme = new int[]
//			{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 75, 100, 250};
//
//	private static final int[][] colors = new int[][] {
//		{32, 232, 83},
//		{205, 243, 40},
//		{140, 68, 251},
//		//..................................................
//		{143, 15, 214},
//		// Always add one more color (black) for trailing values
//		{0, 0, 0}
//	};




}

//class DwellMap<T> {
//
//	private T[][] map;
//
//	public DwellMap(int width, int height) {
//		this.map = new T[width][height];
//	}
//}
