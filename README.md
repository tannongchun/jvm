# VisualVM插件之BTrace 使用
## BTrace插件安装
## 打开JDK/bin/jvisualvm.exe
+ ![VisualVM插件安装](https://i.imgur.com/w1kXBEo.png)
+ 若下载不了查询请在设置处更换 Java VisualVM 插件中心 
地址：如下https://visualvm.github.io/uc/8u131/updates.xml.gz

## BTrace 调试代码

+ 下载涉及依赖包btrace-agent.jar、btrace-boot.jar、btrace-client.jar
+ [Btrcace下载](https://github.com/btraceio/btrace/releases/tag/v1.3.8.3-1)

## 调试栗子

### JAVA
	```  java
		
     package com.konxiang.jvm;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.util.Map;	
	public class BTraceTest {
	
	  public  int add(int i,int j){
	
	    return i +j;
	  }
	
	  public  static  void main(String []args) throws  Exception{
	    BTraceTest traceTest = new BTraceTest();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    for( int i =0 ;i<10;i++){
	      reader.readLine();
	      int a = (int) Math.round(Math.random() *1000);
	      int b = (int) Math.round(Math.random() *1000);
	      System.out.println(traceTest.add(a,b));
	    }
	  }
	
	}


	```
### BTrace

	```  java

		/* BTrace Script Template */
		import com.sun.btrace.annotations.*;
		import static com.sun.btrace.BTraceUtils.*;
		
		@BTrace
		public class TracingScript {
			/* put your code here */
		
		@OnMethod(
		      clazz="com.konxiang.jvm.BTraceTest",
		      method="add",
		      location = @Location(Kind.RETURN)
		  )
		  public  static  void func(@Self com.konxiang.jvm.BTraceTest instance,int a,int b, @Return int result){
		        println("调用栈:");
		        jstack();
		        println(strcat("方法参数 A：",str(a)));
		        println(strcat("方法参数B：",str(b)));
		        println(strcat("结果：",str(result)));
		  }
		}

	```
+ 如下图所示
![](https://i.imgur.com/tNqeIaW.png)

+ 程序代码结构

![](https://i.imgur.com/64XcMSc.png)

[git 载地址]


