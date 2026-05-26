package dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.entity.PedidoErrorEntity;

public interface PedidoErrorJpaRepository extends JpaRepository<PedidoErrorEntity, UUID> {
  
}