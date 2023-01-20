package br.com.tech4me.procedimentos.shared;

public class ProcedimentosCompletoDto {
    
    private String id;
    private String receituario;
    private String encaminhamento;
    private String idPaciente;
    private String atestado;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getAtestado() {
        return atestado;
    }
    public void setAtestado(String atestado) {
        this.atestado = atestado;
    }
}
