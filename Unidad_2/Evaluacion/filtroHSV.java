
package Evaluacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class filtroHSV {

    public static void main(String[] args) {

        try {
            BufferedImage original = ImageIO.read(
                new File("Evaluacion/base.jpg")
            );

            int ancho = original.getWidth();
            int alto = original.getHeight();

            BufferedImage resultado = new BufferedImage(
                ancho, alto, BufferedImage.TYPE_INT_RGB
            );

            for (int x = 0; x < ancho; x++) {
                for (int y = 0; y < alto; y++) {

                    Color color = new Color(original.getRGB(x, y));

                    float[] hsv = Color.RGBtoHSB(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        null
                    );

                    hsv[1] = Math.min(1.0f, hsv[1] * 1.5f);

                    int nuevoRGB = Color.HSBtoRGB(
                        hsv[0],
                        hsv[1],
                        hsv[2]
                    );

                    resultado.setRGB(x, y, nuevoRGB);
                }
            }

            ImageIO.write(
                resultado,
                "jpg",
                new File("Evaluacion/resultado_hsv.jpg")
            );

        } catch (IOException e) {
            System.out.println("Error al procesar la imagen.");
        }
    }
}
