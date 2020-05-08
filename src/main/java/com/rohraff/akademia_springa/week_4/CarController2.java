package com.rohraff.akademia_springa.week_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/week_4")
public class CarController2 {

    private CarService2 carService;

    @Autowired
    public CarController2(CarService2 carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getCars(Model model) {
        model.addAttribute("cars", carService.getCarList());
        model.addAttribute("newCar", new Car2());
        return "week_4_Gui";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car2 car) {
        carService.getCarList().add(car);
        return "redirect:/week_4";
    }

    @GetMapping("/byId")
    public String getCarById(@ModelAttribute Car2 car, Model model) {
        Optional<Car2> carById = carService.getCarById(car.getId());
        if (carById.isPresent()) {
            model.addAttribute("car", carById.get());
            return "carById";
        } else {
            return "carNotFound";
        }
    }

    @GetMapping("/modify")
    public String modifyCar(@ModelAttribute Car2 car) {
        Car2 modifiedCar = carService.modifyCar(car);
        if (modifiedCar.equals(car)) {
            return "carNotFound";
        } else {
            return "redirect:/week_4";
        }
    }


    @GetMapping("/delete")
    public String deleteCar(@ModelAttribute Car2 car) {
        boolean isDeleted = carService.deleteCar(car.getId());
        if (isDeleted) {
            return "redirect:/week_4";
        }
        return "carNotFound";
    }
}

