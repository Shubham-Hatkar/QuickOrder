package com.example.QuickOrder.Controller;

import com.example.QuickOrder.DTO.AddCustomerRequestDto;
import com.example.QuickOrder.DTO.AddProductIntoCartRequestDto;
import com.example.QuickOrder.DTO.GetProductResponseDto;
import com.example.QuickOrder.DTO.RemoveProductFromCartDto;
import com.example.QuickOrder.Entity.Product;
import com.example.QuickOrder.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody AddCustomerRequestDto addCustomerRequestDto)
    {
        try{
            customerService.addCustomer(addCustomerRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Customer added successfully.";
    }

    @PostMapping("/addtocart")
    public String addToCart(@RequestBody AddProductIntoCartRequestDto addProductIntoCartRequestDto)
    {
        try {
            customerService.addToCart(addProductIntoCartRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Product added into cart";
    }

    @DeleteMapping("/removefromcart")
    public String removeFromCart(@RequestBody RemoveProductFromCartDto removeProductFromCartDto)
    {
        try
        {
            customerService.removeFromCart(removeProductFromCartDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Product removed successfully";
    }

    @GetMapping("/showcart")
    public ResponseEntity showCart(@RequestParam("id") int customerId)
    {
        List<GetProductResponseDto> list;
        try
        {
            list = customerService.showCart(customerId);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
