package co.com.bancolombia.usecase;

import co.com.bancolombia.exceptions.CouponsException;
import co.com.bancolombia.service.ItemsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CouponsSolverUseCaseTest {
    @Mock
    private ItemsService itemsService;

    @InjectMocks
    private CouponsSolverUseCase couponsSolverUseCase;

    @Test
    void testProcessCoupon_Success() {
        when(itemsService.getItemPrice(anyString())).thenReturn(Mono.just(10.0f));

        StepVerifier.create(couponsSolverUseCase.processCoupon(Arrays.asList("item1", "item2"), 20.0f))
                .expectNextMatches(result -> result.getItem_ids().size() == 2 && result.getTotal() == 20.0f)
                .verifyComplete();
    }

    @Test
    void testProcessCoupon_NotFound() {
        when(itemsService.getItemPrice(anyString())).thenReturn(Mono.just(0.0f));

        StepVerifier.create(couponsSolverUseCase.processCoupon(Arrays.asList("item1", "item2"), 20.0f))
                .expectError(CouponsException.class)
                .verify();
    }

    @Test
    void testCalculate() {
        Map<String, Float> items = new HashMap<>();
        items.put("item1", 10.0f);
        items.put("item2", 5.0f);

        StepVerifier.create(CouponsSolverUseCase.calculate(items, 15.0f))
                .expectNextMatches(result -> result.size() == 2 && result.contains("item1") && result.contains("item2"))
                .verifyComplete();
    }

    @Test
    void testCalculate_EmptyResult() {
        Map<String, Float> items = new HashMap<>();
        items.put("item1", 10.0f);
        items.put("item2", 5.0f);

        StepVerifier.create(CouponsSolverUseCase.calculate(items, 5.0f))
                .expectNextMatches(result -> result.size() == 1 && result.contains("item2"))
                .verifyComplete();
    }
}
