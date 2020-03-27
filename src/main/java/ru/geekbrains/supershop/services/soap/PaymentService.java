package ru.geekbrains.supershop.services.soap;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.geekbrains.soap.payment.GetPaymentRequest;
import ru.geekbrains.soap.payment.GetPaymentResponse;
import ru.geekbrains.soap.payment.Payment;
import ru.geekbrains.soap.payment.PaymentPort;
import ru.geekbrains.soap.payment.PaymentPortService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public List<Payment> getPayments(String country) {

        PaymentPort paymentPort = new PaymentPortService().getPaymentPortSoap11();

        GetPaymentRequest request = new GetPaymentRequest();
        request.setCountry(country);
        GetPaymentResponse response;

        try {
            response = paymentPort.getPayment(request);
        } catch (Exception ex) {
            response = null;
        }

        return response == null ? new ArrayList<>() : response.getPayments();

    }

}