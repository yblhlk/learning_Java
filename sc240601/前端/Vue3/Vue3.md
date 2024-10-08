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

> ### npm
>
> - **定义**：npm（Node Package Manager）是Node.js的官方包管理器，用于Node.js插件的管理，包括安装、更新、卸载和发布等操作。
> - **功能**：`npm不仅是一个包管理工具，它还维护了一个庞大的包仓库`（registry.npmjs.org），开发者可以在其中找到并安装所需的Node.js包。
> - **特点**：npm的服务器位于国外，因此在国内使用时可能会受到网络延迟或不稳定的影响，导致包下载速度较慢或下载失败。
>
> ### cnpm
>
> - **定义**：cnpm是中国版的npm，由淘宝团队定制并开源。它是一个完整的npmjs.org镜像，可以用作npm的替代品（只读），以确保与官方服务的同步。
> - **功能**：cnpm与npm的功能几乎相同，都用于Node.js插件的管理。但由于其服务器位于国内，因此在使用时可以显著提高包的下载速度，减少因网络问题导致的下载失败。
> - **安装与使用**：要安装cnpm，可以使用npm的全局安装命令，并将仓库地址设置为淘宝的npm镜像。安装完成后，就可以使用cnpm命令来代替npm命令进行包的管理操作了。
> - **推荐场景**：当由于网络原因导致无法使用npm时，推荐使用cnpm作为替代方案。特别是在国内网络环境下，cnpm能够显著提升开发效率。

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

> Vue项目脚手架（Vue Project Scaffolding）通常指的是Vue CLI（Vue Command Line Interface，Vue命令行界面）`提供的一系列工具和功能`，用于快速生成、配置和管理Vue.js项目的结构。简单来说，Vue项目脚手架就是帮助开发者快速搭建Vue.js项目框架的工具集。

> 是vue cli的子组件，帮助我们快速构建Vue项目的，类似于之前使用的Maven项目，安装过程比较繁琐（可能会失败很多次 多装几次就好了，对网络环境要求较高）
>
> * 安装命令
>
>   ```shell
>   cnpm install -g @vue/cli
>   ```
>
>   ![image-20240820175325591](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装Vue项目脚手架.png)

#### 1.5 设置npm和cnpm地址（都设置成淘宝镜像地址）
> 用来加速
>
> * 在命令行中输入：
>
>   ```shell
>   以下两个网址截止到2024年9月1日仍有效
>   npm config set registry https://registry.npmmirror.com/  
>   cnpm config set registry https://registry.npmmirror.com/
>   ```
>
> * 设置成功后C盘的用户的XXX下会出现三个文件：![image-20240821111640709](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\设置npm和cnpm的地址.png)
>
> * 修改.vuerc文件，设置为true开启加速。(创建好的模板也保存在.vuerc文件中)
>   ![image-20240821111826862](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\修改vuerc文件.png)

### 2. 创建Vue项目

#### 2.1 通过终端创建

