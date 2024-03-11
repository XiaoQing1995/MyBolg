import axios from 'axios'
import { whenErrorCheckHttpStatus, whenErrorCheckHttpStatusForFrontDeskUse } from '@/plugin/httpErrorPlugin'
const host = import.meta.env.VITE_API_JAVAURL

async function login(path, userDetails) {
  try {
    const response = await axios.post(`${host}${path}`, userDetails)
    setLoginStore({
      isLogin: true,
      token: response.data.token
    })
    return response
  } catch (error) {
    console.error('api error')
    cleanLoginStore()
    throw error
  }
}

async function apiGet(path, router) {
  try {
    checkToken(router)
    const token = localStorage.getItem('token')
    const headers = { Authorization: `Bearer ${token}` }
    const response = await axios.get(`${host}${path}`, { headers })
    // if (token != null) {
    //   const headers = { Authorization: `Bearer ${token}` }
    //   response = await axios.get(`${host}${path}`, { headers })
    // } else {
    //   response = await axios.get(`${host}${path}`)
    // }
    return response
  } catch (error) {
    console.error('api error')
    whenErrorCheckHttpStatus(error, router)
  }
}

async function apiGetForFrontDeskUse(path, router) {
  try {
    const response = await axios.get(`${host}${path}`)
    return response
  } catch (error) {
    whenErrorCheckHttpStatusForFrontDeskUse(error, router)    
  }
}

async function apiPost(path, item, router) {
    try {
        const token = localStorage.getItem('token')
        const headers = { Authorization: `Bearer ${token}` }
        const response = await axios.post(`${host}${path}`, item , { headers })
        return response
    } catch (error) {
        console.error('api error')
        whenErrorCheckHttpStatus(error, router)
    }
}

async function apiUpdate(path, item, router) {
    try {
        const token = localStorage.getItem('token')
        const headers = { Authorization: `Bearer ${token}` }
        const response = await axios.put(`${host}${path}`, item , { headers })
        return response
    } catch (error) {
        console.error('api error')
        whenErrorCheckHttpStatus(error, router)
    }
}

async function apiDelete(path, router){
    try {
        const token = localStorage.getItem('token')
        const headers = { Authorization: `Bearer ${token}` }
        const response = await axios.delete(`${host}${path}`, { headers })
        return response
    } catch (error) {
        console.error('api error')
        whenErrorCheckHttpStatus(error, router)
    }
}

function setLoginStore(options) {
  window.localStorage.setItem('isLogin', options.isLogin)
  window.localStorage.setItem('token', options.token)
}

function cleanLoginStore() {
  window.localStorage.clear()
}

function checkToken(router){
  const token = localStorage.getItem("token")
  if (token == null) {
    router.push({path: "/login"})
  }
}

export { login, apiGet, apiPost, apiUpdate, apiDelete, cleanLoginStore, apiGetForFrontDeskUse }
