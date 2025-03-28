import { login, register, getUserInfo } from '@/api/user'

export default {
  namespaced: true,
  state: {
    token: localStorage.getItem('token') || '',
    userInfo: null
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    SET_USER_INFO(state, userInfo) {
      state.userInfo = userInfo
    },
    CLEAR_USER(state) {
      state.token = ''
      state.userInfo = null
      localStorage.removeItem('token')
    }
  },
  actions: {
    async login({ commit }, loginData) {
      try {
        const { data } = await login(loginData)
        commit('SET_TOKEN', data.token)
        return data
      } catch (error) {
        throw error
      }
    },
    async register({ commit }, registerData) {
      try {
        const { data } = await register(registerData)
        return data
      } catch (error) {
        throw error
      }
    },
    async getUserInfo({ commit }) {
      try {
        const { data } = await getUserInfo()
        commit('SET_USER_INFO', data)
        return data
      } catch (error) {
        throw error
      }
    },
    logout({ commit }) {
      commit('CLEAR_USER')
    }
  },
  getters: {
    isAuthenticated: state => !!state.token,
    userInfo: state => state.userInfo
  }
} 