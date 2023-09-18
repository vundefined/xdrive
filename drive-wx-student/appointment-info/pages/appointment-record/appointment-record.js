import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";
import Regular from "../../../utils/Regular";

Page({
    data: {
        staticUrl: Api.staticUrl,
        record: [],
        class: []
    },
    onLoad(options) {
        let that = this;
        HttpConnection.request("common/driveLicenseClassTree", {}, "GET").then(function(response) {
            that.setData({
                class: response
            });
            that.pageInit();
        }).catch(function(error){});
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("appointmentRecord/studentList", {
            studentId: _userInfo.id
        }, "GET").then(function(response) {
            for (let item of response) {
                let match = item.addTime.match(Regular.splitDate);
                let year = match[1];
                let month = match[2];
                let day = match[3];

                let today = new Date();
                let todayYear = today.getFullYear();
                let todayMonth = today.getMonth() + 1;
                let todayDay = today.getDate();

                if (year == todayYear && month == todayMonth && day == todayDay) {
                    item.show = false;
                } else {
                    item.show = true;
                }
            }
            that.setData({
                record: response
            });
        }).catch(function(error){});
    },
    changeStatusTap(event) {
		let that = this;
        let _index = event.currentTarget.dataset.index;
        let _item = that.data.record[_index];
        wx.showModal({
            title: '提示',
            content: "取消后今日不可再预约了哦",
            complete: (res) => {
                if (res.cancel) {}
                if (res.confirm) {
                    _item.status = 3;
                    HttpConnection.request("appointmentRecord/changeStatus", _item).then(function(response) {
                        wx.showToast({
                            title: '成功',
                        });
                        that.pageInit();
                    }).catch(function(error){});
                }
            }
        });
	},
})