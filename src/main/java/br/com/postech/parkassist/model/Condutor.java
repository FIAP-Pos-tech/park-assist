package br.com.postech.parkassist.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;

@MongoEntity(collection = "condutor")
public class Condutor extends PanacheMongoEntity {

    private UUID uuid;
    private String nome;
    private String CNH;
    private String senha;
    private String endereco;
    private TipoPagamento tipoPagamento;
    private List<Veiculo> veiculos;


    public Condutor(String nome,UUID uuid, String CNH, String senha, String endereco, TipoPagamento tipoPagamento, List<Veiculo> veiculos) {
        this.nome = nome;
        this.uuid = UUID.randomUUID(); // gera um UUID aleatório
        this.CNH = CNH;
        this.senha = senha;
        this.endereco = endereco;
        this.tipoPagamento = tipoPagamento;
        this.veiculos = veiculos;
    }

     public Condutor() {}

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
