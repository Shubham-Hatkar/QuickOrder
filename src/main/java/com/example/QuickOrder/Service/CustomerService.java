package com.example.QuickOrder.Service;

import com.example.QuickOrder.DTO.AddCustomerRequestDto;
import com.example.QuickOrder.DTO.AddProductIntoCartRequestDto;
import com.example.QuickOrder.DTO.GetProductResponseDto;
import com.example.QuickOrder.DTO.RemoveProductFromCartDto;
import com.example.QuickOrder.Entity.Customer;
import com.example.QuickOrder.Entity.CustomerCart;
import com.example.QuickOrder.Entity.Product;
import com.example.QuickOrder.Exceptions.CustomerEmailAlreadyExistException;
import com.example.QuickOrder.Exceptions.CustomerIdInvalidException;
import com.example.QuickOrder.Exceptions.ProductIdInvalidException;
import com.example.QuickOrder.Repository.CustomerRepository;
import com.example.QuickOrder.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService
{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public void addCustomer(AddCustomerRequestDto addCustomerRequestDto) throws CustomerEmailAlreadyExistException {
        try
        {
            Customer customer = new Customer();
            customer.setCustomerAddress(addCustomerRequestDto.getCustomerAddress());
            customer.setCustomerEmail(addCustomerRequestDto.getCustomerEmail());
            customer.setCustomerAge(addCustomerRequestDto.getCustomerAge());
            customer.setCustomerName(addCustomerRequestDto.getCustomerName());

            // set customer for cart
            CustomerCart customerCart = new CustomerCart();
            customerCart.setCustomer(customer);

            customer.setCustomerCart(customerCart);

            customerRepository.save(customer);
        }
        catch (Exception e)
        {
            throw new CustomerEmailAlreadyExistException();
        }
    }

    public void addToCart(AddProductIntoCartRequestDto addProductIntoCartRequestDto) throws ProductIdInvalidException, CustomerIdInvalidException {
        Product product;
        try
        {
            product = productRepository.findById(addProductIntoCartRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductIdInvalidException();
        }

        Customer customer;
        try
        {
            customer = customerRepository.findById(addProductIntoCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerIdInvalidException();
        }
        customerRepository.findById(addProductIntoCartRequestDto.getCustomerId()).get().getCustomerCart().getProductList().add(product);
        //CustomerCart customerCart = customerRepository.findById(addProductIntoCartRequestDto.getCustomerId()).get().getCustomerCart();
//        System.out.println("***0 = " + customerCart.getProductList().size());
//        customerCart.getProductList().add(product);
//        System.out.println("***1 = " + customerCart.getProductList().size());
//        for(Product product1 : customerCart.getProductList())
//        {
//            System.out.println(product1.getProductId());
//        }
//        customer.setCustomerCart(customerCart);

        //customerRepository.save(customer);
        //System.out.println("*** " + customer.getCustomerCart().getProductList().size());

        product.setCustomerCart(customer.getCustomerCart());
        customerRepository.save(customer);
    }

    public void removeFromCart(RemoveProductFromCartDto removeProductFromCartDto) throws ProductIdInvalidException, CustomerIdInvalidException
    {
        // check productId and customerId is valid or not
        Product product;
        try
        {
            product = productRepository.findById(removeProductFromCartDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductIdInvalidException();
        }

        Customer customer;
        try
        {
            customer = customerRepository.findById(removeProductFromCartDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerIdInvalidException();
        }

        CustomerCart customerCart = customer.getCustomerCart();
        customerCart.getProductList().remove(product);

        product.setCustomerCart(null);
        customerRepository.save(customer);
    }

    public List<GetProductResponseDto> showCart(int customerId) throws CustomerIdInvalidException {
        //System.out.println("****1");
        Customer customer;
        try
        {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e)
        {
            throw new CustomerIdInvalidException();
        }
        List<Product> productList = customer.getCustomerCart().getProductList();
        //System.out.println("****2" + " " + productList.size() + "**");
        List<GetProductResponseDto> ans = new ArrayList<>();

        for(Product product : productList)
        {

            GetProductResponseDto getProductResponseDto = new GetProductResponseDto();
            getProductResponseDto.setProductPrice(product.getProductPrice());
            getProductResponseDto.setProductName(product.getProductName());
            getProductResponseDto.setProductAvailableQuantity(product.getProductAvailableQuantity());
            getProductResponseDto.setProductOwnerName(product.getFarmer().getName());
            getProductResponseDto.setProductOwnerMobNo(product.getFarmer().getPhoneNo());

            ans.add(getProductResponseDto);
        }
        return ans;
    }


}
