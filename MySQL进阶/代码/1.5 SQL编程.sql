-- - 示例：根据分数的值，判定当前分数对应的等级
--   - 分数 >= 90 分等级为优秀
--   - 分数 >= 80 且分数 < 90分等级为良好
--   - 分数 >= 60 分且分数 < 80分等级为及格
--   - 分数 < 60分等级为不及格
delimiter //
-- 定义存储过程
create procedure p2 () 
BEGIN
  -- 定义初始分数变量
  DECLARE score INT DEFAULT 86;
  -- 定义结果变量
  DECLARE result VARCHAR(10);
  
  -- 判断
  IF score >= 90 THEN
    -- 为变量赋值
    SET result := '优秀';
  ELSEIF score >= 80 AND score < 90 THEN
    SET result := '良好';
  ELSEIF score >= 60 AND score < 80 THEN
    SET result := '及格';
  ELSE 
    SET result := '不及格';
  END IF;
  
  -- 查询结果
  select result;

END//


delimiter ;

-- 调用存储过程
CALL p2();

-- - 示例1：传入一个分数的值，判定当前分数对应的等级
--   - 分数 >= 90 分等级为优秀
--   - 分数 >= 80 且分数 < 90分等级为良好
--   - 分数 >= 60 分且分数 < 80分等级为及格
--   - 分数 < 60分等级为不及格
delimiter //
create procedure p3 (IN score INT, OUT result VARCHAR(10))
BEGIN
  -- 判断
  IF score >= 90 THEN
    SET result := '优秀';
  ELSEIF score >= 80 AND score < 90 THEN
    SET result := '良好';
  ELSEIF score >= 60 AND score < 80 THEN
    SET result := '及格';
  ELSE
    SET result := '不及格';
  END IF;

END//
delimiter ;

-- 调用存储过程
CALL p3 (90, @result);
-- 查看结果
select @result;


-- - 示例2：传入一个分数的值，在传入分数的基础上加10分，然后返回
delimiter //
create procedure p4 (INOUT score INT) 
BEGIN
  -- 在原分数上加10分
  SET score := score + 10;
  
END//

delimiter ;

-- 定义一个变量并赋初始值
SET @score = 70;
-- 调用存储过程
CALL p4 (@score);
-- 查看结果
select @score;

-- - 示例一：传入一个状态码，输出该状态码表示的含义
--   - 0：成功
--   - 10001：用户名或密码错误
--   - 10002：您没有对应的权限，请联系管理员
--   - 20001：你传入的参数有误
--   - 20002：没有找到相应的结果
delimiter //
create procedure p5 (IN code INT, OUT result VARCHAR(50))
BEGIN
  CASE code 
    WHEN 0 THEN
      SET result := '成功';
    WHEN 10001 THEN
      SET result := '用户名或密码错误';
    WHEN 10002 THEN
      SET result := '您没有对应的权限，请联系管理员';
    WHEN 20001 THEN
      SET result := '你传入的参数有误';
    WHEN 20002 THEN
      SET result := '没有找到相应的结果';
    ELSE
      SET result := '服务器错误，请联系管理员';
  END CASE;

END//

delimiter ;

-- 调用
CALL p5(10002, @result);
-- 查看结果
select @result;

-- - 示例二：根据传入的月份，输出该月份属于哪个季度
--   - 1 ~ 3月为第一季度
--   - 4 ~ 6月为第二季度
--   - 7 ~ 9月为第三季度
--   - 10 ~ 12月为第四季度
delimiter //
create procedure p6 (IN month INT, OUT result VARCHAR(50))
BEGIN
  CASE 
    WHEN month >= 1 AND month <= 3 THEN
      SET result := '第一季度';
    WHEN month >= 4 AND month <= 6 THEN
      SET result := '第二季度';
    WHEN month >= 7 AND month <= 9 THEN
      SET result := '第三季度';
    WHEN month >= 10 AND month <= 12 THEN
      SET result := '第四季度';
    ELSE
      SET result := '非法输入';
  END CASE;
END//

delimiter ;

-- 调用
CALL p6 (9, @result);
-- 查看结果
select @result;

-- - 示例：传入一个数n，计算从1累加到n的值
delimiter //
create procedure p7 (IN n INT)
BEGIN
  -- 定义一个变量，保存结果
  DECLARE total INT DEFAULT 0;
  
  -- 循环
  WHILE n > 0 DO
    -- 累加操作
    SET total := total + n;
    -- 重置n
    SET n := n - 1;
  END WHILE;
  
  -- 查看结果 
  select total;
END//

delimiter ;

-- 调用
CALL p7(10);


-- - 示例：传入一个数n，计算从1累加到n的值
delimiter //
create procedure p8 (IN n INT)
BEGIN
  -- 定义一个变量保存累加结果
  DECLARE total INT DEFAULT 0 ;
  
  -- 循环
  REPEAT
    -- 查看n的值
    select n;
    -- 累加操作
    SET total := total + n;
    -- 重置n
    SET n := n - 1;
  UNTIL n <= 0 END REPEAT;
  
  -- 查看结果
  SELECT total;

END//
delimiter ;

-- 调用
CALL p8(0);

