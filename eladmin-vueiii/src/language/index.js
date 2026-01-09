import {createI18n} from "vue-i18n";
import CommonEn from './common/en.js';
import CommonZhCn from './common/zh-cn.js';
import buttonEn from './button/en.js';
import buttonZhCn from './button/zh-cn.js';
import messageEn from './message/en.js';
import messageZhCn from './message/zh-cn.js';
import timeEn from './time/en.js';
import timeZhCn from './time/zh-cn.js';
import businessEn from './business/en.js';
import businessZhCn from './business/zh-cn.js';

const i18n = createI18n({
  legacy: false,
  silentTranslationWarn: true,
  missingWarn: false,
  silentFallbackWarn: true,
  fallbackWarn: false,
  globalInjection: true,
  locale: 'zh-cn', // 默认中文，后续将从 store 同步
  fallbackLocale: 'en',
  messages: {
    'zh-cn': {
      'common': CommonZhCn,
      'button': buttonZhCn,
      'message': messageZhCn,
      'time': timeZhCn,
      'business': businessZhCn,
    },
    'en': {
      'common': CommonEn,
      'button': buttonEn,
      'message': messageEn,
      'time': timeEn,
      'business': businessEn,
    }
  }
})

export default i18n;
