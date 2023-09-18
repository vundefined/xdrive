const app = getApp();
import HttpConnection from "../../utils/HttpConnection";

Page({
    data: {
        notification: {}
    },
    onLoad() {
        this.wxLogin();
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
    wxLogin() {
        let that = this;
        wx.login({
            success (res) {
                HttpConnection.request("auth/wxTeacherLogin", {code: res.code}, "GET").then(function(res) {
                    wx.setStorageSync("token", res.token);
                    wx.setStorageSync("uesrInfo", res.userInfo);
                    that.getNotification();
                }).catch(function(error) {
                    wx.clearStorageSync();
                    that.getNotification();
                });
            }
        });
    },
    getNotification() {
        let that = this;
        HttpConnection.request("common/notification", {
            nType: 0
        }, "GET").then(function(response) {
            that.setData({
                notification: response
            });
        }).catch(function(error) {});
    },
})
