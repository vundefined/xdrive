import HttpConnection from "../../../utils/HttpConnection";
import Api from "../../../utils/Api";

Page({
    data: {
        staticUrl: Api.staticUrl,
        teacherList: [],
        settings: {},
        classId: null,
        deleted: false // 是否被禁用
    },
    onLoad(options) {
        this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/teacherBaseList", {
            studentId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                teacherList: response
            });
            that.getSettings();
        }).catch(function(error){});
    },
    getSettings() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/settings", {
            stype: 0,
            studentId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                settings: response
            });
        }).catch(function(error){});
    },
    selectTeacher(e) {
        let _index = e.currentTarget.dataset.index;
        let _item = this.data.teacherList[_index];
        this.setData({
            deleted: _item.deleted,
            'settings.commonId': _item.wxUserBase.id
        });
        if (_item.deleted) {
            wx.showModal({
                title: '错误',
                content: '您已被教练取消预约资格，是否重新申请加入？当前功能未完成，待程序员小哥哥紧急修复中',
                complete: (res) => {
                    if (res.cancel) {}
                    if (res.confirm) {}
                }
            });
        }
    },
    settingsTap() {
        let that = this;
        let _settings = that.data.settings;
        if (_settings.commonId == null) {
            wx.showToast({
                title: '请先选择一名教练人员',
                icon: 'none'
            });
        } else {
            let _userInfo = wx.getStorageSync("uesrInfo");
            HttpConnection.request("student/settingsSave", {
                id: _settings.id,
                studentId: _userInfo.id,
                commonId: _settings.commonId,
                stype: 0
            }).then(function(response) {
                wx.showToast({
                    title: '设置成功',
                });
                that.getSettings();
            }).catch(function(error){});
        }
    },
    bindTeacher() {
        let that = this;
        wx.scanCode({
            success (res) {
                let _value = JSON.parse(res.result);
                let _userInfo = wx.getStorageSync("uesrInfo");
                HttpConnection.request("student/bindTeacher", {
                    studentId: _userInfo.id,
                    teacherId: _value.teacherId,
                    recommend: _value.recommend,
                    qrType: _value.qrType,
                    deleted: false
                }).then(function(res) {
                    wx.showToast({
                        title: '绑定成功',
                    });
                    that.pageInit();
                }).catch(function(error) {
                    wx.showModal({
                        title: '提示',
                        content: error.message
                    });
                });
            }
        })
    },
    teacherDetail() {
        let _settings = this.data.settings;
        if (_settings.commonId == null) {
            wx.showToast({
                title: '请先选择一名教练人员',
                icon: 'none'
            });
        } else {
            wx.navigateTo({
                url: '/personal-info/pages/teachers-detail/teachers-detail?teacherId=' + _settings.commonId,
            });
        }
    }
})