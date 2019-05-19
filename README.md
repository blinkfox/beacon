# Beacon - 轻量级 svg 徽章实时渲染服务

[![HitCount](http://hits.dwyl.io/blinkfox/beacon.svg)](https://github.com/blinkfox/beacon) [![Build Status](https://secure.travis-ci.org/blinkfox/beacon.svg)](https://travis-ci.org/blinkfox/beacon) [![GitHub license](https://img.shields.io/github/license/blinkfox/beacon.svg)](https://github.com/blinkfox/beacon/blob/master/LICENSE) [![codecov](https://codecov.io/gh/blinkfox/beacon/branch/master/graph/badge.svg)](https://codecov.io/gh/blinkfox/beacon) ![Java Version](https://img.shields.io/badge/Java-%3E%3D%208-blue.svg)

这是一个模仿 [shields.io](https://shields.io/) 用来生成 `svg` 徽章的 Java Web 微服务，可以供内网部署使用。

## 特性

Beacon 是一个实时生成 `svg` 徽章的服务，你可以将本服务生成的徽章方便快捷的嵌入到你的 Markdown 或者 HTML 文件中。

- 轻量级、简单快速
- 易于集成，只需将 `svg` 徽章的链接信息按需修改即可
- 支持多种样式风格和颜色主题
- 支持 `4.7` 版本的 [Font Awesome](http://fontawesome.dashgame.com) 图标及黑白两种主题

## 徽章链接参数简介

制作徽章需要使用到以下几个参数，其中仅 `message` 参数是必须的：

- `label`: 徽章左边的文字，如果不填写，将会是空值。
- `message`: 徽章右边的文字，必填。
- `color`: 徽章右边的背景颜色，如果不填写，将会是brightgreen，也可以填写任何16进制的颜色值（不含#号）。
- `style`: 徽章的整体风格，默认是flat。
- `labelColor`: 徽章左边的背景颜色，默认是grey，也可以填写任何16进制的颜色值（不含#号）。
- `logo`: 徽章左边的 LOGO，值为 Font Awesome 中的图标名称，如果不填写或者图标不存在则视为没有。
- `logoTheme`: 徽章 LOGO 的颜色主题，有黑白两种，默认是白色主题，如果 style 为social时，则 LOGO 会自动切换为黑色主题。

## 开发部署

### 开发

如果你想参与本项目的开发和维护，将本项目 `clone` 到本地之后，然后导入 IDEA 或者 Eclipse 中，启动 SpringBoot 服务即可。

或者运行以下命令启动服务：

```bash
mvn spring-boot:run
```

然后访问 <http://127.0.0.1:2020> 即可看到徽章首页，尝试生成徽章即可。

### 部署

你可以直接 `mvn package` 之后，将此服务通过 [jpack](https://github.com/blinkfox/jpack-maven-plugin) 生成的包部署起来。

## 首页效果

以下是启动服务之后的首页效果，供你参考和了解本项目：

![首页效果1](http://static.blinkfox.com/beacon-index-view1.png)

![首页效果2](http://static.blinkfox.com/beacon-index-view2.png)

![首页效果3](http://static.blinkfox.com/beacon-index-view3.png)
