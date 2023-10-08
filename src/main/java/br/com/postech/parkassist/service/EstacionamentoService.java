package br.com.postech.parkassist.service;

import br.com.postech.parkassist.utils.Pagamento;
import br.com.postech.parkassist.utils.ValoresConfig;
import br.com.postech.parkassist.controller.request.TempoFixoRequest;
import br.com.postech.parkassist.controller.request.TempoVariavelRequest;
import br.com.postech.parkassist.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class EstacionamentoService {

    public void iniciarTempoFixo(TempoFixoRequest tempoFixoRequest) {
        // TODO: FAZER O PAGAMENTO

        ObjectId id = new ObjectId(tempoFixoRequest.idCondutor());
        Estacionamento estacionamento = tempoFixoRequest.toEntity();


        Condutor condutor = Condutor.findById(id);
        TipoPagamento tipo = condutor.getTipoPagamento();
        Double valor = ValoresConfig.VALOR_POR_HORA_FIXO * tempoFixoRequest.tempo();
        UUID reciboPagamento = Pagamento.realizarPagamento(tipo, valor);
        estacionamento.setRecibo(new Recibo(reciboPagamento, LocalDateTime.now(), tipo, valor));
        estacionamento.persist();
    }



    public void update(String id, Estacionamento estacionamento) {
        Optional<Estacionamento> estacionamentoValidacao = Estacionamento.findByIdOptional(id);
        if (estacionamentoValidacao.isEmpty()) {
            throw new RuntimeException("Estacionamento não encontrado");
        }

        estacionamento.setId(estacionamentoValidacao.get().id);
        estacionamento.update();
    }


    public void iniciarTempoVariavel(TempoVariavelRequest request) {
        // Valida método de pagamento

        Condutor condutor = Condutor.findById(new ObjectId(request.idCondutor()));
        if (condutor.getTipoPagamento() != TipoPagamento.CREDITO) {
            throw new RuntimeException("Erro Pagamento: Para tempo variável, forma de pagamento deve ser cartão de crédito");
        }

        // Registra inicio do estacionamento
        Estacionamento estacionamento = request.toEntity();
        estacionamento.persist();
    }

    public void finalizaEstacionamento(String id) {
        Estacionamento estacionamento = Estacionamento.findById(new ObjectId(id));
        estacionamento.setTempoDeFim(LocalDateTime.now());

        Condutor condutor = Condutor.findById(estacionamento.getIdCondutor());

        // calcula tempo estacionado, +1 pois renova sempre que uma hora completa passar
        long tempoDecorridoHoras = estacionamento.getTempoDeInicio().until(estacionamento.getTempoDeFim(), ChronoUnit.HOURS) + 1;
        if (tempoDecorridoHoras < 1) {
            throw new RuntimeException("Erro crítico. Não será possível processar o pagamento.");
        }

        // Realiza Cobrança
        TipoPagamento tipo = condutor.getTipoPagamento();
        Double valor = ValoresConfig.VALOR_POR_HORA_VARIAVEL * tempoDecorridoHoras;
        UUID reciboPagamento = Pagamento.realizarPagamento(tipo, valor);

        Recibo recibo = new Recibo(reciboPagamento, LocalDateTime.now(), tipo, valor);

        estacionamento.setRecibo(recibo);
        estacionamento.update();
    }

}
