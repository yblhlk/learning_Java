## Vue3

### 1. 环境搭建

#### 1.1 安装和配置VSCode 

> * 下载VSCode.exe 双击运行。
>
> * 设置里修改 界面风格、字体、终端字体。
>   <img src="D:\AppData\Typora\typora-user-images\image-20240820174029487.png" alt="image-20240820174029487" style="zoom: 56%;" />
>
>   <img src="D:\AppData\Typora\typora-user-images\image-20240820171134652.png" alt="image-20240820171134652" style="zoom: 40%;" />
>
>   <img src="D:\AppData\Typora\typora-user-images\image-20240820171539277.png" alt="image-20240820171539277" style="zoom:56%;" />
>
> * 安装插件Vue-Official（可能会换名字）
>   <img src="D:\AppData\Typora\typora-user-images\image-20240820173741447.png" alt="image-20240820173741447" style="zoom: 40%;" />

#### 1.2 安装node.js

> node.js类似于java的jdk是vue项目的运行环境 而且版本也很重要 如果是Vue3最低支持node14以上的版本
>
> * 双击群里发布node18的版本:
>
> * 检测一下是否成功:
>
>   ```shell
>   cmd窗口:node -v (注意是小写的v)
>   cmd窗口:npm -v (注意是小写的v)
>   ```

#### 1.3 npm 和 cnpm

> npm是node官方提供包管理器.cnpm是中国版的npm，是淘宝定制的命令行 用于替代npm 如果网络原因导致无法使用 推荐使用cnpm
>
> * 安装方式：在cmd命令提示行窗口(保证良好的网络环境)
>
>   ```shell
>   npm install -g cnpm --registry=https://registry.npm.taobao.org
>   或
>   npm install -g cnpm --registry=https://registry.npmmirror.com/
>   ```
>
>   ![image-20240820173236592](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装cnpm.png)

#### 1.4 安装Vue项目脚手架

> 是vue cli的一个组件，帮助我们快速构建Vue项目的，类似于之前使用的Maven项目，安装过程比较繁琐（可能会失败很多次 多装几次就好了，对网络环境要求较高）
>
> * 安装命令
>
>   ```shell
>   cnpm install -g @vue/cli
>   ```
>
>   ![image-20240820175325591](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装Vue项目脚手架.png)

#### 1.5 设置npm和cnpm地址都设置成淘宝镜像地址
> 用来加速
>
> ```shell
> 以下两个网址截止8月21仍有效
> npm config set registry https://registry.npmmirror.com/  
> cnpm config set registry https://registry.npmmirror.com/
> ```
>
> 设置成功后C盘的用户的XXX下会出现三个文件：
>
> ![image-20240821111640709](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\设置npm和cnpm的地址.png)
>
> 修改.vuerc文件，设置为true开启加速。(创建好的模板也保存在.vuerc文件中)
> ![image-20240821111826862](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\修改vuerc文件.png)

### 2. 创建Vue项目

#### 2.1 通过终端创建

