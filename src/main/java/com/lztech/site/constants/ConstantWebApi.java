package com.lztech.site.constants;


public class ConstantWebApi {
    /**
     * 获取人员信息
     */
    public static final String GET_USER_INFO = "api/v1/users/userinfos";

    /**
     * 获取所有用户信息
     */
    public static final String GET_ALL_USER = "api/v1/users/users";

    public static final String GET_CLASS_ROOMS = "/api/v1/classrooms/search";

    /**
     * 获取对应类型的物品信息
     */
    public static final String GET_TYPE_ARTICLES = "/api/v1/lowValueArticles/articles";
    /**
     * 获取低值易耗物品信息
     */
    public static final String POST_LOW_VALUE_ARTICLES = "/api/v1/lowvaluearticles";

    public static final String GET_TEACHER_INFO_BY_NO = "/api/v1/users/userbaseinfoandcolleges";

    /**
     * 根据实验中心获取实验中心下教室信息
     */
    public static final String GET_TEACHING_CENTER_CLASSROOM = "/v1/teachingcenter/classroom";

    /**
     * 文件生成压缩包
     */
    public static final String SAVE_FILE_TO_ZIP = "/v1/filemanagement/packbag";

    /**
     * 课程文件生成压缩包
     */
    public static final String COURSE_FILE_TO_ZIP = "/v1/filemanagement/packcoursebag";

    /**
     * 文件上传
     */
    public static final String POST_FILE_BY_PROJECTNAME = "v1/fileManagement/filesupload/";
    /**
     * 根据教室id获取楼栋信息
     */
    public static final String GET_CLASSROOM_FLOOR_INFO = "/api/v1/classroom/floor";

    /**
     * 获取所有老师信息
     */
    public static final String GET_ALL_TEACHERS = "api/v1/users/teachers";

    /**
     * 获取所有学生信息
     */
    public static final String GET_ALL_STUDENTS = "api/v1/users/students";

    /**
     * 通过工号/学号查询用户信息（判断是否仅返回有邮箱的用户信息）
     */
    public static final String GET_USER_RESOURCE = "/api/v1/user/userresource";

    /**
     * 根据courseDetailId过滤包含填空题的课堂测验
     */
    public static final String FILTER_FILL_ELECTION = "/v1/classtestpaper/filterfillelection";

    /**
     * 根据学院id列表获取老师列表
     */
    public static final String POST_COLLEGE_TEACHERS = "/api/v1/users/collegeteachers";

    /**
     * 根据id获取题目信息
     */
    public static final String GET_QUESTION_LIBRARY = "/v1/questionlibrary";

    /**
     * 根据题库id列表获取题目详情列表
     */
    public static final String POST_QUESTION_LIBRARY_LIST = "/v1/questionlibrary/list";

    /**
     * 根据预约时间获取可预约项目
     */
    public static final String GET_BOOKABLE_PROJECT = "/v1/bookableprojects";
    /**
     * 获取楼栋教室信息
     */
    public static final String GET_BUILD_AND_ROOM = "/builds";

    /**
     * 根据相关条件校验课堂实录中是否存在知识导图
     */
    public static final String POST_COURSE_KNOWLEDGE_GRAPH_AI_GENERATE_TASK ="/v1/courseknowledgegraphaigeneratetask";

    /**
     * 通过用户id列表获取行政班id列表
     */
    public static final String GET_USERS_ADMINISTRATIVE_CLASS_IDS = "/api/v1/users/administrativeclassids";
}

