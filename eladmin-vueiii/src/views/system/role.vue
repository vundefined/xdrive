<template>
  <div class="role-view">
    <el-row :gutter="10">
      <el-col :span="15">
        <div class="role-table">
          <div class="toolbar">
            <el-input class="input-width" v-model="query.name" placeholder="请输入 name 搜索"></el-input>
            <span class="margin-a"></span>
            <el-button type="primary" @click="queryData">搜索</el-button>
            <el-button type="primary" @click="resetQuery">重置</el-button>
            <el-button type="danger" @click="delteRows">批量删除</el-button>
            <el-button type="primary" @click="handleAdd">新增</el-button>
          </div>
          <el-table ref="multipleTableRef" :data="tableData.list" row-key="id"
                    :header-cell-style="tableHeaderStyle"
                    :row-style="tableRowStyle"
                    @selection-change="handleSelectionChange" @row-click="rowClick">
            <el-table-column type="selection" reserve-selection width="55"/>
            <el-table-column prop="id" label="id"></el-table-column>
            <el-table-column prop="name" label="name"></el-table-column>
            <el-table-column prop="encoding" label="encoding"></el-table-column>
            <el-table-column prop="desc" label="desc"></el-table-column>
            <el-table-column prop="status" label="status" width="140" v-slot="scope">
              <el-switch
                v-model="scope.row.status"
                :active-text="scope.row.status ? '启用' : '禁用'"
                :active-value="true"
                :inactive-value="false"
                :loading="loadingStatus"
                @change="changeStatus($event, scope.row)">
              </el-switch>
            </el-table-column>
            <el-table-column label="Operations" width="140">
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
        </div>
      </el-col>
      <el-col :span="9">
        <div class="menu-tree">
          <MenuTree ref="menuTreeRef" :table-row="tableRow"></MenuTree>
        </div>
      </el-col>
    </el-row>
    <el-dialog v-model="dialogVisible" :title="dialogTitleComputed" :close-on-click-modal="false"
               :close-on-press-escape="false" :show-close="false" width="30%">
      <el-form :model="formInfo" :rules="formInfoRules" ref="ruleFormRef" label-width="120px">
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="formInfo.name"/>
        </el-form-item>
        <el-form-item label="角色编码" prop="encoding">
          <el-input v-model="formInfo.encoding"/>
        </el-form-item>
        <el-form-item label="角色描述" prop="desc">
          <el-input v-model="formInfo.desc"/>
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
  nextTick,
  onMounted,
  reactive,
  ref
} from "vue";
import MXhr from "@/utils/MXhr";
import {ElMessage} from "element-plus";
import MenuTree from "@/components/MenuTree.vue";
import useTable from "@/hooks/useTable";

const menuTreeRef = ref();
const loadingStatus = ref(false);
const tableRow = ref({
  id: -1,
  name: "",
  encoding: "",
  desc: "",
  menuIds: [],
  status: false,
  addTime: ""
});
const {
  query,
  tableData,
  multipleTableRef,
  formInfo,
  ruleFormRef,
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
} = useTable("/admin/sysRoleAdd", "/admin/sysRoleDeleteById/", "/admin/sysRoleUpdate", "/admin/sysRolePage");

formInfo.value = {
  name: "",
  encoding: "",
  desc: "",
  menuIds: [],
  menuhalfIds: [],
  status: true
};

const formInfoRules = reactive({
  name: [{required: true, message: "Please input name", trigger: "blur"}],
  encoding: [{required: true, message: "Please input encoding", trigger: "blur"}],
  desc: [{required: true, message: "Please input desc", trigger: "blur"}]
});

onMounted(() => {
  queryData();
});

async function rowClick(row) {
  tableRow.value = row;
  await nextTick();
  menuTreeRef.value.resetSelect();
}

function changeStatus(event, row) {
  loadingStatus.value = true;
  row.status = event;
  MXhr.put("/admin/sysRoleUpdate", row).then((response) => {
    ElMessage.success("更新成功");
    loadingStatus.value = false;
  });
}

function tableRowStyle(row) {
  if (row.id === tableRow.value.id) {
    return {
      backgroundColor: '#f0f9eb'
    };
  }
  return "";
}
</script>

<style scoped lang="scss">
.menu-tree {
  padding: 20px 10px 10px 10px;
  border: 1px solid #eeeeee;
  margin: 20px 0px 0px 0px;
}
</style>
