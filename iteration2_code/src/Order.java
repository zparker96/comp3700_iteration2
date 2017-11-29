import java.sql.Date;
import java.util.List;
import java.util.ArrayList;


public class Order {
	public static final double TAX_RATE = 0.1;
    private int orderID;
    private String customerName;
    private double subTotalCost;
    private double totalCost;
    private double totalTax;
    private String date;
	    
    private List<OrderLine> lines;

	
    public Order() {
        lines = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void addLine(OrderLine line) {
        lines.add(line);
    }

    public void removeLine(OrderLine line) {
        lines.remove(line);
    }

    public List<OrderLine> getLines() {
        return lines;
    }
    
    public double getSubTotalCost() {
		return subTotalCost;
	}

	public void setSubTotalCost(double subTotalCost) {
		this.subTotalCost = subTotalCost;
	}


}
