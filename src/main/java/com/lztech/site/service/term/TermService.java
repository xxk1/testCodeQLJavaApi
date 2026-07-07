package com.lztech.site.service.term;

import com.lztech.domain.model.segment.Segment;
import com.lztech.domain.model.term.Term;
import com.lztech.domain.model.term.enums.TermType;
import com.lztech.persistence.repositories.segment.SegmentRepository;
import com.lztech.persistence.repositories.terms.TermRepository;
import com.lztech.site.resource.term.TermModel;
import com.lztech.site.resource.term.TermResource;
import com.lztech.site.resource.term.TermWeekResource;
import com.lztech.site.service.event.EventService;
import com.lztech.site.service.segment.SegmentService;
import com.lztech.site.until.DateUtils;
import com.lztech.site.until.TimeUtils;
import com.lztech.site.viewmodel.datetime.Times;
import com.lztech.site.viewmodel.event.TermEvent;
import com.lztech.site.viewmodel.term.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.domain.model.term.enums.TermType.getTermType;
import static com.lztech.site.constants.Constant.*;
import static com.lztech.site.constants.ConstantDataVisual.DATA_VISUAL_SOURCE_CLOUD_CLASSROOM;
import static com.lztech.site.until.DateUtils.*;
import static com.lztech.site.until.TimeUtils.dateToLocalDate;
import static java.util.stream.Collectors.toList;