> * 在VSCode中用快捷键 `Ctrl  + ~` 打开终端
>
> * 输入命令创建项目：vue create 项目名`（必须都小写）`
>   ![image-20240821142153511](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\vue项目的名称必须小写.png)
>
> * 选择vue项目模板格式：（选第三个自定义模板）
>
>   ```shell
>   Vue CLI v5.0.8
>   ? Please pick a preset:
>     Default ([Vue 3] babel, eslint)   -- Vue3 模板只带了两个插件
>     Default ([Vue 2] babel, eslint)   -- Vue2 模板只带了两个插件
>   > Manually select features  -- 推荐手动选择插件
>   ```
>
> * 选择自定义模板后，选择项目需要的插件(使用`上下键切换`，使用`空格选择或取消`，按`回车确认`)
>
>   ```shell
>   >(*) Babel
>    ( ) TypeScript
>    (*) Progressive Web App (PWA) Support
>    ( ) Router
>    ( ) Vuex
>    ( ) CSS Pre-processors
>    ( ) Linter / Formatter
>    ( ) Unit Testing
>    ( ) E2E Testing
>   ```
>
>   ```properties
>   Babel:一个js编译器转码器 可以将ES6转换成ES5代码 主要用于向下兼容
>   	  #属于必加项
>   	  
>   TypeScript:底层就是对于js做了一个封装 相当于一种面向对象的js
>   		   #看需要导入
>   		  :简称叫ts 最后运行时也会转换成js;
>   		  :如果选择了它:会有两个子菜单...
>   		  
>   Web App:属于web应用程序 
>   		#属于必加项
>   
>   Router:vue路由 通过请求链接到具体的vue组件(跳转) 目前看需要导入 
>   	   #后期是必加项
>   
>   Vuex: vue的状态管理模式 可以保存一些vue中的对象 、
>         #看需要导入
>         
>   CSS Pre-processors: CSS预处理器 目前看需要 
>   					#一般不需要导入
>   
>   Linter / Formatter: 代码风格检查和格式化，用于检测代码是否符合规范的
>   					#慎用，一般不导入 
>   					#因为如果后期多写个空格也会检测你错了 为了避免不必要的麻烦 不要导入
>   
>   Unit Testing:单元测试的插件 类似于java的junit
>   			 #用于测试
>   			 
>   E2E Testing:(end to end )端到端的测试
>    			#用于测试
>   ```
>
> * 选择第一个和第三个插件关闭第七个插件后回车确认，选择`vue3(3.x)`确认
>
> * 配置 配置文件放在哪里 是默认位置还是放在package.json中，`推荐使用默认`
>
>   ```java
>   > In dedicated config files  -> 使用默认位置
>     In package.json            -> 放入package.json中
>   ```
>
> * 是否要保存历史配置,可以保存上一次创建vue项目的插件配置，可以让我们更加快接创建项目 后期可以使用 前期练手选择N 后期选择y
>   `创建好的模板保存在.vuerc文件中`
>
>   ```java
>   Save this as a preset for future projects?(y/N)
>   ```
>
> * 完成创建，出现以下界面表示vue项目创建成功
>
>   ![image-20240821114257596](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\成功创建vue项目.png)
>
> * 使用`cd one`进入项目
>
> * ==进入项目后==，使用`npm run serve`运行项目

#### 2.2 通过图形化界面创建

> * 在命令行通过 `vue ui` 命令来开启图形化创建工具。
>   ![image-20240821142544672](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\图形化界面创建vue1.png)
> * 选择创建项目
>   ![image-20240821142819551](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\图形化界面创建vue2.png)
> * 创建项目
>   <img src="D:\AppData\Typora\typora-user-images\image-20240821143014464.png" alt="image-20240821143014464" style="zoom: 21%;" /><img src="D:\AppData\Typora\typora-user-images\image-20240821143208247.png" alt="image-20240821143208247" style="zoom: 20%;" />
> * 选择第一个和第三个选项，关闭第七个选项
>   <img src="D:\AppData\Typora\typora-user-images\image-20240821143430070.png" alt="image-20240821143430070" style="zoom: 32%;" />
> * 选择vue版本，确认创建
>   <img src="D:\AppData\Typora\typora-user-images\image-20240821150119600.png" alt="image-20240821150119600" style="zoom: 33%;" />
> * 是否保存模板
>   <img src="D:\AppData\Typora\typora-user-images\image-20240821150322423.png" alt="image-20240821150322423" style="zoom: 55%;" />
> * 创建成功出现以下页面
>   <img src="D:\AppData\Typora\typora-user-images\image-20240821150445561.png" alt="image-20240821150445561" style="zoom: 32%;" />

#### 2.3 Vue项目的目录结构



### 3. 什么是Vue

