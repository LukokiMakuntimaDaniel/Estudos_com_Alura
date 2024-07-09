package Compiler;


import java.util.List;

public class Programa {
    private PackageDeclaration packageDeclaration;
    private List<ImportDeclaration> importDeclarations;
    private List<TypeDeclaration> typeDeclarations;

    public Programa(PackageDeclaration packageDeclaration, List<ImportDeclaration> importDeclarations, List<TypeDeclaration> typeDeclarations) {
        this.packageDeclaration = packageDeclaration;
        this.importDeclarations = importDeclarations;
        this.typeDeclarations = typeDeclarations;
    }

    // Getters e outros métodos

    public PackageDeclaration getPackageDeclaration() {
        return packageDeclaration;
    }

    public void setPackageDeclaration(PackageDeclaration packageDeclaration) {
        this.packageDeclaration = packageDeclaration;
    }

    public List<ImportDeclaration> getImportDeclarations() {
        return importDeclarations;
    }

    public void setImportDeclarations(List<ImportDeclaration> importDeclarations) {
        this.importDeclarations = importDeclarations;
    }

    public List<TypeDeclaration> getTypeDeclarations() {
        return typeDeclarations;
    }

    public void setTypeDeclarations(List<TypeDeclaration> typeDeclarations) {
        this.typeDeclarations = typeDeclarations;
    }
    
}

