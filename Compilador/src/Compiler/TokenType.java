package Compiler;

public enum TokenType {
    // Identificadores (nomes de variáveis, funções, etc.)
    IDENTIFICADOR, // Identificador

    // Comentários (comentários de linha e de bloco)
    COMENTARIO, // Comentário
    COMENTARIO_PARAGRAFO, // Comentário de parágrafo

    // Delimitadores (parênteses, chaves, colchetes)
    ABRE_PARENTESES, // Abre parênteses
    FECHA_PARENTESES, // Fecha parênteses
    ABRE_CHAVES, // Abre chaves
    FECHA_CHAVES, // Fecha chaves
    ABRE_COLCHETES, // Abre colchetes
    FECHA_COLCHETES, // Fecha colchetes

    // Fim de arquivo
    FINAL_ARQUIVO, // Final de arquivo

    // Números (inteiros e flutuantes)
    NUMERO_INTEIRO, // Número inteiro
    NUMERO_FLUTUANTE, // Número flutuante

    // Final de instrução (ponto e vírgula)
    FINAL_DE_INSTRUCAO, // Final de instrução

    // Operadores aritméticos
    MAIS, // Mais (+)
    MAIS_MAIS, // Incremento (++)
    MAIS_IGUAL, // Mais igual (+=)
    MENOS, // Menos (-)
    MENOS_MENOS, // Decremento (--)
    MENOS_IGUAL, // Menos igual (-=)
    MULTIPLICACAO, // Multiplicação (*)
    MULTIPLICACAO_IGUAL, // Multiplicação igual (*=)
    DIVISAO, // Divisão (/)
    DIVISAO_IGUAL, // Divisão igual (/=)
    PERCENTAGEM, // Percentagem (%)
    PERCENTAGEM_IGUAL, // Percentagem igual (%=)
    POTENCIA, // Potência (^)
    POTENCIA_IGUAL, // Potência igual (^=)

    // Operadores de comparação
    IGUAL, // Igual (=)
    IGUAL_IGUAL, // Igual igual (==)
    NAO_IGUAL, // Não igual (!=)
    MAIOR, // Maior que (>)
    MAIOR_IGUAL, // Maior ou igual (>=)
    MENOR, // Menor que (<)
    MENOR_IGUAL, // Menor ou igual (<=)
    SHIFT_ESQUERDA, // Shift à esquerda (<<)
    SHIFT_ESQUERDA_IGUAL, // Shift à esquerda igual (<<=)
    SHIFT_DIREITA, // Shift à direita (>>)
    SHIFT_DIREITA_IGUAL, // Shift à direita igual (>>=)

    // Operadores lógicos
    E_LOGICO, // E lógico (&&)
    OU_LOGICO, // Ou lógico (||)
    NEGACAO_LOGICA, // Negação lógica (!)
    E, // E (&)
    E_IGUAL, // E igual (&=)

    // Literais
    CHAR, // Caractere
    STRING, // String

    // Outros operadores e símbolos
    VIRGULA, // Vírgula (,)
    DOIS_PONTOS, // Dois pontos (:)
    PONTO, // Ponto (.)
    TIL, // Til (~)
    BARRA_VERTICAL, // Barra vertical (|)
    CARDINAL, // Cardinal (#)
    PONTO_E_VIRGULA, // Ponto e vírgula (;)

    // Operadores compostos
    BARRA_IGUAL, // Barra igual (/=)
    MENOR_DOIS_PONTOS, // Menor dois pontos (<:)
    MENOR_PERCENTAGEM, // Menor percentagem (<%)
    PERCENTAGEM_MAIOR, // Percentagem maior (%>)
    PERCENTAGEM_DOIS_PONTOS, // Percentagem dois pontos (%:)
    DUPLO_PERCENTAGEM_DOIS_PONTOS, // Dupla percentagem dois pontos (%%)

    // Palavras reservadas
    PALAVRA_RESERVADA, // Palavra reservada

    // Diretivas
    DIRECTIVA, // Diretiva

    // Tipos e variáveis
    VARIAVEL, // Variável
    VOID, // Void

    // Operador inválido
    OPERADOR_INVALIDO // Operador inválido
}

