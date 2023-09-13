
DROP TABLE kafka_consumer_message;

CREATE TABLE kafka_consumer_message (
  	 id 				INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  	 name	 			VARCHAR2(25),
     topic_num			VARCHAR2(25),
     group_num			VARCHAR2(25),
     message			VARCHAR2(250),
     partition_num		Integer,
     offset_num			Integer,
     modified_by		VARCHAR2(50),
     created_date   	TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
);


DROP TABLE kafka_consumer_message_3;

CREATE TABLE kafka_consumer_message_3 (
  	 id 				INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  	 name	 			VARCHAR2(25),
     topic_num			VARCHAR2(25),
     group_num			VARCHAR2(25),
     message			VARCHAR2(250),
     partition_num		VARCHAR2(25),
     offset_num			Integer,
     modified_by		VARCHAR2(50),
     created_date   	TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
     modified_date  TIMESTAMP(0)
);



CREATE OR REPLACE TRIGGER kafka_consumer_message_3_BEFORE_TRG
BEFORE UPDATE ON kafka_consumer_message_3
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/