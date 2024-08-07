import Axios from "axios";
import {ElMessage, ElLoading} from "element-plus";

let loadingInstance = null;

const MXhr = Axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  timeout: 60 * 1000
});

MXhr.defaults.headers.post["Content-Type"] = "application/json;charset=UTF-8";

MXhr.interceptors.request.use(
  (requestConfig) => {
    loadingInstance = ElLoading.service({
      fullscreen: true,
      lock: true,
      text: "Loading",
      background: "rgba(0, 0, 0, 0.7)"
    });
    requestConfig.headers["admin-token"] = localStorage.getItem("admin-token");
    return requestConfig;
  },
  (error) => {
    loadingInstance.close();
    return Promise.reject(error);
  }
);

MXhr.interceptors.response.use(
  (response) => {
    loadingInstance.close();
    // console.log("MXhr----", response);
    if (response.data.code === 200) {
      return response;
    } else {
      ElMessage.error(response.data.message);
      return Promise.reject(response.data);
    }
  },
  (error) => {
    loadingInstance.close();
    ElMessage.error("token 过期");
    // console.log("----->", error);
    localStorage.clear();
    return Promise.reject(error);
  }
);

export default MXhr;
