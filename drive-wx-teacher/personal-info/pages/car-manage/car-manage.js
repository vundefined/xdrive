import HttpConnection from "../../../utils/HttpConnection";
import Api from "../../../utils/Api";
import StorageServer from "../../../utils/StorageServer";

Page({
    data: {
        staticUrl: Api.staticUrl,
        name: "",
        encode: "",
        img: null,
        carList: []
    },
    onLoad(options) {
        this.initPage();
    },
    initPage() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/carList", {
            teacherId: _userInfo.id
        }, "GET").then(function(res) {
            that.setData({
                carList: res
            });
        }).catch(function(error){});
    },
    carSave() {
        let that = this;
        wx.showModal({
            title: "提示",
            content: "确认提交",
            success(res) {
                if (res.confirm) {
                    let _userInfo = wx.getStorageSync("uesrInfo");
                    HttpConnection.request("teacher/carAdd", {
                        wxUserId: _userInfo.id,
                        name: that.data.name,
                        encode: that.data.encode,
                        img: that.data.img
                    }).then(function(res) {
                        that.initPage();
                        that.setData({
                            name: "",
                            encode: "",
                            img: null
                        });
                        wx.showToast({
                            title: "成功",
                            icon: "success"
                        });
                    }).catch(function(error){});
                }
            }
        });
    },
    carDelete(e) {
        let that = this;
        let _id = e.currentTarget.dataset.id;
        wx.showModal({
            title: "提示",
            content: "确认删除",
            success(res) {
                if (res.confirm) {
                    HttpConnection.request("teacher/carDeleteById/" + _id, {}, "DELETE").then(function(res) {
                        wx.showToast({
                            title: "删除成功",
                            icon: "success",
                            mask: true
                        });
                        that.initPage();
                    }).catch(function(error){});
                }
            }
        });
    },
    uploadFile() {
        let that = this;
        StorageServer.mUploadFile('CAR_IMG').then(function(_filePath) {
            that.setData({
                img: _filePath
            })
        });
    }
});