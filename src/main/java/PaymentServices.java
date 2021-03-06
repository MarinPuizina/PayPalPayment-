package main.java;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;

public class PaymentServices {

    private static final String CLIENT_ID = "AQN1E3jVF39iYvxCW-tjs-tD_85hZTELmAHMstyazfl6sjdjE-mHd3NuOY725FkXanOod2-bj-VUgYxa";
    private static final String CLIENT_SECRET = "EFesiE2H4_IJI8M--nvhzZyug8VmEaShmmKkvazp0SUrSquAdBgrOusigmHDCnXAQSd1hCGYEl6Ob1no";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException {

        Payer payer = getPayer();
        RedirectUrls redirectURLs = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction)
                .setRedirectUrls(redirectURLs)
                .setPayer(payer)
                .setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println("--> Approved Payment <--");
        System.out.println(approvedPayment);

        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {

        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {

            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }

        }
        System.out.println("--> Approval Link <--");
        System.out.println(approvalLink.toString());

        return approvalLink;
    }

    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {

        Details details = getDetails(orderDetail);

        Amount amount = getAmount(orderDetail, details);

        Transaction transaction = getTransaction(orderDetail, amount);

        Item item = getItem(orderDetail);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        items.add(item);
        itemList.setItems(items);

        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private Item getItem(OrderDetail orderDetail) {

        Item item = new Item();
        item.setCurrency("USD")
                .setName(orderDetail.getProductName())
                .setPrice(orderDetail.getSubtotal())
                .setTax(orderDetail.getTax())
                .setQuantity("1");

        return item;
    }

    private Transaction getTransaction(OrderDetail orderDetail, Amount amount) {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getProductName());

        return transaction;
    }

    private Amount getAmount(OrderDetail orderDetail, Details details) {

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        return amount;
    }

    private Details getDetails(OrderDetail orderDetail) {

        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setTax(orderDetail.getTax());
        details.setSubtotal(orderDetail.getSubtotal());

        return details;
    }

    private RedirectUrls getRedirectURLs() {

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8181/PayPalPayment/web/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8181/PayPalPayment/web/review_payment.html");

        return redirectUrls;
    }

    private Payer getPayer() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo
                .setFirstName("TestName")
                .setLastName("TestLastName")
                .setEmail("fql00300@bcaoo.com");

        payer.setPayerInfo(payerInfo);

        return payer;
    }

}
