package Analisadores;

public class EscrevaNode extends CommandNode {
    public final ASTNode expressao;

    public EscrevaNode(ASTNode expressao) {
        this.expressao = expressao;
    }

    @Override
    public String toString() {
        return "Escreva(" + expressao + ")";
    }
}
