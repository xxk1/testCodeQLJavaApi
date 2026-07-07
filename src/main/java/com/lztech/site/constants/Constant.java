package com.lztech.site.constants;

public class Constant {

    /**
     * 体育课程属性
     */
    public static final int PE_COURSE_TYPE = 2;
    /**
     * 英语课程属性
     */
    public static final int ENGLISH_COURSE_TYPE = 3;

    public static final int WEEK_DAYS_NUM = 7;

    /**
     * 数字0
     */
    public static final int ZREO = 0;
    /**
     * 数字5
     */
    public static final int FIVE = 5;
    /**
     * 数字4
     */
    public static final int FOUR = 4;
    /**
     * 数字3
     */
    public static final int THREE = 3;
    /**
     * 数字2
     */
    public static final int TWO = 2;
    /**
     * 数字1
     */
    public static final int ONE = 1;
    /**
     * 数字6
     */
    public static final int SIX = 6;
    /**
     * 数字8
     */
    public static final int EIGHT = 8;
    /**
     * 数字20
     */
    public static final int TWENTY = 20;

    /**
     * 数字7
     */
    public static final int SEVEN = 7;

    // 重复加入
    public static final int ALREADY_IN = 0;
    // 成功
    public static final int SUCCESS = 1;
    // 是创建者
    public static final int IS_BUILDER = 2;
    // 失败
    public static final int FAIL = 3;

    public static final int HAVING_COURSE = 1;

    public static final int NO_COURSE = 0;

    public static final int NOT_IN_CLASS = 0;

    public static final int IN_CLASS = 1;

    public static final String EMPTY_STR = "";

    /**
     * 学期总周次数
     */
    public static final int WEEK_COUNT = 25;

    /**
     * 班级信息集合初始化大小
     */
    public static final int LIST_NINETY_THOUSAND = 90000;

    /**
     * 数字999
     */
    public static final int COURSE_MAX_NUM = 999;

    /**
     * 生成项目code随机数字编码
     */
    public static final int PROJECT_CODE_RANDOM_NUMBER_LENGTH = 3;

    /**
     * 生成项目code随机数字最大值
     */
    public static final int PROJECT_CODE_MAX_NUMBER = 999;

    /**
     * 最小的三位正整数
     */
    public static final int MIN_THREE_DIGIT_INTEGER = 100;
    /**
     * 班级分组方案-已分配学生
     */
    public static final int CLASS_GROUPING_ASSIGNED = 0;
    /**
     * 班级分组方案-未分配学生
     */
    public static final int CLASS_GROUPING_UNASSIGNED = 1;
    /**
     * 班级最大小组方案数
     */
    public static final int CLASS_MAX_SCHEME_NUM = 8;

    /**
     * 未开始的学期
     */
    public static final int TERM_TYPE_FUTURE = 2;
    /**
     * 当前学期
     */
    public static final int TERM_TYPE_NOW_TERM = 1;

    /**
     * 已结束的学期
     */
    public static final int TERM_TYPE_PREVIOUS = 0;

    /**
     * 一个班级最大的实验小组个数
     */
    public static final int CLASS_EXPERIMENTAL_GROUP_MAX_NUM = 8;

    /**
     * 云课堂管理端素材数量统计列表排序方式
     */
    public static final int DEFAULT_COURSECODE = 0;
    public static final int MATERIALNUM = 1;
    public static final int COURSEWARENUM = 2;
    public static final int VIDEONUM = 3;
    public static final int PICTURENUM = 4;


    /**
     * 允许添加实验小组方案
     */
    public static final int CLASS_SCHEME_ADD_PRACTICE = 0;


    /**
     * 允许添加普通小组方案
     */
    public static final int CLASS_SCHEME_ADD_ORDINARY = 1;


    /**
     * 都不允许添加
     */
    public static final int CLASS_SCHEME_ADD_NULL = 2;

    /**
     * 排序规则 -1
     */
    public static final int SORT_LAST_ONE = -1;

    /**
     * 排序规则 -2
     */
    public static final int SORT_LAST_TWO = -2;

    /**
     * kafka 响应时间
     */
    public static final int MAX_BLOCK_MS_CONFIG = 3000;

