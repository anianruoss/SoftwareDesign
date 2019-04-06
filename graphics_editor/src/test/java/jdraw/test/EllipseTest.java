package jdraw.test;

import jdraw.figures.Ellipse;
import org.junit.Before;

public class EllipseTest extends BaseFigureTest {

    @Before
    public void setUp() {
        f = new Ellipse(1, 1, 20, 10);
        g = new Ellipse(10, 10, 10, 10);
        cnt = 0;
    }

}
