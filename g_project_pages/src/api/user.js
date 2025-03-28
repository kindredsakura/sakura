import api from './index'

export const login = (data) => {
  return api.post('/auth/login', data)
}

export const register = (data) => {
  return api.post('/auth/register', data)
}

export const getUserInfo = () => {
  return api.get('/user/info')
}

export const updateUserInfo = (data) => {
  return api.put('/user/info', data)
}

export const changePassword = (data) => {
  return api.put('/user/password', data)
} 