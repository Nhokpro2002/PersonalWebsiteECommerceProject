package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.dto.LaptopDTO;
import com.newwave.bu3internecommerce.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    /**
     * delete all laptops in Shopping Cart
     * @param cartId Shopping Cart is retrieved by cartId
     */
    @Modifying
    @Query("UPDATE Laptop laptop " +
            "SET laptop.cart = NULL" +
            " WHERE laptop.cart.id = :cartId")
    void removeAllProductsFromCart(@Param("cartId") Long cartId);

    /**
     * filter all laptops have price less than special price
     * @param price the special price
     * @return list laptop information have price less than special price
     */
    @Query("SELECT new com.newwave.bu3internecommerce.dto.LaptopDTO(laptop)" +
            " FROM Laptop laptop" +
            " WHERE laptop.price <= :price")
    List<LaptopDTO> findLaptopsByPriceLessThan(@Param("price") double price);


    /**
     * filer all laptop have price in price range
     * @param bottomPrice the bottomPrice
     * @param topPrice the topPrice
     * @return list laptop information have price in price range
     */
    @Query("select new com.newwave.bu3internecommerce.dto.LaptopDTO(laptop)" +
            " FROM Laptop laptop " +
            "WHERE laptop.price >= :bottomPrice AND laptop.price <= :topPrice")
    List<LaptopDTO> findLaptopsByPriceBetween(@Param("bottomPrice") double bottomPrice,
                                              @Param("topPrice") double topPrice);


    /**
     * filter all laptops have price greater than special price
     * @param price the special price
     * @return list laptop information have price greater than special price
     */
    @Query("SELECT new com.newwave.bu3internecommerce.dto.LaptopDTO(laptop)" +
            " FROM Laptop laptop " +
            "WHERE laptop.price >= :price")
    List<LaptopDTO> findLaptopsByPriceGreaterThan(@Param("price") double price);


    /**
     * Get all laptops have same category
     * @param category the category of laptop
     * @return list laptop information have same category
     */
    @Query("SELECT new com.newwave.bu3internecommerce.dto.LaptopDTO(laptop)" +
            " FROM Laptop laptop " +
            "WHERE laptop.category = :category")
    List<LaptopDTO> findByCategory(@Param("category") String category);

    /**
     * Check laptop exist in inventory by name
     * @param name the name of laptop
     * @return true if laptop exist and false in otherwise
     */
    boolean existsByName(String name);

    /**
     * Get Laptop by laptopName
     * @param laptopName name  of laptop
     * @return laptop information
     */
    Laptop findLaptopByName(String laptopName);
}
