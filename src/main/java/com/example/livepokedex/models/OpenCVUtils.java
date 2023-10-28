package com.example.livepokedex.models;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVUtils {
    static {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }
    // Convert BufferedImage to Mat
    public static Mat convertBufferedImageToMat(BufferedImage image) {
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    
    // Convert Mat to BufferedImage

    public BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] b = new byte[bufferSize];
        mat.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }
    
    public static Mat resizeImage(Mat inputImage, int targetWidth, int targetHeight) {
        Mat outputImage = new Mat();
        Size newSize = new Size(targetWidth, targetHeight);
        Imgproc.resize(inputImage, outputImage, newSize);
        return outputImage;
    }

    public static Mat cropImage(Mat inputImage, int x, int y, int width, int height) {
        return new Mat(inputImage, new org.opencv.core.Rect(x, y, width, height));
    }

    public static Mat convertToGrayscale(Mat inputImage) {
        Mat grayscaleImage = new Mat();
        Imgproc.cvtColor(inputImage, grayscaleImage, Imgproc.COLOR_BGR2GRAY);
        return grayscaleImage;
    }

    public static Mat loadImage(String imagePath) {
        return Imgcodecs.imread(imagePath);
    }

    public static void saveImage(Mat image, String outputPath) {
        Imgcodecs.imwrite(outputPath, image);
    }
}
