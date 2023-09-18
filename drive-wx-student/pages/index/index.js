const app = getApp();
import HttpConnection from "../../utils/HttpConnection";
const Regular = require("../../utils/Regular");

Page({
    data: {
        notification: {}
    },
    onLoad(options) {
        let that = this;
        that.getNotification(options);
        // that.wxCheckSession();
    },
    getNotification(options) {
        let that = this;
        HttpConnection.request("common/notification", {
            nType: 0
        }, "GET").then(function(response) {
            that.setData({
                notification: response
            });
            that.wxLogin(options);
        }).catch(function(error) {});
    },
    wxCheckSession() {
        wx.checkSession({
            success (res) {
                console.log("res-----", res);
            },
            fail (error) {
                console.log("error-----", error);
            }
        })
    },
    wxLogin(options) {
        let that = this;
        wx.login({
            success(res) {
                HttpConnection.request("auth/wxStudentLogin", {code: res.code}, "GET").then(function(res) {
                    wx.setStorageSync("token", res.token);
                    wx.setStorageSync("uesrInfo", res.userInfo);
                    if(options.q != undefined) {
                        let _url = decodeURIComponent(options.q);
                        let _scancodeTime = options.scancode_time;
                        let _studentId = res.userInfo.id;
                        let _teacherId = Regular.getUrlParam(_url, "teacherId");
                        let _qrType = Regular.getUrlParam(_url, "qrType");
                        let _recommend = Regular.getUrlParam(_url, "recommend");
                        that.bindTeacher(_studentId, _teacherId, _recommend, _qrType);
                    }
                }).catch(function(error) {
                    wx.clearStorageSync();
                });
            }
        });
    },
    bindTeacher(_studentId, _teacherId, _recommend, _qrType) {
        if (_qrType == 0) { // 0教练邀请已在驾校交完费用的
            wx.showModal({
                title: '提示',
                content: "您有来自"+ _teacherId +"教练的绑定信息，是否绑定",
                success(res) {
                    if (res.confirm) {
                        HttpConnection.request("student/bindTeacher", {
                            studentId: _studentId,
                            teacherId: _teacherId,
                            recommend: _recommend,
                            qrType: _qrType,
                            deleted: false
                        }).then(function(res) {
                            wx.showToast({
                                title: '绑定成功',
                            });
                            wx.navigateTo({
                                url: '/personal-info/pages/teachers/teachers',
                            });
                        }).catch(function(error) {
                            wx.showModal({
                                title: '提示',
                                content: error.message
                            });
                        });
                    }
                }
            });
        } else if (_qrType == 1) { // 1教练自己推广的
            HttpConnection.request("student/bindTeacher", {
                studentId: _studentId,
                teacherId: _teacherId,
                recommend: _recommend,
                qrType: _qrType,
                deleted: false
            }).then(function(res) {
                wx.navigateTo({
                    url: '/pages/publicity/publicity?teacherId=' + _teacherId,
                });
            }).catch(function(error) {
                wx.showModal({
                    title: '提示',
                    content: error.message
                });
            });
        }
    }
})
