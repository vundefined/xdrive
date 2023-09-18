import HttpConnection from "../../../utils/HttpConnection";
import Api from "../../../utils/Api";

Page({
    data: {
        week: [{
            id: 1,
            name: "一",
            checked: false
        }, {
            id: 2,
            name: "二",
            checked: false
        }, {
            id: 3,
            name: "三",
            checked: false
        }, {
            id: 4,
            name: "四",
            checked: false
        }, {
            id: 5,
            name: "五",
            checked: false
        }, {
            id: 6,
            name: "六",
            checked: false
        }, {
            id: 7,
            name: "七",
            checked: false
        }],
        appointmentRule: {}
    },
    onLoad(options) {
        this.pageInit();
    },
    pageInit() {
        let that = this;
        let _userInfo = wx.getStorageSync("uesrInfo");
        HttpConnection.request("teacher/appointmentRuleInfo", {
            teacherId: _userInfo.id
        }, "GET").then(function(response) {
            let _appointmentRule = response;
            let _week = that.data.week;
            let _restIndex = _appointmentRule.restIndex;
            for (let item of _restIndex) {
                _week[item].checked = true;
            }
            that.setData({
                week: _week,
                appointmentRule: _appointmentRule
            });
        }).catch(function(error){});
    },
    appointmentRuleSave() {
        let that = this;
        let _rest = [];
        let _restIndex = [];
        let _week = that.data.week;
        let _appointmentRule = that.data.appointmentRule;
        for (let index in _week) {
            let _item = _week[index];
            if (_item.checked) {
                _rest.push(_item.id);
                _restIndex.push(parseInt(index));
            }
        }
        let _userInfo = wx.getStorageSync("uesrInfo");
        _appointmentRule.rest = _rest;
        _appointmentRule.restIndex = _restIndex;
        _appointmentRule.teacherId = _userInfo.id;
        HttpConnection.request("teacher/appointmentRuleSave", _appointmentRule).then(function(response) {
            wx.showToast({
                title: '成功',
            });
        }).catch(function(error){});
    },
    startTimeChange(event) {
        let _value = event.detail.value + ":00";
        this.setData({
            "appointmentRule.startTime": _value
        });
    },
    endTimeChange(event) {
        let _value = event.detail.value + ":00";
        this.setData({
            "appointmentRule.endTime": _value
        });
    },
    ruleDone(event) {
        let _value = event.detail.value;
        this.setData({
            "appointmentRule.ruleTxt": _value
        });
    },
    weekCheckTap(e) {
        let _index = e.currentTarget.dataset.index;
        let _week = this.data.week;
        _week[_index].checked = !_week[_index].checked;
        this.setData({
            week: _week
        })
    },
    navigateClassDuring() {
        wx.navigateTo({
            url: '/personal-info/pages/appointment-class-during/appointment-class-during',
        });
    }
})