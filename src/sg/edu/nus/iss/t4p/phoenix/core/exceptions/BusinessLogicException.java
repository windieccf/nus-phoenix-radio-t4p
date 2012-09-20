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
package sg.edu.nus.iss.t4p.phoenix.core.exceptions;

/**
 * Business logic exception. This exception will be thrown if
 * the updating is against the business rule.
 **/
@SuppressWarnings("serial")
public class BusinessLogicException extends Exception{
	/**
	 * Constructor for BusinessLogicException. The input message is returned in
	 * toString() message.
	 */
	public BusinessLogicException(String msg) {
		super(msg);
	}

}
