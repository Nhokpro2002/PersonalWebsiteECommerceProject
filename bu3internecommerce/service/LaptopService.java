package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.LaptopDTO;
import com.newwave.bu3internecommerce.model.Cart;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

   @Override
    public String toString() {
        return laptopRepository.toString();
   }

    /**
     * Retrieves a list of laptops with a price less than the specified value.
     *
     * @param price The maximum price limit for laptops.
     * @return A list of LaptopDTO objects that have a price lower than the given value.
     */
    public List<LaptopDTO> findLaptopsByPriceLessThan(double price) {
        return laptopRepository.findLaptopsByPriceLessThan(price);
    }

    /**
     * Retrieves a list of laptops with a price greater than the specified value.
     *
     * @param price The minimum price limit for laptops.
     * @return A list of LaptopDTO objects that have a price higher than the given value.
     */
    public List<LaptopDTO> findLaptopsByPriceGreaterThan(double price) {
        return laptopRepository.findLaptopsByPriceGreaterThan(price);
    }

    /**
     * Retrieves a list of laptops that fall within a specified price range.
     *
     * @param bottom The lower bound of the price range.
     * @param top The upper bound of the price range.
     * @return A list of LaptopDTO objects that have a price between the given range.
     */
    public List<LaptopDTO> findLaptopsByPriceBetween(double bottom, double top) {
        return laptopRepository.findLaptopsByPriceBetween(bottom, top);
    }

    /**
     * Retrieves a list of laptops that belong to a specific category.
     *
     * @param category The category of laptops to filter by.
     * @return A list of LaptopDTO objects that match the given category.
     */
    public List<LaptopDTO> findLaptopsByCategory(String category) {
        return laptopRepository.findByCategory(category);
    }

    /**
     * Imports a laptop into inventory.
     *
     * @param laptop The laptop to be added to inventory.
     */
    public void importLaptopToInventory(Laptop laptop) {
        // Search laptop in inventory
        Laptop existingLaptop = laptopRepository.findLaptopByName(laptop.getName());

        if (existingLaptop != null) {
            existingLaptop.setStock(existingLaptop.getStock() + laptop.getStock());
            laptopRepository.save(existingLaptop);
        } else {
            laptopRepository.save(laptop);
        }

        System.out.println("Laptop imported successfully!");
    }


    /**
     * Export laptop based on laptopName and quantity
     * @param laptopName the laptop to be purchased
     * @param quantity the number of laptops to be purchased
     */
    public void soldLaptopFromInventory(String laptopName, int quantity) {

        // searching laptop by laptopName in inventory
        Laptop laptop = laptopRepository.findLaptopByName(laptopName);

        if (laptop == null) {
            System.out.println("There are no products matching your request.");
            return;
        }

        // check stock is avaliable
        if (laptop.getStock() < quantity) {
            System.out.println("Not enough stock available!");
            return;
        }

        laptop.setStock(laptop.getStock() - quantity);
        laptopRepository.save(laptop);

        System.out.println("Successfully exported " + quantity + " laptops.");
    }


    /**
     * Get all products in Inventory
     * @return laptop list in Inventory
     */
    public List<Laptop> getAll() {
        return laptopRepository.findAll();
    }
    

}
