package sg.edu.nus.iss.t4p.phoenix.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface JoinTable {
	public enum TYPE{ONE_TO_MANY, MANY_TO_ONE, MANY_TO_MANY}
	String tableName() default "";
	String columnName() default "";
	String referenceColumnName() default "";
	JoinTable.TYPE columnType() default JoinTable.TYPE.MANY_TO_ONE;

}
