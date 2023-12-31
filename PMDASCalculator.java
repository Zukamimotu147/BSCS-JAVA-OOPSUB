 import java.util.Stack;
import java.util.Scanner;

class PMDAS{
    
     public static int calculatePMDAS(String expression) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue; // Skip spaces
            } else if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    num = num * 10 + (expression.charAt(i + 1) - '0');
                    i++;
                }
                values.push(num);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    char operator = operators.pop();
                    int operand2 = values.pop();
                    int operand1 = values.pop();
                    values.push(applyOperator(operator, operand1, operand2));
                }
                operators.pop(); // Pop the '('
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    char operator = operators.pop();
                    int operand2 = values.pop();
                    int operand1 = values.pop();
                    values.push(applyOperator(operator, operand1, operand2));
                }
                operators.push(ch);
            }  
        }

        while (!operators.isEmpty()) {
            char operator = operators.pop();
            int operand2 = values.pop();
            int operand1 = values.pop();
            values.push(applyOperator(operator, operand1, operand2));
        }

        return values.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private static int applyOperator(char operator, int operand1, int operand2) {
          switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                } 
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}


public class PMDASCalculator {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PMDAS pmdas = new PMDAS();
        System.out.print("Enter an expression: ");
        String expression = scan.nextLine();

        int result = pmdas.calculatePMDAS(expression);
        System.out.println("Result: " + result);
    }

   
}
