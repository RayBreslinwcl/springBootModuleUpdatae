https://www.cnblogs.com/AvavaAva/p/17654435.html

## 5.@ConditionalOnProperty——控制配置生效的条件

 

参考文章：https://www.cnblogs.com/secbro/p/12011522.html

 

### ①注解分析

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>@ConditionalOnProperty注解类源码如下：</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Retention(RetentionPolicy.RUNTIME)</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Target({ ElementType.TYPE, ElementType.METHOD })</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Documented</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Conditional(OnPropertyCondition.class)</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-meta" style="color: rgb(43, 145, 175);">@interface</span> ConditionalOnProperty {

	<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 数组，获取对应property名称的值，与name不可同时使用</span>
	String[] value() <span class="hljs-keyword" style="color: rgb(0, 0, 255);">default</span> {};

	<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 配置属性名称的前缀，比如spring.http.encoding</span>
	String <span class="hljs-title function_" style="color: rgb(163, 21, 21);">prefix</span><span class="hljs-params">()</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">default</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">""</span>;

	<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 数组，配置属性完整名称或部分名称</span>
	<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 可与prefix组合使用，组成完整的配置属性名称，与value不可同时使用</span>
	String[] name() <span class="hljs-keyword" style="color: rgb(0, 0, 255);">default</span> {};

	<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置</span>
	String <span class="hljs-title function_" style="color: rgb(163, 21, 21);">havingValue</span><span class="hljs-params">()</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">default</span> <span class="hljs-string" style="color: rgb(163, 21, 21);">""</span>;

	<span class="hljs-comment" style="color: rgb(0, 128, 0);">// 缺少该配置属性时是否可以加载。如果为true，没有该配置属性时也会正常加载；反之则不会生效</span>
	<span class="hljs-type" style="color: rgb(163, 21, 21);">boolean</span> <span class="hljs-title function_" style="color: rgb(163, 21, 21);">matchIfMissing</span><span class="hljs-params">()</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">default</span> <span class="hljs-literal" style="color: rgb(163, 21, 21);">false</span>;

}</code></pre></details>

```java
 @Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {

	// 数组，获取对应property名称的值，与name不可同时使用
	String[] value() default {};

	// 配置属性名称的前缀，比如spring.http.encoding
	String prefix() default "";

	// 数组，配置属性完整名称或部分名称
	// 可与prefix组合使用，组成完整的配置属性名称，与value不可同时使用
	String[] name() default {};

	// 可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
	String havingValue() default "";

	// 缺少该配置属性时是否可以加载。如果为true，没有该配置属性时也会正常加载；反之则不会生效
	boolean matchIfMissing() default false;

}
```



**总结：**

首先看matchIfMissing属性，默认情况下matchIfMissing为false，也就是说如果未进行属性配置，则自动配置不生效。如果matchIfMissing为true，则表示如果没有对应的属性配置，则自动配置默认生效；

接着看prefix和name，这两项确定了其在配置文件中对应的字段；

最后看havingValue，当配置文件中的字段值等于havingValue的值时，配置生效，若不相同则配置不生效；

 

 

 

 

