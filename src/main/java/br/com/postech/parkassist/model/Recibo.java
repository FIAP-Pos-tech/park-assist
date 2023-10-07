package br.com.postech.parkassist.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Recibo {
    private UUID id;    // n° recibo
    private UUID idCondutor;
    private LocalDateTime emissao;
    private TipoPagamento tipoPagamento;
    private Double valor;

    public Recibo(UUID id, UUID idCondutor, LocalDateTime emissao, TipoPagamento tipoPagamento, Double valor) {
        this.id = id;
        this.idCondutor = idCondutor;
        this.emissao = emissao;
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
    }

    public Recibo() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(UUID idCondutor) {
        this.idCondutor = idCondutor;
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
