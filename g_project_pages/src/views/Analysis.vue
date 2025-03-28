<template>
  <div class="analysis-container">
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
        <div class="analysis-content">
          <div class="page-header">
            <h2>数据分析</h2>
            <el-button type="primary" @click="handleNewAnalysis">
              <el-icon><plus /></el-icon>
              新建分析
            </el-button>
          </div>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-card class="analysis-card">
                <template #header>
                  <div class="card-header">
                    <span>情感分析</span>
                    <el-button
                      type="primary"
                      link
                      @click="handleAnalyze('sentiment')"
                    >
                      开始分析
                    </el-button>
                  </div>
                </template>
                <div class="chart-container">
                  <div ref="sentimentChart" class="chart"></div>
                </div>
                <div class="analysis-summary">
                  <p>正面情感：{{ sentimentStats.positive }}%</p>
                  <p>中性情感：{{ sentimentStats.neutral }}%</p>
                  <p>负面情感：{{ sentimentStats.negative }}%</p>
                </div>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card class="analysis-card">
                <template #header>
                  <div class="card-header">
                    <span>主题聚类</span>
                    <el-button
                      type="primary"
                      link
                      @click="handleAnalyze('topic')"
                    >
                      开始分析
                    </el-button>
                  </div>
                </template>
                <div class="chart-container">
                  <div ref="topicChart" class="chart"></div>
                </div>
                <div class="analysis-summary">
                  <p>发现主题数：{{ topicStats.count }}</p>
                  <p>主要主题：{{ topicStats.mainTopics.join(', ') }}</p>
                </div>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card class="analysis-card">
                <template #header>
                  <div class="card-header">
                    <span>趋势分析</span>
                    <el-button
                      type="primary"
                      link
                      @click="handleAnalyze('trend')"
                    >
                      开始分析
                    </el-button>
                  </div>
                </template>
                <div class="chart-container">
                  <div ref="trendChart" class="chart"></div>
                </div>
                <div class="analysis-summary">
                  <p>分析周期：{{ trendStats.period }}</p>
                  <p>趋势变化：{{ trendStats.change }}</p>
                </div>
              </el-card>
            </el-col>
          </el-row>

          <el-card class="word-cloud-card">
            <template #header>
              <div class="card-header">
                <span>词云分析</span>
                <el-button
                  type="primary"
                  link
                  @click="handleAnalyze('wordcloud')"
                >
                  生成词云
                </el-button>
              </div>
            </template>
            <div class="word-cloud-container">
              <div ref="wordCloudChart" class="chart"></div>
            </div>
          </el-card>
        </div>
      </el-main>
    </el-container>

    <!-- 新建分析对话框 -->
    <el-dialog
      v-model="dialogVisible"
      title="新建分析"
      width="500px"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="分析名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分析名称" />
        </el-form-item>
        <el-form-item label="分析类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择分析类型">
            <el-option label="情感分析" value="sentiment" />
            <el-option label="主题聚类" value="topic" />
            <el-option label="趋势分析" value="trend" />
            <el-option label="词云分析" value="wordcloud" />
          </el-select>
        </el-form-item>
        <el-form-item label="数据范围" prop="dateRange">
          <el-date-picker
            v-model="form.dateRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          />
        </el-form-item>
        <el-form-item label="数据来源" prop="sources">
          <el-select
            v-model="form.sources"
            multiple
            placeholder="请选择数据来源"
          >
            <el-option label="社交媒体" value="social" />
            <el-option label="新闻" value="news" />
            <el-option label="评论" value="comment" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSubmit">
            开始分析
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import {
  ArrowDown,
  Plus
} from '@element-plus/icons-vue'
import * as echarts from 'echarts'
import {
  getSentimentAnalysis,
  getTopicClustering,
  getTrendAnalysis,
  getWordCloud
} from '@/api/analysis'

const store = useStore()
const router = useRouter()

// 用户信息
const userInfo = computed(() => store.getters['user/userInfo'])

// 图表实例
const sentimentChart = ref(null)
const topicChart = ref(null)
const trendChart = ref(null)
const wordCloudChart = ref(null)
let sentimentChartInstance = null
let topicChartInstance = null
let trendChartInstance = null
let wordCloudChartInstance = null

// 统计数据
const sentimentStats = reactive({
  positive: 0,
  neutral: 0,
  negative: 0
})

const topicStats = reactive({
  count: 0,
  mainTopics: []
})

const trendStats = reactive({
  period: '',
  change: ''
})

// 对话框相关
const dialogVisible = ref(false)
const formRef = ref(null)
const form = reactive({
  name: '',
  type: '',
  dateRange: [],
  sources: []
})

