package com.newwave.bu3internecommerce.controller;


import com.newwave.bu3internecommerce.dto.LaptopDTO;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public Page<LaptopDTO> getLaptops(Pageable pageable) {
        return laptopService.findAll(pageable);
    }


}