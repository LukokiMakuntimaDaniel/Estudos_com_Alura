package Analisadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticAnalyzer {
    private final List<Token> tokens;
    private int pos = 0;
    private final Map<String, TokenType> tabelaSimbolos;

    public SemanticAnalyzer(List<Token> tokens) {
        this.tokens = tokens;
        this.tabelaSimbolos = new HashMap<>();
    }

    public void analisar() {
        if (tokens.get(pos).getTipo() == TokenType.INICIO) {
            pos++;
        } else {
            throw new RuntimeException("Erro semântico: Esperado 'inicio' no início do programa.");
        }

        while (pos < tokens.size() && tokens.get(pos).getTipo() != TokenType.FIM) {
            Token token = tokens.get(pos);

            if (token.getTipo() == TokenType.ESCREVA) {
                processarEscreva();
            } else {
                pos++;
            }
        }

        if (pos < tokens.size() && tokens.get(pos).getTipo() == TokenType.FIM) {
            pos++;
        } else {
            throw new RuntimeException("Erro semântico: Esperado 'fim' no final do programa.");
        }
    }

    private void processarEscreva() {
        pos++;
        if (tokens.get(pos).getTipo() != TokenType.PARENTESE_ESQUERDO) {
            throw new RuntimeException("Erro semântico: Esperado '(' após 'escreva'.");
        }
        pos++;
        verificarExpressao();
        if (tokens.get(pos).getTipo() != TokenType.PARENTESE_DIREITO) {
            throw new RuntimeException("Erro semântico: Esperado ')' após expressão.");
        }
        pos++;
        if (tokens.get(pos).getTipo() != TokenType.PONTO_VIRGULA) {
            throw new RuntimeException("Erro semântico: Esperado ';' após ')'.");
        }
        pos++;
    }

    private void verificarExpressao() {
        verificarTermo();
        while (pos < tokens.size() && (tokens.get(pos).getTipo() == TokenType.MAIS || tokens.get(pos).getTipo() == TokenType.MENOS)) {
            pos++;
            verificarTermo();
        }
    }

    private void verificarTermo() {
        verificarFator();
        while (pos < tokens.size() && (tokens.get(pos).getTipo() == TokenType.MULTIPLICACAO || tokens.get(pos).getTipo() == TokenType.DIVISAO)) {
            pos++;
            verificarFator();
        }
    }

    private void verificarFator() {
        Token token = tokens.get(pos);
        if (token.getTipo() == TokenType.NUMERO_INTEIRO || token.getTipo() == TokenType.IDENTIFICADOR) {
            pos++;
        } else if (token.getTipo() == TokenType.PARENTESE_ESQUERDO) {
            pos++;
            verificarExpressao();
            if (tokens.get(pos).getTipo() != TokenType.PARENTESE_DIREITO) {
                throw new RuntimeException("Erro semântico: Esperado ')' após expressão.");
            }
            pos++;
        } else {
            throw new RuntimeException("Erro semântico: fator inesperado " + token.getValor());
        }
    }
}
