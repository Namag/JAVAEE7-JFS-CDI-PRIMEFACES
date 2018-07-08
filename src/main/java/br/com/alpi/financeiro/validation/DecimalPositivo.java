package br.com.alpi.financeiro.validation;


import java.lang.annotation.Target;


import java.lang.annotation.*;



import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotNull
@DecimalMin("0")
public @interface DecimalPositivo {
	
@OverridesAttribute(constraint = DecimalMin.class, name = "message")
String message() default "{br.com.alpi.NumeroDecimal.message}";
Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default {};
}
