<template>
  <div class="student">
    <h3 class="student-title">学生总览</h3>
    <div style="margin: 10px 0 0 0">
      <el-input
        placeholder="请输入内容"
        v-model="input1"
        class="input-with-select searchForm"
      >
        <el-select
          v-model="select"
          clearable
          slot="prepend"
          placeholder="请选择"
        >
          <el-option label="学号" value="studentId"></el-option>
          <el-option label="姓名" value="studentName"></el-option>
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
            <el-form-item label="学号" prop="studentId">
              <el-input v-model="form.studentId"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="studentName">
              <el-input v-model="form.studentName"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="form.sex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="电话">
              <el-input
                v-model="form.telephone"
                prop="telephone"
              ></el-input> </el-form-item
            ><el-form-item label="邮箱" prop="studentMail">
              <el-input v-model="form.studentMail"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address"></el-input>
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
        <el-table-column prop="studentId" label="学号" width="120">
        </el-table-column>
        <el-table-column prop="studentName" label="姓名" width="120">
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="120"> </el-table-column>
        <el-table-column prop="telephone" label="电话" width="120">
        </el-table-column>
        <el-table-column prop="studentMail" label="邮箱" width="170">
        </el-table-column>
        <el-table-column prop="address" label="地址" width="120"> </el-table-column>
        <el-table-column prop="studentStatus" label="状态">
          <template slot-scope="scope">
        <el-tag
          :type="scope.row.studentStatus === '冻结' ? 'danger' : 'success'"
          disable-transitions>{{scope.row.studentStatus}}</el-tag>
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
          <el-form-item label="姓名">
            <el-input v-model="data.studentName"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="data.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="data.telephone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="data.studentMail"></el-input> </el-form-item
          ><el-form-item label="地址">
            <el-input v-model="data.address"></el-input>
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
  name: "student",
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
        studentId: "",
        studentName: "",
        sex: "",
        telephone: "",
        studentMail: "",
        address: "",
        studentStatus: "",
      },
      data: {
        studentId: "",
        studentName: "",
        sex: "",
        telephone: "",
        studentMail: "",
        address: "",
        studentStatus: "",
      },
      rules: {
        studentId: [{ required: true, message: "请填写学号", trigger: "blur" }],
        studentName: [
          { required: true, message: "请填写姓名", trigger: "blur" },
        ],
      },
    };
  },
  computed: {
    tableData() {
      return this.$store.state.tableData;
    },
    total() {
      return this.$store.state.total;
    },
  },
  methods: {
    search() {
      this.data = {};
      let x = this.select;
      console.log(x);
      this.data[x] = this.input1;
      console.log(this.data);
      this.$store.dispatch("selectStudent", this.data);
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.editDataId = row.studentId;
      this.editVisible = true;
      this.data = {};
    },
    submitEdit() {
      this.data.studentId = this.editDataId;
      console.log(this.data);
      this.$store.dispatch("updateStudent", this.data).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
      this.editVisible = false;
    },
    handleDelete(index, row) {
      this.$confirm("是否确认删除", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.data.studentId = row.studentId;
        this.$store.dispatch("deleteStudent", this.data).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
        this.data = {};
      }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        })
    },
    getTableData() {
      this.$store.dispatch("showStudent", this.currentPage);
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
          this.$store.dispatch("addStudent", this.form).then((res)=>{
            this.getTableData();
          }).catch(err=>{});
          this.form = {};
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
.student{
padding:0px 20px;
box-sizing: border-box;
}
.student-title {
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