> * 在VSCode中用快捷键 `Ctrl  + ~` 打开终端
>
> * 输入命令创建项目：vue create 项目名==（必须都小写，项目名必须全部小写）==
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
>   			Use class-style component syntax 是否使用类风格装饰器（Y）
>   			Use Babel alongside TypeScript Babel和Ts是否一起使用(Y)
>   
>   Web App:属于web应用程序 
>   		#属于必加项
>   
>   Router:vue路由 通过请求链接到具体的vue组件(跳转) 目前看需要导入 
>   	   Use history mode for router? （n） 不使用历史版本的路由组件
>   	   
>   Vuex: vue的状态管理模式 可以保存一些vue中的对象 、
>         #看需要导入
>   
>   CSS Pre-processors: CSS预处理器 目前看需要 
>     					#一般不需要导入
>   
>   Linter / Formatter: 代码风格检查和格式化，用于检测代码是否符合规范的
>     					#慎用，一般不导入 
>     					#因为如果后期多写个空格也会检测你错了 为了避免不必要的麻烦 不要导入
>   
>   Unit Testing:单元测试的插件 类似于java的junit
>     			 #用于测试
>   
>   E2E Testing:(end to end )端到端的测试
>      			#用于测试
>
> * 选择第一个和第三个插件关闭第七个插件后回车确认，选择`vue3(3.x)`确认
>
> * 配置 配置文件放在哪里 是默认位置还是放在package.json中，`推荐使用默认`
>
>   ```java
>     > In dedicated config files  -> 使用默认位置
>       In package.json            -> 放入package.json中
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
> * 使用`cd XXX`进入项目所在目录
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

> 看3.1

### 3. 什么是Vue

> vue是一个前端框架，免除原生的javascript中的繁琐的dom操作(getElement..)，是基于MVVM(Model-View-ViewModel)思想,用于实现数据双向绑定,使开发者只关注于数据即可
>
> * MVVM思想: 实现数据双向绑定，
>   就是模型的数据(Model) 发生了改变，页面展示的数据(View)也会改变，反之如果展示的数据发送改变了 模型的数据也会改变。
>   VM(ViewModel)就是用于实现Model和View之间数据双向绑定 通常ViewModel是通过js实现的(Vue对象)
>   ==实际效果就是：如果输入框修改了，下面绑定的数据也会修改，反之下面的数据被修改了，输入框中的数据也会修改，通过v-mode属性来实现的。==

#### 3.0 MVVM数据模式

> a.MVVM设计模式是什么?谁来实现双向绑定的
>
> * 实现的是数据双向绑定 `页面中的视图` 和 `模型中的数据`
>   实现了双向绑定 只要改变任何一方 绑定的一方也会改变
> * 使用:v-model 去绑定下面模型中定义的变量

#### 3.1 Vue项目的目录结构

> ![image-20240821144901392](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\Vue项目的目录结构.png)
>
> * node_modules：运行vue项目需要的第三方组件或插件，类似于maven导入的依赖(jar) 如果删除掉了，只需要在终端输入`vue install`命令就可以重新安装回来
> * public：vue项目提供的默认图标首页，一般vue项目写完后进行打包时，会默认存在这个位置，会把vue项目的组件转化为css/html，只需要输入命令vue build
> * src：开发人员的核心目录
>   * assets：存储静态资源的位置，css img
>   * `components`：存放vue组件的目录，`创建的所有vue页面都放在该目录下`，但是以后添加了路由插件router就会放在路由的包下(就是我们编写的页面就放在这个目录下)
>   * App.vue：vue的核心组件，所有组件/插件都必须经过App组件，如果不走它就会失效（所以加了新插件可能要修改该文件）
>   * ==main.js==：`程序的入口`，类似于java的main方法，加载完成后会运行App.vue
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

#### ==4.1 vue声明变量的方式有哪些==（var和let和const的区别）

> * var：通常用于定义`全局变量`，`允许重复声明`
>
> * let：ES6新增的，是用来替代var的，只能作用在`局部区域` `不能重复声明`
>
> * const：ES6新增的，用来声明常量的(使用的最多)，只能够作用在局部区域 不能重复声明
>   `const`用于声明一个只读的常量。一旦一个常量被赋值后，就不能再被重新赋值
>   （但如果是对象或数组等引用类型，则可以修改其内部属性或元素）。
>   
>   ```vue
>   <script setup>
>       const user={}; //非响应式，写死了。{}是json语法表示一个对象
>             
>       import {ref} from 'vue' //导入ref包，作用是引用
>       const user=ref({}); //响应式，输入user对象的地址不能改，但是对象里面的属性是可以修改的。
>   </script>
>   ```
>   
>   > `ref` 函数是Vue的Composition API的一部分，用于创建一个响应式的引用对象。这个对象包含一个内部的value属性，该属性是响应式的，意味着当它的值变化时，任何依赖于它的视图或计算属性都会自动更新。
>   >
>   > 当你执行 `const user = ref({});` 时，你实际上是在创建一个响应式的对象引用，这个引用的value是一个空对象`{}`。这意味着你不能直接替换这个对象的引用（即你不能直接将`user.value`指向另一个对象），但你可以修改这个对象内部的属性，并且这些修改是响应式的。

#### 4.2 vue基本指今
> 指令:就是html标签里面添加带有v-前缀的特殊属性，不同指令具有不同的函数而vue是通过这些指令还实现页面动态的效果 比如: v-in v-for v-model....
>
> | 指令                     | 作用                                               |
> | ------------------------ | -------------------------------------------------- |
> | v-bind                   | 为html标签绑定属性值的，比如设置href css样式       |
> | v-model                  | 为==表单元素==实现双向数据绑定的                   |
> | v-on                     | 为html标签绑定事件                                 |
> | v-if   v-else-if  v-else | 做条件性渲染，满足条件就显示元素(渲染)，否则不渲染 |
> | v-show                   | 条件性渲染                                         |
> | v-for                    | 做列表渲染，就是遍历下面定义的集合和数组变量的     |

##### a. v-bind指令

> 用来实现template中的标签的属性和script中的变量的绑定，在template中需要值的标签的前面加上`bind:`或`:`，=后面就可以使用script中的变量了。(软编码)
> `也可以用于响应式地更新标签的属性`
>
> ```vue
> <template>
>   <input v-bind:value="v1" :type="type">
> </template>
> 
> <script setup>
>   const v1 = "adfas123";
>   const type = "password";
> </script>
> ```
>
> ```vue
> <template>
>  <!-- v-bind 用于绑定属性值
>          这里就是将下面创建的url变量，绑定给a标签
>          v-bind可以省略，一个:就表示v-bind
>    -->
>   <a v-bind:href="url">链接1</a>
>   <a :href="url">链接2</a> <!--v-bind可以省略-->
>   <!-- 借助于双向绑定修改url的值 -->
>   地址：<input v-model="url"/>
> </template>
> 
> <script setup>
> import {ref} from 'vue' //vue只能用单引号括起来
> const url = ref("http://www.baidu.com");
> </script>
> ```

##### b. v-model指令

> 使用v-model属性来实现双向绑定，.lazy表示懒加载，.trim表示去除首尾多余空格
>
> ```vue
> <template>
>   <input v-model="num">
>   <p>输入的内容：{{ num }}</p>
>   <input v-model.lazy="num1">
>   <p>输入的内容：{{ num1 }}</p>
>   <input v-model.trim="num2">
>   <p>输入的内容：{{ num2 }}</p>
> </template>
> 
> <script setup>
>   import {ref} from "vue"
>   const num = ref()
>   const num1 = ref()
>   const num2 = ref()
> </script>
> ```
>
> ```vue
> <template>
>     <!-- 存放html的内容 -->
>     <h3>FirstVue</h3>
>     <div>HelloWorld Vue</div>
>     <p>输入：<input v-model.lazy="msg"/></p>
>     <!-- 使用v-model属性来实现双向绑定，.lazy表示懒加载 -->
>     <p>输出：{{ msg }}</p>
> 	<!--使用下面定义好的变量/常量-->
>     <img src="../assets/logo.png"/>
> </template>
> <!-- setup属于vue3.2语法糖
>  1.省略导出export 它会默认导出
>  2.省略繁琐的data()return 返回值
>  3.省略methods 存放js函数的位置
>  缺点必须把对象变成响应式的，使用要引入ref包
> -->
> <script setup>
>  // 使用setup可以更加方便的定义变量
>  import {ref} from 'vue' //vue只能用单引号括起来
>  // 要不变量定义成常量 vue规则变量不能改 但是内容可以修改
>  const msg=ref('呵呵')
> </script>
> ```
>

##### c. v-if指令 和 v-show指令

> 用来判断一个标签是否该在页面显示。
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

> 做页面内容的循环遍历
>
> ```vue
> 语法规则1： 这个集合或数组 需要在script中定义好
> <标签 v-for="临时别名 in 集合或数组">
>  <!--这个集合或数组 需要在script中定义好-->
>  {{临时变量}}
> </标签>
> 
> 语法规则2： 如果页面需要遍历数据的索引值
> <标签 v-for="(临时别名,索引变量) in 集合或数组">
>  {{索引变量+1}} : {{临时变量}}
>  <!--索引变量从0开始,所以这里+1，让他从1开始-->
> </标签>
> ```
>
> ```vue
> <template>
>   <div v-for=" (i,j) in arr">
>     {{ j }} : {{ i }}
>     <div> for盒子 </div>
>   </div>
> </template>
> 
> <script setup>
> 	const arr = ref([11,22,33,44,55]);
> </script>
> 
> <style scoped></style>
> 
> ```

### 5. VSCode快速生成vue3模板

> Vscode的File(文件)选项->Preferences(首选项)->Configure Snippets(配置用户代码片段)->输入vue(或者vue.json)
> 修改vue.json文件
>
> ```json
> {
> 	"Print to console": {
> 		"prefix": "vueCode", //vue文件输入代码前缀启用模板
> 		"body": [ //主体自动生成的代码格式
> 			"<template>\n",
> 			"</template>\n",
> 			"<script setup>",
> 			"\timport {ref} from \"vue\"",
> 			"</script>\n",
> 			"<!--scoped是为了设置样式不能影响其他组件-->",
> 			"<style scoped>\n</style>",
> 			"$2"
> 		],
> 		"description": "Log output to console" //描述信息，无所谓
> 	}
> }
> ```

### 6.组件基础
> Vue是基于组件开发的,我们创建每一个*.vue文件 它是一种特殊的文件格式,在Vue中称这种文件叫做Vue组件(html 样式css 业务逻辑js)

> 在Vue.js中，`.vue` 文件是一种特殊的文件格式，它允许开发者将组件的模板（HTML）、样式（CSS）和业务逻辑（JavaScript）封装在同一个文件中。这种组织方式极大地提高了代码的可维护性和可读性，因为它遵循了“关注点分离”（Separation of Concerns, SoC）的原则，同时又以一种更灵活和集成的方式将它们结合在一起。
>
> 一个 `.vue` 组件文件通常包含三个部分：
>
> 1. **模板（Template）**：这是组件的HTML结构部分，它定义了组件的DOM结构。在 `.vue` 文件中，模板通常被 `<template>` 标签包裹。Vue编译器会把这个模板编译成虚拟DOM，然后在渲染时转换为真实的DOM。
> 2. **脚本（Script）**：这部分包含了组件的JavaScript逻辑，如数据（data）、计算属性（computed）、方法（methods）、生命周期钩子（lifecycle hooks）等。它使用 `<script>` 标签包裹，并通常使用ES6的模块系统（export default）来导出组件对象。
> 3. **样式（Style）**：这是组件的CSS样式部分，它定义了组件的样式规则。在 `.vue` 文件中，样式通常被 `<style>` 标签包裹。Vue支持CSS预处理器（如Sass、Less）以及作用域CSS（Scoped CSS），这有助于避免样式冲突，提高样式的模块化。
>
> `.vue` 文件还允许开发者在 `<script>`、`<template>` 和 `<style>` 标签上添加特殊的属性，以实现更高级的功能，比如使用TypeScript（通过添加 `lang="ts"` 属性到 `<script>` 标签）或者为样式指定不同的作用域（通过添加 `scoped` 属性到 `<style>` 标签）。
>
> 这种将模板、样式和逻辑封装在一起的方式，使得Vue组件既独立又灵活，非常适合于构建大型的单页应用（SPA）。

#### ==6.1 加载组件步骤==

> * 引入组件
>
>   ```vue
>   <script>
>   	import 组件名(可以自定义) from "./components/XXXX.vue"
>   </script>
>   ```
>
> * 注册组件
>
>   ```vue
>   //3.2新语法：
>   <script setup>
>       //0.使用前要导入defineComponent
>       import {defineComponent, ref} from "vue" 
>       //1.引入组件
>         import Two from './Child.vue'
>       //2.注册组件  vue3.2 添加setup语法糖后 需要导出 需要使用defineComponent()来挂载
>       //define:定义 Component:组件
>       defineComponent({  //使用前要导入defineComponent
>           components:{
>               Two
>           }
>       })
>   </script>
>   
>   //传统派语法：
>   <script>
>       //1.引入组件
>       import Two from "./Child.vue";
>       //2.在导出中注册组件
>         // 必须写一个export default才能被其他组件找到和导入
>       export default{
>           components:{
>               Two
>           }
>       }
>   </script>
>   ```
>   
>   > 通过export default将组件导出，这个导出的对象告诉Vue：“这里有一个Vue组件，它的配置是这样的。” 当你从另一个文件通过`import`语句导入这个组件时，你实际上是在获取这个导出的对象，然后你可以在Vue实例或另一个组件的`components`选项中注册它，以便在模板中使用。
>   >
>   > ==必须写一个export default才能被其他组件找到和导入==
>   
> * 显示组件 （在template标签中使用）
>
>   ```vue
>   <组件名/>
>   ```
>
>   App.vue => One.vue => Two.vue

#### 6.2 组件的交互

> 组件和组件之间如果没有任何关系 就没有意思 他们之间是可以进行交互的，比如:a组件的需要 给交给b组件使用 通过prop来进行组件交互。

> ==注:下面几种传递方式前提组件具有父子关系，如果没有父子关系借助于路由来传递数据。==

##### a. 引用者传递，被引用者接收（通过prop来组件交互）

> * 老版本：
>
>   > * 引用者：
>   >   * 在引用，被引用者标签时，在标签名后面加`:传递的变量名=值`来传递
>   > * 被引用者：
>   >   * 在`export default(导出)`中通过`props属性`来接收引用我的组件传来的数据。
>   >   * 在`props中`通过 `传递的变量名:{type: 数据类型, default: 默认值}` 来接收引用者传递的数据。
>
>   ```vue
>   引用者传递数据：
>   <template>
>       <h3>父组件</h3>
>       <!-- 3.显示子组件 -->
>       <!-- vue老版本 通过":属性名1=属性值1" 属性值就是要传递的数据（可以传递任意数据）-->
>       <!-- 注意是空格间隔不是逗号-->
>       <Two :age="myAge" :name="myName" :user="myUser" :list="myList"/>
>   </template>
>   
>   <!-- 注意注释方式 -->
>   <script>
>       //1.引入组件
>       import Two from "./Child.vue";
>       //2.在导出中挂载组件
>       export default{
>           components:{
>               Two
>           },
>           data(){ 
>               return{
>                   myAge:20,
>                   myName:"亚林",
>                   myUser:{id:1,sex:'男',class:"sc240601"},
>                   myList:["java","mysql","js","vue"]
>               }
>           }
>       }
>   </script>
>   ```
>
>   ```vue
>   被引用者接收数据：
>   <template>
>       <h3>子组件</h3>
>       <p>年龄：{{ age }}</p>
>       <p>姓名：{{ name }}</p>
>       <p>用户对象：{{ user }}：{{user.id}}-{{ user.sex }}-{{ user.class }}</p>
>       <p>学科集合：
>           <!-- <span>标签经常与CSS（层叠样式表）一起使用，以便对页面上的特定文本块进行样式定义，比如改变颜色、字体、大小等。 -->
>           <span v-for="(s,i) in list">
>               {{ s + " " }}
>           </span>
>       </p>
>   </template>
>   
>   <!--老版本获取数据引用我的组件传来的数据-->
>   <script >
>   export default{
>       //通过props来接收引用我的组件传来的数据
>       //age就是引用我的组件传递过来的属性名（自定义的）
>       props:{
>           age:{
>               type:Number,
>               default:0
>           },
>           name:{
>               type:String,
>               default:""
>           },
>           user:{
>               type: Object,
>               default:{}
>           },
>           list:{
>               type:Array,
>               default:[]
>           }
>       }
>   }
>   </script>
>   ```
>
> * 新版本（vue3.2)
>
>   > * 引用者：
>   >   * 在引用：被引用者标签时，在标签名后面加`:传递的变量名=值`来传递
>   > * 被引用者：
>   >   * ==在script标签中加上setup==，使用`defineProps()`来接收数据
>   >   * 在`defineProps()中`通过 `传递的变量名:{type: 数据类型, default: 默认值}` 来接收引用者传递的数据。
>
>   ```vue
>   引用者传递数据：
>   <template>
>       <h3>父组件</h3>
>       <!-- 3.显示子组件 -->
>       <!-- vue老版本 通过":属性名1=属性值1" 属性值就是要传递的数据（可以传递任意数据）-->
>       <!-- 注意是空格间隔不是逗号-->
>       <Two :age="myAge" :name="myName" :user="myUser" :list="myList"/>
>   </template>
>         
>   <script setup>
>       import {defineComponent, ref} from "vue"
>       //1.引入组件
>       import Two from './Child.vue'
>       //2.挂载组件  vue3.2需要使用defineComponent()来挂载
>       defineComponent({
>           components:{
>               Two
>           }
>       })
>       const myAge = ref(18);
>       const myName = ref("王");
>       const myUser = ref({id:1,sex:'男',class:"sc240601"});
>       const myList = ref(["java","mysql","js","vue"])
>   </script>
>   ```
>   
>   ```vue
>   被引用者接收数据：
>   <script setup>
>       import {ref} from 'vue';
>       //vue3.2 通过defineProps()来接收引用我的组件传来的值
>       defineProps({
>           age:{
>               type:Number,
>               default:0
>           },
>           name:{
>               type:String,
>               default:""
>           },
>           user:{
>               type: Object,
>               default:{}
>           },
>           list:{
>               type:Array,
>               default:[]
>           }
>       })
>   </script>
>   ```
>

##### b. 引用者接收，被引用者传递

> `被引用者借助于自定义事件来传递数据给引用者`
>
> * 被引用者：
>   * 如果vue之前的版本，借助于`this.$emit(参数1：自定义事件名，参数2：传递的数据)`
>
>   * 如果是新版Vue3.2通过导入`defineEmits(参数1：自定义事件，参数2：传递的数据) `来创建一个触发器绑定自定义事件。
>
> * 引用者：
>   * 在被引用者标签中加上`@自定义事件名="响应函数名"`来监听自定义事件是否被触发，触发就调用响应函数
>
>

> * 老版本：
>
> ```vue
> 被引用者传递数据：
> <template>
>     <h3>子组件</h3>
>     <fieldset>
>         <legend>给引用我的人发送数据</legend>
>         <p>@内置事件="方法名(参数)"，没有参数就可以简写为@内置事件="方法名"</p>
>         <h4><button type="button" @click="send">发放数据给引用我的人</button></h4>
>         <h4><input v-model="msg" /></h4>
>     </fieldset>
> </template>
> 
> <script>
>     import { ref } from 'vue';
>     export default {
>         //注意data后面是() 且没有":"
>         data() {
>             return {
>                 msg: ""
>             }
>         },
>         methods: {
>             send: function(){
>                 //this.$emit() 用于向引用我的组件传值
>                 //参数1：自定义事件名，可以随便写
>                 //参数2：要传递的数据，可以是任意类型，可以写多个参数
>                 this.$emit("childSend", this.msg);
>             }
>         }
>     }
> </script>
> ```
>
> ```vue
> 引用者接收数据：
> <template>
>     <h3>父组件</h3>
>     <fieldset>
>         <legend>引用者接收数据</legend>
>         <h3>父组件接收到的数据：{{ result }}</h3>
>     </fieldset>
> 	
> 	<!--@自定义事件="响应函数"：监听自定义事件是否被触发，触发就调用响应函数-->
>     <Two @childSend="getChildData" />
> </template>
> 
> <script>
> //1.引入组件
> import Two from "./Child.vue";
> //2.在导出中挂载组件
> export default {
>     components: {
>         Two
>     },
>     methods: {
>         // 方法的参数就是子组件传递的数据（参数名可以随便写，可以写多个参数）
>         getChildData: function (result) {
>             this.result = result; //注意lambda表达式形式的函数不能用this
>         }
>     },
>     data() {
>         return {
>             result: ""
>         }
>     }
> }
> </script>
> ```
>
> * 新版本：
>
>   ```vue
>   被引用者发送数据
>   <template>
>       <h3>子组件</h3>
>       <fieldset>
>           <legend>给引用我的人发送数据</legend>
>           <p>@内置事件="方法名(参数)"，没有参数就可以简写为@内置事件="方法名"</p>
>           <h4><button type="button" @click="send">发放数据给引用我的人</button></h4>
>           <h4><input v-model="msg" /></h4>
>       </fieldset>
>   </template>
>             
>   <script setup>
>       import {ref} from 'vue';
>       const msg=ref();
>                 
>       //defineEmits()返回一个触发器 用于触发自定义事件
>       const emit=defineEmits(['childsend'])
>       //注意定义函数时也不要少了const
>       const send=()=>{
>      		//触发器函数（参数1：自定义事件，参数2：传递的数据，可以定义多个）
>           emit('childSend',msg.value);//触发器函数（参数1：自定义事件，参数2：传递的数据）
>           //如果不写msg.value，而是msg那按一次就变成了双向绑定的模式。
>       }
>   </script>
>   ```
>   
>   ```vue
>   引用者接收数据
>   <template>
>       <h3>父组件</h3>
>       <fieldset>
>           <legend>引用者接收数据</legend>
>           <h3>父组件接收到的数据：{{ result }}</h3>
>       </fieldset>
>       <!-- 3.显示子组件 -->
>       <Two @childSend="getChildData" />
>   </template>
>             
>   <script setup>
>       import { defineComponent,ref } from 'vue';
>       //1.引入组件
>       import Two from './Child.vue'
>       //2.挂载组件  vue3.2需要使用defineComponent()来挂载
>       defineComponent({
>           components: {
>               Two
>           }
>       })
>   	          
>       const result = ref();
>       //注意定义函数时也不要少了const
>       //方法的参数就是子组件传递的数据（参数名可以随便写，可以写多个参数）
>       const getChildData = (res) => {
>           result.value = res;
>       }
>   </script>
>   ```
>

> ==注: 上面几种传递方式前提组件具有父子关系，如果没有父子关系借助于路由来传递数据。==

### ==7.组件生命周期==
> 组件在被创建或者使用时都会经过不同的阶段，比如:设置数据监听，实例挂载到组件中… 每经过一个阶段 都会运行一些生命周期函数，用于给用户实现不同阶段来完成不同的业务逻辑
> __生命周期一共有八个阶段__: 每触发一个阶段都会自动运行一个生命周期的函数，这些生命周期函数，也叫钩子`函数`(注意它们是函数）：
>
> > 钩子函数（Hook Function），是一种特殊类型的函数，它允许开发者在某个特定点“挂接”或“注入”自己的代码，以便在程序执行到该点时能够执行一些额外的操作或改变程序的行为。就是达到某个状态或触发某个条件后会自动运行的函数。
>
> | 状态（钩子函数） | 阶段周期                       |
> | ---------------- | ------------------------------ |
> | beforeCreate     | 创建前                         |
> | created          | 创建后                         |
> | beforeMount      | 载入前 渲染前                  |
> | mounted          | 挂载完成 渲染后                |
> | beforUpdate      | 更新前，就是页面数据发生了变化 |
> | updated          | 更新后，页面数据发生了变化     |
> | beforeDestroy    | 销毁前                         |
> | destroyed        | 销毁后                         |
>
> ![image-20240822152533739](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\组件的生命周期.png)

#### 7.1 实例

> * 老版写法(每个钩子函数只能放一个)
>
>   ```vue
>   <template>
>       <h3>msg:{{ msg }}</h3>
>       <!--注意双引号中要用单引号-->
>       <button @click="msg='数据2'">改变数据</button>
>   </template>
>             
>   <!--老版钩子函数-->
>   <!--老版钩子函数 和 methods 是同级的不能放在methods里面，会失效-->
>   <script>
>       export default{
>           data() {
>               return {
>                   msg:"haha"
>               }
>           },
>           //普通函数的位置
>           methods:{
>             
>           },
>           //钩子函数：随着生命周期的变化自动触发
>           //页面刚刚加载 会根据生命周期阶段，执行下面4个函数
>           beforeCreate: function(){
>               console.log("组件创建前");
>           },
>           created(){
>               console.log("组件创建后");
>           },
>           beforeMount:()=>{
>               console.log("组件挂载前");
>           },
>           mounted:()=>{ //注意首字母是小写
>               console.log("挂载后，页面已经渲染完成，可以在前端看到");
>               //最常用的 经常需要在这里加载后端数据
>           },
>           //当任意数据发生改变 会执行下面的两个更新函数
>           beforeUpdate:()=>{
>               console.log("更新前");
>           },
>           updated:()=>{
>               console.log("更新后");
>           },
>           //当组件销毁 会自动执行下面两个函数 看不到效果的
>           beforeDestroy:()=>{
>               console.log("组件卸载前");
>           },
>           destroyed:()=>{
>               console.log("组件卸载前");
>           }
>       }
>   </script>
>   ```
>
> 
>
> * 新版写法
>
>   > 新版钩子函数:
>   > 和旧版相比，新版vue方式beforeCreate和created已经被调用了，所以这两个函数再使用是可以忽略不计的需要导入对应函数名`(on钩子函数)`通过import而且每种钩子函数可以编写多个
>
>   ```vue
>   <template>
>       <h3>msg:{{ msg }}</h3>
>       <!--注意双引号中要用单引号-->
>       <button @click="msg='数据2'">改变数据</button>
>   </template>
>   <!--
>       新版钩子函数:
>       和旧版相比，新版vue方式beforeCreate和created已经被调用了，而且beforeDestroy和destroyed已经被删除了，所以这四个函数再使用是可以忽略不计的。新版的构造函数需要导入对应函数名(on钩子函数)通过import 而且每种钩子函数可以编写多个
>   -->
>   <script setup>
>       import {onMounted,onUpdated,ref} from "vue"
>       // onMounted(匿名函数)
>       const msg=ref("数据1");
>       onMounted(function(){
>           console.log("我挂载了",msg.value);
>       })
>       onMounted(function(){
>           console.log("我又挂载了",msg.value);
>           msg.value="数据1"
>       })
>       onMounted(function(){
>           console.log("我再次挂载了",msg.value);
>       })
>                 
>       onBeforeMount(function(){
>           console.log("被挂载中");
>       });
>       onMounted(function(){
>           console.log("挂载完成了");
>       });
>             
>       onBeforeUpdate(function(){
>           console.log("被更新中");
>       });
>       onUpdated(function(){
>           console.log("更新完成了");
>       });
>   </script>
>   ```

### 8.vue引入第三方
> 第三方不属于vue,是其他人写好可以基于vue来是实现很多常见功能，不需要我们再单独编写了，而我们只需要学习如何引入这些成熟第三方即可
> 比如: 
>
> * ElementPlus (前端后台页面,面向pc端的)
> * Vant(前端前台页面,面向移动端)
> * swiper(免费触摸滑动 轮播组件(实现轮播图) 面向pc端、移动端、平板端都可以)
> * ...

#### 8.1 swiper组件

> 官网：swiper.com.cn (进不去就用：https://www.swiper.com.cn/index.html)
> 查看在Vue中使用swiper
>
> * vue项目中安装swiper（==注意是在要使用的项目的目录中安装==）
>
>   ```shell
>   在命令行中输入下面的语句
>   -- 注意是在要使用的项目的目录中安装
>   -- 安装最新版的swiper
>   cnpm install --save swiper
>   或简写install为i
>   cnpm i swiper
>   
>   -- 可以指定版本安装
>   cnpm install --save swiper@10.2.0
>   ```
>
>   ![image-20240822163216581](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\vue项目中安装swiper.png)
>
> * 在组件中导入swiper，类似于之前学习的组件引入`(Swiper,SwiperSlide)`
>
>   ```vue
>   <!-- 如果使用vue3.2 setup语法糖 可以省略挂载组件 -->
>   <script setup>
>       import { defineComponent } from 'vue';
>       //1.引入组件Swiper,SwiperSlid来自'swiper/vue'
>       import { Swiper,SwiperSlide } from 'swiper/vue';
>       //1.1引入swiper的css组件（必须导入不然是上下排成一列）
>       import 'swiper/css'; 
>   
>       //2.挂载组件(3.2 setup可以省略)
>       defineComponent({
>           components:{
>               Swiper,SwiperSlide
>           }
>       })
>   </script>
>   
>   ```
>   
> * 4.导入成功后使用Swiper标签导入轮播图组件，SwiperSlide子标签来添加图片
>
>   ```vue
>   <template>
>       <!-- 4.导入成功后使用Swiper标签导入轮播图组件，SwiperSlide子标签来添加图片 -->
>       <div >
>           <!-- 属性名前加:表示要绑定下面的变量
>               属性介绍： 
>               modules：类似于v-model 做双向绑定 只不过它是绑定很多个值 需要在下面定义好数组变量
>               navigation:使用上下一页
>               pagination:开启页面指示器
>               autoplay:自动播放 {delay:1000,pauseOnMouseEnter:'false'} 
>   					 delay控制轮播时间, pauseOnMouseEnter鼠标悬停
>             -->
>           <Swiper :modules="modules" navigation :pagination="{ clickable:true}" :autoplay="{delay:1000,pauseOnMouseEnter:'false'}">
>               <SwiperSlide>
>                   <img width="700px" height="400px" src="../assets/1.png">
>               </SwiperSlide>
>               <SwiperSlide>
>                   <img width="700px" height="400px" src="../assets/3.png">
>               </SwiperSlide>
>               <SwiperSlide>
>                   <img width="700px" height="400px" src="../assets/4.png">
>               </SwiperSlide>
>               <SwiperSlide>
>                   <img width="700px" height="400px" src="../assets/6.png">
>               </SwiperSlide>
>               <SwiperSlide>
>                   <img width="700px" height="400px" src="../assets/8.png">
>               </SwiperSlide>
>           </Swiper>
>       </div>
>   </template>
>   <!-- 如果使用vue3.2 setup语法糖 可以省略挂载组件 -->
>   <script setup>
>       import { defineComponent } from 'vue';
>       //1.引入组件Swiper,SwiperSlid来自'swiper/vue'
>       import { Swiper,SwiperSlide } from 'swiper/vue';
>       //1.1引入swiper的css组件
>       import 'swiper/css';
>       //1.2 导入其他组件
>       import {Autoplay,Navigation,Pagination} from 'swiper/modules';
>       //1.3 给1.2中导入的其他组件添加css样式
>       import 'swiper/css/navigation';
>       import 'swiper/css/pagination';
>             
>       // 加入其他功能到数组变量中，给上面做双向绑定：
>       const modules=[Autoplay,Navigation,Pagination];
>             
>       //2.挂载组件(3.2 setup可以省略)
>       defineComponent({
>           components:{
>               Swiper,SwiperSlide
>           }
>       })
>   </script>
>             
>   <style scoped>
>       /* 居中 */
>       div{
>           text-align: center;
>       }
>   </style>
>   ```

### 9. Vue发送异步请求

> * 原生js实现的，步骤比较繁琐 不推荐
> * jQuery封装好的方法 $.post() $.get() s.ajax()，但是需要手动控制请求和响应的数据格式 不推荐。
> * 通过axios发送异步请求 底层就是对jQuery的ajax进一步的封装，非常适合
>   前端分离的项目 它默认的传递数据格式就是json   推荐使用

#### 9.1 axios组件的安装

> * 安装：==(在需要的项目中安装要先进入该项目的目录)==
>
>   ```shell
>   cnpm install --save axios
>   ```
>
>   ![image-20240823100858039](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装axios.png)
>
>   ![image-20240823101108820](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\查看项目已经导入的插件.png)
>
> * 引入axios
>
> * 使用axios

#### 9.2 axios组件的使用

##### a. 引入axios

> * 通过`import axios from'axios' `导入
>
>   ```java
>   缺点：在大型项目，每个组件都需要引入，比较繁琐，也容易忘了导入。
>   ```
>
> * 全局导入：在vue项目入口(main.js) 添加一段配置 这样以后每个组件都无需导入axios
>
>   ```js
>   // 在main.js(vue项目入口)中进行全局配置
>   import { createApp } from 'vue'
>   import App from './App.vue'
>   import './registerServiceWorker'
>     
>   // 1. 导入axios组件
>   import axios from 'axios'
>   // 2.创建App组件：
>   const app = createApp(App)
>   // 3.在创建App组件后，挂载App组件前，进行全局引入
>   //app.config.globalProperties.任意别名=要导入的组件
>   //这样定义好了后，其他组件就可以通过这个 任意别名 直接使用axios
>   app.config.globalProperties.myAxios=axios
>   // 4.挂载App组件
>   app.mount('#app')
>   ```

##### b. 局部导入使用axios

> 就是创建一个发送异步请求的函数，在里面调用axios.get().then() 或 axios.post().then()来发送异步请求。

> ```vue
> <template>  
>   <div>  
>     <h1>Axios GET 请求示例</h1>  
>     <button @click="fetchData">获取数据</button>  
>     <div v-if="loading">加载中...</div>  
>     <div v-if="error">{{ error }}</div>  
>     <div v-if="data">  
>       <h2>获取到的数据:</h2>  
>       <pre>{{ data }}</pre>  
>     </div>  
>   </div>  
> </template>  
>   
> <script>  
> import axios from 'axios';  
>   
> export default {  
>   data() {  
>     return {  
>       data: null,  
>       loading: false,  
>       error: null,  
>     };  
>   },  
>   methods: {  
>     // 创建一个发送异步请求的函数
>     fetchData() {  
>       this.loading = true;  
>       this.error = null;  
>         
>       // 使用 axios 发起 GET 请求  
>       axios.get('https://api.example.com/data').then(response => {  
>           // 处理成功响应  
>           this.data = response.data;  
>         }).catch(error => {  
>           // 处理错误响应  
>           this.error = '请求失败: ' + error.message;  
>         }).finally(() => {  
>           // 无论成功还是失败，都会执行这里  
>           this.loading = false;  
>         });  
>     },  
>   },  
>   // 你也可以在 created 钩子中自动发起请求  
>   // created() {  
>   //   this.fetchData();  
>   // },  
> };  
> </script>  
> ```
>

##### c. 全局导入使用axios

> * 老版本：直接在script中通过this直接使用
>
>   ```vue
>   <!-- 老版本 -->
>   <script>
>       export default{
>           methods:{
>               queryById(){
>                  this.myAxios.post('http://localhost:80/sel?id='+this.id).then(res=>{
>                       console.log(res.data);
>                  })
>               }
>           }
>       }
>   </script>
>   ```
>
> * 新版本 setup语法糖：
>
>   ```vue
>   先通过getCurrentInstance()获取当前对象this，用{proxy}来接收，在后面通过proxy.myAxios.post来发送异步请求：
>   <script setup>
>       import { getCurrentInstance,ref } from 'vue';
>       //类似于老版本的this获取当前的实例
>       //{proxy}是固定返回的 要求不能改名字 需要添加括号
>       const {proxy}=getCurrentInstance() //current instance 当前实例，就是this.
>       const user=ref({})
>       const msg=ref()
>       const add= ()=>{
>           // post()函数参数1是地址，参数2是数据（会自动转化为json数据）
>           proxy.myAxios.post('http://localhost:80/add',user.value).then(res=>{
>               msg.value = res.data.msg
>           })
>       }
>   </script>
>   ```

#### ==9.3 封装axios对象 为 请求处理对象==

##### a. 为什么要进行封装？

> 虽然使用了单次导入axios和全局导入axios，但是两种方式各有优缺点，全局导入缺点是：没有对请求的安全做限制 而且 发送请求的时候也会有很多相同的部分，如：
>
> ```java
> http://localhost:9999/add
> http://localhost:9999/se1
> http://localhost:9999 就属于相同的部分
> ```
>
> 提高安全性(封装时能添加拦截器)和减少代码冗余。

##### b. 封装axios对象

> 在Vue项目中创建一个util目录，在里面创建一个request.js (或者http.js) 看公司规范。
> 将axios对象和请求进行封装，提高安全性。
>
> ```js
> //这个文件的目的是将axios对象进行封装 提高安全性(可以在里面添加拦截器)
> // 1.导入axios
> import axios from 'axios'
> 
> // 2.创建错误对象映射(参数status:状态码，参数info:错误信息)
> //const errorHandler=(status, info)=>{ //js语法不需要带上参数类型
> const errorHandler=(status:Number, info:String)=>{ //ts语法需要带上参数类型
> //使用switch进行等值判断
> switch(status) {
>   case 400: console.log('客户端参数有误');break;
>   case 404: console.log('地址错误');break;
>   case 500: console.log(info);break;
>   default : console.log(info);break;
> }
> }
> 
> // 3.通过axios创建请求实例
> const instance = axios.create({
> timeout:5000 //控制请求超时数据，单位ms
> })
> 
> // 4.通过请求实例 配置 请求拦截器
> //instance.interceptors.request.use(成功函数，失败函数)
> instance.interceptors.request.use(
> config=>{
>   if(config.method == "post") {
>       //处理所有的post请求 要看公司需求
>   } else if(config.method == "get") {
>       //处理所有get请求
>   }
>   // ... 可以写很多个请求处理
>   return config;//一定要返回，不然白写
> },
> error=>{
>   //返回错误信息
>   return Promise.reject(error);
> }
> )
> 
> // 5.通过请求实例 配置 响应拦截器
> instance.interceptors.response.use(
> response=>{
>   //根据状态码是否是200来控制返回的信息
>   return response.status==200? Promise.resolve(response):Promise.reject(response)
> },
> error=>{
>   //定义响应变量，因为常量不能改
>   const {response} = error;
>   //调用上面的错误映射传入状态码和错误信息
>   errorHandler(response.status,response.info);
> }
> )
> // 6.导出请求实例
> export default instance;
> ```
>

##### c. 封装axios对象的作用

> 上面的代码是在封装一个axios实例，并为其添加了请求和响应拦截器。这样做的目的主要是为了更好地处理HTTP请求和响应，以及统一错误处理逻辑。
> 下面是详细解释：
>
> 1. **导入axios**：
>    首先，代码从`axios`库中导入了axios对象。axios是一个基于Promise的HTTP客户端，用于浏览器和node.js。
>
> 2. **创建错误处理函数**：
>    `errorHandler`函数是一个错误处理函数，它根据HTTP状态码来输出不同的错误信息。这有助于开发者在调试时快速定位问题。
>
> 3. **创建axios实例**：
>    使用`axios.create`方法创建了一个axios实例，并设置了超时时间（5000毫秒）。这个实例可以用于发送HTTP请求，并且它的配置（如超时时间）是独立的，不会影响全局的axios配置。
>
> 4. **配置请求拦截器**：
>    请求拦截器是在请求被发送到服务器之前执行的函数。在这个例子中，拦截器根据请求方法（GET或POST）来执行不同的逻辑。虽然在这个代码片段中没有具体实现这些逻辑，但你可以在这里添加任何你需要在请求发送之前执行的代码，比如添加认证token、修改请求头等。
>
> 5. **配置响应拦截器**：
>    响应拦截器是在服务器响应被处理之前执行的函数。在这个例子中，拦截器首先检查响应的状态码是否为200。如果是，则返回响应数据（`response.data`），否则将响应视为错误并拒绝Promise。在错误处理部分，如果服务器返回了状态码和响应体，则调用`errorHandler`函数来输出错误信息。如果请求没有响应（比如网络错误），则输出“网络错误”。
>
> 6. **导出axios实例**：
>    最后，将配置好的axios实例导出，以便在其他文件中使用。这样，你就可以在其他文件中导入这个实例，并使用它来发送HTTP请求，同时享受统一的错误处理和配置。
>
> 总的来说，封装axios实例并添加拦截器的好处包括：
>
> - **统一配置**：你可以在一个地方配置axios实例，并在整个应用中重用它。
> - **统一错误处理**：你可以在一个地方处理所有HTTP请求的错误，而不需要在每个请求中都写错误处理代码。
> - **增强可维护性**：通过封装和抽象，你的代码变得更加简洁和可维护。
> - **添加自定义逻辑**：你可以在拦截器中添加任何你需要的自定义逻辑，比如认证、日志记录等。

##### d. 封装公共地址对象

> 创建一个目录(api目录)（一般存放的是发送网络请求，文件可能会有很多），在目录中创建一个`base.js`(可以处理通用地址) 用来封装公共地址对象。（就是所有请求重复的前缀部分）
>
> ```js
> //创建公共地址，比如：http://localhost:9999
> //这样其他请求 公共部分就无需编写了
> const base={
>     baseUrl:"http://localhost:80" //写项目地址
> }
> 
> //导出(这样别人就能通过import进行导入了，注意如果导出的名字带了{}，要求导入时也要带{})
> export default base;
> ```

##### e. 将前面两个对象封装为请求处理对象

> * index.js : 不同的公司，名称可能不同，但目的是一样的（调用之前封装好的请求 和 定义好的地址）
>
>   ```js
>   //引入封装好的请求(当前文件是js 导入的也是js 所以后者的后缀.js是可以省略的)
>   import myRequest from '../util/request';
>   //引入封装好的公共地址
>   import myBase from 'base';
>     
>   // 创建公共api接口， 编写好了 以后发送不同模块网络请求
>   // 都是引入这个api接口 来实现的 公司业务不同 这个也是经常
>   const api={
>       //比如:post请求封装在一起
>       postReq(url,data){
>           return axios.post(base.baseUrl+url, data)
>       },
>       //比如:get请求封装在一起
>       getReq(url,data){
>           return axios.get(base.baseUrl+url, data)
>       }
>   }
>   export default api;
>   ```

##### f. 使用请求处理对象发送请求

> ```vue
> <script>
> import api from  '../api/index' //局部导入封装好的请求处理对象
> onMounted(()=>{
>     //由于没有参数传递 {} 来占个位置
>     api.postReq('/queryAll',{}).then(res=>{
>         console.log(res.data)
>         users.value=res.data.data
>     })
> })
> </script>
> ```
>
> 使用封装好的请求处理对象发送异步请求来进行前后端分离项目的交互。

### 10.前端跨域

> 跨域问题：前端是一个项目，后端也是一个项目，不同项目之间想去访问必须写完整的url(协议://ip地址:端口号/项目），这三者如果有一个不同都会产生跨域问题。
> 但是前后端分离后url就是不一样的，所以一定会产生跨域问题。

> ==在Vue项目的配置文件vue.config.js， 配置一个proxy代理对象，然后重启服务器。==
>
> ```js
> const { defineConfig } = require('@vue/cli-service')
> module.exports = defineConfig({
>   //将依赖进行转义，主要是增加代码在不同语言的兼容性。
>   transpileDependencies: true,
>   //生产服务器
>   devServer:{
>     //加一个proxy对象表示跨域代理对象,在里面配置跨域
>     proxy:{
>       //可以配置很多组跨域，每组跨域都是一个对象
>       //'/跨域请求':{配置好跨域}
>       //使用http://localhost:9999/任意写/show来启动跨域，
> 	  //就是在请求的前面加一个跨域请求,如下面的/api
>       '/api':{
>         //配置后端的地址(即要跨域的目标地址)，proxy会转发到这里去
>         target:'http://localhost:80',
>         //路径重写(把跨域请求地址，重写为真正的请求地址)目的是去除请求地址中的api
>         //让发给后端的请求不包括/api
>         pathRewrite:{'^/api':''},
>         //代理websocket 可以不写
>         ws:true,
>         //开启跨域
>         changeOrigin:true
>       }
>     }
>   }
> })
> // 更改该配置文件，必须重启服务器才能生效。
> 
> ```
>
> 前端跨域一般可以通过前端vue项目的配置文件vue.config.js,添加一段配置 添加一个proxy代理对象 配置好后端地址 代理对象帮我们转发到目标地址。
>
> ```
>配置前:http://localhost:9999/show  ==>  访问后端查询 但是会出现跨域问题
> 配置好之后: /api/show ==> http://localhost:9999/api/show
> 	再进行路径重写去除多余/api ==> http://localhost:9999/show
> ```
> 
> 如果请求封装了只需要修改base.js 其他内容都不需要修改。
>
> ```js
>//创建公共地址的比如:http://localhost:9999
> //这样其他请求 公共部分无需编写了
> const base={
> 	baseUrl:"/api"//等价于http://localhost:9999/api
> }
> export default base;
> ```
> 
> ==注：修改了vue.config.js一定要重启服务器，否则不生效，就它最特殊，其他都不需要重启。==

### ==11.Vue路由组件==

> 路由(vue-router组件)：主要是用来管理vue组件中的关系，可以实现路径跳转，可以实现组件之间的数据传递，这样可以让vue.js构建单页的应用更加轻松。

#### 11.1 vue路由安装

> 注：不要通过cnpm来安装，虽然语法通过，但是路由都会失效。(因为国内的少了几个组件)，推荐使用vue ui图形界面方式安装依赖，或 创建vue项目时 选择路由插件。
>
> * 使用vue ui图形界面安装 `vue-router组件`（前期联系时使用）。
>
>   在终端用vue ui打开图形化界面。
>   <img src="D:\AppData\Typora\typora-user-images\image-20240826120918245.png" alt="image-20240826120918245" style="zoom:33%;" />
>   <img src="D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\vue路由安装2.png" alt="image-20240826121303364" style="zoom: 25%;" />
>
>   <img src="D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\vue路由安装3.png" alt="image-20240826120550190" style="zoom: 33%;" />
>
>   <img src="D:\AppData\Typora\typora-user-images\image-20240826122446613.png" alt="image-20240826122446613" style="zoom:50%;" />
>
> * 通过创建vue项目 选择route插件安装（后期项目推荐使用）

#### 11.2 路由的配置方式

> 1. 配置路由（在router/index.js中) 
>    (引入创建路由需要的组件  创建路由规则  创建路由对象  导出路由对象)
> 2. 启用路由 （在项目入口 - main.js中）
>    (引入路由，启用路由）
> 3. 使用路由
>    (路由显示入口， 通过链接标签来使用路由引入其他组件）

> * 创建一个目录router，用来保存路由配置文件(index.js)通过路由管理所有vue组件
>
> * 再创建一个目录views，用来保存所有的被路由管理的vue组件，其他vue组件依然可以保存中原来的component目录下，但大多数组件都被路由管理。
>
>   * views: 创建组件的位置 比如: Home.vue\User.vue
>
>   * router: 创建一个index.js 编写路由配置(引入创建路由需要的组件  创建路由规则  创建路由对象  导出路由对象)
>
>     ```js
>     //1.导入创建路由需要的组件
>     import { createRouter, createWebHashHistory} from 'vue-router'
>     import Home from '../views/Home.vue'
>     
>     //2.创建路由规则对象(数组 因为vue组件有很多个)
>     //注意创建路由规则时，名称不能改，必须是routes (routers是错误的)
>     const routes = [
>         {
>             //注意关键字是全小写，错一个都不行
>             path:'/', //访问的路径
>             component:Home //对应的组件
>         },
>         {
>             // 这种写法 是属于异步加载 上面的方式属于同步加载
>             // 般来说首页适合 同步加载 其他组件 都是异步加载
>             path:'/user', //访问的路径;
>             component:()=>import("../views/User.vue") 
>         },
>         {
>             path:'/admin',
>             component:()=>import("../views/admin/Index.vue"),
>             redirect:'/admin/home',// 重定向配置，当访问/admin时，默认重定向到/admin/home 
>             children:[ //二级路由
>               {
>                 path:'home', //二级路由不能加"/"
>                 component:()=>import("../views/admin/Home.vue")
>               },
>               {
>                 path:'myuser',
>                 component:()=>import("../views/admin/MyUser.vue")
>               }
>             ]
>       }
>     ]
>     
>     // 3.创建路由组件
>     const router=createRouter(
>         {
>             //createWebHashHistroy比较常用 表示选择哈希路由模式
>             // 是切换页面（无需加载） 而不是跳转页面了
>             // 如果要跳转页面就需要配合后端配置做重定向，否则会404
>             history: createWebHashHistory(),      // 选择路由方式
>             routes                                // 选择路由规则：上面配置的路由规则
>         }
>     )
>     
>     //4.导出vue组件对象
>     export default router;
>     ```
>   
>   * main.js: 在vue项目的入口 启用vue路由组件
>   
>     ```js
>     import { createApp } from 'vue'
>     import App from './App.vue'
>     import './registerServiceWorker'
>     import axios from 'axios'
>     //上面是其他配置可以不用看
>     
>     //1.引入路由 如果格式是js 可以省略
>     //如果包里面就一个文件 则都可以省略
>     import router from './router'
>     
>     //2.使用路由的时机：在vue项目创建之后，被挂载之前。位置一定不能换！
>     createApp(App).use(router).mount('#app')
>     ```
>

> `/#/` 和 `/` 在 Web 开发中，尤其是在单页面应用（SPA）和 URL 路由管理中，具有不同的含义和用途。以下是它们之间的主要区别：
>
> ### 1. 含义
>
> * `/`：在 URL 中，`/` 通常用作路径分隔符，表示根目录或目录之间的层级关系。在 Web 应用中，它用于指定资源的相对路径或绝对路径。例如，在 URL `http://www.example.com/about` 中，`/` 分隔了域名和路径部分，而路径部分 `/about` 表示网站根目录下的 `about` 目录或资源。
> * `/#/`：这种形式的 URL 路径通常与前端路由（如 Vue Router、React Router 等）中的哈希模式（hash mode）相关。在哈希模式下，URL 的 `#` 符号及其后面的部分不会被发送到服务器，而是由前端路由库捕获并用于控制页面内容的显示。`/#/` 中的 `/` 仍然表示路径分隔符，但整个 `/#/` 部分及其后的内容被视为前端路由的路径，用于在客户端进行页面内容的切换，而无需重新加载整个页面。
>
> ### 2. 用途
>
> * `/`：主要用于定义 Web 应用的资源路径，包括静态文件（如图片、CSS、JavaScript 文件）和动态页面（如通过服务器端渲染或前端路由控制的页面）。它是 Web 应用中资源定位的基础。
> * `/#/`：主要用于前端路由的哈希模式中，以实现单页面应用的页面跳转和状态管理。通过改变 URL 中的哈希部分，前端路由库可以捕获这些变化并相应地更新页面内容，而无需重新加载页面。这种方式有助于提升用户体验，减少服务器请求次数，并使得前端路由更加灵活和可控。
>
> ### 3. 注意事项
>
> * 在使用前端路由时，需要根据项目的具体需求和目标来选择使用哈希模式还是历史模式（history mode）。哈希模式具有更好的兼容性，但 URL 中包含 `#` 符号可能会影响美观和 SEO。历史模式则更加美观和符合现代 Web 应用的标准，但需要服务器端的支持来确保刷新页面时能够正确加载资源。
> * 在设计 URL 结构时，应遵循一定的命名规则和最佳实践，以确保 URL 的清晰、简洁和易于理解。这有助于提升用户体验和 SEO 效果。
>
> 综上所述，`/#/` 和 `/` 在 Web 开发中具有不同的含义和用途，分别用于前端路由的哈希模式和定义 Web 应用的资源路径。在实际应用中，应根据项目的具体需求和目标来选择合适的模式和路径结构。

