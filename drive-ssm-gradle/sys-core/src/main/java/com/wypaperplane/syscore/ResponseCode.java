package com.wypaperplane.syscore;

public enum ResponseCode {

    SUCCESS(200, "成功"),
    FAILED(-1, "失败"),
    VALIDATE_FAILED(402, "参数检验失败"),
    NOT_FOUND(404, "路径不存在，请检查路径是否正确"),
    NOT_SUPPORTED(405, "不支持此请求方法"),
    SERIOUS(502, "系统内部错误"),
    UNAUTHENTICATION(604, "认证失败"),
    UNROLE(609, "未知角色"),

    QREXPIRED(1006, "对不起，该二维码已过期，请联系教练更新"),
    QRCOUNT(1007, "对不起，该二维码已达到加入人数上限，请联系教练更新"),
    NO_TEACHER(1020, "您当前未绑定教练或未设置默认教练"),
    NO_CLASS(1021, "您当前未设置默认驾驶证类型信息"),
    NO_CLASS_MATCH(1023, "您当前默认驾驶证类型与教练不一致"),
    NO_TIME_MATCH(1024, "当前时间不在预约范围内"),
    NO_WEEK_MATCH(1025, "今日为教练的休息日"),
    NO_APPLY_PRICE_A(1026, "对不起, 请联系教练设置报名费后再重试"),
    NO_APPLY_PRICE_B(1027, "对不起, 请支付报名费用后再预约申请"),
    NO_APPLY_PRICE_C(1028, "培训费用未交齐"),
    NO_TEACHER_RULE(1030, "请联系教练完善预约规则"),
    DUPLICATE_KEY(1060, "数据重复，请检查后提交"),
    DUPLICATE_CLASS(1061, "一日一种类型仅能预约一次，不可重复预约"),
    DUPLICATE_TEACHER(1062, "您已绑定过该教练，不可重复绑定"),

    FILESIZEBIG(1070, "文件上传过大"),

    NO_RABBITMQMESSAGE(1080, "无更多 rabbitmq 消息");

    private int code;
    private String message;

    private ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
