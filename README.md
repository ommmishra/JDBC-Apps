# ReminderApp
A small project for exploring the JAVA DATABASE CONNECTIVITY(JDBC).

This program lets you store your reminders in a database through the use of JAVA, although its a very crude program it will be useful
in learning the basics of JAVA DATABASE CONNECTIVITY.Before you start with this program you must have a brief understanding of database
and SQL(Structured Query Language).

**The featured program works specifically with MySQL so make sure you are well versed in the SQL clauses.**

First of all the things you require: JDK(Java Dvelopment Kit)(Obviously as you are running java programs), A suitable IDE or Vim is the best choice if you are used to it, MySQL installed in your computer, A suitable JDBC Driver, since we are using MySQL we will use ConnectorJ.


*************

In line 18:
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database-name", "username", "password");
            
          Change database-name to the database ypu will be connecting to, change username and password to the username and password used
          by you to access your local MySQL server.
      
 In line 36, 45, 56, 78, 96, 115:
                                 
           Change table-name to the name of the table you will be using in the program present in the database.

*************
NOTE:
THE TABLE AND DATABASE HAVE TO BE CREATED SEPARATELY EITHER BY USING JAVA(a database creatiion program file has been include for those who wannt to try) OR DIRECTLY THROUGH MyQL USING SUITABLE METHOD.
WHILE DOWNLOADING ConnectorJ MAKE SURE YOU ARE DOWNLOADING THE COREECT AND UPTODATE VERSION OF IT(Links will be provided below).

Links for ConnectorJ:
(make sure you download the platform indepednt one while downloading for WINDOWS and macOS, all other versions for different OSs' are provided explicitly)
https://dev.mysql.com/downloads/connector/j/

