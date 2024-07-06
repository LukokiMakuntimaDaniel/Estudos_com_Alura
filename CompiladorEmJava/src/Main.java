import Analisadores.ASTNode;
import Analisadores.Lexer;
import Analisadores.Parser;
import Analisadores.SemanticAnalyzer;
import Analisadores.Token;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = "codigo.txt";

        try {
            // Ler o código fonte do arquivo
            String codigoFonte = Lexer.lerCodigoFonte(caminhoArquivo);

            // Análise Léxica
            Lexer lexer = new Lexer(codigoFonte);
            List<Token> tokens = lexer.gerarTokens();

            // Exibir os tokens gerados
            for (Token token : tokens) {
                System.out.println(token);
            }

            // Análise Sintática
            Parser parser = new Parser(tokens);
            ASTNode ast = parser.parse();
            System.out.println(ast);

            // Análise Semântica
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(tokens);
            semanticAnalyzer.analisar();
            System.out.println("Análise semântica concluída sem erros.");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro durante a análise: " + e.getMessage());
        }
    }
}
