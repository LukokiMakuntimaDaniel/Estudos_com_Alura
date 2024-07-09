package Compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalisadorLexico {
    private List<Token> tokens;
    private String inputFilePath;
    private int lineNumber;

    private static final List<String> PALAVRAS_RESERVADAS = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", 
            "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", 
            "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", 
            "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", 
            "try", "void", "volatile", "while"
    );

    public AnalisadorLexico(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        this.tokens = new ArrayList<>();
        this.lineNumber = 1;
    }

    public void tokenize() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
           
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println("Linha " + lineNumber + ": " + line);
                tokenizeLine(line);
            }
            tokens.add(new Token(TokenType.FINAL_ARQUIVO, "EOF",lineNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tokenizeLine(String line) {
        char[] chars = line.toCharArray();
        StringBuilder buffer = new StringBuilder();
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (Character.isWhitespace(c)) {
                // Ignora espaços em branco
            } else if (Character.isLetter(c) || c == '_') {
                buffer.setLength(0);
                buffer.append(c);
                while (i + 1 < chars.length && (Character.isLetterOrDigit(chars[i + 1]) || chars[i + 1] == '_')) {
                    buffer.append(chars[++i]);
                }
                String word = buffer.toString();
                if (PALAVRAS_RESERVADAS.contains(word)) {
                    addToken(TokenType.PALAVRA_RESERVADA, word);
                } else {
                    addToken(TokenType.IDENTIFICADOR, word);
                }
            } else if (Character.isDigit(c)) {
                buffer.setLength(0);
                buffer.append(c);
                boolean isFloat = false;
                while (i + 1 < chars.length && (Character.isDigit(chars[i + 1]) || chars[i + 1] == '.')) {
                    if (chars[i + 1] == '.') {
                        isFloat = true;
                    }
                    buffer.append(chars[++i]);
                }
                if (isFloat) {
                    addToken(TokenType.NUMERO_FLUTUANTE, buffer.toString());
                } else {
                    addToken(TokenType.NUMERO_INTEIRO, buffer.toString());
                }
            } else if (c == '"' || c == '\'') {
                char quoteType = c;
                buffer.setLength(0);
                buffer.append(c);
                while (i + 1 < chars.length && chars[i + 1] != quoteType) {
                    buffer.append(chars[++i]);
                }
                if (i + 1 < chars.length) {
                    buffer.append(chars[++i]);
                }
                addToken(c == '"' ? TokenType.STRING : TokenType.CHAR, buffer.toString());
            } else if (c == '/' && i + 1 < chars.length) {
                if (chars[i + 1] == '/') {
                    buffer.setLength(0);
                    buffer.append(c);
                    buffer.append(chars[++i]);
                    while (i + 1 < chars.length) {
                        buffer.append(chars[++i]);
                    }
                    addToken(TokenType.COMENTARIO, buffer.toString());
                } else if (chars[i + 1] == '*') {
                    buffer.setLength(0);
                    buffer.append(c);
                    buffer.append(chars[++i]);
                    while (i + 1 < chars.length && !(chars[i] == '*' && chars[i + 1] == '/')) {
                        buffer.append(chars[++i]);
                    }
                    if (i + 1 < chars.length) {
                        buffer.append(chars[++i]);
                        buffer.append(chars[++i]);
                    }
                    addToken(TokenType.COMENTARIO_PARAGRAFO, buffer.toString());
                } else {
                    addToken(TokenType.DIVISAO, String.valueOf(c));
                }
            } else {
                switch (c) {
                    case '+':
                        if (i + 1 < chars.length && chars[i + 1] == '+') {
                            addToken(TokenType.MAIS_MAIS, "++");
                            i++;
                        } else if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.MAIS_IGUAL, "+=");
                            i++;
                        } else {
                            addToken(TokenType.MAIS, "+");
                        }
                        break;
                    case '-':
                        if (i + 1 < chars.length && chars[i + 1] == '-') {
                            addToken(TokenType.MENOS_MENOS, "--");
                            i++;
                        } else if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.MENOS_IGUAL, "-=");
                            i++;
                        } else {
                            addToken(TokenType.MENOS, "-");
                        }
                        break;
                    case '*':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.MULTIPLICACAO_IGUAL, "*=");
                            i++;
                        } else {
                            addToken(TokenType.MULTIPLICACAO, "*");
                        }
                        break;
                    case '%':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.PERCENTAGEM_IGUAL, "%=");
                            i++;
                        } else {
                            addToken(TokenType.PERCENTAGEM, "%");
                        }
                        break;
                    case '^':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.POTENCIA_IGUAL, "^=");
                            i++;
                        } else {
                            addToken(TokenType.POTENCIA, "^");
                        }
                        break;
                    case '=':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.IGUAL_IGUAL, "==");
                            i++;
                        } else {
                            addToken(TokenType.IGUAL, "=");
                        }
                        break;
                    case '<':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.MENOR_IGUAL, "<=");
                            i++;
                        } else if (i + 1 < chars.length && chars[i + 1] == '<') {
                            if (i + 2 < chars.length && chars[i + 2] == '=') {
                                addToken(TokenType.SHIFT_ESQUERDA_IGUAL, "<<=");
                                i += 2;
                            } else {
                                addToken(TokenType.SHIFT_ESQUERDA, "<<");
                                i++;
                            }
                        } else {
                            addToken(TokenType.MENOR, "<");
                        }
                        break;
                    case '>':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.MAIOR_IGUAL, ">=");
                            i++;
                        } else if (i + 1 < chars.length && chars[i + 1] == '>') {
                            if (i + 2 < chars.length && chars[i + 2] == '=') {
                                addToken(TokenType.SHIFT_DIREITA_IGUAL, ">>=");
                                i += 2;
                            } else {
                                addToken(TokenType.SHIFT_DIREITA, ">>");
                                i++;
                            }
                        } else {
                            addToken(TokenType.MAIOR, ">");
                        }
                        break;
                    case '!':
                        if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.NAO_IGUAL, "!=");
                            i++;
                        } else {
                            addToken(TokenType.NEGACAO_LOGICA, "!");
                        }
                        break;
                    case '&':
                        if (i + 1 < chars.length && chars[i + 1] == '&') {
                            addToken(TokenType.E_LOGICO, "&&");
                            i++;
                        } else if (i + 1 < chars.length && chars[i + 1] == '=') {
                            addToken(TokenType.E_IGUAL, "&=");
                            i++;
                        } else {
                            addToken(TokenType.E, "&");
                        }
                        break;
                    case '|':
                        if (i + 1 < chars.length && chars[i + 1] == '|') {
                            addToken(TokenType.OU_LOGICO, "||");
                            i++;
                        } else {
                            addToken(TokenType.BARRA_VERTICAL, "|");
                        }
                        break;
                    case ';':
                        addToken(TokenType.PONTO_E_VIRGULA, ";");
                        break;
                    case ',':
                        addToken(TokenType.VIRGULA, ",");
                        break;
                    case ':':
                        addToken(TokenType.DOIS_PONTOS, ":");
                        break;
                    case '.':
                        addToken(TokenType.PONTO, ".");
                        break;
                    case '~':
                        addToken(TokenType.TIL, "~");
                        break;
                    case '#':
                        addToken(TokenType.CARDINAL, "#");
                        break;
                    case '(':
                        addToken(TokenType.ABRE_PARENTESES, "(");
                        break;
                    case ')':
                        addToken(TokenType.FECHA_PARENTESES, ")");
                        break;
                    case '{':
                        addToken(TokenType.ABRE_CHAVES, "{");
                        break;
                    case '}':
                        addToken(TokenType.FECHA_CHAVES, "}");
                        break;
                    case '[':
                        addToken(TokenType.ABRE_COLCHETES, "[");
                        break;
                    case ']':
                        addToken(TokenType.FECHA_COLCHETES, "]");
                        break;
                    default:
                        addToken(TokenType.OPERADOR_INVALIDO, String.valueOf(c));
                        break;
                }
            }
        }
    }

    private void addToken(TokenType type, String value) {
        tokens.add(new Token(type, value,lineNumber));
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
