<template>
  <div class="table-box">
    <div class="toolbar">
      <el-input class="input-width" v-model="query.title" placeholder="请输入 title 搜索"></el-input>
      <span class="margin-a"></span>
      <el-button type="primary" @click="queryData">搜索</el-button>
      <el-button type="primary" @click="resetQuery">重置</el-button>
      <el-button type="danger" @click="delteRows">批量删除</el-button>
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>
    <el-table
      ref="multipleTableRef"
      :data="tableData.list"
      :header-cell-style="tableHeaderStyle"
      :row-style="tableRowStyle"
      row-key="id"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" reserve-selection width="55"/>
      <el-table-column prop="sort" label="sort" align="center"/>
      <el-table-column prop="id" label="id" align="center"/>
      <el-table-column prop="path" label="path" align="center" show-overflow-tooltip/>
      <el-table-column prop="url" label="url" align="center" show-overflow-tooltip/>
      <el-table-column prop="title" label="title" align="center"/>
      <el-table-column prop="icon" label="icon" align="center">
        <template #default="scope">
          <el-icon>
            <component :is="ElementPlusIconsVue[scope.row.icon]"></component>
          </el-icon>
        </template>
      </el-table-column>
      <el-table-column prop="hidden" label="hidden" align="center"/>
      <el-table-column prop="keepAlive" label="keepAlive" align="center"/>
      <el-table-column prop="permission" label="permission" align="center" show-overflow-tooltip/>
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
    <el-dialog v-model="dialogVisible" :title="dialogTitleComputed" :close-on-click-modal="false"
               :close-on-press-escape="false" :show-close="false" width="30%">
      <el-form :model="formInfo" :rules="formInfoRules" ref="ruleFormRef" label-width="120px">
        <el-form-item label="排序" prop="sort">
          <el-input v-model="formInfo.sort" type="number"/>
        </el-form-item>
        <el-form-item label="父节点id" prop="pid">
          <el-input v-model="formInfo.pid" type="number" disabled/>
        </el-form-item>
        <el-form-item label="父节点id" prop="pid">
          <el-cascader
            v-model="formInfo.pid"
            :options="mOptions.pid"
            :props="propCascader"
            @change="handleChange"
            style="width: 100%"
            clearable/>
        </el-form-item>
        <el-form-item label="path" prop="path">
          <el-input v-model="formInfo.path"/>
        </el-form-item>
        <el-form-item label="url" prop="url">
          <el-input v-model="formInfo.url"/>
        </el-form-item>
        <el-form-item label="name" prop="name">
          <el-input v-model="formInfo.name"/>
        </el-form-item>
        <!--
        <el-form-item label="permission" prop="permission">
          <el-input v-model="formInfo.permission"/>
        </el-form-item>
        -->
        <el-form-item label="permission" prop="permission">
          <el-select v-model="formInfo.permission" multiple placeholder="请选择">
            <el-option v-for="item in mOptions.permission" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="component" prop="component">
          <el-input v-model="formInfo.component"/>
        </el-form-item>
        <el-form-item label="菜单名称" prop="title">
          <el-input v-model="formInfo.title"/>
        </el-form-item>
        <el-form-item label="菜单图标" prop="icon">
          <el-popover placement="top" :width="570" trigger="click" @show="popoverShow">
            <template #reference>
              <el-input v-model="formInfo.icon" placeholder="点击选择图标" readonly>
                <template #prefix>
                  <el-icon class="el-input__icon" size="26">
                    <component :is="ElementPlusIconsVue[formInfo.icon]"></component>
                  </el-icon>
                </template>
              </el-input>
            </template>
            <icon-select ref="iconSelectRef" @selected="iconSelected"/>
          </el-popover>
        </el-form-item>
        <el-form-item label="类型" prop="type">
            <el-radio-group v-model="formInfo.type">
                <el-radio :value="0">目录</el-radio>
                <el-radio :value="1">菜单</el-radio>
                <!--<el-radio :value="2">按钮</el-radio>-->
            </el-radio-group>
        </el-form-item>
        <el-form-item label="是否隐藏菜单" prop="hidden">
          <el-radio-group v-model="formInfo.hidden">
            <el-radio :value="true">true</el-radio>
            <el-radio :value="false">false</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否缓存页面" prop="keepAlive">
          <el-radio-group v-model="formInfo.keepAlive">
            <el-radio :value="true">true</el-radio>
            <el-radio :value="false">false</el-radio>
          </el-radio-group>
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
import useTable from "@/hooks/useTable";
import IconSelect from "@/components/IconSelect.vue";
import {onMounted, reactive, ref, watch, watchEffect} from "vue";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import MXhr from "@/utils/MXhr";
import { cloneDeep } from 'lodash-es';

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
} = useTable("/admin/sysMenuAdd", "/admin/sysMenuDeleteById/", "/admin/sysMenuUpdate", "/admin/sysMenuPage");
formInfo.value = {
  sort: 0,
  pid: 0,
  path: "",
  url: "",
  name: "",
  component: "",
  title: "",
  icon: null,
  hidden: false,
  keepAlive: false,
  permission: ""
};
const formInfoRules = reactive({
  sort: [{required: true, message: "Please input sort", trigger: "blur"}],
  pid: [{required: true, message: "Please input pid", trigger: "blur"}],
  path: [{required: true, message: "Please input path", trigger: "blur"}],
  url: [{required: true, message: "Please input path", trigger: "blur"}],
  name: [{required: true, message: "Please input name", trigger: "blur"}],
  component: [{required: true, message: "Please input component", trigger: "blur"}],
  title: [{required: true, message: "Please input title", trigger: "blur"}],
  icon: [{required: true, message: "Please input icon", trigger: "blur"}],
  hidden: [{required: true, message: "Please input hidden", trigger: "blur"}],
  keepAlive: [{required: true, message: "Please input keepAlive", trigger: "blur"}],
  permission: [{required: true, message: "Please input permission", trigger: "blur"}],
});
const iconSelectRef = ref();
const iconColor = ref("#000000");
let propCascader = {
  children: "children",
  label: "name",
  value: "id",
  checkStrictly: true,
  emitPath: false
}
let mOptions = reactive({
  pid: [],
  permission: [
    { value: 'add', label: '新增' },
    { value: 'edit', label: '编辑' },
    { value: 'del', label: '删除' },
    { value: 'download', label: '导出' },
    { value: 'custom', label: '自定义' },
  ]
})

/*watch(()=> formInfo.value.permission, (val)=> {
  let _val = cloneDeep(val);
  if (_val.length > 0) {
    formInfo.value.permission = _val.split(":");
  }
}, {
  // immediate: true,
  once: true
})*/

onMounted(() => {
  queryData();
  queryMenuTree();
});

function popoverShow() {
  iconSelectRef.value.reset();
}

function iconSelected(name) {
  formInfo.value.icon = name;
}

function tableRowStyle(row) {
  if (row.row.pid === 0) {
    return {
      backgroundColor: '#f0f9eb'
    };
  }
  return "";
}

function queryMenuTree() {
  MXhr.get("/admin/sysMenuTree").then((response) => {
    mOptions.pid = response;
  });
}

function handleChange(value) {
  console.log('handleChange---', value)
}
</script>

<style scoped lang="scss"></style>
