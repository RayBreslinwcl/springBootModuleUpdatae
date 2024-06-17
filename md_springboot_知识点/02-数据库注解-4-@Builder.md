https://www.cnblogs.com/AvavaAva/p/17654435.html

## 3.@Builder

参考文章：https://blog.csdn.net/qq_39249094/article/details/120881578

lombok提供的注解，为实体类提供builder()方法——相当于一个全参构造器，允许使用.builder().build()的方式创建对象；

 

**示例：**

```java
@Data
@Builder
public class UserLoginRespDto {

    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "用户token")
    private String token;
}


return RestResp.ok(UserLoginRespDto.builder()
            .token(jwtUtils.generateToken(userInfo.getId(), SystemConfigConsts.NOVEL_FRONT_KEY))
            .uid(userInfo.getId())
            .nickName(userInfo.getNickName()).build());
```