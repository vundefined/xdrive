import Api from "./Api";

export default {
    request(url, data, method = "POST") {
		let token = wx.getStorageSync("token");
		let header = null;
		if (token == undefined || token == null) {
			header = {
				'content-type': 'application/json'
			}
		} else {
			header = {
				'content-type': 'application/json',
				'wxmini-token': token
			}
		}
		return new Promise((resolve, reject) => {
			wx.request({
				url: Api.businessUrl + url,
				data: data,
				header: header,
				method: method,
				success(res) {
					if(res.data.code == 200) {
						resolve(res.data.data);
					}else{
						wx.showToast({title: res.data.message, icon: "none", mask: true});
						reject(res.data);
					}
				}
			})
		});
	}
}
