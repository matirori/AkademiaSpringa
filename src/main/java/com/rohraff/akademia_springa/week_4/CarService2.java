package com.rohraff.akademia_springa.week_4;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService2 {

    private List<Car2> carList;

    public CarService2() {
        carList= new ArrayList<Car2>();
        carList.add(new Car2(1,"Toyota", "Corolla", "Green"));
        carList.add(new Car2(2,"Audi", "A4", "Red"));
        carList.add(new Car2(3,"Ferrari", "LaFerrari", "Red"));
    }

    public List<Car2> getCarList() {
        return carList;
    }

    public Optional<Car2> getCarById(int id) {
        Optional<Car2> car = carList.stream().filter(car21 -> car21.getId() == id).findFirst();
        return car;
    }

    public Car2 modifyCar(Car2 car2) {
        Optional<Car2> car = carList.stream().filter(car21 -> car21.getId() == car2.getId()).findFirst();
        if(car.isPresent()) {
            if(car2.getMark() != "") {
                car.get().setMark(car2.getMark());
            }
            if(car2.getModel() != "") {
                car.get().setModel(car2.getModel());
            }
            if(car2.getColor() != "") {
                car.get().setColor(car2.getColor());
            }
            return car.get();
        } else {
            return car2;
        }
    }

    public boolean deleteCar(int id) {
        boolean check = false;
        Optional<Car2> carToDelete = carList.stream().filter(car21 -> car21.getId() == id).findFirst();
        if (carToDelete.isPresent()) {
            carList.remove(carToDelete.get().getId());
            check = true;
            return check;
        }
        return check;
    }


    @Override
    public String toString() {
        return "CarService{" +
                "carList=" + carList +
                '}';
    }
}
