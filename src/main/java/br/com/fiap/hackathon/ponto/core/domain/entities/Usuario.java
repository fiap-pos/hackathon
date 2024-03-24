package br.com.fiap.hackathon.ponto.core.domain.entities;

public class Usuario {

    private String matricula;
    private String senha;
    private String email;

    public Usuario(String matricula, String senha, String email) {
        this.matricula = matricula;
        this.senha = senha;
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
