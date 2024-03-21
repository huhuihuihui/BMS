<template>
  <div class="book">
    <h3 class="book-title">书籍总览</h3>
    <div style="margin:10px 0px 0px 0px;">
      <el-input
        placeholder="请输入内容"
        v-model="input1"
        class="input-with-select searchForm"
        border:false
      >
        <el-select
          v-model="select"
          clearable
          slot="prepend"
          placeholder="请选择"
        >
          <el-option label="编号" value="bookId"></el-option>
          <el-option label="书名" value="bookName"></el-option>
          <el-option label="类型" value="type"></el-option>
          <el-option label="作者" value="author"></el-option>
          <el-option label="出版社" value="press"></el-option>
          <el-option label="未还书籍" value="bookStatus"></el-option>
        </el-select>
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="search"
        ></el-button>
      </el-input>
    </div>
    <div class="add"
     style="width: 100%; text-align: right; margin-top: 10px"
     >
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
            <el-form-item label="编号" prop="bookId">
              <el-input v-model="form.bookId"></el-input>
            </el-form-item>
            <el-form-item label="书名" prop="bookName">
              <el-input v-model="form.bookName"></el-input> </el-form-item
            ><el-form-item label="类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择书本类型">
                <el-option label="科幻" value="科幻"></el-option>
                <el-option label="玄幻" value="玄幻"></el-option>
                <el-option label="爱情" value="爱情"></el-option>
                <el-option label="动作" value="动作"></el-option>
                <el-option label="恐怖" value="恐怖"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="作者">
              <el-input v-model="form.author" prop="author"></el-input>
            </el-form-item>

            <el-form-item label="出版社">
              <el-input v-model="form.press" prop="press"></el-input> </el-form-item
            ><el-form-item label="价格" prop="price">
              <el-input
                v-model="form.price"
                oninput="value=value.replace(/[^0-9.]/g,'')"
              ></el-input>
            </el-form-item>
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
        <el-table-column prop="bookId" label="编号" width="120">
        </el-table-column>
        <el-table-column prop="bookName" label="书名" width="140">
        </el-table-column>
        <el-table-column prop="type" label="类型" width="120">
        </el-table-column>
        <el-table-column prop="author" label="作者" width="140">
        </el-table-column>
        <el-table-column prop="price" label="价格" width="120">
        </el-table-column>
        <el-table-column prop="press" label="出版社" width="140"> </el-table-column>
        <el-table-column prop="bookStatus" label="状态">
          <template slot-scope="scope">
        <el-tag
          :type="scope.row.bookStatus === '已借出' ? 'danger' : 'success'"
          disable-transitions>{{scope.row.bookStatus}}</el-tag>
      </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="160">
          <template slot-scope="scope">
            <el-button size="small"
              icon="el-icon-edit" circle
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
    <el-dialog title="编辑记录" :visible.sync="editVisible" width="30%" center>
      <div style="padding: 10px">
        <el-form
          ref="editForm"
          :model="data"
          label-width="80px"
          :rules="rules"
          size="mini"
          center
        >
          <el-form-item label="书名">
            <el-input v-model="data.bookName"></el-input> </el-form-item
          ><el-form-item label="类型">
            <el-select v-model="data.type" placeholder="请选择书本类型">
              <el-option label="科幻" value="科幻"></el-option>
                <el-option label="玄幻" value="玄幻"></el-option>
                <el-option label="爱情" value="爱情"></el-option>
                <el-option label="动作" value="动作"></el-option>
                <el-option label="恐怖" value="恐怖"></el-option>
                <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="data.author"></el-input>
          </el-form-item>
          <el-form-item label="出版社">
            <el-input v-model="data.press"></el-input> </el-form-item
          ><el-form-item label="价格">
            <el-input
              v-model="data.price"
              oninput="value=value.replace(/[^0-9.]/g,'')"
            ></el-input>
          </el-form-item>
          <div style="text-align:center">
            <el-button @click="resetForm('editForm')">重置</el-button>
            <el-button type="primary" @click="submitEdit('editForm')"
              >确认</el-button
            >
          </div>
        </el-form>
      </div>
    </el-dialog>
    <div class="block">
      <el-pagination
      background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total,  prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "Book",
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
      },
      data: {
      },
      rules: {
        bookId: [{ required: true, message: "请填写编号", trigger: "blur" }],
        bookName: [{ required: true, message: "请填写书名", trigger: "blur" }],
        type: [{ required: true, message: "请选择类型", trigger: "change" }],
        price: [{ required: true, message: "请填写价格", trigger: "blur" }],
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
      if(x=='bookStatus')this.data[x]='已借出';
      else this.data[x]=this.input1;
      console.log(this.data);
      this.$store.dispatch("selectBook", this.data);
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.editDataId = row.bookId;
      this.editVisible = true;
      this.data = {};
    },
    submitEdit() {
      this.data.bookId = this.editDataId;
      console.log(this.data);
      this.$store.dispatch("updateBook", this.data).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
      this.editVisible=false;
    },
    handleDelete(index, row) {
      this.$confirm("是否确认删除", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.data.bookId = row.bookId;
        this.$store.dispatch("deleteBook", this.data).then((res)=>{
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
      this.$store.dispatch("showBook", this.currentPage);

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
          this.$store.dispatch("addBook", this.form).then((res)=>{
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
.book{
padding:0px 20px;
box-sizing: border-box;
}
.book-title {
  margin-top: 0;
  margin-bottom: 15;
  color: #19446e;
  font-weight:800;
  font-family: "宋体";
}
div /deep/ .el-select .el-input {
  width: 130px;
}
.searchForm /deep/ .el-input__inner{
  background-color: #fff !important;
  font-weight: normal !important;;
  color:#606266 !important;
  height:40px !important;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
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