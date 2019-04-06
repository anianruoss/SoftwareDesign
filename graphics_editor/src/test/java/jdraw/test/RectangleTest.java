package jdraw.test;

import jdraw.figures.Rect;
import org.junit.Before;

public class RectangleTest extends BaseFigureTest {

    @Before
    public void setUp() {
        f = new Rect(1, 1, 20, 10);
        g = new Rect(10, 10, 10, 10);
        cnt = 0;
    }

}
