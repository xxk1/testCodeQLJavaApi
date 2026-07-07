package com.lztech.domain.model.wisdomcourse.enums;

public enum WisdomCourseTaskType {
    TEACHING_MATERIAL_FILE_PARSING(0, "文件解析-教材", "文件解析-教材"),
    COURSEWARE_FILE_PARSING(1, "文件解析-课件", "文件解析-课件"),
    VIDEO_PHONETIC_TRANSCRIPTION(2, "视频语音转写", "语音转写"),
    VIDEO_CLASSROOM_SUMMARY(3, "视频课堂总结", "课堂总结"),
    VIDEO_CLASSROOM_KEY_POINTS(4, "视频课堂要点", "课堂要点"),
    VIDEO_KNOWLEDGE_GRAPH(5, "视频知识图谱", "知识导图"),
    CLASSROOM_NAVIGATION(6, "课堂导览", "课堂导航"),
    CLASSROOM_WORD_CLOUD_VIDEO(7, "课堂词云-视频", "课堂词云-视频"),
    CLASSROOM_WORD_CLOUD_COURSEWARE(8, "课堂词云-课件", "课堂词云-课件"),
    INTELLIGENT_QUESTION_BANK(9, "智能题库", "智能题库"),
    KNOWLEDGE_GRAPH_RELATED_VIDEO(10, "知识图谱挂载视频", "图谱资源挂载-视频"),
    KNOWLEDGE_GRAPH_RELATED_QUESTION(11, "知识图谱挂载问题", "图谱资源挂载-题目"),
    TEACHING_MATERIAL_FILE_KNOWLEDGE_BASE(12, "教材文件知识库", "问答知识库-教材"),
    COURSEWARE_FILE_KNOWLEDGE_BASE(13, "课件文件知识库", "问答知识库-课件"),
    VIDEO_KNOWLEDGE_BASE(14,"视频语音转写知识库","问答知识库-视频语音转写");
    private Integer code;
    private String message;
    private String showName;

    public String getShowName() {
        return showName;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    WisdomCourseTaskType(Integer code, String message, String showName) {
        this.code = code;
        this.message = message;
        this.showName = showName;
    }

    public static WisdomCourseTaskType getByCode(Integer code) {
        for (WisdomCourseTaskType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