> vue是一个前端框架，免除原生的javascript中的繁琐的dom操作(getElement..)，是基于MVVM(Model-View-ViewModel)思想,用于实现数据双向绑定,使开发者只关注于数据即可
>
> * MVVM思想: 实现数据双向绑定，
>   就是模型的数据(Model) 发生了改变，页面展示的数据(View)也会改变，反之如果展示的数据发送改变了 模型的数据也会改变。
>   VM(ViewModel)就是用于实现Model和View之间数据双向绑定 通常ViewModel是通过js实现的(Vue对象)
>   ==实际效果就是：如果输入框修改了，下面绑定的数据也会修改，反之下面的数据被修改了，输入框中的数据也会修改，通过v-mode属性来实现的。==

#### 3.1 Vue项目的目录结构

> ![image-20240821144901392](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\Vue项目的目录结构.png)
>
> * node_modules：运行vue项目需要的第三方组件或插件，类似于maven导入的依赖(jar) 如果删除掉了，只需要在终端输入`vue install`命令就可以重新安装回来
> * public：vue项目提供的默认图标首页，一般vue项目写完后进行打包时，会默认存在这个位置，会把vue项目的组件转化为css/html，只需要输入命令vue build
> * src：开发人员的核心目录
>   * assets：存储静态资源的位置，css img
>   * `components`：存放vue组件的目录，`创建的所有vue页面都放在该目录下`，但是以后添加了路由插件router就会放在路由的包下(就是我们编写的页面就放在这个目录下)
>   * App.vue：vue的核心组件，所有组件/插件都必须经过App组件，如果不走它就会失效（所以加了新插件可能要修改该文件）
>   * main.js：程序的入口，类似于java的main方法，加载完成后会运行App.vue
>   * registerServiceWorker.js：存储一些监听，不重要
> * package.json：等价于之前maven项目中的pom.xml，vue项目安装的所以插件和第三方组件都可以在这里查看。
> * vue.config.js：等价于springboot中的application.properties vue项目的唯一配置文件。==注：配置文件只要修改，就必须重启Vue项目才能生效==

### 4. Vue基础语法

> * template标签：表示编写html内容
>
>   ```vue
>   <!-- 表示编写html内容 -->
>   <template>
>   </template>
>   ```
>
> * script标签：表示编写vue业务逻辑，可以通过js语法编写
>
>   ```vue
>   <!--表示编写vue业务逻辑，可以通过js语法编写-->
>   
>   <!--vue2语法，要自己导出组件-->
>   <script>
>       //导出当前默认组件
>       // 首先一定要导出组件，是为让其他组件能导入 否则App.vue页面就不能引入它显示
>       export default {
>           data(){
>               return {
>                   msg:"信息"
>               }  
>           }
>       }
>   </script>
>   
>   <!--vu3语法-->
>   <script setup>
>       // 使用setup可以更加方便的定义变量
>       import {ref} from 'vue'
>       // 要不变量定义成常量 vue规则变量不能改 但是内容可以修改
>       const msg=ref('呵呵')
>   </script>
>   ```
>
> * style标签：表示编写css
>
>   ```vue
>   <!--编写css-->
>   <style>
>       
>   </style>
>   ```

#### ==4.1 vue声明变量的方式有哪些==

> * var：通常用于定义全局变量，允许重复声明
>
> * let：ES6新增的，是用来替代var的，只能作用在局部区域 不能重复声明
>
> * const：ES6新增的，用来声明常量的(使用的最多)，只能够作用在局部区域 不能重复声明
>
>   ```vue
>   <script setup>
>       const user={}; //非响应式，写死了。
>       
>       import {ref} from 'vue' //导入ref包，作用是引用, vue只能用单引号括起来
>       const user=ref({}); //响应式，输入user对象的地址不能改，但是对象里面的属性是可以修改的。
>   </script>
>   ```

