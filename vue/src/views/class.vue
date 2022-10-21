<template>
  <el-row>
    <el-col :span="3">
      <el-button type="warning" @click="this.deleteClasss(this.deleteClassIds)"
      >批量删除
      </el-button
      >
    </el-col>
    <el-col :span="3"
    >
      <el-button type="primary" @click="this.insertDialogVisible = true"
      >添加班级
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
          @selection-change="getClassIds"
      >
        <el-table-column type="selection" width="55"/>
        <el-table-column
            v-for="column in this.tableColumn"
            :key="column.index"
            :label="column.label"
            :prop="column.prop"
            :width="defaultColumn"
            align="center"
        />
        <el-table-column label="操作" align="center" width="200">
          <template #default="scope">
            <el-button size="small" @click="this.updateClassClick(scope.row)"
            >修改
            </el-button
            >
            <el-button
                size="small"
                type="danger"
                @click="deleteOneClass(scope.row.id)"
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
  <!-- 添加一名班级 -->
  <el-dialog
      v-model="insertDialogVisible"
      title="添加班级"
      width="30%"
      center
      draggable
  >
    <el-form
        :model="insertClassForm"
        :rules="classRule"
        ref="insertClass"
        @keyup.enter="this.insertOneClass(this.insertClassForm)"
    >
      <el-form-item prop="className" label="班级名">
        <el-input v-model="this.insertClassForm.className"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="insertDialogVisible = false">取消</el-button>
      <el-button
          type="primary"
          @click="this.insertOneClass(this.insertClassForm)"
      >添加
      </el-button
      >
    </template>
  </el-dialog>
  <!-- 修改班级 -->
  <el-dialog
      v-model="updateDialogVisible"
      title="修改班级"
      width="30%"
      center
      draggable
  >
    <el-form
        :model="updateClassForm"
        :rules="classRule"
        ref="updateClass"
        @keyup.enter="this.updateOneClass(this.updateClassForm)"
    >
      <el-form-item prop="className" label="班级名">
        <el-input v-model="this.updateClassForm.className"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="updateDialogVisible = false">取消</el-button>
      <el-button
          type="primary"
          @click="this.updateOneClass(this.updateClassForm)"
      >修改
      </el-button
      >
    </template>
  </el-dialog>
</template>

<script>
import {
  lookAllClass,
  insertOneClass,
  updateOneClass,
  deleteOneClass,
  lookOneClassByClass,
} from "../api/class";

export default {
  data() {
    return {
      //表格数据
      tableData: [],
      //查询需要的班级Id
      classId: "",
      //添加时需要的表单元素
      insertClassForm: {
        className: "",
      },
      //修改时需要的表单元素
      updateClassForm: {
        className: "",
      },
      //添加对话框是否显示
      insertDialogVisible: false,
      //修改对话框是否显示
      updateDialogVisible: false,
      //批量删除时的id集合
      deleteClassIds: [],
      //表单校验
      classRule: {
        //班级名
        className: [
          {
            required: true,
            message: "班级名不得为空!",
            trigger: "blur",
          },
        ],
      },
      //班级列表
      classMap: [],
      //列宽
      defaultColumn: 250,
      //表格列名集合
      tableColumn: [
        {
          index: 1,
          label: "编号",
          prop: "id",
        },
        {
          index: 2,
          label: "班级名",
          prop: "className",
        },
      ],
    };
  },
  created() {
    this.lookAllClass();
  },
  methods: {
    //查看所有班级逻辑
    lookAllClass() {
      lookAllClass().then((req) => {
        this.tableData = req.data.data;
        console.log(this.tableData);
      });
    },
    //根据班级Id查询班级
    lookOneClassByClass(classId) {
      console.log(classId);
      if (classId === "") {
        this.lookAllClass();
        return;
      }
      lookOneClassByClass(classId).then((req) => {
        this.tableData = req.data.data;
      });
    },
    //添加一名班级
    insertOneClass(obj) {
      console.log(obj);
      this.$refs["insertClass"].validate((valid) => {
        if (valid) {
          insertOneClass(obj).then((req) => {
            if (req.data.success) {
              this.$message({
                type: "success",
                message: "添加班级成功!可继续进行添加!",
                showClose: true,
              });
              this.lookAllClass();
              // this.insertDialogVisible = false;
              // this.insertClassForm = {};
            } else {
              this.$message({
                type: "error",
                message: "添加班级失败!",
                showClose: true,
              });
            }
          });
        }
      });
    },
    //修改一名班级
    updateOneClass(obj) {
      console.log(obj);
      this.$refs["updateClass"].validate((valid) => {
        if (valid) {
          updateOneClass(obj).then((req) => {
            if (req.data.success) {
              this.$message({
                type: "success",
                message: "修改班级成功!",
                showClose: true,
              });
              this.updateDialogVisible = false;
              this.lookAllClass();
            } else {
              this.$message({
                type: "error",
                message: "修改班级失败!",
                showClose: true,
              });
            }
          });
        }
      });
    },
    //删除一条班级信息
    deleteOneClass(ids) {
      var id = [ids];
      this.$confirm("你真的要删除这条班级的信息吗?(真的永久)", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteOneClass(id).then((req) => {
          if (req.data.success) {
            this.$message({
              type: "success",
              message: "删除班级成功!",
              showClose: true,
            });
            this.lookAllClass();
          } else {
            this.$message({
              type: "error",
              message: "删除班级失败!请检查该班级是否存在学生!",
              showClose: true,
            });
          }
        });
      });
    },
    //批量删除
    deleteClasss(ids) {
      console.log(ids);
      this.$confirm(`你真的要删除${ids.length}条班级的信息吗?(真的永久)`, {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteOneClass(ids).then((req) => {
          if (req.data.success) {
            this.$message({
              type: "success",
              message: "删除班级成功!",
              showClose: true,
            });
            this.lookAllClass();
          } else {
            this.$message({
              type: "error",
              message: "删除班级失败!",
              showClose: true,
            });
          }
        });
      });
    },
    //修改班级前的附加操作
    updateClassClick(obj) {
      console.log(obj);
      this.updateClassForm = obj;
      this.updateDialogVisible = true;
    },
    //获取选中的Id值
    getClassIds(val) {
      var ids = [];
      for (let i = 0; i < val.length; i++) {
        ids[i] = val[i].id;
      }
      this.deleteClassIds = ids;
    },
  },
};
</script>

<style>
</style>