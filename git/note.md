# 概述

## 版本控制方式

1. 集中式版本控制工具

   版本库集中存放在中央服务器，用户从中央服务器下载代码，个人修改后提交到中央版本库，所以必须要联网才能工作。

   例子：SVN和CVS

2. 分布式版本控制工具

   没有”中央服务器“，每个人的电脑上都是一个完整的版本库，工作时无需联网，多人协作只需要将各自的修改推送给对方，就能互相看到对方的修改了

   例子：Git

## Git工作流程图

![image](image\image-20231117181532442.png)



# 常用命令

## 获取本地仓库

1. 在电脑上创建一个空目录作为本地Git仓库

2. 进入该目录，git bash here

3. 执行命令

   ```
   git init
   ```

   初始化如果成功，就能在文件夹下看到一个.git目录



## 基础操作指令

<font size=5>**文件提交** </font>

![image](image\image-20231117182943591.png)

 

1. 查看文件状态。此时会在changes to be commited, untracked files下面显示对应状态的文件

```git
git status
```

2. 对文件进行修改或者创建文件以后，用git add命令让文件进入暂存区。通过.gitignore文件可以设置在add .时不移入暂存区的文件

```
git add file1.txt //将某个文件的修改移入暂存区
git add . //将所有修改都移入暂存区
```

3. 用git commit 命令提交修改

```
git commit -m "add file1,fil2,xxx" //-m 后面的内容是提交的描述信息
```

4. 用git log查看提交记录

```
git log[options]
options
	--all 显示所有分支
	--pretty=oneline 将提交信息显示为一行
	--abbrev-commit 使输出的commitid更加简短
	--graph 以图的形式显示
```

可以用alias指令给长指令设置别名，避免每次都要输入很长的指令



<font size=5>**版本回退** </font>

1. 通过git log显示版本提交记录

2. git reset --hard commitid

   注意：自己用的时候可以这么用，和别人一起合作的时候最好不要这么回退

3. git reflog能够记录所有的操作，如果reset后不慎clear了，可以用reflog查找回退之前的记录来撤销回退



<font size=5>**添加屏蔽文件** </font>

在工作目录中创建一个名为.gitignore的文件，在里面填写需要忽略的文件模式。可以用通配符来填写，比如*.txt表示所有的txt文件



 

<font size=5>**分支** </font>

1. 查看有哪些分支

   ```
   git branch
   ```

2. 创建新分支

   ```
   git branch [name]
   ```

3. 切换分支

   ```
   git check [name]
   ```

4. 推送至远程仓库分支

   ```
   git push [shortName] [name]
   ```

5. 合并分支

   ```
   git merge [name]
   ```

6. 删除分支

   ```
   git branch -d [name]
   git branch -D [name] //强制删除，不管有没有未合并的操作都直接删除
   ```



在开发过程中，一般有如下的分支使用原则和流程：

+ master：生产分支。线上分支，也就是作为线上运行的应用对应的分支
+ develop：开发分支。从master中创建的分支，一般作为开发部门的主要开发分支，阶段开发完成后，需要合并到master分支上准备上线
+ feature：从develop创建的分支，一般是同期并行开发，分支上的研发任务完成后合并到develop分支
+ hotfix：从master派生的分支，一般作为线上bug修复使用，修复后需要合并到master，test，develop分支



## 远程仓库

1. 添加远程仓库

   ```
git remote add [remote name] [path]
   ```
   
2. 查看远程仓库

   ```
   git remote
   ```

3. 推送到远程仓库

   ```
git push [-f] [--set-upstream] [remote name [local branch name]:[remote branch name]]
   ```
   -f：表示强制覆盖

   --set-upstream：推送到远端的同时建立起与远端分支的关联关系。如果已经存在关联关系，就直接push即可

4. 查看本地分支和远程分支的关联关系 

   ```
git branch -vv
   ```
   
5. 从远程仓库克隆

   ```
   git clone [path]
   ```

6. 从远程仓库抓取和推送。

   ```
   git fetch [remote name][branch name]
   
   git pull [remote name][branch name]
   ```

   git fetch：只拉取到本地，但不进行合并，需要执行git merge后才能合并。如果不指定远端分支名称，则抓取所有分支。

   git pull：拉取到本地之后自动进行合并，相当于fetch+merge。如果不指定远端分支名称，则抓取所有分支并更新当前分支。



