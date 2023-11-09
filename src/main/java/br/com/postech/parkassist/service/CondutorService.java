package br.com.postech.parkassist.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.types.ObjectId;
import br.com.postech.parkassist.controller.request.CondutorRequest;
import br.com.postech.parkassist.controller.request.VeiculoRequest;
import br.com.postech.parkassist.controller.response.CondutorResponse;
import br.com.postech.parkassist.customexception.CustomException;
import br.com.postech.parkassist.model.Condutor;
import br.com.postech.parkassist.model.Veiculo;
import br.com.postech.parkassist.utils.ValidaObj;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CondutorService {

	@Inject
	Validator validator;
	ValidaObj valObj;

	@ConfigProperty(name = "knowledgefactory.custom.error.msg.notfound")
	String badrequest;

	public Response update(String id,  CondutorRequest dadosAtualizacao) throws CustomException{
		Set<String> mensagens= new HashSet<String>();
		Condutor condutor = buscaPorId(id);

		if (condutor == null) {
			throw new CustomException("Condutor não encontrado");
		}

		condutor.setNome(dadosAtualizacao.nome());
		condutor.setSenha(dadosAtualizacao.senha());
		condutor.setEndereco(dadosAtualizacao.endereco());   
		condutor.setTipoPagamento(dadosAtualizacao.tipoPagamento());

		if(condutor.getNome().length() ==0) {
			mensagens.add("Campo Nome não pode ser vazio ");
		}

		if(condutor.getSenha().length() ==0) {
			mensagens.add("Campo Senha não pode ser vazio ");
		}

		if(condutor.getEndereco().length() ==0) {
			mensagens.add("Campo Endereço não pode ser vazio ");
		}

		if(condutor.getTipoPagamento().equals(null)) {
			mensagens.add("Campo Tipo de Pagamento não pode ser vazio ");
		}

		if(!mensagens.isEmpty()) {
			throw new CustomException(Arrays.toString(mensagens.toArray()));
		}
		condutor.setId(new ObjectId(id));
		condutor.update();
		return Response.ok().build();  

	}

	public void create(Condutor condutor) throws Exception{
		try {
			valObj.validaObjeto(validator,condutor);
		} catch (Exception e) {}
		condutor.persist();
	}

	public Response delete(String id) throws CustomException {
		boolean isDeleted = deletarPorID(id);
		if (isDeleted==false) {
			throw new CustomException("Condutor não encontrado");
		}
		return Response.ok().build();
	}

	public void criarVeiculo(String id, VeiculoRequest veiculoRequest) throws CustomException{
		Set<String> mensagens= new HashSet<String>();
		Condutor condutor = null;
		try {
			condutor = buscaPorId(id);
		} catch (Exception e ) {
			throw new CustomException("ID do Condutor não encontrado");
		}


		if (veiculoRequest.placa().length() == 0 ) {
			mensagens.add("Campo Placa não informada ");
		}
		if (veiculoRequest.modelo().length() == 0 ) {
			mensagens.add("Campo Modelo não informado ");
		}
		if (veiculoRequest.cor().length() == 0 ) {
			mensagens.add("Campo Cor não informado ");
		}
		if (veiculoRequest.marca().length() == 0 ) {
			mensagens.add("Campo Marca não informada ");
		}
		if (veiculoRequest.ano().length() == 0 ) {
			mensagens.add("Campo Ano não informada ");
		}
		if(!mensagens.isEmpty()) {
			throw new CustomException(Arrays.toString(mensagens.toArray()));
		}

		List<Veiculo> veiculos = condutor.getVeiculos();
		if(veiculos == null) {
			veiculos = List.of(veiculoRequest.toEntity());
		} else {
			veiculos.add(veiculoRequest.toEntity());
		}
		condutor.setVeiculos(veiculos);
		condutor.update();
	}

	public CondutorResponse buscaContudorID(String id) throws CustomException {
		return new CondutorResponse(buscaPorId(id));
	}

	public List<CondutorResponse> listaDeCondutores() {
		List<Condutor> lista = Condutor.listAll();
		return lista.stream().map(CondutorResponse::new).toList();
	}

	public Condutor buscaPorId(String id) {
		Condutor condutor = Condutor.findById(new ObjectId(id));
		if (condutor == null) {
			throw new CustomException("Busca por ID não concluída, Condutor não encontrado");
		}
		return condutor;
	}

	public boolean deletarPorID(String id) {
		return Condutor.deleteById(new ObjectId(id));
	}




}
