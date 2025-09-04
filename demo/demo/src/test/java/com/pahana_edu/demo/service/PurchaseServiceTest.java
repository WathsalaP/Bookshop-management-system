/*package com.pahana_edu.demo.service;

import com.pahana_edu.demo.dto.ItemDTO;
import com.pahana_edu.demo.dto.PurchaseDTO;
import com.pahana_edu.demo.model.ItemModel;
import com.pahana_edu.demo.model.PurchaseModel;
import com.pahana_edu.demo.repo.ItemRepo;
import com.pahana_edu.demo.repo.PurchaseRepo;
import com.pahana_edu.demo.service.ITemService;
import com.pahana_edu.demo.service.PurchaseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepo purchaseRepo;

    @Mock
    private ItemRepo itemRepo;

    @Mock
    private ITemService itemService;

    @Mock
    private ModelMapper modelMapper;

    private PurchaseDTO purchaseDTO;
    private PurchaseModel purchaseModel;
    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        purchaseDTO = new PurchaseDTO(
                null,
                "PRCHS1001",
                "C1",
                "I1",
                "Book",
                BigDecimal.valueOf(100),
                2,
                BigDecimal.ZERO,
                BigDecimal.valueOf(200),
                null);

        purchaseModel = new PurchaseModel(
                1L,
                "PRCHS1001",
                "C1",
                "I1",
                "Book",
                BigDecimal.valueOf(100),
                2,
                BigDecimal.ZERO,
                BigDecimal.valueOf(200),
                null);

        itemDTO = new ItemDTO("I1", "Book", BigDecimal.valueOf(100), "10");
    }

    @Test
    void testSavePurchaseAndCalculate_NewItem() {
        when(itemService.saveItem(any(ItemDTO.class))).thenReturn(itemDTO);
        when(modelMapper.map(any(PurchaseDTO.class), eq(PurchaseModel.class))).thenReturn(purchaseModel);
        when(purchaseRepo.save(any(PurchaseModel.class))).thenReturn(purchaseModel);

        String purchaseCode = purchaseService.savePurchaseAndCalculate(
                "C1",
                BigDecimal.ZERO,
                Arrays.asList(purchaseDTO));

        assertNotNull(purchaseCode);
        verify(purchaseRepo, times(1)).save(any(PurchaseModel.class));
    }

    @Test
    void testSavePurchaseAndCalculate_ExistingItem() {
        purchaseDTO.setItemCode("I1");
        when(itemRepo.findById("I1"))
                .thenReturn(Optional.of(new ItemModel("I1", "Book", BigDecimal.valueOf(100), "10")));
        when(modelMapper.map(any(PurchaseDTO.class), eq(PurchaseModel.class))).thenReturn(purchaseModel);

        purchaseService.savePurchaseAndCalculate("C1", BigDecimal.ZERO, Arrays.asList(purchaseDTO));

        verify(purchaseRepo, times(1)).save(any(PurchaseModel.class));
    }

    @Test
    void testGetPurchaseDetailsByCode() {
        when(purchaseRepo.findByPurchaseCode("PRCHS1001")).thenReturn(Arrays.asList(purchaseModel));
        when(modelMapper.map(any(PurchaseModel.class), eq(PurchaseDTO.class))).thenReturn(purchaseDTO);

        List<PurchaseDTO> result = purchaseService.getPurchaseDetailsByCode("PRCHS1001");

        assertEquals(1, result.size());
        assertEquals("Book", result.get(0).getItemName());
    }

    @Test
    void testCalculateTotal_WithDiscount() {
        List<PurchaseDTO> purchases = Arrays.asList(purchaseDTO);
        BigDecimal total = purchaseService.calculateTotal(purchases, BigDecimal.valueOf(10));

        assertEquals(BigDecimal.valueOf(180), total); // 200 - 10% discount
    }

    @Test
    void testCalculateTotal_NoDiscount() {
        List<PurchaseDTO> purchases = Arrays.asList(purchaseDTO);
        BigDecimal total = purchaseService.calculateTotal(purchases, BigDecimal.ZERO);

        assertEquals(BigDecimal.valueOf(200), total);
    }
}*/

package com.pahana_edu.demo.service;

