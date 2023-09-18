import Api from "../../utils/Api";
import HttpConnection from "../../utils/HttpConnection";

Page({
    data: {
		staticUrl: Api.staticUrl,
		previewList: []
	},
	onLoad(options) {},
	onShow() {
		this.pageInit();
	},
	pageInit() {
		let that = this;
		let _userInfo = wx.getStorageSync("uesrInfo");
		HttpConnection.request("appointmentRecord/ttodayPreview", {
			teacherId: _userInfo.id
		}, "GET").then(function(response) {
			that.setData({
				previewList: response
			});
		}).catch(function(error){});
	},
	studentInfoTap(event) {
		let _item = event.currentTarget.dataset.item;
		wx.navigateTo({
            url: '/appointment-info/pages/preview-detail/preview-detail',
            success(res) {
                res.eventChannel.emit('acceptDataFromOpenerPage', _item)
            }
        });
	}
})