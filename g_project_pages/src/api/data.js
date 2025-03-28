import api from './index'

export const getDataList = (params) => {
  return api.get('/data/list', { params })
}

export const getDataDetail = (id) => {
  return api.get(`/data/${id}`)
}

export const createData = (data) => {
  return api.post('/data', data)
}

export const updateData = (id, data) => {
  return api.put(`/data/${id}`, data)
}

export const deleteData = (id) => {
  return api.delete(`/data/${id}`)
}

export const uploadData = (file) => {
  const formData = new FormData()
  formData.append('file', file)
  return api.post('/data/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
} 