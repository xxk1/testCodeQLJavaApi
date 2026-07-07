package com.lztech.site.until;

import com.alibaba.fastjson.JSON;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphDomain;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNode;
import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeRelationship;
import com.lztech.domain.model.knowledgegraph.enums.KnowledgeNodeLevel;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphCorrelationNodeInfo;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeDetailTreeModel;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeTreeModel;
import com.lztech.site.viewmodel.courseknowledgegraph.ImportCourseKnowledgeGraphNode;
import com.lztech.site.viewmodel.courseknowledgegraphstatistics.LevelKnowledgePointNumberVo;
import com.lztech.site.viewmodel.coursemanagement.CourseResourceKnowledgePointSqlVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.neo4j.driver.Values.parameters;

@Component
public class Neo4jUtil {

    @Autowired
    private Session neo4jSession;
    private static final Logger LOG = LoggerFactory.getLogger(Neo4jUtil.class);

    public Result executeCypherSql(String cypherSql) {
        return neo4jSession.run(cypherSql);
    }

    public Result executeCypherSql(String cypherSql, Value value) {
        return neo4jSession.run(cypherSql, value);
    }

    private List<Record> executeCypherSqlReturnList(String cypher, Map<String, Object> params) {
        Result result = neo4jSession.run(cypher, params);
        return result.list();
    }

