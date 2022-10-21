<template>
  <el-row>
    <el-col :span="3">
      <el-button
          type="warning"
          @click="this.deleteStudents(this.deleteStudentIds)"
      >批量删除
      </el-button
      >
    </el-col>
    <el-col :span="5"
    >
      <el-select v-model="classId" clearable placeholder="选择班级以查询">
        <el-option
            v-for="item in classMap"
            :key="item.key"
            :label="item.label"
            :value="item.value"/>
      </el-select
      >
    </el-col>
    <el-col :span="3"
    >
      <el-button
          type="primary"
          @click="this.lookOneStudentByClass(this.classId)"
      >查询
      </el-button
      >
    </el-col
    >
    <el-col :span="3"
    >
      <el-button type="primary" @click="this.insertDialogVisible = true"
      >添加学生
      </el-button
      >
    </el-col
    >
  </el-row>
  <el-row>
    <el-col>
      <el-table
          height="500"
          :data="tableData"
          border
          @selection-change="getStudentIds"
      >
        <el-table-column type="selection" width="55"/>
        <el-table-column
            v-for="column in this.tableColumn"
            :key="column.index"
            :label="column.label"
            :prop="column.prop"
            :width="defaultColumn"
            align="center"
        >
          <!-- 性别列特殊处理 -->
          <template #default="scope" v-if="column.prop === 'sex'">
            {{ scope.row.sex === 0 ? "女" : "男" }}
          </template
          >
          <!-- 年龄列特殊处理 -->
          <template #default="scope" v-else-if="column.prop === 'age'">
            {{ scope.row.age }}岁
          </template
          >
        </el-table-column
        >
        <el-table-column label="操作" align="center" width="200">
          <template #default="scope">
            <el-button size="small" @click="this.updateStudentClick(scope.row)"
            >修改
            </el-button
            >
            <el-button
                size="small"
                type="danger"
                @click="deleteOneStudent(scope.row.id)"
            >删除
            </el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>
  <el-row>
    <el-col></el-col>
  </el-row>
  <!-- 添加一名学生 -->
  <el-dialog
      v-model="insertDialogVisible"
      title="添加学生"
      width="30%"
      center
      draggable
  >
    <el-form
        :model="insertStudentForm"
        :rules="studentRule"
        ref="insertStudent"
        @keyup.enter="this.insertOneStudent(this.insertStudentForm)"
    >
      <el-form-item prop="name" label="学生姓名">
        <el-input v-model="this.insertStudentForm.name"></el-input>
      </el-form-item>
      <el-form-item prop="age" label="学生年龄">
        <el-input-number
            v-model="this.insertStudentForm.age"
            :min="5"
            :max="35"
        />
      </el-form-item>
      <el-form-item prop="sex" label="学生性别">
        <el-radio-group v-model="this.insertStudentForm.sex">
          <el-radio :label="1" size="large" border>男</el-radio>
          <el-radio :label="0" size="large" border>女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="address" label="学生地址">
        <el-input v-model="this.insertStudentForm.address"></el-input>
      </el-form-item>
      <el-form-item prop="classId" label="学生班级"
      >
        <el-select
            v-model="this.insertStudentForm.classId"
            placeholder="选择班级"
            clearable
        >
          <el-option
              v-for="item in classMap"
              :key="item.key"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="insertDialogVisible = false">取消</el-button>
      <el-button
          type="primary"
          @click="this.insertOneStudent(this.insertStudentForm)"
      >添加
      </el-button
      >
    </template>
  </el-dialog>
  <!-- 修改学生 -->
  <el-dialog
      v-model="updateDialogVisible"
      title="修改学生"
      width="30%"
      center
      draggable
  >
    <el-form
        :model="updateStudentForm"
        :rules="studentRule"
        ref="updateStudent"
        @keyup.enter="this.updateOneStudent(this.updateStudentForm)"
    >
      <el-form-item prop="name" label="学生姓名">
        <el-input v-model="this.updateStudentForm.name"></el-input>
      </el-form-item>
      <el-form-item prop="age" label="学生年龄">
        <el-input-number
            v-model="this.updateStudentForm.age"
            :min="5"
            :max="35"
        />
      </el-form-item>
      <el-form-item prop="sex" label="学生性别">
        <el-radio-group v-model="this.updateStudentForm.sex">
          <el-radio :label="1" size="large" border>男</el-radio>
          <el-radio :label="0" size="large" border>女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="address" label="学生地址">
        <el-input v-model="this.updateStudentForm.address"></el-input>
      </el-form-item>
      <el-form-item prop="classId" label="学生班级"
      >
        <el-select
            v-model="this.updateStudentForm.classId"
            placeholder="选择班级"
            clearable
        >
          <el-option
              v-for="item in classMap"
              :key="item.key"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="updateDialogVisible = false">取消</el-button>
      <el-button
          type="primary"
          @click="this.updateOneStudent(this.updateStudentForm)"
      >修改
      </el-button
      >
    </template>
  </el-dialog>
</template>

<script>
import {
  lookAllStudent,
  insertOneStudent,
  updateOneStudent,
  deleteOneStudent,
  lookOneStudentByClass,
} from "../api/student";
import {lookAllClass} from "../api/class";

