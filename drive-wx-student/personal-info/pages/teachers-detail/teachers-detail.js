import HttpConnection from "../../../utils/HttpConnection";
import Api from "../../../utils/Api";

Page({
    data: {
        staticUrl: Api.staticUrl,
        userBase: {},
        teacherJob: {},
        teacherCarList: {}
    },
    onLoad(options) {
        let that = this;
        HttpConnection.request("student/teacherDetail", {
            teacherId: options.teacherId
        }, "GET").then(function(response) {
            that.setData({
                userBase: response.wxUserBase,
                teacherJob: response.teacherJob,
                teacherCarList: response.teacherCarList
            });
        }).catch(function(error){});
    }
})