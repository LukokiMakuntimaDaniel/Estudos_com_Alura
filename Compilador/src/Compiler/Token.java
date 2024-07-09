package Compiler;

public class Token {
    private TokenType tipo;
    private String valor;
    private int numeroLinha; // Adicionando o número da linha

    public Token(TokenType tipo, String valor, int numeroLinha) {
        this.tipo = tipo;
        this.valor = valor;
        this.numeroLinha = numeroLinha; // Definindo o número da linha
    }

    public TokenType getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public int getNumeroLinha() {
        return numeroLinha;
    }

    @Override
    public String toString() {
        return String.format("Linha %d: [ %-30s <==> '%-35s']", numeroLinha, tipo, valor);
    }
}