### ②示例

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>DbSearchServiceImpl implements SearchService</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;"><span class="hljs-meta" style="color: rgb(43, 145, 175);">@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enabled", havingValue = "false")</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Service</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@RequiredArgsConstructor</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Slf4j</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">class</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">DbSearchServiceImpl</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">implements</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">SearchService</span> {

    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">final</span> BookInfoMapper bookInfoMapper;

    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@Override</span>
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> RestResp&lt;PageRespDto&lt;BookInfoRespDto&gt;&gt; <span class="hljs-title function_" style="color: rgb(163, 21, 21);">searchBooks</span><span class="hljs-params">(BookSearchReqDto condition)</span> {
        Page&lt;BookInfoRespDto&gt; page = <span class="hljs-keyword" style="color: rgb(0, 0, 255);">new</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">Page</span>&lt;&gt;();
        page.setCurrent(condition.getPageNum());
        page.setSize(condition.getPageSize());
        List&lt;BookInfo&gt; bookInfos = bookInfoMapper.searchBooks(page, condition);
        <span class="hljs-keyword" style="color: rgb(0, 0, 255);">return</span> RestResp.ok(
            PageRespDto.of(condition.getPageNum(), condition.getPageSize(), page.getTotal(),
                bookInfos.stream().map(v -&gt; BookInfoRespDto.builder()
                    .id(v.getId())
                    .bookName(v.getBookName())
                    .categoryId(v.getCategoryId())
                    .categoryName(v.getCategoryName())
                    .authorId(v.getAuthorId())
                    .authorName(v.getAuthorName())
                    .wordCount(v.getWordCount())
                    .lastChapterName(v.getLastChapterName())
                    .build()).toList()));
    }

}</code></pre></details>

 ```java
@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enabled", havingValue = "false")
@Service
@RequiredArgsConstructor
@Slf4j
public class DbSearchServiceImpl implements SearchService {

    private final BookInfoMapper bookInfoMapper;

    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {
        Page<BookInfoRespDto> page = new Page<>();
        page.setCurrent(condition.getPageNum());
        page.setSize(condition.getPageSize());
        List<BookInfo> bookInfos = bookInfoMapper.searchBooks(page, condition);
        return RestResp.ok(
            PageRespDto.of(condition.getPageNum(), condition.getPageSize(), page.getTotal(),
                bookInfos.stream().map(v -> BookInfoRespDto.builder()
                    .id(v.getId())
                    .bookName(v.getBookName())
                    .categoryId(v.getCategoryId())
                    .categoryName(v.getCategoryName())
                    .authorId(v.getAuthorId())
                    .authorName(v.getAuthorName())
                    .wordCount(v.getWordCount())
                    .lastChapterName(v.getLastChapterName())
                    .build()).toList()));
    }

}
 ```



