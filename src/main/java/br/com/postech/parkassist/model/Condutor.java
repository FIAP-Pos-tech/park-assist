package br.com.postech.parkassist.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import java.util.List;


@MongoEntity(collection = "condutor")
public class Condutor extends PanacheMongoEntity {

	@NotBlank(message = "O nome deve do condutor deve ser informado")
    private String nome;
	@NotBlank(message = "A CNH deve ser informada")
    private String CNH;
	@NotBlank(message = "A senha deve ser informada")
    private String senha;
	@NotBlank(message = "O endereço deve ser informado")
    private String endereco;
	@NotNull(message = "O tipo de pagamento deve ser informado")
    private TipoPagamento tipoPagamento;
    private List<Veiculo> veiculos;


    public Condutor(String nome, String CNH, String senha, String endereco, TipoPagamento tipoPagamento, List<Veiculo> veiculos) {
        this.nome = nome;
        this.CNH = CNH;
        this.senha = senha;
        this.endereco = endereco;
        this.tipoPagamento = tipoPagamento;
        this.veiculos = veiculos;
    }

    public Condutor() {}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNH() {
        return CNH;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

	
}
