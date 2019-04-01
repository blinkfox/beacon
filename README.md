# Beacon - 轻量级 svg 徽章实时渲染服务

这是一个模仿 [shields.io](https://shields.io/) 用来生成 `svg` 徽章的 Java Web 微服务。

## 特性

Beacon 是一个实时生成 `svg` 徽章的服务，你可以将本服务生成的徽章方便快捷的嵌入到你的 Markdown 或者 HTML 文件中。

- 轻量级、简单快速
- 易于集成，只需将 `svg` 徽章的链接信息按需修改即可
- 支持多种样式风格和颜色主题

## 参与开发

如果你想参与本项目的开发和维护，将本项目 `clone` 到本地之后，然后导入 IDEA 或者 Eclipse 中，启动 SpringBoot 服务即可。

或者运行以下命令启动服务：

```bash
mvn spring-boot:run
```

然后访问 [http://127.0.0.1:2020](http://127.0.0.1:2020) 即可看到徽章首页，尝试生成徽章即可。