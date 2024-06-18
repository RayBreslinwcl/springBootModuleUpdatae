# [Postman接口测试之POST、GET请求方法](https://blog.csdn.net/weixin_42580704/article/details/126990175)

weixin_42580704

于 2022-09-22 15:05:50 发布

阅读量6.5k
 收藏 17

点赞数 4
文章标签： postman restful 服务器
版权

# 一、基础知识

## 1.HTTP的五种请求方法：GET， POST ，HEAD，OPTIONS， PUT， DELETE， TRACE 和 CONNECT 方法。

**GET请求**：请求指定的页面信息，并返回实体主体。（通常用来接收数据）。

**POST请求**：向指定资源提交数据进行处理请求，数据被包含在请求体中。POST请求可能会导致新的资源的建立、已有资源的修改。（通常用来发送数据）。

**HEAD请求**：类似于get请求，只不过返回的响应中没有具体的内容，用于获取报头。

**PUT请求**：从客户端向服务器传送的数据取代指定的文档的内容。

**DELETE请求**：请求服务器删除指定的页面。

**CONNECT请求**：能够将连接改为管道方式的代理服务器。

**OPTIONS请求**：允许客户端查看服务器的性能。

**TRACE请求**：显示服务器收到的请求，主要用于测试或诊断。


## 2.接口调用传参方式：key-value参数形式，Json串传参形式。

key-value参数：把参数拼接在url的后面由?相连，多个参数之间用&相连。

Json串传参：需要在请求的body写中，入Json格式参数。

# 二、接口文档

## 1.GET请求接口文档

功能说明：用户注册

请求URL： http://demo/web-member/auth/memberRegisteredByMobile

请求方式：GET

参数：

![在这里插入图片描述](https://img-blog.csdnimg.cn/2a2226d916cc4ce09af510600d6ddf48.png)

返回示例：无

返回参数说明：
　　![在这里插入图片描述](https://img-blog.csdnimg.cn/a3c6b9deb8da44d8bb9bcaacaf17c2d3.png)

## 2.POST请求接口文档

功能说明：用户登录

请求URL： http://demo/web-member/auth/memberLogin

请求方式：POST

参数：

![在这里插入图片描述](https://img-blog.csdnimg.cn/c060cd87ce7444d48f06c69c77611c82.png)

返回示例：无

返回参数说明：　

![在这里插入图片描述](https://img-blog.csdnimg.cn/b17e263a9df844c18a466959bc408766.png)

# 三、Postman接口操作

## 1.GET请求操作：

![在这里插入图片描述](https://img-blog.csdnimg.cn/a1d7af278f48482cac20552a0a9eaae5.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/b5b28150292247929d9c9aa81fcd2128.png)

GET请求操作结果：

![在这里插入图片描述](https://img-blog.csdnimg.cn/38e525fe8ae145fb9c2282d6b46d004f.png)

## 3.POST请求操作：

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ac15afc19aa4906b06931fa6a53d6d8.png)

POST请求操作结果：

![在这里插入图片描述](https://img-blog.csdnimg.cn/c763d413e1894eccb3eeb27f0286b587.png)



## 4.POST请求from-data

multipart/form-data，它将表单的数据组织成Key-Value形式，也可以上传文件，当上传的字段是文件时，会有 content-type 来说明文件类型；content-disposition，用来说明字段的一些信息；由于有 boundary 隔离，所以 multipart/form-data 既可以上传文件，也可以上传键值对，它采用了键值对的方式，所以可以上传多个文件。

正常数据输入post请求地址，选择form-data请求类型，输入对应参数，点击Send发送请求

![在这里插入图片描述](https://img-blog.csdnimg.cn/86a0e32da64e4ea9a44eb69d037607d9.png)

POST请求form-data上传文件

选择File格式

![在这里插入图片描述](https://img-blog.csdnimg.cn/8c87f2e9159648b4af7c085377a57d48.png)

点击上传文件，发送请求

这里我选择了上传二进制文件，其他的都是一样的内容

![在这里插入图片描述](https://img-blog.csdnimg.cn/2c5caaaf907748aba96ea9e5eaf3eeda.png)

## 5.POST请求x-www-form-urlencoded

application/x-www-from-urlencoded，将表单内的数据转换为Key-Value



![在这里插入图片描述](https://img-blog.csdnimg.cn/c7552798c3c04c89ab2fd846e8eeb3f4.png)

可以通过返回内容看出来，我们需要请求的数据类型是否正确

![在这里插入图片描述](https://img-blog.csdnimg.cn/0e09ea25e362496898358c4589eb2d7f.png)

## 6.POST请求raw

可以通过raw进行传输txt，json xml，html的数据

### xml方法

![在这里插入图片描述](https://img-blog.csdnimg.cn/dc95bd083df24a458264574fb9b39203.png)

查看返回内容

![在这里插入图片描述](https://img-blog.csdnimg.cn/d60993370c9a468c973ef6e4b39d742d.png)

### json数据

![在这里插入图片描述](https://img-blog.csdnimg.cn/ee6c48149a9141f8a43253812adb5c22.png)

我们通过请求后，继续查看返回后的内容，发现现实的数据类型也是json的

![在这里插入图片描述](https://img-blog.csdnimg.cn/b257491f49b049a5aa58bf6d92d27d4c.png)

### binary

表示只可以上传二进制数据，用来上传文件，一次只能上传1个数据

给大家举个小栗子，桌面创建二进制文件，保存在桌面，后缀名为.bin格式

上传创建好的二进制文件，查看返回内容

![在这里插入图片描述](https://img-blog.csdnimg.cn/3769bb36b732469ab2bf65e35ba61a9e.png)

其中肯定会有人问，form-data和x-www-form-urlencoded有什么区别呢？

form-data：既可以上传文件等二进制数据，也可以上传表单键值对。

x-www-form-urlencoded：只能上传键值对，不能用于文件上传。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/weixin_42580704/article/details/126990175