package br.com.postech.parkassist.controller.request;

import br.com.postech.parkassist.model.Estacionamento;
import br.com.postech.parkassist.model.TipoCobranca;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public record TempoFixoRequest(String idCondutor, Integer tempoFixo, String placa) {

    public Estacionamento toEntity(){
        return new Estacionamento(new ObjectId(idCondutor), placa,TipoCobranca.TEMPO_FIXO,
        		tempoFixo, LocalDateTime.now(), null, null);
    }

}