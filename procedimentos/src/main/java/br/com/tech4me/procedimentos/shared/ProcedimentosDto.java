package br.com.tech4me.procedimentos.shared;

import br.com.tech4me.procedimentos.model.Paciente;

public class ProcedimentosDto {
    
    private String id;
    private String idPaciente;
    private Paciente dadosPaciente;
    private String receituario;
    private String encaminhamento;
    private String atestado;

    
    public String getAtestado() {
        return atestado;
    }
    public void setAtestado(String atestado) {
        this.atestado = atestado;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public Paciente getDadosPaciente() {
        return dadosPaciente;
    }
    public void setDadosPaciente(Paciente dadosPaciente) {
        this.dadosPaciente = dadosPaciente;
    }

    public String getReceituario() {
        return receituario;
    }
    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }
   
    public String getEncaminhamento() {
        return encaminhamento;
    }
    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

}
