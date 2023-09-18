import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        appointmentClassList: [],
        appointmentClassIndex: 0,
        appointmentDuringList: [],
        appointmentClassDuringList: [],
        appointmentClassDuringItem: {},
        driveLicenseClassTree: []
    },
    onLoad(options) {
        this.getDriveLicenseClassTree();
    },
    onShow() {
        this.pageInit();
    },
    getDriveLicenseClassTree() {
        let that = this;
        HttpConnection.request("common/driveLicenseClassTree", {}, "GET").then(function(response) {
            that.setData({
                driveLicenseClassTree: response
            });
        }).catch(function(error){});
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/appointmentClassDuringInfo", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                appointmentClassList: response.appointmentClassList,
                appointmentDuringList: response.appointmentDuringList,
                appointmentClassDuringList: response.appointmentClassDuringList
            });
            that.compute(that.data.appointmentClassIndex);
        }).catch(function(error){});
    },
    bindTap() {
        let that = this;
        let _appointmentClassIndex = that.data.appointmentClassIndex;
        if (_appointmentClassIndex == -1) {
            wx.showToast({
                title: '请选择一项驾驶证类型',
                icon: 'none'
            });
            return;
        }
        let _appointmentClassList = that.data.appointmentClassList;
        let _appointmentDuringList = that.data.appointmentDuringList;
        let _appointmentClassDuringItem = that.data.appointmentClassDuringItem;
        let _duringIds = []
        for (let item of _appointmentDuringList) {
            if (item.checked) {
                _duringIds.push(item.id);
            }
        }
        if (_duringIds.length == 0) {
            wx.showToast({
                title: '请至少选择一项时间段',
                icon: 'none'
            });
            return;
        }
        let _userInfo = wx.getStorageSync("uesrInfo");
        let params = {
            wxUserId: _userInfo.id,
            classId: _appointmentClassList[_appointmentClassIndex].classId,
            classIndex: _appointmentClassList[_appointmentClassIndex].classIndex,
            duringIds: _duringIds
        }
        if (_appointmentClassDuringItem.hasOwnProperty("id")) {
            params.id = _appointmentClassDuringItem.id
        }
        HttpConnection.request("teacher/appointmentClassDuringSave", params).then(function(response) {
            wx.showToast({
                title: '保存成功',
			});
			that.pageInit();
        }).catch(function(error){});
    },
    curClass(event) {
        let _index = event.currentTarget.dataset.index;
        this.setData({
            appointmentClassIndex: _index
        });
        this.compute(_index);
    },
    appointmentDuringCurrent(event) {
        let _index = event.currentTarget.dataset.index;
        let _appointmentDuringList = this.data.appointmentDuringList;
        _appointmentDuringList[_index].checked = !_appointmentDuringList[_index].checked;
        this.setData({
            appointmentDuringList: _appointmentDuringList
        });
    },
    compute(appointmentClassIndex) {
        let _classId = this.data.appointmentClassList[appointmentClassIndex].classId;
        let _acdList = this.data.appointmentClassDuringList;
        let _acdItem = {};
        for (let acdItem of _acdList) {
            if (acdItem.classId == _classId) {
                _acdItem = acdItem;
                break;
            } else {
                _acdItem = {};
            }
        }
        this.setData({
            appointmentClassDuringItem: _acdItem
        });
        let _adList = this.data.appointmentDuringList;
        for (let adItem of _adList) {
            adItem.checked = false;
        }
        if (_acdItem.hasOwnProperty("duringIds")) {
            for (let adItem of _adList) {
                for (let acddItem of _acdItem.duringIds) {
                    if (acddItem == adItem.id) {
                        adItem.checked = true;
                    }
                }
            }
        }
        this.setData({
            appointmentDuringList: _adList
        });
    },
    navigateClass() {
        wx.navigateTo({
            url: '/personal-info/pages/appointment-class/appointment-class',
        });
    },
    navigateDuring() {
        wx.navigateTo({
            url: '/personal-info/pages/appointment-during/appointment-during',
        });
    },
})