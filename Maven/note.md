# Maven简介

**Maven**：一种项目管理工具，用java语言编写，将项目开发和管理过程抽象成一个项目对象模型（POM）

**Maven的作用**：项目构建（提供标准的跨平台的自动化项目构建方式），依赖管理（方便快捷地管理项目依赖的资源，避免版本冲突），统一开发结构

POM（Project Object Model）：项目对象模型

	+ 项目对象模型（POM）
	+ 依赖管理（Dependency）：管理资源
	+ 构建生命周期

![image-20231105185326532](C:\Users\83538\AppData\Roaming\Typora\typora-user-images\image-20231105185326532.png)

## 仓库：用于存储资源，包含各种jar包

本地从私服拿，私服从中央拿。

私服的作用：

	1. 保存具有版权的资源（自主研发的，购买的资源），因为中央仓库里的资源都是开源的
 	2. 加快下载速度

	+ 本地仓库：自己电脑上存储资源的仓库，连接远程仓库获取资源
 + 远程仓库：非自己电脑上的仓库
   	+ 私服：部门/公司等小范围内存储资源的仓库
      	+ 中央仓库：Maven团队维护，放着大多数的jar包



**仓库配置**：

​	用户setting：当前用户的配置，会覆盖全局setting

​	全局setting：定义当前计算机中Maven的公共配置



## 坐标：用于描述仓库中资源的位置

groupId：定义当前Maven项目隶属组织名称

artifactId：定义当前Maven项目名称

version：当前项目版本号

packaging（不属于坐标的一部分）：定义该项目的打包方式



## Maven项目结构

java-project

+ src			
   + main
      + java
      + resources
   + test
      + java
      + resources



## 依赖配置

**依赖：**依赖指通过<dependencies>和<dependency>配置当前项目所需要的jar包

```xml
<dependencies>
	<dependency>
        <!--群组id，项目id，版本号-->
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.12</version>
	</dependency>
</dependencies>
```

直接依赖：在当前项目中通过依赖配置建立的依赖关系

间接依赖：被依赖的资源A如果依赖其他资源B，当前项目就对资源B形成间接依赖



### 依赖传递冲突问题

+ 路径优先：层级越深，优先级越低
+ 声明优先：当资源在相同层级被依赖时，配置顺序越靠后，优先级越低
+ 特殊优先：当同级配置了相同资源的不同版本，后配置的覆盖先配置的



### 可选依赖

对外隐藏当前所依赖的资源

```xml
<optional>true</optional>
```



### 排除依赖

主动断开依赖的资源，被排除的资源无需指定版本

```xml
<exclusions>
	<exclusion>
        <groupId></groupId>
        <artifactId></artifactId>
    </exclusion>
</exclusions>
```



### 依赖范围

依赖的jar默认情况下在任何地方都可以使用，可以通过scope标签设定其作用范围

+ 主程序范围内可用（main文件夹范围内）
+ 测试程序范围内能用（test文件夹范围内）
+ 是否参与打包（package指令范围内）

|  scope   | 主代码 | 测试代码 | 打包 |    范例     |
| :------: | :----: | :------: | :--: | :---------: |
| compile  |   Y    |    Y     |  Y   |    log4j    |
|   test   |        |    Y     |      |    junit    |
| provided |   Y    |    Y     |      | servlet-api |
| runtime  |        |          |  Y   |    jdbc     |