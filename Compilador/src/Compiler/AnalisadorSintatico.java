package Compiler;

import java.beans.Statement;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorSintatico {

    private List<Token> tokens;
    private int currentTokenIndex;

    public AnalisadorSintatico(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    private Token currentToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex);
        }
        return null;
    }

    private Token nextToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex++);
        }
        return null;
    }

    private boolean match(TokenType expectedType) {
        if (currentToken() != null && currentToken().getTipo() == expectedType) {
            nextToken();
            return true;
        }
        return false;
    }

    private void expect(TokenType expectedType) {
        if (currentToken().getTipo() != expectedType) {
            throw new RuntimeException("Esperado: " + expectedType + " mas encontrado: " + currentToken());
        }
        nextToken();
    }

    public Programa parseProgram() {
        PackageDeclaration packageDeclaration = parsePackageDeclaration();
        List<ImportDeclaration> importDeclarations = new ArrayList<>();
        while (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA && currentToken().getValor().equals("import")) {
            importDeclarations.add(parseImportDeclaration());
        }
        List<TypeDeclaration> typeDeclarations = new ArrayList<>();
        while (currentToken() != null && currentToken().getTipo()!= TokenType.FINAL_ARQUIVO) {
            typeDeclarations.add(parseTypeDeclaration());
        }
        return new Programa(packageDeclaration, importDeclarations, typeDeclarations);
    }

    private PackageDeclaration parsePackageDeclaration() {
        expect(TokenType.PALAVRA_RESERVADA); // Espera-se "package"
        expect(TokenType.IDENTIFICADOR); // Espera-se o nome do pacote
        String packageName = currentToken().getValor(); // Obtém o nome do pacote
        expect(TokenType.PONTO_E_VIRGULA); // Espera-se ";"
        return new PackageDeclaration(packageName); // Retorna a declaração do pacote
    }

//    private ImportDeclaration parseImportDeclaration() {
//        expect(TokenType.PALAVRA_RESERVADA);
//        expect(TokenType.IDENTIFICADOR);
//        String typeName = currentToken().getValor();
//        boolean isWildcard = false;
//        if (match(TokenType.PONTO)) {
//            if (match(TokenType.MAIS)) {
//                isWildcard = true;
//            } else {
//                throw new RuntimeException("Esperado * após . em declaração de import");
//            }
//        }
//        expect(TokenType.PONTO_E_VIRGULA);
//        return new ImportDeclaration(typeName, isWildcard);
//    }
    private ImportDeclaration parseImportDeclaration() {
        expect(TokenType.PALAVRA_RESERVADA); // Espera 'import'

        StringBuilder typeName = new StringBuilder();
        boolean isWildcard = false;

        while (currentToken().getTipo() == TokenType.IDENTIFICADOR || currentToken().getTipo() == TokenType.PONTO) {
            typeName.append(currentToken().getValor());
            nextToken();
        }

        if (currentToken().getTipo() == TokenType.PONTO) {
            typeName.append(currentToken().getValor());
            nextToken();
            if (currentToken().getTipo() == TokenType.MULTIPLICACAO) {
                isWildcard = true;
                nextToken();
            }
        }

        expect(TokenType.PONTO_E_VIRGULA); // Espera ';'

        return new ImportDeclaration(typeName.toString(), isWildcard);
    }

