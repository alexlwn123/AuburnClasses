import javax.swing.*;

public class PurchaseHistoryUI {

    public JFrame view;
    public JList purchases;

    public UserModel user = null;

//    public JButton btnLoad = new JButton("Load Customer");
//    public JButton btnSave = new JButton("Save Customer");
//
//    public JTextField txtCustomerID = new JTextField(20);
//    public JTextField txtName = new JTextField(20);
//    public JTextField txtPhone = new JTextField(20);
//    public JTextField txtAddress = new JTextField(20);


    public PurchaseHistoryUI(UserModel user) {
        this.user = user;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("View Purchase History - Customer View");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Purchase history for " + user.mFullname);

        title.setFont (title.getFont().deriveFont (24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        PurchaseHistoryModel list = StoreManager.getInstance().getDataAdapter().loadPurchaseHistory(user.mCustomerID);
        DefaultListModel<String> data = new DefaultListModel<>();

        for (PurchaseModel purchase : list.purchases)
            data.addElement(purchase.toString());

        purchases = new JList(data);

        JScrollPane scrollableList = new JScrollPane(purchases);
        view.getContentPane().add(scrollableList);


    }
}