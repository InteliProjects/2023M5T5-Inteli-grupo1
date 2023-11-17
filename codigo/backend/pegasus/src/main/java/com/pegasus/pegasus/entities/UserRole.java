package com.pegasus.pegasus.entities;

// A enumeração UserRole representa os papéis de usuário possíveis em um sistema.
public enum UserRole {

    // Enumeração ADMIN representa o papel de administrador.
    ADMIN("ADMIN"),

    // Enumeração USER representa o papel de usuário comum.
    USER("USER");

    // Atributo role armazena o valor do papel.
    private String role;

    // Construtor de UserRole que associa um valor de papel.
    UserRole(String role) {
        this.role = role;
    }

    // Método getRole retorna o valor do papel.
    public String getRole() {
        return role;
    }
}
