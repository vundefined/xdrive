import Api from '../../../utils/Api.js';
import HttpConnection from '../../../utils/HttpConnection.js';

Page({
	data: {
		staticUrl: Api.staticUrl,
		feedbackText: "",
		imgArr: [], // 上传图片的临时变量
	},
	changeFile(e) {
		this.setData({
			imgArr: e.detail
		});
	},
	getFeedbackText(e){
		this.setData({
			feedbackText: e.detail.value,
		});
	},
	feedbackHistoryTap(){
		wx.navigateTo({
			url: '/personal-info/pages/feedbackhistory/feedbackhistory',
		});
	},
	fCommitTap(){
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
		HttpConnection.request("feedback/commit", {
            "wxUserId": _userInfo.id,
			"content": that.data.feedbackText,
			"images": that.data.imgArr
		}).then(function (res) {
			wx.showToast({
				title: '提交成功',
            });
            that.feedbackHistoryTap();
		}).catch(function(error){});
	},
})