<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>EsSearchServiceImpl implements SearchService</summary><pre class="language-java highlighter-hljs" highlighted="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;<span class="hljs-meta" style="color: rgb(43, 145, 175);">@ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enabled", havingValue = "true")</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Service</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@RequiredArgsConstructor</span>
<span class="hljs-meta" style="color: rgb(43, 145, 175);">@Slf4j</span>
<span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">class</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">EsSearchServiceImpl</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">implements</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">SearchService</span> {

    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">final</span> ElasticsearchClient esClient;

    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@SneakyThrows</span>
    <span class="hljs-meta" style="color: rgb(43, 145, 175);">@Override</span>
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">public</span> RestResp&lt;PageRespDto&lt;BookInfoRespDto&gt;&gt; <span class="hljs-title function_" style="color: rgb(163, 21, 21);">searchBooks</span><span class="hljs-params">(BookSearchReqDto condition)</span> {

        SearchResponse&lt;EsBookDto&gt; response = esClient.search(s -&gt; {

                SearchRequest.<span class="hljs-type" style="color: rgb(163, 21, 21);">Builder</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">searchBuilder</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> s.index(EsConsts.BookIndex.INDEX_NAME);
                <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 构建检索条件</span>
                buildSearchCondition(condition, searchBuilder);
                <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 排序</span>
                <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (!StringUtils.isBlank(condition.getSort())) {
                    searchBuilder.sort(o -&gt; o.field(f -&gt; f
                        .field(StringUtils.underlineToCamel(condition.getSort().split(<span class="hljs-string" style="color: rgb(163, 21, 21);">" "</span>)[<span class="hljs-number" style="color: rgb(136, 0, 0);">0</span>]))
                        .order(SortOrder.Desc))
                    );
                }
                <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 分页</span>
                searchBuilder.from((condition.getPageNum() - <span class="hljs-number" style="color: rgb(136, 0, 0);">1</span>) * condition.getPageSize())
                    .size(condition.getPageSize());
                <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 设置高亮显示</span>
                searchBuilder.highlight(h -&gt; h.fields(EsConsts.BookIndex.FIELD_BOOK_NAME,
                        t -&gt; t.preTags(<span class="hljs-string" style="color: rgb(163, 21, 21);">"&lt;em style='color:red'&gt;"</span>).postTags(<span class="hljs-string" style="color: rgb(163, 21, 21);">"&lt;/em&gt;"</span>))
                    .fields(EsConsts.BookIndex.FIELD_AUTHOR_NAME,
                        t -&gt; t.preTags(<span class="hljs-string" style="color: rgb(163, 21, 21);">"&lt;em style='color:red'&gt;"</span>).postTags(<span class="hljs-string" style="color: rgb(163, 21, 21);">"&lt;/em&gt;"</span>)));

                <span class="hljs-keyword" style="color: rgb(0, 0, 255);">return</span> searchBuilder;
            },
            EsBookDto.class
        );

        <span class="hljs-type" style="color: rgb(163, 21, 21);">TotalHits</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">total</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> response.hits().total();

        List&lt;BookInfoRespDto&gt; list = <span class="hljs-keyword" style="color: rgb(0, 0, 255);">new</span> <span class="hljs-title class_" style="color: rgb(163, 21, 21);">ArrayList</span>&lt;&gt;();
        List&lt;Hit&lt;EsBookDto&gt;&gt; hits = response.hits().hits();
        <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 类型推断 var 非常适合 for 循环，JDK 10 引入，JDK 11 改进</span>
        <span class="hljs-keyword" style="color: rgb(0, 0, 255);">for</span> (<span class="hljs-keyword" style="color: rgb(0, 0, 255);">var</span> hit : hits) {
            <span class="hljs-type" style="color: rgb(163, 21, 21);">EsBookDto</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">book</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> hit.source();
            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">assert</span> book != <span class="hljs-literal" style="color: rgb(163, 21, 21);">null</span>;
            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (!CollectionUtils.isEmpty(hit.highlight().get(EsConsts.BookIndex.FIELD_BOOK_NAME))) {
                book.setBookName(hit.highlight().get(EsConsts.BookIndex.FIELD_BOOK_NAME).get(<span class="hljs-number" style="color: rgb(136, 0, 0);">0</span>));
            }
            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (!CollectionUtils.isEmpty(
                hit.highlight().get(EsConsts.BookIndex.FIELD_AUTHOR_NAME))) {
                book.setAuthorName(
                    hit.highlight().get(EsConsts.BookIndex.FIELD_AUTHOR_NAME).get(<span class="hljs-number" style="color: rgb(136, 0, 0);">0</span>));
            }
            list.add(BookInfoRespDto.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .categoryId(book.getCategoryId())
                .categoryName(book.getCategoryName())
                .authorId(book.getAuthorId())
                .authorName(book.getAuthorName())
                .wordCount(book.getWordCount())
                .lastChapterName(book.getLastChapterName())
                .build());
        }
        <span class="hljs-keyword" style="color: rgb(0, 0, 255);">assert</span> total != <span class="hljs-literal" style="color: rgb(163, 21, 21);">null</span>;
        <span class="hljs-keyword" style="color: rgb(0, 0, 255);">return</span> RestResp.ok(
            PageRespDto.of(condition.getPageNum(), condition.getPageSize(), total.value(), list));

    }

    <span class="hljs-comment" style="color: rgb(0, 128, 0);">/**
     * 构建检索条件
     */</span>
    <span class="hljs-keyword" style="color: rgb(0, 0, 255);">private</span> <span class="hljs-keyword" style="color: rgb(0, 0, 255);">void</span> <span class="hljs-title function_" style="color: rgb(163, 21, 21);">buildSearchCondition</span><span class="hljs-params">(BookSearchReqDto condition,
        SearchRequest.Builder searchBuilder)</span> {

        <span class="hljs-type" style="color: rgb(163, 21, 21);">BoolQuery</span> <span class="hljs-variable" style="color: rgb(0, 128, 0);">boolQuery</span> <span class="hljs-operator" style="color: rgb(171, 86, 86);">=</span> BoolQuery.of(b -&gt; {

            <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 只查有字数的小说</span>
            b.must(RangeQuery.of(m -&gt; m
                .field(EsConsts.BookIndex.FIELD_WORD_COUNT)
                .gt(JsonData.of(<span class="hljs-number" style="color: rgb(136, 0, 0);">0</span>))
            )._toQuery());

            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (!StringUtils.isBlank(condition.getKeyword())) {
                <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 关键词匹配</span>
                b.must((q -&gt; q.multiMatch(t -&gt; t
                    .fields(EsConsts.BookIndex.FIELD_BOOK_NAME + <span class="hljs-string" style="color: rgb(163, 21, 21);">"^2"</span>,
                        EsConsts.BookIndex.FIELD_AUTHOR_NAME + <span class="hljs-string" style="color: rgb(163, 21, 21);">"^1.8"</span>,
                        EsConsts.BookIndex.FIELD_BOOK_DESC + <span class="hljs-string" style="color: rgb(163, 21, 21);">"^0.1"</span>)
                    .query(condition.getKeyword())
                )
                ));
            }

            <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 精确查询</span>
            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (Objects.nonNull(condition.getWorkDirection())) {
                b.must(TermQuery.of(m -&gt; m
                    .field(EsConsts.BookIndex.FIELD_WORK_DIRECTION)
                    .value(condition.getWorkDirection())
                )._toQuery());
            }

            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (Objects.nonNull(condition.getCategoryId())) {
                b.must(TermQuery.of(m -&gt; m
                    .field(EsConsts.BookIndex.FIELD_CATEGORY_ID)
                    .value(condition.getCategoryId())
                )._toQuery());
            }

            <span class="hljs-comment" style="color: rgb(0, 128, 0);">// 范围查询</span>
            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (Objects.nonNull(condition.getWordCountMin())) {
                b.must(RangeQuery.of(m -&gt; m
                    .field(EsConsts.BookIndex.FIELD_WORD_COUNT)
                    .gte(JsonData.of(condition.getWordCountMin()))
                )._toQuery());
            }

            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (Objects.nonNull(condition.getWordCountMax())) {
                b.must(RangeQuery.of(m -&gt; m
                    .field(EsConsts.BookIndex.FIELD_WORD_COUNT)
                    .lt(JsonData.of(condition.getWordCountMax()))
                )._toQuery());
            }

            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">if</span> (Objects.nonNull(condition.getUpdateTimeMin())) {
                b.must(RangeQuery.of(m -&gt; m
                    .field(EsConsts.BookIndex.FIELD_LAST_CHAPTER_UPDATE_TIME)
                    .gte(JsonData.of(condition.getUpdateTimeMin().getTime()))
                )._toQuery());
            }

            <span class="hljs-keyword" style="color: rgb(0, 0, 255);">return</span> b;

        });

        searchBuilder.query(q -&gt; q.bool(boolQuery));

    }
}</code></pre></details>



