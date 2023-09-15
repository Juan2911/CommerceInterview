package com.example.interview.processors;

import com.example.interview.Utils.TestObjectBuilder;
import com.example.interview.domain.CartDomain;
import com.example.interview.entities.Cart;
import com.example.interview.processors.impl.AddProductCommerceProcessorImpl;
import com.example.interview.services.mappers.CommerceMapper;
import com.example.interview.services.persistences.DatabaseService;
import com.example.interview.services.validation.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddProductCommerceProcessorImplTest {

    private AddProductCommerceProcessorImpl addProductCommerceProcessor;

    @Mock
    private ValidationService addProductsValidationServiceMock;

    @Mock
    private DatabaseService databaseServiceMock;

    @Mock
    private CommerceMapper cartMapperMock;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        Cart cartSaved = TestObjectBuilder.buildCartSavedDatabase();
        Cart cartWithProducts = TestObjectBuilder.buildCartWithProductsEntity();

        addProductCommerceProcessor = new AddProductCommerceProcessorImpl(addProductsValidationServiceMock, databaseServiceMock, cartMapperMock);
        when(databaseServiceMock.getCartByCartId(1)).thenReturn(cartSaved);
        when(cartMapperMock.mapToEntityFromDomain(any())).thenReturn(cartWithProducts);
    }

    @Test
    public void shouldSaveACartWithProductsSuccessful() {
        CartDomain cartDomain = TestObjectBuilder.buildCartWithProductsDomain();
        addProductCommerceProcessor.process(cartDomain);
        verify(databaseServiceMock, times(1)).saveCart(any());
    }
}
