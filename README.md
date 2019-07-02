# 毕业设计的客户端

这是仿造今日头的新闻客户端

此项目中的新闻数据是我从上观综合、看看新闻等新闻网站上抓取下来的，如想研究新闻抓取的源码，请移步到我的另一个项目：<https://github.com/TableBear/News-Crawl>。

如想运行此项目必须配置好服务端，此项目服务端的地址为：<https://github.com/TableBear/News-Server>，搭建好服务端之后，必须修改本客户端的主请求IP地址，也即修改位于项目的com.hzx.news.api包下的ApiConstant类中的BASE_SERVER_URL属性的值。一下是对本项目的简介。

1. 应用的首页

   首页主要是由搜索框+TabLayout+Fragment+BottomLayout构成，同时为了实现上拉刷新以及下拉加载更多的功能，我将Fragment用SmartRefreshLayout包裹起来。SmartRefreshLayout是一个开源项目，它实现了下拉刷新时更多的样式，更加可以自定义样式。首页还可以通过左滑和右滑切换不同的栏目。

   ![home](image/home.png)

2. 项目的搜索功能

3. 

