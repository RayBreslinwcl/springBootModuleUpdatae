## 1.启动cn.abel.Application



## 2.接口

### （1）获取一个id的结果

http://localhost:8009/queryUserById
结果

```json
{"id":1,"name":"admin","address":"shanghai","mobile":"123456","email":null,"createTime":null,"role":1}
```



### （2）获取所有数据

http://localhost:8009/queryAllUsers

结果：

```json
[{"id":1,"name":"admin","address":"shanghai","mobile":"123456","email":null,"createTime":null,"role":1},{"id":2,"name":"admin2","address":"pudong","mobile":"223456","email":null,"createTime":null,"role":2}]
```





## 3.重点：跨域问题CROS参考

### 

#### [前后端跨域问题解决： No ‘Access-Control-Allow-Origin’ header is present on the requested resource](https://blog.csdn.net/u010886217/article/details/126711645?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22126711645%22%2C%22source%22%3A%22u010886217%22%7D

)