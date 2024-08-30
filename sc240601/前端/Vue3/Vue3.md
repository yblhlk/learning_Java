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
> * 输入命令创建项目：vue create 项目名==（必须都小写，项目名必须小写）==
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
>   `const`用于声明一个只读的常量。一旦一个常量被赋值后，就不能再被重新赋值（但如果是对象或数组，则可以修改其内部属性或元素）。
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

> 用来实现template中的标签和script中的变量的绑定，在template中需要值的标签的前面加上bind:或:，=后面就可以使用script中的变量了。
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

### 5. VSCode快速生成vue3模板

> File(文件)->Preferences(首选项)->Configure Snippets(配置用户代码片段)->输入vue(或者vue.json)
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
> Vue是基于组件开发的,我们创建每一个*.vue文件 它是一种特殊的文件格式,再
> Vue中称这种文件叫做Vue组件(html 样式css 业务逻辑js)

#### ==6.1 加载组件步骤==

> * 引入组件
>
>   ```vue
>   <script>
>   	import 组件名(可以自定义) from "./components/XXXX.vue"
>   </script>
>   ```
>
> * 挂载组件
>
>   ```vue
>   <script>
>   	components:{
>           组件名，组件名2
>       }
>   </script>
>   
>   <script setup>
>       import {defineComponent, ref} from "vue"
>       //1.引入组件
>       import Two from './Child.vue'
>       //2.挂载组件  vue3.2 添加setup语法糖后 需要导出 需要使用defineComponent()来挂载
>       //define:定义 Component:组件
>       defineComponent({
>           components:{
>               Two
>           }
>       })
>   </script>
>   
>   <script>
>       //1.引入组件
>       import Two from "./Child.vue";
>       //2.在导出中挂载组件
>       export default{
>           components:{
>               Two
>           }
>       }
>   </script>
>   ```
>
> * 显示组件 （在template标签中使用）
>
>   ```vue
>   <组件名/>
>   ```
>
>   App.vue => One.vue => Two.vue

#### 6.2 组件的交互

> 组件和组件之间如果没有任何关系 就没有意思 他们之间是可以进行交互的，比如:a组件的需要 给交给b组件使用 通过prop来组件交互。

> ==注:下面几种传递方式前提组件具有父子关系，如果没有父子关系借助于路由来传递数据。==

##### a. 引用者传递，被引用者接收（通过prop来组件交互）

