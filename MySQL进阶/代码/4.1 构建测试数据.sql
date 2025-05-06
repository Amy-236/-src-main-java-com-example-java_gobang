-- 修改SQL结束符
delimiter //

-- 创建存储过程
CREATE PROCEDURE p_init_index_data ()
BEGIN
  -- 生成学号和主键
  DECLARE id BIGINT DEFAULT 100000;
  -- 年龄
  DECLARE age TINYINT DEFAULT 18;
  -- 性别
  DECLARE gender BIGINT DEFAULT 1;
  -- 班级编号
  DECLARE class_id BIGINT DEFAULT 1;
  -- 循环计算
  DECLARE count INT DEFAULT 0;
  
  -- 创建表
  DROP TABLE IF EXISTS index_demo;
  CREATE TABLE index_demo (
    id bigint auto_increment,
    sn varchar(10) NOT NULL,
    name varchar(20) NOT NULL,
    mail VARCHAR(20),
    age TINYINT(1),
    gender TINYINT(1),
    password VARCHAR(36) NOT NULL,
    class_id bigint NOT NULL,
    create_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL,
    PRIMARY KEY (id),
    index (class_id)
  );
  
  -- 插入一条测试数据
  INSERT INTO index_demo VALUES (100000, '100000', 'testUser', '100000@qq.com', 18, 1, UUID(), 1, NOW(), NOW());
  
  -- 循环构建数据
  WHILE count < 1000000 DO
    -- ID和学号
    SET id := id + 1;
    -- 年龄
    IF count % 10 = 0 THEN
      SET age := age + 1;
    END IF;
    
    IF age > 50 THEN
      SET age := 16;
    END IF;
    
    -- 性别
    IF  count % 3 = 0 THEN
      SET gender := 0;
    ELSE
      SET gender := 1;
    END IF;

    -- 班级编号
    SET class_id := class_id + 1;
    IF class_id > 10 THEN
      SET class_id := 1;
    END IF;
    
    -- 写入数据
    INSERT INTO index_demo VALUES (id, id, CONCAT('user_',id), CONCAT(id,'@qq.com'), age, gender, UUID(), class_id, NOW(), NOW());
    
    -- 更新count
    SET count := count + 1;
      
  END WHILE;

END //

-- 还原SQL结束符
delimiter ;

-- 调用存储过程，开始构建数据，大约20 - 100分钟左右
CALL p_init_index_data();