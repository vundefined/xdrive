import HttpConnection from "./HttpConnection";
import Api from "./Api";

export default {
    // 单个文件上传
	mUploadFile(category) {
        return new Promise((resolve, reject) => {
            wx.chooseMedia({
                count: 1,
                mediaType: ['image'],
                success(res) {
                    let token = wx.getStorageSync("token");
                    wx.uploadFile({
                        url: Api.businessUrl + "storage/create",
                        filePath: res.tempFiles[0].tempFilePath,
                        name: "file",
                        header: {
                            "Content-Type": "multipart/form-data",
                            'wxmini-token': token
                        },
                        formData: {
                            category: category
                        },
                        success(res) {
                            let _resData = JSON.parse(res.data);
                            resolve(_resData.data);
                        }
                    });
                },
            })
        });
    },
    multipleUploadFile(item) {
        return new Promise((resolve, reject) => {
            let token = wx.getStorageSync("token");
            wx.uploadFile({
                url: Api.businessUrl + "storage/create",
                filePath: item.tempFilePath,
                name: "file",
                header: {
                    "Content-Type": "multipart/form-data",
                    'wxmini-token': token
                },
                formData: {
                    category: 'FEEDBACK' // 意见反馈
                },
                success(res) {
                    let _resData = JSON.parse(res.data);
                    resolve(_resData.data);
                }
            });
        });
    }
}