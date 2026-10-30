
package Evaluacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SimulacionCMYK {

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

                    double r = color.getRed() / 255.0;
                    double g = color.getGreen() / 255.0;
                    double b = color.getBlue() / 255.0;

                    double k = 1 - Math.max(r, Math.max(g, b));

                    double c, m, y1;

                    if (k == 1) {
                        c = 0;
                        m = 0;
                        y1 = 0;
                    } else {
                        c = (1 - r - k) / (1 - k);
                        m = (1 - g - k) / (1 - k);
                        y1 = (1 - b - k) / (1 - k);
                    }

                    int R = (int) Math.round(255 * (1 - c) * (1 - k));
                    int G = (int) Math.round(255 * (1 - m) * (1 - k));
                    int B = (int) Math.round(255 * (1 - y1) * (1 - k));

                    R = Math.max(0, Math.min(255, R));
                    G = Math.max(0, Math.min(255, G));
                    B = Math.max(0, Math.min(255, B));

                    resultado.setRGB(x, y, new Color(R, G, B).getRGB());
                }
            }

            ImageIO.write(
                resultado,
                "jpg",
                new File("Evaluacion/resultado_cmyk.jpg")
            );

        } catch (IOException e) {
            System.out.println("Error al procesar la imagen.");
        }
    }
}
