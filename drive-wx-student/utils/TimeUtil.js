module.exports = {
	/*
	* nS: 值为 时间戳 或者 new Date()
	* type: 时间显示模式.若传入12则为12小时制,不传入则为24小时制
	*/
	getLocalTime(nS, type) {
		var Y, M, D, W, H, I, S;
		function fillZero(v) {
			if (v < 10) { v = '0' + v; }
			return v;
		}
		var d = new Date(parseInt(nS / 1000) * 1000);
		var Week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
		Y = d.getFullYear();
		M = fillZero(d.getMonth() + 1);
		D = fillZero(d.getDate());
		W = Week[d.getDay()];
		H = fillZero(d.getHours());
		I = fillZero(d.getMinutes());
		S = fillZero(d.getSeconds());
		if (type && type == 12) {
			if (H <= 12) {
				H = '上午&nbsp;' + H;
			} else if (H > 12 && H < 24) {
				H -= 12;
				H = '下午&nbsp;' + fillZero(H);
			} else if (H == 24) {
				H = '下午&nbsp;00';
			}
		}
		let localTime = {
			year: Y,
			month: M,
			date: D,
			hours: H,
			minute: I,
			second: S,
			week: W,
		};
		return localTime;
	}
}