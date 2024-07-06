package Analisadores;
import java.util.List;

public abstract class ASTNode {
    public static class Program extends ASTNode {
        private final List<ASTNode> declaracoes;

        public Program(List<ASTNode> declaracoes) {
            this.declaracoes = declaracoes;
        }

        @Override
        public String toString() {
            return "Program(" + declaracoes + ")";
        }
    }

    public static class Escreva extends ASTNode {
        private final ASTNode expressao;

        public Escreva(ASTNode expressao) {
            this.expressao = expressao;
        }

        @Override
        public String toString() {
            return "Escreva(" + expressao + ")";
        }
    }

    public static class BinOp extends ASTNode {
        private final ASTNode esquerda;
        private final Token operador;
        private final ASTNode direita;

        public BinOp(ASTNode esquerda, Token operador, ASTNode direita) {
            this.esquerda = esquerda;
            this.operador = operador;
            this.direita = direita;
        }

        @Override
        public String toString() {
            return "BinOp(" + esquerda + ", " + operador + ", " + direita + ")";
        }
    }

    public static class Numero extends ASTNode {
        private final Token numero;

        public Numero(Token numero) {
            this.numero = numero;
        }

        @Override
        public String toString() {
            return "Numero(" + numero + ")";
        }
    }

    public static class Identificador extends ASTNode {
        private final Token identificador;

        public Identificador(Token identificador) {
            this.identificador = identificador;
        }

        @Override
        public String toString() {
            return "Identificador(" + identificador + ")";
        }
    }
}
