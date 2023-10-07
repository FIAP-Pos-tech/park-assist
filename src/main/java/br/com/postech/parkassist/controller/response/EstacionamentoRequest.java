package br.com.postech.parkassist.controller.response;

import br.com.postech.parkassist.model.TipoCobranca;

import java.time.LocalDateTime;

public record EstacionamentoRequest(TipoCobranca tipoCobranca, Integer tempoDefinido,LocalDateTime tempoDeInicio, LocalDateTime tempoDeFim) {

}
