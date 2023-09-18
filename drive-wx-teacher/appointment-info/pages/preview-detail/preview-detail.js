import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        staticUrl: Api.staticUrl,
        previewDetail: {}
    },
    onLoad(options) {
        let that = this;
        const eventChannel = this.getOpenerEventChannel();
        eventChannel.on('acceptDataFromOpenerPage', function(data) {
            that.setData({
                previewDetail: data
            })
        });
    },
    appointmentApproved() { // 通过
        let that = this;
        let _item = that.data.previewDetail;
        _item.status = 1;
		HttpConnection.request("appointmentRecord/changeStatus", _item).then(function(response) {
			wx.showToast({
			    title: '成功',
            });
            that.setData({
                previewDetail: _item
            });
		}).catch(function(error){});
    },
    changeStatusTap() { // 撤销至审核中状态
        let that = this;
        let _item = that.data.previewDetail;
        _item.status = 0;
		HttpConnection.request("appointmentRecord/changeStatus", _item).then(function(response) {
			wx.showToast({
			    title: '成功',
            });
            that.setData({
                previewDetail: _item
            });
		}).catch(function(error){});
	}
})