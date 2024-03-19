package br.com.fiap.hackathon.ponto.core.domain.entities;

public class Ponto {
    private String id;
    private String matricula;
    private String hora_data_registro;
    private String tipo;

    public Ponto(String id, String matricula, String hora_data_registro, String tipo) {
        this.id = id;
        this.matricula = matricula;
        this.hora_data_registro = hora_data_registro;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getHora_data_registro() {
        return hora_data_registro;
    }

    public void setHora_data_registro(String hora_data_registro) {
        this.hora_data_registro = hora_data_registro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
