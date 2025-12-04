package com.homepulse.controller;

import com.homepulse.dto.ProductDetailDto;
import com.homepulse.dto.ProductDto;
import com.homepulse.service.ProductService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import java.util.List;

@Path("/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GET
    @Path("/{id}")
    public ProductDetailDto getProductDetail(@PathParam("id") Long id) {
        return productService.getDetail(id);
    }


    @DELETE
    @Path("/{id}")
    public boolean deleteProduct(@PathParam("id") Long id) {
        productService.deleteProduct(id);
        return true;
    }
}