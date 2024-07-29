import {computed, nextTick, reactive, ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus/es";
import MXhr from "@/utils/MXhr";

export default function useTable(addapi, deleteapi, update, pageapi) {
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
    await nextTick();
    formInfo.value = {...row};
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
      await MXhr.delete(deleteapi + ids);
      ElMessage.success("删除成功");
      queryData();
    });
  }

  function dialogConfirm() {
    const _ruleFormRef = ruleFormRef.value;
    if (!_ruleFormRef) return;
    _ruleFormRef.validate((valid) => {
      if (valid) {
        if (dialogTitle.value === 0) {
          MXhr.post(addapi, formInfo.value).then((response) => {
            ElMessage.success(response.data.message);
            _ruleFormRef.resetFields();
            dialogVisible.value = false;
            queryData();
          });
        }
        if (dialogTitle.value === 1) {
          MXhr.put(update, formInfo.value).then((response) => {
            ElMessage.success(response.data.message);
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
    MXhr.get(pageapi, {
      params: query.value
    }).then((response) => {
      tableData.list = response.data.data.list;
      tableData.total = response.data.data.total;
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
