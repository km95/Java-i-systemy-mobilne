package sample.Model;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawerTask extends Task {
    GraphicsContext gc;
    BufferedImage buffer;
    private double x;
    private double y;
    private int tmp1;
    private int tmp2;
    private Random random = new Random();
    private double valOfIntegral;
    private int repetition;

    public DrawerTask(GraphicsContext gc, int repetition) {
        tmp1 = 0;
        tmp2 = 0;

        this.gc = gc;
        this.repetition = repetition;
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);  // utworzenie buffora
    }

    @Override
    protected Object call() throws Exception {
        for (int i = 0; i < repetition; i++) {
            x = -8 + 16 * random.nextDouble();
            y = -8 + 16 * random.nextDouble();

            if (Equation.calc(x, y)) {              // musimy przeskalowac
                buffer.setRGB((int) (400 - 400 * x / 8), (int) (400 - 400 * y / 8), Color.YELLOW.getRGB());
                tmp1++;
            } else {
                buffer.setRGB((int) (400 - 400 * x / 8), (int) (400 - 400 * y / 8), Color.BLUE.getRGB());
                tmp2++;
            }

            if (i % 1000 == 0) {
                gc.drawImage(SwingFXUtils.toFXImage(buffer, null), 0, 0);
                valOfIntegral = (double) tmp1 / (tmp1 + tmp2);
                updateProgress(i, repetition);          // aktualizacja pasku progresu
            }
            if (isCancelled()) break;
        }


        return valOfIntegral*16*16;
    }

}

