## webmagic-learn
### 介绍
1. 使用webmagic爬取CSDN博客，爬取爱奇艺视频，使用redis缓存爬虫爬过的url
### 后端技术
springBoot、mysql、springBoot Data JPA、webmagic、redis

### 运行效果

![](运行效果/blog.gif)

1. 电影列表页面

   > ​         使用webmagic从爱奇艺官网上爬取电影标题、url等数据，并且将爬虫数据存放MySQL数据库中。电影列表页面渲染如下，点击解析播放可以播放该视频。

<img src="运行效果/films.png" width="680" >





2. 电影列表数据库数据

   > 使用webmagic从爱奇艺官网爬取数据，并且将数据存放mysql数据中。

   

   <img src="运行效果/films-DB-data.png" width="680" >

   

   

   3. CSDN博客列表页面

      > ​       使用webmagic从CSDN网站爬取数据，并且将数据存放mysql数据中。博客列表页面渲染如下，点击博客标题可以查看博客内容。

   ![](运行效果/blogs.png)

   

   

   4. CSDN博客列表数据库中的爬虫数据

      > 使用webmagic从CSDN网站爬取数据，并且将数据存放mysql数据中。

      ![](运行效果/blogs-db-data.png)

      

   5. 从CSDN爬取博客数据，效果如下图。

      ![](运行效果/blos-spider.png)