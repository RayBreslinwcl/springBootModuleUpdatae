# 3.@Autowired 、@Resource

@Component将一个类声明为Bean，将此类的实例化、属性赋值都交由IOC容器进行管理，

而@Autowired和@Resouce则表示从IOC容器中取出实例化、初始化后的对象，之后就可以直接使用此对象进行属性访问和方法调用了；

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>示例</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-keyword" style="color: rgb(0, 0, 255);">package</span> com.tzc.springboot.service.impl;

<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.tzc.springboot.entity.Role;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.tzc.springboot.entity.RoleMenu;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.tzc.springboot.mapper.RoleMapper;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.tzc.springboot.mapper.RoleMenuMapper;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.tzc.springboot.service.IRoleService;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> org.springframework.stereotype.Service;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> org.springframework.transaction.annotation.Transactional;

<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> javax.annotation.Resource;
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">import</span> java.util.List;

<span class="hljs-comment" style="color: rgb(0, 128, 0);">/**
 * &lt;p&gt;
 *  服务实现类
 * &lt;/p&gt;
 *
 * <span class="hljs-doctag" style="color: rgb(128, 128, 128);">@author</span> Mr汤
 * <span class="hljs-doctag" style="color: rgb(128, 128, 128);">@since</span> 2023-05-27
 */</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Service</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">class</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">RoleServiceImpl</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">extends</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">ServiceImpl</span>&lt;RoleMapper, Role&gt; <span class="hljs-keyword" style="color: rgb(0, 0, 255);">implements</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">IRoleService</span> {

    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@Resource</span>
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> RoleMenuMapper roleMenuMapper;

    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional</span>  <span class="hljs-comment" style="color: rgb(0, 128, 0);">//保证下面的两个操作要么全部成功，要么全部失败</span>
    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@Override</span>
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">void</span> <span class="hljs-title function_" style="color: rgb(163, 21, 21);">setRoleMenu</span><span class="hljs-params">(Integer roleId, List&lt;Integer&gt; menuIds)</span> {
        <span class="hljs-comment" style="color: rgb(0, 128, 0);">//先删除当前角色id所有的绑定关系</span>
        QueryWrapper&lt;RoleMenu&gt; queryWrapper = <span class="hljs-keyword" style="color: rgb(0, 0, 255);">new</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">QueryWrapper</span>&lt;&gt;();
        queryWrapper.eq(<span class="hljs-string" style="color: rgb(163, 21, 21);">"role_id"</span>,roleId);
        roleMenuMapper.delete(queryWrapper);

        <span class="hljs-comment" style="color: rgb(0, 128, 0);">//再把前端传过来的菜单id数组绑定到当前的这个角色id上去</span>
        <span class="hljs-keyword" style="color: rgb(0, 0, 255);">for</span>(Integer menuId : menuIds){
            <span class="hljs-type" style="color: rgb(163, 21, 21);">RoleMenu</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">roleMenu</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">new</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">RoleMenu</span>();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@Override</span>
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> List&lt;Integer&gt; <span class="hljs-title function_" style="color: rgb(163, 21, 21);">getRoleMenu</span><span class="hljs-params">(Integer roleId)</span> {
        <span class="hljs-keyword" style="color: rgb(0, 0, 255);">return</span> roleMenuMapper.selectByRoleId(roleId);
    }
}</code></pre></details>

```java
 package com.tzc.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tzc.springboot.entity.Role;
import com.tzc.springboot.entity.RoleMenu;
import com.tzc.springboot.mapper.RoleMapper;
import com.tzc.springboot.mapper.RoleMenuMapper;
import com.tzc.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr汤
 * @since 2023-05-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Transactional  //保证下面的两个操作要么全部成功，要么全部失败
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        //先删除当前角色id所有的绑定关系
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        roleMenuMapper.delete(queryWrapper);

        //再把前端传过来的菜单id数组绑定到当前的这个角色id上去
        for(Integer menuId : menuIds){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
```



 

## 补充——@Autowired和@Resouce的区别

 

**参考文章：https://blog.csdn.net/xhbzl/article/details/126765893**

### 1. 来源不同：@Autowired 来自 Spring 框架，而 @Resource 来自于（Java）JSR-250；

### 2. 依赖查找的顺序不同：@Autowired 先根据类型再根据名称查询，而 @Resource 先根据名称再根据类型查询；

### 3. 支持的参数不同：@Autowired 只支持设置 1 个参数，而 @Resource 支持设置 7 个参数；

### 4. 依赖注入的用法支持不同：@Autowired 既支持构造方法注入，又支持属性注入和 Setter 注入，而 @Resource 只支持属性注入和 Setter 注入；

### 5. 编译器 IDEA 的提示不同：当注入 Mapper 对象时，使用 @Autowired 注解编译器会提示错误，而使用 @Resource 注解则不会提示错误。

 

当一个接口存在多个实现类的情况下，`@Autowired` 和`@Resource`都需要通过名称才能正确匹配到对应的 Bean。`Autowired` 可以通过 `@Qualifier` 注解来显式指定名称，`@Resource`可以通过 `name` 属性来显式指定名称。

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>示例</summary><pre class="language-java highlighter-hljs" highlighted="true" has-selection="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 报错，byName 和 byType 都无法匹配到 bean</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Autowired</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> SmsService smsService;
<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 正确注入 SmsServiceImpl1 对象对应的 bean</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Autowired</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> SmsService smsServiceImpl1;
<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 正确注入  SmsServiceImpl1 对象对应的 bean</span>
<span class="hljs-comment" style="color: rgb(0, 128, 0);">// smsServiceImpl1 就是我们上面所说的名称</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Autowired</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Qualifier(value = "smsServiceImpl1")</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> SmsService smsService;




<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 报错，byName 和 byType 都无法匹配到 bean</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Resource</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> SmsService smsService;
<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 正确注入 SmsServiceImpl1 对象对应的 bean</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Resource</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> SmsService smsServiceImpl1;
<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 正确注入 SmsServiceImpl1 对象对应的 bean（比较推荐这种方式）</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Resource(name = "smsServiceImpl1")</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> SmsService smsService;</code></pre></details>

 

```java
 // 报错，byName 和 byType 都无法匹配到 bean
@Autowired
private SmsService smsService;
// 正确注入 SmsServiceImpl1 对象对应的 bean
@Autowired
private SmsService smsServiceImpl1;
// 正确注入  SmsServiceImpl1 对象对应的 bean
// smsServiceImpl1 就是我们上面所说的名称
@Autowired
@Qualifier(value = "smsServiceImpl1")
private SmsService smsService;




// 报错，byName 和 byType 都无法匹配到 bean
@Resource
private SmsService smsService;
// 正确注入 SmsServiceImpl1 对象对应的 bean
@Resource
private SmsService smsServiceImpl1;
// 正确注入 SmsServiceImpl1 对象对应的 bean（比较推荐这种方式）
@Resource(name = "smsServiceImpl1")
private SmsService smsService;
```

