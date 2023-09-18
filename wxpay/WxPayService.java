package com.wypaperplane.drivewxmini.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.http.*;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.model.TransactionAmount;
import com.wypaperplane.drivewxmini.wxpayments.*;
import com.wypaperplane.syscore.entity.*;
import com.wypaperplane.syscore.mapper.OrderStudentMapper;
import com.wypaperplane.syscore.mapper.OrderTeacherMapper;
import com.wypaperplane.syscore.mapper.TeacherSettingsMapper;
import com.wypaperplane.syscore.mapper.WxUserMapper;
import com.wypaperplane.syscore.properties.WxPayProperties;
import com.wypaperplane.syscore.properties.WxStudentProperties;
import com.wypaperplane.syscore.properties.WxTeacherProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.wechat.pay.java.core.util.GsonUtil.toJson;

@Service
public class WxPayService {

    private final Logger logger = LoggerFactory.getLogger(WxPayService.class);

    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private WxStudentProperties wxStudentProperties;

    @Autowired
    private WxTeacherProperties wxTeacherProperties;

    @Autowired
    private Config wxPayConfig;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private OrderTeacherMapper orderTeacherMapper;

    @Autowired
    private OrderStudentMapper orderStudentMapper;

    @Autowired
    private TeacherSettingsMapper teacherSettingsMapper;

    /**
     * transactions 下单
     * wxOpenId: 支付者
     * total: 支付金额
     * */
    public Map<String, Object> transactions(String wxOpenId, Integer total) {
        LocalDateTime localDatetime = LocalDateTime.now();
        String curTime = localDatetime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        char[] alphabet = "0123456789".toCharArray();
        String nanoidId = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, 6);
        StringBuilder outTradeNo = new StringBuilder();
        outTradeNo.append("PDR").append(wxPayProperties.getMerchantId()).append(curTime).append(nanoidId);
        Amount amount = new Amount();
        amount.setTotal(total * 100);
        amount.setCurrency("CNY");
        Payer payer = new Payer();
        payer.setOpenid(wxOpenId);
        SettleInfo settleInfo = new SettleInfo();
        settleInfo.setProfitSharing(true);

