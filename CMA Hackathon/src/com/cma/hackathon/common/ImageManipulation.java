/**
 * 
 */
package com.cma.hackathon.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
/**
 * @author 251699
 *
 */
public class ImageManipulation {
	
	//public static String path = CommonUtil.getImagePath();

	/**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
     
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
    
    
    /**
     * Stores the image in the mentioned folder
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return 
     */
    public static void stringToImage(String imageDataString,String fileName) {
    	try {           
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(imageDataString);
             
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = dateFormat.format(date);
            // Write a image byte array into file system
            new File("D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\images\\FSR\\"+dateStr+"\\").mkdirs();
            FileOutputStream imageOutFile = new FileOutputStream(
                    "D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\images\\FSR\\"+dateStr+"\\"+fileName+".jpg");
            //FileOutputStream imageOutFile = new FileOutputStream(path+""+fileName+".jpg");
            imageOutFile.write(imageByteArray);
 
            imageOutFile.close();
            /*if(!fileName.contains("Signature")){
            //rotateImage(fileName,dateStr);
            }*/
 
            System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
    
public static void rotateImage(String fileName,String dateStr){
    	
    	BufferedImage oldImage;
		try {
			
			oldImage = ImageIO.read(new FileInputStream("D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\images\\FSR\\"+dateStr+"\\"+fileName+".jpg"));
	    	BufferedImage newImage = new BufferedImage(oldImage.getHeight(), oldImage.getWidth(), oldImage.getType());
	    	Graphics2D graphics = (Graphics2D) newImage.getGraphics();
	    	graphics.rotate(Math.toRadians(90), newImage.getWidth() / 2, newImage.getHeight() / 2);
	    	graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2, (newImage.getHeight() - oldImage.getHeight()) / 2);
	    	graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
	    	ImageIO.write(newImage, "JPG", new FileOutputStream("D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\images\\FSR\\"+dateStr+"\\"+fileName+".jpg"));
    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
