package com.rohraff.akademia_springa.week_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Car2 carById = carService.getCarById(car.getId());
        model.addAttribute("car", carById);
        if (carById != null) {
            return "carById";
        }
        return "carNotFound";
    }

    @GetMapping("/modify")
    public String modifyCar(@ModelAttribute Car2 car) {
        Car2 carToModify = carService.getCarById(car.getId());
        if (carToModify != null) {
            carToModify.setColor(car.getColor());
            carToModify.setMark(car.getMark());
            carToModify.setModel(car.getModel());
            return "redirect:/week_4";
        }
        return "carNotFound";
        }

    @GetMapping("/modifyOneThing")
    public String modifyOneThing(@ModelAttribute Car2 car) {
        Car2 carToModify = carService.getCarById(car.getId());
        String colorToChange = car.getColor();
        String modelToChange = car.getModel();
        String markToChange = car.getMark();
        if (carToModify != null) {
            if(colorToChange != null) {
                carToModify.setColor(colorToChange);
                return "redirect:/week_4";
            } else if(modelToChange != null) {
                carToModify.setModel(modelToChange);
                return "redirect:/week_4";
            } else if(markToChange != null) {
                carToModify.setMark(markToChange);
                return "redirect:/week_4";
            }
        }
        return "carNotFound";
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

