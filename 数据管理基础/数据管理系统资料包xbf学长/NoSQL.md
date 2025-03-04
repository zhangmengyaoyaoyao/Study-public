# NoSQL

> ppt 62-74

见《nosql精粹》第1-6，8-11章

## 1. 为什么关系型数据库好

- 可回答第一章的部分
- 也可回答PPT62的部分

## 2. NoSQL的由来（考选择题）

- 内存中的数据结构和关系型数据不匹配，每次处理的过程很麻烦（阻抗失谐）
- 集成数据库和应用程序数据库的问题（NoSQL是应用程序数据库）
- 集群问题（让用户自行决定可用性和一致性的平衡）

## 3. 聚合无知

- 关系型数据库不知道一个特定的聚合（数据结构），把其打散存在不同关系里，select时，再聚在一起

## 4. 无模式

关系型数据库模式和NoSQL模式的区别：

NoSQL是无模式的，不需要预先定义值和型。

![image-20220619224154166](https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619224154166.png)

![image-20220619224203066](https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619224203066.png)

![image-20220619224209118](https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619224209118.png)

## 5. 分布式

- 概念默认大家知道，没必要考
- PPT66

## 6. 一致性和持久性

- CAP定理
  - CAP定理:给定“一致性”(Consistency)、“可用性”(Availability)、“分区耐受性”( Partition tolerance) 这三个属性，我们只能同时满足其中两个属性。
    - “一致性”
    - “可用性”，如果客户可以同集群中的某个节点通信，那么该节点就必然能够处理读取及写入操作。 
    - “分区耐受性” ，如果发生通信故障，导致整个集群被分割成多个无法互相通信的分区时(这种情况也叫“ 脑裂”，split brain)，集群仍然可用。
  - 分区耐受性不能够妥协，但凡有一个结点down，整个系统都要down掉

- 剩下的理解为主，不需要照搬背诵

## 7. 仲裁

- 经典公式：
  - R + W > N
  - 写入：二分之一
- 复制因子
- 实际情况

## 8. key-value数据库

- 数据库是如何完成基本存放的想法 / 数据库存放的是什么东西，怎么存的
  - 是一张简单的哈希表(hash table)，主要用在所有数据库访问均通过主键(primary key)来操作的情况下。
    - 可把此表想象成传统的“关系” 该关系有两列：ID与NAME
      - ID列代表关键字，NAME列存放值。NAME列仅能存放String型的数据。
      - 应用程序可提供ID及VALUE值，并将这一键值对持久化
      - 假如ID已存在，就用新值覆盖当前值，否则就新建一条数据。
  - 客户端可以根据键查询值，设置键所对应的值，或从数据库中删除键。
    - “值”只是数据库存储的一块数据而已，它并不关心也无需知道其中的内容
    - 应用程序负责理解所存数据的含义。
    - 能够存储list、set、hash 等数据结构
- 适合做什么，不适合做什么
  - 非常适合保存会话(用会话ID作为键)、购物车数据、用户配置等信息
  - 不适合数据间关系、含有多项操作的事务、查询数据、操作关键字集合

<img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230157032.png" alt="image-20220619230157032" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230207232.png" alt="image-20220619230207232" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230216807.png" alt="image-20220619230216807" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230247150.png" style="zoom:33%;" />

## 9. 文档数据库

- 数据库是如何完成基本存放的想法
  - 文档彼此相似，但不必完全相同。文档数据库所存放的文档，就相当于键值数据库所存放的“值”。
  - 文档数据库可视为其值可查的键值数据库。
- 数据库存放的是什么东西，怎么存的
  - “文档”( document)是文档数据库中的主要概念。
    - 其格式可以是XML、JSON、BSON等。
    - 文档具备自述性(self-describing)，呈现分层的树状数据结构(hierarchical tree data structure)，可以包含映射表、集合和标量值。

![image-20220619230506254](https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230506254.png)

- 适合做什么，不适合做什么
  - 适用：事件记录、内容管理系统及博客平台、网站分析与实时分析、电子商务应用程序
  - 不适用：包含多项操作的复杂事务、查询持续变化的聚合结构

<img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230532073.png" alt="image-20220619230532073" style="zoom:37%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230539532.png" alt="image-20220619230539532" style="zoom:37%;" />

![image-20220619230738368](https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230738368.png)

## 10. 列族数据库

- 数据库是如何完成基本存放的想法
  - 列族数据库将数据存储在列族中，而列族里的行则把许多列数据与本行的“行键”(row key)关联起来。
- 数据库存放的是什么东西，怎么存的
  - 基本存储单元为“列”，列由一个“名值对”(name-value pair)组成，其中的名字也充当关键字。
  - 每个键值对都占据一列，并且都存有一个“时间戳”值。令数据过期、解决写入冲突、处理陈旧数据等操作都会用到时间戳。若某列数据不再使用，则数据库可于稍后的“压缩阶段”(compaction phase)回收其所占空间。
  - 行是列的集合，这些列都附在某个关键字名下，或与之相连。由相似行所构成的集合就是列族。
  - 每个列族都可以与关系型数据库的“行容器”(container of rows)相对照:
    - 两者都用关键字标识行，并且每一行都由多个列组成。
    - 其差别在于，列族数据库的各行不一定要具备完全相同的列，并且可以随意向其中某行加入一列，而不用把它添加到其他行中。

![image-20220619230901046](https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619230901046.png)

- “标准列族”(standard column family)中的列都是“简单列”(simple column) 。
- “超列族”(super column family)：
  - 如果某列中包含一个由小列组成的映射表，那么它就是“超列”(super column)。可将超列视为“列容器”(container of columns)。
  - 用超列构建的列族叫做“超列族” 。
  - 超列族适合将相关数据存在一起。但是，如果部分列在大部分情况下都用不到，则存在不必要的开销。
- “键空间” (keyspace)与关系型数据库中的“数据库”类似，与应用程序有关的全部列族都存放于此。
  - 必须先创建键空间，才能为其增添列族

- 适合做什么，不适合做什么
  - 适合：事件记录、内容管理系统与博客平台、计数器、限期
  - 不适合：需要以“ACID事务”执行写入及读取操作的系统。

<img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231049580.png" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231100397.png" alt="image-20220619231100397" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231109051.png" alt="image-20220619231109051" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231042459.png" alt="image-20220619231042459" style="zoom:33%;" />

## 11. 图数据库

- 数据库是如何完成基本存放的想法
  - 图数据库可存放实体及实体间关系。
  - 用图将数据一次性组织好，稍后便可根据“关系”以不同方式解读它。
- 数据库存放的是什么东西，怎么存的
  - 实体也叫“节点”(node)，它们具有属性(property)。可将节点视为应用程序中某对象的实例。
  - 关系又叫“边”(edge)，它们也有属性。边具备方向性( directional significance)，而节点则按关系组织起来，以便在其中查找所需模式。

<img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231153740.png" alt="image-20220619231153740" style="zoom: 45%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231212492.png" alt="image-20220619231212492" style="zoom: 45%;" />

- 适合做什么，不适合做什么
  - 适合：互联数据、安排运输路线、分派货物和基于位置的服务、推荐引擎
  - 不适合：更新全部或某子集内的实体

<img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231334636.png" alt="image-20220619231334636" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231341134.png" alt="image-20220619231341134" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231350216.png" alt="image-20220619231350216" style="zoom:33%;" /><img src="https://peng-img.oss-cn-shanghai.aliyuncs.com/markdown-img/image-20220619231356558.png" alt="image-20220619231356558" style="zoom:33%;" />