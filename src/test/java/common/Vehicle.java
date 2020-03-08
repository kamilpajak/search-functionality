package common;

import com.google.auto.value.AutoValue;

import java.math.BigDecimal;
import java.time.LocalDate;

@AutoValue
public abstract class Vehicle {

    public abstract BigDecimal price();

    public abstract LocalDate firstRegistration();

    public static Builder builder() {
        return new AutoValue_Vehicle.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder price(BigDecimal price);

        public abstract Builder firstRegistration(LocalDate firstRegistration);

        public abstract Vehicle build();
    }
}
