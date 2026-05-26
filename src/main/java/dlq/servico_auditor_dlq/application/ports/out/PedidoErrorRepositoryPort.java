package dlq.servico_auditor_dlq.application.ports.out;

import com.fasterxml.jackson.core.JsonProcessingException;

import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;

public interface PedidoErrorRepositoryPort {
  
  PedidoErrorBO salvar(PedidoErrorBO pedidoErrorBO) throws JsonProcessingException;

}