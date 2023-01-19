package br.com.tech4me.procedimentos.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("")
public class Procedimentos {
 
    private String id;
    private String receituário;
    private String encaminhamento;
    private String atestado;
}
