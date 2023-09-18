import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        range: [],
        rangeIndex: [0, 0],
        driveLicenseClassTree: [],
        appointmentClassList: []
    },
    onLoad(options) {
        this.getDriveLicenseClassTree();
    },
    getDriveLicenseClassTree() {
        let that = this;
        HttpConnection.request("common/driveLicenseClassTree", {}, "GET").then(function(response) {
            that.setData({
                driveLicenseClassTree: response,
                range: [
                    response,
                    response[0].children
                ]
            });
            that.pageInit();
        }).catch(function(error){});
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/appointmentClassInfo", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                appointmentClassList: response
            })
        }).catch(function(error){});
    },
    appointmentClassSave() {
        let that = this;
        let _rangeIndex = that.data.rangeIndex;
        let _origin = this.data.driveLicenseClassTree;
        let _id = _origin[_rangeIndex[0]].children[_rangeIndex[1]].id;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/appointmentClassSave", {
            wxUserId: _userInfo.id,
            classId: _id,
            classIndex: _rangeIndex
        }).then(function(response) {
            that.pageInit();
        }).catch(function(error){});
    },
    appointmentClassDelete() {
        let that = this;
        let _appointmentClassList = this.data.appointmentClassList;
        let ids = [];
        for (let item of _appointmentClassList) {
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
                        HttpConnection.request("teacher/appointmentClassDelete/" + ids, {}, "DELETE").then(function(response) {
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
    bindMultiPickerColumnChange(e) {
        let _column = e.detail.column;
        let _value = e.detail.value;
        let _origin = this.data.driveLicenseClassTree;
        // console.log('列为', _column, '，值为', _value);
        if (_column === 0) {
            let _range = this.data.range;
            _range[1] = _origin[_value].children;
            this.setData({
                range: _range
            })
        }
        let _rangeIndex = this.data.rangeIndex;
        _rangeIndex[_column] = _value;
        this.setData({
            rangeIndex: _rangeIndex
        });
    },
    switchChange(event) {
        let _index = event.currentTarget.dataset.index;
        let _value = event.detail.value;
        let _appointmentClassList = this.data.appointmentClassList;
        _appointmentClassList[_index].checked = _value;
        this.setData({
            appointmentClassList: _appointmentClassList
        });
    }
})