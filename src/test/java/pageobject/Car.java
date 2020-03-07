package pageobject;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Car {

    public abstract String firstRegistration();

    public static Builder builder() {
        return new AutoValue_Car.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder firstRegistration(String firstRegistration);

        public abstract Car build();
    }
}
