package tn.esprit.rh.achat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StockServiceTest{

    @InjectMocks
    StockServiceImpl StockService;

    @Mock
    StockRepository StockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Stock Stock1 = new Stock();

        Stock1.setLibelleStock("Prod1");

        Stock Stock2 = new Stock();

        Stock2.setLibelleStock("Prod2");

        // Mock behavior for the repository
        when(StockRepository.findAll()).thenReturn(Arrays.asList(Stock1, Stock2));
        when(StockRepository.save(any(Stock.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllStocks() {
        List<Stock> Stock = StockService.retrieveAllStocks();

        assertEquals(2, Stock.size());
        assertTrue(Stock.stream().anyMatch(d -> d.getLibelleStock().equals("Prod1")));
        assertTrue(Stock.stream().anyMatch(d -> d.getLibelleStock().equals("Prod1")));
    }

}