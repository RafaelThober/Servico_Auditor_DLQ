package dlq.servico_auditor_dlq.application.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import dlq.servico_auditor_dlq.application.ports.in.PedidoErrorServicePort;
import dlq.servico_auditor_dlq.application.ports.out.PedidoErrorRepositoryPort;
import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;

@Service
public class PedidoErrorService implements PedidoErrorServicePort {

  private final PedidoErrorRepositoryPort pedidoErrorRepository;

  public PedidoErrorService(PedidoErrorRepositoryPort pedidoErrorRepository) {
    this.pedidoErrorRepository = pedidoErrorRepository;
  }

  @Override
  public PedidoErrorBO processarPedidoError(
      PedidoErrorBO pedidoErrorBO) throws JsonProcessingException {

    pedidoErrorRepository.salvar(pedidoErrorBO);

    return pedidoErrorBO;
  }
}