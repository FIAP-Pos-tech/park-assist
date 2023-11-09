package br.com.postech.parkassist.controller.request;

import java.util.List;

import br.com.postech.parkassist.model.Condutor;
import br.com.postech.parkassist.model.TipoPagamento;
import br.com.postech.parkassist.model.Veiculo;

public record CondutorRequest(String nome, String CNH, String senha, String endereco, TipoPagamento tipoPagamento, List<Veiculo> veiculos) {
	 public Condutor toEntity() {
	        return new Condutor(nome, CNH, senha, endereco, tipoPagamento, veiculos);
	 }
	
}
