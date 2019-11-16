package com.duan.buyer.entity;

import java.util.ArrayList;
import java.util.List;

public class DatadictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatadictExample() {
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

        public Criteria andDtNameIsNull() {
            addCriterion("dt_name is null");
            return (Criteria) this;
        }

        public Criteria andDtNameIsNotNull() {
            addCriterion("dt_name is not null");
            return (Criteria) this;
        }

        public Criteria andDtNameEqualTo(String value) {
            addCriterion("dt_name =", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameNotEqualTo(String value) {
            addCriterion("dt_name <>", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameGreaterThan(String value) {
            addCriterion("dt_name >", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameGreaterThanOrEqualTo(String value) {
            addCriterion("dt_name >=", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameLessThan(String value) {
            addCriterion("dt_name <", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameLessThanOrEqualTo(String value) {
            addCriterion("dt_name <=", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameLike(String value) {
            addCriterion("dt_name like", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameNotLike(String value) {
            addCriterion("dt_name not like", value, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameIn(List<String> values) {
            addCriterion("dt_name in", values, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameNotIn(List<String> values) {
            addCriterion("dt_name not in", values, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameBetween(String value1, String value2) {
            addCriterion("dt_name between", value1, value2, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtNameNotBetween(String value1, String value2) {
            addCriterion("dt_name not between", value1, value2, "dtName");
            return (Criteria) this;
        }

        public Criteria andDtCodeIsNull() {
            addCriterion("dt_code is null");
            return (Criteria) this;
        }

        public Criteria andDtCodeIsNotNull() {
            addCriterion("dt_code is not null");
            return (Criteria) this;
        }

        public Criteria andDtCodeEqualTo(String value) {
            addCriterion("dt_code =", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeNotEqualTo(String value) {
            addCriterion("dt_code <>", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeGreaterThan(String value) {
            addCriterion("dt_code >", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dt_code >=", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeLessThan(String value) {
            addCriterion("dt_code <", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeLessThanOrEqualTo(String value) {
            addCriterion("dt_code <=", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeLike(String value) {
            addCriterion("dt_code like", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeNotLike(String value) {
            addCriterion("dt_code not like", value, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeIn(List<String> values) {
            addCriterion("dt_code in", values, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeNotIn(List<String> values) {
            addCriterion("dt_code not in", values, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeBetween(String value1, String value2) {
            addCriterion("dt_code between", value1, value2, "dtCode");
            return (Criteria) this;
        }

        public Criteria andDtCodeNotBetween(String value1, String value2) {
            addCriterion("dt_code not between", value1, value2, "dtCode");
            return (Criteria) this;
        }

        public Criteria andKeyNoIsNull() {
            addCriterion("key_no is null");
            return (Criteria) this;
        }

        public Criteria andKeyNoIsNotNull() {
            addCriterion("key_no is not null");
            return (Criteria) this;
        }

        public Criteria andKeyNoEqualTo(Integer value) {
            addCriterion("key_no =", value, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoNotEqualTo(Integer value) {
            addCriterion("key_no <>", value, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoGreaterThan(Integer value) {
            addCriterion("key_no >", value, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("key_no >=", value, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoLessThan(Integer value) {
            addCriterion("key_no <", value, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoLessThanOrEqualTo(Integer value) {
            addCriterion("key_no <=", value, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoIn(List<Integer> values) {
            addCriterion("key_no in", values, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoNotIn(List<Integer> values) {
            addCriterion("key_no not in", values, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoBetween(Integer value1, Integer value2) {
            addCriterion("key_no between", value1, value2, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNoNotBetween(Integer value1, Integer value2) {
            addCriterion("key_no not between", value1, value2, "keyNo");
            return (Criteria) this;
        }

        public Criteria andKeyNameIsNull() {
            addCriterion("key_name is null");
            return (Criteria) this;
        }

        public Criteria andKeyNameIsNotNull() {
            addCriterion("key_name is not null");
            return (Criteria) this;
        }

        public Criteria andKeyNameEqualTo(String value) {
            addCriterion("key_name =", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotEqualTo(String value) {
            addCriterion("key_name <>", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameGreaterThan(String value) {
            addCriterion("key_name >", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameGreaterThanOrEqualTo(String value) {
            addCriterion("key_name >=", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameLessThan(String value) {
            addCriterion("key_name <", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameLessThanOrEqualTo(String value) {
            addCriterion("key_name <=", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameLike(String value) {
            addCriterion("key_name like", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotLike(String value) {
            addCriterion("key_name not like", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameIn(List<String> values) {
            addCriterion("key_name in", values, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotIn(List<String> values) {
            addCriterion("key_name not in", values, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameBetween(String value1, String value2) {
            addCriterion("key_name between", value1, value2, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotBetween(String value1, String value2) {
            addCriterion("key_name not between", value1, value2, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyValueIsNull() {
            addCriterion("key_value is null");
            return (Criteria) this;
        }

        public Criteria andKeyValueIsNotNull() {
            addCriterion("key_value is not null");
            return (Criteria) this;
        }

        public Criteria andKeyValueEqualTo(String value) {
            addCriterion("key_value =", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueNotEqualTo(String value) {
            addCriterion("key_value <>", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueGreaterThan(String value) {
            addCriterion("key_value >", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueGreaterThanOrEqualTo(String value) {
            addCriterion("key_value >=", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueLessThan(String value) {
            addCriterion("key_value <", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueLessThanOrEqualTo(String value) {
            addCriterion("key_value <=", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueLike(String value) {
            addCriterion("key_value like", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueNotLike(String value) {
            addCriterion("key_value not like", value, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueIn(List<String> values) {
            addCriterion("key_value in", values, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueNotIn(List<String> values) {
            addCriterion("key_value not in", values, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueBetween(String value1, String value2) {
            addCriterion("key_value between", value1, value2, "keyValue");
            return (Criteria) this;
        }

        public Criteria andKeyValueNotBetween(String value1, String value2) {
            addCriterion("key_value not between", value1, value2, "keyValue");
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