```java
 @ConditionalOnProperty(prefix = "spring.elasticsearch", name = "enabled", havingValue = "true")
@Service
@RequiredArgsConstructor
@Slf4j
public class EsSearchServiceImpl implements SearchService {

    private final ElasticsearchClient esClient;

    @SneakyThrows
    @Override
    public RestResp<PageRespDto<BookInfoRespDto>> searchBooks(BookSearchReqDto condition) {

        SearchResponse<EsBookDto> response = esClient.search(s -> {

                SearchRequest.Builder searchBuilder = s.index(EsConsts.BookIndex.INDEX_NAME);
                // 构建检索条件
                buildSearchCondition(condition, searchBuilder);
                // 排序
                if (!StringUtils.isBlank(condition.getSort())) {
                    searchBuilder.sort(o -> o.field(f -> f
                        .field(StringUtils.underlineToCamel(condition.getSort().split(" ")[0]))
                        .order(SortOrder.Desc))
                    );
                }
                // 分页
                searchBuilder.from((condition.getPageNum() - 1) * condition.getPageSize())
                    .size(condition.getPageSize());
                // 设置高亮显示
                searchBuilder.highlight(h -> h.fields(EsConsts.BookIndex.FIELD_BOOK_NAME,
                        t -> t.preTags("<em style='color:red'>").postTags("</em>"))
                    .fields(EsConsts.BookIndex.FIELD_AUTHOR_NAME,
                        t -> t.preTags("<em style='color:red'>").postTags("</em>")));

                return searchBuilder;
            },
            EsBookDto.class
        );

        TotalHits total = response.hits().total();

        List<BookInfoRespDto> list = new ArrayList<>();
        List<Hit<EsBookDto>> hits = response.hits().hits();
        // 类型推断 var 非常适合 for 循环，JDK 10 引入，JDK 11 改进
        for (var hit : hits) {
            EsBookDto book = hit.source();
            assert book != null;
            if (!CollectionUtils.isEmpty(hit.highlight().get(EsConsts.BookIndex.FIELD_BOOK_NAME))) {
                book.setBookName(hit.highlight().get(EsConsts.BookIndex.FIELD_BOOK_NAME).get(0));
            }
            if (!CollectionUtils.isEmpty(
                hit.highlight().get(EsConsts.BookIndex.FIELD_AUTHOR_NAME))) {
                book.setAuthorName(
                    hit.highlight().get(EsConsts.BookIndex.FIELD_AUTHOR_NAME).get(0));
            }
            list.add(BookInfoRespDto.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .categoryId(book.getCategoryId())
                .categoryName(book.getCategoryName())
                .authorId(book.getAuthorId())
                .authorName(book.getAuthorName())
                .wordCount(book.getWordCount())
                .lastChapterName(book.getLastChapterName())
                .build());
        }
        assert total != null;
        return RestResp.ok(
            PageRespDto.of(condition.getPageNum(), condition.getPageSize(), total.value(), list));

    }

    /**
     * 构建检索条件
     */
    private void buildSearchCondition(BookSearchReqDto condition,
        SearchRequest.Builder searchBuilder) {

        BoolQuery boolQuery = BoolQuery.of(b -> {

            // 只查有字数的小说
            b.must(RangeQuery.of(m -> m
                .field(EsConsts.BookIndex.FIELD_WORD_COUNT)
                .gt(JsonData.of(0))
            )._toQuery());

            if (!StringUtils.isBlank(condition.getKeyword())) {
                // 关键词匹配
                b.must((q -> q.multiMatch(t -> t
                    .fields(EsConsts.BookIndex.FIELD_BOOK_NAME + "^2",
                        EsConsts.BookIndex.FIELD_AUTHOR_NAME + "^1.8",
                        EsConsts.BookIndex.FIELD_BOOK_DESC + "^0.1")
                    .query(condition.getKeyword())
                )
                ));
            }

            // 精确查询
            if (Objects.nonNull(condition.getWorkDirection())) {
                b.must(TermQuery.of(m -> m
                    .field(EsConsts.BookIndex.FIELD_WORK_DIRECTION)
                    .value(condition.getWorkDirection())
                )._toQuery());
            }

            if (Objects.nonNull(condition.getCategoryId())) {
                b.must(TermQuery.of(m -> m
                    .field(EsConsts.BookIndex.FIELD_CATEGORY_ID)
                    .value(condition.getCategoryId())
                )._toQuery());
            }

            // 范围查询
            if (Objects.nonNull(condition.getWordCountMin())) {
                b.must(RangeQuery.of(m -> m
                    .field(EsConsts.BookIndex.FIELD_WORD_COUNT)
                    .gte(JsonData.of(condition.getWordCountMin()))
                )._toQuery());
            }

            if (Objects.nonNull(condition.getWordCountMax())) {
                b.must(RangeQuery.of(m -> m
                    .field(EsConsts.BookIndex.FIELD_WORD_COUNT)
                    .lt(JsonData.of(condition.getWordCountMax()))
                )._toQuery());
            }

            if (Objects.nonNull(condition.getUpdateTimeMin())) {
                b.must(RangeQuery.of(m -> m
                    .field(EsConsts.BookIndex.FIELD_LAST_CHAPTER_UPDATE_TIME)
                    .gte(JsonData.of(condition.getUpdateTimeMin().getTime()))
                )._toQuery());
            }

            return b;

        });

        searchBuilder.query(q -> q.bool(boolQuery));

    }
}
```



