package com.homepulse.backend.resource;

import com.homepulse.backend.dto.ProductDto;
import com.homepulse.backend.dto.ProductRequest;
import com.homepulse.backend.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    public List<ProductDto> getAllProducts() {
        List<Product> products = entityManager.createQuery("select p from Product p", Product.class).getResultList();
        return products.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImages()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImages())).build();
    }

    @POST
    @Transactional
    public Response createProduct(ProductRequest request) {
        if (request == null || request.getName() == null || request.getPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImages(request.getImages());
        entityManager.persist(product);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateProduct(@PathParam("id") Long id, ProductRequest request) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getImages() != null) {
            product.setImages(request.getImages());
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(product);
        return Response.noContent().build();
    }
}
