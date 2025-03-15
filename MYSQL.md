# **MySQL**

*数据模型：关系型数据库**

● **关系型数据库****是建立在****关系模型****基础上的数据库,简单说,关系型数据库是由多张能互相连接的****二维表****组成的数据库**

优点：

1. 都是使用表结构,格式一致,易于维护。
2. 使用通用的SQL语言操作,使用方便,可用于复杂查询。
3. 数据存储在磁盘中,安全。

![img](MYSQL.assets/17420063564724.png)



**SQL****通用语法**

1. SQL语句可以单行或多行书写,以分号结尾。
2. MySQL数据库的SQL语句**不区分大小写**,关键字建议使用大写。
3. 注释：
   1.  · 单行注释 :-- 注释内容 或**#注释内容(****MySQL** **特有)**

   2.  · 多行注释:/* 注释 */

## DDL

● DDL(Data Definition Language)数据定义语言,用来定义数据库对象:数据库,表,列等

### 操作数据库

<img src="MYSQL.assets/17420063591607.png" alt="img" style="zoom:80%;" /> 

### 操作表

#### **数据类型**

MySQL支持多种类型,可以分为三类:

1.数值

|              |         |                |
| ------------ | ------- | -------------- |
| 数据类型     | 大小    | 描述           |
| TINYINT      | 1 byte  | 小整数值       |
| SMALLINT     | 2 bytes | 大整数值       |
| MEDIUMINT    | 3 bytes | 大整数值       |
| INT或INTEGER | 4 bytes | 大整数值       |
| BIGINT       | 8 bytes | 极大整数值     |
| FLOAT        | 4 bytes | 单精度浮点数值 |
| DOUBLE       | 8 bytes | 双精度浮点数值 |
| DECIMAL      |         | 小数值         |

score double(总长度,小数点后保留的位数)    score double(5.2)

2.日期

![img](MYSQL.assets/174200636409710.png) 

3.字符串

