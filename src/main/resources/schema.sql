
CREATE TABLE TASK
(
	ID NUMBER NOT NULL ,
	NAME VARCHAR2(100 BYTE) NOT NULL ,
	STARTDATE     DATE NOT NULL ,
	VERSION      NUMBER(12) NOT NULL ,
    CREATIONTIME DATE ,
    LASTUPDATETIME DATE 
);