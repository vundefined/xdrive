import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";
let Regular = require("../../../utils/Regular");
import WxapiUtil from "../../../utils/WxapiUtil.js";

Page({
    data: {
        staticUrl: Api.staticUrl,
        qrInvite: {
            id: null
        },
        isExpired: true // true已过期
    },
    onLoad(options) {
        this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/qrInvite", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            if (response.qrTime != null) {
                let exp = response.qrTime * 60000;
                let t1 = new Date(response.updateTime).getTime() + exp;
                let t2 = new Date().getTime();
                let _isExpired = t2 > t1;
                that.setData({
                    qrInvite: response,
                    isExpired: _isExpired
                });
            }
        }).catch(function(error){});
    },
    qrcodeGenerate(e) {
        let that = this;
        let _value = e.detail.value;
        let mbooleana = Regular.integerReg.test(_value.qrTime);
        let mbooleanb = Regular.integerReg.test(_value.qrCount);
        if (!mbooleana || !mbooleanb) {
            wx.showToast({
                title: '请输入大于0的数',
                icon: 'none'
            });
            return;
		}
		if (_value.qrTime > 127 || _value.qrCount > 127) {
			wx.showToast({
                title: '请输入小于127的数',
                icon: 'none'
            });
            return;
        }
        let _userInfo = wx.getStorageSync("uesrInfo");
        let _qrInvite = that.data.qrInvite;
        HttpConnection.request("teacher/qrInviteSave", {
            id: _qrInvite.id,
            teacherId: _userInfo.id,
            qrTime: _value.qrTime,
            qrCount: _value.qrCount
        }).then(function(response) {
            that.pageInit();
        }).catch(function(error){});
    },
    downloadQr() {
        let _url = Api.staticUrl + this.data.qrInvite.qrImg;
        WxapiUtil.downloadFileToPhotosAlbum(_url);
    }
})