const rules = {
  name: [
    { required: true, message: '请输入分析名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  type: [
    { required: true, message: '请选择分析类型', trigger: 'change' }
  ],
  dateRange: [
    { required: true, message: '请选择数据范围', trigger: 'change' }
  ],
  sources: [
    { required: true, message: '请选择数据来源', trigger: 'change' }
  ]
}

// 初始化图表
const initCharts = () => {
  // 情感分析图表
  sentimentChartInstance = echarts.init(sentimentChart.value)
  sentimentChartInstance.setOption({
    title: {
      text: '情感分布',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        type: 'pie',
        radius: '50%',
        data: [
          { value: 0, name: '正面' },
          { value: 0, name: '中性' },
          { value: 0, name: '负面' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  })

  // 主题聚类图表
  topicChartInstance = echarts.init(topicChart.value)
  topicChartInstance.setOption({
    title: {
      text: '主题分布',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    xAxis: {
      type: 'category',
      data: []
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [],
        type: 'bar'
      }
    ]
  })

  // 趋势分析图表
  trendChartInstance = echarts.init(trendChart.value)
  trendChartInstance.setOption({
    title: {
      text: '情感趋势',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      data: []
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [],
        type: 'line',
        smooth: true
      }
    ]
  })

  // 词云图表
  wordCloudChartInstance = echarts.init(wordCloudChart.value)
  wordCloudChartInstance.setOption({
    title: {
      text: '词云图',
      left: 'center'
    },
    tooltip: {
      show: true
    },
    series: [
      {
        type: 'wordCloud',
        shape: 'circle',
        left: 'center',
        top: 'center',
        width: '70%',
        height: '80%',
        right: null,
        bottom: null,
        sizeRange: [12, 60],
        rotationRange: [-90, 90],
        rotationStep: 45,
        gridSize: 8,
        drawOutOfBound: false,
        textStyle: {
          fontFamily: 'sans-serif',
          fontWeight: 'bold',
          color: function () {
            return 'rgb(' + [
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160)
            ].join(',') + ')'
          }
        },
        emphasis: {
          focus: 'self',
          textStyle: {
            shadowBlur: 10,
            shadowColor: '#333'
          }
        },
        data: []
      }
    ]
  })
}

// 更新图表数据
const updateCharts = async () => {
  try {
    // 情感分析
    const sentimentData = await getSentimentAnalysis()
    sentimentChartInstance.setOption({
      series: [{
        data: [
          { value: sentimentData.positive, name: '正面' },
          { value: sentimentData.neutral, name: '中性' },
          { value: sentimentData.negative, name: '负面' }
        ]
      }]
    })
    Object.assign(sentimentStats, sentimentData)

    // 主题聚类
    const topicData = await getTopicClustering()
    topicChartInstance.setOption({
      xAxis: {
        data: topicData.topics.map(t => t.name)
      },
      series: [{
        data: topicData.topics.map(t => t.count)
      }]
    })
    topicStats.count = topicData.topics.length
    topicStats.mainTopics = topicData.topics.slice(0, 3).map(t => t.name)

    // 趋势分析
    const trendData = await getTrendAnalysis()
    trendChartInstance.setOption({
      xAxis: {
        data: trendData.dates
      },
      series: [{
        data: trendData.values
      }]
    })
    trendStats.period = `${trendData.dates[0]} 至 ${trendData.dates[trendData.dates.length - 1]}`
    trendStats.change = trendData.change

    // 词云
    const wordCloudData = await getWordCloud()
    wordCloudChartInstance.setOption({
      series: [{
        data: wordCloudData.words.map(w => ({
          name: w.word,
          value: w.weight
        }))
      }]
    })
  } catch (error) {
    ElMessage.error('获取分析数据失败')
  }
}

// 新建分析
const handleNewAnalysis = () => {
  dialogVisible.value = true
}

const handleSubmit = async () => {
  if (!formRef.value) return

  try {
    await formRef.value.validate()
    dialogVisible.value = false
    await updateCharts()
    ElMessage.success('分析完成')
  } catch (error) {
    if (error.response) {
      ElMessage.error(error.response.data.message || '分析失败')
    } else {
      ElMessage.error('分析失败，请稍后重试')
    }
  }
}

// 快速分析
const handleAnalyze = async (type) => {
  try {
    await updateCharts()
    ElMessage.success('分析完成')
  } catch (error) {
    ElMessage.error('分析失败')
  }
}

// 用户操作
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

// 监听窗口大小变化
const handleResize = () => {
  sentimentChartInstance?.resize()
  topicChartInstance?.resize()
  trendChartInstance?.resize()
  wordCloudChartInstance?.resize()
}

onMounted(() => {
  initCharts()
  updateCharts()
  window.addEventListener('resize', handleResize)
})

onUnmounted(() => {
  window.removeEventListener('resize', handleResize)
  sentimentChartInstance?.dispose()
  topicChartInstance?.dispose()
  trendChartInstance?.dispose()
  wordCloudChartInstance?.dispose()
})
</script>

<style scoped>
.analysis-container {
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

.analysis-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.page-header h2 {
  margin: 0;
  color: var(--text-primary);
}

.analysis-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-container {
  height: 300px;
  margin-bottom: 20px;
}

.chart {
  width: 100%;
  height: 100%;
}

.analysis-summary {
  padding: 10px;
  background-color: var(--bg-hover);
  border-radius: var(--radius-sm);
}

.analysis-summary p {
  margin: 5px 0;
  color: var(--text-secondary);
}

.word-cloud-card {
  margin-top: 20px;
}

.word-cloud-container {
  height: 400px;
}

@media screen and (max-width: 768px) {
  .header-content {
    padding: 0 10px;
  }

  .nav-menu {
    margin: 0 20px;
  }

  .analysis-content {
    padding: 10px;
  }

  .el-col {
    width: 100%;
    margin-bottom: 20px;
  }
}
</style> 