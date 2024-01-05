# Spring概述

## 为什么需要Spring

### 传统Javaweb开发存在的问题：

+ 接口与具体实现之间紧密耦合。解决方法：在程序代码中不手动创建对象，而是由第三方根据要求创建
+ 通用的事务以及日志功能耦合在业务对象中。解决方法：第三方根据要求为程序提供需要的Bean对象的代理对象



### IoC，DI，AOP

IoC：Inversion of Control.。控制反转。指将创建Bean的权利反转给第三方

DI：Dependency Injection。依赖注入。由第三方来负责设计Bean之间的关系

AOP：Aspect Oriented Programming。面向切面编程。功能的横向抽取



### Spring框架

**框架**：基于基础技术之上，从众多业务中抽取出的通用解决方案。

**常见基础框架：**MyBatis，Spring，SpringMVC

**常见服务框架：**MQ，ES，Nacos



