import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        classId: null,
        classIndex: null,
        wxUserBase: {},
        appointmentRule: {},
        appointmentDuringList: [],
        driveLicenseClassTree: [],
        duringIndex: -1
    },
    onLoad(options) {
        let that = this;
        const eventChannel = this.getOpenerEventChannel();
        HttpConnection.request("common/driveLicenseClassTree", {}, "GET").then(function(response) {
            eventChannel.on('acceptDataFromOpenerPage', function(adfopres) {
                that.setData({
                    driveLicenseClassTree: response,
                    classId: adfopres.classId,
                    classIndex: adfopres.classIndex,
                    wxUserBase: adfopres.wxUserBase,
                    appointmentRule: adfopres.appointmentRule,
                    appointmentDuringList: adfopres.appointmentDuringList
                });
            }).catch(function(error){});
        }).catch(function(error){});
    },
    commitTap() {
        let that = this;
        let _index = that.data.duringIndex;
        if (_index == -1) {
            wx.showToast({
                title: '请至少选择一项时间段',
                icon: 'none'
            });
            return;
        }
        let duringItem = that.data.appointmentDuringList[_index];
        let _appointmentRule = that.data.appointmentRule;
        let _teacherId = that.data.wxUserBase.id;
        let _userInfo = wx.getStorageSync("uesrInfo");
        let _data = {
            classId: that.data.classId,
            classIndex: that.data.classIndex,
            studentId: _userInfo.id,
            teacherId: _teacherId,
            duringId: duringItem.id,
            duringStartTime: duringItem.startTime,
            duringEndTime: duringItem.endTime,
            status: 0,
            appointmentRuleRest: _appointmentRule.rest,
            appointmentRuleStartTime: _appointmentRule.startTime,
            appointmentRuleEndTime: _appointmentRule.endTime
        }
        HttpConnection.request("student/appointmentCommit", _data).then(function(response) {
            wx.showModal({
                title: '提示',
                content: '预约成功',
                complete: (res) => {
                    if (res.cancel) {}
                    if (res.confirm) {
                        wx.navigateTo({
                            url: '/appointment-info/pages/appointment-record/appointment-record',
                        });
                    }
                }
            });
        }).catch(function(error) {
            wx.showModal({
                title: '提示',
                content: error.message,
                complete: (res) => {
                    if (res.cancel) {}
                    if (res.confirm) {
                        if (error.code == 1024 || error.code == 1025 || error.code == 1061) {
                            wx.navigateBack({
                                delta: 1
                            });
                        }
                    }
                }
            });
        });
    },
    appointmentDuringCurrent(event) {
        let _index = event.currentTarget.dataset.index;
        this.setData({
            duringIndex: _index
        });
    }
})