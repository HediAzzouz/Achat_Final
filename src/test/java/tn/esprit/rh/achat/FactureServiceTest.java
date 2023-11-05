package tn.esprit.rh.achat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FactureServiceTest{

    @InjectMocks
    FactureServiceImpl FactureService;

    @Mock
    FactureRepository FactureRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Facture Facture1 = new Facture();

        Facture1.cancelFacture(1);

        Facture Facture2 = new Facture();

        Facture2.retrieveFacture(2);

        // Mock behavior for the repository
        when(FactureRepository.findAll()).thenReturn(Arrays.asList(Facture1, Facture2));
        when(FactureRepository.save(any(Facture.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllFactures() {
        List<Facture> Facture = FactureService.retrieveAllFactures();

        assertEquals(2, Facture.size());
        assertTrue(Facture.stream().anyMatch(d -> d.cancelFacture().equals(1)));
        assertTrue(Facture.stream().anyMatch(d -> d.retrieveFacture().equals(2)));
    }

}