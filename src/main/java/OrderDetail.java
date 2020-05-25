package main.java;

public class OrderDetail {

    private String productName;
    private String subtotal;
    private String shipping;
    private String tax;
    private String total;

    public OrderDetail(String productName, String subtotal, String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.tax = tax;
        this.total = total;
    }


    public String getProductName() {
        return productName;
    }

    public String getSubtotal() {
        return String.format("%.2f", subtotal);
    }

    public String getShipping() {
        return String.format("%.2f", shipping);
    }

    public String getTax() {
        return String.format("%.2f", tax);
    }

    public String getTotal() {
        return String.format("%.2f", total);
    }

}
