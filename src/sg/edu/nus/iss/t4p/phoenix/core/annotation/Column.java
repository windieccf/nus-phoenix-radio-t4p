package sg.edu.nus.iss.t4p.phoenix.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface Column {
	public enum TYPE{PRIMARY, ORDINARY,FOREIGN}
	String name() default "";
	Column.TYPE columnType() default Column.TYPE.ORDINARY;
}

