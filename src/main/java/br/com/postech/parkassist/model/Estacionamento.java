package br.com.postech.parkassist.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

public class Estacionamento extends PanacheMongoEntity {
   
	@NotBlank(message = "O ID do condutor deve ser informado")
    private ObjectId idCondutor;
	@NotBlank(message = "Aplaca deve ser informado")
    private String placa;
	@NotBlank(message = "O tempo fixo deve ser informado")
    private Integer tempoFixo;
    private TipoCobranca tipoCobranca;
    private LocalDateTime tempoDeInicio;
    private LocalDateTime tempoDeFim;
    private Recibo recibo;


    public Estacionamento(ObjectId idCondutor, String placa, TipoCobranca tipoCobranca, Integer tempoFixo, LocalDateTime tempoDeInicio, LocalDateTime tempoDeFim, Recibo recibo) {
        this.idCondutor = idCondutor;
        this.placa = placa;
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}


}