//    private TypeDeclaration parseTypeDeclaration() {
//        if (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA && currentToken().getValor().equals("class")) {
//            return parseClassDeclaration();
//        } else {
//            throw new RuntimeException("Tipo de declaração não suportado: " + currentToken());
//        }
//    }
    private TypeDeclaration parseTypeDeclaration() {
        String keyword = currentToken().getValor();
        if (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA && (keyword.equalsIgnoreCase("class"))) {
            return parseClassDeclaration();
//            if (keyword.equals("class")) {
//                return parseClassDeclaration();
//            }

            // Adicione outras verificações de tipos de declaração aqui, se necessário
        } else if (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA) {
            if (keyword.equals("public") || keyword.equals("private") || keyword.equals("protected") || keyword.equals("static") || keyword.equals("final")) {
                return parseMethodDeclaration();
            }
        }

        throw new RuntimeException("Tipo de declaração não suportado: " + currentToken());
    }

    private MethodDeclaration parseMethodDeclaration() {
        // Parse modifiers (public, private, etc.)
        List<String> modifiers = new ArrayList<>();
        while (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA
                && (currentToken().getValor().equals("public") || currentToken().getValor().equals("private")
                || currentToken().getValor().equals("protected") || currentToken().getValor().equals("static")
                || currentToken().getValor().equals("final"))) {
            modifiers.add(currentToken().getValor());
            nextToken();
        }

        // Parse return type
        String returnType = currentToken().getValor();
        nextToken();

        // Parse method name
        String methodName = currentToken().getValor();
        nextToken();

        // Parse parameters
        expect(TokenType.ABRE_PARENTESES);
        List<Parameter> parameters = parseParameters();  // Supondo que você tenha um método para analisar parâmetros
        expect(TokenType.FECHA_PARENTESES);

        // Parse method body
        MethodBody methodBody = parseMethodBody();

        return new MethodDeclaration(methodName, methodBody);
    }

