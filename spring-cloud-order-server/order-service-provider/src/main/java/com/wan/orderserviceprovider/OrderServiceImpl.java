package com.wan.orderserviceprovider;


import com.wan.springcloud.clients.OrderService;
import com.wan.springcloud.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class OrderServiceImpl implements OrderService {

    @Override
    public String orders() {
        try {
           // Thread.sleep(2000);
            System.out.println("order-service:orders被调用");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Return All Orders";
    }

    @Override
    public int insert(OrderDto dto) {
        return 0;
    }
}
