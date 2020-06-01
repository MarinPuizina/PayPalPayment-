package main.java;

import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/authorize_payment") // must match with the form action (checkout.html)
public class AuthorizePaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Getting parameters from a form
        String product = request.getParameter("product");
        String subtotal = request.getParameter("subtotal");
        String shipping = request.getParameter("shipping");
        String tax = request.getParameter("tax");
        String total = request.getParameter("total");

        OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);

        PaymentServices paymentServices = new PaymentServices();
        String approvalLink = null;
        try {
            approvalLink = paymentServices.authorizePayment(orderDetail);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        response.sendRedirect(approvalLink);

    }

}
