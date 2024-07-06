package Analisadores;

public class BinaryOpNode extends ASTNode {
    public final ASTNode left;
    public final Token operator;
    public final ASTNode right;

    public BinaryOpNode(ASTNode left, Token operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator.getValor() + " " + right + ")";
    }
}

