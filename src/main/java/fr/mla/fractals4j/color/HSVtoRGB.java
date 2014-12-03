package fr.mla.fractals4j.color;

/**
 * Created by mathieu on 02/12/14.
 */
public class HSVtoRGB {

    public static void main(String[] args) {

        float[] hsv = new float[3];
        float[] rgb = new float[3];

        try {
            hsv[0] = Float.parseFloat(args[0]);
            hsv[1] = Float.parseFloat(args[1]);
            hsv[2] = Float.parseFloat(args[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("H=" + hsv[0] + " S=" + hsv[1] + " V=" + hsv[2]);
        HSVtoRGB.convert(hsv, rgb);
        System.out.println("R=" + rgb[0] + " G=" + rgb[1] + " B=" + rgb[2]);
        System.out.println("R=" + (int)(255 * rgb[0]) + " G=" + (int)(255 * rgb[1]) + " B=" + (int)(255 * rgb[2]));
    }


    public static void convert(float[] hsv, float[] rgb) {

        int i;
        float f, p, q, t;

        if (hsv[1] == 0) {
            // achromatic (grey)
            rgb[0] = hsv[2];
            rgb[1] = hsv[2];
            rgb[2] = hsv[2];
            return;
        }

        hsv[0] /= 60;			// sector 0 to 5
        i = (int) hsv[0];
        f = hsv[0] - i;			// factorial part of h
        p = hsv[2] * ( 1 - hsv[1] );
        q = hsv[2] * ( 1 - hsv[1] * f );
        t = hsv[2] * ( 1 - hsv[1] * ( 1 - f ) );

        switch( i ) {
            case 0:
                rgb[0] = hsv[2];
                rgb[1] = t;
                rgb[2] = p;
                break;
            case 1:
                rgb[0] = q;
                rgb[1] = hsv[2];
                rgb[2] = p;
                break;
            case 2:
                rgb[0] = p;
                rgb[1] = hsv[2];
                rgb[2] = t;
                break;
            case 3:
                rgb[0] = p;
                rgb[1] = q;
                rgb[2] = hsv[2];
                break;
            case 4:
                rgb[0] = t;
                rgb[1] = p;
                rgb[2] = hsv[2];
                break;
            default:		// case 5:
                rgb[0] = hsv[2];
                rgb[1] = p;
                rgb[2] = q;
                break;
        }


    }




    public static void convert(float[] hsv, int[] rgb) {

        int i;
        float f;
        int p, q, t, v;

        v = (int)(255 * hsv[2]);


        if (hsv[1] == 0) {
            // achromatic (grey)
            rgb[0] = v;
            rgb[1] = v;
            rgb[2] = v;
            return;
        }

        hsv[0] /= 60;			// sector 0 to 5
        i = (int) hsv[0];
        f = hsv[0] - i;			// factorial part of h
        p = (int)(255 * hsv[2] * ( 1 - hsv[1] ));
        q = (int)(255 * hsv[2] * ( 1 - hsv[1] * f ));
        t = (int)(255 * hsv[2] * ( 1 - hsv[1] * ( 1 - f ) ));

        switch( i ) {
            case 0:
                rgb[0] = v;
                rgb[1] = t;
                rgb[2] = p;
                break;
            case 1:
                rgb[0] = q;
                rgb[1] = v;
                rgb[2] = p;
                break;
            case 2:
                rgb[0] = p;
                rgb[1] = v;
                rgb[2] = t;
                break;
            case 3:
                rgb[0] = p;
                rgb[1] = q;
                rgb[2] = v;
                break;
            case 4:
                rgb[0] = t;
                rgb[1] = p;
                rgb[2] = v;
                break;
            default:		// case 5:
                rgb[0] = v;
                rgb[1] = p;
                rgb[2] = q;
                break;
        }


    }




//    public static void convert1(float hue, float saturation, float value, int[] rgb) {
//
//        int i;
//        float f;
//        int p, q, t;
//
//        if (saturation == 0) {
//            // achromatic (grey)
//            rgb[0] = (int) (256 * value);
//            rgb[1] = (int) (256 * value);
//            rgb[2] = (int) (256 * value);
//            return;
//        }
//
//        hue /= 60;            // sector 0 to 5
//        i = (int) hue;
//        f = hue - i;            // factorial part of h
//        p = (int) (256 * value * (1 - saturation));
//        q = (int) (256 * value * (1 - saturation * f));
//        t = (int) (256 * value * (1 - saturation * (1 - f)));
//
////        System.out.println("i=" + i + " value=" + value + " f=" + f + " p=" + p + " q=" + t);
//
//        switch (i) {
//            case 0:
//                rgb[0] = (int) (256 * value);
//                rgb[1] = t;
//                rgb[2] = p;
//
////                *r = v;
////                *g = t;
////                *b = p;
//                break;
//            case 1:
//                rgb[0] = q;
//                rgb[1] = (int) (256 * value);
//                rgb[2] = p;
//
////                *r = q;
////                *g = v;
////                *b = p;
//                break;
//            case 2:
//                rgb[0] = p;
//                rgb[1] = (int) (256 * value);
//                rgb[2] = t;
//
////                *r = p;
////                *g = v;
////                *b = t;
//                break;
//            case 3:
//                rgb[0] = p;
//                rgb[1] = q;
//                rgb[2] = (int) (256 * value);
//
////                *r = p;
////                *g = q;
////                *b = v;
//                break;
//            case 4:
//                rgb[0] = t;
//                rgb[1] = p;
//                rgb[2] = (int) (256 * value);
//
////                *r = t;
////                *g = p;
////                *b = v;
//                break;
//            default:        // case 5:
//                rgb[0] = (int) (256 * value);
//                rgb[1] = p;
//                rgb[2] = q;
//
////                *r = v;
////                *g = p;
////                *b = q;
//                break;
//        }
//    }
//
//    public static void convert0(float hue, float saturation, float value, float[] rgb) {
//
//        int i;
//        float f, p, q, t;
//
//        if (saturation == 0) {
//            // achromatic (grey)
//            rgb[0] = value;
//            rgb[1] = value;
//            rgb[2] = value;
//            return;
//        }
//
//        hue /= 60;			// sector 0 to 5
//        i = (int) hue;
//        f = hue - i;			// factorial part of h
//        p = value * ( 1 - saturation );
//        q = value * ( 1 - saturation * f );
//        t = value * ( 1 - saturation * ( 1 - f ) );
//
////        System.out.println("i=" + i + " value=" + value + " f=" + f + " p=" + p + " q=" + t);
//
//        switch( i ) {
//            case 0:
//                rgb[0] = value;
//                rgb[1] = t;
//                rgb[2] = p;
//
////                *r = v;
////                *g = t;
////                *b = p;
//                break;
//            case 1:
//                rgb[0] = q;
//                rgb[1] = value;
//                rgb[2] = p;
//
////                *r = q;
////                *g = v;
////                *b = p;
//                break;
//            case 2:
//                rgb[0] = p;
//                rgb[1] = value;
//                rgb[2] = t;
//
////                *r = p;
////                *g = v;
////                *b = t;
//                break;
//            case 3:
//                rgb[0] = p;
//                rgb[1] = q;
//                rgb[2] = value;
//
////                *r = p;
////                *g = q;
////                *b = v;
//                break;
//            case 4:
//                rgb[0] = t;
//                rgb[1] = p;
//                rgb[2] = value;
//
////                *r = t;
////                *g = p;
////                *b = v;
//                break;
//            default:		// case 5:
//                rgb[0] = value;
//                rgb[1] = p;
//                rgb[2] = q;
//
////                *r = v;
////                *g = p;
////                *b = q;
//                break;
//        }
//
//
//    }



}
