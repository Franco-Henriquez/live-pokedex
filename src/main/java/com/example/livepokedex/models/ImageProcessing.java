package com.example.livepokedex.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageProcessing {

    public static BufferedImage resizeAndConvertToGrayscale(BufferedImage originalImage, int targetWidth, int targetHeight) {
    	if (targetWidth >= 1 && targetHeight >= 1) {
    		//Resize the image
    		BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);
    		// Convert to grayscale
    		BufferedImage grayscaleImage = convertToGrayscale(resizedImage);
            return grayscaleImage;
    	} else {
            // Convert to grayscale only
    		BufferedImage resizedImage = originalImage;
            BufferedImage grayscaleImage = convertToGrayscale(resizedImage);
            return grayscaleImage;
    	}
    	

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }

    private static BufferedImage convertToGrayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayscaleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return grayscaleImage;
    }
}
