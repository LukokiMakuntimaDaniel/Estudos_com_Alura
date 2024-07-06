package Analisadores;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        return programa();
    }

    private ASTNode programa() {
        match(TokenType.INICIO);
        List<ASTNode> declaracoes = declaracoes();
        match(TokenType.FIM);
        return new ASTNode.Program(declaracoes);
    }

    private List<ASTNode> declaracoes() {
        List<ASTNode> declaracoes = new ArrayList<>();
        while (pos < tokens.size() && tokens.get(pos).getTipo() != TokenType.FIM) {
            declaracoes.add(declaracao());
        }
        return declaracoes;
    }

    private ASTNode declaracao() {
        return comandoEscreva();
    }

    private ASTNode comandoEscreva() {
        match(TokenType.ESCREVA);
        match(TokenType.PARENTESE_ESQUERDO);
        ASTNode expressao = expressao();
        match(TokenType.PARENTESE_DIREITO);
        match(TokenType.PONTO_VIRGULA);
        return new ASTNode.Escreva(expressao);
    }

    private ASTNode expressao() {
        ASTNode termo = termo();
        while (pos < tokens.size() && (tokens.get(pos).getTipo() == TokenType.MAIS || tokens.get(pos).getTipo() == TokenType.MENOS)) {
            Token operador = tokens.get(pos);
            pos++;
            termo = new ASTNode.BinOp(termo, operador, termo());
        }
        return termo;
    }

    private ASTNode termo() {
        ASTNode fator = fator();
        while (pos < tokens.size() && (tokens.get(pos).getTipo() == TokenType.MULTIPLICACAO || tokens.get(pos).getTipo() == TokenType.DIVISAO)) {
            Token operador = tokens.get(pos);
            pos++;
            fator = new ASTNode.BinOp(fator, operador, fator());
        }
        return fator;
    }

    private ASTNode fator() {
        Token token = tokens.get(pos);
        if (token.getTipo() == TokenType.NUMERO_INTEIRO) {
            pos++;
            return new ASTNode.Numero(token);
        } else if (token.getTipo() == TokenType.IDENTIFICADOR) {
            pos++;
            return new ASTNode.Identificador(token);
        } else if (token.getTipo() == TokenType.PARENTESE_ESQUERDO) {
            pos++;
            ASTNode expressao = expressao();
            match(TokenType.PARENTESE_DIREITO);
            return expressao;
        } else {
            throw new RuntimeException("Erro de sintaxe: fator inesperado " + token.getValor());
        }
    }

    private void match(TokenType tipo) {
        if (tokens.get(pos).getTipo() == tipo) {
            pos++;
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado " + tipo + " mas encontrado " + tokens.get(pos).getTipo());
        }
    }
}
