package Analisadores;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String codigoFonte;
    private int pos;
    private char currentChar;

    public Lexer(String codigoFonte) {
        this.codigoFonte = codigoFonte;
        this.pos = 0;
        this.currentChar = codigoFonte.charAt(pos);
    }

    public static String lerCodigoFonte(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }

    private void avancar() {
        pos++;
        if (pos >= codigoFonte.length()) {
            currentChar = '\0'; // Fim do arquivo
        } else {
            currentChar = codigoFonte.charAt(pos);
        }
    }

    private void pularEspacos() {
        while (Character.isWhitespace(currentChar)) {
            avancar();
        }
    }

    private Token numero() {
        StringBuilder result = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            result.append(currentChar);
            avancar();
        }
        return new Token(TokenType.NUMERO_INTEIRO, result.toString());
    }

    private Token identificador() {
        StringBuilder result = new StringBuilder();
        while (Character.isLetterOrDigit(currentChar)) {
            result.append(currentChar);
            avancar();
        }
        String valor = result.toString();
        switch (valor) {
            case "inicio": return new Token(TokenType.INICIO, valor);
            case "fim": return new Token(TokenType.FIM, valor);
            case "se": return new Token(TokenType.SE, valor);
            case "entao": return new Token(TokenType.ENTAO, valor);
            case "senao": return new Token(TokenType.SENAO, valor);
            case "enquanto": return new Token(TokenType.ENQUANTO, valor);
            case "faca": return new Token(TokenType.FACA, valor);
            case "escreva": return new Token(TokenType.ESCREVA, valor);
            case "leia": return new Token(TokenType.LEIA, valor);
            default: return new Token(TokenType.IDENTIFICADOR, valor);
        }
    }

    public List<Token> gerarTokens() {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                pularEspacos();
                continue;
            }

            if (Character.isDigit(currentChar)) {
                tokens.add(numero());
                continue;
            }

            if (Character.isLetter(currentChar)) {
                tokens.add(identificador());
                continue;
            }

            switch (currentChar) {
                case '+':
                    tokens.add(new Token(TokenType.MAIS, "+"));
                    avancar();
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MENOS, "-"));
                    avancar();
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLICACAO, "*"));
                    avancar();
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIVISAO, "/"));
                    avancar();
                    break;
                case '=':
                    avancar();
                    if (currentChar == '=') {
                        tokens.add(new Token(TokenType.IGUAL_IGUAL, "=="));
                        avancar();
                    } else {
                        tokens.add(new Token(TokenType.IGUAL, "="));
                    }
                    break;
                case '!':
                    avancar();
                    if (currentChar == '=') {
                        tokens.add(new Token(TokenType.DIFERENTE, "!="));
                        avancar();
                    }
                    break;
                case '<':
                    avancar();
                    if (currentChar == '=') {
                        tokens.add(new Token(TokenType.MENOR_IGUAL, "<="));
                        avancar();
                    } else {
                        tokens.add(new Token(TokenType.MENOR, "<"));
                    }
                    break;
                case '>':
                    avancar();
                    if (currentChar == '=') {
                        tokens.add(new Token(TokenType.MAIOR_IGUAL, ">="));
                        avancar();
                    } else {
                        tokens.add(new Token(TokenType.MAIOR, ">"));
                    }
                    break;
                case ';':
                    tokens.add(new Token(TokenType.PONTO_VIRGULA, ";"));
                    avancar();
                    break;
                case ',':
                    tokens.add(new Token(TokenType.VIRGULA, ","));
                    avancar();
                    break;
                case '(':
                    tokens.add(new Token(TokenType.PARENTESE_ESQUERDO, "("));
                    avancar();
                    break;
                case ')':
                    tokens.add(new Token(TokenType.PARENTESE_DIREITO, ")"));
                    avancar();
                    break;
                case '{':
                    tokens.add(new Token(TokenType.CHAVE_ESQUERDA, "{"));
                    avancar();
                    break;
                case '}':
                    tokens.add(new Token(TokenType.CHAVE_DIREITA, "}"));
                    avancar();
                    break;
                default:
                    throw new RuntimeException("Caractere inesperado: " + currentChar);
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

}
