-- 修改专业名称字段字符排序规则
ALTER TABLE `tb_major` MODIFY `major_name` varchar(255) CHARACTER SET UTF8 COLLATE utf8_general_ci;

