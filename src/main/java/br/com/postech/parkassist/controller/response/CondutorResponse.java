package br.com.postech.parkassist.controller.response;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.postech.parkassist.model.Condutor;
import br.com.postech.parkassist.model.TipoPagamento;
import br.com.postech.parkassist.model.Veiculo;

public record CondutorResponse(ObjectId id, String nome, String CNH, String endereco, TipoPagamento tipoPagamento, List<Veiculo> veiculos) {
    public CondutorResponse(Condutor condutor) {
        this(condutor.getId(), condutor.getNome(), condutor.getCNH(), condutor.getEndereco(), condutor.getTipoPagamento(), condutor.getVeiculos());
    }
}
