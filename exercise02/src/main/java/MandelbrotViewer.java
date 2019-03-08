import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotViewer extends Application {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Mandelbrot Viewer");
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        paintScene(gc.getPixelWriter(), WIDTH, HEIGHT);
        stage.setScene(new Scene(new Group(canvas)));
        stage.show();
    }

    private void paintScene(PixelWriter wr, int width, int height) {
        final int max_iter = 40;
        final int radius = 2;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                Complex c = new Complex(
                        3. * x / (width - 1.) - 2., 3. * y / (height - 1.) - 1.5
                );

                Complex z = new Complex(0, 0);
                int iter = 0;

                do {
                    z = Complex.add(Complex.square(z), c);
                    ++iter;
                } while (iter < max_iter && Complex.absoluteValue(z) < radius);

                final double color_val = iter;
                Color color = Color.hsb(color_val, 1.0f, 1.0f);
                wr.setColor(x, y, color);
            }
        }
    }
}
