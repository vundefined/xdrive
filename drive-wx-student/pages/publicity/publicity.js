import HttpConnection from "../../utils/HttpConnection";
import Api from "../../utils/Api";
import Regular from "../../utils/Regular";

Page({
    data: {
        staticUrl: Api.staticUrl,
        userBase: {},
        teacherJob: {},
        teacherCarList: {},
        qrRecommendList: [],
        qrRecommendItem: {
            price: 0
        },
        isAgree: false
    },
    onLoad(options) {
        this.pageInit(options);
    },
    pageInit(options) {
        let that = this;
        HttpConnection.request("student/teacherDetail", {
            teacherId: options.teacherId
        }, "GET").then(function(response) {
            that.setData({
                userBase: response.wxUserBase,
                teacherJob: response.teacherJob,
                teacherCarList: response.teacherCarList
            });
            that.getRecommendInfo(options);
        }).catch(function(error){});
    },
    getRecommendInfo(options) {
        let that = this;
        HttpConnection.request("teacher/getRecommendPrice", {
            teacherId: options.teacherId
        }, "GET").then(function(response) {
            that.setData({
                qrRecommendList: response
            });
        }).catch(function(error){});
    },
    classChange(e) {
        let that = this;
        let _index = e.currentTarget.dataset.index;
        let _item = that.data.qrRecommendList[_index];
        that.setData({
            qrRecommendItem: _item
        });
    },
    payTap() {
        let that = this;
        let _qrRecommendItem = that.data.qrRecommendItem;
        if (_qrRecommendItem.price == 0) {
            wx.showToast({
                title: '培训金额应大于0',
                icon: 'none'
            });
            return;
        }
        if (that.data.isAgree) {
            let _userInfo = wx.getStorageSync("uesrInfo");
            HttpConnection.request("student/prepayTransactions", {
                price: _qrRecommendItem.price,
                studentId: _userInfo.id
            }).then(function(response) {
                wx.requestPayment({
                    timeStamp: response.prepayInfo.timeStamp,
                    nonceStr: response.prepayInfo.nonceStr,
                    package: response.prepayInfo.packageVal,
                    signType: response.prepayInfo.signType,
                    paySign: response.prepayInfo.paySign,
                    success(res) {
                        console.log("wx.requestPayment-res---", res);
                        that.queryOrderc(response.outTradeNo);
                    },
                    fail(err) {
                        console.log("wx.requestPayment-err---", err);
                    }
                });
            }).catch(function(error){});
        } else {
            wx.showToast({
                title: '请点击同意协议按钮',
                icon: 'none'
            });
        }
    },
    queryOrderc(outTradeNo) {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/queryOrderc", {
            teacherId: that.data.userBase.id,
            studentId: _userInfo.id,
            classId: that.data.qrRecommendItem.classId,
            outTradeNo: outTradeNo
        }).then(function(response) {
            wx.showToast({
                title: '支付成功',
            });
            wx.navigateBack({
                delta: 1
            });
        }).catch(function(error) {});
    },
    switchChange(e) {
        this.setData({
            isAgree: e.detail.value
        });
    },
    toNotification() {
        wx.navigateTo({
            url: '/pages/notification/notification?nType=1',
        });
    }
})