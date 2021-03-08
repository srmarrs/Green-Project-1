package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML Button btnGrayScale;
    @FXML Button btnSepia;
    @FXML Button btnSurprise;
    @FXML ImageView imgBefore;
    @FXML ImageView imgAfter;

    //Import Image with ImagePicker there's an example of it on D2l

    //Convert before image to Gray Scale in the After Image
    public void convertGrayScale(ActionEvent actionEvent) {
        imgAfter.setImage(imgBefore.getImage());
        Image image = imgAfter.getImage();

        //calls the grayscale method
        imgAfter.setImage(grayscale(image));
    }

    //grayscale method called from convertGrayScale
    static Image grayscale(Image img) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);

        //Initializing the loops to run through the pixels
        for (int x = 0; x < bImage.getWidth(); x++) {
            for (int y= 0; y < bImage.getHeight(); y++) {

                //setting up the pixels so we can change the values
                int pixel = bImage.getRGB(x,y);
                java.awt.Color newPixel = new java.awt.Color(pixel);

                //Getting the RGB values for each pixel
                int red = newPixel.getRed();
                int green = newPixel.getGreen();
                int blue = newPixel.getBlue();

                //The grayscale calculation
                int grayscale = ((red + green + blue) / 3);
                java.awt.Color newGrayscale = new java.awt.Color(grayscale,grayscale,grayscale);
                bImage.setRGB(x,y, newGrayscale.getRGB());
            }
        }
        //Returning the Image
        Image image2 = SwingFXUtils.toFXImage(bImage, null);
        return image2;
    }

    //Convert Image to Sepia in the After Image
    public void convertSepia(ActionEvent actionEvent) {
        imgAfter.setImage(imgBefore.getImage());
        Image image = imgAfter.getImage();

        //Calling the Sepia method
        imgAfter.setImage(sepia(image));
    }

    //Sepia method from convertSepia
    static Image sepia(Image img) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);

        //Initializing the loops
        for (int x = 0; x < bImage.getWidth(); x++) {
            for (int y= 0; y < bImage.getHeight(); y++) {

                //Initializing the pixel variables so we can change them
                int pixel = bImage.getRGB(x,y);
                java.awt.Color newPixel = new java.awt.Color(pixel);

                //Sepia Calculation
                int red = (int) ((newPixel.getRed() * 0.393) + (newPixel.getGreen() * 0.769) + (newPixel.getBlue() * 0.189));
                int green = (int) ((newPixel.getRed() * 0.349) + (newPixel.getGreen() * 0.686) + (newPixel.getBlue() * 0.168));
                int blue = (int) ((newPixel.getRed() * 0.272) + (newPixel.getGreen() * 0.534) + (newPixel.getBlue() * 0.131));

                //If - else statements to make sure RGB values don't exceed 255
                if (red > 255) {
                    red = 255;
                }else {
                    red = (int) ((newPixel.getRed() * 0.393) + (newPixel.getGreen() * 0.769) + (newPixel.getBlue() * 0.189));
                }

                if (green > 255) {
                    green = 255;
                } else {
                    green = (int) ((newPixel.getRed() * 0.349) + (newPixel.getGreen() * 0.686) + (newPixel.getBlue() * 0.168));
                }

                if (blue > 255) {
                    blue = 255;
                } else {
                    blue = (int) ((newPixel.getRed() * 0.272) + (newPixel.getGreen() * 0.534) + (newPixel.getBlue() * 0.131));
                }

                java.awt.Color newSepia = new java.awt.Color(red, green, blue);
                bImage.setRGB(x,y,newSepia.getRGB());
            }

        }
        //Returning the image
        Image image2 = SwingFXUtils.toFXImage(bImage, null);
        return image2;
    }

    //Convert Image to whatever you want in the After Image
    public void convertSurprise(ActionEvent actionEvent) {
        imgAfter.setImage(imgBefore.getImage());
        Image image = imgAfter.getImage();

        imgAfter.setImage(surprise(image));
    }

    public Image surprise(Image img) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
        BufferedImage newImage = rotateImage(bImage);
        Image image2 = SwingFXUtils.toFXImage(newImage, null);

        return image2;
    }

    public java.awt.Color ChangePixel(int pixel) {
        java.awt.Color color = new java.awt.Color(pixel);
        int alpha = color.getAlpha();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        java.awt.Color newPixelColor = new java.awt.Color(red, green, blue, alpha);
        return newPixelColor;
    }

    public BufferedImage rotateImage(BufferedImage img) {
        int nHeight = img.getHeight();
        int nWidth = img.getWidth();

        for (int y = 0; y < nHeight; y++) {
            for (int x = 0; x < nWidth/2; x++) {

                int pixel1 = img.getRGB(x, y);
                java.awt.Color newPixelColor1 = ChangePixel(pixel1);

                int pixel2 = img.getRGB(nWidth - 1 - x, y);
                java.awt.Color newPixelColor2 = ChangePixel(pixel2);

                img.setRGB(x, y, newPixelColor2.getRGB());
                img.setRGB(nWidth - 1 - x, y, newPixelColor1.getRGB());
            }
        }

        return img;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //File chooser to pick your own image to manipulate
    //Used with the "Open Image" button
    public void openImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        imgBefore.setImage(image);
        imgAfter.setImage(image);
    }
}

