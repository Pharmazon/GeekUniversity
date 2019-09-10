package ru.shcheglov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Assert;
import org.junit.Test;
import ru.shcheglov.dto.ProductDTO;

import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

public class XmlTest {

    @Test
    public void testToXml() throws JsonProcessingException {
        final ProductDTO dto = new ProductDTO();
        dto.setId("1");
        dto.setName("123");

        final ObjectMapper mapper = new XmlMapper();
        final String prettyXml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        final String uglyXml = mapper.writeValueAsString(dto);
        System.out.println(prettyXml);
        System.out.println(uglyXml);
    }

    @Test
    public void testFromJason() throws IOException {
        final String xml = "<ProductDTO><id>1</id><name>123</name><categoryName/><parentCategoryName/><price/><quantity/><amount/><dateAdded/></ProductDTO>";
        final ObjectMapper mapper = new XmlMapper();
        final ProductDTO dto = mapper.readValue(xml, ProductDTO.class);
        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getId());
        Assert.assertNotNull(dto.getName());
        Assert.assertEquals(dto.getId(), "1");
        Assert.assertEquals(dto.getName(), "123");
    }
}
