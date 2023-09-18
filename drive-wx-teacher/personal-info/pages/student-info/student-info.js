import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        staticUrl: Api.staticUrl,
        basicInfo: {}
    },
    onLoad(options) {
        let that = this;
        const eventChannel = this.getOpenerEventChannel();
        eventChannel.on('acceptDataFromOpenerPage', function(data) {
            that.setData({
                basicInfo: data
            })
        });
    },
    unBindTeacher() {
        let that = this;
        let _item = that.data.basicInfo;
        _item.deleted = true;
        HttpConnection.request("student/unBindTeacher", _item).then(function(response) {
            wx.showToast({
                title: '解绑成功',
			});
			wx.navigateBack({
				delta: 2
			});
        }).catch(function(error){});
    }
})