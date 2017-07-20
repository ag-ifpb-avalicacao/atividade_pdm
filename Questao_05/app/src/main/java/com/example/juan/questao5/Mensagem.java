package com.example.juan.questao5;

import java.util.UUID;

/**
 * Created by juan on 16/07/17.
 */

public class Mensagem {
    private long id;
    private String remetente, texto;
    private boolean souEu;

    public Mensagem(String remetente, String texto, boolean souEu) {
        this.remetente = remetente;
        this.texto = texto;
        this.souEu = souEu;
        this.id = Math.abs(UUID.randomUUID().hashCode());
    }
    
    public Mensagem(long id, String remetente, String texto, boolean souEu) {
        this.remetente = remetente;
        this.texto = texto;
        this.souEu = souEu;
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isSouEu() {
        return souEu;
    }

    public void setSouEu(boolean souEu) {
        this.souEu = souEu;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", remetente='" + remetente + '\'' +
                ", texto='" + texto + '\'' +
                ", souEu=" + souEu +
                '}';
    }
}