    /**
     * kafka 重试次数
     */
    public static final int RETRIES_CONFIG = 3;

    /**
     * kafka 连接超时时间
     */
    public static final int REQUEST_TIMEOUT_MS_CONFIG = 1000;

    /**
     * kafka 最大并发数
     */
    public static final int MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = 1;


    /**
     * icloudClassRoomDev项目下获取自定义排序后的教室id（按照：校区，楼，层，教室排序，教室编号 进行的排序返回的教室id集合接口实现）
     */
    public static final String GET_CLASSROOM_ID_AFTER_SORTING = "/sorting/classroom";

    /**
     * 本地缓存key名称（开始）
     */
    public static final String TERM_RESOURCE_ID_LIST = "termResourceIdList";

    public static final String GROUP_NO_LIST = "groupNoList";

    public static final String EVERY_ROW_ERROR_MESSAGES = "everyRowErrorMessages";

    public static final String COURSE_CODE_AND_COURSE_NAME_MAP = "courseCodeAndCourseNameMap";

    public static final String COLLEGE_CODE_AND_COLLEGE_NAME_MAP = "collegeCodeAndCollegeNameMap";
    public static final String ALL_TEACHER_NO = "allTeacherNo";
    public static final String ALL_TEACHER_MODEL = "allTeacherModel";
    public static final String ALL_STUDENT_NO = "allStudentNo";
    public static final String IMPORT_CLASS_VO = "importClassVo";
    public static final String IMPORT_STUDENT_VO = "importStudentVo";
    public static final String OPERATOR_ID = "operatorId";
    public static final String OPERATOR_NAME = "operatorName";
    public static final String ROW_INDEX = "rowIndex";
    public static final Integer ROW_INDEX_VALUE_INIT = 0;


    /**
     * 批量导入教学班级信息模版字段名称（开始）
     */
    public static final String TERM_OF_SEMESTER = "所属学期";

    public static final String CLASS_NO = "班级编号";

    public static final String CLASS_NAME = "班级名称";

    public static final String CLASS_ATTRIBUTE = "班级属性";

    public static final String COURSE_CODE = "授课课程编号";

    public static final String COURSE_NAME = "授课课程名称";

    public static final String OPEN_COLLEGE_CODE = "开课学院编号";

    public static final String OPEN_COLLEGE_NAME = "开课学院名称";

    public static final String COURSE_CATEGORY = "课程类别";

    public static final String COURSE_TEACHER_CODES = "授课老师";

    public static final String OPEN_COLLEGE = "开课学院";
    public static final String STUDENT_NO = "学号";
    public static final String STUDENT_NAME = "姓名";

    /**
     * excel读取缓存
     */
    public static final Integer BATCH_COUNT = 2;

    /**
     * 导入表格最大行数
     */
    public static final Integer ROW_INDEX_MAX_VALUE = 1000;

    /**
     * excel空表格错误码(使用方：教学班导入、教学班学生导入)
     */
    public static final String EMPTY_TABLE_ERROR_CODE = "0001";

    /**
     * excel导入数据上限错误码(使用方：教学班导入、教学班学生导入)
     */
    public static final String TABLE_UPPER_LIMIT_ERROR_CODE = "0002";

    /**
     * 正则表达式，验证是否有中文
     */
    public static final String HAS_CHINESE = "[\u4e00-\u9fa5]";

    /**
     * 允许的图片类型
     */
    public static final String ALLOWED_PIC_TYPE = "jpg,png,jpeg,bmp,raw";

    /**
     * 允许的视频型
     */
    public static final String ALLOWED_VIDEO_TYPE = "mp4,flv,avi,mov,rmvb,mkv";

    /**
     * 需要区分学校授课学生类型
     */
    public static final String NEED_DISTINGUISH_COURSE_STUDENT_TYPE = "1";

    /**
     * 导入文件名称限制长度
     */
    public static final int IMPORT_FILE_NAME_LIMIT_LENGTH = 200;

    /**
     * 专家听课授权范围为点播状态
     */
    public static final int SCOPE_OF_AUTHORITY_ON_DEMAND = 2;

    /**
     * 一百万
     */
    public static final int HUNDRED = 1000000;

