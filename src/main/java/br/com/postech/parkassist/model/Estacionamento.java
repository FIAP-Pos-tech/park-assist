package br.com.postech.parkassist.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.UUID;


public class Estacionamento extends PanacheMongoEntity {
    private UUID UUID;
    private ObjectId idCondutor;
    private TipoCobranca tipoCobranca;
    private Integer tempoFixo;
    private LocalDateTime tempoDeInicio;
    private LocalDateTime tempoDeFim;
    private Recibo recibo;


    public Estacionamento(UUID UUID, ObjectId idCondutor, TipoCobranca tipoCobranca, Integer tempoFixo, LocalDateTime tempoDeInicio, LocalDateTime tempoDeFim, Recibo recibo) {
        this.UUID = UUID;
        this.idCondutor = idCondutor;
        this.tipoCobranca = tipoCobranca;
        this.tempoFixo = tempoFixo;
        this.tempoDeInicio = tempoDeInicio;
        this.tempoDeFim = tempoDeFim;
        this.recibo = recibo;
    }

    public Estacionamento() {}

    public void setId(ObjectId id) {
        this.id = id;
    }

    public java.util.UUID getUUID() {
        return UUID;
    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public ObjectId getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(ObjectId idCondutor) {
        this.idCondutor = idCondutor;
    }

    public TipoCobranca getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(TipoCobranca tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public Integer getTempoFixo() {
        return tempoFixo;
    }

    public void setTempoFixo(Integer tempoFixo) {
        this.tempoFixo = tempoFixo;
    }

    public LocalDateTime getTempoDeInicio() {
        return tempoDeInicio;
    }

    public void setTempoDeInicio(LocalDateTime tempoDeInicio) {
        this.tempoDeInicio = tempoDeInicio;
    }

    public LocalDateTime getTempoDeFim() {
        return tempoDeFim;
    }

    public void setTempoDeFim(LocalDateTime tempoDeFim) {
        this.tempoDeFim = tempoDeFim;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }
}
