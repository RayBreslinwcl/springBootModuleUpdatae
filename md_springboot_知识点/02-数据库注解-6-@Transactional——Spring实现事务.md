## 4.@Transactional——Spring实现事务

一般用于标识service业务层Impl实现方法，标识的方法内所有语句要不全部成功执行，要不全都不执行（也可以标识service业务层类，相当于给类中的所有方法都标识上）

 

### ①属性1——只读readOnly

对一个查询操作来说，如果我们把它设置成只读，就能够明确告诉数据库，这个操作不涉及写操作。这样数据库就能够针对查询操作来进行优化；

对增删改操作设置只读会抛出下面异常Caused by: java.sql.SQLException: Connection is read-only. Queries leading to data modificationare not allowed

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>示例</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(readOnly = true)</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">void</span> <span class="hljs-title function_" style="color: rgb(163, 21, 21);">buyBook</span><span class="hljs-params">(Integer bookId, Integer userId)</span> {
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//查询图书的价格</span>
    <span class="hljs-type" style="color: rgb(163, 21, 21);">Integer</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">price</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> bookDao.getPriceByBookId(bookId);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//更新图书的库存</span>
    bookDao.updateStock(bookId);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//更新用户的余额</span>
    bookDao.updateBalance(userId, price);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//System.out.println(1/0);</span>
}</code></pre></details>

## 

```java
 @Transactional(readOnly = true)
public void buyBook(Integer bookId, Integer userId) {
    //查询图书的价格
    Integer price = bookDao.getPriceByBookId(bookId);
    //更新图书的库存
    bookDao.updateStock(bookId);
    //更新用户的余额
    bookDao.updateBalance(userId, price);
    //System.out.println(1/0);
}
```



 

### ②属性2——超时timeout

事务在执行过程中，有可能因为遇到某些问题，导致程序卡住，从而长时间占用数据库资源，此时这个很可能出问题的程序应该被回滚，撤销它已做的操作，事务结束，把资源让出来，让其他正常

程序可以执行，概括来说就是一句话：超时回滚，释放资源。

```java
@Transactional(timeout = 3)
public void buyBook(Integer bookId, Integer userId) {
    try {
        TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    //查询图书的价格
    Integer price = bookDao.getPriceByBookId(bookId);
    //更新图书的库存
    bookDao.updateStock(bookId);
    //更新用户的余额
    bookDao.updateBalance(userId, price);
    //System.out.println(1/0);
}


//若发生超时则会在执行过程中抛出异常：
//org.springframework.transaction.TransactionTimedOutException: Transaction timed out:deadline was Fri Jun 04 16:25:39 CST 2022
```



<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>查看代码</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;"><span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(timeout = 3)</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">void</span> <span class="hljs-title function_" style="color: rgb(163, 21, 21);">buyBook</span><span class="hljs-params">(Integer bookId, Integer userId)</span> {
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">try</span> {
        TimeUnit.SECONDS.sleep(<span class="hljs-number" style="color: rgb(136, 0, 0);">5</span>);
    } <span class="hljs-keyword" style="color: rgb(0, 0, 255);">catch</span> (InterruptedException e) {
        e.printStackTrace();
    }
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//查询图书的价格</span>
    <span class="hljs-type" style="color: rgb(163, 21, 21);">Integer</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">price</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> bookDao.getPriceByBookId(bookId);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//更新图书的库存</span>
    bookDao.updateStock(bookId);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//更新用户的余额</span>
    bookDao.updateBalance(userId, price);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//System.out.println(1/0);</span>
}


<span class="hljs-comment" style="color: rgb(0, 128, 0);">//若发生超时则会在执行过程中抛出异常：</span>
<span class="hljs-comment" style="color: rgb(0, 128, 0);">//org.springframework.transaction.TransactionTimedOutException: Transaction timed out:deadline was Fri Jun 04 16:25:39 CST 2022</span></code></pre></details>

 

 

 

### ③属性3——回滚策略

