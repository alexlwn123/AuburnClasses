import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class ManagePurchaseUI {
    public JFrame view;
   
    public JButton btnLoad = new JButton("Load Purchase");
    public JButton btnSave = new JButton("Save Purchase");
   
    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);
   
    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");
   
    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");
    
    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");

    PurchaseModel purchase;
    ProductModel product;
    CustomerModel customer;

    public ManagePurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));
   
       JPanel panelButtons = new JPanel(new FlowLayout());
       panelButtons.add(btnLoad);
       panelButtons.add(btnSave);
       view.getContentPane().add(panelButtons);
   
       JPanel line = new JPanel(new FlowLayout());
       line.add(new JLabel("PurchaseID "));
       line.add(txtPurchaseID);
       line.add(labDate);
       view.getContentPane().add(line);
   
       line = new JPanel(new FlowLayout());
       line.add(new JLabel("CustomerID "));
       line.add(txtCustomerID);
       line.add(labCustomerName);
       view.getContentPane().add(line);
   
       line = new JPanel(new FlowLayout());
       line.add(new JLabel("ProductID "));
       line.add(txtProductID);
       line.add(labProductName);
       view.getContentPane().add(line);
   
       line = new JPanel(new FlowLayout());
       line.add(new JLabel("Quantity "));
       line.add(txtQuantity);
       line.add(labPrice);
       view.getContentPane().add(line);
   
       line = new JPanel(new FlowLayout());
       line.add(labCost);
       line.add(labTax);
       line.add(labTotalCost);
       view.getContentPane().add(line);
   
       line = new JPanel(new FlowLayout());
       view.getContentPane().add(line);

        txtPurchaseID.addFocusListener(new PurchaseIDFocusListener());
        txtCustomerID.addFocusListener(new CustomerIDFocusListener());
        txtQuantity.getDocument().addDocumentListener(new QuantityChangeListener());

        btnLoad.addActionListener(new LoadButtonListerner());
        btnSave.addActionListener(new SaveButtonListerner());
        
        
    }

    public void run() {
        purchase = new PurchaseModel();
        purchase.mDate = Calendar.getInstance().getTime().toString();
        labDate.setText("Date of purchase: " + purchase.mDate);
        view.setVisible(true);
    }

    private class PurchaseIDFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent focusEvent) {

        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            process();
        }

        private void process() {
            String s = txtPurchaseID.getText();

            System.out.println("PurchaseID = " + s);

            try {
                purchase.mPurchaseID = Integer.parseInt(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Invalid PurchaseID", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            purchase = StoreManager.getInstance().getDataAdapter().loadPurchase(purchase.mPurchaseID);

            if (purchase == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No purchase with id = " + purchase.mPurchaseID + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            labPrice.setText("Purchase Price: " + purchase.mPrice);

        }

    }

    private class CustomerIDFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent focusEvent) {

        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            process();
        }

        private void process() {
            String s = txtCustomerID.getText();

            if (s.length() == 0) {
                labCustomerName.setText("Customer Name: [not specified!]");
                return;
            }

            System.out.println("CustomerID = " + s);

            try {
                purchase.mCustomerID = Integer.parseInt(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Invalid CustomerID", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            customer = StoreManager.getInstance().getDataAdapter().loadCustomer(purchase.mCustomerID);

            if (customer == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No customer with id = " + purchase.mCustomerID + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                labCustomerName.setText("Customer Name: ");

                return;
            }

            labCustomerName.setText("Purchase Name: " + customer.mName);

        }

    }

    private class QuantityChangeListener implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            process();
        }

        public void removeUpdate(DocumentEvent e) {
            process();
        }

        public void insertUpdate(DocumentEvent e) {
            process();
        }

        private void process() {
            String s = txtQuantity.getText();

            if (s.length() == 0) {
                //labCustomerName.setText("Customer Name: [not specified!]");
                return;
            }

            System.out.println("Quantity = " + s);

            try {
                purchase.mQuantity = Double.parseDouble(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter an invalid quantity", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (purchase.mQuantity <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter an invalid quantity", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (purchase.mQuantity > purchase.mQuantity) {
                JOptionPane.showMessageDialog(null,
                        "Not enough available purchases!", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            purchase.mCost = purchase.mQuantity * purchase.mPrice;
            purchase.mTax = purchase.mCost * 0.09;
            purchase.mTotal = purchase.mCost + purchase.mTax;

            labCost.setText("Cost: $" + String.format("%8.2f", purchase.mCost).trim());
            labTax.setText("Tax: $" + String.format("%8.2f", purchase.mTax).trim());
            labTotalCost.setText("Total: $" + String.format("%8.2f", purchase.mTotal).trim());

        }
    }
    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            // call data access!

            purchase = StoreManager.getInstance().getDataAdapter().loadPurchase(purchase.mPurchaseID);

            if (purchase == null) {
                JOptionPane.showMessageDialog(null, "Purchase NOT exists!");
            } else {
   
               customer = StoreManager.getInstance().getDataAdapter().loadCustomer(purchase.mCustomerID);
               product = StoreManager.getInstance().getDataAdapter().loadProduct(purchase.mProductID);
               
                txtCustomerID.setText(Integer.toString(purchase.mCustomerID));
                labCustomerName.setText("Name: " + customer.mName);
                txtProductID.setText(Integer.toString(purchase.mProductID));
                labProductName.setText("Name: " + product.mName);
                labPrice.setText("Price: " + Double.toString(product.mPrice));
                txtQuantity.setText(Double.toString(purchase.mQuantity));
                
               purchase.mCost = purchase.mQuantity * purchase.mPrice;
               purchase.mTax = purchase.mCost * 0.09;
               purchase.mTotal = purchase.mCost + purchase.mTax;
   
               labCost.setText("Cost: $" + String.format("%8.2f", purchase.mCost).trim());
               labTax.setText("Tax: $" + String.format("%8.2f", purchase.mTax).trim());
               labTotalCost.setText("Total: $" + String.format("%8.2f", purchase.mTotal).trim());
               
            }
        }
    }

    class SaveButtonListerner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }
            
            id = txtCustomerID.getText();
            
            if (id.length() == 0) {
               JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
               return;
            }
            
            try {
                purchase.mCustomerID= Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }
            
           id = txtProductID.getText();
   
           if (id.length() == 0) {
              JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
              return;
           }
   
           try {
              purchase.mProductID= Integer.parseInt(id);
           } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "ProductID is invalid!");
              return;
           }


            String quant = txtQuantity.getText();
            try {
                purchase.mQuantity = Double.parseDouble(quant);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }


            int  res = StoreManager.getInstance().getDataAdapter().savePurchase(purchase);

            if (res == IDataAdapter.PRODUCT_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "Purchase is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "Purchase is SAVED successfully!");
        }
    }

}
