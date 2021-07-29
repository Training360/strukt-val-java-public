package employees;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface IsValidName {

    String message() default "Invalid name";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int minLength() default 0;
}
