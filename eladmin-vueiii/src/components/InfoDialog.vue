<template>
  <el-dialog v-model="dialogVisible" title="个人信息" :close-on-click-modal="false" :close-on-press-escape="false"
             :show-close="false" width="500px">
    <el-form :model="formInfo" :rules="formInfoRules" ref="ruleFormRef" label-width="120px">
      <el-form-item label="username" prop="username">
        <el-input v-model="formInfo.username"/>
      </el-form-item>
      <el-form-item label="password" prop="password">
        <el-input v-model="formInfo.password"/>
      </el-form-item>
      <el-form-item label="mobile" prop="mobile">
        <el-input v-model="formInfo.mobile"/>
      </el-form-item>
      <el-form-item label="email" prop="email">
        <el-input v-model="formInfo.email"/>
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
</template>

<script setup>
import {reactive, ref, watch} from "vue";
import {ElMessageBox} from "element-plus";
import useUpload from "@/hooks/useUpload";
import {useAuthStore} from "@/stores/auth";
import MXhr from "@/utils/MXhr";
import {ElMessage} from "element-plus/es";
import {Plus} from "@element-plus/icons-vue";

const props = defineProps({
  userInfo: {
    type: Object,
    default: {}
  }
});
const dialogVisible = ref(false);
const formInfo = reactive({
  id: props.userInfo.id,
  username: props.userInfo.username,
  password: "",
  avatar: props.userInfo.avatar,
  mobile: props.userInfo.mobile,
  email: props.userInfo.email,
});
const formInfoRules = reactive({
  username: [{required: true, message: "Please input username", trigger: "blur"}],
  password: [{required: true, message: "Please input password", trigger: "blur"}],
  mobile: [{required: true, message: "Please input mobile", trigger: "blur"}],
  email: [{required: true, message: "Please input email", trigger: "blur"}],
  avatar: [{required: true, message: "Please input avatar", trigger: "blur"}]
});
const ruleFormRef = ref();
const {
  fileName,
  upAction,
  upHeader,
  handleUploadSuccess,
  fileFilter
} = useUpload();
const upData = {"category": 0};

const authStore = useAuthStore();

watch(fileName, (val) => {
  formInfo.avatar = val;
});

function dialogConfirm() {
  const _ruleFormRef = ruleFormRef.value;
  if (!_ruleFormRef) return;
  _ruleFormRef.validate((valid) => {
    if (valid) {
      MXhr.put("sysUserUpdate", formInfo).then((response) => {
        ElMessage.success(response.data.message);
        _ruleFormRef.resetFields();
        dialogVisible.value = false;
        ElMessageBox.confirm("更新后需重新登录", "温馨提示", {
          confirmButtonText: "确定",
          type: "warning"
        }).then(() => {
          authStore.logout();
        });
      });
    }
  });
}

function dialogCancel() {
  const _ruleFormRef = ruleFormRef.value;
  if (!_ruleFormRef) return;
  _ruleFormRef.resetFields();
  dialogVisible.value = false;
}

function openDialog() {
  dialogVisible.value = true;
}

defineExpose({
  openDialog
});
</script>

<style scoped></style>
