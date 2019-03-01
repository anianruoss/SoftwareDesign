public class Complex {
    private double real;
    private double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex() {
        this.real = 0;
        this.imag = 0;
    }

    public static Complex add(Complex x, Complex y) {
        return new Complex(x.real + y.real, x.imag + y.imag);
    }

    public static Complex subtract(Complex x, Complex y) {
        return new Complex(x.real - y.real, x.imag - y.imag);
    }

    public static Complex multiply(Complex x, Complex y) {
        return new Complex(
                x.real * y.real - x.imag * y.imag,
                x.real * y.imag + x.imag * y.real
        );
    }

    public static Complex divide(Complex x, Complex y) {
        return multiply(x, invert(y));
    }

    public static Complex invert(Complex x) {
        double abs = absoluteValue(x);
        return new Complex(x.real / (abs * abs), -x.imag / (abs * abs));
    }

    public static double absoluteValue(Complex x) {
        return Math.sqrt(x.real * x.real + x.imag * x.imag);
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

}
