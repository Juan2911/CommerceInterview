package com.example.interview.processors;

import com.example.interview.Utils.TestObjectBuilder;
import com.example.interview.domain.ModifyQuantityDomain;
import com.example.interview.entities.Cart;
import com.example.interview.processors.impl.ModifyQuantityProcessor;
import com.example.interview.services.mappers.CommerceMapper;
import com.example.interview.services.persistences.DatabaseService;
import com.example.interview.services.validation.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ModifyQuantityCommerceProcessorImplTest {

    private CommerceProcessor modifyQuantityProcessor;

    @Mock
    private ValidationService modifyQuantityValidationSerivceImplMock;

    @Mock
    private DatabaseService databaseServiceImplMock;

    @Mock
    private CommerceMapper cartMapperMock;

    private Cart cartWithProducts;
    private Cart cartWithQuantityChanged;
    private ModifyQuantityDomain modifyQuantityDomain;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        cartWithProducts = TestObjectBuilder.buildCartWithProductsEntity();
        cartWithQuantityChanged = TestObjectBuilder.buildCartWithProductsEntityModifiedQuantity();
        modifyQuantityDomain = TestObjectBuilder.buildModifyQuantityDomain();
        modifyQuantityProcessor = new ModifyQuantityProcessor(modifyQuantityValidationSerivceImplMock, databaseServiceImplMock, cartMapperMock);

        when(databaseServiceImplMock.getCartByCartId(1)).thenReturn(cartWithProducts);

    }

    @Test
    public void shouldSaveACartWithProductsAndQuantityModifiedSuccessful() {
        modifyQuantityProcessor.process(modifyQuantityDomain);
        verify(databaseServiceImplMock, times(1)).saveCart(cartWithQuantityChanged);
    }
}