#### 4.2 vue基本指今
> 指令:就是html标签里面添加带有v-前缀的特殊属性，不同指令具有不同的函数而vue是通过这些指令还实现页面动态的效果 比如: v-in v-for v-model....
>
> | 指令                     | 作用                                               |
> | ------------------------ | -------------------------------------------------- |
> | v-bind                   | 为html标签绑定属性值的，比如设置href css样式       |
> | v-model                  | 为==表单元素==插件双向数据绑定的                   |
> | v-on                     | 为html标签绑定事件                                 |
> | v-if   v-else-if  v-else | 做条件性渲染，满足条件就显示元素(渲染)，否则不渲染 |
> | v-show                   | 条件性渲染                                         |
> | v-for                    | 做列表渲染，就是遍历下面定义的集合和数组变量的     |

##### a. v-bind指令

> 1
>
> ```vue
> <template>
>     <!-- v-bind 用于绑定属性值
>             这里就是将下面创建的url变量，绑定给a标签
>             v-bind可以省略，一个:就表示v-bind
>       -->
>      <a v-bind:href="url">链接1</a>
>      <a :href="url">链接2</a> <!--v-bind可以省略-->
>      <!-- 借助于双向绑定修改url的值 -->
>      地址：<input v-model="url"/>
> </template>
> 
> <script setup>
> import {ref} from 'vue' //vue只能用单引号括起来
> const url = ref("http://www.baidu.com");
> </script>
> ```

##### b. v-model指令

> 使用v-model属性来实现双向绑定，.lazy表示懒加载
>
> ```vue
> <template>
>     <!-- 存放html的内容 -->
>     <h3>FirstVue</h3>
>     <div>HelloWorld Vue</div>
>     <p>输入：<input v-model.lazy="msg"/></p>
>     <!-- 使用v-model属性来实现双向绑定，.lazy表示懒加载 -->
>     <p>输出：{{ msg }}</p>
>     <img src="../assets/logo.png"/>
> </template>
> <!-- setup属于vue3.2语法糖
>     1.省略导出export 它会默认导出
>     2.省略繁琐的data()return 返回值
>     3.省略methods 存放js函数的位置
>     缺点必须把对象变成响应式的，使用要引入ref包
> -->
> <script setup>
>     // 使用setup可以更加方便的定义变量
>     import {ref} from 'vue' //vue只能用单引号括起来
>     // 要不变量定义成常量 vue规则变量不能改 但是内容可以修改
>     const msg=ref('呵呵')
> </script>
> ```
>
> 

##### c. v-if指令 和 v-show指令

> 1
>
> ```vue
> <template>
>     <div v-show="status==1">show启用</div>
>     <div v-show="status==2">show禁用</div>
>     <div v-show="status==3">show未验证</div>
>     <!-- 表示编写html内容 -->
>     <div v-if="status==1">启用</div>
>     <div v-else-if="status==2">禁用</div>
>     <div v-else-if="status==3">未验证</div>
>     <div v-else>冻结</div>
>     <input v-model="status"/>
> </template>
> 
> <!-- setup属于vue3.2语法糖
>     1.省略导出export 它会默认导出
>     2.省略繁琐的data()return 返回值
>     3.省略methods 存放js函数的位置
>     缺点必须把对象变成响应式的，使用要引入ref包
> -->
> <script setup>
>     import {ref} from 'vue'
>     const status=ref(1)
> </script>
> ```

##### ==d. v-if 和 v-show的区别==
> ​	if底层不满足就不会渲染(创建)标签，标签就是不存在的
> ​	show底层通过display是否设置none来显示隐藏的，标签都是存在的但是有的隐藏了看不到

##### e. v-for指令

> 1
>
> ```vue
> 语法规则1： 这个集合或数组 需要在script中定义好
> <标签 v-for="临时别名 in 集合或数组">
>     <!--这个集合或数组 需要在script中定义好-->
>     {{临时变量}}
> </标签>
> 
> 语法规则2： 如果页面需要遍历数据的索引值
> <标签 v-for="(临时别名,索引变量) in 集合或数组">
>     {{索引变量+1}} : {{临时变量}}
>     <!--索引变量从0开始-->
> </标签>
> ```



### 问题记录

> 错误1： script标签不加setup
>
> ![image-20240821171055276](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\错误1：script不加setup.png)