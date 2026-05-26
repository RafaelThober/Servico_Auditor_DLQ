package dlq.servico_auditor_dlq.core.domain.bo;

public class OrderItemBO {
  
  private int sku;
  private int amount;

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}