import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";

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
		HttpConnection.request("appointmentRecord/stodayPreview", {
            studentId: _userInfo.id
        }, "GET").then(function(response) {
			that.setData({
				previewList: response
			});
		}).catch(function(error) {
            wx.showModal({
                title: '提示',
                content: error.message,
                complete: (res) => {
                    if (res.cancel) {
                        wx.navigateBack({delta: 1});
                    }
                    if (res.confirm) {
                        if (error.code == 1020) {
                            wx.navigateTo({
                                url: '/personal-info/pages/teachers/teachers',
                            })
                        }
                    }
                }
            });
        });
    },
})