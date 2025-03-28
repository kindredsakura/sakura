<template>
  <div class="home">
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
            <template v-if="isAuthenticated">
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
            </template>
            <template v-else>
              <el-button type="primary" @click="$router.push('/login')">登录</el-button>
              <el-button @click="$router.push('/register')">注册</el-button>
            </template>
          </div>
        </div>
      </el-header>
      <el-main>
        <div class="welcome-content">
          <h2>欢迎使用基于BERT和LDA的社交媒体舆情监控系统</h2>
          <p>本系统提供以下功能：</p>
          <el-row :gutter="20">
            <el-col :span="8">
              <el-card class="feature-card">
                <template #header>
                  <div class="card-header">
                    <el-icon><data-line /></el-icon>
                    <span>数据管理</span>
                  </div>
                </template>
                <p>支持多种数据源的导入和管理，包括社交媒体数据、新闻数据等。</p>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card class="feature-card">
                <template #header>
                  <div class="card-header">
                    <el-icon><trend-charts /></el-icon>
                    <span>情感分析</span>
                  </div>
                </template>
                <p>基于BERT模型的情感分析，准确识别文本情感倾向。</p>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card class="feature-card">
                <template #header>
                  <div class="card-header">
                    <el-icon><pie-chart /></el-icon>
                    <span>主题聚类</span>
                  </div>
                </template>
                <p>使用LDA算法进行主题聚类，发现热点话题。</p>
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
import { ArrowDown, DataLine, TrendCharts, PieChart } from '@element-plus/icons-vue'

const store = useStore()
const router = useRouter()

const isAuthenticated = computed(() => store.getters['user/isAuthenticated'])
const userInfo = computed(() => store.getters['user/userInfo'])

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
.home {
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
  gap: 10px;
}

.user-dropdown {
  display: flex;
  align-items: center;
  gap: 4px;
  cursor: pointer;
  color: var(--text-regular);
}

.welcome-content {
  max-width: 1200px;
  margin: 40px auto;
  padding: 0 20px;
}

.welcome-content h2 {
  text-align: center;
  margin-bottom: 30px;
  color: var(--text-primary);
}

.welcome-content p {
  text-align: center;
  margin-bottom: 40px;
  color: var(--text-secondary);
}

.feature-card {
  height: 100%;
  transition: var(--transition-normal);
}

.feature-card:hover {
  transform: translateY(-5px);
}

.card-header {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 16px;
  font-weight: 600;
  color: var(--text-primary);
}

.card-header .el-icon {
  font-size: 20px;
  color: var(--primary-color);
}

.feature-card p {
  margin: 0;
  color: var(--text-secondary);
  line-height: 1.6;
}

@media screen and (max-width: 768px) {
  .header-content {
    padding: 0 10px;
  }

  .nav-menu {
    margin: 0 20px;
  }

  .welcome-content {
    padding: 0 10px;
  }

  .el-col {
    width: 100%;
    margin-bottom: 20px;
  }
}
</style> 