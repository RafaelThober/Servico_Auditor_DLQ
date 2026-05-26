package dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import dlq.servico_auditor_dlq.application.ports.out.PedidoErrorRepositoryPort;
import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;
import dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.entity.PedidoErrorEntity;
import dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.jpa.PedidoErrorJpaRepository;
import dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.mapper.PedidoErrorMapper;


@Repository
public class PedidoErrorRepository implements PedidoErrorRepositoryPort{

  private final PedidoErrorJpaRepository jpaRepository;

  public PedidoErrorRepository(PedidoErrorJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }


  @Override
  public PedidoErrorBO salvar(PedidoErrorBO pedidoErrorBO)throws JsonProcessingException{
    PedidoErrorEntity entity = PedidoErrorMapper.toEntity(pedidoErrorBO);

    if (entity.getErrorId() == null) {
      entity.setErrorId(UUID.randomUUID());
    }

    PedidoErrorEntity savedEntity = jpaRepository.save(entity);
    return PedidoErrorMapper.toBO(savedEntity);
  }

}
