package br.com.postech.parkassist.service;

import br.com.postech.parkassist.model.Estacionamento;
import br.com.postech.parkassist.model.TipoCobranca;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@ApplicationScoped
public class EstacionamentoService {


//    public void salvar(EstacionamentoRequest estacionamentoReq) {
//        if (estacionamentoReq.tipoCobranca() == TipoCobranca.TEMPO_FIXO && estacionamentoReq.tempoDefinido() == null) {
//            throw new RuntimeException("Erro Cobrança tempo fixo");
//        }
//
//        if (estacionamentoReq.tipoCobranca() == TipoCobranca.TEMPO_VARIAVEL && estacionamentoReq.tempoDeInicio() != null) {
//            throw  new RuntimeException("Erro cobrança tempo variável");
//        }
//
//
//    }

    public void iniciarTempoFixo(ObjectId idCondutor, Integer tempo) {
        // TODO: opcional: passar a forma de pagamento
        // TODO: cadastrar recibo
        Estacionamento estacionamento = new Estacionamento(null, idCondutor, TipoCobranca.TEMPO_FIXO, tempo, LocalDateTime.now(), null, null);
        estacionamento.persist();
    }

    public void iniciarTempoVariavel(ObjectId idCondutor) {
        // TODO: validar se método de Pagamento é Cartão de Crédito
        Estacionamento estacionamento = new Estacionamento(null, idCondutor, TipoCobranca.TEMPO_VARIAVEL, null, LocalDateTime.now(), null, null);
        estacionamento.persist();
    }

    public void finalizaEstacionamento(String id) {
        // TODO: emitir recibo e gerar cobrança
        Estacionamento estacionamento = Estacionamento.findById(new ObjectId(id));
        estacionamento.setTempoDeFim(LocalDateTime.now());
        estacionamento.update();
    }

}
