# tfourprobe-initialization
基本搭建
基于springboot+jpa+gradle 环境的搭建，整合线程池和阿里巴巴数据库数据源

开发环境 MacOS   idea 2018.2.2 + jdk1.8 + springboot2.1.2.RELEASE +jpa+gradle5.1.1

sql脚本生成，先运行springboot启动类自动生成表结构

gradle5.1.1 安装参考官网文档 https://gradle.org/install/
            安装包下载地址 http://services.gradle.org/distributions/
            
搭建项目步骤

![Image text](https://github.com/shanewds/Image/blob/master/image/TfourProbe-one.png) 

![Image text](https://github.com/shanewds/Image/blob/master/image/TfourProbe-two.png) 

![Image text](https://github.com/shanewds/Image/blob/master/image/TfourProbe-three.png) 

![Image text](https://github.com/shanewds/Image/blob/master/image/TfourProbe-four.png) 


注意事项：

  1.idea 需要手动的去安装lombok插件
     ![Image text](https://github.com/shanewds/Image/blob/master/image/lombok-idea-install.png) 

  2.idea 使用Lombok编译时无法找到get/set方法,解决方法
    ![Image text](https://github.com/shanewds/Image/blob/master/image/lombok-idea-get.png) 
  3.durid数据源登录
    http://localhost:8082/TfourProbe/druid/login.html
    ![Image text](https://github.com/shanewds/Image/blob/master/image/durid-query-sql.png) 
    ![Image text](https://github.com/shanewds/Image/blob/master/image/durid-controller-count.png)
  4.gradle整合springboot打成jar和war包的方式
    ![Image text](https://github.com/shanewds/Image/blob/master/image/gradle-springboot-jar:war.png)
    
  5.添加ehcache缓存 此配置设置过期时间2分钟
  
     需要添加依赖的jar
     
     implementation 'net.sf.ehcache:ehcache:2.10.6'
     implementation 'org.springframework.boot:spring-boot-starter-cache:2.1.2.RELEASE'
  
    application.yml
    #缓冲的配置
    cache:
      type: ehcache
      ehcache:
      config: classpath:ehcache.xml
      
     然后，在springboot启动类添加启动缓冲注解 @EnableCaching
     
  6.添加日志(此配置文件设置的级别是info,所以log.debug("debug测试")不会打印)
    命名文件logback.xml，无须在application.yml文件中进行加载logback.xml,springboot会默认的加载
    
    需要在java类中定义 private static final Logger log = LoggerFactory.getLogger(类名.class);
    
    生成的目录结构
    
    
    
    
    
 ![Image text](https://github.com/shanewds/Image/blob/master/image/log.png) 
