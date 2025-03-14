package com.newwave.bu3internecommerce.controller;


import com.newwave.bu3internecommerce.dto.response.LaptopResponseDTO;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.service.LaptopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

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
     * @param laptopName The laptop name of laptop
     * @param newPrice The new price for laptop
     */
    @PatchMapping("/name/{laptopName}/newPrice/{newPrice}")
    public void updatePrice(@PathVariable String laptopName,
                            @PathVariable double newPrice) {
        laptopService.updatePrice(laptopName, newPrice);
    }

    /**
     * Sell laptop from inventory
     * @param laptopName The laptop name of laptop
     * @param quantity The laptop quantity be sold
     */
    @PatchMapping("/name/{laptopName}/quantity/{quantity}")
    public void sellLaptop(@PathVariable String laptopName,
                           @PathVariable int quantity) {
        laptopService.sellLaptop(laptopName, quantity);
    }

    /**
     * Retrieves a paginated list of all laptops.
     *
     * @param pageable Pagination details including page number and size.
     * @return A paginated list of LaptopResponseDTO objects.
     */
    @GetMapping
    public Page<LaptopResponseDTO> getLaptops(Pageable pageable) {
        return laptopService.findAll(pageable);
    }


}