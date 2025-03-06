package com.newwave.bu3internecommerce.service;


import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/laptops")
public class LaptopService {

    @Autowired
    LaptopRepository laptopRepository;

    /**
     * Add Laptop into inventory, if laptop is existing so update, if no so add new
     * @param laptop The new Laptop
     */
    public void importLaptop(Laptop laptop) {
        Optional<Laptop> temporaryLaptop = laptopRepository.findByName(laptop.getName());
        if (temporaryLaptop.isPresent()) {
            Laptop existingLaptop = temporaryLaptop.get();
            existingLaptop.setStock(laptop.getStock() + existingLaptop.getStock());
            laptopRepository.save(existingLaptop);
        }
        else {
            laptopRepository.save(laptop);
        }
    }

    /**
     * Sell laptop for customer
     * @param laptopName The laptop
     * @param quantity The laptop quantity be sold
     */
    @PostMapping()
    public void sellLaptop(String laptopName, int quantity) {
        Optional<Laptop> temporaryLaptop = laptopRepository.findByName(laptopName);
        if (temporaryLaptop.isPresent()) {
            Laptop existingLaptop = temporaryLaptop.get();
            if (existingLaptop.getStock() >= quantity) {
                existingLaptop.setStock(existingLaptop.getStock() - quantity);
                laptopRepository.save(existingLaptop);
            }

        }
        else {
            System.out.println("Not enough quantity in inventory");
        }
    }

    /**
     * Update the new price for laptop
     * @param laptopName The Laptop
     * @param newPrice The new price for laptop
     */
    public void updatePrice(String laptopName, double newPrice) {
        Laptop laptop = laptopRepository.findByName(laptopName)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));
        laptop.setPrice(newPrice);
        laptopRepository.save(laptop);
    }

    /**
     * Get all laptops information inventory
     * @return all laptops information in inventory
     */
    public List<Laptop> getLaptops() {

        return laptopRepository.findAll();
    }


}
