package com.example.QuickOrder.Service;

import com.example.QuickOrder.DTO.AddFarmerRequestDto;
import com.example.QuickOrder.DTO.GetAllProductsOfFarmerResponseDto;
import com.example.QuickOrder.Exceptions.FarmerAlreadyExistWithThisMobileNo;
import com.example.QuickOrder.Exceptions.FarmerIdDoesNotExistException;
import com.example.QuickOrder.Entity.Farmer;
import com.example.QuickOrder.Entity.Product;
import com.example.QuickOrder.Repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmerService
{

    @Autowired
    FarmerRepository farmerRepository;

    public void addFarmer(AddFarmerRequestDto addFarmerRequestDto) throws FarmerAlreadyExistWithThisMobileNo {
        // phone no is unique so check if current phone no is already present or not.
        if(farmerRepository.findByPhoneNo(addFarmerRequestDto.getPhoneNo()) != null)
            throw new FarmerAlreadyExistWithThisMobileNo();

        Farmer farmer = new Farmer();
        farmer.setAddress(addFarmerRequestDto.getAddress());
        farmer.setAge(addFarmerRequestDto.getAge());
        farmer.setName(addFarmerRequestDto.getName());
        farmer.setPhoneNo(addFarmerRequestDto.getPhoneNo());
        farmerRepository.save(farmer);
    }

    public List<GetAllProductsOfFarmerResponseDto> getAllProductsByFarmerId(int farmerId) throws FarmerIdDoesNotExistException {
        // check if farmer id is exist or not
        if(farmerRepository.findById(farmerId) == null)
            throw new FarmerIdDoesNotExistException();

        List<Product> productList = farmerRepository.findById(farmerId).get().getProductList();
        List<GetAllProductsOfFarmerResponseDto> products = new ArrayList<>();
        for(Product product : productList)
        {
            GetAllProductsOfFarmerResponseDto getAllProductsOfFarmerResponseDto = new GetAllProductsOfFarmerResponseDto();
            getAllProductsOfFarmerResponseDto.setProductPrice(product.getProductPrice());
            getAllProductsOfFarmerResponseDto.setProductName(product.getProductName());
            getAllProductsOfFarmerResponseDto.setProductAvailableQuantity(product.getProductAvailableQuantity());

            products.add(getAllProductsOfFarmerResponseDto);
        }

        return products;
    }
}
