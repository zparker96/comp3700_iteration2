
public class OrderLine {

    private int productID;
    private int orderID;
    private int quantity; // changed here
    private double cost;
    private double tax;
    
    public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) { // changed here
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
}
