
-- 上课方式编号（0：不在教室 1：在教室上课）
alter table tb_group_member add column learning_method_code int(11)  DEFAULT 1 ;

-- 上课方式名称（0：不在教室 1：在教室上课）
alter table  tb_group_member add column learning_method_name varchar(255)  DEFAULT '正常' ;


-- 上课方式初始化数据默认值触发器
DELIMITER $$

CREATE TRIGGER before_insert_tb_group_member
BEFORE INSERT ON tb_group_member
FOR EACH ROW
BEGIN
    IF NEW.learning_method_code IS NULL THEN
        SET NEW.learning_method_code = 1;
    END IF;
    IF NEW.learning_method_name IS NULL THEN
        SET NEW.learning_method_name = '正常';
    END IF;
END$$

DELIMITER ;