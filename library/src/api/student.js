import request from '../utils/request'


export function addStudent(data){
    return request({
        url:'/student/add', 
        method:'post',
        data
    })
}
export function showStudent(currentPage){
    return request({
        url:`/student/${currentPage}`,
        method:'get',
    })
}
export function selectStudent(data){
    return request({
        url:'/student/select',
        method:'post',
        data
        
    })
}
export function updateStudent(data){
    return request({
        url:'/student/update',
        method:'post',
        data
    })
}
export function deleteStudent(data){
    return request({
        url:'/student/delete', 
        method:'delete',
        data
    })
}