import com.pahana_edu.demo.dto.ItemDTO;
import com.pahana_edu.demo.dto.PurchaseDTO;
import com.pahana_edu.demo.model.ItemModel;
import com.pahana_edu.demo.model.PurchaseModel;
import com.pahana_edu.demo.repo.ItemRepo;
import com.pahana_edu.demo.repo.PurchaseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepo purchaseRepo;

    @Mock
    private ItemRepo itemRepo;

    @Mock
    private ITemService itemService;

    @Mock
    private ModelMapper modelMapper;

    private PurchaseDTO purchaseDTO;
    private PurchaseModel purchaseModel;
    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a valid PurchaseDTO
        purchaseDTO = new PurchaseDTO(
                null, "PRCHS1001", "1001", "ITEM0004", "Book",
                BigDecimal.valueOf(100), 2, BigDecimal.ZERO, BigDecimal.valueOf(200), null);

        // Initialize a corresponding PurchaseModel
        purchaseModel = new PurchaseModel(
                null, "PRCHS1001", "1001", "ITEM0004", "Book",
                BigDecimal.valueOf(100), 2, BigDecimal.ZERO, BigDecimal.valueOf(200), null);

        // Initialize a valid ItemDTO
        itemDTO = new ItemDTO("ITEM0004", "Book", BigDecimal.valueOf(100), "10");
    }

    @Test
    void testSavePurchaseAndCalculate_NewItem() {
        // Simulate saving a new item
        purchaseDTO.setItemCode(null); // No item code, triggers item creation
        when(itemService.saveItem(any(ItemDTO.class))).thenReturn(itemDTO);
        when(modelMapper.map(any(PurchaseDTO.class), eq(PurchaseModel.class))).thenReturn(purchaseModel);
        when(purchaseRepo.save(any(PurchaseModel.class))).thenReturn(purchaseModel);

        String purchaseCode = purchaseService.savePurchaseAndCalculate(
                "1001", BigDecimal.ZERO, Arrays.asList(purchaseDTO));

        assertNotNull(purchaseCode);
        verify(itemService, times(1)).saveItem(any(ItemDTO.class));
        verify(purchaseRepo, times(1)).save(any(PurchaseModel.class));
    }

    @Test
    void testSavePurchaseAndCalculate_ExistingItem() {
        // Existing item in DB
        purchaseDTO.setItemCode("ITEM0004");
        when(itemRepo.findById("ITEM0004")).thenReturn(
                Optional.of(new ItemModel("ITEM0004", "Book", BigDecimal.valueOf(100), "10")));
        when(modelMapper.map(any(PurchaseDTO.class), eq(PurchaseModel.class))).thenReturn(purchaseModel);
        when(purchaseRepo.save(any(PurchaseModel.class))).thenReturn(purchaseModel);

        String purchaseCode = purchaseService.savePurchaseAndCalculate(
                "1001", BigDecimal.ZERO, Arrays.asList(purchaseDTO));

        assertNotNull(purchaseCode);
        verify(itemRepo, times(1)).findById("ITEM0004");
        verify(purchaseRepo, times(1)).save(any(PurchaseModel.class));
    }

    @Test
    void testGetPurchaseDetailsByCode() {
        when(purchaseRepo.findByPurchaseCode("PRCHS1001")).thenReturn(Arrays.asList(purchaseModel));
        when(modelMapper.map(any(PurchaseModel.class), eq(PurchaseDTO.class))).thenReturn(purchaseDTO);

        List<PurchaseDTO> result = purchaseService.getPurchaseDetailsByCode("PRCHS1001");

        assertEquals(1, result.size());
        assertEquals("Book", result.get(0).getItemName());
    }

    @Test
    void testCalculateTotal_WithDiscount() {
        List<PurchaseDTO> purchases = Arrays.asList(purchaseDTO);
        BigDecimal total = purchaseService.calculateTotal(purchases, BigDecimal.valueOf(10));
        assertEquals(BigDecimal.valueOf(180).setScale(2), total.setScale(2)); // 200 - 10%
    }

    @Test
    void testCalculateTotal_NoDiscount() {
        List<PurchaseDTO> purchases = Arrays.asList(purchaseDTO);
        BigDecimal total = purchaseService.calculateTotal(purchases, BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(200).setScale(2), total.setScale(2));
    }
}
