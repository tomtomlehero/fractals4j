package fr.mla.fractals4j;

import fr.mla.fractals4j.algorithm.Orbit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mathieu on 01/12/14.
 */
public class FileUtil {


    private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");



    public static void saveIntArrayToFile(int[] someArray) {

        try (PrintWriter outputFile = new PrintWriter(new FileWriter(someTimeStampedFileName("stat", "txt")))) {
            for (int n = 0; n < someArray.length; n++) {
                outputFile.println(n + "\t" + someArray[n]);
            }
        } catch (IOException e) {
            System.out.println("Unable to save file (IOException)");
        }

    }



    public static void saveComparativeDwellMapToFile(int[][] dwellMap, double[][] continuousDwellMap) {

        try (PrintWriter outputFile = new PrintWriter(new FileWriter(someTimeStampedFileName("stat", "txt")))) {

            int width = dwellMap.length;
            int height = dwellMap[0].length;

            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
            outputFile.println("* " + j + "\t" + i + "\t" + dwellMap[i][j] + "\t" + continuousDwellMap[i][j]);

                }

            }



        } catch (IOException e) {
            System.out.println("Unable to save file (IOException)");
        }

    }




    public static void saveImage(BufferedImage someImage) throws IOException {
            ImageIO.write(someImage, "PNG", new File(someTimeStampedFileName("render", "png")));
    }



    private static String someTimeStampedFileName(String prefix, String ext) {
        return AppConst.workingDirectory + "/" + prefix + "-" + dateFormat.format(new Date()) + "." + ext;
    }

}