-- - 示例：传入一个数n，计算从1累加到n的值
delimiter //
create PROCEDURE p9 (IN n INT)
BEGIN

  -- 定义一个变量保存累加结果
  DECLARE total INT DEFAULT 0 ;

  -- 循环
  sum_label: LOOP
    -- 退出条件
    IF n <= 0 THEN
      LEAVE sum_label; 
    END IF;
    
    -- 累加操作
    SET total := total + n;
    -- 重置n
    SET n := n - 1;
    
  END LOOP sum_label;
  
  -- 查询结果
  select total;
END//
delimiter ;

-- 调用
CALL p9(10);

-- - 示例：传入一个数n，累加从1累加到n之间偶数的值
delimiter //
create PROCEDURE p10 (IN n INT)
BEGIN

  -- 定义一个变量保存累加结果
  DECLARE total INT DEFAULT 0;

  -- 循环
  sum_label: LOOP
    -- 退出条件
    IF n <= 0 THEN
      LEAVE sum_label; 
    END IF;
    
    -- 奇数跳出本次循环
    IF n % 2 = 1 THEN
      -- 重置n
      SET n := n - 1;
      -- 跳出本次循环
      ITERATE sum_label;
    END IF;
    
    -- 累加操作
    SET total := total + n;
    -- 重置n
    SET n := n - 1;
    
  END LOOP sum_label;
  
  -- 查询结果
  select total;
END//
delimiter ;

-- 调用
CALL p10(10);


-- - 示例：传入班级编号，查询学生表中属于该班级的学生信息，并将符合条件的学生信息写入到一张新表中
--   - 新表及字段t_student_class (id, student_name, class_name)
-- - 实现逻辑
--   - 定义变量用于接收查询结果集中每一行中列的值
--   - 声明游标，用于接收查询结果集
--   - 创建新表
--   - 开启游标
--   - 从游标中获取结果集中的记录
--   - 向新表中写入数据
--   - 关闭游标
delimiter //
create procedure p11 (IN class_id INT)
BEGIN
  -- 定义变量用于接收查询结果集中每一行中列的值
  -- 学生姓名
  DECLARE student_name VARCHAR(20);
  -- 班级名
  DECLARE class_name VARCHAR(20);
  
  -- 声明游标，用于接收查询结果集
  DECLARE s_cursor CURSOR FOR 
    select s.`name` student_name, c.`name` class_name from student s, class c 
      where s.class_id = c.id and s.class_id = class_id;
      
  -- 创建新表
  drop table if exists t_student_class;
  create table if not exists t_student_class (
    id BIGINT PRIMARY KEY auto_increment,
    student_name VARCHAR(20) NOT NULL,
    class_name VARCHAR(20) NOT NULL
  );
  
  -- 开启游标
  OPEN s_cursor;
  
  -- 遍历结果集
  WHILE TRUE DO
    -- 获取游标记录
    FETCH s_cursor INTO student_name, class_name;
    -- 写入到新表
    INSERT INTO t_student_class VALUES (NULL, student_name, class_name);
  END WHILE;
  
  -- 关闭游标
  CLOSE s_cursor;

END//

delimiter ;

-- 调用存储过程
CALL p11(1);

-- - 示例：加入条件处理程序，解决游标越界问题
drop procedure if exists p12;
delimiter //
create procedure p12 (IN class_id INT)
BEGIN
  -- 定义变量用于接收查询结果集中每一行中列的值
  -- 学生姓名
  DECLARE student_name VARCHAR(20);
  -- 班级名
  DECLARE class_name VARCHAR(20);
  -- 定义游标的结束标识
  DECLARE is_done bool DEFAULT FALSE;
  
  -- 声明游标，用于接收查询结果集
  DECLARE s_cursor CURSOR FOR 
    select s.`name` student_name, c.`name` class_name from student s, class c 
      where s.class_id = c.id and s.class_id = class_id;
      
  -- 定义条件处理程序
  DECLARE CONTINUE HANDLER FOR NOT FOUND 
    SET is_done := TRUE;
  
  -- 创建新表
  drop table if exists t_student_class;
  create table if not exists t_student_class (
    id BIGINT PRIMARY KEY auto_increment,
    student_name VARCHAR(20) NOT NULL,
    class_name VARCHAR(20) NOT NULL
  );
  
  -- 开启游标
  OPEN s_cursor;
  
  -- 遍历结果集
  read_loop: LOOP
    -- 获取游标记录
    FETCH s_cursor INTO student_name, class_name;
    -- 判断退出条件
    IF is_done THEN
      LEAVE read_loop; 
    END IF; 
    
    -- 写入到新表
    INSERT INTO t_student_class VALUES (NULL, student_name, class_name);
    
  END LOOP read_loop;
  
--   WHILE NOT is_done DO
--     -- 获取游标记录
--     FETCH s_cursor INTO student_name, class_name;
--     -- 写入到新表
--     INSERT INTO t_student_class VALUES (NULL, student_name, class_name);
--   END WHILE;
  
  -- 关闭游标
  CLOSE s_cursor;

END//

delimiter ;

-- 调用存储过程
CALL p12(1);


-- 使用存储函数实现
-- - 示例：传入一个数n，计算从1累加到n的值
delimiter //
CREATE FUNCTION fun1(n INT) RETURNS INT DETERMINISTIC
BEGIN
  -- 定义变量保存结果
  DECLARE total INT DEFAULT 0;
  -- 循环
  WHILE n > 0 DO
    -- 累加
    SET total := total + n;
    -- 重置 n
    SET n := n - 1;
  END WHILE;
  
  -- 返回结果
  RETURN total;

END//

delimiter ;

-- 调用存储函数
select fun1(10);


























