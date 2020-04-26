package kafka.streams.interactive.query.api;


import kafka.streams.interactive.query.bean.ProductDTO;
import kafka.streams.interactive.query.port.ProductApiInterface;
import kafka.streams.interactive.query.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/")
public class ProductApi implements ProductApiInterface {

    @Autowired
    private ProductService productService;

    @Override
    public void addProduct(@RequestParam("name") String name, @RequestParam("brand") String brand, @RequestParam("price") Long price  ) {
        ProductDTO productDTO = new ProductDTO(name, brand, price);
        productService.addProduct(productDTO);
    }

    @Override
    public void deleteProduct(String id) {productService.deleteProduct(id); }


}
