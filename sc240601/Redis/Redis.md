## Redis

### 1. 什么是nosql(非关系型数据库)

> 全称not only sql 和 传统的关系型数据库相比mysql oracle，它不需要创建关系模型(二维表)，数据和数据之间也可以没有必然的联系，底层是基于key-value存储的。而且是在内存中进行存储的，所以读写速度远远高于 传统的关系型数据库。

### 2.什么是Redis

> redis是一个开源的，使用C语言编写的，支持网络交互，可以基于内存存储，也可以支持持久化存储，读写速度非常快，支持事务(没有回滚) 还可以设置过期数据，非常适合做缓存操作。

#### 2.1 redis应用场景

> * 缓存操作：第一次查询从mysql中查数据，并且将数据存储在redis中，再次查询相同的数据时走redis不走mysql 数据，如果更新了则清空redis缓存。
> * 计算：点赞量 下载量 访问量 ...(原子性，不会有并发问题，因为redis的底层有一个实现自增自减的命令 专门用来实现计数，而且不会出现并发问题，因为他们的自增自减具有原子性)
> * 排行榜：（推荐帖子 推荐消息 推荐商家 榜一大哥...) 因为redis有一个专门的数据类型 可以进行排序， 可以把存储的数据绑定一个分数，可以根据分数排序...
> * 好友关系:（共同爱好，共同好友，共同粉丝...) redis有一个类型 专门实现交集并集差集，很容易实现 数据之间的关系
> * session共享：以后做了集群，每个服务器都会有一个session
> * 简单的队列：只能完成一个简单的队列 复杂的队列 可以交给RabbitMQ来实现。

#### 2.2 redis环境搭建

> * 安装redis：不用安装 解压即可使用。
>
> * 找到redis配置文件`config/redis.conf`
>
> * 配置redis配置文件 `config/redis.conf`
>
>   ```properties
>   #设置绑定的IP地址,  客户端是通过这个IP地址去链接服务端的
>   bind 192.168.111.111
>   #指定是否开启保护模式, 如果开启了  需要访问密码
>   protected-mode no
>   #端口号 可以任意写 只要保证端口不冲突即可
>   port 6379
>   #设置是否为守护进程 , 如果开启redis可以后台运行
>   daemonize yes
>   #保存进程id的目录
>   pidfile /var/run/redis_6379.pid
>   #保存日志的目录
>   logfile /usr/local/sc240601/redis-5.0.3/log/redis_6379.log
>   #持久化文件名
>   dbfilename redis_6379.rdb
>   #持久化文件路径
>   dir /usr/local/sc240601/redis-5.0.3/data/
>   ```
>
> * redis是基于C/S架构的 通过reids服务端命令启动redis.
>
>   ```
>   redis-server 指定的包的配置文件
>   ```
>
>   * ps -ef | grep redis 查看是否成功，如果启动失败了，查看日志
>
> * 通过redis客户端连接redis服务端
>
>   ```
>   redis-cli -h 服务端ip地址 -p 服务器端口号
>   redis-cli -h 192.168.111.111 -p 6379
>
>   -- 测试结果
>   ip地址:端口号>
>   ```
>
> * 写redis代码

### 3. Redis基本命令

#### 3.0 启动Redis

> 要启动Redis服务器和客户端
>
> * 启动Redis服务端(==注意是config目录下的config==)[出现redis图标就表示开错了]
>
>   ```
>   redis-server 指定的包的配置文件
>   redis-server /usr/local/sc240601/redis-5.0.3/config/redis.conf
>   ```
>
> * 启动Redis客户端连接服务端
>
>   ```
>   redis-cli -h 服务端ip地址 -p 服务器端口号
>   redis-cli -h 192.168.111.111 -p 6379
>               
>   -- 成功结果
>   ip地址:端口号>
>   ```
>

#### 3.1 通用命令

> 无论什么类型 都可以使用的命令
>
> ```
> keys 正则：查看符合条件的key（查看有几条数据）
> select 数字:切换数据库 redis默认创建16个数据库 通过0-15进行切换，默认为0
> type key:查看key的类型
> dbsize:查看数据库中key的数据
> exists key:查看key是否存在 返回1/0 redis没有true和false
> del key:根据key来删除数据
> 
> 
> rename oldkey newkey: 用来给key重命名的如果newkey已经存在了，会替换掉原来的值，可能会出现数据丢失
> renamenx oldkey newkey：给key重命名，newkey不存在时，才能重命名(注意是nx)
> expire key 秒：设置key多少秒失效。（可以用来做验证码）
> pexpire key 毫秒：设置key多少毫秒失效。
> ttl key：查看key的剩余时间，如果返回-1表示没有设置过过期时间会一直存在，返回-2则表示该事件不存在。🙅‍
> persist key：取消过期时间，转变为永久存在（过期就是被删除了）
> flushdb：清空redis 慎用🈲
> ```

#### ==3.2 redis的基本数据类型==

> * String：是一个二进制类型，可以存储字符串，图片，视频，音频，也可以存储一些静态资源的文件(比如：js css ...) String类型的每个value最大值是 512M
>   * 什么场景适合使用String？
>     * 做缓存
>     * 计数功能(点赞)：String类型中有自增自减的原子性操作。
> * List：是一个列表类型，底层是双向链表结构，类似于java中的LinkedList，是一个有序的可以重复的集合
>   * 什么场景适合使用List?
>     * 适合做简单的队列（有序：可实现先进先出，先进后出）
> * Set：是一个无序不重复集合类型，底层是通过hashtable实现的，查看元素和删除元素都特别快，可以通过它记录一些不重复的数据
>   * 什么场景适合使用List?
>     * 共同爱好，共同好友，粉丝：有一个实现交集并集差集
>     * 点名，抽奖功能：有一个命令，可以设计获取集合中的元素
>       (srandmember + srem 或者 spop)
> * Sorted Set：可能被叫为Zset，是一个排序不重复集合类型，元素是唯一的，只不过底层是通过每个元素会绑定一个double类型分数，redis通过分数对集合的元素进行升序/降序排序，集合元素是唯一的 但是分数是可以重复的。
>   * 什么场景适合使用Zset?
>     * 排行榜相关应用：这个类型会自动根据分数进行排序，把集合的元素存储用户信息，分数表示运动步数，就能实现运动排名。
> * Hash： map套map，就是每个key都会对应一个hashtable，适合用来存储对象数据。
>   * 什么场景适合使用Hash?
>     * 适合存储对象数据，比如：key对应用户id，value对应用户属性集合