@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;
    @Value("${dataVisual.enable}")
    private Boolean dataVisualEnable;
    @Autowired
    private EventService eventService;


    @Autowired
    private SegmentRepository segmentRepository;
    private int dayOfWeek = 7;
    private String[] weekNames = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private Integer[] weekNos = {1, 2, 3, 4, 5, 6, 7};

    @Autowired
    SegmentService segmentService;

    private int expiredIndex = 2;

    public Term getNowTerm() {
        Date nowStartTime = DateUtils.getEndOfDay(new Date());
        Date nowEndTime = DateUtils.getStartOfDay(new Date());
        return termRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(nowStartTime, nowEndTime);
    }

    public List<Term> getStartTimeAndEndTimeTerm(String startDate, String endDate) {
        Date nowStartDate = DateUtils.stringToDate("yyyy-MM-dd", startDate);
        Date nowEndDate = DateUtils.stringToDate("yyyy-MM-dd", endDate);
        List<Term> termList = termRepository.findAll();
        termList = termList.stream().filter(term ->
                (term.getStartDate().getTime() >= nowStartDate.getTime() && term.getStartDate().getTime() <= nowEndDate.getTime()) ||
                        (term.getEndDate().getTime() >= nowStartDate.getTime() && term.getEndDate().getTime() <= nowEndDate.getTime()) ||
                        (term.getStartDate().getTime() < nowStartDate.getTime() && term.getEndDate().getTime() > nowEndDate.getTime())
        ).collect(toList());
        return termList;

    }

    /**
     * 老师:获取前2个学期、当前学期、后1个学期
     * 学生:获取当前学期
     * 其他:获取当前学期与写一个学期
     */
    public List<TermResource> getTermResource(String source) {
        List<TermResource> termResourceList = new ArrayList<>();
        List<Term> termList = new ArrayList<>();
        Term nowTerm = getNowTerm();
        if (source != null && source.equals("1")) {
            if (nowTerm != null) {
                termList.add(nowTerm);
            }
            Term nextTerm = termRepository.findTop1ByStartDateGreaterThanOrderByStartDateAsc(new Date());
            List<Term> beforeTwoTermList = termRepository.findTop2ByEndDateLessThanOrderByStartDateDesc(new Date());
            if (nextTerm != null) {
                termList.add(nextTerm);
            }
            if (!CollectionUtils.isEmpty(beforeTwoTermList)) {
                termList.addAll(beforeTwoTermList);
            }
            termResourceList = termList.stream().map(t -> {
                TermResource termResource = new TermResource();
                termResource.setTerm((String.valueOf(t.getTerm().getIndex())));
                termResource.setSchoolYear(t.getSchoolYear());
                termResource.setDescription(t.getDescription());
                termResource.setSchoolYearTermNickName(t.getNickName());
                termResource.setStartDate(TimeUtils.dateToStr(t.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(t.getEndDate()));
                termResource.setId(t.getId());
                return termResource;
            }).collect(Collectors.toList());

            return termResourceList.stream()
                    .sorted(Comparator.comparing(TermResource::getId).reversed())
                    .collect(Collectors.toList());
        } else if (source != null && source.equals("0")) {
            TermResource termResource = new TermResource();
            if (Objects.nonNull(nowTerm)) {
                termResource.setTerm((String.valueOf(nowTerm.getTerm().getIndex())));
                termResource.setSchoolYear(nowTerm.getSchoolYear());
                termResource.setDescription(nowTerm.getDescription());
                termResource.setSchoolYearTermNickName(nowTerm.getNickName());
                termResource.setStartDate(TimeUtils.dateToStr(nowTerm.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(nowTerm.getEndDate()));
                termResource.setId(nowTerm.getId());
                termResourceList.add(termResource);
            }
            return termResourceList;
        } else {
            if (nowTerm != null) {
                termList.add(nowTerm);
            }
            Term nextTerm = termRepository.findTop1ByStartDateGreaterThanOrderByStartDateAsc(new Date());
            if (nextTerm != null) {
                termList.add(nextTerm);
            }
            termResourceList = termList.stream().map(term1 -> {
                TermResource termResource = new TermResource();
                termResource.setTerm((String.valueOf(term1.getTerm().getIndex())));
                termResource.setSchoolYear(term1.getSchoolYear());
                termResource.setDescription(term1.getDescription());
                termResource.setSchoolYearTermNickName(term1.getNickName());
                termResource.setStartDate(TimeUtils.dateToStr(term1.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(term1.getEndDate()));
                termResource.setId(term1.getId());
                return termResource;
            }).collect(Collectors.toList());

            return termResourceList;
        }

    }

    /**
     * 获取当前学期和下一个学期
     */
    public ResponseEntity<List<TermResource>> getNowTermAndNextTerm() {
        List<Term> termList = new ArrayList<>();
        Term nowTerm = getNowTerm();
        if (nowTerm != null) {
            termList.add(nowTerm);
        }
        Term nextTerm = termRepository.findTop1ByStartDateGreaterThanOrderByStartDateAsc(new Date());
        if (nextTerm != null) {
            termList.add(nextTerm);
        }
        if (termList.size() > 0) {
            return new ResponseEntity<>(termList.stream().map(term1 -> {
                TermResource termResource = new TermResource();
                termResource.setTerm((String.valueOf(term1.getTerm().getIndex())));
                termResource.setSchoolYear(term1.getSchoolYear());
                termResource.setDescription(term1.getDescription());
                termResource.setSchoolYearTermNickName(term1.getNickName());
                termResource.setStartDate(TimeUtils.dateToStr(term1.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(term1.getEndDate()));
                termResource.setId(term1.getId());
                return termResource;
            }).collect(toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<TermResource> getNowTermAndBeforFiveTerm() {
        List<Term> termList = new ArrayList<>();
        Term nowTerm = getNowTerm();
        if (ObjectUtils.isNotEmpty(nowTerm)) {
            termList.add(nowTerm);
        }
        List<Term> beforFiveTerm = termRepository.findTop5ByEndDateLessThanOrderByStartDateDesc(new Date());
        if (!CollectionUtils.isEmpty(beforFiveTerm)) {
            termList.addAll(beforFiveTerm);
        }
        List<TermResource> termResourceList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!CollectionUtils.isEmpty(termList)) {
            termList.forEach(term -> {
                TermResource termResource = new TermResource();
                termResource.setTerm(String.valueOf(term.getTerm().getIndex()));
                termResource.setDescription(term.getDescription());
                termResource.setSchoolYearTermNickName(term.getNickName());
                termResource.setId(term.getId());
                termResource.setStartDate(sdf.format(term.getStartDate()));
                termResource.setEndDate(sdf.format(term.getEndDate()));
                termResource.setSchoolYear(term.getSchoolYear());
                termResourceList.add(termResource);
            });
        }
        return termResourceList.stream()
                .sorted(Comparator.comparing(TermResource::getId).reversed())
                .collect(Collectors.toList());
    }

    public List<Term> saveTerms(List<TermResource> termResourceList) {
        List<Term> termList = termResourceList.stream().map(termResource -> {
            TermType termType = TermType.getTermType(Integer.valueOf(termResource.getTerm()));
            Term term = termRepository.findBySchoolYearAndTerm(termResource.getSchoolYear(), termType);
            if (term != null) {
                return term;
            }
            term = new Term();
            term.setId(termResource.getSchoolYear() + "-" + termType.getIndex());
            term.setSchoolYear(termResource.getSchoolYear());
            term.setTerm(termType);
            term.setRemark("");
            term.setStartDate(stringToDate(DATE, termResource.getStartDate()));
            term.setEndDate(stringToDate(DATE, termResource.getEndDate()));
            term.setDescription(termType.getName());
            return term;
        }).collect(Collectors.toList());
        return termRepository.saveAll(termList);
    }


    public ResponseEntity<List<TermResource>> getTermList() {
        List<Term> termList = termRepository.findAllByOrderByStartDateDesc();
        List<TermResource> termResourceList = new ArrayList<>();
        if (termList.size() > 0) {
            for (Term term : termList) {
                TermResource termResource = new TermResource();
                termResource.setStartDate(TimeUtils.dateToStr(term.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(term.getEndDate()));
                termResource.setId(term.getId());
                termResource.setDescription(term.getDescription());
                termResource.setSchoolYearTermNickName(term.getNickName());
                termResource.setSchoolYear(term.getSchoolYear());
                termResource.setTerm((String.valueOf(term.getTerm().getIndex())));
                termResourceList.add(termResource);
            }
        }
        return new ResponseEntity<>(termResourceList, HttpStatus.OK);
    }

    public ResponseEntity<TermWeekResource> getTermWeeks(String buildId) {
        Term term = getNowTerm();
        if (org.springframework.util.StringUtils.isEmpty(buildId)) {
            buildId = "0";
        }
        TermWeekResource termWeekResource = new TermWeekResource();
        if (term == null) {
            return new ResponseEntity<>(termWeekResource, HttpStatus.OK);
        }
        List<List<String>> nameList = new ArrayList<>();
        List<List<Integer>> indexList = new ArrayList<>();
        List<String> weekNums = new ArrayList<>();
        List<Integer> weekIndexes = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(TimeUtils.dateToStr(term.getStartDate()), df);
        LocalDate endDate = LocalDate.parse(TimeUtils.dateToStr(term.getEndDate()), df);
        LocalDate startSunDay = startDate.plusDays(dayOfWeek - startDate.getDayOfWeek().getValue());//开学第一周的周末
        LocalDate endSunDay = endDate.plusDays(dayOfWeek - endDate.getDayOfWeek().getValue());
        int termWeeks = (int) ((endSunDay.toEpochDay() - startSunDay.toEpochDay()) / dayOfWeek);
        for (int i = 1; i <= termWeeks + 1; i++) {
            weekNums.add("第" + i + "周");
            weekIndexes.add(i);
        }

        nameList.add(weekNums);
        nameList.add(Arrays.asList(weekNames));
        indexList.add(weekIndexes);
        indexList.add(Arrays.asList(weekNos));
        List<Segment> segmentList = segmentRepository.findByBuildId(buildId);
        nameList.add(segmentList.stream().map(segment -> "第" +
                segment.getSegment()
                + "节次").collect(Collectors.toList()));
        indexList.add(segmentList.stream().map(Segment::getSegment).collect(Collectors.toList()));
        termWeekResource.setNameList(nameList);
        termWeekResource.setIndexList(indexList);
        return new ResponseEntity<>(termWeekResource, HttpStatus.OK);
    }

    public ResponseEntity<WeeksResources> futureWeeksGet(String buildingId) {
        Term term = getNowTerm();
        if (StringUtils.isEmpty(buildingId)) {
            buildingId = "0";
        }
        if (term == null) {
            return null;
        }
        WeeksResources weeksResources = new WeeksResources();
        List<List<String>> nameList = new ArrayList<>();
        List<List<Integer>> indexList = new ArrayList<>();
        List<String> weekNums = new ArrayList<>();
        List<Integer> weekIndexes = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String end = TimeUtils.dateToStr(term.getEndDate());
        String start = TimeUtils.dateToStr(term.getStartDate());
        LocalDate startDate = LocalDate.parse(start, df);
        LocalDate endDate = LocalDate.parse(end, df);
        LocalDate startSunDay = startDate.plusDays(dayOfWeek - startDate.getDayOfWeek().getValue());//开学第一周的周末
        LocalDate endSunDay = endDate.plusDays(dayOfWeek - endDate.getDayOfWeek().getValue());
        int termWeeks = (int) ((endSunDay.toEpochDay() - startSunDay.toEpochDay()) / dayOfWeek);
        List<Integer> segmentIdx = segmentService.getNowSegment(buildingId);
        for (int i = segmentIdx.get(0); i < termWeeks + 1; i++) {
            weekNums.add("第" + (i + 1) + "周");
            weekIndexes.add(i + 1);

        }
        nameList.add(weekNums);
        nameList.add(Arrays.asList(weekNames));
        indexList.add(weekIndexes);
        indexList.add(Arrays.asList(weekNos));
        weeksResources.setIndexList(indexList);
        weeksResources.setNameList(nameList);
        return new ResponseEntity<>(weeksResources, HttpStatus.OK);

    }

    public ResponseEntity<WeeksResources> futureSegments(String buildingId) {
        Term term = getNowTerm();
        if (org.springframework.util.StringUtils.isEmpty(buildingId)) {
            buildingId = "0";
        }
        if (term == null) {
            return null;
        }
        WeeksResources weeksResources = new WeeksResources();
        List<List<String>> nameList = new ArrayList<>();
        List<List<Integer>> indexList = new ArrayList<>();
        List<Segment> segmentList = segmentRepository.findByBuildId(buildingId);
        List<Integer> segmentIdx = segmentService.getNowSegment(buildingId);

        List<Segment> nowSegmentList = segmentList.stream().filter(segment
                -> segmentIdx.get(expiredIndex) <= segment.getSegment())
                .collect(toList());

        List<String> nowSegmentNameList = nowSegmentList.stream().map(segment -> "第" +
                segment.getSegment()
                + "节次").collect(Collectors.toList());
        nameList.add(nowSegmentNameList);
        nameList.add(nowSegmentNameList);

        List<Integer> nowSegmentNumberList = nowSegmentList.stream().map(Segment::getSegment)
                .collect(Collectors.toList());
        indexList.add(nowSegmentNumberList);
        indexList.add(nowSegmentNumberList);

        weeksResources.setNameList(nameList);
        weeksResources.setIndexList(indexList);
        return new ResponseEntity<>(weeksResources, HttpStatus.OK);
    }

    public Times getNowDateTime() {
        Times dateTimeInfo = new Times();
        dateTimeInfo.setNowDateTime(TimeUtils.getNowDateTime());
        dateTimeInfo.setNowYear(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")));
        dateTimeInfo.setNowMonth(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM")));
        dateTimeInfo.setNowDay(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd")));
        dateTimeInfo.setNowHour(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        dateTimeInfo.setNowMinute(LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm")));
        dateTimeInfo.setNowSecond(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")));
        dateTimeInfo.setTimeStamp(String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime()));
        return dateTimeInfo;
    }

    public ResponseEntity<List<TermWeekVo>> getWeeks(Integer week, String schoolYear, Integer termName) {
        List<TermWeekVo> result = new ArrayList<>();
        Term term;
        if (Objects.isNull(schoolYear)) {
            term = getNowTerm();
        } else {
            term = getTermsBySchoolYearAndTerm(schoolYear, TermType.getTermType(termName));
        }
        if (Objects.isNull(term)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        String nowDate = TimeUtils.getNowDate();
        java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        long weekCounts = TimeUtils.getWeekCounts(sdf.format(term.getStartDate()), sdf.format(term.getEndDate()));
        Map<Integer, String[]> termCalendar = TimeUtils.getTermCalendar(sdf.format(term.getStartDate()), weekCounts);
        if (termCalendar == null || termCalendar.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int weekSize = org.springframework.util.StringUtils.isEmpty(week) || week > weekCounts ? (int) weekCounts : week;
        for (int i = 0; i < weekSize; i++) {
            String weekOrder = (i + 1) + "";
            TermWeekVo termWeekVo = new TermWeekVo();
            termWeekVo.setNowDate(nowDate);
            termWeekVo.setWeek(weekOrder);
            termWeekVo.setNowWeek(TimeUtils.getWeekNumByDate(termCalendar, nowDate));
            String[] weekDays = termCalendar.get(i);
            for (int j = 0; j < weekDays.length; j++) {
                TermWeekDayResource termWeekDayResource = new TermWeekDayResource();
                termWeekDayResource.setWeekNum(j + 1 + "");
                termWeekDayResource.setDate(weekDays[j]);
                termWeekVo.addDaysItem(termWeekDayResource);
            }
            result.add(termWeekVo);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public Term getTermsBySchoolYearAndTerm(String schoolYear, TermType termType) {
        List<Term> terms = termRepository.findAllBySchoolYearAndTerm(schoolYear, termType);
        return terms.size() == 0 ? null : terms.get(0);
    }

    public Term getNowTermOrNextTerm() {
        Term term = getNowTerm();
        if (term != null) {
            return term;
        } else {
            term = termRepository.findTop1ByStartDateGreaterThanOrderByStartDateAsc(new Date());
            if (term != null) {
                return term;
            }
            return null;
        }
    }

    /**
     * 获取当前学期和上一个学期
     */
    public ResponseEntity<List<TermResource>> getNowTermAndLastTerm() {
        List<Term> termList = new ArrayList<>();

        Term nowTerm = getNowTerm();
        if (nowTerm != null) {
            termList.add(nowTerm);
        }
        Term lastTerm = termRepository.findTop1ByEndDateLessThanOrderByStartDateDesc(new Date());
        if (lastTerm != null) {
            termList.add(lastTerm);
        }
        if (termList.size() > 0) {
            return new ResponseEntity<>(termList.stream().map(term1 -> {
                TermResource termResource = new TermResource();
                termResource.setTerm((String.valueOf(term1.getTerm().getIndex())));
                termResource.setSchoolYear(term1.getSchoolYear());
                termResource.setDescription(term1.getDescription());
                termResource.setSchoolYearTermNickName(term1.getNickName());
                termResource.setStartDate(TimeUtils.dateToStr(term1.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(term1.getEndDate()));
                termResource.setId(term1.getId());
                return termResource;
            }).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 获取当前学期和之前最近的三个学期
     */
    public ResponseEntity<List<TermResource>> getNowTermAndLastThreeTerm() {
        List<Term> termList = termRepository.findTop4ByStartDateLessThanEqualOrderByStartDateDesc(new Date());
        if (termList.size() > 0) {
            return new ResponseEntity<>(termList.stream().map(term -> {
                TermResource termResource = new TermResource();
                termResource.setTerm((String.valueOf(term.getTerm().getIndex())));
                termResource.setSchoolYear(term.getSchoolYear());
                termResource.setDescription(term.getDescription());
                termResource.setSchoolYearTermNickName(term.getNickName());
                termResource.setStartDate(TimeUtils.dateToStr(term.getStartDate()));
                termResource.setEndDate(TimeUtils.dateToStr(term.getEndDate()));
                termResource.setId(term.getId());
                return termResource;
            }).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public TermResource getTerm(String schoolYear, String term) {
        Term termInfo = termRepository.findBySchoolYearAndTerm(schoolYear, getTermType(Integer.valueOf(term)));
        TermResource termResource = new TermResource();
        if (termInfo != null) {
            termResource.setTerm((String.valueOf(termInfo.getTerm().getIndex())));
            termResource.setSchoolYear(termInfo.getSchoolYear());
            termResource.setDescription(termInfo.getDescription());
            termResource.setSchoolYearTermNickName(termInfo.getNickName());
            termResource.setStartDate(TimeUtils.dateToStr(termInfo.getStartDate()));
            termResource.setEndDate(TimeUtils.dateToStr(termInfo.getEndDate()));
            termResource.setId(termInfo.getId());
        }
        return termResource;
    }

    public List<TermModel> getAllTermList() {
        List<Term> termList = termRepository.findAll().stream().sorted(Comparator.comparing(Term::getStartDate)).collect(toList());
        if (termList == null || termList.size() == 0) {
            return null;
        }
        List<TermModel> termModelList = termList.stream().map(term -> new TermModel() {{
            this.schoolYear(term.getSchoolYear());
            this.term(term.getTerm().getIndex() + "");
            this.termName(term.getSchoolYear() + " " + term.getDescription());
            this.setStartDate(TimeUtils.dateToStr(term.getStartDate()));
            this.setEndDate(TimeUtils.dateToStr(term.getEndDate()));
            this.setSchoolYearTermNickName(term.getNickName());
            Boolean whetherCurrentTerm = betweenStartAndEnd(LocalDate.now(),
                    stringToLocalDate(this.getStartDate()), stringToLocalDate(this.getEndDate()));
            this.setWhetherCurrentTerm(whetherCurrentTerm ? "1" : "0");
        }}).sorted(Comparator.comparing(TermModel::getSchoolYear).thenComparing(TermModel::getTerm)).collect(toList());
        return termModelList;
    }

    public static Boolean betweenStartAndEnd(LocalDate data, LocalDate start, LocalDate end) {
        return (data.isAfter(start) || data.equals(start)) && (data.equals(end) || data.isBefore(end));
    }

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public List<TermVo> getRecentTerm(Integer previousTermNum,
                                      Integer futureTermNum) {
        List<Term> termList = termRepository.findAll();
        LocalDate today = LocalDate.now();
        previousTermNum = Objects.isNull(previousTermNum) || previousTermNum < 0 ? 0 : previousTermNum;
        futureTermNum = Objects.isNull(futureTermNum) || futureTermNum < 0 ? 0 : futureTermNum;
        List<TermVo> termVoList = new ArrayList<>();
        List<TermVo> previousTermVoList = new ArrayList<>();
        List<TermVo> futureTermVoList = new ArrayList<>();
        for (Term term : termList) {
            LocalDate startDate = dateToLocalDate(term.getStartDate());
            LocalDate endDate = dateToLocalDate(term.getEndDate());
            TermVo termVo = new TermVo();
            termVo.setId(term.getId());
            termVo.setSchoolYear(term.getSchoolYear());
            termVo.setTerm(String.valueOf(term.getTerm().getIndex()));
            termVo.setStartDate(TimeUtils.dateToStr(term.getStartDate()));
            termVo.setEndDate(TimeUtils.dateToStr(term.getEndDate()));
            termVo.setDescription(term.getDescription());
            if ((startDate.isBefore(today) && endDate.isAfter(today)) || startDate.isEqual(today) || endDate.isEqual(today)) {
                termVo.setTermType(TERM_TYPE_NOW_TERM);
                termVoList.add(termVo);
            } else if (endDate.isBefore(today)) {
                termVo.setTermType(TERM_TYPE_PREVIOUS);
                previousTermVoList.add(termVo);
            } else if (startDate.isAfter(today)) {
                termVo.setTermType(TERM_TYPE_FUTURE);
                futureTermVoList.add(termVo);
            }
            termVo.setSchoolYearTermNickName(term.getNickName());
        }
        termVoList.addAll(previousTermVoList
                .stream()
                .sorted(Comparator.comparing(TermVo::getStartDate).reversed())
                .limit(previousTermNum).collect(Collectors.toList()));
        termVoList.addAll(futureTermVoList
                .stream()
                .sorted(Comparator.comparing(TermVo::getStartDate))
                .limit(futureTermNum)
                .collect(Collectors.toList()));

        return termVoList.stream().sorted(Comparator.comparing(TermVo::getStartDate)).collect(Collectors.toList());
    }

    public Term saveAndUpdateTerm(TermResource termResource) {
        Term term = new Term();
        Term historyTerm = new Term();
        term.setId(termResource.getSchoolYear() + "-" + termResource.getTerm());
        if (ObjectUtils.isNotEmpty(termResource.getId())) {
            historyTerm = termRepository.getOne(termResource.getId());
            term.setId(historyTerm.getId());
        }
        term.setTerm(TermType.getTermType(Integer.valueOf(termResource.getTerm())));
        term.setSchoolYear(termResource.getSchoolYear());
        term.setDescription(ObjectUtils.isEmpty(termResource.getDescription()) ? term.getTerm().getName() : termResource.getDescription());
        term.setStartDate(stringToDate(DATE, termResource.getStartDate()));
        term.setEndDate(stringToDate(DATE, termResource.getEndDate()));
        term.setNickName(termResource.getSchoolYearTermNickName());
        boolean result = !ObjectUtils.isNotEmpty(termResource.getId()) || !term.equals(historyTerm);
        term = termRepository.save(term);
        if (dataVisualEnable && result) {
            sendTermEvent(term);
        }
        return term;

    }

    private void sendTermEvent(Term term) {
        TermEvent termEvent = new TermEvent();
        termEvent.setId(term.getId());
        termEvent.setSchoolYear(term.getSchoolYear());
        termEvent.setTerm(term.getTerm().getIndex());
        termEvent.setDescription(term.getDescription());
        termEvent.setSource(DATA_VISUAL_SOURCE_CLOUD_CLASSROOM);
        termEvent.setStartDate(DateUtils.formatDate(DATE, term.getStartDate()));
        termEvent.setEndDate(DateUtils.formatDate(DATE, term.getEndDate()));
        termEvent.setModifyTime(DateUtils.formatDate(DATE_TIME, new Date()));
        eventService.sendTermEvent(termEvent);
    }

    public List<TermTypeResource> getTermTypes() {
        return Arrays.stream(TermType.values()).map(termType -> new TermTypeResource() {{
            this.termTypeId(termType.getIndex());
            this.termtypeName(termType.getName());
        }}).collect(Collectors.toList());
    }

    /**
     * 根据日期获取所在学年学期
     */
    public Term getTermByDate(Date courseDate) {
        return termRepository.getTermByDate(courseDate);
    }

}