export default {
  data() {
    return {
      //表格数据
      tableData: [],
      //查询需要的班级Id
      classId: "",
      //添加时需要的表单元素
      insertStudentForm: {
        name: "",
        age: 18,
        sex: 1,
        address: "",
        classId: "",
      },
      //修改时需要的表单元素
      updateStudentForm: {
        name: "",
        age: "",
        sex: 0,
        address: "",
        classId: "",
      },
      //添加对话框是否显示
      insertDialogVisible: false,
      //修改对话框是否显示
      updateDialogVisible: false,
      //批量删除时的id集合
      deleteStudentIds: [],
      //表单校验
      studentRule: {
        //姓名
        name: [
          {
            required: true,
            message: "学生姓名不得为空!",
            trigger: "blur",
          },
        ],
        //年龄
        age: [
          {
            required: true,
            message: "年龄不得为空",
            trigger: "change",
          },
        ],
        //性别
        sex: [
          {
            required: true,
            message: "性别不得为空!",
            trigger: "change",
          },
        ],
        //地址
        address: [
          {
            required: true,
            message: "地址不得为空!",
            trigger: "blur",
          },
        ],
        //班级Id
        classId: [
          {
            required: true,
            message: "班级不得为空!",
            trigger: "change",
          },
        ],
      },
      //班级列表
      classMap: [],
      //列宽
      defaultColumn: 160,
      //表格列名集合
      tableColumn: [
        {
          index: 1,
          label: "编号",
          prop: "id",
        },
        {
          index: 2,
          label: "学生姓名",
          prop: "name",
        },
        {
          index: 3,
          label: "学生年龄",
          prop: "age",
        },
        {
          index: 4,
          label: "学生性别",
          prop: "sex",
        },
        {
          index: 5,
          label: "学生地址",
          prop: "address",
        },
      ],
    };
  },
  created() {
    this.lookAllStudent();
    this.lookAllClass();
  },
  methods: {
    //查看所有学生逻辑
    lookAllStudent() {
      lookAllStudent().then((req) => {
        this.tableData = req.data.data;
        console.log(this.tableData);
      });
    },
    //获取所有班级
    lookAllClass() {
      lookAllClass().then((req) => {
        var classList = req.data.data;
        for (let i = 0; i < classList.length; i++) {
          var a = {
            key: i,
            value: classList[i].id,
            label: classList[i].className,
          };
          this.classMap.push(a);
        }
      });
    },
    //根据班级Id查询学生
    lookOneStudentByClass(classId) {
      console.log(classId);
      if (classId === "") {
        this.lookAllStudent();
        return;
      }
      lookOneStudentByClass(classId).then((req) => {
        this.tableData = req.data.data;
      });
    },
    //添加一名学生
    insertOneStudent(obj) {
      console.log(obj);
      this.$refs["insertStudent"].validate((valid) => {
        if (valid) {
          insertOneStudent(obj).then((req) => {
            if (req.data.success) {
              this.$message({
                type: "success",
                message: "添加学生成功!可继续进行添加!",
                showClose: true,
              });
              this.lookAllStudent();
              // this.insertDialogVisible = false;
              // this.insertStudentForm = {};
            } else {
              this.$message({
                type: "error",
                message: "添加学生失败!",
                showClose: true,
              });
            }
          });
        }
      });
    },
    //修改一名学生
    updateOneStudent(obj) {
      console.log(obj);
      this.$refs["updateStudent"].validate((valid) => {
        if (valid) {
          updateOneStudent(obj).then((req) => {
            if (req.data.success) {
              this.$message({
                type: "success",
                message: "修改学生成功!",
                showClose: true,
              });
              this.updateDialogVisible = false;
              this.lookAllStudent();
            } else {
              this.$message({
                type: "error",
                message: "修改学生失败!",
                showClose: true,
              });
            }
          });
        }
      });
    },
    //删除一条学生信息
    deleteOneStudent(ids) {
      var id = [ids];
      this.$confirm("你真的要删除这条学生的信息吗?(真的永久)", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteOneStudent(id).then((req) => {
          if (req.data.success) {
            this.$message({
              type: "success",
              message: "删除学生成功!",
              showClose: true,
            });
            this.lookAllStudent();
          } else {
            this.$message({
              type: "error",
              message: "删除学生失败!",
              showClose: true,
            });
          }
        });
      });
    },
    //批量删除
    deleteStudents(ids) {
      console.log(ids);
      this.$confirm(`你真的要删除${ids.length}条学生的信息吗?(真的永久)`, {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteOneStudent(ids).then((req) => {
          if (req.data.success) {
            this.$message({
              type: "success",
              message: "删除学生成功!",
              showClose: true,
            });
            this.lookAllStudent();
          } else {
            this.$message({
              type: "error",
              message: "删除学生失败!",
              showClose: true,
            });
          }
        });
      });
    },
    //修改学生前的附加操作
    updateStudentClick(obj) {
      console.log(obj);
      this.updateStudentForm = obj;
      this.updateDialogVisible = true;
    },
    //获取选中的Id值
    getStudentIds(val) {
      var ids = [];
      for (let i = 0; i < val.length; i++) {
        ids[i] = val[i].id;
      }
      this.deleteStudentIds = ids;
    },
  },
};
</script>

<style>
</style>