        PrepayRequest request = new PrepayRequest();
        request.setAppid(wxStudentProperties.getAppid());
        request.setMchid(wxPayProperties.getMerchantId());
        request.setDescription("驾驶学习培训费");
        request.setOutTradeNo(outTradeNo.toString());
        request.setNotifyUrl(wxPayProperties.getNotifyUrl());
        request.setAmount(amount);
        request.setPayer(payer);
        request.setSettleInfo(settleInfo);
        JsapiServiceExtension jsapiServiceExtension = new JsapiServiceExtension.Builder().config(wxPayConfig).build();
        PrepayWithRequestPaymentResponse pwrpr = jsapiServiceExtension.prepayWithRequestPayment(request);
        Map<String, Object> mmap = new LinkedHashMap<>();
        mmap.put("prepayInfo", pwrpr);
        mmap.put("outTradeNo", outTradeNo.toString());
        return mmap;
    }

    /**
     * 根据商户订单号查询支付详情
     */
    public OrderStudent queryOrder(Integer teacherId, Integer studentId, Integer classId, String outTradeNo) {
        QueryOrderByOutTradeNoRequest qobotnr = new QueryOrderByOutTradeNoRequest();
        qobotnr.setMchid(wxPayProperties.getMerchantId());
        qobotnr.setOutTradeNo(outTradeNo);
        JsapiServiceExtension jsapiServiceExtension = new JsapiServiceExtension.Builder().config(wxPayConfig).build();
        Transaction transaction = jsapiServiceExtension.queryOrderByOutTradeNo(qobotnr);
        if (transaction.getTradeState().equals(Transaction.TradeStateEnum.SUCCESS)) {
            TransactionAmount transactionAmount = transaction.getAmount();
            OrderStudent orderStudent = new OrderStudent();
            orderStudent.setStudentId(studentId);
            orderStudent.setTeacherId(teacherId);
            orderStudent.setClassId(classId);
            orderStudent.setOutTradeNo(transaction.getOutTradeNo());
            orderStudent.setTransactionId(transaction.getTransactionId());
            orderStudent.setTradeState(transaction.getTradeState().name());
            orderStudent.setTradeStateDesc(transaction.getTradeStateDesc());
            orderStudent.setSuccessTime(transaction.getSuccessTime());
            orderStudent.setTotal(transactionAmount.getTotal());
            orderStudent.setPayerTotal(transactionAmount.getPayerTotal());
            orderStudent.setCurrency(transactionAmount.getCurrency());
            orderStudent.setPayerCurrency(transactionAmount.getPayerCurrency());
            orderStudent.setProfitsharing(false);
            orderStudentMapper.insertSelective(orderStudent);
            return orderStudent;
        } else {
            return null;
        }
    }

    /**
     * 与教练分账
     */
    public void profitsharingToTeacher(OrderStudent orderStudent) {
        WxUser wxUser = wxUserMapper.selectByPrimaryKey(orderStudent.getTeacherId());

        /*
        * 判断微信支付后台是否已经存在分账接收方
        * 如果不存在，则添加一条记录
        * */
        if (!wxUser.getReceivers()) {
            profitsharingReceiversAdd(wxUser.getOpenid());
            wxUser.setReceivers(true);
            wxUserMapper.updateByPrimaryKeySelective(wxUser);
        }

        String requestPath = "https://api.mch.weixin.qq.com/v3/profitsharing/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
        headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());

        TeacherSettings teacherSettings = teacherSettingsMapper.selectByPrimaryKey(1);
        // 配置分账比例
        Integer total = orderStudent.getPayerTotal() * teacherSettings.getPercentage() / 100;
        List<ReceiversReq> receivers = new ArrayList<>();
        receivers.add(new ReceiversReq("PERSONAL_OPENID", wxUser.getOpenid(), total, "驾驶学习培训费"));

        char[] alphabet = "0123456789".toCharArray();
        String nanoidId = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, 6);

        LocalDateTime localDatetime = LocalDateTime.now();
        String curTime = localDatetime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        StringBuilder outTradeNo = new StringBuilder();
        outTradeNo.append("SDR").append(wxPayProperties.getMerchantId()).append(curTime).append(nanoidId);

        ProfitsharingRequest profitsharingRequest = new ProfitsharingRequest(
                wxTeacherProperties.getAppid(),
                orderStudent.getTransactionId(),
                outTradeNo.toString(),
                receivers,
                false);
        RequestBody body = new JsonRequestBody.Builder().body(toJson(profitsharingRequest)).build();
        HttpClient httpClient = new DefaultHttpClientBuilder().config(wxPayConfig).build();
        HttpResponse<ProfitsharingResponse> httpResponse = httpClient.post(headers, requestPath, body, ProfitsharingResponse.class);
        ProfitsharingResponse profitsharingResponse = httpResponse.getServiceResponse();
        ReceiversRes receiversRes = profitsharingResponse.getReceivers().get(0);
        OrderTeacher orderTeacher = new OrderTeacher();
        orderTeacher.setTeacherId(orderStudent.getTeacherId());
        orderTeacher.setStudentId(orderStudent.getStudentId());
        orderTeacher.setTransactionId(profitsharingResponse.getTransactionId());
        orderTeacher.setOutOrderNo(profitsharingResponse.getOutOrderNo());
        orderTeacher.setOrderId(profitsharingResponse.getOrderId());
        orderTeacher.setState(profitsharingResponse.getState().name());
        orderTeacher.setType(receiversRes.getType());
        orderTeacher.setAccount(receiversRes.getAccount());
        orderTeacher.setAmount(receiversRes.getAmount());
        orderTeacher.setDescription(receiversRes.getDescription());
        orderTeacher.setResult(receiversRes.getResult());
        orderTeacher.setFailReason(receiversRes.getFailReason());
        orderTeacher.setDetailId(receiversRes.getDetailId());
        orderTeacher.setCreateTime(receiversRes.getCreateTime());
        orderTeacher.setFinishTime(receiversRes.getFinishTime());
        orderTeacherMapper.insertSelective(orderTeacher);
        orderStudent.setProfitsharing(true);
        orderStudentMapper.updateByPrimaryKeySelective(orderStudent);
    }

    /**
     * 添加分账接收方
     * */
    public void profitsharingReceiversAdd(String openid) {
        String requestPath = "https://api.mch.weixin.qq.com/v3/profitsharing/receivers/add";
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
        headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        HttpClient httpClient = new DefaultHttpClientBuilder().config(wxPayConfig).build();
        ProfitsharingReceiversRequest profitsharingReceiversRequest = new ProfitsharingReceiversRequest(
                wxTeacherProperties.getAppid(), "PERSONAL_OPENID", openid, "USER");
        RequestBody body = new JsonRequestBody.Builder().body(toJson(profitsharingReceiversRequest)).build();
        HttpResponse<ProfitsharingReceiversResponse> httpResponse = httpClient.post(headers, requestPath, body, ProfitsharingReceiversResponse.class);
        ProfitsharingReceiversResponse profitsharingReceiversResponse = httpResponse.getServiceResponse();
        logger.info("添加分账接收方{}", profitsharingReceiversResponse.toString());
    }

    /**
     * 解冻剩余资金
     * @param transactionId 微信订单号
     * @param outOrderNo 商户分账单号
     * */
    public ProfitsharingUnfreezeResponse profitsharingUnfreeze(String transactionId, String outOrderNo) {
        String requestPath = "https://api.mch.weixin.qq.com/v3/profitsharing/orders/unfreeze";
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
        headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        HttpClient httpClient = new DefaultHttpClientBuilder().config(wxPayConfig).build();
        ProfitsharingUnfreezeRequest profitsharingUnfreezeRequest = new ProfitsharingUnfreezeRequest(transactionId, outOrderNo, "解冻全部剩余资金");
        RequestBody body = new JsonRequestBody.Builder().body(toJson(profitsharingUnfreezeRequest)).build();
        HttpResponse<ProfitsharingUnfreezeResponse> httpResponse = httpClient.post(headers, requestPath, body, ProfitsharingUnfreezeResponse.class);
        ProfitsharingUnfreezeResponse profitsharingUnfreezeResponse = httpResponse.getServiceResponse();
        return profitsharingUnfreezeResponse;
    }

    /**
     * 查询剩余待分金额
     * @param transactionId 微信订单号
     * */
    public UnsplitAmountResponse profitsharingResidue(String transactionId) {
        String requestPath = "https://api.mch.weixin.qq.com/v3/profitsharing/transactions/" + transactionId + "/amounts";
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
        headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        HttpClient httpClient = new DefaultHttpClientBuilder().config(wxPayConfig).build();
        HttpResponse<UnsplitAmountResponse> httpResponse = httpClient.get(headers, requestPath, UnsplitAmountResponse.class);
        UnsplitAmountResponse unsplitAmountResponse = httpResponse.getServiceResponse();
        return unsplitAmountResponse;
    }
}
