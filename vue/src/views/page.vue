<template>
  <el-row>
    <el-col :span="3">
      <el-menu default-active="1" :collapse="collapse">
        <el-menu-item
            v-for="item in menu"
            :key="item.index"
            :index="item.index"
            @click="this.getPath(item.path)"
        >
          <span>{{ item.name }}</span>
        </el-menu-item>
      </el-menu>
    </el-col
    >
    <el-col :span="21">
      <el-container>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      menu: [
        {
          index: 1,
          path: "student",
          name: "学生管理",
        },
        {
          index: 2,
          path: "class",
          name: "班级管理",
        },
        {
          index: 3,
          path: "user",
          name: "用户管理",
        },
        {
          index: 4,
          path: "login",
          name: "退出登录",
        },
      ],
    };
  },
  created() {
  },
  methods: {
    getPath(name) {
      if (name === "login") {
        this.$confirm(`你真的要退出登录吗?退出后需要重新登录才能使用此系统`, {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          sessionStorage.clear();
          this.$router.push({name: name});
        });
      } else {
        this.$router.push({name: name});
      }
    },
  },
};
</script>

<style>
</style>