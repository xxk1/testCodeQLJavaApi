package com.lztech.site.service.coursemanagement;

import com.lztech.domain.model.course.CourseResourceFile;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeResourceFile;
import com.lztech.site.viewmodel.transcode.TransCodeRequestVo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TranscodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TranscodeService.class);

    @Value("${transcode.exchange}")
    private String exchange;
    @Value("${transcode.routingKey}")
    private String routingKey;
    @Value("${transcode.videoType}")
    private String videoType;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTranscodeMqRequestMessage(List<CourseResourceFile> courseResourceFiles) {
        Channel channel = rabbitTemplate.getConnectionFactory()
                .createConnection()
                .createChannel(false);

        try {
            channel.exchangeDeclare(exchange, "direct", true);
            channel.queueDeclare(routingKey, true, false, false, null);
            channel.queueBind(routingKey, exchange, routingKey);

            courseResourceFiles.forEach(courseResourceFile -> {
                try {
                    TransCodeRequestVo transCodeRequestVo = new TransCodeRequestVo();
                    transCodeRequestVo.setVideoResourceId(courseResourceFile.getId());
                    transCodeRequestVo.setInnerIp(courseResourceFile.getInnerIp() + courseResourceFile.getFilePath());
                    transCodeRequestVo.setFileName(courseResourceFile.getFileSavedName());
                    transCodeRequestVo.setResourceType("TEACHING_RESOURCE");

                    rabbitTemplate.convertAndSend(exchange, routingKey, transCodeRequestVo);
                } catch (Exception e) {
                    LOGGER.error("sendTranscodeMqRequestMessage send->{}", e);
                }
            });
        } catch (IOException e) {
            LOGGER.error("sendTranscodeMqRequestMessage->{}", e);
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                    LOGGER.error("sendTranscodeMqRequestMessage close channel->{}", e);
                }
            }
        }
    }
    public void sendKnowledgeGraphTranscodeMqRequestMessage(
            List<CourseKnowledgeGraphNodeResourceFile> courseKnowledgeGraphNodeResourceFileList) {
        Channel channel = rabbitTemplate.getConnectionFactory()
                .createConnection()
                .createChannel(false);
        try {
            channel.exchangeDeclare(exchange, "direct", true);
            channel.queueDeclare(routingKey, true, false, false, null);
            channel.queueBind(routingKey, exchange, routingKey);
            courseKnowledgeGraphNodeResourceFileList.forEach(courseKnowledgeGraphNodeResourceFile -> {
                try {
                    TransCodeRequestVo transCodeRequestVo = new TransCodeRequestVo();
                    transCodeRequestVo.setVideoResourceId(courseKnowledgeGraphNodeResourceFile.getId());
                    transCodeRequestVo.setInnerIp(courseKnowledgeGraphNodeResourceFile.getInnerUrl() +
                            courseKnowledgeGraphNodeResourceFile.getFilePath());
                    transCodeRequestVo.setFileName(courseKnowledgeGraphNodeResourceFile.getFileRealName());
                    transCodeRequestVo.setResourceType("KNOWLEDGE_GRAPH_NODE_RESOURCE");
                    rabbitTemplate.convertAndSend(exchange, routingKey, transCodeRequestVo);
                } catch (Exception e) {
                    LOGGER.error("sendTranscodeMqRequestMessage send->{}", e);
                }
            });
        } catch (IOException e) {
            LOGGER.error("sendTranscodeMqRequestMessage->{}", e);
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                    LOGGER.error("sendTranscodeMqRequestMessage close channel->{}", e);
                }
            }
        }
    }

    public Boolean isTransCodeVideoType(String fileType) {
        List<String> transCodeVideoTypeList =  Arrays.asList(videoType.split(","));
        long filterCount = transCodeVideoTypeList.stream().filter(s->s.equals(fileType.toLowerCase())).count();
        return filterCount > 0;
    }


}