//    private MethodBody parseMethodBody() {
//        expect(TokenType.ABRE_CHAVES);
//        List<Statement> statements = new ArrayList<>();
//        while (currentToken().getTipo() != TokenType.FECHA_CHAVES) {
//            statements.add(parseStatement());
//        }
//        expect(TokenType.FECHA_CHAVES);
//        return new MethodBody(statements);
//    }
    private String parseType() {
        // This method should parse and return the type of the method
        // For simplicity, let's assume it just parses and returns a string representing the type
        // You should implement this method based on your grammar and parsing logic
        expect(TokenType.IDENTIFICADOR); // For simplicity, assume the type is just an identifier
        String type = currentToken().getValor();
        nextToken(); // Consume the type token
        return type;
    }

    private List<Parameter> parseParameterList() {
        List<Parameter> parameters = new ArrayList<>();
        // Implement logic to parse parameters (if any) here
        // For simplicity, let's assume there are no parameters for now
        return parameters;
    }

    private ClassDeclaration parseClassDeclaration() {
        expect(TokenType.PALAVRA_RESERVADA);
        expect(TokenType.IDENTIFICADOR);
        String className = currentToken().getValor();
        expect(TokenType.ABRE_CHAVES);
        List<MembroClasse> classMembers = new ArrayList<>();
        while (currentToken().getTipo() != TokenType.FECHA_CHAVES) {
            classMembers.add(parseClassMember());
        }
        expect(TokenType.FECHA_CHAVES);
        return new ClassDeclaration(className, classMembers);
    }

    private MembroClasse parseClassMember() {
        List<String> modifiers = new ArrayList<>();
        while (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA && isModifier(currentToken().getValor())) {
            modifiers.add(currentToken().getValor());
            nextToken();
        }
        if (currentToken().getTipo() == TokenType.PALAVRA_RESERVADA && isPrimitiveType(currentToken().getValor())) {
            return parseFieldOrMethodDeclaration(modifiers);
        } else if (currentToken().getTipo() == TokenType.IDENTIFICADOR) {
            return parseFieldOrMethodDeclaration(modifiers);
        } else {
            throw new RuntimeException("Membro de classe não suportado: " + currentToken());
        }
    }

    private boolean isModifier(String value) {
        return value.equals("public") || value.equals("private") || value.equals("protected") || value.equals("static") || value.equals("final");
    }

    private boolean isPrimitiveType(String value) {
        return value.equals("int") || value.equals("double") || value.equals("boolean");
    }

    private FieldDeclaration parseFieldOrMethodDeclaration(List<String> modifiers) {
        String type = currentToken().getValor();
        expect(TokenType.IDENTIFICADOR);
        List<VariableDeclarator> variableDeclarators = parseVariableDeclarators();
        expect(TokenType.PONTO_E_VIRGULA);
        return new FieldDeclaration(modifiers, type, variableDeclarators);
    }

    private List<VariableDeclarator> parseVariableDeclarators() {
        List<VariableDeclarator> variableDeclarators = new ArrayList<>();
        variableDeclarators.add(parseVariableDeclarator());
        while (match(TokenType.VIRGULA)) {
            variableDeclarators.add(parseVariableDeclarator());
        }
        return variableDeclarators;
    }

    private VariableDeclarator parseVariableDeclarator() {
        expect(TokenType.IDENTIFICADOR);
        String identifier = currentToken().getValor();
        String initializer = null;
        if (match(TokenType.IGUAL)) {
            initializer = parseExpression();
        }
        return new VariableDeclarator(identifier, initializer);
    }

    private String parseExpression() {
        // Para simplicidade, apenas retorna a expressão como string. Em um analisador completo, seria retornado um nó de expressão.
        StringBuilder expression = new StringBuilder();
        while (currentToken() != null && currentToken().getTipo() != TokenType.PONTO_E_VIRGULA && currentToken().getTipo() != TokenType.VIRGULA) {
            expression.append(currentToken().getValor()).append(" ");
            nextToken();
        }
        return expression.toString().trim();
    }

    private MethodBody parseMethodBody() {
        expect(TokenType.ABRE_CHAVES); // Espera-se "{"

        List<Statement> statements = new ArrayList<>();

        // Loop para analisar todas as declarações no corpo do método
        while (currentToken().getTipo() != TokenType.FECHA_CHAVES) {
            Statement statement = parseStatement(); // Analisa a declaração atual
            statements.add(statement); // Adiciona a declaração à lista

            // Se houver mais declarações após a atual, espera-se uma vírgula
            if (currentToken().getTipo() == TokenType.VIRGULA) {
                nextToken(); // Consome a vírgula
            }
        }

        expect(TokenType.FECHA_CHAVES); // Espera-se "}"

        return new MethodBody(statements);
    }

    private Statement parseStatement() {
        // Implementação básica para analisar declarações de expressão
        if (currentToken().getTipo() == TokenType.IDENTIFICADOR) {
            // Se o próximo token for um identificador, então é uma declaração de expressão
            return parseExpressionStatement();
        } else {
            // Caso contrário, lançamos uma exceção indicando que o tipo de declaração não é suportado
            throw new RuntimeException("Tipo de declaração não suportado: " + currentToken());
        }
    }

    private Statement parseExpressionStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    private List<Parameter> parseParameters() {
//        List<Parameter> parameters = new ArrayList<>();
//        // Adicione a lógica para analisar os parâmetros
//        // Por exemplo, parseParameter() pode ser usado para analisar um único parâmetro
//        while (currentToken().getTipo() != TokenType.FECHA_PARENTESES) {
//            parameters.add(parseParameter());
//            if (currentToken().getTipo() == TokenType.VIRGULA) {
//                nextToken(); // consumir a vírgula
//            }
//        }
//        return parameters;
//    }
    private List<Parameter> parseParameters() {
        List<Parameter> parameters = new ArrayList<>();
        while (currentToken().getTipo() != TokenType.FECHA_PARENTESES) {
            // Verifica se o tipo do parâmetro é "String[]"
            if (currentToken().getTipo() == TokenType.IDENTIFICADOR && currentToken().getValor().equals("String")) {
                nextToken(); // Consome "String"
                if (currentToken().getTipo() == TokenType.ABRE_COLCHETES) {
                    nextToken(); // Consome "["
                    if (currentToken().getTipo() == TokenType.FECHA_COLCHETES) {
                        nextToken(); // Consome "]"
                        // Adiciona o parâmetro à lista
//                    parameters.add(new Parameter("String[]")); // Cria um novo parâmetro do tipo "String[]"
                    } else {
                        throw new RuntimeException("Esperado FECHA_COLCHETES mas encontrado: " + currentToken());
                    }
                } else {
                    throw new RuntimeException("Esperado ABRE_COLCHETES mas encontrado: " + currentToken());
                }
            } else {
                // Se o tipo do parâmetro não for "String[]", lança uma exceção
                throw new RuntimeException("Tipo de parâmetro não suportado: " + currentToken());
            }

            if (currentToken().getTipo() == TokenType.VIRGULA) {
                nextToken(); // Consome a vírgula
            }
        }
        return parameters;
    }

    private Parameter parseParameter() {
        // Verificar se o token atual é um identificador (tipo do parâmetro)
        if (currentToken().getTipo() != TokenType.IDENTIFICADOR) {
            throw new RuntimeException("Esperado: IDENTIFICADOR mas encontrado: " + currentToken());
        }

        String type = currentToken().getValor();
        nextToken();

        // Verificar se o próximo token é um identificador (nome do parâmetro)
        if (currentToken().getTipo() != TokenType.IDENTIFICADOR) {
            throw new RuntimeException("Esperado: IDENTIFICADOR mas encontrado: " + currentToken());
        }

        String name = currentToken().getValor();
        nextToken();

        return new Parameter(type, name);
    }

    private static class MethodDeclaration extends TypeDeclaration {

        private String accessModifier;
        private String returnType;
        private String methodName;
        private List<Parameter> parameters;
        private MethodBody methodBody;

        public MethodDeclaration(String accessModifier, String returnType, String methodName, List<Parameter> parameters, MethodBody methodBody) {
            this.accessModifier = accessModifier;
            this.returnType = returnType;
            this.methodName = methodName;
            this.parameters = parameters;
            this.methodBody = methodBody;
        }

        private MethodDeclaration(String methodName, MethodBody methodBody) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        // Getters e setters para os atributos, se necessário
        public String getAccessModifier() {
            return accessModifier;
        }

        public void setAccessModifier(String accessModifier) {
            this.accessModifier = accessModifier;
        }

        public String getReturnType() {
            return returnType;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public List<Parameter> getParameters() {
            return parameters;
        }

        public void setParameters(List<Parameter> parameters) {
            this.parameters = parameters;
        }

        public MethodBody getMethodBody() {
            return methodBody;
        }

        public void setMethodBody(MethodBody methodBody) {
            this.methodBody = methodBody;
        }

    }

    public class MethodBody {

        private List<Statement> statements;

        public MethodBody(List<Statement> statements) {
            this.statements = statements;
        }

        // Getter e setter para o campo statements, se necessário
        public List<Statement> getStatements() {
            return statements;
        }

        public void setStatements(List<Statement> statements) {
            this.statements = statements;
        }

//        private MethodBody parseMethodBody() {
//            expect(TokenType.ABRE_CHAVES); // Espera-se "{"
//
//            List<Statement> statements = new ArrayList<>();
//
//            // Loop para analisar todas as declarações no corpo do método
//            while (currentToken().getTipo() != TokenType.FECHA_CHAVES) {
//                Statement statement = parseStatement(); // Analisa a declaração atual
//                statements.add(statement); // Adiciona a declaração à lista
//
//                // Se houver mais declarações após a atual, espera-se uma vírgula
//                if (currentToken().getTipo() == TokenType.VIRGULA) {
//                    nextToken(); // Consome a vírgula
//                }
//            }
//
//            expect(TokenType.FECHA_CHAVES); // Espera-se "}"
//
//            return new MethodBody(statements);
//        }
        private MethodBody parseMethodBody() {
            expect(TokenType.ABRE_CHAVES);
            List<Statement> statements = new ArrayList<>();
            while (currentToken().getTipo() != TokenType.FECHA_CHAVES) {
                statements.add(parseStatement());
            }
            expect(TokenType.FECHA_CHAVES);
            return new MethodBody(statements);
        }

//        MethodBody methodBody = parseMethodBody();
    }

    public class Parameter {

        private String type;
        private String name;

        public Parameter(String type, String name) {
            this.type = type;
            this.name = name;
        }

        // Getters e setters
        public String getTipo() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
