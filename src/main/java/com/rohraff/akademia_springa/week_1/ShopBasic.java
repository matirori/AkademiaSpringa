package com.rohraff.akademia_springa.week_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("basic")
public class ShopBasic implements Shop {

    ShopList shopList;

    @Autowired
    public ShopBasic(ShopList shopList) {
        this.shopList = shopList;
    }

    @Override
    public void start() {
        shopList.getShopList();
    }

    @Override
    public void getPrice() {
        System.out.println("Cena zbiorcza: " + shopList.getCartPrice());
    }
}
