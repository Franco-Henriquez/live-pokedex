package com.example.livepokedex.models;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class OcrClass {
    public static String performOCR(String imageData) {
    	System.setProperty("TESSDATA_PREFIX", "C:\\Users\\franc\\Documents\\workspace-spring-tool\\week6\\live-pokedex\\src\\main\\resources\\static\\Tess4J");
    	System.out.println("Image Path: " + imageData);
    	String[] base64ImageDataParts = imageData.split(",");
    	String base64ImageData = base64ImageDataParts[1];
    	System.out.println("Image Path: " + base64ImageData);
        java.util.logging.Logger.getLogger("net.sourceforge.tess4j").setLevel(java.util.logging.Level.FINE);

        byte[] imageBytes = Base64.decodeBase64(base64ImageData);
        ITesseract tesseract = new Tesseract();
        //OpenCVUtils openCVUtils = new OpenCVUtils();
        tesseract.setDatapath("C:\\Users\\franc\\Documents\\workspace-spring-tool\\week6\\live-pokedex\\src\\main\\resources\\static\\Tess4J\\tessdata");
        tesseract.setLanguage("eng");

//        try {
//            // Set the path to the Tesseract executable (optional, can be skipped if it's in your system's PATH)
//            tesseract.setDatapath("C:\\Users\\franc\\Documents\\workspace-spring-tool\\week6\\live-pokedex\\src\\main\\resources\\static\\Tess4J\\tessdata");
//
//            // Do the OCR on the given image file
//            System.out.println("Image Path: " + imageData);
//            File imageFile = new File(imageData+".png");
//            String recognizedText = tesseract.doOCR(imageFile);
//            System.out.println("Processing recognized Text");
//            return recognizedText;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error during OCR processing: " + e.getMessage();
//        }
        
        try {
            // Save the image data to a temporary file
            File tempFile = File.createTempFile("tempImage", ".png");
            System.out.println("TEMP FILE PATH: "+tempFile);
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(imageBytes);
            }
            

            // Load the original image
            BufferedImage originalImage = ImageIO.read(tempFile);

            // Resize and convert to grayscale
            int targetWidth = 0; // Set your desired width
            int targetHeight = 0; // Set your desired height
            // Assuming the trading card dimensions are 1106x696
            int cardWidth = 1106;
            int cardHeight = 696;

            // Desired crop dimensions
            int cropWidth = 100;
            int cropHeight = 100;
            
            // Calculate coordinates for the top-left corner of the crop area
            int x = cardWidth - cropWidth; // X-coordinate for the top-left corner
            int y = cardHeight - cropHeight; // Y-coordinate for the top-left corner

	         // Ensure x and y are non-negative
	         x = Math.max(x, 0);
	         y = Math.max(y, 0);
	        //Mat matImage = OpenCVUtils.convertBufferedImageToMat(originalImage);
            //Mat croppedImage = openCVUtils.cropImage(matImage, x, y, cropWidth, cropHeight);
            //BufferedImage convertedImage = openCVUtils.matToBufferedImage(croppedImage);
            //BufferedImage processedImage = ImageProcessing.resizeAndConvertToGrayscale(convertedImage, targetWidth, targetHeight);
	         
	         //Only convert to grayscale
	         BufferedImage processedImage = ImageProcessing.resizeAndConvertToGrayscale(originalImage, targetWidth, targetHeight);

            // Save processed image for debugging (optional)
            try {
                File outputImageFile = new File("C:\\Users\\franc\\AppData\\Local\\Temp\\processed_image.png");
                ImageIO.write(processedImage, "png", outputImageFile);
                System.out.println("Processed image saved: " + outputImageFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            // Do the OCR on the processed image
            String recognizedText = tesseract.doOCR(processedImage);
            
            
            
            // Do the OCR on the temporary image file
            //String recognizedText = tesseract.doOCR(tempFile);
            System.out.println("Processing recognized Text");

            // Clean up: delete the temporary file
            //Files.deleteIfExists(tempFile.toPath());

            return recognizedText;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error during OCR processing: " + e.getMessage();
        } catch (TesseractException e) {
            e.printStackTrace();
            return "Error during OCR processing: " + e.getMessage();
        }
    }
}