#### ==3.3 String类型的命令==

> 这里的命令 和 Java中操作redis的方法对应

```properties
set key value: 添加一组值 如果key存在会进行替换
setnx key value: 添加一组值 key不存在 才可以设置
get key: 根据key获取值
mset k1 v1 k2 v2 ... : 一次性存储很多组值 这种操作具有原子性，要么同时成功，要么同时失败
mget k1 k2 k3 ... : 一次性获取很多值

#以下四个命令也是实现,redis计数功能(点赞数 下载量...)，而且 他们不会有多线程的并发问题 它们具有原子性
incr key: 对key的值进行自增(值需要是整型的)
incrby key 数字: 对key的值进行自增指定的数字
decr key: 自减
decrby key 数字: 自减指定数字

append key 数据:给指定的key的值追加新内容 如果key不存在 会帮你创建这个key
strlen key: 获取长度
getrange key startIndex endIndex: 获取key指定范围的数据（类似于截取字符串）[下标从0开始]
setrange key index value：将对应位置的值替换为value(类似于替换字符串)[下标从0开始]
```

#### 3.4 List类型命令

```properties
#存值
lpush key v1 v2 v3 ...: 在集合key的左边依次插入不同的value[...v3 v2 v1]
rpush key v1 v2 v3 ...: 在集合key的右边依次插入不同的value[v1 v2 v3 ...]

#查看
lrange key beginIndex endIndex : 获取集合中begin-end范围的元素，如果end是-1表示获取全部内容，-2表示								   获取到 倒数第二个元素...
lrange key 0-1 获取集合全部内容
lindex key index: 根据集合的下标(从0开始)获取元素
llen key : 获取集合的长度

#取出并移除元素
lpop key : 取出并移除集合中的第一个元素
rpop key : 取出并移除集合中的最后一个元素

lrem key 数字 value: 删除集合中，指定个数的，指定value数据
	如果数字是正数 从头0开始删除多少个
	如果数字是负数 从尾开始删除多少个
	数字是0 则全部删除

```

#### 3.5 Set数据类型

```properties
sadd key v1 v2 v3: 向集合中存储多个不重复的数据(会自动去重)
smembers key: 无序获取集合中的所有元素。（只能取全部元素）

srandmenber key: 随机获取集合中的元素。（做点名和抽奖）
srandmenber key 2: 随机获取集合中两个的元素

scard key: 获取集合中元素的个数
srem key value: 删除集合中指定的元素
spop key (个数): 随机获取和删除集合中的元素（抽奖 + 点名）

删除整个list和set使用 del

#集合运算,交集并集差集共同特性(好友粉丝 爱好 游戏...)
sunion key1 key2 key3 ...: 并集 将多个集合元素合并取出来 相同只取一个
sdiff key1 key2 key3 ...: 差集 获取第一个集合 和后面的集合不同的元素
sinter key1 key2 key3 ...: 交集 获取多个集合共同的元素

sinterstore newKey key1 key2 ...: 获取后面多个集合共同的元素 存入新集合
sdiffstore newKey key1 key2 ...: 获取key1集合和后面的集合 不同的元素 存入到新集合
sinterstore newKey key1 key2 ...: 获取获取多个集合共同的元素 存入到新集合
```



#### 3.6 有序集合Sorted Set (Zset)

> sorted set 类似于set 元素都是唯一的，底层会为每一个元素绑定一个double类型的分数 redis通过分数进行排序（升序 降序都可以）。
> 非常适合做排行榜功能。
> 如果使用mysql来做排序，一是要表关联查询，二是要反复查询(在高并发的情况下效率是非常低的)。

```properties
zadd key 分数 数据: 向集合中添加一个元素并为其绑定为一个分数 默认按照分数升序排序
#如 zdd key 1 a
zrange key start end: 获取指定范围的元素 默认按照升序返回 （end为-1就是到最后一个）（end为-2就是到倒数第二个）
zrevrange key start end: 功能和上面一样，只不过是降序排列。(reverse反转)
zincrby key 数字 数据: 给集合中的数据对应的分数自增多少（注意是给分数增加）（设置负数就是自减）
zcount key min max: 获取分数在指定范围的个数
zscore key 数据: 获取集合中数据对应的分数
zrem key 数据: 移除集合指定元素的数据
zcard key: 获取集合中元素的个数
```



#### 3.7 Hash数据类型

> hash类型就是每个key 都会对应一个hashtable 比较适合存储对象数据
>
> 比如：key对应用户id      其他信息(k 对应属性名 v 对应属性值)
>
> ```
> 回顾：各种数据类型增加元素的命令：
> set k v
> lpush list v 或 rpush list v
> sadd set v
> zadd zset score k
> hset 对象 k v k2 v2
> ```
>
> ```
> hset hashkey key value: hashkey可以当成对象名 key可以当成属性名 value可以当成属性值
> 	User u = new User(); hashkey可以当成对象名u
> 	u.属性名 === 属性值;   key可以当成属性名，value可以当成属性值
> 
> hmset hashkey k v k2 v2 k3 v3 ...: 向对象中存储很多组的属性值
> hget hashkey key : 获取对象中指定属性的值。
> hgetall hashkey : 获取对象中的属性和值
> hkeys hashkey: 获取对象中所有的属性名
> hvals hashkey: 获取对象中所有的属性值
> ```

