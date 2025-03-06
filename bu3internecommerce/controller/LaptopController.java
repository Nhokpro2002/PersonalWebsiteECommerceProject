package com.newwave.bu3internecommerce.controller;


import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    /**
     * Import laptops to Inventory
     * @param laptop The laptop be added in inventory
     */
    @PostMapping()
    public void importLaptop(@RequestBody Laptop laptop) {
        laptopService.importLaptop(laptop);
    }

    /**
     * return all laptops in inventory
     */
    @GetMapping()
    public List<Laptop> getLaptops() {
        return laptopService.getLaptops();
    }

    /**
     * Update new price for laptop
     * @param laptopName The laptop
     * @param newPrice The new price for laptop
     */
    @PatchMapping("/name/{laptopName}/newPrice/{newPrice}")
    public void updatePrice(@PathVariable String laptopName,
                            @PathVariable double newPrice) {
        laptopService.updatePrice(laptopName, newPrice);
    }

    /**
     * export laptop from inventory
     * @param laptopName The Laptop
     * @param quantity The quantity be requested
     */
    @PatchMapping("/name/{laptopName}/quantity/{quantity}")
    public void sellLaptop(@PathVariable String laptopName,
                           @PathVariable int quantity) {
        laptopService.sellLaptop(laptopName, quantity);
    }


}