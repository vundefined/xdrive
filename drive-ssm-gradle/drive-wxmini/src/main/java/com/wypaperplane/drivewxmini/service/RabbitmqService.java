package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.vo.AppointmentStudentVo;
import com.wypaperplane.syscore.entity.AppointmentRecord;
import com.wypaperplane.syscore.mapper.AppointmentRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class RabbitmqService {

    private final Logger logger = LoggerFactory.getLogger(RabbitmqService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AppointmentRecordMapper appointmentRecordMapper;

    /**
     * 发送消息 学员提交预约信息
     * */
    public void sendMessageAppointmentDrive(AppointmentRecord appointmentRecord) {
        rabbitTemplate.convertAndSend("appointment.drive.exchange", "appointment.drive.routingkey", appointmentRecord);
    }

    /**
     * 监听消息 教练自动接收消息
     * */
    @RabbitListener(queues = "appointment.drive.queue")
    public void receiveMessageAppointmentDrive(Message message) {
        if (!ObjectUtils.isEmpty(message)) {
            AppointmentStudentVo appointmentStudentVo = (AppointmentStudentVo) SerializationUtils.deserialize(message.getBody());
            AppointmentRecord appointmentRecord = appointmentStudentVo;
            appointmentRecordMapper.insertSelective(appointmentRecord);
        }
    }
}
