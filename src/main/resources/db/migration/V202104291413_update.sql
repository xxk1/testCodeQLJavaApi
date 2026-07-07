-- 把非自由班级的名称存入班级组成
update tb_group set class_composition_name=group_name where source != 3;