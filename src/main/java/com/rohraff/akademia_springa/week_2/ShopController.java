package com.rohraff.akademia_springa.week_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ShopController {

    private Shop shop;

    @Autowired
    public ShopController(Shop shop) {
        this.shop = shop;
    }

    //@EventListener(ApplicationReadyEvent.class)
    private void startShop() {
        shop.start();
        shop.getPrice();
    }
}

