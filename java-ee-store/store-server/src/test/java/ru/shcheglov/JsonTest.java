package ru.shcheglov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import ru.shcheglov.dto.ProductDTO;

import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

public class JsonTest {

    @Test
    public void testToJson() throws JsonProcessingException {
        final ProductDTO dto = new ProductDTO();
        dto.setId("1");
        dto.setName("Клюшка хоккейная");
        dto.setPrice(34.12);
        dto.setQuantity(10);

        final ObjectMapper mapper = new ObjectMapper();
        final String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        final String uglyJson = mapper.writeValueAsString(dto);
        System.out.println(prettyJson);
        System.out.println(uglyJson);
    }

    @Test
    public void testFromJason() throws IOException {
        final String json = "{\"id\":\"1\",\"name\":\"Клюшка хоккейная\",\"categoryName\":null,\"parentCategoryName\":null,\"price\":34.12,\"quantity\":10,\"amount\":null,\"dateAdded\":null}";
        final ObjectMapper mapper = new ObjectMapper();
        final ProductDTO dto = mapper.readValue(json, ProductDTO.class);
        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getId());
        Assert.assertNotNull(dto.getName());
        Assert.assertEquals(dto.getId(), "1");
        Assert.assertEquals(dto.getName(), "Клюшка хоккейная");
    }
}
