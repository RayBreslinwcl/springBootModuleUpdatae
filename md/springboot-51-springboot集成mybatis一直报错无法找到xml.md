一、IDEA Maven Mybatis target下无 xml文件

## 1.首先检查配置（在pom.xml中）需要加入以下配置

    ```xml
     <build>
            <resources>
                <resource>
                    <directory>src/main/java</directory><!--所在的目录-->
                    <includes><!--包括目录下的.properties,.xml 文件都会扫描到-->
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>false</filtering>
                </resource>
                <resource>
                    <directory>src/main/resources</directory>
                    <includes>
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                        <include>**/*.tld</include>
                    </includes>
                    <filtering>false</filtering>
                </resource>
        </resources>
    </build>



## 2.点击如下配置

![image-20210228211012833](springboot-51-springboot%E9%9B%86%E6%88%90mybatis%E4%B8%80%E7%9B%B4%E6%8A%A5%E9%94%99%E6%97%A0%E6%B3%95%E6%89%BE%E5%88%B0xml.assets/image-20210228211012833.png)

这时候在target下面应该会出现

![在这里插入图片描述](https://img-blog.csdnimg.cn/2021020811473724.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p6al9jX2xvdmU=,size_16,color_FFFFFF,t_70)

## 3. 如果还没出现，点击Build

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210208114644389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2p6al9jX2xvdmU=,size_16,color_FFFFFF,t_70)

## 4. 如果还是不行，可以将这两个文件直接复制到target响应的位置，一般来说这步用不到



# 二、参考

## 1.[IDEA Maven Mybatis target下无 xml文件](https://blog.csdn.net/jzj_c_love/article/details/113754460)



# 三、总结

## 1.当报错无法dao层的接口没有实现xml的时候，大部分都是这个问题了。