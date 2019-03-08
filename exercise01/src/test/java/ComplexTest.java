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
        assertEquals(new Complex(-2, 3), s1);

        Complex s2 = Complex.add(x, z);
        assertEquals(x, s2);
    }

    @Test
    public void subtract() {
        Complex d1 = Complex.subtract(x, y);
        assertEquals(new Complex(4, 1), d1);

        Complex d2 = Complex.subtract(x, z);
        assertEquals(x, d2);
    }

    @Test
    public void multiply() {
        Complex p1 = Complex.multiply(x, y);
        assertEquals(new Complex(-5, -5), p1);

        Complex p2 = Complex.multiply(x, z);
        assertEquals(z, p2);
    }

    @Test
    public void divide() {
        double tolerance = 1e-9;
        Complex q1 = Complex.divide(x, y);
        assertEquals(-1 / 10., q1.getReal(), tolerance);
        assertEquals(-7 / 10., q1.getImag(), tolerance);

        Complex q2 = Complex.divide(z, x);
        assertEquals(z.getReal(), q2.getReal(), 0);
        assertEquals(z.getImag(), q2.getImag(), 0);
    }

    @Test
    public void invert() {
        double tolerance = 1e-9;

        Complex i1 = Complex.invert(x);
        assertEquals(1 / 5., i1.getReal(), tolerance);
        assertEquals(-2 / 5., i1.getImag(), tolerance);

        Complex i2 = Complex.invert(y);
        assertEquals(-3 / 10., i2.getReal(), tolerance);
        assertEquals(-1 / 10., i2.getImag(), tolerance);

    }

    @Test
    public void square() {
        Complex s1 = Complex.square(x);
        assertEquals(new Complex(-3, 4), s1);

        Complex s2 = Complex.square(z);
        assertEquals(new Complex(0, 0), s2);
    }

    @Test
    public void absoluteValue() {
        double a1 = Complex.absoluteValue(x);
        assertEquals(Math.sqrt(5), a1, 0);

        double a2 = Complex.absoluteValue(z);
        assertEquals(0, a2, 0);
    }

    @Test
    public void getReal() {
        assertEquals(1, x.getReal(), 0);
        assertEquals(-3, y.getReal(), 0);
        assertEquals(0, z.getReal(), 0);
    }

    @Test
    public void setReal() {
        x.setReal(-1);
        y.setReal(0);
        z.setReal(1);
        assertEquals(-1, x.getReal(), 0);
        assertEquals(0, y.getReal(), 0);
        assertEquals(1, z.getReal(), 0);
    }

    @Test
    public void getImag() {
        assertEquals(2, x.getImag(), 0);
        assertEquals(1, y.getImag(), 0);
        assertEquals(0, z.getImag(), 0);
    }

    @Test
    public void setImag() {
        x.setImag(-1);
        y.setImag(0);
        z.setImag(1);
        assertEquals(-1, x.getImag(), 0);
        assertEquals(0, y.getImag(), 0);
        assertEquals(1, z.getImag(), 0);
    }
}