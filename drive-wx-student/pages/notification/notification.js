import HttpConnection from "../../utils/HttpConnection";
import Api from "../../utils/Api";

Page({
    data: {
        notification: {}
    },
    onLoad(options) {
        this.getNotification(options.nType);
    },
    getNotification(nType) {
        let that = this;
        HttpConnection.request("common/notification", {
            nType: nType
        }, "GET").then(function(response) {
            that.setData({
                notification: response
            });
        }).catch(function(error) {});
    },
})