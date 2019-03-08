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

    public static Complex square(Complex x) {
        return multiply(x, x);
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

    @Override
    public String toString() {
        if (0 < imag) {
            return Double.toString(real) + '+' + imag + 'i';
        } else if (0 == imag) {
            return Double.toString(real);
        } else {
            return Double.toString(real) + imag + 'i';
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            Complex other = (Complex) obj;
            return this.real == other.real && this.imag == other.imag;
        }
    }
}