可知DbSearchServiceImpl和EsSearchServiceImpl都是对SearchService接口的实现，启动哪个实现类取决于配置文件中的spring.elasticsearch.enabled字段，

若其为true，则启用EsSearchServiceImpl，若其为false，则启用DbSearchServiceImpl；

 

 

<details open="" data-math-rendered="true" style="color: rgb(68, 68, 68); font-family: Tahoma, Arial, Helvetica, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; white-space: normal; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;"><summary>application.yml</summary><pre class="language-java highlighter-hljs" highlighted="true" has-selection="true" style="transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; overflow: auto; margin: 0px; padding: 0px; list-style-type: none; list-style-image: none; font-family: &quot;Courier New&quot;, Courier, monospace;"><code class="highlighter-hljs language-java hljs" style="font-family: &quot;Courier New&quot;, sans-serif; transition-duration: 0.2s; transition-property: background, font-size, border-color, border-radius, border-width, padding, margin, color; background: rgb(245, 245, 245); color: rgb(68, 68, 68); padding: 1em; display: block; font-size: 12px; border: 1px solid rgb(204, 204, 204); border-radius: 3px; overflow-x: auto; line-height: 1.5; margin: 0px;">&nbsp;spring:
  data:
    # Redis 配置
    redis:
      host: <span class="hljs-number" style="color: rgb(136, 0, 0);">192.168</span><span class="hljs-number" style="color: rgb(136, 0, 0);">.189</span><span class="hljs-number" style="color: rgb(136, 0, 0);">.129</span>
      port: <span class="hljs-number" style="color: rgb(136, 0, 0);">6379</span>
      password: <span class="hljs-number" style="color: rgb(136, 0, 0);">111111</span>

  # Elasticsearch 配置
  elasticsearch:
    # 是否开启 Elasticsearch 搜索引擎功能：<span class="hljs-literal" style="color: rgb(163, 21, 21);">true</span>-开启 <span class="hljs-literal" style="color: rgb(163, 21, 21);">false</span>-不开启
    enabled: <span class="hljs-literal" style="color: rgb(163, 21, 21);">false</span>
    uris:
      - https:<span class="hljs-comment" style="color: rgb(0, 128, 0);">//my-deployment-ce7ca3.es.us-central1.gcp.cloud.es.io:9243</span>
    username: elastic
    password: qTjgYVKSuExX6tWAsDuvuvwl</code></pre></details>

```yml
 spring:
  data:
    # Redis 配置
    redis:
      host: 192.168.189.129
      port: 6379
      password: 111111

  # Elasticsearch 配置
  elasticsearch:
    # 是否开启 Elasticsearch 搜索引擎功能：true-开启 false-不开启
    enabled: false
    uris:
      - https://my-deployment-ce7ca3.es.us-central1.gcp.cloud.es.io:9243
    username: elastic
    password: qTjgYVKSuExX6tWAsDuvuvwl
```





此时配置文件中spring.elasticsearch.enabled字段值为false，因此此时启用了DbSearchServiceImpl实现类；

 

 