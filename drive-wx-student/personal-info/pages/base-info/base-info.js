import HttpConnection from "../../../utils/HttpConnection";
import Api from "../../../utils/Api";
import StorageServer from "../../../utils/StorageServer";
import Regular from "../../../utils/Regular";

Page({
    data: {
        staticUrl: Api.staticUrl,
        basicInfo: {}
    },
    onLoad(options) {
        this.initPage();
    },
    initPage() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("auth/baseInfo", {
            wxUserId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                basicInfo: response
            });
        }).catch(function(error){});
    },
    genderChange(e) {
        let _value = e.detail.value;
        this.setData({
            "basicInfo.gender": parseInt(_value)
        });
    },
    basicSave(e) {
        let that = this;
        let _value = e.detail.value;
        let _basicInfo = that.data.basicInfo;
        if (!Regular.telRule.test(_value.mobile)) {
            wx.showToast({
                title: '手机号错误',
                icon: 'error'
            });
            return;
        }
        HttpConnection.request("auth/baseInfoUpdate", {
            id: _basicInfo.id,
            nickname: _value.nickname,
            reallname: _value.reallname,
            mobile: _value.mobile,
            gender:  _basicInfo.gender,
            avatar:  _basicInfo.avatar,
        }).then(function(res) {
            wx.showToast({
                title: "更新成功"
            });
            wx.navigateBack({delta: 1});
        }).catch(function(error){});
    },
    uploadFile() {
        let that = this;
        StorageServer.mUploadFile('WX_AVATAR').then(function(_filePath) {
            that.setData({
                "basicInfo.avatar": _filePath
            })
        });
    }
});