### ==4. redis的持久化方式==

> 持久化就是把内存中的数据存储到磁盘中去。

#### 4.1 RDB模式

> RDB模式也叫半持久化模式，实际操作redis服务端 每隔一段实际 会生成一个子进程 通过子进程负责将数据写入到临时文件，等待写入成功后再去提取之前保留的文件。
>
> * 优点：
>   * 如果使用RDB模式 redis只需要保存一个rdb文件即可 对于以后数据的备份和还原都是比较完美的，如果出现宕机很容易就可以恢复数据。
>   * RDB模式是通过子进程进行数据的持久化，不会影响redis服务端主进程的读写，所以其执行效率是比较高的。
> * 缺点：
>   * `容易出现数据丢失`，因为底层是通过子进程，每隔一段时间，进行数据持久化，万一在这段时间之内，redis宕机了，那么这段时间之内写入的数据就丢失了。
>   * 临时文件只有一个，万一临时文件损坏，就无法恢复数据了。
>
> ![img](D:\Desktop\gitee\java-learning\sc240601\Linux\img\Redis的持久化-RDB模式.png)

#### 4.2 AOF模式

> AOF模式也叫做全持久化模式(写一次就存储一次，不像RDB是隔一段时间存一次)，底层是通过日志形式去记录redis服务端所执行的命令(只会记录增set 删del 改的命令，像get mget 这些查询命令就不会记录)。
> 如果后期redis宕机了，后期做灾难恢复时，只需要重新执行一遍记录这些的命令即可
>
> * 优点：
>   * 如果采用AOF模式，具有更高的数据安全性，因为redis有他自己的同步策略(每秒同步 每修改同步 不同步)如果设置成每修改同步，每次修改后都会通过日志记录下来，这样就不会出现数据丢失了。
>   * 由于AOF模式 是通过日志记录的，底层是通过追加模式，每次写入新的数据就会进行追加，不会损坏之前存储的日志信息。
> * 缺点：
>   * 每一次写入都需要记录执行的命令，并发量特别高时，就会影响到redis服务端执行的速度，会降低执行效率。
>   * AOF模式保存的日志形式的文件，可能会出现很多个，所以要恢复数据比较麻烦，因为每个文件都需要恢复，数据量特别大 恢复数据是很麻烦的。

> 开发按需求来选用持久化方式，但是我们使用reids就是为了提高效率，所以默认是使用RDB模式。

### 5. redis的主从模式（master/slave)

