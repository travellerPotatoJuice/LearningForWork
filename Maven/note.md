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