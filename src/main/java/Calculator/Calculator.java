package Calculator;

public class Calculator {
    private double x;
    private double y;
    private String operator;
    private double result;

    public Calculator(double x, double y, String operator) {
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getOperator() {
        return operator;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getResult() throws ZeroDivisionException {
        switch (this.operator) {
            case "+":
                result = this.x + this.y;
            case "-":
                result = this.x - this.y;
            case "*":
                result = this.x * this.y;
            case "/":
                if (this.y == 0) {
                    throw new ZeroDivisionException();
                } else {
                    result = this.x / this.y;
                }
                break;
        }
        return result;
    }
}
