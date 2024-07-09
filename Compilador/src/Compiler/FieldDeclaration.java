package Compiler;

import java.util.List;

public class FieldDeclaration extends MembroClasse {
    private List<String> modifiers;
    private String type;
    private List<VariableDeclarator> variableDeclarators;

    public FieldDeclaration(List<String> modifiers, String type, List<VariableDeclarator> variableDeclarators) {
        this.modifiers = modifiers;
        this.type = type;
        this.variableDeclarators = variableDeclarators;
    }

    // Getters e outros métodos

    public List<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<VariableDeclarator> getVariableDeclarators() {
        return variableDeclarators;
    }

    public void setVariableDeclarators(List<VariableDeclarator> variableDeclarators) {
        this.variableDeclarators = variableDeclarators;
    }

    String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
