# 基于BERT和LDA的社交媒体舆情监控系统

## 项目简介

本项目是一个基于BERT和LDA的社交媒体舆情监控系统，用于分析和管理社交媒体上的舆情数据。系统提供数据管理、情感分析、主题聚类等功能，帮助用户更好地理解和把握舆情动态。

## 技术栈

- 前端：Vue 3 + Vite + Element Plus + ECharts
- 后端：Spring Boot + MySQL + Redis
- 算法：BERT + LDA

## 功能特点

- 数据管理：支持多种数据源的导入和管理
- 情感分析：基于BERT模型的情感分析
- 主题聚类：使用LDA算法进行主题聚类
- 趋势分析：舆情趋势可视化
- 词云分析：关键词词云展示

## 项目结构

```
g_project/
├── src/
│   ├── api/          # API 接口
│   ├── assets/       # 静态资源
│   ├── components/   # 公共组件
│   ├── router/       # 路由配置
│   ├── store/        # 状态管理
│   ├── views/        # 页面组件
│   ├── App.vue       # 根组件
│   └── main.js       # 入口文件
├── public/           # 公共资源
├── .env              # 环境变量
├── index.html        # HTML 模板
├── package.json      # 项目配置
├── vite.config.js    # Vite 配置
└── README.md         # 项目说明
```

## 开发环境要求

- Node.js >= 16.0.0
- npm >= 7.0.0

## 安装和运行

1. 克隆项目
```bash
git clone [项目地址]
cd g_project
```

2. 安装依赖
```bash
npm install
```

3. 启动开发服务器
```bash
npm run dev
```

4. 构建生产版本
```bash
npm run build
```

## 环境变量配置

在 `.env` 文件中配置以下环境变量：

```
VITE_APP_TITLE=舆情监控系统
VITE_API_BASE_URL=http://localhost:8080/api
```

## 浏览器支持

- Chrome >= 88
- Firefox >= 87
- Safari >= 14
- Edge >= 88

## 贡献指南

1. Fork 项目
2. 创建特性分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

## 许可证

[MIT License](LICENSE)
