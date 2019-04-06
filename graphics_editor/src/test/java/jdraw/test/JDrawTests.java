package jdraw.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DrawModelTest.class,
        RectangleTest.class,
        EllipseTest.class,
        LineTest.class
})
public class JDrawTests {
}
