package com.newwave.bu3internecommerce.controller;


import com.newwave.bu3internecommerce.dto.LaptopDTO;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    /**
     * Endpoint to retrieve a list of laptops with a price less than the specified value.
     *
     * @param price The maximum price limit for filtering laptops.
     * @return A list of LaptopDTO objects with a price lower than the given value.
     */
    @GetMapping("/laptops/less/{price}")
    public List<LaptopDTO> findLaptopsByPriceLessThan(@PathVariable double price) {
        return laptopService.findLaptopsByPriceLessThan(price);
    }

    /**
     * Endpoint to retrieve a list of laptops with a price greater than the specified value.
     *
     * @param price The minimum price limit for filtering laptops.
     * @return A list of LaptopDTO objects with a price higher than the given value.
     */
    @GetMapping("/laptops/greater/{price}")
    public List<LaptopDTO> findLaptopsByPriceGreaterThan(@PathVariable double price) {
        return laptopService.findLaptopsByPriceGreaterThan(price);
    }

    /**
     * Endpoint to retrieve a list of laptops within a specific price range.
     *
     * @param bottom The lower bound of the price range.
     * @param top The upper bound of the price range.
     * @return A list of LaptopDTO objects with a price between the given range.
     */
    @GetMapping("/laptops/between/{bottom}/and/{top}")
    public List<LaptopDTO> findLaptopsByPriceBetween(
            @PathVariable double bottom,
            @PathVariable double top) {
        return laptopService.findLaptopsByPriceBetween(bottom, top);
    }

    /**
     * @return all laptop in laptop list
     */
    @GetMapping("/laptops")
    public List<Laptop> getAll() {
        return laptopService.getAll();
    }

    /**
     * import laptop to inventory
     * @param laptop the laptop be imported into inventory
     */
    @PostMapping("/inventory")
    public void importLaptopToInventory(Laptop laptop) {
        laptopService.importLaptopToInventory(laptop);
    }

    /**
     * export laptop base laptopName and quantity
     * @param laptopName the laptop be sold from inventory
     * @param quantity the number of laptop be sold
     */

    @PatchMapping("laptops/{laptopName}/{quantity}")
    public void soldLaptopFromInventory(
            @PathVariable String laptopName,
            @PathVariable int quantity) {
        laptopService.soldLaptopFromInventory(laptopName, quantity);
    }

}