> * 老版本：
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
>   <!-- <script setup>
>       import {defineComponent, ref} from "vue"
>       //1.引入组件
>       import Two from './Child.vue'
>       //2.挂载组件  vue3.2需要使用defineComponent()来挂载
>       defineComponent({
>           components:{
>               Two
>           }
>       })
>   </script> -->
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
> * 新版本（vue3.2)
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
>   ```vue
>   引用者传递数据：
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
>   

##### b. 引用者接收，被引用者传递

>子组件想传递数据父组件借助于自定义事件，如果vue之前的版本，借助于this.semit(自定义事件,数据)如果是新版Vue3.2通过导入defineEmits() 创建一个触发器绑定自定义事件。

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
>         data() {
>             return {
>                 msg: ""
>             }
>         },
>         methods: {
>             send: function(){
>                 //this.$emit() 用于向引用我的组件传值
>                 //参数1：自定义事件名，可以随便写
>                 //参数2：要传递的数据，可以是任意类型
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
>     <!-- 3.显示子组件 -->
>     <!-- vue老版本 通过":属性名1=属性值1" 属性值就是要传递的数据（可以传递任意数据）-->
>     <!-- 注意是空格间隔不是逗号-->
>     <Two @childSend="getChildData" />
>     <!-- <Two :age="myAge" :name="myName" :user="myUser" :list="myList"/> -->
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
>         // 方法的参数就是子组件传递的数据（参数名可以随便写）
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
>       //defineEmits()返回一个触发器 用于触发自定义事件
>       //触发器函数（参数1：自定义事件，参数2：传递的数据）
>       const emit=defineEmits(['childsend'])
>       const msg=ref();
>       const send=()=>{
>           emit('childSend',msg.value);//触发器函数（参数1：自定义事件，参数2：传递的数据）
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
>       const getChildData = (res) => {
>           result.value = res;
>       }
>   </script>
>   ```
>
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
>           mounted:()=>{
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
>           // //当组件销毁 会自动执行下面两个函数 看不到效果的
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
>   > 和旧版相比，新版vue方式beforeCreate和created已经被调用了，所以这两个函数再使用是可以忽略不计的需要导入对应函数名`(on钩子函数)`通过import 而且每种钩子函数可以编写多个
>
>   ```vue
>   <template>
>       <h3>msg:{{ msg }}</h3>
>       <!--注意双引号中要用单引号-->
>       <button @click="msg='数据2'">改变数据</button>
>   </template>
>   <!--
>       新版钩子函数:
>       和旧版相比，新版vue方式beforeCreate和created已经被调用了，所以这两个函数再使用是可以忽略不计的需要导入对应函数名(on钩子函数)通过import 而且每种钩子函数可以编写多个
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
>       onUpdated(function(){
>           console.log("更新后")
>           msg.value="数据3"
>       })
>       onMounted(function(){
>           
>       })
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
>
> * vue项目中安装swiper（==注意是在要使用的项目的目录中安装==）
>
>   ```shell
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
> * 在组件中导入swiper，类似于之前学习的组件引入
>
>   ```vue
>   <template>
>       <!-- 4.导入成功后使用Swiper标签导入轮播图组件，SwiperSlide子标签来添加图片 -->
>       <div>
>           <Swiper>
>           <SwiperSlide>
>               <img src="../assets/logo.png">
>           </SwiperSlide>
>           <SwiperSlide>
>               <img src="../assets/logo.png">
>           </SwiperSlide>
>           <SwiperSlide>
>               <img src="../assets/logo.png">
>           </SwiperSlide>
>           <SwiperSlide>
>               <img src="../assets/logo.png">
>           </SwiperSlide>
>       </Swiper>
>       </div>
>   </template>
>   
>   <!-- 如果使用vue3.2 setup语法糖 可以省略挂载组件 -->
>   <script setup>
>       import { defineComponent } from 'vue';
>       //1.引入组件Swiper,SwiperSlid来自'swiper/vue'
>       import { Swiper,SwiperSlide } from 'swiper/vue';
>       //1.1引入swiper的css组件
>       import 'swiper/css';
>   
>       //2.挂载组件(3.2加setup可以省略)
>       defineComponent({
>           components:{
>               Swiper,SwiperSlide
>           }
>       })
>   </script>
>   
>   <style scoped>
>       div{
>           text-align: center;
>       }
>   </style>
>   d
>   ```

### 9. Vue发送异步请求

> * 原生js实现的，步骤比较繁琐 不推荐
> * jQuery封装好的方法 $.post() $.get() s.ajax(),但是需要手动控制请求和响应的数据格式 不推荐
> * 通过axios发送异步请求 底层就是对jQuery的ajax进一步的封装，非常适合
>   前端分离的项目 它默认的传递数据格式就是json   推荐使用

#### 9.1 axios的安装和使用

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
>   > * 通过import axios from'axios' 这样使用的话
>   >   缺点：大型项目，每个组件都需要引入 比较多 繁琐 也容易忘了导入。
>   >
>   > * 全局导入：在vue项目入口(main.js) 添加一段配置 这样以后每个组件都无需导入axios
>   >
>   >   ```js
>   >   // 在main.js中进行全局配置
>   >   import { createApp } from 'vue'
>   >   import App from './App.vue'
>   >   import './registerServiceWorker'
>   >   // 1. 导入axios组件
>   >   import axios from 'axios'
>   >   
>   >   //createApp(App).mount('#app')
>   >   //2.创建App组件：
>   >   const app = createApp(App)
>   >   //3.在创建App组件后，挂载App组件前，进行全局引入
>   >   //app.config.globalProperties.任意别名=要导入的组件
>   >   //这样定义好了后，其他组件就可以通过这个任意别名直接使用axios
>   >   app.config.globalProperties.myAxios=axios
>   >   //4.挂载App组件
>   >   app.mount('#app')
>   >   ```
>
> * 使用：
>
>   > * 老版本：直接在script中通过this直接使用
>   >
>   >   ```vue
>   >   <!-- 老版本 -->
>   >   <script>
>   >       export default{
>   >           methods:{
>   >               queryById(){
>   >                  this.myAxios.post('http://localhost:80/sel?id=' + this.id).then(res=>{
>   >                       console.log(res.data);
>   >                  })
>   >               }
>   >           }
>   >       }
>   >   </script>
>   >   ```
>   >
>   > * 新版本 setup语法糖：
>   >
>   >   ```vue
>   >   先通过getCurrentInstance()获取当前对象this，用{proxy}来接收，在后面通过proxy.myAxios.post来发送异步请求：
>   >   <script setup>
>   >       import { getCurrentInstance,ref } from 'vue';
>   >       //类似于老版本的this获取当前的实例
>   >       //{proxy}是固定返回的 要求不能改名字 需要添加括号
>   >       const {proxy}=getCurrentInstance()
>   >       const user=ref({})
>   >       const msg=ref()
>   >       const add=()=> {
>   >           // post()函数参数1是地址，参数2是数据（会自动转化为json数据）
>   >           proxy.myAxios.post('http://localhost:80/add',user.value).then(res=>{
>   >               msg.value = res.data.msg
>   >           })
>   >       }
>   >   </script>
>   >   ```

#### ==9.3 请求的封装==
> 虽然使用了单次导入axios和全局导入axios但是两种方式各有优缺点,全局导入缺点是：没有对请求的安全做限制 而且 发送请求的时候也会有很多相同的部分，如：
>
> ```java
> http://localhost:9999/add
> http://localhost:9999/se1
> http://localhost:9999 就属于相同的部分
> ```

> 封装方式：
>
> ```js
> //这个文件的目的是将axios对象进行封装 提高安全性
> // 1.导入axios
> import axios from 'axios'
> // 2.创建错误对象映射(参数status:状态码，参数info:错误信息)
> const errorHandler=(status,info)=>{
>     //使用switch进行等值判断
>     switch(status) {
>         case 400: console.log('客户端参数有误');break;
>         case 404: console.log('地址错误');break;
>         case 500: console.log(info);break;
>         default : console.log(info);break;
>     }
> }
> 
> // 3.通过axios创建请求实例
> const instance = axios.create({
>     timeout:5000 //控制请求超时数据，单位ms
> })
> 
> // 4.通过请求实例 配置 请求拦截器
> //instance.interceptors.request.use(成功函数，失败函数)
> instance.interceptors.request.use(
>     config=>{
>         if(config.method == "post") {
>             //处理所有的post请求 要看公司需求
>         } else if(config.method == "get") {
>             //处理所有get请求
>         }
>         // ... 可以写很多个
>         //一定要返回，不然白写
>         return config;
>     },
>     error=>{
>         //返回错误信息
>         return Promise.reject(error);
>     }
> )
> 
> // 5.通过请求实例 配置 响应拦截器
> instance.interceptors.response.use(
>     responce=>{
>         //根据状态码是否是200来控制返回的信息
>         return responce.status==200? Promise.resolve(response):Promise.reject(response)
>     },
>     eror=>{
>         //定义响应变量，因为常量不能改
>         const {response} = error;
>         //调用上面的错误映射传入状态码和错误信息
>         errorHandler(response.status,response.info);
>     }
> )
> // 6.导出请求实例
> export default instance;
> ```
>
> * 创建一个包(api包)（一般存放的是发送网络请求，文件可能会有很多），
>   比如：base.js(可以常量同意地址) index.js user.js bbs.js
>
>   * base.js
>
>     ```js
>     //创建公共地址，比如：http://localhost:9999
>     //这样其他请求 公共部分就无需编写了
>     const base={
>         baseUrl:"http://localhost:80" //写项目地址
>     }
>     
>     //导出(这样别人就能通过import进行导入了，注意导出的名字带了{}，要求导入时也要带{})
>     export default base;
>     ```
>
>   * index.js : 不同的公司，名称可能不同，但目的是一样的（调用之前封装好的请求 和 定义好的地址）
>
>     ```js
>     //引入封装好的请求(当前文件是js 导入的也是js 后者是可以省略的)
>     import myRequest from '../util/request';
>     //引入封装好的公共地址
>     import myBase from 'base';
>     
>     // 创建公共api接口， 编写好了 以后发送不同模块网络请求
>     // 都是引入这个api接口 来实现的 公司业务不同 这个也是经常
>     const api={
>         //比如:post请求封装在一起
>         postReq(url,data){
>             return axios.post(base.baseUrl+url, data)
>         },
>         //比如:get请求封装在一起
>         getReq(url,data){
>             return axios.get(base.baseUrl+url, data)
>         }
>     }
>     export default api;
>     ```
>
> * 如何使用封装好的api接口发送请求
>
>   ```
>   ```

### 10.前端跨域
> 前端跨域一般可以通过前端vue项目的配置文件vue.config.js,添加一段配置 添加一个proxy代理对象 配置好后端地址 代理对象帮我们转发到目标地址
>
> ```
> 配置前:http://localhost:9999/show  ==>  访问后端查询 但是会出现跨域问题
> 配置好之后: /api/show ==> http://localhost:9999/api/show
> 	再进行路径重写去除多余/api ==> http://localhost:9999/show
> ```
>
> 如果请求封装了只需要修改base.js 其他内容都不需要修改
>
> ```js
> //创建公共地址的比如:http://localhost:9999
> //这样其他请求 公共部分无需编写了
> const base={
> 	baseUrl:"/api"//等价于http://localhost:9999
> }
> export default base;
> ```
>
> ==注：修改了vue.config.js一定要重启服务器，否则不生效，就它最特殊，其他都不需要重启。==

### ==11.Vue路由==

> 路由主要是用来管理vue组件中的关系，可以实现路径跳转，可以实现组件之间的数据传递，这样可以让vue.js构建单页的应用更加轻松。

#### 11.1 vue路由安装

> 注：不要通过cnpm来安装，虽然语法通过，但是路由都会失效。(因为国内的少了几个组件)，推荐使用vue ui图形界面方式安装依赖，或 创建vue项目时 选择路由插件
>
> * 使用vue ui图形界面安装（前期联系时使用）
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

> * 创建一个目录：router用来保存路由配置文件(index.js)通过路由管理所有vue组件
>
> * 再创建一个目录：view用来保存所有的被路由管理的vue组件，其他vue组件依然可以保存中原来的component目录下，但大多数组件都被路由管理。
>
>   * views: 创建组件的位置 比如: Home.vue User.vue
>
>   * router: 创建一个index.js 编写路由配置(引入路由 路由规则 创建路由 导出路由)
>
>     ```js
>     //1.导入路由组件对象
>     import { createRouter, createWebHashHistory} from 'vue-router'
>     import Home from '../views/Home.vue'
>     
>     //2.创建路由对象(数组 因为vue组件有很多个)
>     const routes = [
>         {
>             path:'/', //访问的路径
>             //注意是全小写，错一个都不行
>             component:Home 
>         },
>         {
>             // 这种写法 是属于异步加载 上面的方式属于同步加载
>             // 般来说首页适合 同步加载 其他组件 都是异步加载
>             path:'/user', //访问的路径;
>             component:( )=>import("../views/User.vue") 
>         }
>     ]
>     
>     // 3.创建路由组件
>     const router=createRouter(
>         {
>             //createWebHashHistroy比较常用
>             // 需要配合后端配置做重定向，否则会404
>             history: createWebHashHistory(),
>             routes //路由规则
>         }
>     )
>     
>     //4.导出vue组件对象
>     export default router;
>     ```
>
>     
>
>   * main.js: 再vue项目的入口 启用vue 路由
>
>     ```js
>     import { createApp } from 'vue'
>     import App from './App.vue'
>     import './registerServiceWorker'
>     // 1. 导入axios组件
>     import axios from 'axios'
>     
>     //引入路由 如果格式是js 可以省略
>     //如果文件名就一个叫index.js 则都可以省略
>     import router from './router'
>     
>     
>     //使用路由 创建之后 挂载之前 位置一定不能换
>     createApp(App).use(router).mount('#app')
>     ```

#### 11.3 使用路由

> 在vue项目的首页App.vue(其他页面也可以添加)添加几个标签：
>
> ```vue
> <!--表示路由显示的入口-->
> <router-view/> 或 <routerView/>
> 
> <!--等价于之前的a标签 连接标签-->
> <router-link to="链接地址">链接名称</router-link>
> ```

#### 11.4 路由传递参数

> 类似于后端 根据id传递后端 会在同一个页面显示不同的内容 而vue路由肯定也需要这种需求
>
> * 路由配置地址的时候添加":"表示 传递参数
>
>   ```vue
>   {
>       //传递多个 /goodz/:id/:name
>       path:'/goodz/:id/:name
>       component:()=> import("../views/XX.vue")
>   }
>   ```
>
> * route-link标签地址体哦阿正时传入指定的随机（要和配置的方式一样）
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
>       const id=useRoute().params.id
>       const name=useRoute().params.name
>   </script>
>   ```

#### 11.5 二级路由
> 如果项目中的功能特别复杂 设计很多组件 肯定会出现父级导航 和子级导航 每个导航 都有很多子级组件 对应 就要设计到二级路由或者多级路由。
>
> * 在路由配置文件添加属性 children
>
> ```js
> //1.导入路由组件对象
> import { createRouter, createWebHashHistory} from 'vue-router'
> import Home from '../views/Home.vue'
> 
> //2.创建路由对象(数组 因为vue组件有很多个)
> const routes = [
>     {
>         path:'/', //访问的路径
>         component:Home //小写的c
>     },
>     {
>         // 这种写法 是属于异步加载 上面的方式属于同步加载
>         // 般来说首页适合 同步加载 其他组件 都是异步加载
>         path:'/user', //访问的路径;
>         component:()=>import("../views/User.vue") 
>     },
>     {
>         path:'/news',
>         component:() => import("../views/New.vue")
>     },
>     {
>         // ":"表示我要传递参数，类似于后端的？ 师兄们可以任意编写
>         // vue组件就需要根据属性名来取值
>         // /newContent/:属性1/:属性2/:属性3  来传递多个值
>         path:'/newContent/:type',
>         component: ()=> import("../views/NewsContent.vue")
>     },
>     {
>         //一级路由
>         path:'/article',
>         component:()=>import("../views/Article.vue"),
>         //二级路由:类似于一级路由的嵌套编写
>         //注意：二级路由开始 地址不要加/
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
> * 在一级路由组件页面中 通过<router-link to="/一级/二级"
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

> * 完整引入: 无论是否 需要 不用在乎打包项目的大小 优点在于使用很方便。
>
>   ```js
>   //在main.js或main.ts中
>   import { createApp } from 'vue'
>   import App from './App.vue'
>   import './registerServiceWorker'
>   import router from './router'
>   //1. 导入elementplus
>   import Elementplus from 'element-plus'
>   //1.1 导入elementplus的样式index.css
>   import 'element-plus/dist/index.css'
>   
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
>       //按需引入添加的
>       configureWebpack:{
>         plugins: [
>           AutoImport({
>             resolvers: [ElementPlusResolver()],
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
>   * 修改配置文件，一定要重启服务器。

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

### 13.Vue使用Vant(做前台)

> Vant是一个轻量级的 可以定制化移动端的组件库 使用方式类似于Elementplus区别在于Vant 适用于实现移动端 小程序端 实现前台展示界面。
>
> 官网地址：https://vant-ui.github.io/vant/#/zh-CN

#### 13.1 Vant安装和使用

> * 安装: 通过vue ui安装
>   ![image-20240829113447925](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装vant.png)
>   ![image-20240829113647599](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\成功安装vant.png)
>
> * 引入: 推荐按需引入插件
>
>   * 安装两个场景：（如果安装过就无需再次安装）
>
>     ```shell
>     cnpm i @vant/auto-import-resolver unplugin-vue-components -D
>     如果Elementplus组件安装过unplugin-vue-components 无需再次安装
>     cnpm i @vant/auto-import-resolver -D
>     ```
>
>     ![image-20240829114602469](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\安装Vant必要的插件.png)
>
>   * 1
>
>     ```js
>     //如果在Elementplus组件导入过就不要再导入
>     const AutoImport = require('unplugin-auto-import/webpack');
>     const Components = require('unplugin-vue-components/webpack');
>     //在elementplus基础上Vant额外导入
>     const { VantResolver } = require('@vant/auto-import-resolver');
>     
>     module.exports = defineConfig({
>       transpileDependencies: true,
>       //生产服务器
>       devServer: {
>         //加一个proxy对象表示跨域代理对象,在里面配置跨域
>         proxy: {
>           //可以配置很多组跨域，每组跨域都是一个对象
>           //'/任意写':{配置好跨域}
>           //使用http://localhost:9999/任意写/show来启动跨域，就是在写请求的前面加一个跨域请求
>           '/api': {
>             //配置后端的地址(即要跨域的目标地址)，proxy会转发到这里去
>             target: 'http://localhost:80', //一定要和后端的端口号相同
>             //路径重写(把跨域请求地址，重写为真正的请求地址)目的是去除请求地址中的api
>             pathRewrite: { '^/api': '' },
>             //代理websocket 可以不写
>             ws: true,
>             //开启跨域
>             changeOrigin: true
>           }
>         }
>       },
>       //按需引入添加的这里是在ElementPlus基础上额外加了VantResolver()
>       configureWebpack: {
>         plugins: [
>           AutoImport({
>             resolvers: [ElementPlusResolver(),VantResolver()],
>           }),
>           Components({
>             resolvers: [ElementPlusResolver(),VantResolver()],
>           }),
>         ],
>       }
>     })
>     ```
>
> * 使用: 在vant官网复制粘贴代码

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
>   { //如何编写一个载荷：
>   	//上面的默认字段
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
> 错误2:
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
>
> 错误6：后端数据为什么前端无法读取？
>
> ![image-20240828172829063](D:\Desktop\gitee\java-learning\sc240601\前端\Vue3\img\image-20240828172829063.png)
>
> <hr>
>
> 错误7：ssm项目和sm项目用的分页插件是不一样的
> sm(Springboot+mybatis)项目：
>
> ```xml
>         <!--SpringBoot - mybatis专用分页插件-->
>         <dependency>
>             <groupId>com.github.pagehelper</groupId>
>             <artifactId>pagehelper-spring-boot-starter</artifactId>
>             <version>1.2.12</version>
>         </dependency>
> ```
>
> 
>
> 