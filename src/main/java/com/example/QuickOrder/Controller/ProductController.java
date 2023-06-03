package com.example.QuickOrder.Controller;

import com.example.QuickOrder.DTO.AddProductRequestDto;
import com.example.QuickOrder.DTO.GetAllProductsOfFarmerResponseDto;
import com.example.QuickOrder.DTO.GetProductResponseDto;
import com.example.QuickOrder.Exceptions.FarmerIdDoesNotExistException;
import com.example.QuickOrder.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody AddProductRequestDto addProductRequestDto) throws FarmerIdDoesNotExistException {
        try
        {
            productService.addProduct(addProductRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "product added successfully.";
    }

    @GetMapping("/getproductbyname")
    public ResponseEntity getProductbyName(@RequestParam("name") String productName)
    {
        List<GetProductResponseDto> productList;
        try
        {
            productList = productService.getProductbyName(productName);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(productList, HttpStatus.FOUND);
    }

    @GetMapping("/getproductswhichareavailable") // means quantities are above 0
    public List<GetProductResponseDto> getProductsWhichAreAvailable()
    {
        return productService.getProductsWhichAreAvailable();
    }


}
