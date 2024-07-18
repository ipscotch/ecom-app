package com.example.cartservice;

import com.example.cartservice.controller.CartController;
import com.example.cartservice.model.Cart;
import com.example.cartservice.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart("1");
        cart.getItems().put("item1", 2);
    }

    @Test
    public void testGetCartById() throws Exception {
        Mockito.when(cartService.getCartById("1")).thenReturn(cart);

        mockMvc.perform(MockMvcRequestBuilders.get("/carts/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.items.item1", is(2)));
    }

    @Test
    public void testAddItemToCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/carts/1/items/item2")
                .param("quantity", "3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(cartService).addItemToCart("1", "item2", 3);
    }

    @Test
    public void testRemoveItemFromCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/carts/1/items/item1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(cartService).removeItemFromCart("1", "item1");
    }
}
