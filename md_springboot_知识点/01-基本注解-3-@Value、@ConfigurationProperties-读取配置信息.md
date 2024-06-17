## 2.@Value、@ConfigurationProperties——读取配置信息

```yml
wuhan2020: 2020年初武汉爆发了新型冠状病毒，疫情严重，但是，我相信一切都会过去！武汉加油！中国加油！

my-profile:
  name: Guide哥
  email: koushuangbwcx@163.com

library:
  location: 湖北武汉加油中国加油
  books:
    - name: 天才基本法
      description: 二十二岁的林朝夕在父亲确诊阿尔茨海默病这天，得知自己暗恋多年的校园男神裴之即将出国深造的消息——对方考取的学校，恰是父亲当年为她放弃的那所。
    - name: 时间的秩序
      description: 为什么我们记得过去，而非未来？时间“流逝”意味着什么？是我们存在于时间之内，还是时间存在于我们之中？卡洛·罗韦利用诗意的文字，邀请我们思考这一亘古难题——时间的本质。
    - name: 了不起的我
      description: 如何养成一个新习惯？如何让心智变得更成熟？如何拥有高质量的关系？ 如何走出人生的艰难时刻？
```



<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>application.yml</summary><pre class="language-yaml highlighter-hljs" highlighted="true" has-selection="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-yaml hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-attr" style="color: rgb(255, 0, 0);">wuhan2020:</span> <span class="hljs-number" style="color: rgb(136, 0, 0);">2020</span><span class="hljs-string" style="color: rgb(163, 21, 21);">年初武汉爆发了新型冠状病毒，疫情严重，但是，我相信一切都会过去！武汉加油！中国加油！</span>

<span class="hljs-attr" style="color: rgb(255, 0, 0);">my-profile:</span>
  <span class="hljs-attr" style="color: rgb(255, 0, 0);">name:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">Guide哥</span>
  <span class="hljs-attr" style="color: rgb(255, 0, 0);">email:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">koushuangbwcx@163.com</span>

<span class="hljs-attr" style="color: rgb(255, 0, 0);">library:</span>
  <span class="hljs-attr" style="color: rgb(255, 0, 0);">location:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">湖北武汉加油中国加油</span>
  <span class="hljs-attr" style="color: rgb(255, 0, 0);">books:</span>
    <span class="hljs-bullet" style="color: rgb(0, 176, 232);">-</span> <span class="hljs-attr" style="color: rgb(255, 0, 0);">name:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">天才基本法</span>
      <span class="hljs-attr" style="color: rgb(255, 0, 0);">description:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">二十二岁的林朝夕在父亲确诊阿尔茨海默病这天，得知自己暗恋多年的校园男神裴之即将出国深造的消息——对方考取的学校，恰是父亲当年为她放弃的那所。</span>
    <span class="hljs-bullet" style="color: rgb(0, 176, 232);">-</span> <span class="hljs-attr" style="color: rgb(255, 0, 0);">name:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">时间的秩序</span>
      <span class="hljs-attr" style="color: rgb(255, 0, 0);">description:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">为什么我们记得过去，而非未来？时间“流逝”意味着什么？是我们存在于时间之内，还是时间存在于我们之中？卡洛·罗韦利用诗意的文字，邀请我们思考这一亘古难题——时间的本质。</span>
    <span class="hljs-bullet" style="color: rgb(0, 176, 232);">-</span> <span class="hljs-attr" style="color: rgb(255, 0, 0);">name:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">了不起的我</span>
      <span class="hljs-attr" style="color: rgb(255, 0, 0);">description:</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">如何养成一个新习惯？如何让心智变得更成熟？如何拥有高质量的关系？</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">如何走出人生的艰难时刻？</span></code></pre></details>

 

### ①@Value

使用 `@Value("${property}")` 读取比较简单的配置信息：

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>查看代码</summary><pre class="language-yaml highlighter-hljs" highlighted="true" has-selection="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-yaml hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-string" style="color: rgb(163, 21, 21);">@Value("${wuhan2020}")</span>
<span class="hljs-string" style="color: rgb(163, 21, 21);">String</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">wuhan2020;</span></code></pre></details>

 

 ```java
@Value("${wuhan2020}")
String wuhan2020;
 ```



 

### ②@ConfigurationProperties

通过`@ConfigurationProperties`读取配置信息并与 bean 绑定

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>查看代码</summary><pre class="language-yaml highlighter-hljs" highlighted="true" has-selection="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-yaml hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-string" style="color: rgb(163, 21, 21);">@Component</span>
<span class="hljs-string" style="color: rgb(163, 21, 21);">@ConfigurationProperties(prefix</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">=</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">"library"</span><span class="hljs-string" style="color: rgb(163, 21, 21);">)</span>
<span class="hljs-string" style="color: rgb(163, 21, 21);">class</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">LibraryProperties</span> {
    <span class="hljs-string" style="color: rgb(163, 21, 21);">@NotEmpty</span>
    <span class="hljs-string" style="color: rgb(163, 21, 21);">private</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">String</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">location;</span>
    <span class="hljs-string" style="color: rgb(163, 21, 21);">private</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">List&lt;Book&gt;</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">books;</span>

    <span class="hljs-string" style="color: rgb(163, 21, 21);">@Setter</span>
    <span class="hljs-string" style="color: rgb(163, 21, 21);">@Getter</span>
    <span class="hljs-string" style="color: rgb(163, 21, 21);">@ToString</span>
    <span class="hljs-string" style="color: rgb(163, 21, 21);">static</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">class</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">Book</span> {
        <span class="hljs-string" style="color: rgb(163, 21, 21);">String</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">name;</span>
        <span class="hljs-string" style="color: rgb(163, 21, 21);">String</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">description;</span>
    }
  <span class="hljs-string" style="color: rgb(163, 21, 21);">省略getter/setter</span>
  <span class="hljs-string" style="color: rgb(163, 21, 21);">......</span>
}</code></pre></details>

 

```java
@Component
@ConfigurationProperties(prefix = "library")
class LibraryProperties {
    @NotEmpty
    private String location;
    private List<Book> books;

    @Setter
    @Getter
    @ToString
    static class Book {
        String name;
        String description;
    }
  省略getter/setter
  ......
}
```

