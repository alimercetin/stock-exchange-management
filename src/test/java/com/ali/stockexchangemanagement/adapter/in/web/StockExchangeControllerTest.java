package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.model.AddStockForStockExchangeRequest;
import com.ali.stockexchangemanagement.application.domain.model.StockExchange;
import com.ali.stockexchangemanagement.application.port.in.AddStockForStockExchangeUseCase;
import com.ali.stockexchangemanagement.application.port.in.GetStockExchangeUseCase;
import com.ali.stockexchangemanagement.application.port.in.RemoveStockFromStockExchangeUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.ali.stockexchangemanagement.common.StockExchangeTestData.savedStockExchange;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockExchangeController.class)
class StockExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetStockExchangeUseCase getStockExchangeUseCase;

    @MockBean
    private AddStockForStockExchangeUseCase addStockForStockExchangeUseCase;

    @MockBean
    private RemoveStockFromStockExchangeUseCase removeStockFromStockExchangeUseCase;

    @Test
    void testGetStockExchange() throws Exception {
        StockExchange stockExchange = savedStockExchange();

        given(getStockExchangeUseCase.getStockExchange(eq(stockExchange.getName())))
                .willReturn(stockExchange);

        mockMvc.perform(get("/api/v1/stock-exchanges/{name}", stockExchange.getName())
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(stockExchange)));
    }

    @Test
    void testRemoveStockFromStockExchange() throws Exception {
        Long stockId = 1L;
        String stockExchangeName = "XAMS";
        mockMvc.perform(delete("/api/v1/stock-exchanges/{name}/stock/{stock-id}", stockExchangeName, stockId)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isNoContent());

        then(removeStockFromStockExchangeUseCase).should()
                .removeStock(eq(stockExchangeName), eq(stockId));
    }

    @Test
    void testAddStockForStockExchange() throws Exception {
        AddStockForStockExchangeRequest request = new AddStockForStockExchangeRequest(1L);
        String stockExchangeName = "XAMS";


        mockMvc.perform(post("/api/v1/stock-exchanges/{name}/stock", stockExchangeName)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());

        then(addStockForStockExchangeUseCase).should()
                .addStock(eq(stockExchangeName), eq(request.getStockId()));
    }

}
