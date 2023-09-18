import HttpConnection from "../../../utils/HttpConnection";
import Api from "../../../utils/Api";
import StorageServer from "../../../utils/StorageServer";

Page({
    data: {
        staticUrl: Api.staticUrl,
        jobInfo: {}
    },
    onLoad(options) {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/jobInfo", {
            teacherId: _userInfo.id
        }, "GET").then(function(res) {
            that.setData({
                jobInfo: res
            });
        }).catch(function(error){});
    },
    bindDateChange(e) {
        this.setData({
            "jobInfo.time": e.detail.value
        });
    },
    jobInfoSave(e) {
        let that = this;
        let _value = e.detail.value;
        let _userInfo = wx.getStorageSync("uesrInfo");
        let _jobInfo = that.data.jobInfo;
        _jobInfo.encode = _value.encode;
        _jobInfo.companyName = _value.companyName;
        _jobInfo.companyAddress = _value.companyAddress;
        _jobInfo.wxUserId = _userInfo.id;
        wx.showModal({
            title: "提示",
            content: "确认提交",
            success(res) {
                if (res.confirm) {
                    HttpConnection.request("teacher/jobInfoSave", _jobInfo).then(function(res) {
                        wx.showToast({
                          title: '成功',
                        });
                    }).catch(function(error){});
                }
            }
        });
    },
    uploadFile() {
        let that = this;
        StorageServer.mUploadFile(2).then(function(_filePath) {
            that.setData({
                "jobInfo.img": _filePath
            })
        });
    }
});
