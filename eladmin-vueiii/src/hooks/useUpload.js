import {reactive, ref} from "vue";

export default function useUpload() {
  const fileName = ref("");

  const baseUrl = import.meta.env.VITE_BASE_URL + "storage/create";

  const upAction = ref(baseUrl);

  const upHeader = reactive({
    "admin-token": localStorage.getItem("admin-token")
  });

  function handleUploadSuccess(res) {
    fileName.value = res.data;
  }

  function fileFilter(fileName) {
    return import.meta.env.VITE_UPLOAD_URL + fileName;
  }

  return {
    fileName,
    upAction,
    upHeader,
    handleUploadSuccess,
    fileFilter
  };
}
