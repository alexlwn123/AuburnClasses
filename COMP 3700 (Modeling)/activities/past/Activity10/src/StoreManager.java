public class StoreManager {

    public static void main(String[] args) {
        System.out.println("Hello class!");
        SQLiteDataAdapter adapter = new SQLiteDataAdapter();
        adapter.connect();
        ProductModel product = adapter.loadProduct(3);

        System.out.println("Product: ID = " + product.mProductID + " Name = " + product.mName);

        AddProductView apView = new AddProductView();
        AddProductController apCtr = new AddProductController();
        apView.setVisible(true);

        apView.btnAdd.addActionListener(apCtr.addButtonListerner);
        apView.btnCancel.addActionListener(apCtr.cancelButtonListerner);
    }
}
