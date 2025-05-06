-- - 通过触发器记录学生表的变更日志，将变更日志写入日志表student_log中，包含增加，修改和删除操作。

-- 创建学生日志表
create table student_log (
    id bigint primary key auto_increment,
    operation_type varchar(10) not null comment '操作类型：insert/update/delete',
    operation_time datetime not null comment '操作时间',
    operation_id bigint not null comment '操作的记录ID',
    operation_data varchar(500) comment '操作数据'
);

-- 插入数据的触发器
delimiter //
CREATE TRIGGER IF NOT EXISTS trg_student_insert
  AFTER INSERT ON student FOR EACH ROW
BEGIN
  -- 插入日志到student_log表
  insert into student_log (
    operation_type,
    operation_time,
    operation_id,
    operation_data
  ) values (
    'insert',
    now(),
    new.id,
    concat(new.id, ',', new.name, ',', new.sno, ',', new.age, ',', new.gender, ',', new.enroll_date, ',', new.class_id)
  );
END//
delimiter ;

-- 向学生表中插入记录
insert into student values (null, '曹操', '300001', 28, 1, '2024-09-01', 3);

-- 更新数据的触发器
delimiter //
CREATE TRIGGER IF NOT EXISTS trg_student_update
  AFTER UPDATE ON student FOR EACH ROW 
BEGIN
  -- 插入日志到student_log表
  insert into student_log (
    operation_type,
    operation_time,
    operation_id,
    operation_data
  ) values (
    'update',
    now(),
    new.id,
    concat(old.id, ',', old.name, ',', old.sno, ',', old.age , ',', old.gender , ',', old.enroll_date, ',', old.class_id , '|',
           new.id, ',', new.name, ',', new.sno, ',', new.age, ',', new.gender, ',', new.enroll_date, ',', new.class_id
    )
  );
END//
delimiter ;

-- 更新学生表中记录
update student set age = 20, class_id = 2 where name = '曹操';

-- 更新多条记录
update student set class_id = 3 where id >= 7;

-- 删除数据的触发器
delimiter //
CREATE TRIGGER IF NOT EXISTS trg_student_delete
  AFTER DELETE ON student FOR EACH ROW 
BEGIN
  -- 插入日志到student_log表
  insert into student_log (
    operation_type,
    operation_time,
    operation_id,
    operation_data
  ) values (
    'delete',
    now(),
    old.id,
    concat(old.id, ',', old.name, ',', old.sno, ',', old.age , ',', old.gender , ',', old.enroll_date, ',', old.class_id)
  );
END//
delimiter ;

-- 删除学生表中记录
delete from student where name = '曹操';

-- 查看触发器
SHOW TRIGGERS;

-- 删除触发器
DROP TRIGGER IF EXISTS trg_student_insert;
