    /**
     * 创建根节点
     *
     * @param courseKnowledgeGraphNode
     * @param courseKnowledgeGraphDomainId
     */
    public void createRootCourseKnowledgeGraphNode(CourseKnowledgeGraphNode courseKnowledgeGraphNode,
                                                   String courseKnowledgeGraphDomainId) {
        try {
            String propertiesString = getFilterPropertiesJson(JSON.toJSONString(courseKnowledgeGraphNode));
            String cypherSql = String.format("create (n:`%s` %s) return n", courseKnowledgeGraphDomainId, propertiesString);
            LOG.info("创建根节点脚本->{}", cypherSql);
            executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param courseKnowledgeGraphDomainId
     * @return 获取知识节点个数
     */
    public int getDomainNodeCount(String courseKnowledgeGraphDomainId) {
        int i = 0;
        try {
            String cypherSql = String.format("MATCH (n:`%s`) RETURN count(n) as nodeCounts", courseKnowledgeGraphDomainId);
            LOG.info("获取知识节点个数脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        i = Integer.parseInt(value.toString());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * @param courseKnowledgeGraphDomainId
     * @return 获取知识图谱关系个数
     */
    public int getDomainRelationShipCount(String courseKnowledgeGraphDomainId) {
        int i = 0;
        try {
            String cypherSql = String.format("MATCH (n:`%s`) -[r]->(m:`%s`) RETURN count(r) as relationShipCounts",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId);
            LOG.info("获取知识关系个数脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        i = Integer.parseInt(value.toString());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * @param courseKnowledgeGraphDomainId
     * @param parentCourseKnowledgeGraphNodeId
     * @return 统计子节点个数
     */
    public int getDomainChildNodeCount(String courseKnowledgeGraphDomainId, String parentCourseKnowledgeGraphNodeId) {
        int i = 0;
        try {
            String cypherSql = String.format("match (n:`%s`{id:'%s'})-[:PARENT_CHILD]->(m:`%s`) " +
                            "RETURN count(m) as childNodeCounts", courseKnowledgeGraphDomainId,
                    parentCourseKnowledgeGraphNodeId, courseKnowledgeGraphDomainId);
            LOG.info("获取子节点个数脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        i = Integer.parseInt(value.toString());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * @param courseKnowledgeGraphDomainId
     * @return 根据知识图谱id获取知识图谱树形结构
     */
    public CourseKnowledgeGraphNodeTreeModel getNodeTree(String courseKnowledgeGraphDomainId, String rootCourseKnowledgeGraphNodeId) {
        try {
            String cypherSql = String.format("MATCH p=(n:`%s`{id:'%s'})-[:PARENT_CHILD*]->(m:`%s`) with collect(p) " +
                            "as ps call apoc.convert.toTree(ps) yield value return value",
                    courseKnowledgeGraphDomainId, rootCourseKnowledgeGraphNodeId, courseKnowledgeGraphDomainId);
            LOG.info("获取知识图谱树形结构脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    Map<String, Object> map = value.asMap();
                    return getMapToObject(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param courseKnowledgeGraphDomainId
     * @return 根据知识图谱id获取知识图谱树形结构
     */
    public CourseKnowledgeGraphNodeDetailTreeModel getNodeDetailTree(String courseKnowledgeGraphDomainId, String rootCourseKnowledgeGraphNodeId) {
        try {
            String cypherSql = String.format("MATCH p=(n:`%s`{id:'%s'})-[:PARENT_CHILD*]->(m:`%s`) with collect(p) " +
                            "as ps call apoc.convert.toTree(ps) yield value return value",
                    courseKnowledgeGraphDomainId, rootCourseKnowledgeGraphNodeId, courseKnowledgeGraphDomainId);
            LOG.info("获取知识图谱树形结构脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    Map<String, Object> map = value.asMap();
                    return getMapToDetailObject(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CourseKnowledgeGraphNodeDetailTreeModel getMapToDetailObject(Map<String, Object> map) {
        CourseKnowledgeGraphNodeDetailTreeModel courseKnowledgeGraphNodeTreeModel = new CourseKnowledgeGraphNodeDetailTreeModel();
        courseKnowledgeGraphNodeTreeModel.setId(String.valueOf(map.get("id")));
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(String.valueOf(map.get("knowledgeNodeName")));
        int knowledgeNodeLevel = Integer.parseInt(map.get("knowledgeNodeLevel").toString());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevel).getIndex());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelName(KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevel).getName());
        courseKnowledgeGraphNodeTreeModel.setContentDetail(String.valueOf(map.get("contentDetail")));
        courseKnowledgeGraphNodeTreeModel.setLevelType(Objects.isNull(map.get("levelType")) ? null :
                Integer.parseInt(map.get("levelType").toString()));
        int sort = Integer.parseInt(map.get("sort").toString());
        courseKnowledgeGraphNodeTreeModel.setSort(sort);
        List<Map<String, Object>> parentChildList = (List<Map<String, Object>>) map.get("parent_child");
        if (CollectionUtils.isNotEmpty(parentChildList)) {
            for (Map<String, Object> stringObjectMap : parentChildList) {
                CourseKnowledgeGraphNodeDetailTreeModel childCourseKnowledgeGraphNodeTreeModel =
                        getMapToDetailObject(stringObjectMap);
                courseKnowledgeGraphNodeTreeModel.addSubsetCourseKnowledgeGraphNodeTreeListItem(childCourseKnowledgeGraphNodeTreeModel);
                List<CourseKnowledgeGraphNodeDetailTreeModel> subsetCourseKnowledgeGraphNodeTreeList =
                        courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList();
                List<CourseKnowledgeGraphNodeDetailTreeModel> sortSubsetCourseKnowledgeGraphNodeTreeList =
                        subsetCourseKnowledgeGraphNodeTreeList.stream()
                                .sorted(Comparator.comparing(CourseKnowledgeGraphNodeDetailTreeModel::getSort,
                                        Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
                courseKnowledgeGraphNodeTreeModel.setSubsetCourseKnowledgeGraphNodeTreeList(sortSubsetCourseKnowledgeGraphNodeTreeList);

            }
        }
        return courseKnowledgeGraphNodeTreeModel;
    }

    private CourseKnowledgeGraphNodeTreeModel getMapToObject(Map<String, Object> map) {
        CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel = new CourseKnowledgeGraphNodeTreeModel();
        courseKnowledgeGraphNodeTreeModel.setId(String.valueOf(map.get("id")));
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(String.valueOf(map.get("knowledgeNodeName")));
        int knowledgeNodeLevel = Integer.parseInt(map.get("knowledgeNodeLevel").toString());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevel).getIndex());
        courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelName(KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevel).getName());
        int sort = Integer.parseInt(map.get("sort").toString());
        courseKnowledgeGraphNodeTreeModel.setSort(sort);
        List<Map<String, Object>> parentChildList = (List<Map<String, Object>>) map.get("parent_child");
        if (CollectionUtils.isNotEmpty(parentChildList)) {
            for (Map<String, Object> stringObjectMap : parentChildList) {
                CourseKnowledgeGraphNodeTreeModel childCourseKnowledgeGraphNodeTreeModel =
                        getMapToObject(stringObjectMap);
                courseKnowledgeGraphNodeTreeModel.addSubsetCourseKnowledgeGraphNodeTreeListItem(childCourseKnowledgeGraphNodeTreeModel);
                List<CourseKnowledgeGraphNodeTreeModel> subsetCourseKnowledgeGraphNodeTreeList =
                        courseKnowledgeGraphNodeTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList();
                List<CourseKnowledgeGraphNodeTreeModel> sortSubsetCourseKnowledgeGraphNodeTreeList =
                        subsetCourseKnowledgeGraphNodeTreeList.stream()
                                .sorted(Comparator.comparing(CourseKnowledgeGraphNodeTreeModel::getSort,
                                        Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
                courseKnowledgeGraphNodeTreeModel.setSubsetCourseKnowledgeGraphNodeTreeList(sortSubsetCourseKnowledgeGraphNodeTreeList);

            }
        }
        return courseKnowledgeGraphNodeTreeModel;
    }


    public String getFilterPropertiesJson(String jsonStr) {
        String propertiesString = jsonStr.replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2"); // 去掉key的引号
        return propertiesString;
    }

    /**
     * 根据id查询数据
     */
    public CourseKnowledgeGraphNode getCourseKnowledgeGraphNode(String courseKnowledgeGraphDomainId, String courseKnowledgeGraphNodeId) {
        try {
            CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
            String cypherSql = String.format("MATCH (n:`%s`{id:$id}) return n", courseKnowledgeGraphDomainId);
            Value parameterValue = parameters("id", courseKnowledgeGraphNodeId);
            LOG.info("根据id查询知识节点详情脚本->{},courseKnowledgeGraphNodeId->{}", cypherSql, courseKnowledgeGraphNodeId);
            Result result = executeCypherSql(cypherSql, parameterValue);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    Map<String, Object> map = value.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNode, map);
                    return courseKnowledgeGraphNode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id查询根节点
     */
    public CourseKnowledgeGraphNode getRootCourseKnowledgeGraphNode(String courseKnowledgeGraphDomainId) {
        try {
            CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
            String cypherSql = String.format("MATCH (n:`%s`{knowledgeNodeLevel:0}) return n limit 1", courseKnowledgeGraphDomainId);
            LOG.info("根据知识图谱id查询根节点脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    Map<String, Object> map = value.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNode, map);
                    return courseKnowledgeGraphNode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据父节点id创建子节点
     */
    public void createChildCourseKnowledgeGraphNode(String courseKnowledgeGraphDomainId, String parentCourseKnowledgeGraphNodeId,
                                                    CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        try {
            String propertiesString = getFilterPropertiesJson(JSON.toJSONString(courseKnowledgeGraphNode));
            String cypherSql = String.format("create (n:`%s` %s) return n", courseKnowledgeGraphDomainId, propertiesString);
            LOG.info("创建知识节点脚本->{}", cypherSql);
            List<HashMap<String, Object>> graphNodeList = getGraphNode(cypherSql);
            if (graphNodeList.size() > 0) {
                String rSql = String.format(
                        "match(n:`%s`),(m:`%s`) where n.id='%s' and m.id='%s' create (n)-[r:PARENT_CHILD {name:'父子关系'," +
                                "createTimestamp:timestamp()}]->(m) return r",
                        courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId,
                        parentCourseKnowledgeGraphNodeId, courseKnowledgeGraphNode.getId());
                LOG.info("创建父知识节点和子知识节点父子关系脚本->{}", rSql);
                List<HashMap<String, Object>> rshipList = getGraphRelationShip(rSql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<HashMap<String, Object>> getGraphNode(String cypherSql) {
        List<HashMap<String, Object>> ents = new ArrayList<HashMap<String, Object>>();
        try {
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    List<Pair<String, Value>> f = recordItem.fields();
                    for (Pair<String, Value> pair : f) {
                        HashMap<String, Object> rss = new HashMap<String, Object>();
                        String typeName = pair.value().type().name();
                        if (typeName.equals("NODE")) {
                            Node noe4jNode = pair.value().asNode();
                            String uuid = String.valueOf(noe4jNode.id());
                            Map<String, Object> map = noe4jNode.asMap();
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                rss.put(key, entry.getValue());
                            }
                            rss.put("uuid", uuid);
                            ents.add(rss);
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ents;
    }

    public List<HashMap<String, Object>> getGraphRelationShip(String cypherSql) {
        List<HashMap<String, Object>> ents = new ArrayList<HashMap<String, Object>>();
        try {
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    List<Pair<String, Value>> f = recordItem.fields();
                    for (Pair<String, Value> pair : f) {
                        HashMap<String, Object> rss = new HashMap<String, Object>();
                        String typeName = pair.value().type().name();
                        if (typeName.equals("RELATIONSHIP")) {
                            Relationship rship = pair.value().asRelationship();
                            String uuid = String.valueOf(rship.id());
                            String sourceid = String.valueOf(rship.startNodeId());
                            String targetid = String.valueOf(rship.endNodeId());
                            Map<String, Object> map = rship.asMap();
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                rss.put(key, entry.getValue());
                            }
                            rss.put("uuid", uuid);
                            rss.put("sourceid", sourceid);
                            rss.put("targetid", targetid);
                            ents.add(rss);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ents;
    }


    public void deleteKnowledgeGraphNodeAndChildKnowledgeGraphNode(String courseKnowledgeGraphDomainId,
                                                                   CourseKnowledgeGraphNode courseKnowledgeGraphNode) {
        try {
            /*删除该节点的所有子节点和对应关系脚本*/
            String deleteChildKnowledgeGraphNodeSql = String.format("MATCH (parent:`%s`)-[r:PARENT_CHILD*]->(child) " +
                            " WHERE parent.id = '%s' OPTIONAL MATCH (child)-[r2]-() DELETE child, r2",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphNode.getId());
            LOG.info("删除该节点的所有子节点和对应关系脚本->{}", deleteChildKnowledgeGraphNodeSql);
            executeCypherSql(deleteChildKnowledgeGraphNodeSql);
            /*删除该节点及对应关系脚本*/
            String deleteKnowledgeGraphNodeSql = String.format("MATCH (parent:`%s`)-[r]-(child)  WHERE parent.id = '%s' DELETE parent, r",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphNode.getId());
            LOG.info("删除该节点及对应关系脚本->{}", deleteKnowledgeGraphNodeSql);
            executeCypherSql(deleteKnowledgeGraphNodeSql);
            /*修改同级知识点sort顺序*/
            String updateNodeSortSql = String.format("MATCH(n:`%s`{knowledgeNodeLevel:%s}) where n.sort>%s set n.sort = n.sort-1",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphNode.getKnowledgeNodeLevel(), courseKnowledgeGraphNode.getSort());
            LOG.info("修改同级知识点sort顺序->{}", updateNodeSortSql);
            executeCypherSql(updateNodeSortSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourseKnowledgeGraphNode(String cypherSql, Value value) {
        try {
            LOG.info("更新知识节点内容数据脚本->{}", cypherSql);
            LOG.info("value->{}", value);
            executeCypherSql(cypherSql, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CourseKnowledgeGraphNodeRelationship> getCourseKnowledgeGraphNodeRelationshipList(
            String courseKnowledgeGraphDomainId, String courseKnowledgeGraphNodeId) {
        List<CourseKnowledgeGraphNodeRelationship> courseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
        String cypherSql = String.format("MATCH (n:`%s`{id:'%s'})-[r]-(m)  where type(r) in ['FRONT_REAR','RELEVANCE'] " +
                        " RETURN DISTINCT  startNode(r).id as startNodeId ,startNode(r).knowledgeNodeName as startNodeName ," +
                        " startNode(r).knowledgeNodeLevel as startKnowledgeNodeLevel, " +
                        " endNode(r).id as endNodeId,endNode(r).knowledgeNodeName as endNodeName ," +
                        " endNode(r).knowledgeNodeLevel as endKnowledgeNodeLevel," +
                        " id(r) as relationshipId, " +
                        "type(r) as relationshipType,  r.name as relationshipName,r.createTimestamp as relationshipCreateTimestamp",
                courseKnowledgeGraphDomainId, courseKnowledgeGraphNodeId);
        LOG.info("获取对应知识节点关系列表脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship = new CourseKnowledgeGraphNodeRelationship();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNodeRelationship, map);
                    courseKnowledgeGraphNodeRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphNodeRelationshipList;
    }

    public void deleteNodeRelationship(String courseKnowledgeGraphDomainId, String relationshipId) {
        String cypherSql = String.format("MATCH(n:`%s`)-[r]-(m:`%s`) where id(r) = %s delete r",
                courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, relationshipId);
        LOG.info("删除知识图谱对应关系脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验两个节点之间是否存在前后置关系
     *
     * @param courseKnowledgeGraphDomainId 图谱id
     * @param firstKnowledgeGraphNodeId    第一节点id
     * @param secondKnowledgeGraphNodeId   第二节点id
     * @return
     */
    public Boolean checkFrontRearRelationship(
            String courseKnowledgeGraphDomainId, String firstKnowledgeGraphNodeId, String secondKnowledgeGraphNodeId) {
        int i = 0;
        try {
            String cypherSql = String.format("match(n:`%s`)-[r:FRONT_REAR]-(m:`%s`) " +
                            "where n.id='%s' and m.id='%s' return count(r) as relationShipCounts",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, firstKnowledgeGraphNodeId, secondKnowledgeGraphNodeId);
            LOG.info("查询两个节点间是否存在前后置关系脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        i = Integer.parseInt(value.toString());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i > 0;
    }

    /**
     * 校验两个节点之间是否存在前后置关系
     *
     * @param courseKnowledgeGraphDomainId 图谱id
     * @param firstKnowledgeGraphNodeId    第一节点id
     * @param secondKnowledgeGraphNodeId   第二节点id
     * @return
     */
    public Boolean checkRelevanceRelationship(
            String courseKnowledgeGraphDomainId, String firstKnowledgeGraphNodeId, String secondKnowledgeGraphNodeId) {
        int i = 0;
        try {
            String cypherSql = String.format("match(n:`%s`)-[r:RELEVANCE]-(m:`%s`) " +
                            "where n.id='%s' and m.id='%s' return count(r) as relationShipCounts",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, firstKnowledgeGraphNodeId, secondKnowledgeGraphNodeId);
            LOG.info("查询两个节点间是否存在关联关系脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        i = Integer.parseInt(value.toString());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i > 0;
    }

    /**
     * 根据两个节点id创建父子关系
     *
     * @param courseKnowledgeGraphDomainId 图谱id
     * @param preKnowledgeGraphNodeId      前置节点id
     * @param postKnowledgeGraphNodeId     后置节点id
     */
    public void createParentChildRelationship(
            String courseKnowledgeGraphDomainId, String preKnowledgeGraphNodeId, String postKnowledgeGraphNodeId) {
        try {
            String cypherSql = String.format("match(n:`%s`),(m:`%s`) where n.id='%s' and m.id='%s' " +
                            "create (n)-[r:PARENT_CHILD {name:'父子关系',createTimestamp:timestamp()}]->(m) return r",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, preKnowledgeGraphNodeId, postKnowledgeGraphNodeId);
            LOG.info("根据两个节点id创建父子关系脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据两个节点id创建前后置关系
     *
     * @param courseKnowledgeGraphDomainId 图谱id
     * @param preKnowledgeGraphNodeId      前置节点id
     * @param postKnowledgeGraphNodeId     后置节点id
     */
    public String createFrontRearRelationship(
            String courseKnowledgeGraphDomainId, String preKnowledgeGraphNodeId, String postKnowledgeGraphNodeId) {
        String relationshipId = "";
        try {
            String cypherSql = String.format("match(n:`%s`),(m:`%s`) where n.id='%s' and m.id='%s' " +
                            "create (n)-[r:FRONT_REAR {name:'前后置关系',createTimestamp:timestamp()}]->(m) return id(r) ",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, preKnowledgeGraphNodeId, postKnowledgeGraphNodeId);
            LOG.info("根据两个节点id创建前后置关系脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        relationshipId = value.toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationshipId;

    }

    /**
     * 根据两个节点id创建关联关系
     *
     * @param courseKnowledgeGraphDomainId 图谱id
     * @param preKnowledgeGraphNodeId      前置节点id
     * @param postKnowledgeGraphNodeId     后置节点id
     */
    public String createRelevanceRelationship(
            String courseKnowledgeGraphDomainId, String preKnowledgeGraphNodeId, String postKnowledgeGraphNodeId) {
        String relationshipId = "";
        try {
            String cypherSql = String.format("match(n:`%s`),(m:`%s`) where n.id='%s' and m.id='%s' " +
                            "create (n)-[r:RELEVANCE {name:'关联关系',createTimestamp:timestamp()}]->(m) return id(r)",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, preKnowledgeGraphNodeId, postKnowledgeGraphNodeId);
            LOG.info("根据两个节点id创建关联关系脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    String t = value.type().name();
                    if (t.equals("INTEGER")) {
                        relationshipId = value.toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationshipId;
    }


    public List<String> getDeleteChildKnowledgeGraphNodeIdList(String courseKnowledgeGraphDomainId,
                                                               String courseKnowledgeGraphNodeId) {
        List<String> knowledgeGraphNodeIdList = new ArrayList<>();
        try {
            /*获取所有子节点*/
            String getChildKnowledgeGraphNodeSql = String.format("MATCH (parent:`%s`)-[r:PARENT_CHILD*]->(child) " +
                            " WHERE parent.id = '%s' OPTIONAL MATCH (child)-[r2]-() return child.id as childKnowledgeGraphNodeId",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphNodeId);
            LOG.info("获取所有子节点脚本->{}", getChildKnowledgeGraphNodeSql);
            knowledgeGraphNodeIdList.add(courseKnowledgeGraphNodeId);
            Result result = executeCypherSql(getChildKnowledgeGraphNodeSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    Map<String, Object> map = recordItem.asMap();
                    knowledgeGraphNodeIdList.add(map.get("childKnowledgeGraphNodeId").toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return knowledgeGraphNodeIdList;
    }

    public List<CourseKnowledgeGraphNode> getCourseKnowledgeGraphDomainAllNodeList(String courseKnowledgeGraphDomainId) {
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        try {
            String cypherSql = String.format("MATCH (n:`%s`) return n ", courseKnowledgeGraphDomainId);
            LOG.info("获取图谱所有节点脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    for (Value value : recordItem.values()) {
                        CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                                new CourseKnowledgeGraphNode();
                        Map<String, Object> map = value.asMap();
                        BeanUtils.populate(courseKnowledgeGraphNode, map);
                        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphNodeList;
    }

    public List<CourseKnowledgeGraphNodeRelationship> getCourseKnowledgeGraphDomainAllRelationshipList(
            String courseKnowledgeGraphDomainId) {
        List<CourseKnowledgeGraphNodeRelationship> courseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
        try {
            String cypherSql = String.format("MATCH (n:`%s`)-[r]-(m:`%s`)  " +
                            "where type(r) in ['PARENT_CHILD','FRONT_REAR','RELEVANCE'] " +
                            "RETURN DISTINCT  startNode(r).id as startNodeId ,startNode(r).knowledgeNodeName as startNodeName , " +
                            "endNode(r).id as endNodeId,endNode(r).knowledgeNodeName as endNodeName , " +
                            "id(r) as relationshipId, " +
                            "type(r) as relationshipType,  r.name as relationshipName,r.createTimestamp as relationshipCreateTimestamp",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId);
            LOG.info("获取图谱所有关系->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship =
                            new CourseKnowledgeGraphNodeRelationship();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNodeRelationship, map);
                    courseKnowledgeGraphNodeRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphNodeRelationshipList;
    }


    public List<CourseResourceKnowledgePointSqlVo> getCourseResourceKnowledgePointList(String courseKnowledgeGraphDomainId, List<String> detailIds) {
        List<CourseResourceKnowledgePointSqlVo> pointSqlVoList = new ArrayList<>();
        String cypherSql = String.format("MATCH (n:`%s`) where n.id in $detailIds return n.id as pointId,n.knowledgeNodeName as pointContent ",
                courseKnowledgeGraphDomainId);
        Value parameterValue = parameters("detailIds", detailIds);
        LOG.info("获取对应知识节点关系列表脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql, parameterValue);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseResourceKnowledgePointSqlVo pointSqlVo = new CourseResourceKnowledgePointSqlVo();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(pointSqlVo, map);
                    pointSqlVoList.add(pointSqlVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointSqlVoList;
    }

    public void deleteCourseKnowledgeGraphDomainParentChildRelationship(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        /*删除该节点的所有子节点和对应关系脚本*/
        String deleteChildKnowledgeGraphNodeSql = String.format("MATCH (parent:`%s`)-[r:PARENT_CHILD]->(child:`%s`) DELETE r; ",
                courseKnowledgeGraphDomain.getId(), courseKnowledgeGraphDomain.getId());
        LOG.info("删除该图谱的所有节点和对应的父子关系脚本->{}", deleteChildKnowledgeGraphNodeSql);
        try {
            executeCypherSql(deleteChildKnowledgeGraphNodeSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CourseKnowledgeGraphNode> childCourseKnowledgeGraphNodeList(
            String courseKnowledgeGraphDomainId, String parentCourseKnowledgeGraphNodeId) {
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        try {
            String cypherSql = String.format("MATCH (n:`%s`)-[r:PARENT_CHILD]->(m:`%s`) " +
                            "where n.id='%s' return m ", courseKnowledgeGraphDomainId,
                    courseKnowledgeGraphDomainId, parentCourseKnowledgeGraphNodeId);
            LOG.info("获取图谱{}下同层级知识点脚本->{}", parentCourseKnowledgeGraphNodeId, cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    for (Value value : recordItem.values()) {
                        CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                                new CourseKnowledgeGraphNode();
                        Map<String, Object> map = value.asMap();
                        BeanUtils.populate(courseKnowledgeGraphNode, map);
                        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphNodeList;
    }

    public void createCourseKnowledgeGraphNode(String courseKnowledgeGraphDomainId, String
            parentCourseKnowledgeGraphNodeId, ImportCourseKnowledgeGraphNode importCourseKnowledgeGraphNode) {
        try {
            CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
            courseKnowledgeGraphNode.setId(importCourseKnowledgeGraphNode.getId());
            courseKnowledgeGraphNode.setKnowledgeNodeName(importCourseKnowledgeGraphNode.getKnowledgeNodeName());
            courseKnowledgeGraphNode.setLevelType(importCourseKnowledgeGraphNode.getLevelType());
            courseKnowledgeGraphNode.setContentDetail(importCourseKnowledgeGraphNode.getContentDetail());
            courseKnowledgeGraphNode.setKnowledgeNodeLevel(importCourseKnowledgeGraphNode.getKnowledgeNodeLevel());
            courseKnowledgeGraphNode.setSort(importCourseKnowledgeGraphNode.getSort());
            courseKnowledgeGraphNode.setCreateTime(importCourseKnowledgeGraphNode.getCreateTime());
            courseKnowledgeGraphNode.setCreatorId(importCourseKnowledgeGraphNode.getCreatorId());
            courseKnowledgeGraphNode.setCreatorName(importCourseKnowledgeGraphNode.getCreatorName());
            courseKnowledgeGraphNode.setModifyTime(importCourseKnowledgeGraphNode.getModifyTime());
            courseKnowledgeGraphNode.setModifierId(importCourseKnowledgeGraphNode.getModifierId());
            courseKnowledgeGraphNode.setModifierName(importCourseKnowledgeGraphNode.getModifierName());
            courseKnowledgeGraphNode.setName(importCourseKnowledgeGraphNode.getName());

            String propertiesString = getFilterPropertiesJson(JSON.toJSONString(courseKnowledgeGraphNode));
            String cypherSql = String.format("create (n:`%s` %s) return n", courseKnowledgeGraphDomainId, propertiesString);
            LOG.info("创建知识节点脚本->{}", cypherSql);
            List<HashMap<String, Object>> graphNodeList = getGraphNode(cypherSql);
            if (graphNodeList.size() > 0) {
                String rSql = String.format(
                        "match(n:`%s`),(m:`%s`) where n.id='%s' and m.id='%s' create (n)-[r:PARENT_CHILD {name:'父子关系'," +
                                "createTimestamp:timestamp()}]->(m) return r",
                        courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId,
                        parentCourseKnowledgeGraphNodeId, courseKnowledgeGraphNode.getId());
                LOG.info("创建父知识节点和子知识节点父子关系脚本->{}", rSql);
                List<HashMap<String, Object>> rshipList = getGraphRelationShip(rSql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CourseKnowledgeGraphNode getParentCourseKnowledgeGraphNode(String courseKnowledgeGraphDomainId, String
            courseKnowledgeGraphNodeId) {
        try {
            CourseKnowledgeGraphNode courseKnowledgeGraphNode = new CourseKnowledgeGraphNode();
            String cypherSql = String.format("MATCH (n:`%s`)-[:PARENT_CHILD]->(m:`%s`) where m.id='%s' return n limit 1",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, courseKnowledgeGraphNodeId);
            LOG.info("根据知识图谱id查询父节点脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                Record record = result.next();
                for (Value value : record.values()) {
                    Map<String, Object> map = value.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNode, map);
                    return courseKnowledgeGraphNode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteNodeFrontRelationshipList(String courseKnowledgeGraphDomainId, String id) {
        String cypherSql = String.format("MATCH (n:`%s`)<-[r:FRONT_REAR]-(m)  " +
                "where  n.id ='%s' delete r ", courseKnowledgeGraphDomainId, id);
        LOG.info("删除知识节点对应前置关系脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNodeBehindRelationshipList(String courseKnowledgeGraphDomainId, String id) {
        String cypherSql = String.format("MATCH (n:`%s`)-[r:FRONT_REAR]->(m)  " +
                "where  n.id ='%s' delete r ", courseKnowledgeGraphDomainId, id);
        LOG.info("删除知识节点对应前置关系脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNodeRelevanceRelationshipList(String courseKnowledgeGraphDomainId, String id) {
        String cypherSql = String.format("MATCH (n:`%s`)-[r]-(m)  " +
                "where type(r) in ['RELEVANCE'] and n.id ='%s' delete r ", courseKnowledgeGraphDomainId, id);
//        Value parameterValue = parameters("ids", ids);
        LOG.info("删除知识节点对应关联关系脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断两个节点之间是否存在关系，存在则删除
     *
     * @param courseKnowledgeGraphDomainId 图谱id
     * @param firstKnowledgeGraphNodeId    第一节点id
     * @param secondKnowledgeGraphNodeId   第二节点id
     * @return
     */
    public Boolean checkRelationshipAndDelete(
            String courseKnowledgeGraphDomainId, String firstKnowledgeGraphNodeId, String secondKnowledgeGraphNodeId) {
        int i = 0;
        try {
            String cypherSql = String.format("match(n:`%s`)-[r]-(m:`%s`) " +
                            "where n.id='%s' and m.id='%s' and type(r) in ['FRONT_REAR','RELEVANCE'] delete r ",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId, firstKnowledgeGraphNodeId, secondKnowledgeGraphNodeId);
            LOG.info("查询两个节点间是否存在关系,存在即删除脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i > 0;
    }

    public CourseKnowledgeGraphNodeRelationship getCourseKnowledgeGraphNodeRelationship(
            String courseKnowledgeGraphDomainId, String relationshipId) {
        List<CourseKnowledgeGraphNodeRelationship> courseKnowledgeGraphNodeRelationshipList = new ArrayList<>();
        String cypherSql = String.format("MATCH (n:`%s`)-[r]-(m)  where type(r) in ['FRONT_REAR','RELEVANCE']  and id(r)=%s" +
                        " RETURN DISTINCT  startNode(r).id as startNodeId ,startNode(r).knowledgeNodeName as startNodeName ," +
                        " startNode(r).knowledgeNodeLevel as startKnowledgeNodeLevel, " +
                        " endNode(r).id as endNodeId,endNode(r).knowledgeNodeName as endNodeName ," +
                        " endNode(r).knowledgeNodeLevel as endKnowledgeNodeLevel," +
                        " id(r) as relationshipId, " +
                        "type(r) as relationshipType,  r.name as relationshipName,r.createTimestamp as relationshipCreateTimestamp",
                courseKnowledgeGraphDomainId, relationshipId);
        LOG.info("获取对应知识节点关系列表脚本->{}", cypherSql);
        try {
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship = new CourseKnowledgeGraphNodeRelationship();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNodeRelationship, map);
                    courseKnowledgeGraphNodeRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isNotEmpty(courseKnowledgeGraphNodeRelationshipList)) {
            return courseKnowledgeGraphNodeRelationshipList.get(0);
        } else {
            return null;
        }
    }


    public List<CourseKnowledgeGraphNode> getDataByDateTimeAndTeacherIdIn(String neo4jSql, Value value) {
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        try {
            LOG.info("根据条件获取对应知识点列表脚本->{}", neo4jSql);
            Result result = executeCypherSql(neo4jSql, value);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    for (Value onevalue : recordItem.values()) {
                        CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                                new CourseKnowledgeGraphNode();
                        Map<String, Object> map = onevalue.asMap();
                        BeanUtils.populate(courseKnowledgeGraphNode, map);
                        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("根据条件获取对应知识点列表数据成功，节点个数->{}", courseKnowledgeGraphNodeList.size());
        return courseKnowledgeGraphNodeList;
    }

    public List<CourseKnowledgeGraphNode> getCourseKnowledgeGraphNodeList(CourseKnowledgeGraphDomain courseKnowledgeGraphDomain) {
        List<CourseKnowledgeGraphNode> courseKnowledgeGraphNodeList = new ArrayList<>();
        try {
            String neo4jSql = String.format(" MATCH (n:`%s`) where  n.knowledgeNodeLevel <>0  RETURN n ", courseKnowledgeGraphDomain.getId());
            LOG.info("根据图谱id获取图谱对应知识点列表脚本->{}", neo4jSql);
            Result result = executeCypherSql(neo4jSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    for (Value onevalue : recordItem.values()) {
                        CourseKnowledgeGraphNode courseKnowledgeGraphNode =
                                new CourseKnowledgeGraphNode();
                        Map<String, Object> map = onevalue.asMap();
                        BeanUtils.populate(courseKnowledgeGraphNode, map);
                        courseKnowledgeGraphNodeList.add(courseKnowledgeGraphNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphNodeList;
    }

    public List<CourseKnowledgeGraphCorrelationNodeInfo> getCourseKnowledgeGraphCorrelationNodeInfoList(String cypherSql) {
        LOG.info("查询节点脚本->{}", cypherSql);
        List<CourseKnowledgeGraphCorrelationNodeInfo> courseKnowledgeGraphCorrelationNodeInfoList = new ArrayList<>();
        try {
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseKnowledgeGraphCorrelationNodeInfo courseKnowledgeGraphCorrelationNodeInfo =
                            new CourseKnowledgeGraphCorrelationNodeInfo();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(courseKnowledgeGraphCorrelationNodeInfo, map);
                    courseKnowledgeGraphCorrelationNodeInfoList.add(courseKnowledgeGraphCorrelationNodeInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphCorrelationNodeInfoList;
    }

    public List<CourseKnowledgeGraphCorrelationNodeInfo> getCourseKnowledgeGraphCorrelationNodeInfoList(
            String cypherSql, Value value) {
        LOG.info("查询节点脚本->{}", cypherSql);
        List<CourseKnowledgeGraphCorrelationNodeInfo> courseKnowledgeGraphCorrelationNodeInfoList = new ArrayList<>();
        try {
            Result result = executeCypherSql(cypherSql, value);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseKnowledgeGraphCorrelationNodeInfo courseKnowledgeGraphCorrelationNodeInfo =
                            new CourseKnowledgeGraphCorrelationNodeInfo();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(courseKnowledgeGraphCorrelationNodeInfo, map);
                    courseKnowledgeGraphCorrelationNodeInfoList.add(courseKnowledgeGraphCorrelationNodeInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphCorrelationNodeInfoList;
    }

    public List<CourseKnowledgeGraphNodeTreeModel> getCourseKnowledgeGraphNodeTreeList(String courseKnowledgeGraphDomainId,
                                                                                       List<String> nodeIdList) {
        // 1. 拼接 Cypher
        String cypher = String.format(
                "MATCH (n:`%s`) " +
                        "WHERE n.id IN $ids " +
                        "OPTIONAL MATCH (n)-[:PARENT_CHILD]->(child:`%s`) " +
                        "WHERE child.id IN $ids " +
                        "RETURN n.id AS id, n.knowledgeNodeName AS knowledgeNodeName, " +
                        "n.knowledgeNodeLevel AS knowledgeNodeLevelIndex, " +
                        "n.sort AS sort, n.courseKnowledgeGraphId AS courseKnowledgeGraphId, " +
                        "collect(child.id) AS children " +
                        "ORDER BY n.sort",
                courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId
        );

        Map<String, Object> params = new HashMap<>();
        params.put("ids", nodeIdList);

        // 2. 执行查询
        List<Record> records = executeCypherSqlReturnList(cypher, params);

        // 3. 先构建节点 Map
        Map<String, CourseKnowledgeGraphNodeTreeModel> nodeMap = new HashMap<>();
        Map<String, List<String>> childrenMap = new HashMap<>();
        Set<String> allIds = new HashSet<>();
        Set<String> childIds = new HashSet<>();

        for (Record r : records) {
            String id = r.get("id").asString();
            CourseKnowledgeGraphNodeTreeModel node = new CourseKnowledgeGraphNodeTreeModel();
            node.setId(id);
            node.setKnowledgeNodeName(r.get("knowledgeNodeName").asString(null));
            int knowledgeNodeLevel = Integer.parseInt(r.get("knowledgeNodeLevelIndex").toString());
            node.setKnowledgeNodeLevelIndex(knowledgeNodeLevel);
            KnowledgeNodeLevel knowledgeNodeLevelEnum = KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevel);
            node.setKnowledgeNodeLevelName(Objects.isNull(knowledgeNodeLevelEnum)
                    ? "" : knowledgeNodeLevelEnum.getName());
            node.setSort(r.get("sort").asInt(0));
            node.setParentId("root");
            node.setCourseKnowledgeGraphId(r.get("courseKnowledgeGraphId").asString(null));
            nodeMap.put(id, node);

            List<Object> childList = r.get("children").asList();
            List<String> childIdList = childList.stream().map(Object::toString).collect(Collectors.toList());
            childrenMap.put(id, childIdList);

            allIds.add(id);
            childIds.addAll(childIdList);
        }

        // 4. 构建树
        List<CourseKnowledgeGraphNodeTreeModel> roots = new ArrayList<>();
        for (String id : allIds) {
            if (!childIds.contains(id)) {
                roots.add(nodeMap.get(id));
            }
        }

        for (Map.Entry<String, List<String>> entry : childrenMap.entrySet()) {
            CourseKnowledgeGraphNodeTreeModel parent = nodeMap.get(entry.getKey());
            for (String childId : entry.getValue()) {
                CourseKnowledgeGraphNodeTreeModel child = nodeMap.get(childId);
                child.setParentId(parent.getId());
                parent.getSubsetCourseKnowledgeGraphNodeTreeList().add(child);

            }
        }

        return roots;
    }


    public List<CourseKnowledgeGraphNodeTreeModel> getCourseKnowledgeGraphNodeListByIdList(
            String courseKnowledgeGraphDomainId,
            List<String> relatedKnowledgePointIdList) {

        // 1. 拼接 Cypher
        String cypher = String.format(
                "MATCH (n:`%s`) WHERE n.id IN $idList RETURN n.id AS id," +
                        " n.knowledgeNodeName AS knowledgeNodeName," +
                        " n.knowledgeNodeLevel AS knowledgeNodeLevelIndex",
                courseKnowledgeGraphDomainId);
        Map<String, Object> params = new HashMap<>();
        params.put("idList", relatedKnowledgePointIdList);
        List<Record> records = executeCypherSqlReturnList(cypher, params);
        List<CourseKnowledgeGraphNodeTreeModel> courseKnowledgeGraphNodeTreeList = new ArrayList<>();
        for (Record record : records) {
            CourseKnowledgeGraphNodeTreeModel courseKnowledgeGraphNodeTreeModel = new CourseKnowledgeGraphNodeTreeModel();
            courseKnowledgeGraphNodeTreeModel.setId(record.get("id").asString());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeName(record.get("knowledgeNodeName").asString());
            int knowledgeNodeLevel = Integer.parseInt(record.get("knowledgeNodeLevelIndex").toString());
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelIndex(knowledgeNodeLevel);
            KnowledgeNodeLevel knowledgeNodeLevelEnum = KnowledgeNodeLevel.getKnowledgeNodeLevel(knowledgeNodeLevel);
            courseKnowledgeGraphNodeTreeModel.setKnowledgeNodeLevelName(Objects.isNull(knowledgeNodeLevelEnum)
                    ? "" : knowledgeNodeLevelEnum.getName());
            courseKnowledgeGraphNodeTreeList.add(courseKnowledgeGraphNodeTreeModel);
        }
        return courseKnowledgeGraphNodeTreeList;
    }

    public List<CourseKnowledgeGraphNodeRelationship> getCourseKnowledgeGraphRelationshipListByIdList(
            String courseKnowledgeGraphDomainId, List<String> detailIds) {
        List<CourseKnowledgeGraphNodeRelationship> courseKnowledgeGraphNodeRelationshipList = new ArrayList<>();

        // 添加参数校验
        if (detailIds == null || detailIds.isEmpty()) {
            return courseKnowledgeGraphNodeRelationshipList;
        }

        try {
            String cypherSql = String.format("MATCH (n:`%s`)-[r]-(m:`%s`)  " +
                            "where n.id in $idList and  type(r) in ['PARENT_CHILD','FRONT_REAR','RELEVANCE'] " +
                            "RETURN DISTINCT  startNode(r).id as startNodeId ,startNode(r).knowledgeNodeName as startNodeName , " +
                            "endNode(r).id as endNodeId,endNode(r).knowledgeNodeName as endNodeName , " +
                            "id(r) as relationshipId, " +
                            "type(r) as relationshipType,  r.name as relationshipName,r.createTimestamp as relationshipCreateTimestamp",
                    courseKnowledgeGraphDomainId, courseKnowledgeGraphDomainId);
            Value parameterValue = parameters("idList", detailIds);
            Result result = executeCypherSql(cypherSql, parameterValue);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship =
                            new CourseKnowledgeGraphNodeRelationship();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(courseKnowledgeGraphNodeRelationship, map);
                    courseKnowledgeGraphNodeRelationshipList.add(courseKnowledgeGraphNodeRelationship);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseKnowledgeGraphNodeRelationshipList;
    }


    public List<LevelKnowledgePointNumberVo> getDomainLevelNodeCount(String courseKnowledgeGraphDomainId) {
        List<LevelKnowledgePointNumberVo> levelKnowledgePointNumberVos = new ArrayList<>();

        try {
            String cypherSql = String.format("MATCH (n:`%s`)" +
                    "RETURN " +
                    " n.knowledgeNodeLevel as level," +
                    " count(n) as nodeCount" +
                    " ORDER BY level", courseKnowledgeGraphDomainId);

            LOG.info("获取各级别知识节点个数脚本->{}", cypherSql);
            Result result = executeCypherSql(cypherSql);
            if (result.hasNext()) {
                List<Record> records = result.list();
                for (Record recordItem : records) {
                    LevelKnowledgePointNumberVo levelKnowledgePointNumberVo = new LevelKnowledgePointNumberVo();
                    Map<String, Object> map = recordItem.asMap();
                    BeanUtils.populate(levelKnowledgePointNumberVo, map);
                    levelKnowledgePointNumberVos.add(levelKnowledgePointNumberVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return levelKnowledgePointNumberVos;
    }

}
