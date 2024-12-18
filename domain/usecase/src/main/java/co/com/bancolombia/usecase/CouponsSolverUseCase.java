package co.com.bancolombia.usecase;

import co.com.bancolombia.model.CouponsResult;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CouponsSolverUseCase {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Mono<CouponsResult> processCoupon(List<String> itemIds, Float amount) {
        logger.log(Level.INFO, "Start CouponsSolverUseCase.processCoupon(): {0}", itemIds);
        return Mono.just(CouponsResult.builder()
                .item_ids(itemIds)
                .total(itemIds.size() * amount)
                .build());
    }

}
