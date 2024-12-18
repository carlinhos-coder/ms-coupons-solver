package co.com.bancolombia.api.command;

import co.com.bancolombia.api.dto.CouponsResultDTO;
import co.com.bancolombia.model.CouponsRequest;
import co.com.bancolombia.usecase.CouponsSolverUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/coupon/")
@AllArgsConstructor
public class CouponsSolverCommand {
    private final CouponsSolverUseCase couponsSolverUseCase;
    private ObjectMapper objectMapper;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<CouponsResultDTO> processCoupon(@RequestBody CouponsRequest couponsRequest) {
        return couponsSolverUseCase.processCoupon(couponsRequest.getItem_ids(), couponsRequest.getAmount())
                .map(couponsResult -> objectMapper.convertValue(couponsResult, CouponsResultDTO.class));
    }
}
