package Analisadores;

public class NumberNode extends ASTNode {
    public final Token token;

    public NumberNode(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token.getValor();
    }
}
