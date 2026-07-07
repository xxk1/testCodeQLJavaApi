-- 查询需要修改数据库表的引擎的语句，自行修改需要查询的库名
SELECT
    CONCAT( 'ALTER TABLE ' ,TABLE_NAME ,' ENGINE=InnoDB; ')
FROM information_schema.TABLES AS t
WHERE TABLE_SCHEMA = 'coursedb' AND TABLE_TYPE = 'BASE TABLE' and engine = 'MyISAM' ;

-- 复制所有查询出来的语句执行，其他库同理