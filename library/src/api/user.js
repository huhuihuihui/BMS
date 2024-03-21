import request from '../utils/request'

//user
export function getUser(data){
    return request({
        url:'/user/login', //`/book/{${data.currentPage}}`,
        method:'post',
        data
    })
}
export function addUser(data){
    return request({
        url:'/user/sign', //`/book/{${data.currentPage}}`,
        method:'post',
        data
    })
}
export function deleteUser(data){
    return request({
        url:'/user/delete', //`/book/{${data.currentPage}}`,
        method:'delete',
        data
    })
}
export function updateUser(data){
    return request({
        url:'/user/update', //`/book/{${data.currentPage}}`,
        method:'post',
        data
    })
}