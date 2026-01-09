<template>
  <div class="table-box">
    <div class="toolbar">
      <el-input class="input-width" v-model="query.username" placeholder="请输入 username 搜索"></el-input>
      <span class="margin-a"></span>
      <el-button type="primary" @click="queryData">搜索</el-button>
      <el-button type="primary" @click="resetQuery">重置</el-button>
      <el-button type="danger" @click="delteRows">批量删除</el-button>
      <el-button type="primary" v-permission="false" @click="handleAdd">新增</el-button>
    </div>
    <el-table ref="multipleTableRef" :data="tableData.list" row-key="id" :header-cell-style="tableHeaderStyle"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" reserve-selection width="55"/>
      <el-table-column prop="username" label="username" align="center"/>
      <el-table-column prop="avatar" label="avatar" align="center">
        <template #default="scope">
          <el-image class="row-avatar" :src="fileFilter(scope.row.avatar)"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="mobile" label="mobile" align="center"/>
      <el-table-column prop="email" label="email" align="center"/>
      <el-table-column prop="roleIds" label="roleIds" align="center">
        <template #default="scope">{{scope.row.roleIds}}</template>
      </el-table-column>
      <el-table-column prop="roleIds" label="roleIds" align="center">
        <template #default="scope">{{scope.row.roleNames}}</template>
      </el-table-column>
      <el-table-column label="Operations" align="center" width="200">
        <template #default="scope">
          <el-button text size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button text size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :total="tableData.total"
      layout="sizes, total, prev, pager, next"
      :page-sizes="[10, 20, 50]"
      v-model:current-page="query.curPage"
      v-model:page-size="query.limit"
      @current-change="handleCurrentPage"
      @size-change="handleSizeChange">
    </el-pagination>
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitleComputed"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false" width="30%">
      <el-form :model="formInfo" :rules="formInfoRules" ref="ruleFormRef" label-width="120px">
        <el-form-item label="排序" prop="sort">
          <el-input v-model="formInfo.sort" type="number"/>
        </el-form-item>
        <el-form-item label="username" prop="username">
          <el-input v-model="formInfo.username" :disabled="dialogTitle === 1"/>
        </el-form-item>
        <el-form-item v-show="dialogTitle === 0" label="password" prop="password">
          <el-input v-model="formInfo.password" disabled/>
        </el-form-item>
        <el-form-item label="mobile" prop="mobile">
          <el-input v-model="formInfo.mobile"/>
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="formInfo.email"/>
        </el-form-item>
        <el-form-item label="roleIds" prop="roleIds">
          <el-select v-model="formInfo.roleIds" multiple collapse-tags placeholder="Select">
            <el-option v-for="item in roles" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="avatar" prop="avatar">
          <el-upload
            class="avatar-uploader"
            :action="upAction"
            :headers="upHeader"
            :data="upData"
            :show-file-list="false"
            :on-success="handleUploadSuccess">
            <img v-if="formInfo.avatar" :src="fileFilter(formInfo.avatar)" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogCancel">取消</el-button>
          <el-button type="primary" @click="dialogConfirm">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {
  onMounted,
  reactive,
  ref, watch
} from "vue";
import useTable from "@/hooks/useTable";
import useUpload from "@/hooks/useUpload";
import MXhr from "@/utils/MXhr";
import {Plus} from "@element-plus/icons-vue";
import { cloneDeep } from 'lodash-es';

const {
  query,
  tableData,
  multipleTableRef,
  formInfo,
  ruleFormRef,
  dialogTitle,
  dialogVisible,
  dialogTitleComputed,
  handleAdd,
  handleDelete,
  handleEdit,
  handleSelectionChange,
  handleCurrentPage,
  handleSizeChange,
  resetQuery,
  delteRows,
  dialogConfirm,
  dialogCancel,
  queryData,
  tableHeaderStyle
} = useTable("/admin/sysUserAdd", "/admin/sysUserDeleteById/", "/admin/sysUserUpdate", "/admin/sysUserPage");

formInfo.value = {
  sort: 0,
  username: "",
  password: "123456",
  avatar: "",
  mobile: "",
  email: "",
  roleIds: []
};

const formInfoRules = reactive({
  sort: [{required: true, message: "Please input sort", trigger: "blur"}],
  username: [{required: true, message: "Please input username", trigger: "blur"}],
  roleIds: [{required: true, message: "Please input roleIds", trigger: "blur"}]
});

const {
  fileName,
  upAction,
  upHeader,
  handleUploadSuccess,
  fileFilter
} = useUpload();
const upData = {"category": 'ADMIN_AVATAR'};

watch(fileName, (val) => {
  formInfo.value.avatar = val;
});

const roles = ref({});

onMounted(() => {
  queryData();
  setTimeout(()=> {
    setRole();
  }, 1000)
});

function setRole() {
  MXhr.get("/admin/sysRolePage", {params: {curPage: 1, limit: 10}}).then((response) => {
    roles.value = response.list;
  });
}

function filterRole(ids) {
  let _roles = cloneDeep(roles.value);
}
</script>

<style scoped lang="scss">
.row-avatar {
  width: 40px;
  height: 40px;
}

:deep(.avatar-uploader .el-upload) {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

:deep(.avatar-uploader .el-upload:hover) {
  border-color: var(--el-color-primary);
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
