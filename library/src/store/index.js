import Vue from 'vue'
import Vuex from 'vuex'
import { Message } from 'element-ui';
import router from '../router'

import {addBook} from '@/api/book'
import {showBook} from '@/api/book'
import {selectBook} from '@/api/book'
import {updateBook} from '@/api/book'
import {deleteBook} from '@/api/book'

import {addStudent} from '@/api/student'
import {showStudent} from '@/api/student'
import {selectStudent} from '@/api/student'
import {updateStudent} from '@/api/student'
import {deleteStudent} from '@/api/student'

import {addRecord} from '@/api/record'
import {showRecord} from '@/api/record'
import {selectRecord} from '@/api/record'
import {updateRecord} from '@/api/record'
import {deleteRecord} from '@/api/record'

import {getUser} from '@/api/user'
import {addUser} from '@/api/user'
import {deleteUser} from '@/api/user'
import {updateUser} from '@/api/user'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    tableData:[],
    total:0,
    user:{
      userId:0,
      userName:'游客',
      userMail:"",
      password:""
    },
  },
  getters: {
  },
  mutations: {
    FILLTABLEDATA(state,data){
      //console.log(data);
      state.tableData=data.tableData;
      state.total=data.total;
    },
    LOGIN(state,data){
      //console.log(data);
      state.user=data;
      console.log(state.user);
    },
  },
  actions: {
    //user
    getUser({commit},user){
      getUser(user).then(res=>{
        Message({
          message:res.message,
          type:'success'
        });
        router.push({path:'/home'});
        console.log(res);
        commit('LOGIN',res.data.user)
      })
      .catch(error=>{
      })
  },
    addUser({commit},user){
      addUser(user).then(res=>{
        Message({
          message:res.message+'，为您跳转至登录界面',
          type:'success'
        });
        router.push({path:'/'});
      }).catch((error)=>{
        Message({
          message:error.message,
          type:'error'
        })
      })
    },
    updateUser({commit},data){
      updateUser(data).then(res=>{
        Message({
          message:res.message+'，下次登录生效',
          type:'success'
        } )
      }).catch((error)=>{
        Message({
          message:error.message,
          type:'error'
        })
      })
    },
    deleteUser({commit},data){
      deleteUser(data).then(res=>{
        Message({
          message:res.message+'，退出登录',
          type:'success'
        } )
        router.push({path:'/'});
      }).catch((error)=>{
        Message({
          message:error.message,
          type:'error'
        })
      })
    },
    //book
    addBook({commit},data){
      return new Promise((resolve,reject)=>{
          addBook(data).then(res=>{
            resolve(res)
                Message({
                  message:res.message,
                  type:'success'
                })
              })
              .catch((error)=>{
                reject(error)
                Message({
                  message:'书籍编号重复',
                  type:'error'
                } )
              });
      })
  },

    showBook({commit},currentPage){
      showBook(currentPage).then(res=>{
        commit('FILLTABLEDATA',{tableData:res.data.books,total:res.data.total})
      })
    },
    selectBook({commit},data){
      selectBook(data).then(res=>{
        console.log(res),
        commit('FILLTABLEDATA',{tableData:res.data.book,total:res.data.total})
      })
    },
    updateBook({commit},data){
      return new Promise((resolve,reject)=>{
      updateBook(data).then(res=>{
        resolve(res);
        Message({
          message:res.message,
          type:'success'
        } )
      }).catch((error)=>{
        reject(error)
      })
    })
    },
    
    deleteBook({commit},data){
      return new Promise((resolve,reject)=>{
      deleteBook(data).then(res=>{
        resolve(res)
        Message({
          message:res.message,
          type:'success'
        } )
      }).catch((error)=>{
        reject(error)
      })
    })
    },
    //student
    addStudent({commit},data){
      return new Promise((resolve,reject)=>{
        addStudent(data).then(res=>{
          resolve(res)
          Message({
            message:res.message,
            type:'success'
          } )
        }).catch((error)=>{
          reject(error)
        })
      })
    },
    showStudent({commit},currentPage){
      showStudent(currentPage).then(res=>{
        console.log(res);
        commit('FILLTABLEDATA',{tableData:res.data.students,total:res.data.total})
      })
    },
    selectStudent({commit},data){
      selectStudent(data).then(res=>{
        console.log(res);
        commit('FILLTABLEDATA',{tableData:res.data.students,total:res.data.total})
      })
    },
    updateStudent({commit},data){
      return new Promise((resolve,reject)=>{
        updateStudent(data).then(res=>{
          resolve(res)
          Message({
            message:res.message,
            type:'success'
          } )
        }).catch((error)=>{
          reject(error)
        })
      })
    },
    deleteStudent({commit},data){
      return new Promise((resolve,reject)=>{
        deleteStudent(data).then(res=>{
          resolve(res)
          Message({
            message:res.message,
            type:'success'
          } )
        }).catch((error)=>{
          reject(error)
        })
      })
    },
    //record
    addRecord({commit},data){
      return new Promise((resolve,reject)=>{
        addRecord(data).then(res=>{
          resolve(res)
          Message({
            message:res.message,
            type:'success'
          } )
        }).catch((error)=>{
          reject(error)
        })
      })
    },
    showRecord({commit},currentPage){
      showRecord(currentPage).then(res=>{
        commit('FILLTABLEDATA',{tableData:res.data.records,total:res.data.total})
      })
    },
    selectRecord({commit},data){
      selectRecord(data).then(res=>{
        console.log(res),
        commit('FILLTABLEDATA',{tableData:res.data.records,total:res.data.total})
      })
    },
    updateRecord({commit},data){
      return new Promise((resolve,reject)=>{
        updateRecord(data).then(res=>{
          resolve(res)
          Message({
            message:res.message,
            type:'success'
          } )
        }).catch((error)=>{
          reject(error)
        })
      })
    },
    deleteRecord({commit},data){
      return new Promise((resolve,reject)=>{
        deleteRecord(data).then(res=>{
          resolve(res)
          Message({
            message:res.message,
            type:'success'
          } )
        }).catch((error)=>{
          reject(error)
        })
      })
    },
    
  },
  modules: {
  }
})
