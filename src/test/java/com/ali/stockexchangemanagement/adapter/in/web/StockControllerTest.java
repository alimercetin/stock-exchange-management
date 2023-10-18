package com.ali.stockexchangemanagement.adapter.in.web;

import com.ali.stockexchangemanagement.adapter.in.web.model.CreateStockRequest;
import com.ali.stockexchangemanagement.adapter.in.web.model.UpdateStockPriceRequest;
import com.ali.stockexchangemanagement.application.port.in.CreateStockUseCase;
import com.ali.stockexchangemanagement.application.port.in.DeleteStockUseCase;
import com.ali.stockexchangemanagement.application.port.in.UpdateStockPriceUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.ali.stockexchangemanagement.common.StockTestData.defaultCreateStockRequest;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateStockUseCase createStockUseCase;

    @MockBean
    private DeleteStockUseCase deleteStockUseCase;

    @MockBean
    private UpdateStockPriceUseCase updateStockPriceUseCase;

    @Test
    void testCreateStock() throws Exception {
        CreateStockRequest request = defaultCreateStockRequest();

        mockMvc.perform(post("/api/v1/stocks")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        then(createStockUseCase).should()
                .createStock(any());
    }

    @Test
    void testDeleteStock() throws Exception {
        Long stockId = 1L;
        mockMvc.perform(delete("/api/v1/stocks/{id}", stockId)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isNoContent());

        then(deleteStockUseCase).should()
                .deleteStock(eq(stockId));
    }

    @Test
    void testUpdateStockPrice() throws Exception {
        UpdateStockPriceRequest request = new UpdateStockPriceRequest(BigDecimal.valueOf(987L));
        Long stockId = 1L;
        mockMvc.perform(put("/api/v1/stocks/{id}/price", stockId)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());

        then(updateStockPriceUseCase).should()
                .updateStockPrice(eq(stockId), eq(request.getNewPrice()));
    }

}
