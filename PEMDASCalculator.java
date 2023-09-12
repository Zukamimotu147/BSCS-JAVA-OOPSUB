import java.util.*;

public class PEMDASCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an expression: ");
        String expression = scanner.nextLine();
        double result = calculate(expression);
        System.out.println("Result: " + result);
    }

    public static double calculate(String expression) {
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder numBuilder = new StringBuilder();
                numBuilder.append(ch);
                while (i + 1 < expression.length() && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    numBuilder.append(expression.charAt(++i));
                }
                operands.push(Double.parseDouble(numBuilder.toString()));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    performOperation(operands, operators);
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    performOperation(operands, operators);
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            performOperation(operands, operators);
        }

        return operands.pop();
    }

    private static void performOperation(Stack<Double> operands, Stack<Character> operators) {
        char operator = operators.pop();
        double operand2 = operands.pop();
        double operand1 = operands.pop();
        double result = applyOperator(operand1, operand2, operator);
        operands.push(result);
    }

    private static double applyOperator(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }
}