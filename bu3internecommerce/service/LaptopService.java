package com.newwave.bu3internecommerce.service;


import com.newwave.bu3internecommerce.dto.response.LaptopResponseDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@RequestMapping("/laptops")
public class LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

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
        } else {
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

        } else {
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
                .orElseThrow(() -> new AppException(ErrorCode.LAPTOP_NOT_EXISTED));
        laptop.setSellingPrice(newPrice);
        laptopRepository.save(laptop);
    }

    /**
     * Retrieves a paginated list of all laptops.
     *
     * @param pageable Pagination details including page number and size.
     * @return A paginated list of LaptopResponseDTO objects.
     */
    public Page<LaptopResponseDTO> findAll(Pageable pageable) {
        Page<Laptop> laptopPage = laptopRepository.findAll(pageable);
        return laptopPage.map(this::convertToDTO);
    }

    /**
     * Converts a Laptop entity to a LaptopResponseDTO.
     *
     * @param laptop The Laptop entity to convert.
     * @return A LaptopResponseDTO containing the laptop's details.
     */
    public LaptopResponseDTO convertToDTO(Laptop laptop) {
        return LaptopResponseDTO.fromEntity(laptop);
    }


}
