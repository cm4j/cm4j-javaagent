# 介绍

本项目是生成agent类的，供需要JDK的agent方式热更的项目使用

# 使用说明

执行 mvn clean install，即可把本项目打包安装到本地maven仓库中

# 注意

打包插件中，配置了agent类

```xml
<manifestEntries>
  <Agent-Class>com.cm4j.agent.JavaDynAgent</Agent-Class>
  <Can-Redefine-Classes>true</Can-Redefine-Classes>
  <Can-Retransform-Classes>true</Can-Retransform-Classes>
</manifestEntries>
```
