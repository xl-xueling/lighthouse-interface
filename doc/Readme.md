# 配置

- 1、修改 src/main/resources目录下的lighthouse-interface.yml文件中的locators参数为RPC服务的地址，与原来的LightHouse.init方法参数一致。
- 2、修改 src/main/resources目录下的log4j2-interface.xml中的日志输出目录地址；

# 编译

mvn clean install

如果提示找不到lighthouse-shaded-client-x.x.x.jar包，可以将xl-lighthouse工程代码clone,使用main分支，然后执行mvn clean install编译一下，这会将lighthouse-client打包到maven的本地仓库，然后interface工程就可以引用了。


# 部署命令

## 1、上传jar包

上传编译目录下./target/lighthouse-interface-1.0.0.jar包到服务器目录。

## 2、执行部署命令

内存参数可以根据实际需要指定，另外如果数据量较大，可以使用nginx配置负载均衡。

nohup java -Xms300M -Xmx300M -XX:+UseG1GC -jar /opt/soft/test/lighthouse-interface-1.0.0.jar > output.log 2>&1 &

如果需要重新启动，请将原先的进程kill掉。
可执行jps -l命令，找到 "475878 /opt/soft/test/lighthouse-interface-1.0.0.jar" ,kill掉原来的进程即可，务必注意：不要Kill错其他的进程。

## 3、查看所配置日志信息

提示： Started LightHouseAPIApplication in 2.611 seconds (JVM running for 3.68)，表示部署成功！

## 接口地址

http://10.206.6.5:9085/api/v1/stat

## 接口参数
必须使用post请求，包体参数示例如下：
```
{
  "token" : "TlZ:test_stat",
  "secretKey" : "mgasBFCD8iFcIgKNkpmBwfMm51M0CE2DZsKWwB5z",
  "timestamp" : 1713006941196,
  "repeat" : 1,
  "params" : {
    "score" : 153.90985341472552,
    "province" : 3,
    "uuid" : "test_101"
  }
}

参数说明：
token: 不能为空，不要配置错误
secretKey: 不能为空，不要配置错误
timestamp: 时间戳，精确到毫秒，可以不指定，默认为接口接收到数据的时间
repeat: 可以不指定，默认为1
params: 不能为空
```

##  初步测试可以使用test目录中的单元测试方法。

TestHttpApi：使用http请求接口。
HelloWorld：使用原来的rpc接口。


## 其他说明

1、LightHouse.stat内部逻辑为异步处理，该接口只要将数据写入到内存队列中就会返回成功。所以该接口只会验证参数是否为空，而不会验证参数具体值。
    如果token、secretKey的值配置错误，该接口仍会返回success，此时可以查看lighthouse-interface服务的异常日志获取具体信息。