package com.rohraff.akademia_springa.week_3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.CaretListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private List<Car> carList;

    public CarController() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1,"Toyota", "Corolla", "Green"));
        carList.add(new Car(2,"Audi", "A4", "Red"));
        carList.add(new Car(3,"Ferrari", "LaFerrari", "Red"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarsById (@PathVariable int id) {
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id).findFirst();
        if(first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/color/{color}", method = RequestMethod.GET)
    public ResponseEntity<List> getCarsByColor (@PathVariable String color) {
        List newCarList = carList.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toList());
        if (newCarList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCarList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCar (@RequestBody Car newCar) {
        boolean add = carList.add(newCar);
        if (add) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity updateCar(@RequestBody Car newCar) {
         Optional<Car> car = carList.stream().filter(car1 -> car1.getId() == newCar.getId()).findFirst();
         if (car.isPresent()) {
             carList.remove(car.get());
             carList.add(newCar);
             return new ResponseEntity(HttpStatus.OK);
         }
         return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity modifyCar (@RequestParam int id, @RequestParam(required = false) String mark, String model, String color) {
        Optional<Car> carToModify = carList.stream().filter(car -> car.getId() == id).findFirst();
        if(carToModify.isPresent()) {
            if (mark != null) {
                carToModify.get().setMark(mark);
            }
            if (model != null) {
                carToModify.get().setModel(model);
            }
            if (color != null) {
                carToModify.get().setColor(color);
            }
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity deleteCar (@PathVariable int id) {
        Optional<Car> car = carList.stream().filter(car1 -> car1.getId() == id).findFirst();
        if (car.isPresent()) {
            carList.remove(car.get());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
     }
}