package main.java;

import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.RedirectUrls;

public class PaymentServices {

    private static final String CLIENT_ID = "AQN1E3jVF39iYvxCW-tjs-tD_85hZTELmAHMstyazfl6sjdjE-mHd3NuOY725FkXanOod2-bj-VUgYxa";
    private static final String CLIENT_SECRET = "EFesiE2H4_IJI8M--nvhzZyug8VmEaShmmKkvazp0SUrSquAdBgrOusigmHDCnXAQSd1hCGYEl6Ob1no";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail) {

        Payer payer = getPayer();

        RedirectUrls redirectURLs = getRedirectURLs();

        return null;
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
