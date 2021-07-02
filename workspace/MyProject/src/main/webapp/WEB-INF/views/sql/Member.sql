DROP TABLE MEMBER;

CREATE TABLE MEMBER
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(32) NOT NULL UNIQUE,
	PW VARCHAR2(32) NOT NULL,
	NAME VARCHAR2(32) NOT NULL,
	PHONE VARCHAR2(32) NOT NULL UNIQUE,
	EMAIL VARCHAR2(64) NOT NULL,
	ADDRESS VARCHAR2(64),
	REGDATE DATE,
	STATUS NUMBER
);

DROP SEQUENCE MEMBER_SEQ;
CREATE SEQUENCE MEMBER_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;