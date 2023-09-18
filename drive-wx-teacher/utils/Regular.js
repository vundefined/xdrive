module.exports = {
	/**
     *  获取地址栏参数
     */
	getQueryByName(url, name) {
		let reg = new RegExp('[?&]' + name + '=([^&#]+)');
		let query = url.match(reg);
		return query ? query[1] : null;
	},
	/**
     * 手机号 验证
     */
	telRule: /^1[3-9]\d{9}$/,

	/**
     * 正整数
     */
	integerReg: /^[1-9][0-9]*$/, // 

	/**
     * 金钱 带两位小数
     */
	priceReg: /^[0-9]{1,4}\.\d{2}/ // 
}
