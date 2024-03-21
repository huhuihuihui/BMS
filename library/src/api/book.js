import request from '../utils/request'


export function addBook(data){
    return request({
        url:'/book/add', 
        method:'post',
        data
    })
}
export function showBook(currentPage){
    return request({
        url:`/book/${currentPage}`,
        method:'get',
    })
}
export function selectBook(data){
    return request({
        url:'/book/select',
        method:'post',
        data
        
    })
}
export function updateBook(data){
    return request({
        url:'/book/update',
        method:'post',
        data
    })
}
export function deleteBook(data){
    return request({
        url:'/book/delete', 
        method:'delete',
        data
    })
}
