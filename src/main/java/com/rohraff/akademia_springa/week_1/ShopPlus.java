package com.rohraff.akademia_springa.week_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("plus")

public class ShopPlus implements Shop {

    ShopList shopList;

    @Value("${shop-info.vat}")
    private double vat;

    @Autowired
    public ShopPlus(ShopList shopList) {
        this.shopList = shopList;
    }

    @Override
    public void start() {
        shopList.getShopList();
    }

    @Override
    public void getPrice() {
        double bruttoValue = shopList.getCartPrice();
        double vatValue = (bruttoValue/((vat/100)+1))*(vat/100);
        System.out.print("Cena zbiorcza: " + bruttoValue + ", w tym VAT w kwocie: ");
        System.out.format("%.2f%n", vatValue);
    }
}