> 哈希模式（Hash Mode）和历史模式（History Mode）是Vue Router中提供的两种路由模式，它们在URL的显示、服务器的配置需求以及SEO（搜索引擎优化）的友好性等方面存在显著差异。
>
> ### 1. 哈希模式（Hash Mode）
>
> **特点**：
>
> * **URL格式**：使用`#`符号分隔路径，例如`http://example.com/#/home`。哈希值之后的部分由客户端解析。
> * **服务器配置**：无需服务器配置，因为哈希值部分不会被发送到服务器。
> * **浏览器支持**：兼容所有现代浏览器以及一些老旧浏览器。
> * **SEO友好性**：URL中的`#`符号对搜索引擎不友好，影响SEO。搜索引擎难以索引`#`后面的内容。
> * **页面加载**：浏览器在哈希值发生变化时不会重新加载页面，只会触发`hashchange`事件，客户端脚本（如Vue Router）可以捕捉并处理这个事件来进行导航。
>
> **适用场景**：
>
> * 适用于不需要SEO支持的项目。
> * 适用于不便或无法配置服务器的项目。
>
> ### 2. 历史模式（History Mode）
>
> **特点**：
>
> * **URL格式**：使用正常的路径格式，没有`#`符号，例如`http://example.com/home`。
> * **服务器配置**：需要配置服务器来处理所有路由请求，返回应用的主页面。这是因为在History模式下，直接访问浏览器中的URL路径时，服务器需要返回前端应用的HTML文件，由前端路由来处理该URL。
> * **SEO友好性**：URL结构对搜索引擎友好，有助于SEO。搜索引擎可以正常索引内容。
> * **页面加载**：利用HTML5 History API（如`pushState`和`replaceState`）来管理历史记录，路径的变化会更新浏览器的地址栏，但不会重新加载页面，客户端脚本（如Vue Router）处理路径变化。
>
> **适用场景**：
>
> * 适用于需要更友好的URL和更好的SEO支持的项目。
> * 适用于项目的目标浏览器支持HTML5 History API，并且需要处理特定的服务器请求（如RESTful API）的情况。
>
> ### 总结
>
> 哈希模式和历史模式各有优劣，选择哪种模式取决于项目的具体需求和目标。如果项目对SEO要求不高，或者无法配置服务器，可以选择哈希模式。如果项目需要更美观的URL、更好的SEO支持，并且能够处理服务器配置，那么历史模式将是更好的选择。



