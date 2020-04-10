package com.rohraff.akademia_springa.week_1;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Random;

@Component
public class ShopList{
    private ArrayList<Product> shopList = new ArrayList();

    public ShopList(ArrayList<Product> shopList) {
        this.shopList = shopList;
    }

    Random random = new Random();

    public ShopList() {
        shopList.add(new Product("Kalosze", random.nextInt(250)+50));
        shopList.add(new Product("Spodnie", random.nextInt(250)+50));
        shopList.add(new Product("Kurtka", random.nextInt(250)+50));
        shopList.add(new Product("Koszula", random.nextInt(250)+50));
        shopList.add(new Product("Skarpetki", random.nextInt(250)+50));
    }

    public void getShopList() {
        for (Product product: shopList) {
            System.out.println("Nazwa produktu: " + product.getName() + " Cena Produktu: " + product.getPrice());
        }
    }

    @Override
    public String toString() {
        return "ShopList{" +
                "shopList=" + shopList +
                '}';
    }

    public int getCartPrice() {
        int sum = 0;
        for(int i = 0; i < shopList.size(); i++ ) {
            sum += shopList.get(i).getPrice();
        }
        return sum;
    }
}