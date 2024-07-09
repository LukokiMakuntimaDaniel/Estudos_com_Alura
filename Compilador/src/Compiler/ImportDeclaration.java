package Compiler;

public class ImportDeclaration {
    private String typeName;
    private boolean isWildcard;

    public ImportDeclaration(String typeName, boolean isWildcard) {
        this.typeName = typeName;
        this.isWildcard = isWildcard;
    }

    // Getters e outros métodos

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isIsWildcard() {
        return isWildcard;
    }

    public void setIsWildcard(boolean isWildcard) {
        this.isWildcard = isWildcard;
    }
    
}
