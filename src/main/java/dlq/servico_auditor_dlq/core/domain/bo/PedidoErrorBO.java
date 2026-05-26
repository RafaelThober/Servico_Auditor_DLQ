package dlq.servico_auditor_dlq.core.domain.bo;

import java.time.Instant;
import java.util.List;


public class PedidoErrorBO {

  private String zipCode;
  private int customerId;
  private List<OrderItemBO> orderItems;
  private String origin;
  private Instant occurredAt;

  public int calculoQuantidadeTotalProdutos() {
        return orderItems.stream()
                .mapToInt(OrderItemBO::getAmount)
                .sum();
    }

    public String definirSeveridade() {

        int total = calculoQuantidadeTotalProdutos();

        if (total > 100) {
            return "HIGH";
        }

        if (total >= 50) {
            return "MEDIUM";
        }

        return "LOW";
    }
  
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public List<OrderItemBO> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItemBO> orderItems) {
    this.orderItems = orderItems;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public Instant getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(Instant occurredAt) {
    this.occurredAt = occurredAt;
  }

}
