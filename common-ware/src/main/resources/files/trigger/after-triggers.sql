
DROP TRIGGER USER_TABLE_AFTER_TRG;
DROP TRIGGER person_AFTER_TRG;
DROP TRIGGER manager_AFTER_TRG;
DROP TRIGGER department_AFTER_TRG;
DROP TRIGGER employee_AFTER_TRG;

CREATE OR REPLACE TRIGGER USER_TABLE_AFTER_TRG
AFTER INSERT OR UPDATE OR DELETE ON USER_TABLE
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER person_AFTER_TRG
AFTER INSERT OR UPDATE OR DELETE ON person
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER manager_AFTER_TRG
AFTER INSERT OR UPDATE OR DELETE ON manager
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER department_AFTER_TRG
AFTER INSERT OR UPDATE OR DELETE ON department
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER employee_AFTER_TRG
AFTER INSERT OR UPDATE OR DELETE ON employee
FOR EACH ROW
BEGIN
	:NEW.MODIFIED_DATE := SYSDATE;
END;
/