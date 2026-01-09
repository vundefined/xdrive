import {computed, reactive, ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus/es";
import { cloneDeep } from 'lodash-es';
import MXhr from "@/utils/MXhr";

export default function useTable(addApi, deleteApi, updateApi, pageApi) {
  const query = ref({
    curPage: 1,
    limit: 10
  });
  const tableData = reactive({
    list: [],
    total: 0
  });
  let multipleSelection = [];
  const multipleTableRef = ref();
  const formInfo = ref({});
  const ruleFormRef = ref();
  const dialogTitle = ref(0);
  const dialogVisible = ref(false);
  const dialogTitleComputed = computed(() => {
    if (dialogTitle.value === 0) {
      if (formInfo.value.hasOwnProperty("id")) delete formInfo.value.id;
      return "新增";
    } else if (dialogTitle.value === 1) {
      return "编辑";
    } else {
      return "";
    }
  });

  const tableHeaderStyle = reactive({
    backgroundColor: '#ecf5ff',
    color: '#459FFC',
  });

  function handleAdd() {
    dialogTitle.value = 0;
    dialogVisible.value = true;
  }

  function handleDelete(row) {
    multipleSelection = [{...row}];
    delteRows();
  }

  async function handleEdit(row) {
    dialogTitle.value = 1;
    dialogVisible.value = true;
    formInfo.value = cloneDeep(row);
  }

  function handleCurrentPage() { // args -> val: number
    queryData();
  }

  function handleSizeChange() { // args -> val: number
    queryData();
  }

  function resetQuery() {
    query.value = {
      curPage: 1,
      limit: 10
    };
    queryData();
  }

  function delteRows() {
    if (multipleSelection.length === 0) {
      ElMessage.error("当前未选择任何数据");
      return;
    }
    ElMessageBox.confirm("您是否确认删除数据?", "温馨提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning"
    }).then(async () => {
      const ids = [];
      for (const item of multipleSelection) {
        ids.push(item.id);
      }
      await MXhr.delete(deleteApi + ids);
      ElMessage.success("删除成功");
      queryData();
    });
  }

  function dialogConfirm() {
    const _ruleFormRef = ruleFormRef.value;
    if (!_ruleFormRef) return;
    _ruleFormRef.validate((valid) => {
      if (valid) {
        let _formInfo = cloneDeep(formInfo.value);
        if (dialogTitle.value === 0) {
          MXhr.post(addApi, _formInfo).then((response) => {
            ElMessage.success("成功");
            _ruleFormRef.resetFields();
            dialogVisible.value = false;
            queryData();
          });
        }
        if (dialogTitle.value === 1) {
          MXhr.put(updateApi, _formInfo).then((response) => {
            ElMessage.success("更新成功");
            _ruleFormRef.resetFields();
            dialogVisible.value = false;
            queryData();
          });
        }
      }
    });
  }

  function dialogCancel() {
    const _ruleFormRef = ruleFormRef.value;
    if (!_ruleFormRef) return;
    _ruleFormRef.resetFields();
    dialogVisible.value = false;
  }

  function queryData() {
    MXhr.get(pageApi, {
      params: query.value
    }).then((response) => {
      tableData.list = response.list;
      tableData.total = response.total;
    });
  }

  function handleSelectionChange(val) {
    multipleSelection = val;
  }

  // const getRowKeys = (row: { id: string }) => {return row.id;};
  return {
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
  };
}
