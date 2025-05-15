package com.example.laptopstorebackend.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VnpayUtils {
    private static final String VNP_VERSION = "2.1.0";
    private static final String VNP_COMMAND = "pay";
    private static final String VNP_TMN_CODE = "M4ADTD6U";
    private static final String VNP_HASH_SECRET = "MJZMOXL1D8B5IM9MUCGG4HGCRMKK2SG3";
    private static final String VNP_PAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    //private  static final String VNP_BANKCODE = "VNPAYQR";

    public static String getRandomTxnRef() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    public static String createPaymentUrl(String txnRef, String orderInfo, String orderType,
                                          String totalPrice, String returnUrl, String ipAddr) {
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNP_VERSION);
        vnp_Params.put("vnp_Command", VNP_COMMAND);
        vnp_Params.put("vnp_TmnCode", VNP_TMN_CODE);
        vnp_Params.put("vnp_Amount", totalPrice);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", txnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", returnUrl);
        vnp_Params.put("vnp_IpAddr", ipAddr);

        //
        vnp_Params.put("vnp_ExpireDate", LocalDateTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        vnp_Params.put("vnp_CreateDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        // Sorting follow ABC (anphabet dictinory)
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (String fieldName : fieldNames) {
            String value = vnp_Params.get(fieldName);
            if ((value != null) && (!value.isEmpty())) {
                // Build query string
                hashData.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
                query.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
            }
        }

        // Delete character last "&"
        if (!hashData.isEmpty()) {
            hashData.setLength(hashData.length() - 1);
            query.setLength(query.length() - 1);
        }

        // Create checksum
        String vnp_SecureHash = hmacSHA512(VNP_HASH_SECRET, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        return VNP_PAY_URL + "?" + query;
    }

    public static String hmacSHA512(String key, String data) {
        try {
            Mac hmac512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmac512.init(secretKey);
            byte[] bytes = hmac512.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(bytes);
        } catch (Exception ex) {
            throw new RuntimeException("Lỗi tạo mã HMAC", ex);
        }
    }

}
