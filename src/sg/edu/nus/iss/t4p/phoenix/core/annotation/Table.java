/*
 * CONFIDENTIAL AND PROPRIETARY SOURCE CODE OF
 * Institute of Systems Science, National University of Singapore
 *
 * Copyright 2012 Team 4(Part-Time), ISS, NUS, Singapore. All rights reserved.
 * Use of this source code is subjected to the terms of the applicable license
 * agreement.
 *
 * -----------------------------------------------------------------
 * REVISION HISTORY
 * -----------------------------------------------------------------
 * DATE             AUTHOR          REVISION		DESCRIPTION
 * 15 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */
package sg.edu.nus.iss.t4p.phoenix.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for DB Table
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE}) 
public @interface Table {
	String name() default "";
	String schema() default "";
}
