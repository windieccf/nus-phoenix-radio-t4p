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
 * 28 Sep 2012    	Team 4		    0.1				Initial creating
 * 													
 * 													
 * 
 */
 
 1. Database connection info can be retrieved from DBConstants.java
	COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	dbUrl = "jdbc:mysql://localhost:3306/phoenix";
	dbUserName = "root";
	dbPassword = "password";
 2. Please create table and insert data from sqlscript.sql
 3. Available users for login:
 (1)
 Username: admin
 password: password
 Role: admin
 (2)
 Username: manager
 password: password
 Role: manager
 (3)
 Username: presenter
 password: password
 Role: presenter
 (4)
 Username: producer
 password: password
 Role: producer
 Please get more user details info from database table: USER and USER_ROLE
 4. Please kindly contact below engineers if you get any issues related to this application:
 Chen Changfeng @ 9005 1315
 Robin Foe @ 9115 7054