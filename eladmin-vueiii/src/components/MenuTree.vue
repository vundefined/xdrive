<template>
  <div class="toolbar">
    <el-input v-model="title" clearable @clear="queryData" placeholder="请输入title"></el-input>
    <br /><br />
    <el-button type="primary" @click="queryData">搜索</el-button>
    <el-button type="primary" @click="resetSelect">重置</el-button>
    <el-button type="danger" @click="clearSelect">清空</el-button>
    <el-button type="primary" @click="confirmMenu">确定</el-button>
  </div>
  <el-tree
    ref="treeRef"
    :data="menuTree"
    :props="menuTreeProps"
    default-expand-all
    show-checkbox
    node-key="id"
    :filter-node-method="filterNode">
  </el-tree>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage, ElTree} from "element-plus";
import MXhr from "@/utils/MXhr";

const props = defineProps({
  tableRow: {
    type: Object,
    default: {}
  }
});
const title = ref("");
const menuTree = ref([]);
const treeRef = ref();
const menuTreeProps = {
  children: "children",
  label(data, node) {
    return data.meta.title;
  },
};

onMounted(() => {
  queryMenuTree();
});

function queryData() {
  treeRef.value.filter(title.value);
}

function resetSelect() {
  const _tableRow = props.tableRow;
  if (!_tableRow.propertyIsEnumerable("id")) {
    ElMessage.error("请选择角色");
    return;
  }
  if (_tableRow.menuIds === null) {
    clearSelect();
  } else {
    treeRef.value.setCheckedKeys(_tableRow.menuIds, false);
  }
}

function clearSelect() {
  treeRef.value.setCheckedKeys([], false);
}

function confirmMenu() {
  const keys = treeRef.value.getCheckedKeys(false);
  const halfKeys = treeRef.value.getHalfCheckedKeys();
  const _tableRow = props.tableRow;
  if (!_tableRow.propertyIsEnumerable("id")) {
    ElMessage.error("请选择角色");
    return;
  }
  if (keys.length === 0) {
    ElMessage.error("请选择至少一项菜单");
    return;
  }
  _tableRow.menuIds = keys;
  if (halfKeys.length > 0) {
    _tableRow.menuhalfIds = halfKeys;
  }
  MXhr.put("/admin/sysRoleUpdate", _tableRow).then(res => {
    ElMessage.success("更新成功")
  });
}

function filterNode(value, data) {
  if (!value) return true;
  return data.meta.title.includes(value);
}

function queryMenuTree() {
  MXhr.get("/admin/sysMenuTree").then((response) => {
    menuTree.value = response;
  });
}

defineExpose({
  resetSelect,
  clearSelect
});
</script>

<style scoped>
.toolbar {
  margin: 0px 0px 20px 0px;
}

</style>
