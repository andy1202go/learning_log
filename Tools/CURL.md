# CURL

[toc]

## 常用命令

```
curl www.baidu.com get百度
curl -X POST www.baidu.com
curl -X POST --data "shit=happens" www.baidu.com
curl -X POST --data-urlencode "shit=happens" www.baidu.com 表单编码，post提交
curl -i www.baidu.com 显示header信息
curl -o test.txt www.baidu.com 保存
curl -v test.txt www.baidu.com 显示通信过程
curl --user-agent "Mozilla/5.0" www.baidu.com
curl --cookie "name=xxx" www.baidu.com
curl --header "Content-Type:applicaiton/json" www.baidu.com
curl --user name:password www.baidu.com
```

### 一次curl，多个URL

空格隔开就行；jianshu.com/p/fc0eb6c60816

### GET命令的多个参数问题

会有&在URL中，不同操作系统的写法不同https://blog.csdn.net/sinat_27818621/article/details/104517435

linux：

```shell
curl localhost:8080/test?a=1\&b=2
```

windows

```shell
curl -s "http://localhost:8080/get?name=zhangsan&age=12&sex=1"
```

## CookBook

### Make a POST Request (TLDR: Use -X POST argument)

1. [Send an Empty POST Request](https://catonmat.net/cookbooks/curl/make-post-request#send-empty-post-request)

   ```shell
   curl -X POST https://sth.com
   ```

   

2. [Send a POST Request with Form Data](https://catonmat.net/cookbooks/curl/make-post-request#post-form-data)

   ```shell
   curl -d 'a=1&b=2' -X POST https://sth.com
   ```

   > When using the `-d` argument, curl also sets the `Content-Type` header to `application/x-www-form-urlencoded`.
   >
   > Note that the `key=value` data should be URL-escaped.

3. [Skipping the -X Argument](https://catonmat.net/cookbooks/curl/make-post-request#skipping-x-argument)

   ```shell
   curl -d 'a=1&b=2' https://sht.com
   ```

   > When `-d` is used, curl implicitly sets the request's type to POST.

4. [A Neater Way to POST Data](https://catonmat.net/cookbooks/curl/make-post-request#neater-way-to-post)

   ```shell
   curl -d 'a=1' -d 'b=2' https://sth.com
   ```

   

5. [Send a POST Request and Follow a Redirect](https://catonmat.net/cookbooks/curl/make-post-request#post-with-redirect)

6. [Send a POST Request with JSON Data](https://catonmat.net/cookbooks/curl/make-post-request#post-json-data)

   ```shell
   curl -d '{"a":2,"b":1}' -H 'Content-Type:application/json' https://sth.com
   ```

   必须手动声明Content-Type头为application/json

7. [Send a POST Request with XML Data](https://catonmat.net/cookbooks/curl/make-post-request#post-xml-data)

   ```shell
   curl -d '<user>1</user>' -H 'Content-Type:text/xml' https://sth.com
   ```

   注意Content-Type

8. [Send a POST Request with Plain Text Data](https://catonmat.net/cookbooks/curl/make-post-request#post-plain-text-data)

   ```shell
   curl -d 'hello world' -H 'Content-Type:text/plain' https://sht.com
   ```

   注意Content-Type

9. [Send a POST Request with Data from a File](https://catonmat.net/cookbooks/curl/make-post-request#post-data-from-file)

10. [URL-encode POST Data](https://catonmat.net/cookbooks/curl/make-post-request#url-encode-post-data)

    > So far, all recipes have been using the `-d` argument to add POST data to requests. This argument assumes that your data is URL-encoded already. If it is not, then there can be some trouble. If your data is not URL-encoded, then replace `-d` with `--data-urlencode`. It works exactly the same way as `-d`, except the data gets URL-encoded by curl before it's sent out on the wire.

    ```shell
    curl --data-urlencode 'a=1' https://sth.com
    ```

    -d是假设参数已经url编码好了的，--data-urlencode是对参数执行url编码

11. [POST a Binary File](https://catonmat.net/cookbooks/curl/make-post-request#post-binary-file)

12. [POST a Binary File and Set Its MIME Type](https://catonmat.net/cookbooks/curl/make-post-request#post-binary-file-set-mime)

13. [POST a Binary File and Change Its Filename](https://catonmat.net/cookbooks/curl/make-post-request#post-binary-file-change-filename)

### Add POST Data to a Request (TLDR: Use -d var=val argument)

同上一个

### Construct a Query String (TLDR: Use -G argument)

> This curl recipe shows you how to construct query strings for your GET requests. This is done via the `-G` command line argument in combination with the `-d` or `--data-urlencode` arguments. The `-G` argument will append the data specified in `-d` and `--data-urlencode` arguments at the end of the request URL, joining all data pieces with the `&` character and separating them from the URL with the `?` character.

使用-G和-d或者--data-urlencode，把参数按照?&&的格式拼接到url的后面

1. [Construct Two Query Arguments](https://catonmat.net/cookbooks/curl/construct-query-string#construct-two-query-arguments)

   ```shell
   curl -G -d 'a=1' -d 'b=2' https://sth.com
   ```

   > Be careful – if you forget the `-G` argument, then curl will make a POST request instead!

2. [URL-encode a Query Argument](https://catonmat.net/cookbooks/curl/construct-query-string#url-encode-query-argument)

   ```shell
   curl -G --data-urlencode 'comment=this cookbook is awesome' https://catonmat.net
   ```

### Add HTTP Headers (TLDR: Use -H 'Header: Value' argument)

1. [Add a Single Header](https://catonmat.net/cookbooks/curl/add-http-headers#add-a-single-header)
2. [Add Two Headers](https://catonmat.net/cookbooks/curl/add-http-headers#add-two-header)
3. [Add an Empty Header](https://catonmat.net/cookbooks/curl/add-http-headers#add-an-empty-header)

```shell
curl -H 'Accept-Language: en-US' -H 'Secret-Message: xyzzy' https://google.com
curl -H 'Puppies;' https://google.com
```

### Change the User Agent (TLDR: Use -A 'User Agent' argument)

浏览器啥的设置

### Set Cookies (TLDR: Use -b name=value argument)

1. [Add a Cookie](https://catonmat.net/cookbooks/curl/set-cookies#add-cookie)

   ```shell
   curl -b 'user_id=abc' https://sth.com
   ```

   在一个get请求中，添加了一个cookie，Cookie: user_id=abc

2. [Add Two Cookies](https://catonmat.net/cookbooks/curl/set-cookies#add-two-cookies)

   ```shell
   curl -b 'session=abcdef' -b 'loggedin=true' https://google.com
   ```

3. [Add an Empty Cookie](https://catonmat.net/cookbooks/curl/set-cookies#add-empty-cookie)

4. [Save Cookies to a File](https://catonmat.net/cookbooks/curl/set-cookies#save-cookies-to-file)

5. [Load Cookies from a File](https://catonmat.net/cookbooks/curl/set-cookies#load-cookies-from-file)

### Add a Referrer (TLDR: Use -e URL argument)

信任来源设置

### Follow a 3XX Redirect (TLDR: Use -L argument)

重定向

### Use the Basic HTTP Authentication (TLDR: Use -u user:pass argument)

基础的HTTP登录，可以传递用户名和密码的

```shell
curl -u 'bob:12345' https://google.com/login
```

### Print the Response Headers (TLDR: Use -i argument)

1. [Print the Response Headers and Body (together)](https://catonmat.net/cookbooks/curl/print-response-headers#print-headers-and-response)

   ```shell
   curl -i https://catonmat.net
   ```

   注意这里一定是小写的-i，大写的功能是不一样的。结果会先打印response header，然后是空一行，然后是响应结果。

2. [Print Only the Response Headers](https://catonmat.net/cookbooks/curl/print-response-headers#print-only-headers)

   只打印header不要响应的情况。

### Use a Proxy (TLDR: Use -x protocol://host:port argument)

使用代理

### Ignore the SSL Certificate (TLDR: Use -k argument)

忽略SSL证书校验啥的

### Make Curl Silent (TLDR: Use -s argument)

> how to make curl silent so that it doesn't print progress bar, errors, and other output that can get in the way

### Save the Response to a File (TLDR: Use -o file argument)

```shell
curl -o response.txt https://google.com?q=kitties
```

### Make Curl Slow (TLDR: Use --limit-rate 8k (8KB/sec) argument)

神奇，可以通过--limit-rate来限制curl的速度，也不知道为啥需要这个

### Debug Curl Requests (TLDR: Use -v or --trace arguments)

其实不是代码那种debug断点啥的，是把整个过程详细的打印出来

1. [Make Curl Verbose](https://catonmat.net/cookbooks/curl/debug-curl-requests#make-curl-verbose)

   ```shell
   curl -v https://www.baidu.com
   ```

   会把整个过程详细的打印出来；具体怎么看，参考下文：

   > This recipe uses the `-v` argument to make curl print detailed information about the request and the response. Lines prefixed by `>` is the data sent to the server, lines prefixed by `<` is the data received from the server, and lines starting with `*` is misc information, such as connection information, SSL handshake information, and protocol information.

2. [Detailed Trace](https://catonmat.net/cookbooks/curl/debug-curl-requests#detailed-trace)

   ```shell
   curl --trace - https://catonmat.net
   ```

   16进制打印中间详细结果，感觉目前用不到。注意命令比较特殊。

3. [Detailed Trace with Timestamps](https://catonmat.net/cookbooks/curl/debug-curl-requests#detailed-trace-with-timestamps)

4. [Include Response Headers in the Output](https://catonmat.net/cookbooks/curl/debug-curl-requests#include-response-headers-in-output)

   -i 

   注意，如果想看到requestHeader的话，目前似乎只看到通过-v来看的

5. [Print Only the Response Headers](https://catonmat.net/cookbooks/curl/debug-curl-requests#print-only-response-headers)

6. [Print Only the Request Headers](https://catonmat.net/cookbooks/curl/debug-curl-requests#print-only-request-headers)

7. [Print Only the Response Code](https://catonmat.net/cookbooks/curl/debug-curl-requests#print-only-response-code)

### Make a GET Request (TLDR: No arguments required, it's the default)

默认的curl就是发送GET请求

```shell
curl https://catonmat.net
```

## 参考文献

1 [《curl cookbook》](https://catonmat.net/cookbooks/curl)

2 [《curl 初学者教程》](https://www.ruanyifeng.com/blog/2011/09/curl.html)

3 https://curl.se/

4 http://www.ruanyifeng.com/blog/2019/09/curl-reference.html



## TODO List

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



## 总结

| 时间 | 内容 |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |



