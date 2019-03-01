import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComplexTest {
    private Complex x, y, z;

    @Before
    public void init() {
        x = new Complex(1, 2);
        y = new Complex(-3, 1);
        z = new Complex();
    }

    @Test
    public void add() {
        Complex s1 = Complex.add(x, y);
        assertEquals("add incorrect", -2, s1.getReal(), 0);
        assertEquals("add incorrect", 3, s1.getImag(), 0);

        Complex s2 = Complex.add(x, z);
        assertEquals("add incorrect", x.getReal(), s2.getReal(), 0);
        assertEquals("add incorrect", x.getImag(), s2.getImag(), 0);
    }

    @Test
    public void subtract() {
        Complex d1 = Complex.subtract(x, y);
        assertEquals("subtract incorrect", 4, d1.getReal(), 0);
        assertEquals("subtract incorrect", 1, d1.getImag(), 0);

        Complex d2 = Complex.subtract(x, z);
        assertEquals("subtract incorrect", x.getReal(), d2.getReal(), 0);
        assertEquals("subtract incorrect", x.getImag(), d2.getImag(), 0);
    }

    @Test
    public void multiply() {
        Complex p1 = Complex.multiply(x, y);
        assertEquals("multiply incorrect", -5, p1.getReal(), 0);
        assertEquals("multiply incorrect", -5, p1.getImag(), 0);

        Complex p2 = Complex.multiply(x, z);
        assertEquals("multiply incorrect", z.getReal(), p2.getReal(), 0);
        assertEquals("multiply incorrect", z.getImag(), p2.getImag(), 0);
    }

    @Test
    public void divide() {
        double tolerance = 1e-9;
        Complex q1 = Complex.divide(x, y);
        assertEquals("divide incorrect", -1 / 10., q1.getReal(), tolerance);
        assertEquals("divide incorrect", -7 / 10., q1.getImag(), tolerance);

        Complex q2 = Complex.divide(z, x);
        assertEquals("divide incorrect", z.getReal(), q2.getReal(), 0);
        assertEquals("divide incorrect", z.getImag(), q2.getImag(), 0);
    }

    @Test
    public void invert() {
        double tolerance = 1e-9;

        Complex i1 = Complex.invert(x);
        assertEquals("invert incorrect", 1 / 5., i1.getReal(), tolerance);
        assertEquals("invert incorrect", -2 / 5., i1.getImag(), tolerance);

        Complex i2 = Complex.invert(y);
        assertEquals("invert incorrect", -3 / 10., i2.getReal(), tolerance);
        assertEquals("invert incorrect", -1 / 10., i2.getImag(), tolerance);

    }

    @Test
    public void absoluteValue() {
        double abs = Complex.absoluteValue(z);
        assertEquals("absolute value incorrect", 0, abs, 0);
    }

    @Test
    public void getReal() {
        assertEquals("getReal incorrect", 1, x.getReal(), 0);
        assertEquals("getReal incorrect", -3, y.getReal(), 0);
        assertEquals("getReal incorrect", 0, z.getReal(), 0);
    }

    @Test
    public void setReal() {
        x.setReal(-1);
        y.setReal(0);
        z.setReal(1);
        assertEquals("setReal incorrect", -1, x.getReal(), 0);
        assertEquals("setReal incorrect", 0, y.getReal(), 0);
        assertEquals("setReal incorrect", 1, z.getReal(), 0);
    }

    @Test
    public void getImag() {
        assertEquals("getImag incorrect", 2, x.getImag(), 0);
        assertEquals("getImag incorrect", 1, y.getImag(), 0);
        assertEquals("getImag incorrect", 0, z.getImag(), 0);
    }

    @Test
    public void setImag() {
        x.setImag(-1);
        y.setImag(0);
        z.setImag(1);
        assertEquals("setImag incorrect", -1, x.getImag(), 0);
        assertEquals("setImag incorrect", 0, y.getImag(), 0);
        assertEquals("setImag incorrect", 1, z.getImag(), 0);
    }
}