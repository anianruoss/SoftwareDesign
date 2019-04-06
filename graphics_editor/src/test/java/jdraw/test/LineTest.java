package jdraw.test;

import jdraw.figures.Line;
import org.junit.Before;

public class LineTest extends BaseFigureTest {

    @Before
    public void setUp() {
        f = new Line(1, 1, 20, 10);
        g = new Line(10, 10, 10, 10);
        cnt = 0;
    }

}
