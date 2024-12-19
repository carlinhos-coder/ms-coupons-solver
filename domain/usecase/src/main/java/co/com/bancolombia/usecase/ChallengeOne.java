package co.com.bancolombia.usecase;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static co.com.bancolombia.usecase.CouponsSolverUseCase.calculate;

public class ChallengeOne {
    public static void main(String[] args) {
        Map<String, Float> items = Map.of(
                "MLA1", 100f,
                "MLA2", 210f,
                "MLA3", 220f,
                "MLA4", 80f,
                "MLA5", 90f
        );
        Float amount = 500f;

        Mono<List<String>> result = calculate(items, amount);
        System.out.println(result.block());
    }
}
