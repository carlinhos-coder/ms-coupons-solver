package co.com.bancolombia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CouponsPetition {
    private List<String> item_ids;
    private float amount;
}
