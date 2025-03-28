import api from './index'

export const getAnalysisList = (params) => {
  return api.get('/analysis/list', { params })
}

export const getAnalysisDetail = (id) => {
  return api.get(`/analysis/${id}`)
}

export const createAnalysis = (data) => {
  return api.post('/analysis', data)
}

export const getSentimentAnalysis = (data) => {
  return api.post('/analysis/sentiment', data)
}

export const getTopicClustering = (data) => {
  return api.post('/analysis/topic', data)
}

export const getTrendAnalysis = (data) => {
  return api.post('/analysis/trend', data)
}

export const getWordCloud = (data) => {
  return api.post('/analysis/wordcloud', data)
} 