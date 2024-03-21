import request from '../utils/request'


export function addRecord(data){
    return request({
        url:`/record/add/${data.bookId}/${data.studentId}`, 
        // url:'/record/add?bookId='+data.bookId+'&&studentId='+data.studentId,
        method:'get',
    })
}
export function showRecord(currentPage){
    return request({
        url:`/record/${currentPage}`,
        method:'get',
    })
}
export function selectRecord(data){
    return request({
        url:'/record/select',
        method:'post',
        data
        
    })
}
export function updateRecord(data){
    console.log(data);
    return request({
        url:'/record/update',
        method:'post',
        data
    })
}
export function deleteRecord(data){
    return request({
        url:'/record/delete', 
        method:'delete',
        data
    })
}