#### 11.3 使用路由

> 在vue项目的首页App.vue(其他页面也可以添加)添加几个标签：
>
> ```vue
> <!--表示路由显示的入口(单标签)，下面的两种写法都行，不用‘-’就要用驼峰法命名-->
> <router-view/> 或 <routerView/>
> 
> <!--连接标签：等价于之前的a标签 -->
> <router-link to="配置好的路由规则中的地址，对应了一个组件">链接名称</router-link>
> <router-link to="配置好的路由规则中的地址，对应了一个组件">链接名称</router-link>
> ```

> 路由配置和使用方式的总结：
>
> 1. 配置路由（在router/index.js中) 
>     (引入创建路由需要的组件  创建路由规则  创建路由对象  导出路由对象)
>     (注意创建路由规则时，名称不能改,必须是routes (routers是错误的)
> 2. 启用路由 （在项目入口 - main.js中）
> (引入路由，启用路由）
> 3. 使用路由
> (路由显示入口， 通过链接标签来使用路由引入其他组件(切换式引入，每次只引入一个)）

#### 11.4 路由传递参数

##### a. 给被引用的组件传递参数

> 类似于后端，根据id传递后端，会在同一个页面显示不同的内容，而vue路由肯定也需要这种需求
>
> * 路由配置地址的时候添加":要传递的参数名"表示 要传递参数 参数名可以任意编写
>   （使用路由引用其他组件的组件按顺序传值，被引用的组件根据参数名取值）
>
>   ```vue
>   {
>       //传递多个 /goodz/:id/:name
>       path:'/goodz/:id/:name
>       component:()=> import("../views/XX.vue")
>   }
>   ```
>
> * route-link标签地址跳转时传入指定的数据（要和配置的方式一样）
>
>   ```
>   <route-link to="/goodz/10/衣服"> 链接</route-link>
>   ```
>
> * 进入指定的组件后 获取id和name属性值
>
>   ```vue
>   <template>
>       {{ $route.params.id }}
>       {{ Sroute.params.name }}
>   </template>
>   <script setup>
>       import {useRoute} from 'vue-router
>       //在script标签中要通过useRoute()函数来获取
>       const id=useRoute().params.id
>       const name=useRoute().params.name
>   </script>
>   ```
>
> ![image-20240903080430977](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\使用路由传参.png)
>
> 被引用的组件在script中获取传递过来的参数：
> ![image-20240919073523690](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\被引用的组件在script中获取传递过来的参数.png)

##### b.	给引用我的组件传递参数

> ？

#### 11.5 二级路由
> ![image-20240919074438809](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\二级路由.png)  
>
> 如果项目中的功能特别复杂 设计很多组件 肯定会出现父级导航 和子级导航 每个导航 都有很多子级组件 对应 就要设计到二级路由或者多级路由。
>
> * 在路由配置文件的路由规则对象中添加属性children
>
> ```js
> //1.导入路由组件对象
> import { createRouter, createWebHashHistory} from 'vue-router'
> import Home from '../views/Home.vue'
> 
> //2.创建路由规则对象(数组 因为vue组件有很多个)
> const routes = [
>     {
>         path:'/', //访问的路径
>         component:Home //小写的c
>     },
>     {
>         // 这种写法 是属于异步加载 上面的方式属于同步加载
>         // 般来说首页适合 同步加载 其他组件 都是异步加载
>         //一级路由
>         path:'/article',
>         component:()=>import("../views/Article.vue"),
>         //二级路由:类似于一级路由的嵌套编写
>         //注意：二级路由开始 路径path不要加/
>         children:[
>             {
>                 path:'history',
>                 component:()=>import("../views/article/History.vue")
>             },
>             {
>                 path:'music',
>                 component:()=>import("../views/article/Music.vue")
>             },
>             {
>                 path:'sports',
>                 component:()=>import("../views/article/Sports.vue")
>             }
>         ]
>     }
> ]
> 
> // 3.创建路由组件
> const router=createRouter(
>     {
>         //createWebHashHistroy比较常用
>         // 需要配合后端配置做重定向，否则会404
>         history: createWebHashHistory(),
>         routes //路由规则
>     }
> )
> 
> //4.导出vue组件对象
> export default router;
> ```
>
> * 在一级路由组件页面中 通过<router-link to="/一级/二级" 来访问二级路由。必须加上一级路由才能访问二级，三级路由就必须加上一级和二级。
>   ![image-20240903095919051](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\使用多级路由.png)
>
>   ```vue
>   <template>
>       <h3>栏目一级路由</h3>
>       <div class="article">
>           <!-- 如果要访问多级路由， 就需要 to="/一级/二级/..." -->
>           <router-link to="/article/history">历史栏目</router-link>
>           <router-link to="/article/music">音乐栏目</router-link>
>           <router-link to="/article/sports">体育栏目</router-link>
>       </div>
>       <div>
>           <router-view></router-view>
>       </div>
>   </template>
>     
>   <script setup>
>       import {ref} from "vue"
>   </script>
>   ```
>
> * 注：如果时三级路由，继续无限套娃 理论上是没有上限的。但是绝大部分功能二级路由已经足够使用了。

> 创建项目时选中第四个组件，会帮我们自动在项目中创建router包和index.js文件和views包。在路由模式选择时选择n就是选择哈希模式的路由。

### 12.Vue使用Elementplus(做后台)

> Elementplus应用场景非常广泛，比如：pc端 移动端 微信小程序 通常用于实现他们后台管理界面 提供很多编写好的可视化组件 如果环境搭建好了只需要Elementplus官网代码复制过来即可生效，是基于Vue3.0以上的版本，如果是Vue2.0版本兼容的是ElementUI。

#### 12.1 安装Elementplus插件

> * 去官网https://element-plus.org/zh-CN/component/button.html，找到插件的安装方式
>   ![image-20240827105122130](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\Elementplus官网.png)
>   ![image-20240827105749487](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装步骤.png)
>
> ==注:不推荐使用命令安装 因为他会失效,原因ELementplus内部有一个utils包也引入了vue 和你安装的vue版本会有冲突==

> 推荐使用图形化界面导入
>
> * 打开vue图形化界面，`切换为要使用Elementplus插件的项目` ，选择依赖进行导入
>   ![image-20240827110328034](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\图形化界面安装elementplus依赖.png)
> * 在packge.json中检测依赖是否导入成功
>   ![image-20240827110622279](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\在packge.json中检查依赖是否导入成功.png)

#### 12.2 elementplus引入

> * 完整引入: 无论是否需要，不用在乎打包项目的大小，优点在于使用很方便。
>
>   ```js
>   //在main.js或main.ts中
>   import { createApp } from 'vue'
>   import App from './App.vue'
>   import './registerServiceWorker'
>   import router from './router'
>   
>   //1. 导入elementplus
>   import Elementplus from 'element-plus'
>   //1.1 导入elementplus的样式index.css
>   import 'element-plus/dist/index.css'
>   //2. 使用elementplus
>   // elementplus一定要在路由前使用，否则会失效。
>   createApp(App).use(Elementplus).use(router).mount('#app')
>   ```
>
> 
>
> * 按需引入: 应用场景比较广泛 会根据需要引入必要组件 比较节省空间 缺点再于需要做一个额外的配置。
>
>   * 安装两个必要的插件components 和 auto-import两个插件
>     bug：插件的版本需要回退到老版本，新版本不兼容(默认下的最新版本两个插件可能互不兼容，所以要网上去找到两个场景互相兼容的版本)
>
>     ```shell
>     可以直接使用命令来安装(--注意版本一定要对应)，在图形化界面找版本比较麻烦
>     cnpm install -D unplugin-vue-components@0.25.2 unplugin-auto-import@0.16.1
>     ```
>
>     下图的版本可能不互相兼容，可以使用上面命令中的两个版本。
>
>     ![image-20240827114844016](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\按需引入elementplus.png)
>
>   * 修改vue.config.js配置文件 查看官网去修改
>
>     ```js
>     const { defineConfig } = require('@vue/cli-service')
>     // webpack.config.js 按需引入elementplus 需要的两个必要的插件components 和 auto-import
>     const AutoImport = require('unplugin-auto-import/webpack')
>     const Components = require('unplugin-vue-components/webpack')
>     const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')
>
>     module.exports = defineConfig({
>       transpileDependencies: true,
>       //按需引入在这里添加
>       configureWebpack:{
>         plugins: [
>           AutoImport({
>             resolvers: [ElementPlusResolver()], //添加Elementplus
>           }),
>           Components({
>             resolvers: [ElementPlusResolver()],
>           }),
>         ],
>       }
>     })
>     ```
>
>   * main.js 还原，把之前全局引入在main.js中的修改都删除掉。
>
>   * 修改配置文件vue.config.js，一定要重启服务器。

#### 12.3 vue使用Elementplus

> 无论按需引入还是全局引入，使用方式基本都是一样的，只需要在elementplus组件官网找需要的组件，查看代码，复制到自己的vue组件中 刷新即可使用。

#### 12.4 vue注册ElementPlus图标

> 由于Elementplus自己无论按需导入还是全局导入，图标是不包含的，所以如果需要添加图标，要做一些额外的配置。（查看ElementPlus官网的图标栏目）
>
> * 安装element-plus/icons-vue插件
>
>   ```
>   通过vue ui来安装插件
>   查找element-plus/icons-vue依赖
>   在packge.json中确定是否成功导入
>   ```
>
> * main.ts 或者 main.js添加一段配置
>
>   ```js
>   import { createApp } from 'vue'
>   import App from './App.vue'
>   import './registerServiceWorker'
>   import router from './router'
>               
>   //引入所有的Elementplus图标
>   import * as ElementPlusIconsVue from '@element-plus/icons-vue'
>               
>   //通过app组件遍历所有的Elementplus图标 遍历一个注册(挂载)一个
>   const app = createApp(App)
>   for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
>     app.component(key, component) //挂载组件
>   }
>               
>   app.use(router).mount('#app')
>   ```

#### 12.5 设计程序的时候会调整大小会报错，但是不会影响程序的执行

> * 原因是Elementpuls中的组件都有自己的宽高，你调整大小势必会影响它里面组件的布局，所以会报错。
> * 解决方案：可以在main.ts 添加一个定时器，让其改变大小时有一点微小的时间间隔。
>
> ```js
> const de = (fn: any, delay: any) => {
>     let timer: any
>     return (...args: any) => {
>         if (timer) clearTimeout(timer)
>         timer = setTimeout(() => {
>             fn(...args)
>         }, delay)
>     }
> }
> const _ResizeObserver = window.ResizeObserver;
> window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
>     constructor(callback: any) {
>         callback = de(callback, 200);
>         super(callback);
>     }
> }
> ```

![image-20240920142407411](D:\AppData\Typora\typora-user-images\image-20240920142407411.png)

### 13.Vue使用Vant(做前台)

> Vant是一个轻量级的 可以定制化移动端的组件库 使用方式类似于Elementplus区别在于Vant 适用于实现移动端 小程序端 实现前台展示界面。
>
> 官网地址：https://vant-ui.github.io/vant/#/zh-CN

#### 13.1 安装Vant

> * 安装: 通过vue ui安装
>   ![image-20240829113447925](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装vant.png)
>   ![image-20240829113647599](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\成功安装vant.png)

#### 13.2按需引入Vant插件

> - 安装两个插件：（如果安装过就无需再次安装，在package.json中查看）
>
>   ```
>    cnpm i @vant/auto-import-resolver unplugin-vue-components -D
>    如果Elementplus组件安装过unplugin-vue-components 无需再次安装
>    cnpm i @vant/auto-import-resolver -D
>   ```
>
>   ![image-20240829114602469](file://D:/Desktop/gitee/java-learning/sc240601/%E5%89%8D%E7%AB%AF/Vue3/img/%E5%AE%89%E8%A3%85Vant%E5%BF%85%E8%A6%81%E7%9A%84%E6%8F%92%E4%BB%B6.png?lastModify=1726965309)
>
> - 修改vue的配置文件
>
>   ```
>    //如果在Elementplus组件导入过就不要再导入
>    const AutoImport = require('unplugin-auto-import/webpack');
>    const Components = require('unplugin-vue-components/webpack');
>    //在elementplus基础上Vant额外导入
>    const { VantResolver } = require('@vant/auto-import-resolver');
>    
>    module.exports = defineConfig({
>      transpileDependencies: true,
>      //生产服务器
>      devServer: {
>        //加一个proxy对象表示跨域代理对象,在里面配置跨域
>        proxy: {
>          //可以配置很多组跨域，每组跨域都是一个对象
>          //'/任意写':{配置好跨域}
>          //使用http://localhost:9999/任意写/show来启动跨域，就是在写请求的前面加一个跨域请求
>          '/api': {
>            //配置后端的地址(即要跨域的目标地址)，proxy会转发到这里去
>            target: 'http://localhost:80', //一定要和后端的端口号相同
>            //路径重写(把跨域请求地址，重写为真正的请求地址)目的是去除请求地址中的api
>            pathRewrite: { '^/api': '' },
>            //代理websocket 可以不写
>            ws: true,
>            //开启跨域
>            changeOrigin: true
>          }
>        }
>      },
>      //按需引入添加的这里是在ElementPlus基础上额外加了VantResolver()
>      configureWebpack: {
>        plugins: [
>          AutoImport({
>            resolvers: [ElementPlusResolver(),VantResolver()],
>          }),
>          Components({
>            resolvers: [ElementPlusResolver(),VantResolver()],
>          }),
>        ],
>      }
>    })
>   ```
>
> - 使用: 在vant官网复制粘贴代码

### 14.JWT

> JWT用于实现前后端分离项目的登录功能。

#### 14.1 什么是JWT

> JWT(JSON Web Token)，它是跨域身份认证的解决方案之一，通过json对象在前端和后端进行设计传递，主要用于实现用户的授权登录。比如：一旦用户登录成功，后端会返回一个token(令牌) 相当于后端给前端返回了授权码，之后前端发送给后端的每一个请求，都要在请求中携带这个token，后端在执行方法之前会进行拦截校验token，校验通过了，才能执行具体的业务逻辑。
> ![image-20240830094757926](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\JWT原理.png)

#### 14.2 JWT组成

> 一个JWT中的token就是一个字符串，主要包含三个部分：Header(头部信息)，PayLoad(载荷 存储用户信息)，signature(签名)，这三个部分会通过"."来链接，比如一个完整的JWT TOKEN是：头部.载荷.签名
> ![img](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\JWT的组成.png)
>
> * 头部header: 属于token第一部分 主要用户存放声明的加密算法
>
>   ```json
>   {
>   	typ:"JWT",  //声明令牌类型 JWT令牌 统一写JWT
>   	alg:"HS256" //声明签名使用的算法
>   }
>   ```
>
> * 载荷: JWT第二部分 也是一个json对象 主要可以保存一些默认字段和用户需要传递的内容 通过Base64 算法将其转换成字符串保存
>
>   ```js
>   //默认字段：
>   iss(issuer):签发人/发行人
>   sub(subject):主题
>   aud(audience):用户 给哪个用户发送的签证
>   exp(...): 过期时间，令牌不是永久有效
>   iat(...): 签发时间
>   ......
>   
>   //如何编写一个载荷：
>   { 
>   	//对应上面的默认字段
>       sub:"admin",
>       // 可以自定义字段，用来表示和存储用户信息
>       username:"admin",
>       name:"管理员",
>       sex:"男"
>   }
>   ```
>
> * 签名：是jwt的第三部分，如果想要做签名，实现需要指定一个secret(密码)，该secret是存储在服务端的，
>   要保证不被其他用户知道。这个需要通过 头部和载荷进行Base64加密后 通过.来进行链接 然后通过头部声明加密算法 进行加密 加盐 最后形成了一个签名

#### 14.4 SpringBoot后端+Vue3.2前端+JWT整合

>* 在后端的pom.xml中添加依赖
>
>  ```xml
>  <!--jwt依赖-->
>  <dependency>
>  	<groupId>io.jsonwebtoken</groupId>
>  	<artifactId>jjwt</artifactId>
>  	<version>0.9.0</version>
>  </dependency>
>  ```
>
>* 在后端提供两个方法(创建token  验证token)
>
>  ```java
>  package com.sc.sm.util;
>  
>  import io.jsonwebtoken.*;
>  import java.util.Date;
>  import java.util.UUID;
>  
>  public class JwtUtils {
>      private static long time = 1000 * 60 * 60 * 24;//表示有效期为24h
>      private static String signature = "admin";//定义签名
>  
>      public static String createToken() {//创建token的方法
>          //JwtBuilder用来构建jwt
>          JwtBuilder jwtBuilder = Jwts.builder();
>          String jwtToken = jwtBuilder
>                  //header(计算头部信息）
>                  .setHeaderParam("typ", "JWT")
>                  .setHeaderParam("alg", "HS256")
>                  //payload（计算载荷）
>                  //自定义用户信息
>                  .claim("username", "admin")
>                  .claim("role", "user")
>                  .setSubject("admin-test")
>                  //设置过期时间
>                  .setExpiration(new Date(System.currentTimeMillis() + time))
>                  .setId(UUID.randomUUID().toString())
>                  //signature（设置主题）
>                  .signWith(SignatureAlgorithm.HS256, signature)
>                  .compact();//把三部分拼接起来
>          return jwtToken;
>      }
>  
>      //校验token的方法,其实就是解析token，如果解析成功就是可以放行
>      public static boolean checkToken(String token) {
>          if (token == null) {
>              return false;
>          }
>          try {
>              //通过signature（签名）和token进行比较来判断
>              Jws<Claims> claimsJws =
>                      Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
>          } catch (Exception e) {
>              return false;
>          }
>          return true;
>      }
>  }
>  ```
>
>* 前端vue发送一个登录请求给后端...
>
>* 后端接收这个登录请求，验证登录成功和失败
>
>* 如果验证登录成功了，要使用创建token的方法，来帮助我们生成一个token，再给登录的用户信息赋值。（一起统一返回给前端）
>
>* 前端vue回调函数就可以获取这个用户信息（有token），这个时候前端就可以通过localStorage或者sessionStorage对其用户信息进行浏览器本地存储（为了方便其他vue组件获取使用的）。

#### 14.5 路由守卫
> 路由守卫是属于路由组件的一个重要功能，可以实现登录拦截功能，而且还可以 设置拦截规则。
>
> ```js
> //在路由管理文件中 /router/index.ts
> import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
> import Home from '../views/Home.vue'
> 
> const routes: Array<RouteRecordRaw> = [
>   // 后台管理路由：
>   {
>     path:'/admin',
>     component:()=>import("../views/admin/Index.vue"),
>     redirect:'/admin/home',//配置默认路由，默认进入home
>     children:[ //二级路由
>       {
>         path:'home', //二级路由不能加"/"
>         component:()=>import("../views/admin/Home.vue")
>       },
>       {
>         path:'myuser',
>         component:()=>import("../views/admin/MyUser.vue")
>       }
>     ]
>   },
>   //前台路由管理：
>   {
>     path:'/',
>     // 刚进入的app首页适合同步加载，所以没有使用()=>import
>     component: Home,
>     redirect:'/demo1', //配置默认路由，默认进入demo1
>     children:[
>       {
>         path:'demo1',
>         component:()=>import("../views/layout/Demo1.vue")
>       },
>       {
>         path:'demo2',
>         component:()=>import("../views/layout/Demo2.vue")
>       },
>       {
>         path:'demo3',
>         component:()=>import("../views/layout/Demo3.vue")
>       },
>       {
>         path:'demo4',
>         component:()=>import("../views/layout/Demo4.vue")
>       }
>     ]
>   },
>   {
>     path:'/login',
>     component:()=>import("../views/Login.vue")
>   },
>   {
>     path:'/reg',
>     component:()=>import("../views/Reg.vue")
>   },
> ]
> 
> const router = createRouter({
>   history: createWebHashHistory(),
>   routes
> })
> 
> //在创建路由变量后，导出路由变量前：控制哪些请求可以放行，哪些请求必须要登录
> //参数1：to表示要访问的地址 
> //参数2：from表示从哪个地址过来的
> //参数3：表示放行或强制访问login 如next('/login') 强制访问login
> router.beforeEach((to, from, next)=>{
>   //先定义一个用来配置放行地址的数组
>   const exclude=["/login", "/reg", "/Demo1", "/Demo3", "/Demo4"];
>   //参数1和放行地址数组进行匹配
>   //如果访问的地址被exclude包含，则可以放行
>   if(exclude.includes(to.path))
>     next()
>   else { //如果不是放行的请求，就需要判断是否登录过
>     const token = localStorage.getItem("token");
>     if(!token) { //如果没有登录的token则强制登录
>         return next('/login')
>     } else {
>       //如果有登录的token,则正常访问
>       next()
>     }
>   }
> })
> 
> export default router
> 
> ```

### 问题记录

> 错误1： script标签不加setup
>
> ![image-20240821171055276](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\错误1：script不加setup.png)
>
> <hr>
>
> 错误2: 父子组件传递参数时要注意的问题
>
> ![image-20240822141608781](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\组件传递时要注意的问题.png)
>
> <hr>
>
> 错误3：script标签中注意lambda表达式形式的函数不能用this
>
> ![image-20240822145416905](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\script标签注意lambda形式的函数中不能用this.png)
>
> <hr>
>
> 错误4：![image-20240822151221986](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\注意点.png)
>
> <hr>
>
> 错误5：路由的信息无法传递给前端
> ==注意创建路由对象时要全小写，写错一个字母都不行！==
> ![image-20240826162747926](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\错误5路由的信息无法传递给前端.png)
>
> <hr>
> 错误6：后端数据为什么前端无法读取？
> 要对应，别写错了字母。
>
> ![image-20240828172829063](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\image-20240828172829063.png)
>
> <hr>
>
> 错误7：ssm项目和sm项目用的分页插件是不一样的
> sm(Springboot+mybatis)项目：
>
> ```xml
>      <!--SpringBoot - mybatis专用分页插件-->
>      <dependency>
>          <groupId>com.github.pagehelper</groupId>
>          <artifactId>pagehelper-spring-boot-starter</artifactId>
>          <version>1.2.12</version>
>      </dependency>
> ```
>
> <hr>
>
> 错误8：跨域问题
> ![image-20240918215253850](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\image-20240918215253850.png)