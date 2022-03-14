# 使用说明

---
## doc描述
- code-sample 代码实例
- data 
  - temp 缓存目录
  - 题目测试样例
  
- db 数据库脚本
- paper.zip 开题报告与论文


---
## 操作步骤

### 服务器搭建
- 搭建mysql数据库服务，导入数据库脚本
    - `doc/db/acm-init.sql`初始化表结构数据
    - `doc/db/acm-data.sql`表结构与测试数据
    
- 代码执行环境配置
    - Java
    - Python
    - C
    - ...
    
### 配置修改
- 修改`application.yml`配置文件
    - mysql数据库信息
    
- 修改`judgecore.xml`配置文件
    - 编译脚本修改

- 修改`db/data/**`路径
    - 测试数据修改

---
## 部署说明
- Java环境
- Maven
- 构建打包jar
- 执行`java -jar xxx.jar`启动程序