
DROP TRIGGER USER_TABLE_BEFORE_TRG;
DROP TRIGGER person_BEFORE_TRG;
DROP TRIGGER manager_BEFORE_TRG;
DROP TRIGGER department_BEFORE_TRG;
DROP TRIGGER employee_BEFORE_TRG;

CREATE OR REPLACE TRIGGER USER_TABLE_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON USER_TABLE
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER person_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON person
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER manager_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON manager
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER department_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON department
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER holiday_date_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON holiday_date
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;

CREATE OR REPLACE TRIGGER department_holiday_date_mapping_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON department_holiday_date_mapping
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

/CREATE OR REPLACE TRIGGER employee_BEFORE_TRG
BEFORE INSERT OR UPDATE OR DELETE ON employee
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/