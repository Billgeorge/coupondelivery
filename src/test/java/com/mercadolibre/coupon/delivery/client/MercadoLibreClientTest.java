package com.mercadolibre.coupon.delivery.client;

import com.mercadolibre.coupon.delivery.exception.ClientException;
import com.mercadolibre.coupon.delivery.model.Product;
import com.mercadolibre.coupon.delivery.model.SearchProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mercadolibre.coupon.delivery.client.MercadoLibreClient.ITEM_URL;
import static org.junit.jupiter.api.Assertions.*;

class MercadoLibreClientTest {

    private MercadoLibreClient  mercadoLibreClient;

    @BeforeEach
    void setUp() {
        mercadoLibreClient= new MercadoLibreClient();
    }

    @Test
    public void getItemThatExistsTest(){
        try {
            String resourceProductUrl="sites/MLA/search?q=Motorola%20G6%20Samsung%20Galaxy%20S8";
            SearchProductResponse searchProductResponse= mercadoLibreClient
                    .executeGet(resourceProductUrl,SearchProductResponse.class);

            if(searchProductResponse==null ||
                    searchProductResponse.getResults()==null || searchProductResponse.getResults().isEmpty()){
                fail("Search of items does not return results");
            }
            String itemId = searchProductResponse.getResults().get(0).getId();
            String resourceUrl = ITEM_URL+itemId;
            Product product = mercadoLibreClient.executeGet(resourceUrl,Product.class);
            assertEquals(itemId,product.getId());
        } catch (ClientException e) {
            fail();
        }
    }
    @Test
    public void getItemNotFoundTest(){
        try {
            String itemId = "items/MLA8344525101";
            mercadoLibreClient.executeGet(itemId,Product.class);
        } catch (ClientException e) {
            assertEquals("404 Not Found: [{\"message\":\"Item with id MLA8344525101 not found\",\"error\":\"not_found\",\"status\":404,\"cause\":[]}]",e.getCause().getMessage());
        }
    }

}