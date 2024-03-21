<template>
    <div class="record">
      <h3 class="record-title">借阅记录</h3>
      <div style="margin: 10px 0 0 0">
        <el-input
          placeholder="请输入内容"
          v-model="input1"
          class="input-with-select  searchForm"
        >
          <el-select
            v-model="select"
            clearable
            slot="prepend"
            placeholder="请选择"
          >
            <el-option label="学生学号" value="studentId"></el-option>
            <el-option label="书籍编号" value="bookId"></el-option>
            <el-option label="未还记录" value="recordStatus"></el-option>
          </el-select>
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="search"
          ></el-button>
        </el-input>
      </div>
      <div class="add" style="width: 100%; text-align: right; margin-top: 10px">
        <el-button type="primary" plain size="small" @click="addVisible = true"
          >添加记录</el-button
        >
        <el-dialog title="添加记录" :visible.sync="addVisible" width="30%" center>
          <div style="padding: 10px">
            <el-form
              ref="form"
              :model="form"
              label-width="80px"
              :rules="rules"
              size="mini"
              center
            >
              <el-form-item label="学生学号" prop="studentId">
                <el-input v-model="form.studentId"></el-input>
              </el-form-item>
              <el-form-item label="书籍编号" prop="bookId">
                <el-input v-model="form.bookId"></el-input> </el-form-item
              >
              <div style="text-align:center">
                <el-button @click="resetForm('form')">重置</el-button>
                <el-button type="primary" @click="submitForm('form')"
                  >添加</el-button
                >
              </div>
            </el-form>
          </div>
        </el-dialog>
      </div>
      <div>
        <el-table
          class="table"
          :data="tableData"
          stripe
          border:true
          style="width: 100%"
          max-height="400"
          :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
        >
          <el-table-column prop="studentId" label="学生学号" width="120">
          </el-table-column>
          <el-table-column prop="studentName" label="学生名字" width="120">
          </el-table-column>
          <el-table-column prop="bookId" label="书籍编号" width="120">
          </el-table-column>
          <el-table-column prop="bookName" label="书籍名字" width="120">
          </el-table-column>
          <el-table-column prop="btime" label="借出时间" width="120">
          </el-table-column>
          <el-table-column prop="rtime" label="归还时间" width="120">
          </el-table-column>
          <el-table-column prop="deadline" label="截止日期" width="120"> </el-table-column>
          <el-table-column prop="recordStatus" label="状态"> 
            <template slot-scope="scope">
        <el-tag
          :type="scope.row.recordStatus === '未还' ? 'danger' : 'success'"
          disable-transitions>{{scope.row.recordStatus}}</el-tag>
      </template>
          </el-table-column>
  
          <el-table-column label="操作" fixed="right" width="160">
            <template slot-scope="scope">
            <el-button size="small"
              icon="el-icon-check" circle
              type="primary"
              plain
               @click="handleEdit(scope.$index, scope.row)"
              ></el-button
            >
            <span style="margin-left:10px;"></span>
            <el-button
              size="small"
              type="danger"
              icon="el-icon-delete" circle 
              @click="handleDelete(scope.$index, scope.row)"
              ></el-button
            >
          </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pageSize"
          layout="total,  prev, pager, next, jumper"
          :total="total"
          background
        >
        </el-pagination>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "record",
    data() {
      return {
        editVisible: false,
        addVisible: false,
        input1: "",
        select: "",
        currentPage: 1,
        pageSize: 6,
        editDataId: "",
        form: {
          recordId:"",
          studentId:"",
          studentName:"",
          bookId:"",
          bookName:"",
          recordStatus:"",
          btime:"",
          rtime:"",
          deadline:""
        },
        data: {
        recordId:"",
          studentId:"",
          studentName:"",
          bookId:"",
          bookName:"",
          recordStatus:"",
          btime:"",
          rtime:"",
          deadline:""
        },
        rules: {
          studentId: [{ required: true, message: "请填写学生学号", trigger: "blur" }],
          bookId: [{ required: true, message: "请填写书籍编号", trigger: "blur" }],
        },
      };
    },
    computed: {
      tableData() {
        return this.$store.state.tableData;
      },
      total(){
        return this.$store.state.total;
      }
    },
    methods: {
      search() {
        this.data = {};
        let x = this.select;
        console.log(x);
        if(x=='recordStatus')this.data[x] = '未还';
        else this.data[x] = this.input1;
        console.log(this.data);
        this.$store.dispatch("selectRecord", this.data);
      },
      handleEdit(index, row) {
        this.data.recordId = row.recordId;
        console.log(this.data);
        this.$store.dispatch("updateRecord", this.data).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
        this.data = {};
      },
      handleDelete(index, row) {
        this.$confirm("是否确认删除", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          this.data.recordId = row.recordId;
          this.$store.dispatch("deleteRecord", this.data).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
          this.data = {};
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      getTableData() {
        this.$store.dispatch("showRecord", this.currentPage);
  
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.getTableData();
      },
      handleSizeChange(val) {
        this.pageSize = val;
        this.currentPage = 1;
        this.getTableData();
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.form);
            this.$store.dispatch("addRecord", {bookId:this.form.bookId,studentId:this.form.studentId}).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
            this.form={};
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    },
    created() {
      this.getTableData();
      console.log(this.tableData);
    },
  };
  </script>
  
  <style scoped>
  .record{
padding:0px 20px;
box-sizing: border-box;
}
  .record-title {
    margin-top: 0;
    margin-bottom: 15;
    color: #19446e;
  font-weight:800;
  font-family: "宋体";
  }
  div /deep/ .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .searchForm /deep/ .el-input__inner{
  background-color: #fff !important;
  color:#58626b !important;
  height:40px !important;
}
  .block {
    text-align: center;
    padding: 20px;
  }
  .el-table__body-wrapper::-webkit-scrollbar {
    /*width: 0;宽度为0隐藏*/
    width: 0px;
  }
  </style>