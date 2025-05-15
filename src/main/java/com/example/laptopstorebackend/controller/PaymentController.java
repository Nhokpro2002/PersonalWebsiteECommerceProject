package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.utils.VnpayUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
public class PaymentController {
    @GetMapping("/payment/vnpay")
    public ApiResponse<String> createPayment(@RequestParam("totalPrice") Integer totalPrice) {
        // Create random code for Order
        String vnp_TxnRef = VnpayUtils.getRandomTxnRef();
        String vnp_OrderInfo = "Pay for Order";
        String vnp_OrderType = "other";
        // Format struct Money for VNPay
        String vnp_Amount = String.valueOf(totalPrice * 100);

        String vnp_ReturnUrl = "https://fea5-222-252-25-162.ngrok-free.app";
        String vnp_IpAddr = "127.0.0.1";

        String paymentUrl = VnpayUtils.createPaymentUrl(
                vnp_TxnRef, vnp_OrderInfo, vnp_OrderType, vnp_Amount,
                vnp_ReturnUrl, vnp_IpAddr
        );

        //return ResponseEntity.ok(Collections.singletonMap("url", paymentUrl));
        return ApiResponse.<String>builder()
                .code(200)
                .data(paymentUrl)
                .message("Create url send to VNPay Successfully")
                .build();
    }

    @GetMapping("/payment/vnpay-return")
    public ResponseEntity<?> handleVnpayReturn(
            /*@RequestParam Map<String, String> allParams, @RequestParam Integer totalPrice*/
    @RequestParam String responseCode) {
        // Kiểm tra chữ ký, xử lý trạng thái đơn hàng tại đây
        //String vnp_SecureHash = allParams.remove("vnp_SecureHash");
        //String signData = VnpayUtils.buildHashData(allParams);

        //String mySecureHash = VnpayUtils.hmacSHA512("MJZMOXL1D8B5IM9MUCGG4HGCRMKK2SG3", signData);

        /*if (mySecureHash.equals(vnp_SecureHash)) {
            String responseCode = allParams.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                return ResponseEntity.ok("Thanh toán thành công!");
            } else {
                return ResponseEntity.badRequest().body("Thanh toán thất bại!");
            }
        } else {
            return ResponseEntity.badRequest().body("Sai chữ ký!");
        }*/
        //ApiResponse<String> response = createPayment(totalPrice);
        //if (Objects.equals(allParams.get("vnp_SecureHash"), response.getData())) {
            //String responseCode = allParams.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                return ResponseEntity.ok("Order Payment Successfully!");
            } else {
                return ResponseEntity.badRequest().body("Order Payment failed!");
            }
        //} else {
            //return ResponseEntity.badRequest().body("Something Else Incorrect!");
        //}
    }

}
