package Compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompiladorJava20200303 {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.txt";  // O caminho do arquivo de entrada
        String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));

        AnalisadorLexico lexer = new AnalisadorLexico(inputFilePath);
        lexer.tokenize();

        System.out.println("\n Analise Lexica - TOKENS \n");
        System.out.println("\n              TOKEN                              VALOR \n");
        for (Token token : lexer.getTokens()) {
            System.out.println(token);
        }
        List<Token> tokens = lexer.getTokens();

        AnalisadorSintatico parser = new AnalisadorSintatico(tokens);
        Programa program = parser.parseProgram();
        System.out.println("\n Análise Sinatica concluída sem erros.");

        AnalisadorSemantico Semantico = new AnalisadorSemantico(program);
        Semantico.analyze();

        System.out.println("\n Análise semântica concluída sem erros.");

    }
}
