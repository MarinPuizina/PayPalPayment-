package main.java;

import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;

public class PaymentServices {

    private static final String CLIENT_ID = "AQN1E3jVF39iYvxCW-tjs-tD_85hZTELmAHMstyazfl6sjdjE-mHd3NuOY725FkXanOod2-bj-VUgYxa";
    private static final String CLIENT_SECRET = "EFesiE2H4_IJI8M--nvhzZyug8VmEaShmmKkvazp0SUrSquAdBgrOusigmHDCnXAQSd1hCGYEl6Ob1no";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail) {

        Payer payer = getPayer();

        return null;
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
