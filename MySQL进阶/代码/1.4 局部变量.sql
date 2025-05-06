-- 系统变量

-- 示例：查看以auto开头的全局系统变量
SHOW GLOBAL VARIABLES LIKE 'auto%';
-- 示例：查看以char开头的会话系统变量
SHOW SESSION VARIABLES LIKE 'char%';
-- 示例：查看事务自动提交全局系统变量
SELECT @@GLOBAL.autocommit;

-- 查看会话系统变量
SELECT @@SESSION.autocommit;


-- 用户自定义变量

-- 示例：定义一个age变量并赋值为18，并查看
SET @age := 18;
SELECT @age;

-- 示例：从学生表中查询编号为1的学生学号并赋值给sno变量
SELECT sno INTO @sno from student where id = 1; 
SELECT @sno;

-- 示例：查询学生表中的总记录数并赋值给conut变量
SELECT count(*) INTO @count from student;
SELECT @count;

-- 示例：访问一个未赋值的变量，返回NULL
SELECT @var;

-- SELECT 中使用 = 号是比较的意思
SELECT @age = 18;
SELECT @age = 19;



-- 局部变量
delimiter //

CREATE PROCEDURE p1() 
BEGIN
  -- 定义局部变量，并指定默认值
  DECLARE stu_count INT DEFAULT 0;
  -- 把查询结果赋值给局部变量
  select count(*) into stu_count from student;
  -- 使用局部变量
  select stu_count;
END//

-- 调用存储过程
call p1();

delimiter ;

select count(*) from student;