声明式事务默认只针对运行时异常回滚，编译时异常不回滚。

可以通过@Transactional中相关属性设置回滚策略

rollbackFor属性：需要设置一个Class类型的对象

rollbackForClassName属性：需要设置一个字符串类型的全类名

noRollbackFor属性：需要设置一个Class类型的对象

rollbackFor属性：需要设置一个字符串类型的全类名

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>示例</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(noRollbackFor = ArithmeticException.class)</span>
<span class="hljs-comment" style="color: rgb(0, 128, 0);">//@Transactional(noRollbackForClassName = "java.lang.ArithmeticException")</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">void</span> <span class="hljs-title function_" style="color: rgb(163, 21, 21);">buyBook</span><span class="hljs-params">(Integer bookId, Integer userId)</span> {
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//查询图书的价格</span>
    <span class="hljs-type" style="color: rgb(163, 21, 21);">Integer</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">price</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> bookDao.getPriceByBookId(bookId);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//更新图书的库存</span>
    bookDao.updateStock(bookId);
    <span class="hljs-comment" style="color: rgb(0, 128, 0);">//更新用户的余额</span>
    bookDao.updateBalance(userId, price);
    System.out.println(<span class="hljs-number" style="color: rgb(136, 0, 0);">1</span>/<span class="hljs-number" style="color: rgb(136, 0, 0);">0</span>);
}</code></pre></details>

虽然购买图书功能中出现了数学运算异常（ArithmeticException），但是我们设置的回滚策略是，当出现ArithmeticException不发生回滚，因此购买图书的操作正常执行

 ```java
 @Transactional(noRollbackFor = ArithmeticException.class)
//@Transactional(noRollbackForClassName = "java.lang.ArithmeticException")
public void buyBook(Integer bookId, Integer userId) {
    //查询图书的价格
    Integer price = bookDao.getPriceByBookId(bookId);
    //更新图书的库存
    bookDao.updateStock(bookId);
    //更新用户的余额
    bookDao.updateBalance(userId, price);
    System.out.println(1/0);
}
 ```



 

 

### ④属性4——事务隔离级别isolation

隔离级别一共有四种：

读未提交：READ UNCOMMITTED
允许Transaction01读取Transaction02未提交的修改。

读已提交：READ COMMITTED、
要求Transaction01只能读取Transaction02已提交的修改。

可重复读：REPEATABLE READ
确保Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其它事务对这个字段进行更新。

串行化：SERIALIZABLE
确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其它事务对这个表进行添加、更新、删除操作。可以避免任何并发问题，但性能十分低下。

![img](https://img2023.cnblogs.com/blog/2832901/202308/2832901-20230825161821293-2042577062.png)

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>示例</summary><pre class="language-java highlighter-hljs" highlighted="true" has-selection="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(isolation = Isolation.DEFAULT)</span><span class="hljs-comment" style="color: rgb(0, 128, 0);">//使用数据库默认的隔离级别</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(isolation = Isolation.READ_UNCOMMITTED)</span><span class="hljs-comment" style="color: rgb(0, 128, 0);">//读未提交</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(isolation = Isolation.READ_COMMITTED)</span><span class="hljs-comment" style="color: rgb(0, 128, 0);">//读已提交</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(isolation = Isolation.REPEATABLE_READ)</span><span class="hljs-comment" style="color: rgb(0, 128, 0);">//可重复读</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Transactional(isolation = Isolation.SERIALIZABLE)</span><span class="hljs-comment" style="color: rgb(0, 128, 0);">//串行化</span></code></pre></details>

 

 ```java
@Transactional(isolation = Isolation.DEFAULT)//使用数据库默认的隔离级别
@Transactional(isolation = Isolation.READ_UNCOMMITTED)//读未提交
@Transactional(isolation = Isolation.READ_COMMITTED)//读已提交
@Transactional(isolation = Isolation.REPEATABLE_READ)//可重复读
@Transactional(isolation = Isolation.SERIALIZABLE)//串行化
 ```

