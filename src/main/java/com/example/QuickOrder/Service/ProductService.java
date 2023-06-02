package com.example.QuickOrder.Service;

import com.example.QuickOrder.DTO.AddProductRequestDto;
import com.example.QuickOrder.DTO.GetProductResponseDto;
import com.example.QuickOrder.Exceptions.FarmerIdDoesNotExistException;
import com.example.QuickOrder.Exceptions.ProductNameDoesNotExistException;
import com.example.QuickOrder.Entity.Farmer;
import com.example.QuickOrder.Entity.Product;
import com.example.QuickOrder.Repository.FarmerRepository;
import com.example.QuickOrder.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    FarmerRepository farmerRepository;

    public void addProduct(AddProductRequestDto addProductRequestDto) throws FarmerIdDoesNotExistException
    {
        // first check farmer id exist or not
        int farmerId = addProductRequestDto.getFarmerId();
        Farmer farmer;
        try
        {
            farmer = farmerRepository.findById(farmerId).get();
        }
        catch (Exception e)
        {
            throw new FarmerIdDoesNotExistException();
        }

        // if farmer found
        Product product = new Product();
        product.setProductName(addProductRequestDto.getProductName());
        product.setProductPrice(addProductRequestDto.getProductPrice());
        product.setProductAvailableQuantity(addProductRequestDto.getProductAvailableQuantity());
        product.setFarmer(farmer);
        productRepository.save(product);
    }

    public List<GetProductResponseDto> getProductbyName(String productName) throws ProductNameDoesNotExistException
    {
        List<Product> productList;
        // first check if product is exist with this name or not
        try
        {
            productList = productRepository.findAllByProductName(productName);
        }
        catch (Exception e)
        {
            throw new ProductNameDoesNotExistException();
        }

        List<GetProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : productList)
        {
            GetProductResponseDto getProductResponseDto = new GetProductResponseDto();
            getProductResponseDto.setProductName(product.getProductName());
            getProductResponseDto.setProductPrice(product.getProductPrice());
            getProductResponseDto.setProductAvailableQuantity(product.getProductAvailableQuantity());

            Farmer ownerOfProduct = product.getFarmer();
            getProductResponseDto.setProductOwnerName(ownerOfProduct.getName());
            getProductResponseDto.setProductOwnerMobNo(ownerOfProduct.getPhoneNo());

            productResponseDtos.add(getProductResponseDto);
        }
        return productResponseDtos;
    }
}
