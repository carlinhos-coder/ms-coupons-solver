package co.com.bancolombia.usecase;

import co.com.bancolombia.enums.CouponsEnum;
import co.com.bancolombia.enums.CouponsErrorEnums;
import co.com.bancolombia.exceptions.CouponsException;
import co.com.bancolombia.model.CouponsResult;
import co.com.bancolombia.model.Items;
import co.com.bancolombia.service.ItemsService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CouponsSolverUseCase {
    private final ItemsService itemsService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Mono<CouponsResult> processCoupon(List<String> itemIds, Float amount) {
        logger.log(Level.INFO, "Start CouponsSolverUseCase.processCoupon(): {0}", itemIds);
        return Flux.fromIterable(itemIds)
                .flatMap(itemId -> itemsService.getItemPrice(itemId)
                        .map(price -> Map.entry(itemId, price))
                        .filterWhen(entry -> Mono.just(entry.getValue() > CouponsEnum.ZERO.getIntValue())))
                .collectMap(Map.Entry::getKey, Map.Entry::getValue)
                .flatMap(stringFloatMap -> calculate(stringFloatMap, amount)
                        .filter(selectedItems -> !selectedItems.isEmpty())
                        .switchIfEmpty(Mono.error(new CouponsException(CouponsErrorEnums.NOT_FOUND)))
                        .flatMap(selectedItems -> {
                            float total = selectedItems.stream()
                                    .map(stringFloatMap::get)
                                    .reduce(CouponsEnum.ZERO_FLOAT.getFloatValue(), Float::sum);
                            return Mono.just(new CouponsResult(selectedItems, total));
                        }));
    }

    public static Mono<List<String>> calculate(Map<String, Float> items, Float amount) {
        return Flux.fromIterable(items.entrySet())
                .map(entry -> new Items(entry.getKey(), entry.getValue()))
                .sort((item1, item2) -> Float.compare(item2.getPrice(), item1.getPrice()))
                .collectList()
                .flatMap(itemList -> {
                    List<String> result = new ArrayList<>();
                    float total = 0;

                    for (Items item : itemList) {
                        if (total + item.getPrice() <= amount) {
                            total += item.getPrice();
                            result.add(item.getId());
                        }
                    }
                    return Mono.just(result);
                });
    }

}
