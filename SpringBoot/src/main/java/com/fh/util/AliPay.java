package com.fh.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fh.model.Order;

import java.math.BigDecimal;

public class AliPay {

   /* public static void main(String[] args) {
        Order order=new Order();
        order.setId(6);
        order.setTotalMoney(new BigDecimal(25.50));
        aliPay(order);
        try {
            queryAliPayStatus(order);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }*/


    public static String  aliPay(Order order){
        AlipayClient alipayClient =  new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do" ,
                "2021000116688410",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDQH/mJH7T41I61ML/S8NeQrcrI/HetHn5+cP1fWxC578dViQ1JYMCqhu7tzS2Fx3PlB2ipg6syB9OCeL21sh6Wv5D90izN7cK9RGsxwnizyNKyYwHB79h11CverIJk8beUKHy3NUNiEG+8mknDKlQS8Q+B//ktjOcajSBP1Bgm/L0uUCvHZsxyZghebFhA7J5HCrCxyWc8+qF7U1g7Ht0ohJ/HDpekcWEmVqEnMq6ZsGp5H8tnTDc33QT9wjbVBjaiItkjQ8jkMRxR5xoyxfJjU5diPpCh4IX0DWs6mbyvxhLqiUBo5N8KOwGb5Mdm4aghfAKde/zaon9oqakRZncTAgMBAAECggEAIZu4OQt0tuXkMaAwqciYltVptLEIRl1xQcvPLpHPDqeIwehCzAGiuybU+HO0kTDXJzneDdmkUIzw+9WxlOCxjdhbepFxCMQHOXcN9FgaE7YvR/0y8HVhuA1OFEQ5PkHGSleLZC5mtjQ/Ru6HUMvKDdD9lxmVzGQ/gYsel6WBTPIik04S0U4rbiN8CUprQmz12SMhNBMPIDPCiTd2yXrwGHJH7QfTQ29qyv6CtdiTv4Ks/r6T7HKk/UJpVQr4daixQG+ZRMz7wu8tc2IazCr9RFpsKGzEK9T9IPbq/rHmZQLoFyCaJX0iBZnY3MttOGIrAxn+r0c9Ej9Mdm7U5WfUeQKBgQD0TxOZqm2JqSrF+CxuDjU+qd+6+PzMKYZp5X7SHW9FvnzyzQbajgGZ32izNCNwxvA/oT0RT4T+FOhsl+5RTZuX/D3gUmLVVjapehvS4IZqWC/QDCt4Kn2VPMwvrgpBHncI1sojHLPReP/YLDyEEwg2R3phvAZlvM2lYT7rBT29vQKBgQDaFZ+mIhP5Birg8cPbRtxs8Kvb1XtfLQZ5gLyZo3AH9bFCzOXwPh2TgEaj8cVBQY24Mq4ZHAvKX8I9/V593FfNYSabGGCiqoYP/XfeNdST/CZOZCekdktIn1quYxoQQ5qhPOzHLhGR3g+VvgZour4+8yncdBNAJIXnRGqW2rjNDwKBgCBT1mQfCR+OIvrPPBHUD3ebQt+a0tixJfgdRgrHmlHAQwhd2OpSoCOcTF5qJ8ZkjKXe0BXaT97PJXI5KG/ZtR0HY6irPii8n78NSWE+FIs94BXHhnkAJl35DYvBm9nCDj49LcI3qhOtzyzrrZZMbmnAS5FjL/WGZi/cDldK+kCBAoGAWrSHtFeUKw0PpIOpzHgKkhz/ve1q5MkbWknbJ6O8ovLjiT8jm+qZ7pF50BJs0plrUmNx2eu6U6/EYHgTZxHQSxHPNs7K0p1OKTRpOLj28536aLxraJHy+f+dfc6mNoGHvOxUJvvYPu03Wjaw/7o8Cn6kuJZms1T9hJUGKDxMhK0CgYBvkv5vHaFFBHW8Vdmx6kwX43I2uji9Qt4IA5a6pmTH9LHYuOp95CAO/qssBnQ8Euf5XQZn0Fl153gA68hu2k7PSQ1ex81rq6Gj/N1IUhUxP3EkGTggZMrmHXHahxp9FkDsJJlLqJtKhqYbbcVbWaV42drQx6QuUZYmMOqn8H21sw==",
                "JSON",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvdvQ6Oa27QcHCTgWdBPjju4jxAqTN6zxfgPJy/46ekK0f/FD2udStYJ4g5JjWiMfS2XyJnxTZMSGdwqNiMeqHtuhv6MlKp7Ma6WLoCVDJChUqFb2Xo3CAHcftQVz5QXdwoUHJSpp8a0n1n6SERciQE9b4OTY6mO/xGIw0hVXLar1np8Z7XWpAAzeAnTOlVfjlFcTXtnd0UnJTEKH0mPjvwfwAwGhnyzGfwXhPUOmRIOavZQj7uQB+HCBOQTaXSAEu5waInHBGtS18Rioovq3fw7+ZBOHLeBiAU75mRWFKhEsoWzv2NMiPNAhOTaUndHyLXUKR/HsHfFDm9lQiH7q7QIDAQAB",
                "RSA2");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "http://localhost:8088/success.html" );
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\"hhg"+order.getId()+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":"+order.getTotalMoney()+","  +
                "    \"subject\":\"ABC_商城\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }


    public static void queryAliPayStatus(Order order) throws AlipayApiException {
        AlipayClient alipayClient =  new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do" ,
                "2021000116688410",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDQH/mJH7T41I61ML/S8NeQrcrI/HetHn5+cP1fWxC578dViQ1JYMCqhu7tzS2Fx3PlB2ipg6syB9OCeL21sh6Wv5D90izN7cK9RGsxwnizyNKyYwHB79h11CverIJk8beUKHy3NUNiEG+8mknDKlQS8Q+B//ktjOcajSBP1Bgm/L0uUCvHZsxyZghebFhA7J5HCrCxyWc8+qF7U1g7Ht0ohJ/HDpekcWEmVqEnMq6ZsGp5H8tnTDc33QT9wjbVBjaiItkjQ8jkMRxR5xoyxfJjU5diPpCh4IX0DWs6mbyvxhLqiUBo5N8KOwGb5Mdm4aghfAKde/zaon9oqakRZncTAgMBAAECggEAIZu4OQt0tuXkMaAwqciYltVptLEIRl1xQcvPLpHPDqeIwehCzAGiuybU+HO0kTDXJzneDdmkUIzw+9WxlOCxjdhbepFxCMQHOXcN9FgaE7YvR/0y8HVhuA1OFEQ5PkHGSleLZC5mtjQ/Ru6HUMvKDdD9lxmVzGQ/gYsel6WBTPIik04S0U4rbiN8CUprQmz12SMhNBMPIDPCiTd2yXrwGHJH7QfTQ29qyv6CtdiTv4Ks/r6T7HKk/UJpVQr4daixQG+ZRMz7wu8tc2IazCr9RFpsKGzEK9T9IPbq/rHmZQLoFyCaJX0iBZnY3MttOGIrAxn+r0c9Ej9Mdm7U5WfUeQKBgQD0TxOZqm2JqSrF+CxuDjU+qd+6+PzMKYZp5X7SHW9FvnzyzQbajgGZ32izNCNwxvA/oT0RT4T+FOhsl+5RTZuX/D3gUmLVVjapehvS4IZqWC/QDCt4Kn2VPMwvrgpBHncI1sojHLPReP/YLDyEEwg2R3phvAZlvM2lYT7rBT29vQKBgQDaFZ+mIhP5Birg8cPbRtxs8Kvb1XtfLQZ5gLyZo3AH9bFCzOXwPh2TgEaj8cVBQY24Mq4ZHAvKX8I9/V593FfNYSabGGCiqoYP/XfeNdST/CZOZCekdktIn1quYxoQQ5qhPOzHLhGR3g+VvgZour4+8yncdBNAJIXnRGqW2rjNDwKBgCBT1mQfCR+OIvrPPBHUD3ebQt+a0tixJfgdRgrHmlHAQwhd2OpSoCOcTF5qJ8ZkjKXe0BXaT97PJXI5KG/ZtR0HY6irPii8n78NSWE+FIs94BXHhnkAJl35DYvBm9nCDj49LcI3qhOtzyzrrZZMbmnAS5FjL/WGZi/cDldK+kCBAoGAWrSHtFeUKw0PpIOpzHgKkhz/ve1q5MkbWknbJ6O8ovLjiT8jm+qZ7pF50BJs0plrUmNx2eu6U6/EYHgTZxHQSxHPNs7K0p1OKTRpOLj28536aLxraJHy+f+dfc6mNoGHvOxUJvvYPu03Wjaw/7o8Cn6kuJZms1T9hJUGKDxMhK0CgYBvkv5vHaFFBHW8Vdmx6kwX43I2uji9Qt4IA5a6pmTH9LHYuOp95CAO/qssBnQ8Euf5XQZn0Fl153gA68hu2k7PSQ1ex81rq6Gj/N1IUhUxP3EkGTggZMrmHXHahxp9FkDsJJlLqJtKhqYbbcVbWaV42drQx6QuUZYmMOqn8H21sw==",
                "JSON",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvdvQ6Oa27QcHCTgWdBPjju4jxAqTN6zxfgPJy/46ekK0f/FD2udStYJ4g5JjWiMfS2XyJnxTZMSGdwqNiMeqHtuhv6MlKp7Ma6WLoCVDJChUqFb2Xo3CAHcftQVz5QXdwoUHJSpp8a0n1n6SERciQE9b4OTY6mO/xGIw0hVXLar1np8Z7XWpAAzeAnTOlVfjlFcTXtnd0UnJTEKH0mPjvwfwAwGhnyzGfwXhPUOmRIOavZQj7uQB+HCBOQTaXSAEu5waInHBGtS18Rioovq3fw7+ZBOHLeBiAU75mRWFKhEsoWzv2NMiPNAhOTaUndHyLXUKR/HsHfFDm9lQiH7q7QIDAQAB",
                "RSA2");  //获得初始化的AlipayClient
        AlipayTradeQueryRequest request =  new  AlipayTradeQueryRequest();
        request.setBizContent( "{"  +
                "     \"out_trade_no\":\"hhg"+order.getId()+"\""  +
                "  }" );
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()){
            System.out.println( "调用成功" );
            String tradeStatus = response.getTradeStatus();
            System.out.println(tradeStatus);
        }  else  {
            System.out.println( "调用失败" );
        }
    }
}
