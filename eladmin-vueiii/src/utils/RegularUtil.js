export default {
  /*
  * 手机中间四位星号显示
  * (\d{3})   $1 前三位
  * \d{4}     **** 中间四位
  * (\d{4})   $2 后四位
  * let telRule = /^1[3|4|5|6|7|8|9]\d{9}$/;
  * */
  validatePhone(phone) {
    phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
    return phone;
  },
  /*
  * 获取地址栏参数
  * url 'https://lazy.lanhouxiyi.com/upload/lazyMonkey?channelId=64&=9';
  * name activityId
  */
  getQueryByName(url, name) {
    var reg = new RegExp('[?&]' + name + '=([^&#]+)');
    var query = url.match(reg);
    return query ? query[1] : null;
  }
}
