<template>
  <div class="screenshots-container">
    <el-container>
      <el-header height="60px">
        <div class="header-content">
          <div class="logo">
            <h1>舆情监控系统</h1>
          </div>
          <div class="nav-menu">
            <el-menu mode="horizontal" :router="true">
              <el-menu-item index="/">首页</el-menu-item>
              <el-menu-item index="/data">数据管理</el-menu-item>
              <el-menu-item index="/analysis">数据分析</el-menu-item>
              <el-menu-item index="/screenshots">系统截图</el-menu-item>
            </el-menu>
          </div>
          <div class="user-info">
            <el-dropdown @command="handleCommand">
              <span class="user-dropdown">
                {{ userInfo.nickname || userInfo.username }}
                <el-icon><arrow-down /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item command="profile">个人信息</el-dropdown-item>
                  <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <el-main>
        <div class="screenshots-content">
          <div class="page-header">
            <h2>系统截图</h2>
          </div>

          <el-row :gutter="20">
            <el-col :span="8" v-for="(screenshot, index) in screenshots" :key="index">
              <el-card class="screenshot-card" :body-style="{ padding: '0px' }">
                <el-image
                  :src="screenshot.image"
                  :alt="screenshot.title"
                  fit="cover"
                  class="screenshot-image"
                  :preview-src-list="[screenshot.image]"
                >
                  <template #error>
                    <div class="image-error">
                      <el-icon><picture-filled /></el-icon>
                      <span>加载失败</span>
                    </div>
                  </template>
                </el-image>
                <div class="screenshot-info">
                  <h3>{{ screenshot.title }}</h3>
                  <p>{{ screenshot.description }}</p>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ArrowDown, PictureFilled } from '@element-plus/icons-vue'

const store = useStore()
const router = useRouter()

const userInfo = computed(() => store.getters['user/userInfo'])

const screenshots = [
  {
    title: '登录界面',
    description: '系统登录界面，支持用户名密码登录',
    image: '/images/screenshots/login.png'
  },
  {
    title: '数据管理',
    description: '数据管理界面，支持数据导入和管理',
    image: '/images/screenshots/data.png'
  },
  {
    title: '情感分析',
    description: '基于BERT的情感分析结果展示',
    image: '/images/screenshots/sentiment.png'
  },
  {
    title: '主题聚类',
    description: '使用LDA算法的主题聚类结果',
    image: '/images/screenshots/topic.png'
  },
  {
    title: '趋势分析',
    description: '舆情趋势分析图表展示',
    image: '/images/screenshots/trend.png'
  },
  {
    title: '词云分析',
    description: '关键词词云可视化展示',
    image: '/images/screenshots/wordcloud.png'
  }
]

const handleCommand = (command) => {
  switch (command) {
    case 'profile':
      // TODO: 实现个人信息页面
      break
    case 'logout':
      store.dispatch('user/logout')
      router.push('/login')
      break
  }
}
</script>

<style scoped>
.screenshots-container {
  min-height: 100vh;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
  padding: 0 20px;
  background-color: var(--bg-white);
  box-shadow: var(--shadow-sm);
}

.logo h1 {
  margin: 0;
  font-size: 20px;
  color: var(--text-primary);
}

.nav-menu {
  flex: 1;
  margin: 0 40px;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-dropdown {
  display: flex;
  align-items: center;
  gap: 4px;
  cursor: pointer;
  color: var(--text-regular);
}

.screenshots-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.page-header {
  margin-bottom: 30px;
}

.page-header h2 {
  margin: 0;
  color: var(--text-primary);
}

.screenshot-card {
  margin-bottom: 20px;
  transition: var(--transition-normal);
}

.screenshot-card:hover {
  transform: translateY(-5px);
  box-shadow: var(--shadow-md);
}

.screenshot-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.image-error {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  color: var(--text-secondary);
}

.image-error .el-icon {
  font-size: 40px;
  margin-bottom: 10px;
}

.screenshot-info {
  padding: 15px;
}

.screenshot-info h3 {
  margin: 0 0 10px;
  color: var(--text-primary);
}

.screenshot-info p {
  margin: 0;
  color: var(--text-secondary);
  font-size: 14px;
}

@media screen and (max-width: 768px) {
  .header-content {
    padding: 0 10px;
  }

  .nav-menu {
    margin: 0 20px;
  }

  .screenshots-content {
    padding: 10px;
  }

  .el-col {
    width: 100%;
  }
}
</style> 