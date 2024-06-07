# [解决Could not autowire.No beans of ‘ ‘ type found](https://blog.csdn.net/loler15/article/details/114924159)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210317123304592.png)

在注入类的时候，@Autowired可能会出现
类似于
此类问题
出现此情况有多种解决方法

# 文章目录

## 解决1:启动类位置

解决方法 ：将启动类或者要注入的类放到启动类的同级或子级包内

## 解决2:降低Bean的安全级别

## 解决3：将@Autowired改为@Resource

## 解决1:启动类位置

可以查看本地启动类放置位置
启动类一般要放在最外层的根目录位置
这样才能扫到同级别以及子级的类

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210317124044461.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvbGVyMTU=,size_16,color_FFFFFF,t_70)

同上图，如果你的类放在gdcp包外，启动类是扫描不到的

解决方法 ：将启动类或者要注入的类放到启动类的同级或子级包内

## 解决2:降低Bean的安全级别

此问题是建立在：程序能正常运行，但是依旧有红色波浪线报错，但是无影响单纯是看红色碍眼

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210317124550333.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvbGVyMTU=,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210317124555607.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvbGVyMTU=,size_16,color_FFFFFF,t_70)

降低安全级别后，恢复正常
此问题是Spring扫描问题，不造成太大影响

## 解决3：将@Autowired改为@Resource

两个注解的区别是一个是@Autowired是Spring，@Resource是J2EE的
使用@Resource能减少Spring耦合度
@AutoWried按by type自动注入，而@Resource默认按byName自动注入。
@Resource的查询注入顺序是，去Bean中查找Name，如果查不到就去查Class，其次再从属性去查找，如果我们定义的类中有相同的Name可能会报错，因为查询到了多个。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/loler15/article/details/114924159