> 主从模式也叫主从复制也叫读写分离，表示有一台主服务器(主要负责写入) 和 多台从服务器(只能读取 不能写入 具有主服务器的备份）。
>
> * 缺点: 如果从服务器宕机了 没有任何影响
>   如果主服务器宕机了 数据虽然不会丢失 但是不能写入了
>   需要人工介入 将从服务器升为主服务器 才可以再次写入

#### 5.1 搭建流程

> - 创建mastersalve包  复制了三份redis.conf配置文件 要搭建一主两从 
> - 编辑里面的配置文件(ip地址 端口号 进程文件名 日志文件 持久化文件名)
> - 如果是从服务器 需要添加一个额外配置  (关联主服务器)
>   - replicaof  主服务器ip地址   主服务器端口号
> - 通过redis-server启动三台redis服务器  
> - 通过redis-cli 连接redis服务端  输入 info replication查看主从信息
>   - redis-cli -h  ip地址  -p 端口
>     - info replication
>
> ![image-20240910172219204](D:\AppData\Typora\typora-user-images\image-20240910172219204.png)
>
> ![image-20240910172755667](D:\AppData\Typora\typora-user-images\image-20240910172755667.png)
>
> ![image-20240910173511457](D:\AppData\Typora\typora-user-images\image-20240910173511457.png)
>
> ![image-20240910173837925](D:\AppData\Typora\typora-user-images\image-20240910173837925.png)
>
> ![image-20240910174238642](D:\AppData\Typora\typora-user-images\image-20240910174238642.png)
>
> ![image-20240910174251127](D:\AppData\Typora\typora-user-images\image-20240910174251127.png)

#### 5.2 主和从服务器的切换

> * 命令1：将从服务器直接升为主服务器，前提是需要先人力介入
>
>   ```
>   slaveof no one
>   ```
>
> * 命令2：切换我的主服务器为其他服务器，通过其他服务器指定好的主服务器 前提是需要先人力介入
>
>   ```
>   slaveof 新的主服务器ip 新的主服务器端口
>   ```
>

#### 5.3 主从模式的缺点

> 因为主从模式可以提高redis的数据安全性，但是如果主服务器宕机，需要如果介入来输入命令(slaveof no one...) 将从服务器升级为主服务器 这样就浪费了人力成本和时间成本 所以redis推出了哨兵模式相当于主从模式的升级版。

### 6. redis的哨兵模式（Sentinel）

> 哨兵模式中，除了主从服务器外，还多了很多个哨兵服务器，主要用于监控主从服务器的可用性（底层是它每隔一段时间向这些服务器发送一些心跳），如果发现主服务器宕机了，那么这些哨兵会投票决定，将哪台从服务器升级为主服务器(投票超过半数才会决定)，好处在于无需人工介入。

#### 6.1  要几台哨兵？

> 假设:搭建一台主服务 一台从服务器 需要搭建几个哨兵?
> 		3个
> 假设:搭建一台主服务器 2台从服务器 需要搭建几个哨兵?
> 		3个 或者3个以上都行?大部分都是奇数 因为要超过半数
>
> ![image-20240911104239546](D:\Desktop\gitee\java-learning\sc240601\Linux\img\需要几台哨兵.png)

#### 6.2 配置哨兵

> * 创建一个新目录(sentinel)保存哨兵服务器的所有配置文件(1主1从3哨兵)
>
> * 主和从的配置文件和之前的主从模式一致，没有变化
>
> * 配置哨兵服务器的配置文件
>
>   * 删除哨兵服务器不需要的配置![image-20240911105441309](D:\Desktop\gitee\java-learning\sc240601\Linux\img\删除哨兵服务器不需要的配置.png)
>
>   * 配置哨兵的配置文件
>
>     ```properties
>     bind 192.168.111.111
>     port 10000
>     daemonize yes
>     pidfile /var/run/redis_10000.pid
>     logfile /usr/local/sc240601/redis-5.0.3/log/redis_10000.log
>     ##哨兵配置
>     #1. 配置主从信息 
>     #  主服务器    主服务器名   主服务器ip  主端口号  主和从节点的个数
>     sentinel monitor mymaster 192.168.111.111 7000 2
>     #2. 设置多少秒是没有恢复 哨兵才认为主节点下线(毫秒)
>     sentinel down-after-milliseconds mymaster 30000
>     #3.设置故障转移时 最多多少个从服务器同步到主服务器
>     sentinel parallel-syncs mymaster 1
>     #4.设置故障转移最大升级
>     sentinel failover-timeout mymaster 60000
>           
>     ```
>
> * 通过redis服务端命令 启动主从服务器
>
>   ```
>   redis-server 包/xxx.conf
>   ```
>
> * 通过redis哨兵命令 启动三个哨兵服务器
>
>   ```
>   redis-sentinel 包/xxx.conf
>   ```
>
> * 测试：ps -ef | grep redis

#### 6.3 哨兵配置文件的解析

>
> 您提供的配置内容包含了 Redis 服务器的基本设置和 Redis 哨兵（Sentinel）的配置。解释这两部分的配置：
>
> ### Redis 服务器配置
>
> 1. **bind 192.168.111.111**
>    - 绑定 Redis 服务器到指定的 IP 地址。在这个例子中，Redis 服务器将只接受来自 192.168.111.111 的连接。如果您希望 Redis 服务器能够接受来自其他 IP 地址的连接，可以添加更多的 IP 地址或使用 `0.0.0.0` 来表示接受所有 IP 地址的连接。
>
> 2. **port 10000**
>    - 设置 Redis 服务器的端口号为 10000。客户端需要通过这个端口来连接到 Redis 服务器。
>
> 3. **daemonize yes**
>    - 将 Redis 服务器设置为守护进程模式运行。这意味着 Redis 服务器将在后台运行，并且不会占用终端窗口。
>
> 4. **pidfile /var/run/redis_10000.pid**
>    - 指定 Redis 服务器的进程 ID 文件（PID 文件）的路径。这个文件包含了 Redis 服务器的进程 ID，可以用于停止或重启 Redis 服务器。
>
> 5. **logfile /usr/local/sc240601/redis-5.0.3/log/redis_10000.log**
>    - 指定 Redis 服务器的日志文件路径。Redis 服务器会将所有的日志信息写入到这个文件中，方便您进行日志分析和故障排查。
>
> ### Redis 哨兵配置
>
> 1. **sentinel monitor mymaster 192.168.111.111 7000 2**
>    - 配置哨兵监控名为 `mymaster` 的主服务器。主服务器的 IP 地址是 192.168.111.111，端口号是 7000。`2` 表示哨兵需要至少检测到 2 个从服务器或哨兵同意，才能认为主服务器下线。
>
> 2. **sentinel down-after-milliseconds mymaster 30000**
>    - 设置哨兵在检测到主服务器下线之前需要等待的时间（毫秒）。在这个例子中，如果主服务器在 30 秒内没有响应哨兵的 ping 请求，哨兵就会认为主服务器下线。
>
> 3. **sentinel parallel-syncs mymaster 1**
>    - 设置在进行故障转移时，最多允许多少个从服务器同时同步到新的主服务器。在这个例子中，只允许 1 个从服务器同步到新的主服务器。
>
> 4. **sentinel failover-timeout mymaster 60000**
>    - 设置哨兵在执行故障转移操作时的最大超时时间（毫秒）。在这个例子中，哨兵有 60 秒的时间来完成故障转移操作。如果在规定的时间内没有完成故障转移，哨兵可能会再次尝试或报告错误。
>
> 请注意，您提供的配置中 Redis 服务器和哨兵监控的主服务器使用了不同的端口号（10000 和 7000）。确保这些端口号在您的网络中是可用的，并且没有与其他服务冲突。另外，哨兵配置通常需要放置在单独的哨兵配置文件中，而不是与 Redis 服务器的配置文件混合在一起。在实际部署时，请根据您的具体需求和环境进行调整。

#### 6.4 测试哨兵模式自动切换主服务器

> 在springboot项目的配置文件中添加依赖：
>
> ```xml
> <!--redis-->
> <!--springboot整合redis的依赖-->
> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-data-redis</artifactId>
> </dependency>
> <!--redis客户端依赖，单纯的ssm项目只导入这个依赖就能连接redis-->
> <dependency>
>     <groupId>redis.clients</groupId>
>     <artifactId>jedis</artifactId>
> </dependency>
> ```
>
> 编写Java代码连接服务器：
>
> ```java
> package com.sc.sm.testRedis;
> import redis.clients.jedis.Jedis;
> import redis.clients.jedis.JedisSentinelPool;
> import java.util.HashSet;
> import java.util.Set;
> 
> public class TestSentinel {
>     public static void main(String[] args) {
>      //0.哨兵服务器的IP地址:端口 的set集合（用set是为了去重）
>         Set<String> set = new HashSet<>();
>         set.add("192.168.111.111:10000");
>         set.add("192.168.111.111:10001");
>         set.add("192.168.111.111:10002");
>      //1.定义Redis哨兵池对象（参数1：主服务器的名字在配置文件中定义，参数2：哨兵的地址set集合（去重）
>         JedisSentinelPool pool = new JedisSentinelPool("mymaster", set);
>         
>         int i = 1;
>         while(true) {
>             Jedis jedis = null;//创建Jedis对象(redis客户端对象）
>             try {
>                 jedis = pool.getResource();
>                 jedis.set("key-"+i, "value-"+i);
>                 System.out.println("插入数据成功：key-"+i++);
>                 Thread.sleep(2000);
>             } catch (Exception e) {
>                 System.out.println("可能主服务器宕机了，请等待30秒");
>             } finally {
>                 try {
>                     jedis.close();
>                 } catch (Exception e) {
>                     System.out.println("可能主服务器宕机了，请等待30秒");
>                 }
>             }
>         }
>     }
> }
> 
> ```
>
> 在linux中关闭防火墙或开启相应端口号
>
> ```
> systemctl stop firewalld
> 
> vi /ect/firewalld/zones/public.xml
> <rule family="ipv4">
> 	<port protocol="tcp" port="要开放的端口号"/>
> 	<accept/>
> </rule>
> ```
>
> 杀死主服务器，对比从服务器前后的身份。
>
> ```
> //杀死主服务器
> ps -ef | grep redis
> kill -9 主服务器进程id
> 
> //对比从服务器的前后身份
> redis-cli -h 从服务器ip -p 从服务器端口
> info replication
> ```

#### 6.5 哨兵模式的缺点

> 1主(写)1从(读)3哨兵(检测)，6台服务器中只有1台服务器在做事。
>
> 哨兵模式基本可以解决大部分需求，但是`只有一个主节点`，意味着只有一台服务器可以写入，如果并发量特别高时，redis的容量是无法扩容的(Redis不好在线扩容的，集群容量一旦到达上限，在线扩容就十分麻烦)，redis并发量不能增长。所以redis推出了集群模式。

### 7. redis的集群模式 （cluster）

> 集群模式最大可以增加1000个节点（服务器），这里面就可以包括多态主节点和多台从节点。这样redis整体性能就会随着节点越多，性能越高（支持更高的并发量，支持更高的容量）

#### 7.1 什么是集群？

> redis-cluster主要包含三个内容：
>
> * master：主服务器（主节点）负责写 是多个
> * slave：从服务器（从节点）负责读 会保存从的备份 是多个
> * slot：数据分槽（槽点）redis一共有16384个槽，每个槽点管理一部分数据。redis会根据主节点的数量，将16384槽，平均分成n个数据分槽。这些数据分槽会平均分配到所有主节点上。这样每个主节点管理的数据分槽越少，这样整体的数据量是不变的，但是每个主节点存储的数据和并发量就越来越少了
> * 比如:有三个主节点，16384个槽会平均分配给这3台主服务器
>   主节点A：管理0-5500个槽点  ->  从节点A
>   主节点B：管理5501-11000个槽点 ->  从节点B
>   主节点c：管理11001-16384个槽点 -> 从节点C

#### 7.2 配置集群

> 集群模式下redis会自动设置主从，不需要去配置主和从，所以我们只需要在主的配置文件的基础上增加一个集群配置，就完成了集群节点的配置文件。
>
> - 创建一个集群目录(cluster)保存所有集群的配置文件
>
> - 每个节点的配置文件的前半部分都和主从模式下主节点的配置相同，但`还需要添加额外的集群配置`
>
>   ```
>   ###集群配置
>   #1.开启集群
>   cluster-enabled yes
>   #2.指定集群配置文件
>   cluster-config-file nodes-当前节点的端口号.conf
>   #3.如果一个主节点宕机了 没有从节点做故障转移 那整个集群是否可已继续使用的配置
>   cluster-require-full-coverage no
>   ```
>
> ![image-20240911150507609](D:\Desktop\gitee\java-learning\sc240601\Linux\img\集群中节点的配置文件.png)

#### 7.3 创建开启集群的脚本

> ==注：刚创建好的脚本一般是没有执行权限x的，所以要chmod 744 start.sh==
>
> ```sh
> #!/bin/bash
> #上面这句表示该文件是一个可执行的脚本
> #注释用#
> #打印一句话使用echo
> echo '开启redis集群'
> for i in {7000..7006} #设置循环
> do #循环开启
> 	redis-server redis-$i.conf
> 	echo 'redis-'$i'启动成功'
> done #循环结束
> echo 'redis集群已经开启'
>   #做循环使用for i in {7000..7006} do done
>   #使用$i来取出变量,变量不能写在字符串里面，直接拼接,脚本中没有拼接符
> #定义一个集合{7000..7006} 其中..表示范围
> #do-循环开启 done-循环结束
> ```

#### 7.4 开启自动分配槽点（只需要开启一次，哪怕关闭了Linux）

> 最后一个配置：让redis自动分配槽点 自动创建主从节点（只需要做一次）
>
> ```shell
> #注意下面是一条指令 不可以换行
> # --cluster-replicas 1 表示每个主节点 对应1个从节点
> redis-cli --cluster create 192.168.111.111:7000 192.168.111.111:7001 192.168.111.111:7002 192.168.111.111:7003 192.168.111.111:7004 192.168.111.111:7005 192.168.111.111:7006 --cluster-replicas 1
> ```
>
> ==注：运行上面的命令时，如果出现确认提示，必须要输入yes（一定是小写的yes），否则集群槽点分配失效，再次输入命令就会出现错误，就会出现不是空的错误，因为槽点已经分配过了。==
>
> 遇到该问题的解决方法：
>
> * 先关闭集群所有redis服务器 （kill -9 后可以跟多个进程）
>
> * 删除redis目录下data包下的所有配置文件
>   ![image-20240911154702251](D:\Desktop\gitee\java-learning\sc240601\Linux\img\删除data包下的所有文件.png)
>
> * 重启服务器 ./开启集群的脚本
>
> * 重新执行自动分配槽点的命令，遇到中间的提示输入yes(一定是小写的yes)
>
>   以下为成功的结果：
>   ![image-20240911155054424](D:\Desktop\gitee\java-learning\sc240601\Linux\img\自动分配槽点命令成功.png)
>   还能在上面的提示看到集群的具体分配信息：
>   ![image-20240911155504359](D:\Desktop\gitee\java-learning\sc240601\Linux\img\集群的基本信息1.png)



#### 7.5 测试集群的自动分配

> 测试前要导入redis的相关依赖。
>
> ```java
> //测试集群可用性
> public class TestCluster{
>     public static void main(String[] args) {
>         Set<HostAndPort> set=new HashSet<>();
>         for(int i=7000;i<=7005;i++) {
>             set.add(new HostAndPort("192.168.100.250", i));
>         }
>         //创建集群对象
>         JedisCluster cluster=new JedisCluster(set);
>         int count=1;
>         while(true){
>             try {
>                 cluster.set("key-"+count,"value-"+count);
>                 System.out.println("集群添加数据成功:key-"+count++);
>                 Thread.sleep(2000);
>             } catch (Exception e) {
>                 System.out.println("主节点可能宕机了 请等待...");
>             }
>         }
>     }
> }
> ```
>
> 测试时不能杀死太多节点，至少要留3对主从，不然会一直抛异常。



### 8.springboot整合redis

> 使用: 一般是在业务层使用，用来做缓存。
> 可以直接使用RedisTemplate进行手动存储
> 可以使用redis直接来实现缓存功能:不推荐初学者使用

#### 8.1 导入依赖

> 1.springboot整合redis依赖  2.redis客户端

```xml
<!--redis-->
<!--springboot整合redis的依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<!--redis客户端依赖，单纯的ssm项目只导入这个依赖就能连接redis-->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```

#### 8.2 springboot配置文件 配置redis集群

```properties
### redis配置
#1. 配置redis集群节点 ip1:端口1,ip2:端口2,...
#如果后期很多节点 不方便配置 可以通过配置类写循环来实现
#但还是建议在这里配置，因为ip和端口可能都是没有规律的，无法在配置类中写循环
spring.redis.cluster.nodes=192.168.111.111:7000,192.168.111.111:7001,192.168.111.111:7002,192.168.111.111:7003,192.168.111.111:7004,192.168.111.111:7005,192.168.111.111:7006
#2. redis辅助配置 都可以不配置 都有默认值
#连接池最大连接数 负数表示不限制
spring.redis.jedis.pool.max-active=-1
#连接池最大等待时间
spring.redis.jedis.pool.max-wait=-1ms
#连接池最大空闲连接默认就是8
spring.redis.jedis.pool.max-idle=8
#连接池最小空闲连接 默认就是0
spring.redis.jedis.pool.min-idle=0
```

#### 8.3 springboot定义redis配置类RedisConfig

```java
package com.sc.sm.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@CacheConfig //缓存配置
@EnableCaching //开启注解缓存
@Configuration
public class RedisConfig {
    @Autowired
    RedisConnectionFactory factory;

    //只需要创建一个bean对象RedisTemplate 操作redis的核心对象
    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        //报红没关系，因为配置文件写了集群节点地址
        //服务器启动后，会自动创建连接工厂 所以spring容器会自动注入。现在因为没注入才报错的。
        RedisTemplate<String, Object> rt = new RedisTemplate<>();
        //1.指定key序列化方式字符串 (StringRedisSerializer:redis的字符串序列化方式）
        rt.setKeySerializer(new StringRedisSerializer());
        rt.setHashKeySerializer(new StringRedisSerializer());
        //2.指定value序列化方式 json
        rt.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        rt.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //3.设置连接工厂
        rt.setConnectionFactory(factory); //服务器启动后，会自动创建连接工厂 所以spring容器会自动注入。现在因为每注入才报错的。
        return rt;
    }

    //假设 以后节点有很多 不方便写配置文件ip:端口， 借助于配置类直接把连接工厂创建出来
//    @Bean
//    RedisConnectionFactory factory() {
//        Set<String> nodes = new HashSet<>();
//        for (int i = 7000; i < 7007; i++) {
//            nodes.add("192.168.111.111:"+i);//注意主机后面有一个冒号
//        }
//        RedisClusterConfiguration cluster = new RedisClusterConfiguration(nodes);
//        return new JedisConnectionFactory(cluster);
//    }
}


// 配置类创建问题：
// 1.配置类循环导入redis主机时注意冒号: （报错看第一个报错的结尾，或最后一个报错）
// 2.Springboot配置类中的redis的配置多了一个.
// 3.防火墙没有关闭导致连接受阻
// 4. RedisConnectionFactory factory; redis连接工厂是写在方法外面
```

#### 8.4 使用`RedisTemplate`来做redis缓存(Server层)

> 1. 连接redis，创建redis对象
>
>    ```java
>    @Autowired //在配置类中创建redis连接工厂后会自动注入。
>    RedisTemplate<String, Object> redis; //连接redis，注意是写在方法外面
>    ```
>
> 2. 使用redis对象存储数据到redis服务器中
>
>    ```java
>    // 设置简单字符串值。
>    redis.opsForValue().set();
>    // 操作列表。使用 leftPush、rightPush 等方法。
>    redis.opsForList().rightPush("myList", "value1"); // 使用 rightPush 在列表末尾添加元素 
>    // 存储数据到哈希中。 
>    redis.opsForHash().put("myHash", "field1", "value1");  
>    // 向集合中添加元素。 
>    redis.opsForSet().add("mySet", "value1"); 
>    // 向有序集合中添加元素并指定分数。 
>    redis.opsForZSet().add("myZSet", "value1", 1.0); 
>    ```
>
> 3. 使用redisd对象获取数据(有数据就不走mysql)
>
>    ```java
>    // 获取简单字符串值  
>    String myValue = (String) redis.opsForValue().get("myKey");
>          
>    // 从列表中获取数据  
>    List<String> myList = redis.opsForList().range("myList", 0, -1); // 获取整个列表  
>    String firstElement = redis.opsForList().leftPop("myList");//获取并移除列表的第一个元素
>          
>    // 从集合中获取数据  
>    Set<String> mySet = redis.opsForSet().members("mySet");
>          
>    // 从有序集合中获取数据  
>    Set<String> myZSet = redis.opsForZSet().range("myZSet", 0, -1); // 获取整个有序集合  
>    Set<ZSetOperations.TypedTuple<String>> myZSetWithScores = redis.opsForZSet().reverseRangeWithScores("myZSet", 0, -1); // 获取整个有序集合及其分数，按分数降序排列
>          
>    // 从哈希中获取数据  
>    String value1 = (String) redis.opsForHash().get("myHash", "field1");  
>    Map<Object, Object> allFields = redis.opsForHash().entries("myHash");
>    ```

> 在 Spring Data Redis 中，`RedisTemplate` 提供了多种操作 Redis 数据结构的方法。你列出的方法属于不同的操作类型，分别用于处理 Redis 中的哈希（Hash）、列表（List）、有序集合（ZSet 或 Sorted Set）、集合（Set）和简单字符串值（Value）。下面是每个方法的简要说明和正确使用方式：
>
> 1. **`redis.opsForHash().hasKey(key, hashKey)`**
>    - **说明**：检查哈希中是否存在指定的键（`key`）和哈希键（`hashKey`）对应的值。
>    - **参数**：
>      - `key`：Redis 中哈希的键。
>      - `hashKey`：哈希中的键。
>    - **返回值**：`boolean`，表示是否存在。
>
> 2. **`redis.opsForList().set(key, index, value)`**
>    - **说明**：在列表中指定索引位置设置值。如果索引超出当前列表的长度，会在列表末尾添加元素。
>    - **参数**：
>      - `key`：Redis 中列表的键。
>      - `index`：要设置的索引位置。
>      - `value`：要设置的值。
>    - **注意**：这个方法在较新版本的 Spring Data Redis 中可能已经被弃用或更改，因为 List 的 `set` 操作不是 Redis 原生命令的直接映射。你可能需要使用其他方法，如 `leftPush`、`rightPush`、`range` 等来操作列表。
>
> 3. **`redis.opsForZSet().add(key, value, score)`**
>    - **说明**：向有序集合中添加元素，并指定其分数（用于排序）。
>    - **参数**：
>      - `key`：Redis 中有序集合的键。
>      - `value`：要添加的值。
>      - `score`：与值关联的分数。
>    - **返回值**：`boolean`，表示是否成功添加。
>
> 4. **`redis.opsForSet().add(key, values)`**
>    - **说明**：向集合中添加一个或多个元素。
>    - **参数**：
>      - `key`：Redis 中集合的键。
>      - `values`：要添加的值，可以是单个值或多个值的集合。
>    - **返回值**：`long`，表示实际添加的元素数量。
>
> 5. **`redis.opsForValue().set(key, value)`**
>    - **说明**：设置简单字符串值。
>    - **参数**：
>      - `key`：Redis 中字符串的键。
>      - `value`：要设置的值。
>    - **返回值**：`void`，表示操作不返回结果。
>
> **注意**：
> - 在使用这些方法之前，确保你已经正确配置了 `RedisTemplate`，包括连接工厂、序列化器等。
> - Redis 的数据类型和操作是区分大小写的，因此确保你的键和值在大小写上是一致的。
> - 对于 `opsForList().set()` 方法，如果你在使用较新版本的 Spring Data Redis，并且发现这个方法不可用或行为不符合预期，请查阅官方文档以获取最新的 API 说明和使用指南。
>
> 最后，这里提供的是操作 Redis 数据结构的基本方法。在实际应用中，你可能还需要考虑事务、管道、发布/订阅等高级功能。

```java
package com.sc.sm.service.Impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.sm.mapper.MyuserMapper;
import com.sc.sm.pojo.Myuser;
import com.sc.sm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service("us1")
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    MyuserMapper mapper;
    @Autowired
    RedisTemplate<String, Object> redis; //连接redis，注意是写在方法外面

    @Override
    public int add(Myuser u) {
        //批量删除redis多个key值，通过keys命令，写正则来匹配多个key
        //新增要更新分页信息，所以要删除所有分页信息
        redis.delete(redis.keys("user-*")); //这里只是凑巧删除了用户信息
        return mapper.insertSelective(u);
    }

    @Override
    public int del(Integer id) {
        redis.delete("user-*");
        return mapper.deleteByPrimaryKey(id);
    }

    //redis批量删除
    public Long delAll(Integer id) {
        return redis.delete(redis.keys("user-*"));
    }

    @Override
    public Myuser update(Myuser u) {
        // 更新就是删除，删完了，等用户重新查mysql，再存入redis，完成了更新
        //新增要更新分页信息，所以要删除所有分页信息
        // 为什么要先删除后更新？
        redis.delete(redis.keys("user-*")); //这里只是凑巧删除了用户信息
        mapper.updateByPrimaryKeySelective(u);
        return mapper.selectByPrimaryKey(u.getId());
    }

    //使用redis来做缓存
    //1. 查询：第一次查mysql, 保存redis, 第二次就直接查redis就可以了
    //2. 增删改：要情况redis的缓存，防止脏读
    public Myuser selectById(Integer id) {
        Myuser user = (Myuser) redis.opsForValue().get("user-"+id);
        if(user==null) { //查不到就证明是第一次查，要从mysql中来查，查完保存到redis
            user  = mapper.selectByPrimaryKey(id);
            System.out.println("查询到了mysql:" + user + "，并且存储到redis");
            redis.opsForValue().set("user-"+id, user);
        }
        return user;
    }

    @Override
    // 分页 + 动态搜索
    public PageInfo<Myuser> selectAll(Integer pageNum, Integer pageSize, Myuser u) {
        PageInfo<Myuser> p = (PageInfo<Myuser>)redis.opsForValue().get("user-"+pageNum+"-"+pageSize);
        System.out.println("尝试从redis获取分页数据：" + p);
        //如果从redis获取不到信息，表示没有缓存，证明我们要在mysql先获取数据再保存到redis
        if(p == null) {
            //设置分页
            PageHelper.startPage(pageNum, pageSize);
            p = new PageInfo<>(mapper.selectAll(u));
            redis.opsForValue().set("user-"+pageNum+"-"+pageSize, p);
            System.out.println("在mysql查询到了分页信息:" + p + "，并且存储到redis");
        }
        return p;
    }

    @Override
    public int deleteAll(Integer[] ids) {
        return mapper.deleteAll(ids);
    }
}
```

#### 8.5 使用缓存注解来做redis缓存（server层）

> 可以使用redis注解来实现缓存功能: 不推荐初学者使用 
>
> * 使用redis注解前提: 配置类上必须添加@Enablecaching 开启注解缓存
>   ==为什么我不加@Enablecaching也能使用？==
> * @Cacheable: 一般针对于査询操作 底层原理第一次査mysql 把方法的返回值存储到redis 第二次再访问时 不执行业务层方法(不走mysql)
> * @CacheEvict: 清空缓存的注解 一般适用于增(删) 改
> * @CachePut: 更新缓存的注解 一般适用于更新，redis会把业务层方法的返回值 更新到redis中

```java
package com.sc.sm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sc.sm.mapper.MyuserMapper;
import com.sc.sm.pojo.Myuser;
import com.sc.sm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("us2")
public class MyUserServiceImpl2 implements MyUserService {
    @Autowired
    MyuserMapper mapper;

    @Override
    public int add(Myuser u) {
        return mapper.insertSelective(u);
    }

    @CacheEvict(cacheNames = "user", key = "#id")
    public int del(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }


    @CachePut(cacheNames = "user", key = "#u.id")
    public Myuser update(Myuser u) {
        mapper.updateByPrimaryKeySelective(u);
        return mapper.selectByPrimaryKey(u.getId());
    }

    //使用redis注解来做缓存
    //如果不加注解参数，自动生成的key的名字非常复杂
    //cacheNames: 缓存的名字 默认cacheNames::key  作为存储redis的key
    //cacheNames: 缓存的名字
    //key: 可以随便写也可以写方法的参数 #id #u.id
    @Cacheable(cacheNames = "user", key="#id") //结果为user::形参id的值
    public Myuser selectById(Integer id) {
        System.out.println("执行了mysql:" + id);
        return mapper.selectByPrimaryKey(id);
    }
}
```









### 9.redis缓存雪崩 穿透 击穿---面试题
如果使用redis做缓存 由于redis的不同原因(redis宕机了 网络延迟 key可能超时了...) 都会导致无法正常使用缓存 这样就会出现不同的问题

* 缓存穿透:在访问redis中不存在的数据 最后就只能访问关系型数据库比如mysql，如果正好用户量特别大 这样关系型数据库 压力就很大了 
  * 解决方案: 可以在redis缓存中存储一些空对象 这些空对象还要设置有效期。
* 缓存击穿: 在同一个时间点，访问同一个数据时，这时redis的数据突然就没了（可能被其他删除了，可能key因为超过时间失效了）这个时间，这么高的并发量，就会同时访问mysql数据库，造成击穿。
  * 解决方案：设置key永不过期，或者加锁，一个线程访问其他线程需要等待...
* 缓存雪崩: 在redis运行过程中，由于redis宕机了，或者redis中所有key集体过期了。
  * 解决方案：可以搭建redis集群，就算一台redis服务器宕机，还要其他服务器可以使用，针对key集体过期的问题，设置不同(随机的)的过期时间



### 10.redis总结

>1.什么场景下使用redis?  什么是redis?   项目中什么功能用了redis?
>
>2.redis五种基本类型?
>
>3.redis是否支持持久化?    RDB 和AOF区别?   优缺点?
>
>4.redis主从模式    主从复制 ?
>
>5.redis哨兵模式   集群模式?
>
>6.redis是否保证原子性？
>
>​	答:  redis不能保证原子性, 可以保证redis单个命令执行是原子性  
>
>7.redis是否支持事务?
>
>​	答:  redis有事务,  但是redis事务可以包含多次指令的执行 
>​	事务不能保证多次指令的是原子性   redis事务时没有回滚
>​	如果有条指令执行失败  其他指令依然可以执行
>​	redis事务主要分三个阶段: 1.开始事务    2.命令入行   3.执行事务
>
>8.redis缓存穿透   缓存击穿   缓存雪崩 ?  解决方案?
>
>9.项目中你是如何使用redis的?
>
>- redisTemplate     
>- redis注解(@Cacheable  @CachePut  @CacheEvict)
>
>...













