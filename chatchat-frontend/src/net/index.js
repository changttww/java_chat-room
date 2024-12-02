import axios from "axios";
import {ElMessage} from "element-plus";

const authItemName = "authorize"

let isAuthorized = false;
const defaultError = (error) => {
    console.error(error)
    ElMessage.error('发生了一些错误，请联系管理员')
}

const defaultFailure = (message, status, url) => {
    console.warn(`请求地址: ${url}, 状态码: ${status}, 错误信息: ${message}`)
    ElMessage.warning(message)
}


/*封装axios，此处已经处理response body部分*/
function internalPost(url, data, headers, success, failure, error = defaultError){
    axios.post(url, data, { headers: headers, withCredentials: true}).then(({data}) => {
        if(data.code === 200)
            success(data.data)
        else
            failure(data.message, data.code, url)
    }).catch(err => error(err))
}

function internalGet(url, headers, success, failure, error = defaultError){
    axios.get(url, { headers: headers , withCredentials: true}).then(({data}) => {
        if(data.code === 200)
            success(data.data)
        else
            failure(data.message, data.code, url)
    }).catch(err => error(err))
}

function login(username, password, remember, success, failure = defaultFailure){
    internalPost('/api/auth/login', {
        username: username,
        password: password
    }, {
        'Content-Type': 'application/x-www-form-urlencoded'
    }, (data) => {
        ElMessage.success(`登录成功，欢迎 ${data.username} 来到我们的系统`)
        success(data)
        isAuthorized = true;
    }, failure)
}

function post(url, data, success, failure = defaultFailure) {
    internalPost(url, data, null , success, failure)
}

function logout(success, failure = defaultFailure){
    get('/api/auth/logout', () => {
        ElMessage.success(`退出登录成功，欢迎您再次使用`)
        success()
    }, failure)
}

function get(url, success, failure = defaultFailure) {
    internalGet(url, null, success, failure)
}

function unauthorized() {
    return !isAuthorized;
}

export { post, get, login, logout, unauthorized}