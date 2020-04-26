package kafka.streams.interactive.query.port;

import kafka.streams.interactive.query.bean.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ProductApiInterface {

    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void addProduct(ProductDTO ProductDTO);

    @RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void deleteProduct(String id);
}

