package Calculator.logic;

public class Calculator {
    private int x;
    private int y;
    private String operator;
    private double result;

    public Calculator(int x, int y, String operator) {
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOperator() {
        return operator;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

//    public void setOperator(String operator) throws NoSuchOperatorException {
//        if (operator.equals("+")||operator.equals("-")||operator.equals("/")||operator.equals("*")) {
//            this.operator = operator;
//        } else {
//            throw new NoSuchOperatorException(operator);
//        }
//    }

    public void setResult(double result) {
        this.result = result;
    }


    public double getSum(double x, double y) {
        double sum;
        sum = x + y;
        return sum;
    }

    public double getSubtraction(double x, double y) {
        double sub;
        sub = x - y;
        return sub;
    }
    public double getMultiply(double x, double y) {
        double result;
        result = x * y;
        return result;
    }
    public double getDivision(double x, double y) {
        double result;
        result = x / y;
        return result;
    }


    public double getResult() throws ZeroDivisionException, NoSuchOperatorException {
        switch (this.operator) {
            case "+":
                result = getSum(this.x, this.y);
                break;
            case "-":
                result = getSubtraction(this.x, this.y);
                break;
            case "*":
                result = getMultiply(this.x, this.y);
                break;
            case "/":
                if (this.y == 0) {
                    throw new ZeroDivisionException();
                } else {
                    result = getDivision(this.x, this.y);
                }
                break;
            default:
                throw new NoSuchOperatorException();

        }
        return result;
    }

    public static class ZeroDivisionException extends Exception {
    }
}