    /**
     * 60
     */
    public static final int SIXTY = 60;

    /**
     * 24
     */
    public static final int TWENTY_FOUR = 24;
    /**
     * 1000
     */
    public static final int THOUSAND = 1000;

    /**
     * 一周
     */
    public static final int WEEK = 7;

    /**
     * 500
     */
    public static final int FIVE_HUNDRED = 500;

    /**
     * 100
     */
    public static final int ONE_HUNDRED = 100;

    /**
     * 200
     */
    public static final int TWO_HUNDRED = 200;


    /**
     * 按用户所属学院
     */
    public static final Integer SUPERVISE_COLLEGE_TYPE_USER_COLLEGE = 0;

    /**
     * 按开课学院
     */
    public static final Integer SUPERVISE_COLLEGE_TYPE_COURSE_TABLE_COLLEGE = 1;

    /**
     * 魔法数字_30
     */
    public static final Integer MAGIC_THIRTY = 30;

    /**
     * 魔法数字_600
     */
    public static final Integer MAGIC_SIX_HUNDRED = 600;


    /**
     * 知识点级别1
     */
    public static final int ONE_LEVEL = 1;

    /**
     * 识点级别2
     */
    public static final int TWO_LEVEL = 2;
    /**
     * 识点级别3
     */
    public static final int THREE_LEVEL = 3;
    /**
     * 识点级别4
     */
    public static final int FOUR_LEVEL = 4;
    /**
     * 识点级别5
     */
    public static final int FIVE_LEVEL = 5;
    /**
     * 识点级别6
     */
    public static final int SIX_LEVEL = 6;
    /**
     * 识点级别7
     */
    public static final int SEVEN_LEVEL = 7;

    /**
     * 知识点名称最长30个字
     */
    public static final int THIRTY_WORDS = 30;
    /**
     * 知识点内容最长600个字
     */
    public static final int SIX_HUNDRED_WORDS = 600;

    /**
     * 导入excel第3行，索引为2
     */
    public static final int TWO_ROW = 2;
    /**
     * 7列
     */
    public static final int SEVEN_COLUMN = 7;
    /**
     * 7列
     */
    public static final int TWELVE_COLUMN = 12;
    /**
     * 0.1
     */
    public static final double ZERO_POINT_ONE = 0.1;
    /**
     * 课程建设-提交教学材料附件临时对象标记（删除）
     */
    public static final int DATA_OPERATION_TYPE_DELETE = 0;
    /**
     * 课程建设-提交教学材料附件临时对象标记（添加）
     */
    public static final int DATA_OPERATION_TYPE_ADD = 1;
    /**
     * 知识图谱节点最大级数
     */
    public static final int KNOWLEDGE_NODE_MAX_LEVEL = 10;

    /**
     * 导入知识点excel总的列数
     */
    public static final int IMPORT_KNOWLEDGE_NODE_COLUMN_COUNT = 15;

    /**
     * 导入知识点excel最大级数
     */
    public static final int IMPORT_KNOWLEDGE_NODE_MAX_LEVEL = 10;

    /**
     * 识点级别8
     */
    public static final int EIGHT_LEVEL = 8;
    /**
     * 识点级别9
     */
    public static final int NINE_LEVEL = 9;
    /**
     * 识点级别10
     */
    public static final int TEN_LEVEL = 10;

    /**
     * 魔法数字:10
     */
    public static final int MAGIC_TEN = 10;

    /**
     * 互动课堂不允许的备课包文件类型
     */
    public static final String INTERACTIVE_CLASSROOM_NOT_ALLOWED_PREPARATION_COURSE_BAG_FILE_TYPE = "zip,rar,tar,7z,txt,mkv";

    /**
     * 允许的视频型
     */
    public static final String ALLOWED_AUDIO_TYPE = "mp3,aac,ogg,wma,flac,ape,alac,wav,m4a";

    /**
     * 课程建设-提交教学材料附件临时对象标记（修改）
     */
    public static final int DATA_OPERATION_TYPE_UPDATE = 3;

    /**
     * 最大流水号
     */
    public static final int MAX_SERIAL_NUMBER = 9999;

    /**
     * 200
     */
    public static final int MAGIC_TWO_HUNDRED = 200;
}
