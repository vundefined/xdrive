import HttpConnection from "../../../utils/HttpConnection";
let Regular = require("../../../utils/Regular");

Page({
    data: {
        id: null,
        price: 0,
        classId: null,
        qrRecommends: [],
        classs: [],
        notification: {}
    },
    onLoad() {
        let that = this;
        HttpConnection.request("common/driveLicenseClassList", {}, "GET").then(function(response) {
            that.setData({
                classs: response
            });
            that.getNotification();
        }).catch(function(error){});
    },
    getNotification() {
        let that = this;
        HttpConnection.request("common/notification", {
            nType: 2
        }, "GET").then(function(response) {
            that.setData({
                notification: response
            });
            that.pageInit();
        }).catch(function(error) {});
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/qrRecommend", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                qrRecommends: response
            });
        }).catch(function(error){});
    },
    saveTap() {
        let that = this;
        let _price = that.data.price;
        let mbooleana = Regular.integerReg.test(_price);
        if (!mbooleana) {
            wx.showToast({
                title: '请输入大于零的整数',
                icon: 'none'
            });
            return;
        }
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/qrRecommendSave", {
            id: that.data.id,
            teacherId: _userInfo.id,
            classId: that.data.classId,
            price: _price
        }).then(function(response) {
			that.pageInit();
			wx.showToast({title: "设置成功"})
        }).catch(function(error){});
    },
    curClass(event) {
        let _classId = event.currentTarget.dataset.id;
        this.compute(_classId);
    },
    compute(classId) {
        let that = this;
        if (that.data.qrRecommends.length > 0) {
            for (let item of that.data.qrRecommends) {
                if (item.classId == classId) {
                    that.setData({
                        id: item.id,
                        price: item.price,
                        classId: classId
                    });
                    break;
                } else {
                    that.setData({
                        id: null,
                        price: 0,
                        classId: classId
                    });
                }
            }
        } else {
            that.setData({
                classId: classId
            });
        }
    },
    changePrice(e) {
        this.setData({
            price: e.detail.value
        });
    }
})