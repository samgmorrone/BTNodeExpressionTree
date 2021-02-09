import java.io.*;
import java.util.*;

public class ExpressionTree {

    public static void main(String[] args) {
        String fileName = "in.dat";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (br.ready()) {
                line = br.readLine().replace('$', ' ');
                String garbage = br.readLine();
                System.out.println("Postfix expression is: " + line);
                BTNode tree = makeTree(line);
                printTree(tree, 0);
                System.out.print("Fully parenthesized infix expression is: ");
                tree.inorderPrint();
                System.out.println();
                System.out.println("Value of this expression is: " + evaluate(tree));
                System.out.println("Press <ENTER> to continue ...");
                new BufferedReader(new InputStreamReader(System.in)).readLine();

            }

            System.out.println("No more expressions.");

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

    }

    public static BTNode makeTree(String s) {
        String next;
        String operator;
        Scanner scan = new Scanner(s);
        Stack<BTNode> stack = new Stack<BTNode>();
        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                BTNode node1 = new BTNode(scan.nextInt(), null, null);
                stack.push(node1);
            } else if (stack.size() >= 2) {
                operator = scan.next();
                if (operator.equals("!")) {
                    BTNode node2 = new BTNode(operator, stack.pop(), null);
                    stack.push(node2);
                } else {
                    BTNode node2 = new BTNode(operator, stack.pop(), stack.pop());
                    stack.push(node2);
                }
            } else {
                operator = scan.next();
                BTNode node2 = new BTNode(operator, stack.pop(), null);
                stack.push(node2);
            }

        }

        return stack.pop();
    }

    public static void printTree(BTNode btn, int position) {
        if (btn == null) {
            return;
        }
        printTree(btn.getRight(), position + 1);
        for (int i = 0; i < position; i++) {
            System.out.print("\t");
        }
        System.out.print(btn.getData());
        System.out.println();
        printTree(btn.getLeft(), position + 1);
    }

    public static int evaluate(BTNode root) {
        if (root == null) {
            return 0;
        }
        if ((root.getRight() == null) && (root.getLeft() == null)) {
            return Integer.parseInt(root.getData().toString());
        } else {
            String op = (String) root.getData();
            switch (op) {
                case "+":
                    return evaluate(root.getLeft()) + evaluate(root.getRight());
                case "-":
                    return evaluate(root.getLeft()) - evaluate(root.getRight());
                case "*":
                    return evaluate(root.getLeft()) * evaluate(root.getRight());
                case "%":
                    return evaluate(root.getLeft()) % evaluate(root.getRight());
                case "/":
                    return evaluate(root.getLeft()) / evaluate(root.getRight());
                case "^":
                    double x = Double.parseDouble(String.valueOf(evaluate(root.getLeft())));
                    double y = Double.parseDouble(String.valueOf(evaluate(root.getRight())));
                    int p = (int) (Math.pow(x, y));
                    return p;
                case "==":
                    return evaluate(root.getLeft()) == evaluate(root.getRight()) ? 1 : 0;
                case ">=":
                    return evaluate(root.getLeft()) >= evaluate(root.getRight()) ? 1 : 0;
                case "<=":
                    return evaluate(root.getLeft()) <= evaluate(root.getRight()) ? 1 : 0;
                case "<":
                    return evaluate(root.getLeft()) < evaluate(root.getRight()) ? 1 : 0;
                case ">":
                    return evaluate(root.getLeft()) > evaluate(root.getRight()) ? 1 : 0;
                case "!=":
                    return evaluate(root.getLeft()) != evaluate(root.getRight()) ? 1 : 0;
                case "&&":
                    boolean j = evaluate(root.getLeft()) > 0 ? true : false;
                    boolean k = evaluate(root.getRight()) > 0 ? true : false;
                    return j && k ? 1 : 0;
                case "||":
                    boolean m = evaluate(root.getLeft()) > 0 ? true : false;
                    boolean n = evaluate(root.getRight()) > 0 ? true : false;
                    return m || n ? 1 : 0;
                default:
                    return evaluate(root.getLeft()) > 0 ? 0 : 1;
            }
        }
    }
}