<template>
  <div class="data-container">
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
        <div class="data-content">
          <div class="page-header">
            <h2>数据管理</h2>
            <el-button type="primary" @click="handleUpload">
              <el-icon><upload /></el-icon>
              上传数据
            </el-button>
          </div>

          <el-card class="data-card">
            <template #header>
              <div class="card-header">
                <span>数据列表</span>
                <el-input
                  v-model="searchQuery"
                  placeholder="搜索数据"
                  class="search-input"
                  clearable
                  @clear="handleSearch"
                  @input="handleSearch"
                >
                  <template #prefix>
                    <el-icon><search /></el-icon>
                  </template>
                </el-input>
              </div>
            </template>

            <el-table
              v-loading="loading"
              :data="tableData"
              style="width: 100%"
              border
            >
              <el-table-column prop="id" label="ID" width="80" />
              <el-table-column prop="title" label="标题" min-width="200" />
              <el-table-column prop="source" label="来源" width="120" />
              <el-table-column prop="type" label="类型" width="100" />
              <el-table-column prop="createTime" label="创建时间" width="180" />
              <el-table-column label="操作" width="200" fixed="right">
                <template #default="{ row }">
                  <el-button-group>
                    <el-button
                      type="primary"
                      size="small"
                      @click="handleView(row)"
                    >
                      查看
                    </el-button>
                    <el-button
                      type="warning"
                      size="small"
                      @click="handleEdit(row)"
                    >
                      编辑
                    </el-button>
                    <el-button
                      type="danger"
                      size="small"
                      @click="handleDelete(row)"
                    >
                      删除
                    </el-button>
                  </el-button-group>
                </template>
              </el-table-column>
            </el-table>

            <div class="pagination-container">
              <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[10, 20, 50, 100]"
                :total="total"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </el-card>
        </div>
      </el-main>
    </el-container>

    <!-- 上传对话框 -->
    <el-dialog
      v-model="uploadDialogVisible"
      title="上传数据"
      width="500px"
    >
      <el-upload
        class="upload-demo"
        drag
        action="/api/data/upload"
        :headers="uploadHeaders"
        :on-success="handleUploadSuccess"
        :on-error="handleUploadError"
        :before-upload="beforeUpload"
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">
          将文件拖到此处，或<em>点击上传</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            支持 .csv, .xlsx, .json 格式文件
          </div>
        </template>
      </el-upload>
    </el-dialog>

    <!-- 查看/编辑对话框 -->
    <el-dialog
      v-model="editDialogVisible"
      :title="dialogType === 'view' ? '查看数据' : '编辑数据'"
      width="800px"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="来源" prop="source">
          <el-input v-model="form.source" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option label="社交媒体" value="social" />
            <el-option label="新闻" value="news" />
            <el-option label="评论" value="comment" />
          </el-select>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input
            v-model="form.content"
            type="textarea"
            :rows="6"
            :disabled="dialogType === 'view'"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editDialogVisible = false">取消</el-button>
          <el-button
            v-if="dialogType === 'edit'"
            type="primary"
            @click="handleSave"
          >
            保存
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  ArrowDown,
  Upload,
  Search,
  UploadFilled
} from '@element-plus/icons-vue'
import { getDataList, uploadData, updateData, deleteData } from '@/api/data'

const store = useStore()
const router = useRouter()

// 表格数据
const loading = ref(false)
const tableData = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const searchQuery = ref('')

// 用户信息
const userInfo = computed(() => store.getters['user/userInfo'])

// 上传相关
const uploadDialogVisible = ref(false)
const uploadHeaders = computed(() => ({
  Authorization: `Bearer ${store.state.user.token}`
}))

// 编辑相关
const editDialogVisible = ref(false)
const dialogType = ref('view')
const formRef = ref(null)
const form = reactive({
  id: '',
  title: '',
  source: '',
  type: '',
  content: ''
})

const rules = {
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' },
    { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
  ],
  source: [
    { required: true, message: '请输入来源', trigger: 'blur' }
  ],
  type: [
    { required: true, message: '请选择类型', trigger: 'change' }
  ],
  content: [
    { required: true, message: '请输入内容', trigger: 'blur' }
  ]
}

// 获取数据列表
const fetchData = async () => {
  try {
    loading.value = true
    const params = {
      page: currentPage.value,
      size: pageSize.value,
      query: searchQuery.value
    }
    const { data } = await getDataList(params)
    tableData.value = data.items
    total.value = data.total
  } catch (error) {
    ElMessage.error('获取数据列表失败')
  } finally {
    loading.value = false
  }
}

// 搜索
const handleSearch = () => {
  currentPage.value = 1
  fetchData()
}

// 分页
const handleSizeChange = (val) => {
  pageSize.value = val
  fetchData()
}

const handleCurrentChange = (val) => {
  currentPage.value = val
  fetchData()
}

// 上传
const handleUpload = () => {
  uploadDialogVisible.value = true
}

const beforeUpload = (file) => {
  const isValidType = ['text/csv', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/json'].includes(file.type)
  if (!isValidType) {
    ElMessage.error('只能上传 CSV、Excel 或 JSON 文件！')
    return false
  }
  return true
}

const handleUploadSuccess = (response) => {
  ElMessage.success('上传成功')
  uploadDialogVisible.value = false
  fetchData()
}

const handleUploadError = () => {
  ElMessage.error('上传失败')
}

// 查看/编辑
const handleView = (row) => {
  dialogType.value = 'view'
  Object.assign(form, row)
  editDialogVisible.value = true
}

const handleEdit = (row) => {
  dialogType.value = 'edit'
  Object.assign(form, row)
  editDialogVisible.value = true
}

const handleSave = async () => {
  if (!formRef.value) return

  try {
    await formRef.value.validate()
    await updateData(form.id, form)
    ElMessage.success('保存成功')
    editDialogVisible.value = false
    fetchData()
  } catch (error) {
    if (error.response) {
      ElMessage.error(error.response.data.message || '保存失败')
    } else {
      ElMessage.error('保存失败，请稍后重试')
    }
  }
}

// 删除
const handleDelete = (row) => {
  ElMessageBox.confirm(
    '确定要删除这条数据吗？',
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      await deleteData(row.id)
      ElMessage.success('删除成功')
      fetchData()
    } catch (error) {
      ElMessage.error('删除失败')
    }
  })
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

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.data-container {
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

.data-content {
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

.data-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-input {
  width: 300px;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.upload-demo {
  text-align: center;
}

.el-upload__tip {
  margin-top: 10px;
  color: var(--text-secondary);
}

@media screen and (max-width: 768px) {
  .header-content {
    padding: 0 10px;
  }

  .nav-menu {
    margin: 0 20px;
  }

  .data-content {
    padding: 10px;
  }

  .search-input {
    width: 200px;
  }
}
</style> 