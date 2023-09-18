import Api from "../../../utils/Api";
import HttpConnection from "../../../utils/HttpConnection";

Page({
    data: {
        staticUrl: Api.staticUrl,
        studentBaseVoList: []
    },
    onLoad(options) {
        this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("student/studentBaseList", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            that.setData({
                studentBaseVoList: response
            })
        }).catch(function(error){});
    },
    basicUserItem(event) {
        let _index = event.currentTarget.dataset.index;
        let _item = this.data.studentBaseVoList[_index];
        wx.navigateTo({
            url: '/personal-info/pages/student-info/student-info',
            success(res) {
                res.eventChannel.emit('acceptDataFromOpenerPage', _item)
            }
        });
    }
})