module.exports = {
    /**
     * 获取地址栏参数
     */
    getUrlParam(url, key) {
		let _reg = new RegExp('[?&]' + key + '=([^&#]+)');
        let _result = url.match(_reg);
		return _result ? _result[1] : null;
    },
    /**
     * 手机号 验证
     */
    telRule: /^1[3-9]\d{9}$/,
    /**
     * 正整数
     */
    integerReg: /^[1-9][0-9]*$/,
    /**
     * 金钱 带两位小数
     */
    priceReg: /^[0-9]{1,4}\.\d{2}/,
    
    /**
     * 2023-05-17 09:58:13 截取时分秒
     */
    splitDate: /(\d{4})-(\d{2})-(\d{2})/,

    /**
     * 2023-05-17 09:58:13 截取时分秒
     */
    splitDateTime: /\d{2}:\d{2}:\d{2}/,
    
    /**
     * 生成 n 位随机字符串
     */
    randomChar(n) {
        let charsa = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        let charsb = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        let nums = "";
        for (let i = 0; i < n; i++) {
            nums += charsb[parseInt(Math.random() * 61)];
        }
        return nums;
    }
}
