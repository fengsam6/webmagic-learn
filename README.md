# 一、webmagic-learn
## 1.介绍
1. 使用webmagic、springboot爬取CSDN博客，爬取爱奇艺视频、360影视。每隔2天，清空爬虫数据，重新爬取数据。
## 2.后端技术
springBoot、mysql、springBoot Data JPA、webmagic、redis

## 3.运行效果

* 具体效果可以访问：http://152.136.105.250:8066/

![](运行效果/film_spider.gif)

1. 电影列表页面

   > ​         使用webmagic从爱奇艺官网上爬取电影标题、url等数据，并且将爬虫数据存放MySQL数据库中。电影列表页面渲染如下，借助第三方视频解析，点击解析播放可以播放该视频。

<img src="运行效果/IQIYIFilmList.png" width="680" >





2. 电影列表数据库数据

   > 使用webmagic从爱奇艺官网爬取数据，并且将数据存放mysql数据中。

   <img src="运行效果/films-DB-data.png" width="680" >

   

   

   3. CSDN博客列表页面

      > ​       使用webmagic从CSDN网站爬取数据，并且将数据存放mysql数据中。博客列表页面渲染如下，点击博客标题可以查看博客内容。

   ![](运行效果/blogList.png)

   

   

   4. CSDN博客列表数据库中的爬虫数据

      > 使用webmagic从CSDN网站爬取数据，并且将数据存放mysql数据中。

      ![](运行效果/blogs-db-data.png)

      

   5. 从CSDN爬取博客数据，效果如下图。

      ![](运行效果/blos-spider.png)

## 4. 本地运行

1. 软件安装。需要安装java、maven、mysql等软件。
2. 下载项目代码。执行git clone git@gitee.com:fengsam618/webmagic-learn.git,将项目导入idea中
3. 将sql文件夹下spider_film.sql在Navicat中执行。成功执行会创建两张表
4. 修改resource目录下 application-dev.properties。重点修改连接数据库名称、登录用户名称、密码
5. idea打开，启动成功后，浏览器访问http://localhost/
6. 部署可以参考bin目录脚本



## 5.待完成

1. 电影列表，支持多种搜索（根据电影名称、时间）
2. 电影图片url错误，待解决

3. 数据支持增量插入，定时插入

4. 视频支持切换播放线路，vip视频解析url

## 6.备注
1. 项目码云地址：https://gitee.com/fengsam618/webmagic-learn
2. 项目github地址：https://github.com/fengsam6/webmagic-learn
3. 感兴趣，可以给一个start
4. .视频解析如果有侵权，请停止使用