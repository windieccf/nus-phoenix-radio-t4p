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
 * NotFoundException exception. This exception will be thrown from Dao object if
 * load, update or delete for one object fails to find the correct row.
 **/

public class NotFoundException extends Exception {
	/**
	 * For eclipse based unique identity
	 */
	private static final long serialVersionUID = -8937329631346507674L;

	/**
	 * Constructor for NotFoundException. The input message is returned in
	 * toString() message.
	 */
	public NotFoundException(String msg) {
		super(msg);
	}

}