![img](https://ncnwfcsxrcte.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjFmMzEzOTFkOGVmNTAxNWU1MzllZDM5NjUxYmQ3NGVfRVdvblBRVXRvV3lGS0VWR0RtbjRjYlpKTVpoU0dSNFBfVG9rZW46Q0xRU2JIa3BMb25VaGl4VWhxd2NFbGtXblhnXzE3NDIwMDQ5MDk6MTc0MjAwODUwOV9WNA) 

#### 操作

<img src="MYSQL.assets/174200636753113.png" alt="img" style="zoom:80%;" /> 

<img src="MYSQL.assets/174200636961416.png" alt="img" style="zoom:80%;" /> 

<img src="MYSQL.assets/174200637219719.png" alt="img" style="zoom:80%;" />  

<img src="MYSQL.assets/174200639290822.png" alt="img" style="zoom:80%;" />  

## DML

● DML(Data Manipulation Language)数据操作语言,用来对数据库中表的数据进行增删改

**查询所有数据：**select * from 表名

<img src="MYSQL.assets/174200639545125.png" alt="img" style="zoom:80%;" /> 

<img src="MYSQL.assets/174200639751528.png" alt="img" style="zoom:80%;" /> 

例：update stu set sex='女’ where name=‘张三';

<img src="MYSQL.assets/174200639991531.png" alt="img" style="zoom:80%;" /> 

例：delete from stu where name = 'xx';

## DQL

● DQL(Data Query Language)数据查询语言,用来查询数据库中表的记录(数据)

#### 基础查询

<img src="MYSQL.assets/174200640205334.png" alt="img" style="zoom:80%;" /> 

#### 条件查询

<img src="MYSQL.assets/174200640398937.png" alt="img" style="zoom:80%;" /> 

#### 排序查询

#### <img src="MYSQL.assets/174200640663540.png" alt="img" style="zoom:80%;" />

##### 聚合函数

<img src="MYSQL.assets/image-20250315010057220.png" alt="image-20250315010057220" style="zoom:80%;" /> 

##### 分组查询语法

!<img src="MYSQL.assets/174200641451344.png" alt="img" style="zoom:80%;" /> 

**例：**select sex, avg (math) , count (*) from stu where math > 70 group by sex having count (*)  > 2;

#### 分页查询

<img src="MYSQL.assets/174200641789947.png" alt="img" style="zoom:80%;" /> 

tips:

· 分页查询limit是MySQL数据库的方言

· Oracle 分页查询使用 rownumber

· SQL Server分页查询使用top

### DCL

● DCL(Data Control Language)数据控制语言,用来定义数据库的访问权限和安全级别,及创建用户

## 约束

### 约束的概念

· 约束是作用于表中列上的规则,用于限制加入表的数据

· 约束的存在保证了数据库中数据的正确性、有效性和完整性

### **约束的分类**

<img src="MYSQL.assets/174200642143950.png" alt="img" style="zoom:80%;" /> 

Tips: MYSQL不支持检查约束，但是可以通过Java检查

#### 非空约束

<img src="MYSQL.assets/17420063097691-174200645799555.png" alt="img" style="zoom:80%;" />  

#### 唯一约束

<img src="MYSQL.assets/174200646103557.png" alt="img" style="zoom:80%;" /> 

<img src="https://ncnwfcsxrcte.feishu.cn/space/api/box/stream/download/asynccode/?code=NTI5M2I0OTRhZmQyNmQyN2IxMjExOGQ5ZjgwZjFkMjBfNlJtVGg4V2xkc21yU0VuTUg2OWY5enB2Ujg4RUJsUktfVG9rZW46Vm40QWI3V0FjbzFSbTN4S2U0NGNxQTVkbldnXzE3NDIwMDU0MTQ6MTc0MjAwOTAxNF9WNA" alt="img" style="zoom:80%;" /> 

#### 主键约束

<img src="MYSQL.assets/174200675440163.png" alt="img" style="zoom:80%;" /> 

<img src="MYSQL.assets/174200679352666.png" alt="img" style="zoom:80%;" />  

#### 默认约束

<img src="MYSQL.assets/174200683199869.png" alt="img" style="zoom:80%;" /> 

<img src="MYSQL.assets/174200684567772.png" alt="img" style="zoom:80%;" /> 

#### 外键约束

**1.概念**

· 外键用来让两个表的数据之间建立链接,保证数据的一致性和完整性

###### <img src="MYSQL.assets/image-20250315011316234.png" alt="image-20250315011316234" style="zoom:80%;" /> 



[constraint] fk foreign key(dep_id) references (id)

## 数据库设计

**数据库设计设计什么?**

· 有哪些表

**·** 表里有哪些字段

**·** 表和表之间是什么关系

### **表关系**

#### 一对一

**如:用户 和 用户详情**

**一对一关系多用于表拆分,将一个实体中经常使用的字段放一张表,不经常使用的字段放另一张表,用于提升查询性能**

**实现方式:在任意一方加入外键,关联另一方主键,并且设置外键为唯一(UNIQUE)**

#### 一对多(多对一)

<img src="MYSQL.assets/image-20250314233722451.png" alt="image-20250314233722451" style="zoom:80%;" />

####  多对多

<img src="MYSQL.assets/174200686119477.png" alt="img" style="zoom:80%;" /> 

**多表查询:从多张表查询数据**

### 内连接

相当于查询A B交集数据

 <img src="MYSQL.assets/image-20250314235947931.png" alt="image-20250314235947931" style="zoom:80%;" /> 



### 外连接

左外连接:相当于查询A表所有数据和交集部分数据

右外连接:相当于查询B表所有数据和交集部分数据 

### <img src="MYSQL.assets/image-20250315000842047.png" alt="image-20250315000842047" style="zoom:80%;" />



### 子查询

<img src="MYSQL.assets/image-20250315001857902.png" alt="image-20250315001857902" style="zoom:80%;" /> 



## JDBC

### DriverManager

DriverManager(驱动管理类)作用:

注册驱动（可省略）

获取数据库连接

<img src="MYSQL.assets/image-20250315142252641.png" alt="image-20250315142252641" style="zoom:80%;" /> 

### Connection

<img src="MYSQL.assets/image-20250315161859088.png" alt="image-20250315161859088" style="zoom:80%;" /> 

事务管理

<img src="MYSQL.assets/image-20250315162008324.png" alt="image-20250315162008324" style="zoom:80%;" /> 

**可以用try catch捕获异常然后回滚事务**







