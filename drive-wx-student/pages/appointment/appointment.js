import HttpConnection from "../../utils/HttpConnection";

Page({
    data: {
        classId: null,
        classIndex: null,
        wxUserBase: {},
        appointmentRule: {},
        appointmentDuringList: [],
        hasTeacher: false
    },
    onShow(options) {
        this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/appointmentRule", {
            studentId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                classId: response.classId,
                classIndex: response.classIndex,
                wxUserBase: response.wxUserBase,
                appointmentRule: response.appointmentRule,
                appointmentDuringList: response.appointmentDuringList,
                hasTeacher: false
            });
        }).catch(function(error) {
            that.setData({hasTeacher: true});
            wx.showModal({
                title: '提示',
                content: error.message,
                complete: (res) => {
                    if (res.cancel) {}
                    if (res.confirm) {
                        if (error.code == 1020) {
                            wx.navigateTo({
                                url: '/personal-info/pages/teachers/teachers',
                            })
                        }else if (error.code == 1021 || error.code == 1023) {
                            wx.navigateTo({
                                url: '/personal-info/pages/job-info/job-info',
                            })
                        }
                    }
                }
            });
        });
    },
    toCommitTap() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/isCompletePayment", {
            classId: that.data.classId,
            teacherId: that.data.wxUserBase.id,
            studentId: _userInfo.id
        }).then(function(response) {
            wx.navigateTo({
                url: '/appointment-info/pages/appointment-commit/appointment-commit',
                success(res) {
                    res.eventChannel.emit('acceptDataFromOpenerPage', {
                        classId: that.data.classId,
                        classIndex: that.data.classIndex,
                        wxUserBase: that.data.wxUserBase,
                        appointmentRule: that.data.appointmentRule,
                        appointmentDuringList: that.data.appointmentDuringList
                    });
                }
            });
        }).catch(function(error) {
            wx.showModal({
                title: '提示',
                content: error.message,
                complete: (res) => {
                    if (res.cancel) {}
                    if (res.confirm) {
                        if (error.code == 1027) {
                            let _teacherId = that.data.wxUserBase.id;
                            wx.navigateTo({
                                url: '/pages/publicity/publicity?teacherId=' + _teacherId,
                            });
                        }
                    }
                }
            });
        });
    },
    previewTap() {
        wx.navigateTo({
            url: '/appointment-info/pages/appointment-preview/appointment-preview'
        });
    }
})