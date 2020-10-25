package common;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class Vehicle {

    BigDecimal price;

    LocalDate firstRegistration;
}
