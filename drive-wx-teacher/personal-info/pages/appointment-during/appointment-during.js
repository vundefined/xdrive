import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        startTime: "08:00:00",
        endTime: "08:30:00",
        appointmentDuringList: []
    },
    onLoad(options) {
        this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/appointmentDuringInfo", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                appointmentDuringList: response
            });
        }).catch(function(error){});
    },
    appointmentDuringSave() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/appointmentDuringSave", {
            wxUserId: _userInfo.id,
            startTime: that.data.startTime,
            endTime: that.data.endTime
        }).then(function(response) {
            that.pageInit();
        }).catch(function(error){});
    },
    appointmentDuringDelete() {
        let that = this;
        let _appointmentDuringList = this.data.appointmentDuringList;
        let ids = [];
        for (let item of _appointmentDuringList) {
            if (item.checked) {
                ids.push(item.id);
            }
        }
        if (ids.length > 0) {
            wx.showModal({
                title: "提示",
                content: "确认删除",
                success(res) {
                    if (res.confirm) {
                        HttpConnection.request("teacher/appointmentDuringDelete/" + ids, {}, "DELETE").then(function(response) {
                            wx.showToast({
                                title: '删除成功',
                                icon: 'none'
                            });
                            that.pageInit();
                        }).catch(function(error){});
                    }
                }
            });
        } else {
            wx.showToast({
                title: '请至少选择一项进行删除',
                icon: 'none'
            });
        }
    },
    startTimeChange(event) {
        let _value = event.detail.value + ":00";
        this.setData({
            startTime: _value
        });
    },
    endTimeChange(event) {
        let _value = event.detail.value + ":00";
        this.setData({
            endTime: _value
        });
    },
    switchChange(event) {
        let _index = event.currentTarget.dataset.index;
        let _value = event.detail.value;
        let _appointmentDuringList = this.data.appointmentDuringList;
        _appointmentDuringList[_index].checked = _value;
        this.setData({
            appointmentDuringList: _appointmentDuringList
        });
    },
    defaultDuring() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/generateDuring", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                appointmentDuringList: response
            });
        }).catch(function(error){});
    }
})