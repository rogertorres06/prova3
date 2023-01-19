package br.com.tech4me.procedimentos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("procedimentos")
public class Procedimentos {
    @Id 
    private String id;
    private String receituário;
    private String encaminhamento;
    private String atestado;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReceituário() {
        return receituário;
    }
    public void setReceituário(String receituário) {
        this.receituário = receituário;
    }
    public String getEncaminhamento() {
        return encaminhamento;
    }
    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }
    public String getAtestado() {
        return atestado;
    }
    public void setAtestado(String atestado) {
        this.atestado = atestado;
    }

    
}
