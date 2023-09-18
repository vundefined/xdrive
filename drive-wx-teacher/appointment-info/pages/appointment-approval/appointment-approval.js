import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
		staticUrl: Api.staticUrl,
		appointmentRecordVoList: [],
		appointmentClassDuringList: [],
		driveLicenseClassTree: [],
		classId: ""
	},
	onLoad(options) {
		let that = this;
        HttpConnection.request("common/driveLicenseClassTree", {}, "GET").then(function(response) {
            that.setData({
                driveLicenseClassTree: response
            });
            that.pageInit();
        }).catch(function(error){});
	},
	pageInit() {
		let that = this;
		let _userInfo = wx.getStorageSync("uesrInfo");
		HttpConnection.request("appointmentRecord/teacherList", {
			teacherId: _userInfo.id,
			classId: that.data.classId
		}, "GET").then(function(response) {
			that.setData({
				appointmentRecordVoList: response.appointmentRecordVoList,
				appointmentClassDuringList: response.appointmentClassDuringList
            });
		}).catch(function(error){});
	},
	appointmentApproved(event) { // 通过
		let that = this;
		let _index = event.currentTarget.dataset.index;
		let _item = that.data.appointmentRecordVoList[_index];
		_item.status = 1;
		HttpConnection.request("appointmentRecord/changeStatus", _item).then(function(response) {
			wx.showToast({
			    title: '成功',
			});
			that.pageInit();
		}).catch(function(error){});
	},
	changeStatusTap(event) { // 撤销至审核中状态
		let that = this;
		let _index = event.currentTarget.dataset.index;
		let _item = that.data.appointmentRecordVoList[_index];
		_item.status = 0;
		HttpConnection.request("appointmentRecord/changeStatus", _item).then(function(response) {
			wx.showToast({
			    title: '成功',
			});
			that.pageInit();
		}).catch(function(error){});
	},
	appointmentRejected() { // 其余学员设置不通过
		let that = this;
		let _userInfo = wx.getStorageSync("uesrInfo");
		HttpConnection.request("appointmentRecord/tRejected", {
			teacherId: _userInfo.id,
		}, "GET").then(function(response) {
			wx.showToast({
			    title: '成功',
			});
			that.pageInit();
		}).catch(function(error){});
	},
	appointmentClassCurrent(event) {
		let _classId = event.currentTarget.dataset.classId;
        this.setData({
			classId: _classId
		});
		this.pageInit();
    },
})