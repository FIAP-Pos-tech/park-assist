package br.com.postech.parkassist.service;

import br.com.postech.parkassist.utils.Pagamento;
import br.com.postech.parkassist.utils.ValidaObj;
import br.com.postech.parkassist.utils.ValoresConfig;
import br.com.postech.parkassist.controller.request.TempoFixoRequest;
import br.com.postech.parkassist.controller.request.TempoVariavelRequest;
import br.com.postech.parkassist.customexception.CustomException;
import br.com.postech.parkassist.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class EstacionamentoService {

	
	@Inject
	Validator validator;
	ValidaObj valObj;
	
	@ConfigProperty(name = "knowledgefactory.custom.error.msg.notfound")
	String badrequest;


	public void iniciarTempoFixo(TempoFixoRequest tempoFixoRequest) throws CustomException {
		// TODO: FAZER O PAGAMENTO
		Set<String> mensagens= new HashSet<String>();
		if ( tempoFixoRequest.idCondutor().length() == 0 ) {
			mensagens.add("Busca por ID não concluída, Condutor não encontrado ");
		}
		if(tempoFixoRequest.placa().length() ==0) {
			mensagens.add("Campo Placa não pode ser vazio ");
		}
		if(tempoFixoRequest.tempoFixo() ==0) {
			mensagens.add("Campo Tempo Fixo deve ser maior que ZERO ");
		}
		if(!mensagens.isEmpty()) {
			throw new CustomException(Arrays.toString(mensagens.toArray()));
		}

		Estacionamento estacionamento = tempoFixoRequest.toEntity();
		Condutor condutor = buscaPorId(tempoFixoRequest.idCondutor());
		TipoPagamento tipo = condutor.getTipoPagamento();
		Double valor = ValoresConfig.VALOR_POR_HORA_FIXO * tempoFixoRequest.tempoFixo();
		UUID reciboPagamento = Pagamento.realizarPagamento(tipo, valor);

		estacionamento.setRecibo(new Recibo(reciboPagamento, LocalDateTime.now(), tipo, valor));
		estacionamento.persist();
	}

	public void iniciarTempoVariavel(TempoVariavelRequest tempoVariavelRequest) {
		// Valida método de pagamento
		Set<String> mensagens= new HashSet<String>();

		if(tempoVariavelRequest.idCondutor().length() == 0) {
			throw new CustomException("ID do Condutor não informado ");
		}

		Condutor condutor = buscaPorId(tempoVariavelRequest.idCondutor());
		if (condutor.getTipoPagamento() != TipoPagamento.CREDITO) {
			mensagens.add("Erro Pagamento: Para tempo variável, forma de pagamento deve ser cartão de crédito");
		}

		if (tempoVariavelRequest.placa().length() == 0) {
			mensagens.add("Campo Placa não pode ser vazio  ");
		}

		if (!mensagens.isEmpty()) {
			throw new CustomException(Arrays.toString(mensagens.toArray()));
		}

		// Registra inicio do estacionamento
		Estacionamento estacionamento = tempoVariavelRequest.toEntity();
		estacionamento.persist();
	}
	
	public void finalizaEstacionamento(String id) throws CustomException{
		Estacionamento estacionamento = Estacionamento.findById(new ObjectId(id));
		estacionamento.setTempoDeFim(LocalDateTime.now());
		Condutor condutor = Condutor.findById(estacionamento.getIdCondutor());

		// calcula tempo estacionado, +1 pois renova sempre que uma hora completa passar
		long tempoDecorridoHoras = estacionamento.getTempoDeInicio().until(estacionamento.getTempoDeFim(), ChronoUnit.HOURS) + 1;
		if (tempoDecorridoHoras < 1) {
			throw new CustomException("Erro crítico. Não será possível processar o pagamento.");
		}

		// Realiza Cobrança
		TipoPagamento tipo = condutor.getTipoPagamento();
		Double valor = ValoresConfig.VALOR_POR_HORA_VARIAVEL * tempoDecorridoHoras;
		UUID reciboPagamento = Pagamento.realizarPagamento(tipo, valor);

		Recibo recibo = new Recibo(reciboPagamento, LocalDateTime.now(), tipo, valor);

		estacionamento.setRecibo(recibo);
		estacionamento.update();
	}

	public Response delete(String id) throws CustomException {
		boolean isDeleted = deletarPorID(id);
		if (isDeleted == false) {
			throw new CustomException("Estacionamento não encontrado");
		}
		return Response.ok().build();
	}

	public Estacionamento buscaContudorID(String id) throws CustomException {
		Estacionamento estacionamento = buscaEstacionamentoPorId(id);
		if (estacionamento == null) {
			throw new CustomException("Busca por ID não concluída, Estacionamento não encontrado");
		}
		return estacionamento;
	}
	
	public void update(String id, Estacionamento estacionamento)  throws CustomException{
		Optional<Estacionamento> estacionamentoValidacao = Estacionamento.findByIdOptional(id);
		if (estacionamentoValidacao.isEmpty()) {
			throw new CustomException("Estacionamento não encontrado");
		}

		estacionamento.setId(estacionamentoValidacao.get().id);
		estacionamento.update();
	}
	
	public boolean deletarPorID(String id) {
		return Estacionamento.deleteById(new ObjectId(id));
	}
	
	public List<Estacionamento> listaDeEstaconamentos() {
		return Estacionamento.listAll();
	}
	
	public Condutor buscaPorId(String id) {
		return Condutor.findById(new ObjectId(id));
	}
	
	public Estacionamento buscaEstacionamentoPorId(String id) {
		return Estacionamento.findById(new ObjectId(id));
	}
}
