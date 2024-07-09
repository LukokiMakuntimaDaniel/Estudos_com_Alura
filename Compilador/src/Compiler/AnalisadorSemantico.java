/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compiler;

import java.util.HashSet;
import java.util.List;

public class AnalisadorSemantico {

    private Programa program;

    public AnalisadorSemantico(Programa program) {
        this.program = program;
    }

    public void analyze() {
        checkPackageDeclaration();
        checkImportDeclarations();
        checkTypeDeclarations();
    }

    private void checkPackageDeclaration() {
        // Verifica se há uma declaração de pacote
        if (program.getPackageDeclaration() != null) {
            String packageName = program.getPackageDeclaration().getPackageName();

            // Verifica se o nome do pacote segue uma convenção específica, por exemplo, começando com letras minúsculas
            if (!Character.isLowerCase(packageName.charAt(0))) {
                throw new RuntimeException("O nome do pacote deve começar com letra minúscula.");
            }

            // Outras verificações semânticas podem ser adicionadas conforme necessário
        }
    }

    private void checkImportDeclarations() {
        // Verificações semânticas para declarações de importação
        for (ImportDeclaration importDeclaration : program.getImportDeclarations()) {
            String importedType = importDeclaration.getTypeName();

            // Verifica se o tipo importado está em conformidade com as regras semânticas, por exemplo, se é uma classe existente
            if (!isValidImport(importedType)) {
                throw new RuntimeException("Tipo importado inválido: " + importedType);
            }

            // Outras verificações semânticas podem ser adicionadas conforme necessário
        }
    }

    private void checkTypeDeclarations() {
        for (TypeDeclaration typeDeclaration : program.getTypeDeclarations()) {
            if (typeDeclaration instanceof ClassDeclaration) {
                checkClassDeclaration((ClassDeclaration) typeDeclaration);
            }
        }
    }

    private static final HashSet<String> existingClasses = new HashSet<>();

    static {
        existingClasses.add("java.lang.String");
        existingClasses.add("java.util.ArrayList");
        existingClasses.add("meuPacote.MinhaClasse");
        // Adicione outras classes conforme necessário
    }

    private boolean isValidImport(String importedType) {
        // Verifica se a classe importada está na lista de classes existentes
        return existingClasses.contains(importedType);
    }

    private void checkClassDeclaration(ClassDeclaration classDeclaration) {
        // Verificação semântica para declarações de classe
        for (MembroClasse classMember : classDeclaration.getClassMembers()) {
            if (classMember instanceof FieldDeclaration) {
                checkFieldDeclaration((FieldDeclaration) classMember);
            }
            // Adicione outras verificações de membros de classe conforme necessário
        }
    }

    private void checkFieldDeclaration(FieldDeclaration fieldDeclaration) {
        // Verifica se o tipo do campo é válido
        String fieldType = fieldDeclaration.getType();
        if (!isValidType(fieldType)) {
            throw new RuntimeException("Tipo de campo inválido: " + fieldType);
        }

        // Verifica se o nome do campo segue uma convenção específica, por exemplo, começando com letra minúscula
        String fieldName = fieldDeclaration.getName();
        if (!Character.isLowerCase(fieldName.charAt(0))) {
            throw new RuntimeException("O nome do campo deve começar com letra minúscula.");
        }

        // Outras verificações semânticas podem ser adicionadas conforme necessário
    }

    private boolean isValidType(String type) {
        // Verifica se o tipo é um tipo primitivo válido
        if (isPrimitiveType(type)) {
            return true;
        }

        // Se não for um tipo primitivo nem uma classe existente, consideramos inválido
        return false;
    }

// Função auxiliar para verificar se o tipo é um tipo primitivo válido
    private boolean isPrimitiveType(String type) {
        // Lista de tipos primitivos válidos em Java
        String[] primitiveTypes = {"byte", "short", "int", "long", "float", "double", "boolean", "char"};

        // Verifica se o tipo está na lista de tipos primitivos
        for (String primitive : primitiveTypes) {
            if (type.equals(primitive)) {
                return true;
            }
        }

        // Se não estiver na lista, não é um tipo primitivo válido
        return false;
    }
    // Adicione mais métodos de verificação semântica conforme necessário
}
