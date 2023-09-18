import Api from '../../../utils/Api.js';
import HttpConnection from '../../../utils/HttpConnection.js';

Page({
	data: {
		staticUrl: Api.staticUrl,
		feedBackData: [],
	},
	onLoad() {
		this.pageInit();
	},
	pageInit() {
		let that = this;
		let _userInfo = wx.getStorageSync("uesrInfo");
		HttpConnection.request("feedback/history", {
			wxUserId: _userInfo.id
		}, "GET").then(function (res) {
			that.setData({
				feedBackData: res
			});
		}).catch(function(error){});
	},
	previewImage(e){
		/*
		let that = this;
		let _index = e.currentTarget.dataset.index;
		that.data.feedBackData[_index]
		wx.previewImage({
			current: _imgpath, // 当前图片地址
			urls: _urls
		}) */
	},
})