import Analisadores.Lexer;
import Analisadores.Token;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String caminhoArquivo= "codigo.txt";
        String codigoFonte = null;
        try {
            codigoFonte = Lexer.lerCodigoFonte(caminhoArquivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Lexer lexer = new Lexer(codigoFonte);
        List<Token> tokens = lexer.gerarTokens();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}