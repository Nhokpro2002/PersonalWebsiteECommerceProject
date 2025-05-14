package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.utils.VnpayUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class PaymentController {
    @GetMapping("/payment/vnpay")
    public ResponseEntity<?> createPayment(@RequestParam("totalPrice") Integer totalPrice) {
        String vnp_TxnRef = VnpayUtils.getRandomTxnRef(); // mã đơn hàng ngẫu nhiên
        String vnp_OrderInfo = "Thanh toan don hang";
        String vnp_OrderType = "other";
        String vnp_Amount = String.valueOf(totalPrice * 100); // VNPay yêu cầu * 100

        String vnp_ReturnUrl = "https://0caa-42-113-205-213.ngrok-free.app";
        String vnp_IpAddr = "127.0.0.1";

        String paymentUrl = VnpayUtils.createPaymentUrl(
                vnp_TxnRef, vnp_OrderInfo, vnp_OrderType, vnp_Amount,
                vnp_ReturnUrl, vnp_IpAddr
        );

        return ResponseEntity.ok(Collections.singletonMap("url", paymentUrl));
    }

    /*@GetMapping("/api/payment/vnpay-return")
    public ResponseEntity<?> handleVnpayReturn(@RequestParam Map<String, String> allParams) {
        // Kiểm tra chữ ký, xử lý trạng thái đơn hàng tại đây
        String vnp_SecureHash = allParams.remove("vnp_SecureHash");
        String signData = VnpayUtils.buildHashData(allParams);

        String mySecureHash = VnpayUtils.hmacSHA512("MJZMOXL1D8B5IM9MUCGG4HGCRMKK2SG3", signData);

        if (mySecureHash.equals(vnp_SecureHash)) {
            String responseCode = allParams.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                return ResponseEntity.ok("Thanh toán thành công!");
            } else {
                return ResponseEntity.badRequest().body("Thanh toán thất bại!");
            }
        } else {
            return ResponseEntity.badRequest().body("Sai chữ ký!");
        }
    }*/

}
