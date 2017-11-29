import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;



public class CheckoutController implements ActionListener {
    private CheckoutScreen view;
    private DataAdapter dataAdapter; // to save and load product
    private Order order = null;
    private double totalTax;
    private double totalCost;
    private String receipt = "Receipt: \n";
   

    
    public CheckoutController(CheckoutScreen view, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.view = view;

        view.getBtnAdd().addActionListener(this);
        view.getBtnPay().addActionListener(this);
        
        order = new Order();
        order.setOrderID(dataAdapter.nextOrderID());
    }

    private DecimalFormat numberFormat = new DecimalFormat("0.00");
    private DecimalFormat quantityFormat = new DecimalFormat("#");

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAdd())
            addProduct();
        else
        if (e.getSource() == view.getBtnPay())
            saveOrder();
    }
    
    private void saveOrder() {
    	
    	String custName = "";
    	java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
    	String id = JOptionPane.showInputDialog("Enter Payment Type:\n" + "(1 = Card | 2 = Cash/Check)");
    	
    	int idNum = Integer.parseInt(id);
    	
    	if (idNum == 1) {
    		custName = JOptionPane.showInputDialog("Enter customer name:");
    		if (custName.length() > 30) {
    			JOptionPane.showMessageDialog(null, "Customer name cannot exceed 30 characters!");
    			return;
    		}
    		String custCard = JOptionPane.showInputDialog("Enter card number:");
    		if (custCard.length() != 16) {
    			JOptionPane.showMessageDialog(null, "Card number must be exactly 16 characters!");
    			return;
    		}
    		
    	}
    	
    	else if (idNum == 2) {
    		custName = JOptionPane.showInputDialog("Enter customer name:");
    		if (custName.length() > 30) {
    			JOptionPane.showMessageDialog(null, "Customer name cannot exceed 30 characters!");
    			return;
    		}
    		
    		JOptionPane.showMessageDialog(null, "Finish payment by hand and click OK when transaction is complete.");
    	} 
    	
    	else { 
    		JOptionPane.showMessageDialog(null, "This payment type is not valid!"); 
    	}

    	String dateString = date.toString().trim();
//    	System.out.println(dateString);


    	order.setCustomerName(custName);
    	order.setDate(dateString);
    	order.setTotalCost(totalCost);
    	order.setTotalTax(totalTax);    	
 		
		dataAdapter.saveOrder(order);
		
		receipt = receipt + "\n" + "Name: " + order.getCustomerName() + "\n" + "Date: " +
                order.getDate() + "\n" + "OrderID: " + "000" + order.getOrderID() + "\n\n"
			    + "Subtotal: $" + order.getSubTotalCost() + "\n" + "Tax: $" +
                order.getTotalTax() + "\n" + "Total: $" + order.getTotalCost();
		
		JOptionPane.showMessageDialog(null, receipt);
        this.view.invalidate();
    }

    private void addProduct() {
        String id = JOptionPane.showInputDialog("Enter Product ID: ");
        Product product = dataAdapter.loadProduct(Integer.parseInt(id));
        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        }

        int quantity = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter quantity: "));

        if (quantity < 0 || quantity > product.getQuantity()) {
            JOptionPane.showMessageDialog(null, "This quantity is not valid!");
            return;
        }

        OrderLine line = new OrderLine();
        line.setOrderID(this.order.getOrderID());
        line.setProductID(product.getProductID());
        line.setQuantity(quantity);
        line.setCost(quantity * product.getPrice());
        
        
        product.setQuantity(product.getQuantity() - quantity); // update new quantity!!
        dataAdapter.saveProduct(product); // and store this product back right away!!!

        order.getLines().add(line);
        order.setSubTotalCost(order.getSubTotalCost() + line.getCost());
        order.setTotalTax(order.getSubTotalCost() * Order.TAX_RATE);
        order.setTotalCost(order.getSubTotalCost() + order.getTotalTax());

        Object[] row = new Object[5];
        row[0] = line.getProductID();
        row[1] = product.getName();
        row[2] = "$" + numberFormat.format(product.getPrice());
        row[3] = quantityFormat.format(line.getQuantity());
        row[4] = "$" + numberFormat.format(line.getCost());

        
        receipt = receipt + "Product ID: " + line.getProductID() + " || Name: " + product.getName() + " || Price: " + "$" + numberFormat.format(product.getPrice()) +
        		" || Quantity: " + quantityFormat.format(line.getQuantity()) + " || Cost: " + "$" + numberFormat.format(line.getCost()) + "\n";
        
        this.view.addRow(row);
        this.view.getLabSubTotal().setText("Subtotal: $" + numberFormat.format(order.getSubTotalCost()));
        this.view.getLabTax().setText("Tax: $" + numberFormat.format(order.getTotalTax()));
        this.view.getLabTotal().setText("Total: $" + numberFormat.format(order.getTotalCost()));
        
        totalTax = order.getTotalTax();
        totalCost = order.getTotalCost();
        
        
        this.view.invalidate();
    }
    


}