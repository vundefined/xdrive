import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";
let Regular = require("../../../utils/Regular");
import WxapiUtil from "../../../utils/WxapiUtil.js";

Page({
    data: {
        staticUrl: Api.staticUrl,
        qrImg: null,
    },
    onLoad(options) {
        // this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/generateQrcode", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            let _url = Api.staticUrl + response;
            that.setData({
                qrImg: _url
            });
        }).catch(function(error){});
    },
    dwQrcode() {
        let that = this;
        WxapiUtil.downloadFileToPhotosAlbum(that.data.qrImg);
    },
    applyPriceConfig() {
        wx.navigateTo({
            url: '/recommend/pages/apply-price/apply-price',
        });
    }
})