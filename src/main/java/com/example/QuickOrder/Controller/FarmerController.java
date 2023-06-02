package com.example.QuickOrder.Controller;

import com.example.QuickOrder.DTO.AddFarmerRequestDto;
import com.example.QuickOrder.DTO.GetAllProductsOfFarmerResponseDto;
import com.example.QuickOrder.Service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmer")
public class FarmerController
{
    @Autowired
    FarmerService farmerService;

    @PostMapping("/add")
    public String addFarmer(@RequestBody AddFarmerRequestDto addFarmerRequestDto)
    {
        try
        {
            farmerService.addFarmer(addFarmerRequestDto);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
        return "Farmer registered successfully";
    }

    @GetMapping("/getallproduct")
    public ResponseEntity getAllProductsByFarmerId(@RequestParam("id") int farmerId) {
        List<GetAllProductsOfFarmerResponseDto> productList;
        try
        {
            productList = farmerService.getAllProductsByFarmerId(farmerId);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(productList, HttpStatus.FOUND);
    }
}
