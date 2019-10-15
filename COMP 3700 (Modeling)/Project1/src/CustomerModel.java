public class CustomerModel {
    public int mCustomerID;
    public String mName;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mCustomerID).append(",");
        sb.append("\"").append(mName).append("\"").append(",");
        return sb.toString();
    }
}
