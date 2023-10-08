package br.com.postech.parkassist.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Recibo {
    private UUID reciboPagamento;    // n° recibo
    private LocalDateTime emissao;
    private TipoPagamento tipoPagamento;
    private Double valor;

    public Recibo(UUID reciboPagamento, LocalDateTime emissao, TipoPagamento tipoPagamento, Double valor) {
        this.reciboPagamento = reciboPagamento;
        this.emissao = emissao;
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
    }

    public Recibo() {}

    public UUID getReciboPagamento() {
        return reciboPagamento;
    }

    public void setReciboPagamento(UUID reciboPagamento) {
        this.reciboPagamento = reciboPagamento;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
