package br.com.postech.parkassist.controller.request;

import br.com.postech.parkassist.model.Veiculo;

public record VeiculoRequest (String placa, String modelo, String cor, String marca, String ano){
    public Veiculo toEntity() {
        return new Veiculo(placa, modelo, cor, marca, ano);
    }
}
