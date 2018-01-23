# ETL
数据库同步迁移工具

使用mdb数据库的时候需要加载 mdbutil_jni.dll文件

反编译自 myetl 将jar的依赖替换为pom引用，部分jar文件由于版权问题只能通过本地lib引入

使用阿里云maven仓库

maven编译后会打出  etl.one-jar.jar 可运行文件

通过 java -jar etl.one-jar.jar 调用执行

同时需要将 dll 文件放入环境变量中以调取mdb支持(暂时没找linux下的so支持库)