package com.duan.buyer.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOptNameIsNull() {
            addCriterion("opt_name is null");
            return (Criteria) this;
        }

        public Criteria andOptNameIsNotNull() {
            addCriterion("opt_name is not null");
            return (Criteria) this;
        }

        public Criteria andOptNameEqualTo(String value) {
            addCriterion("opt_name =", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameNotEqualTo(String value) {
            addCriterion("opt_name <>", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameGreaterThan(String value) {
            addCriterion("opt_name >", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameGreaterThanOrEqualTo(String value) {
            addCriterion("opt_name >=", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameLessThan(String value) {
            addCriterion("opt_name <", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameLessThanOrEqualTo(String value) {
            addCriterion("opt_name <=", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameLike(String value) {
            addCriterion("opt_name like", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameNotLike(String value) {
            addCriterion("opt_name not like", value, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameIn(List<String> values) {
            addCriterion("opt_name in", values, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameNotIn(List<String> values) {
            addCriterion("opt_name not in", values, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameBetween(String value1, String value2) {
            addCriterion("opt_name between", value1, value2, "optName");
            return (Criteria) this;
        }

        public Criteria andOptNameNotBetween(String value1, String value2) {
            addCriterion("opt_name not between", value1, value2, "optName");
            return (Criteria) this;
        }

        public Criteria andOptContentIsNull() {
            addCriterion("opt_content is null");
            return (Criteria) this;
        }

        public Criteria andOptContentIsNotNull() {
            addCriterion("opt_content is not null");
            return (Criteria) this;
        }

        public Criteria andOptContentEqualTo(String value) {
            addCriterion("opt_content =", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotEqualTo(String value) {
            addCriterion("opt_content <>", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentGreaterThan(String value) {
            addCriterion("opt_content >", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentGreaterThanOrEqualTo(String value) {
            addCriterion("opt_content >=", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentLessThan(String value) {
            addCriterion("opt_content <", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentLessThanOrEqualTo(String value) {
            addCriterion("opt_content <=", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentLike(String value) {
            addCriterion("opt_content like", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotLike(String value) {
            addCriterion("opt_content not like", value, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentIn(List<String> values) {
            addCriterion("opt_content in", values, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotIn(List<String> values) {
            addCriterion("opt_content not in", values, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentBetween(String value1, String value2) {
            addCriterion("opt_content between", value1, value2, "optContent");
            return (Criteria) this;
        }

        public Criteria andOptContentNotBetween(String value1, String value2) {
            addCriterion("opt_content not between", value1, value2, "optContent");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}