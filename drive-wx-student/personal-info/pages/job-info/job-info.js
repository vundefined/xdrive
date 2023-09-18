import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        range: [],
        rangeIndex: [0, 0],
        jobInfoList: [],
        driveLicenseClassTree: [],
        settings: {}
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
            that.getSettings();
        }).catch(function(error){});
    },
    getJobInfo() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/jobInfo", {
            studentId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                jobInfoList: response
            });
        }).catch(function(error){});
    },
    getSettings() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/settings", {
            studentId: _userInfo.id,
            stype: 1
        }, "GET").then(function(response) {
            that.setData({
                settings: response
            });
            that.getJobInfo();
        }).catch(function(error){});
    },
    jobInfoSave() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        let _classId = that.data.range[1][that.data.rangeIndex[1]].id;
        HttpConnection.request("student/jobInfoSave", {
            studentId: _userInfo.id,
            classId: _classId,
            classIndex: that.data.rangeIndex
        }).then(function(response){
            wx.showToast({
                title: "成功",
                icon: "success"
            });
            that.getJobInfo();
        }).catch(function(error){});
    },
    jobInfoDelete(e) {
        let that = this;
        let _index = e.currentTarget.dataset.index;
        let _item = that.data.jobInfoList[_index];
        wx.showModal({
            title: "提示",
            content: "确认删除",
            success(res) {
                if (res.confirm) {
                    HttpConnection.request("student/jobInfoDelete", _item).then(function(res) {
                        wx.showToast({
                            title: "删除成功",
                            icon: "success",
                            mask: true
                        });
                        that.getJobInfo();
                    }).catch(function(error){});
                }
            }
        });
    },
    studentSettingsTap(e) {
        let that = this;
        let _index = e.currentTarget.dataset.index;
        let _userInfo = wx.getStorageSync("uesrInfo");
        let _settings = that.data.settings;
        HttpConnection.request("student/settingsSave", {
            id: _settings.id,
            studentId: _userInfo.id,
            commonId: that.data.jobInfoList[_index].classId,
            stype: 1
        }).then(function(response) {
            wx.showToast({
                title: '设置成功',
            });
            that.getSettings();
        }).catch(function(error){});
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
        })
    }
})