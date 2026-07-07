-- 创建过滤字段内容中html标签函数
SET GLOBAL log_bin_trust_function_creators=1;
DROP FUNCTION IF EXISTS fnStripTags;
DELIMITER |
CREATE FUNCTION fnStripTags( Dirty text(0) )
RETURNS text(0)
DETERMINISTIC 
BEGIN
  DECLARE iStart, iEnd, iLength int;
    WHILE Locate( '<', Dirty ) > 0 And Locate( '>', Dirty, Locate( '<', Dirty )) > 0 DO
      BEGIN
        SET iStart = Locate( '<', Dirty ), iEnd = Locate( '>', Dirty, Locate('<', Dirty ));
        SET iLength = ( iEnd - iStart) + 1;
        IF iLength > 0 THEN
          BEGIN
            SET Dirty = Insert( Dirty, iStart, iLength, '');
          END;
        END IF;
      END;
    END WHILE;
    RETURN Dirty;
END;
|
DELIMITER ;

-- 将【课程资料表】中原【课程介绍】字段内容文字信息提取到【课程内容】字段中
update tb_course_material set course_content=fnStripTags(course_presentation);